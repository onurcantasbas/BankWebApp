package com.example.bankweb.Request;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CreditApplicationRequest {
    private String token;
    private int guarantee;
}
