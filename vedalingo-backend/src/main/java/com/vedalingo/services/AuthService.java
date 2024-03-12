package com.vedalingo.services;


import com.vedalingo.dtos.LoginUserDto;
import com.vedalingo.dtos.RegisterUserDto;
import com.vedalingo.exceptions.CustomAuthenticationException;
import com.vedalingo.models.User;
//import org.springframework.security.core.userdetails.UserDetailsService;


public interface AuthService {// extends UserDetailsService

    User registerUser(RegisterUserDto registerUserDto) throws CustomAuthenticationException;

    User authenticateUser(LoginUserDto loginUserDto) throws CustomAuthenticationException;

}
