package com.example.bankweb.service.abstracts;
import com.example.bankweb.Request.CreditApplicationRequest;
import com.example.bankweb.dto.CreditDto;
import org.springframework.http.ResponseEntity;

import java.text.ParseException;
import java.util.List;

public interface ICreditService {
    List<CreditDto> checkCreditApplication(String userIdNumber, String userBirthDate);
    void creditApplication(CreditApplicationRequest creditApplicationRequest) throws ParseException;

    double calculateCreditLimit(int creditScore, int income, int guarantee);
}
