package com.example.bankweb.service.concretes;

import com.example.bankweb.Request.AuthenticationRequest;
import com.example.bankweb.Response.AuthenticationResponse;
import com.example.bankweb.controller.AuthenticationController;
import com.example.bankweb.core.Role;
import com.example.bankweb.core.User;
import com.example.bankweb.dto.UserDto;
import com.example.bankweb.exception.creditProcessException.CreditNotFoundException;
import com.example.bankweb.exception.userProcessException.IdNumberDuplicateException;
import com.example.bankweb.exception.userProcessException.PhoneNumberDuplicateException;
import com.example.bankweb.exception.userProcessException.UsernameDuplicateException;
import com.example.bankweb.exception.userProcessException.UsernameOrPasswordInvalidException;
import com.example.bankweb.repository.IUserRepository;
import com.example.bankweb.service.abstracts.IAuthenticationService;
import com.example.bankweb.service.abstracts.IUserService;
import com.example.bankweb.service.mapping.UserAndUserDtoMapping;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.text.ParseException;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements IAuthenticationService {
    private final IUserRepository userRepository;
    private final IUserService userService;
    private final JwtService jwtService;
    private final UserAndUserDtoMapping userAndUserDtoMapping;
    private final AuthenticationManager authenticationManager;
    private static final Logger LOG = Logger.getLogger(String.valueOf(AuthenticationService.class));
    public AuthenticationResponse register(UserDto userDto) throws ParseException {

        try {
            if(userService.findUserByIdNumber(userDto.getIdNumber())==null)
            {
                if(userService.findUserByUsername(userDto.getUsername())==null)
                {
                    if (userService.findUserByPhoneNumber(userDto.getPhoneNumber())==null)
                    {
                        var user = userAndUserDtoMapping.userDtoToUser(userDto);
                        userService.saveUser(user);
                        LOG.info("New user registered.");
                        var jwtToken = jwtService.generateToken(user);
                        return AuthenticationResponse.builder()
                                .token(jwtToken)
                                .idNumber(user.getIdNumber())
                                .build();
                    }else throw new PhoneNumberDuplicateException("The phone number you entered is already in use by another user");
                }else throw new UsernameDuplicateException("The user name you entered is already in use by another user");
            }else throw new IdNumberDuplicateException("The ID number you entered is already in use by another user");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request)  {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        }catch (Exception e){
            throw new UsernameOrPasswordInvalidException("Username or Password are Invalid.");
        };

        var user = userRepository.findUserByUsername(request.getUsername())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        LOG.info("User: "+ request.getUsername()+ " Logged In.");
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .idNumber(user.getIdNumber())
                .build();
    }
}
