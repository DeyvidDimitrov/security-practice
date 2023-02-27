package com.practice.servicea;

import com.practice.servicea.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
//    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByEmail(s)
            .orElseThrow(() -> new UsernameNotFoundException(String.format("User with email %s not found.", s)));
    }

    @Transactional
    public void register(UserCreationDto user) {
        boolean userExists = userRepository.findByEmail(user.getEmail())
            .isPresent();

        if (userExists) {
            throw new InvalidEmailException("Email already taken");
        }

        User creation = new User(
            user.getFirstName(),
            user.getLastName(),
            user.getEmail(),
            user.getPassword(),
            Role.USER
        );

//        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());

//        user.setPassword(encodedPassword);

        userRepository.save(creation);
    }
}
