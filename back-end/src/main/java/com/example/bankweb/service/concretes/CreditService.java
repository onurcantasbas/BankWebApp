package com.example.bankweb.service.concretes;
import com.example.bankweb.Request.CreditApplicationRequest;
import com.example.bankweb.core.Credit;
import com.example.bankweb.dto.CreditDto;
import com.example.bankweb.dto.UserDto;
import com.example.bankweb.exception.creditProcessException.CreditNotFoundException;
import com.example.bankweb.exception.creditProcessException.IdNumberDoesntMatchWithBirthDateException;
import com.example.bankweb.exception.creditProcessException.PhoneNumberInvalidException;
import com.example.bankweb.exception.userProcessException.IdNumberNotFoundException;
import com.example.bankweb.repository.ICreditRepository;
import com.example.bankweb.service.abstracts.ICreditService;
import com.example.bankweb.service.abstracts.IUserService;
import com.example.bankweb.service.abstracts.ISmsService;
import com.example.bankweb.service.mapping.CreditAndCreditDtoMapping;
import com.example.bankweb.service.mapping.UserAndUserDtoMapping;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CreditService implements ICreditService {
    private final ICreditRepository creditRepository;
    private final IUserService userService;
    private final CreditAndCreditDtoMapping creditAndCreditDtoMapping;
    private final JwtService jwtService;
    private final ISmsService smsService;
    private static final Logger LOG = Logger.getLogger(String.valueOf(CreditService.class));

    private final UserAndUserDtoMapping userAndUserDtoMapping;
    private int creditLimitMultiplier = 4;

    public CreditService(ICreditRepository creditRepository, IUserService userService, CreditAndCreditDtoMapping creditAndCreditDtoMapping, JwtService jwtService, ISmsService smsService, UserAndUserDtoMapping userAndUserDtoMapping) {
        this.creditRepository = creditRepository;
        this.userService = userService;
        this.creditAndCreditDtoMapping = creditAndCreditDtoMapping;
        this.jwtService = jwtService;
        this.smsService = smsService;
        this.userAndUserDtoMapping = userAndUserDtoMapping;
    }
    public List<CreditDto> checkCreditApplication(String userIdNumber, String userBirthDate){
        UserDto userDto = userService.findUserByIdNumber(userIdNumber);
        if (userDto!=null) {
            if (userDto.getBirthDate().equals(userBirthDate)) {
                List<CreditDto> creditDtos = findAllByUserId(userIdNumber);
                if (creditDtos.size() != 0) {
                    LOG.info("Credit applications checked by id number: " + userIdNumber);
                    return creditDtos;
                } else
                    throw new CreditNotFoundException("there is no loan applied before");
            } else
                throw new IdNumberDoesntMatchWithBirthDateException("Id Number Doesn't Match With Birth Date");
        }else throw new IdNumberNotFoundException("Id Number you entered is not exist.");
    }
    @Override
    public void creditApplication(CreditApplicationRequest creditApplicationRequest) throws ParseException {
        String username = jwtService.extractUsername(creditApplicationRequest.getToken());
        UserDto userDto = userService.findUserByUsername(username);

        Credit credit = Credit.builder()
                .creditStatus(isCreditScoreOk(userDto.getCreditScore()))
                .creditLimit(calculateCreditLimit(userDto.getCreditScore(), userDto.getIncome(),creditApplicationRequest.getGuarantee()))
                .user(userAndUserDtoMapping.userDtoToUser(userDto))
                .guarantee(creditApplicationRequest.getGuarantee())
                .build();
        creditRepository.save(credit);
        LOG.info("loan application made by user : "+ username);
        try{
            smsService.sendSMS(userDto,credit);
        }catch (Exception e){
            throw new PhoneNumberInvalidException("Phone Number you entered is unavailable");
        }

    }
    public double calculateCreditLimit(int creditScore, int income, int guarantee){
        if (isCreditScoreOk(creditScore)){
            if(500<=creditScore && creditScore<1000){
                return creditScoreBeetween500And1000(income,guarantee);
            } else {
                return creditScoreEqualsOrGreaterThan1000(income,guarantee);
            }
        }else return 0;
    }
    private List<CreditDto> findAllByUserId(String customerIdNumber){
        List<Credit> credits = creditRepository.findAllByUserIdNumber(customerIdNumber).orElse(null);
        if(credits!=null && credits.size()!=0){
        List<CreditDto> creditDtos = new ArrayList<>();
        for (Credit credit: credits
        ) {
            CreditDto creditDto = creditAndCreditDtoMapping.creditToCreditDto(credit);
            creditDtos.add(creditDto);
        }
        return creditDtos;
        }else throw new CreditNotFoundException("Credit application not found.");
    }
    private boolean isCreditScoreOk(int creditScore){
        if(creditScore>=500){
            return true;
        }
        return false;
    }

    private double creditScoreBeetween500And1000(int income, int guarantee){
        if (income<5000){
            return (10000+(guarantee*0.1));
        } else if (5000<=income && income<10000) {
            return (20000+(guarantee*0.2));
        } else {
            return ((income*(creditLimitMultiplier/2)) + (guarantee * 0.25));
        }
    }
    //Kredi skoru 1000 puana eşit veya üzerinde ise kullanıcıya
    //AYLIK GELİR BİLGİSİ * KREDİ LİMİT ÇARPANI döner
    // Eğer teminat vermişse teminat bedelinin yüzde 50 si kadar tutar kredi limitine eklenir.
    private double creditScoreEqualsOrGreaterThan1000(int income,int guarantee){

        return ((income * creditLimitMultiplier) + (guarantee*0.5));
    }
}
