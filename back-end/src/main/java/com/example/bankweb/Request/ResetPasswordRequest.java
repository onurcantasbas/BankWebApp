package com.example.bankweb.Request;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ResetPasswordRequest {
    private String username;
    private String idNumber;
    private String newPassword;
}
