package com.vedalingo.services.impl;

import com.vedalingo.dtos.LoginUserDto;
import com.vedalingo.dtos.RegisterUserDto;
import com.vedalingo.exceptions.CustomAuthenticationException;
import com.vedalingo.models.Role;
import com.vedalingo.models.User;
import com.vedalingo.repositories.UserRepository;
import com.vedalingo.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;



@Service
public class AuthServiceImpl implements AuthService {//, UserDetailsService

    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private BCryptPasswordEncoder passwordEncoder;


    @Override
    public User registerUser(RegisterUserDto registerUserDto) throws CustomAuthenticationException {

        if(this.userRepository.existsByEmail(registerUserDto.getEmail())) {
            //means already user present in db with same email
            throw  new CustomAuthenticationException("Email already registered");
        }
        User user=new User();
        user.setFirstName(registerUserDto.getFirstName());
        user.setLastName(registerUserDto.getLastName());
        user.setEmail(registerUserDto.getEmail());
//        user.setPassword(passwordEncoder.encode(registerUserDto.getPassword()));
        user.setPassword(registerUserDto.getPassword());
        user.setRoles(Arrays.asList(new Role("ROLE_USER")));

        User user1 = this.userRepository.save(user);

        return user1;
    }

    @Override
    public User authenticateUser(LoginUserDto loginUserDto) throws CustomAuthenticationException {
        User user = userRepository.findByEmail(loginUserDto.getEmail());
        if (user==null || !user.getPassword().equals(loginUserDto.getPassword())){
            throw new CustomAuthenticationException("incorrect username or password!!");
        }
       return user;

        // Retrieve user from the database based on the provided email
//        User user = userRepository.findByEmail(loginUserDto.getEmail());
//
//        // Check if the user exists
//        if (user == null) {
//            throw new UsernameNotFoundException("User not found with email: " + loginUserDto.getEmail());
//        }
//
//        // Compare the provided password with the stored password
//        if (!passwordEncoder.matches(loginUserDto.getPassword(), user.getPassword())) {
//            throw new CustomAuthenticationException("Invalid password");
//        }

        // If authentication is successful, you may perform additional tasks such as setting authentication token
        // For example, you can use Spring Security's SecurityContextHolder to set authentication token
        // SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//        return user;

    }
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        // Load user details by email from the database
//        User user = userRepository.findByEmail(email);
//        if (user == null) {
//            throw new UsernameNotFoundException("User not found with email: " + email);
//        }
//        // Construct UserDetails object from User entity
//        return new org.springframework.security.core.userdetails.User(
//                user.getEmail(),
//                user.getPassword(),
//                new ArrayList<>()
//        );
//    }


}
