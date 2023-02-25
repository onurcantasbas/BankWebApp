package com.example.bankweb.core;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.ZoneId;


@Entity
@NoArgsConstructor
@Setter
@Getter

public class Credit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int guarantee;
    private double creditLimit;
    private boolean creditStatus;
    private LocalDate createdDate ;
    @ManyToOne
    @JoinColumn(name = "user_id_number",referencedColumnName = "id_number")
    private User user;
    @Builder
    public Credit(int guarantee, double creditLimit, boolean creditStatus, User user) {
        this.guarantee = guarantee;
        this.creditLimit = creditLimit;
        this.creditStatus = creditStatus;
        this.createdDate = LocalDate.now(ZoneId.of("Asia/Istanbul"));
        this.user = user;
    }
}
