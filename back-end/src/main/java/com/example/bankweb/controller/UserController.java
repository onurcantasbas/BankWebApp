package com.example.bankweb.controller;
import com.example.bankweb.Request.ResetPasswordRequest;
import com.example.bankweb.dto.UserDto;
import com.example.bankweb.service.abstracts.IUserService;
//import org.apache.log4j.Logger;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final IUserService userService;

    @PutMapping("/resetpassword")
    public ResponseEntity resetPassword(@RequestBody ResetPasswordRequest resetPasswordRequest) throws ParseException {
         userService.resetPassword(resetPasswordRequest);
        return ResponseEntity.ok("password updated.");
    }


}
