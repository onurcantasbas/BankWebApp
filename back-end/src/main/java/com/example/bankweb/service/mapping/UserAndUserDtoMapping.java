package com.example.bankweb.service.mapping;

import com.example.bankweb.core.Role;
import com.example.bankweb.core.User;
import com.example.bankweb.dto.UserDto;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.text.ParseException;

@Configuration
public class UserAndUserDtoMapping {
    private final DateToStringConvert dateToStringConvert;
    private final PasswordEncoder passwordEncoder;

    public UserAndUserDtoMapping(DateToStringConvert dateToStringConvert, PasswordEncoder passwordEncoder) {
        this.dateToStringConvert = dateToStringConvert;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDto userToUserDto(User user){
        return UserDto.builder()
                .username(user.getUsername())
                .idNumber(user.getIdNumber())
                .password(user.getPassword())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .birthDate(dateToStringConvert.dateToString(user.getBirthDate()))
                .income(user.getIncome())
                .creditScore(user.getCreditScore())
                .phoneNumber(user.getPhoneNumber())
                .build();
    }
    public User userDtoToUser(UserDto userDto) throws ParseException {
        return User.builder()
                .idNumber(userDto.getIdNumber())
                .username(userDto.getUsername())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .birthDate(dateToStringConvert.stringToDate(userDto.getBirthDate()))
                .creditScore(userDto.getCreditScore())
                .income(userDto.getIncome())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .phoneNumber(userDto.getPhoneNumber())
                .role(Role.USER)
                .build();
    }
}
