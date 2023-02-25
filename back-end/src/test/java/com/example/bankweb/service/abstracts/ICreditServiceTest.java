package com.example.bankweb.service.abstracts;
import com.example.bankweb.core.Credit;
import com.example.bankweb.dto.CreditDto;
import com.example.bankweb.dto.UserDto;
import com.example.bankweb.repository.ICreditRepository;
import com.example.bankweb.service.mapping.UserAndUserDtoMapping;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class ICreditServiceTest {
    @Autowired
    private ICreditService underTest;
    @Autowired
    private ICreditRepository creditRepository;
    @Autowired
    private IAuthenticationService authenticationService;
    @Autowired
    private UserAndUserDtoMapping userAndUserDtoMapping;
    @Autowired
    private IUserService userService;
    @Test
    void checkCreditApplication() throws ParseException {
        String idNumber = "CreditServiceTestIdNumber";
        List<Credit> credits = creditRepository.findAllByUserIdNumber(idNumber).orElse(null);
        UserDto userDto;
        if(credits==null && credits.size()==0){
             userDto = UserDto.builder()
                    .idNumber(idNumber)
                    .username("CreditServiceTestUsername")
                    .password("CreditServiceTestPassword")
                    .firstName("CreditServiceTestFirstName")
                    .lastName("CreditServiceTestLastName")
                    .phoneNumber("+90000000000")
                    .birthDate("23/10/1997")
                    .creditScore(700)
                    .income(15000)
                    .build();
            authenticationService.register(userDto);

        }else{
            userDto = userService.findUserByIdNumber(idNumber);
        }
        Credit credit = Credit.builder()
                .creditStatus(true)
                .guarantee(15000)
                .user(userAndUserDtoMapping.userDtoToUser(userDto))
                .creditLimit(underTest.calculateCreditLimit(userDto.getCreditScore(),userDto.getIncome(),15000))
                .build();
        creditRepository.save(credit);
        //given
        String userIdNumber=userDto.getIdNumber();
        String userBirthDate=userDto.getBirthDate();
        //when
        List<CreditDto> result = underTest.checkCreditApplication(userIdNumber,userBirthDate);
        //then
        assertThat(result.toString().equals(credit.toString()));
    }
    @Test
    void itShouldCalculateCreditLimitIfCreditScoreBetween500And1000AndIncomeEqualOrGreaterThan10k(){
        //given
        int income = 10000;
        int guarantee = 22000;
        int creditScore = 700;
        //then
        double result = underTest.calculateCreditLimit(creditScore,income,guarantee);
        //when
        assertThat(result).isEqualTo(25500);
    }
    @Test
    void itShouldCalculateCreditLimitIfCreditScoreBetween500And1000AndIncomeBetween5kAnd10k(){
        //given
        int income = 8000;
        int guarantee = 22000;
        int creditScore = 700;
        //when
        double result = underTest.calculateCreditLimit(creditScore,income,guarantee);
        // then
        assertThat(result).isEqualTo(24400);
    }
    @Test
    void itShouldCalculateCreditLimitIfCreditScoreBetween500And1000AndIncomeLowerThan5k(){
        //given
        int income = 4000;
        int guarantee = 15000;
        int creditScore = 700;
        //when
        double result = underTest.calculateCreditLimit(creditScore,income,guarantee);
        // then
        assertThat(result).isEqualTo(11500);
    }
    @Test
    void itShouldCalculateCreditLimitIfCreditScoreEqualOrGreaterThan1000AndIncomeLower(){
        //given
        int income = 4000; // 16000
        int guarantee = 15000; // 7500
        int creditScore = 1000;
        //when
        double result = underTest.calculateCreditLimit(creditScore,income,guarantee);
        // then
        assertThat(result).isEqualTo(23500);
    }
    @Test
    void itShouldCalculateCreditLimitAsZeroIfCreditScoreLowerThan500(){
        //given
        int income = 4000; // 16000
        int guarantee = 15000; // 7500
        int creditScore = 400;
        //when
        double result = underTest.calculateCreditLimit(creditScore,income,guarantee);
        // then
        assertThat(result).isEqualTo(0);
    }
}