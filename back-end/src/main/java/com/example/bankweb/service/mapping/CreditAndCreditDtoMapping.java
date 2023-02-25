package com.example.bankweb.service.mapping;

import com.example.bankweb.core.Credit;
import com.example.bankweb.dto.CreditDto;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CreditAndCreditDtoMapping {

    public CreditDto creditToCreditDto(Credit credit){
        return CreditDto.builder()
                .creditStatus(credit.isCreditStatus())
                .creditLimit(credit.getCreditLimit())
                .guarantee(credit.getGuarantee())
                .createdDate(credit.getCreatedDate())
                .customerIdNumber(credit.getUser().getIdNumber())
                .build();
    }

    public Credit CreditDtoToCredit(CreditDto creditDto){
        return Credit.builder()
                .creditStatus(creditDto.isCreditStatus())
                .creditLimit(creditDto.getCreditLimit())
                .guarantee(creditDto.getGuarantee())
                .build();
    }
}
