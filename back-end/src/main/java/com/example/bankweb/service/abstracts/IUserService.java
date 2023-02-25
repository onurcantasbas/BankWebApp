package com.example.bankweb.service.abstracts;
import com.example.bankweb.Request.ResetPasswordRequest;
import com.example.bankweb.core.User;
import com.example.bankweb.dto.UserDto;
import org.springframework.http.ResponseEntity;

import java.text.ParseException;

public interface IUserService {
    UserDto findUserByIdNumber(String idNumber);
    UserDto findUserByUsername(String username);
    UserDto findUserByPhoneNumber(String phoneNumber);
    void saveUser(User user);

    void resetPassword(ResetPasswordRequest resetPasswordRequest) throws ParseException;
    void updateUser(UserDto userDto) throws ParseException;

}
