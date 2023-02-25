package com.example.bankweb.service.abstracts;
import com.example.bankweb.core.Credit;
import com.example.bankweb.dto.UserDto;

public interface ISmsService {
    void sendSMS(UserDto userDto, Credit credit);
}
