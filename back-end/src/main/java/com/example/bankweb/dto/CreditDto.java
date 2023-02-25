package com.example.bankweb.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CreditDto {

    private int guarantee;
    private double creditLimit;
    private boolean creditStatus;
    private String customerIdNumber;
    private LocalDate createdDate;
}
