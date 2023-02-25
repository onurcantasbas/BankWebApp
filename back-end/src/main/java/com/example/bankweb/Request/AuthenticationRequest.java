package com.example.bankweb.Request;

import lombok.*;

//@Data

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AuthenticationRequest {
    private String username;
    private String password;
}
