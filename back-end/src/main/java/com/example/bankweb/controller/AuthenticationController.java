package com.example.bankweb.controller;

import com.example.bankweb.Request.AuthenticationRequest;
import com.example.bankweb.Response.AuthenticationResponse;
import com.example.bankweb.dto.UserDto;
import com.example.bankweb.service.abstracts.IAuthenticationService;
import com.example.bankweb.service.concretes.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final IAuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody UserDto userDto) throws ParseException {

        return ResponseEntity.ok(authenticationService.register(userDto));
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {

        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

}
