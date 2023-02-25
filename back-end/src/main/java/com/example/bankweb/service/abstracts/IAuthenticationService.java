package com.example.bankweb.service.abstracts;

import com.example.bankweb.Request.AuthenticationRequest;
import com.example.bankweb.Response.AuthenticationResponse;
import com.example.bankweb.dto.UserDto;

import java.text.ParseException;

public interface IAuthenticationService {
    AuthenticationResponse register(UserDto userDto)throws ParseException;
    AuthenticationResponse authenticate(AuthenticationRequest request);
}
