package com.example.bankweb.dto;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDto {

    private String username;
    private String idNumber;
    private String password;
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private String birthDate;
    private int income;
    private int creditScore;
}
