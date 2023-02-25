package com.example.bankweb.service.concretes;
import com.example.bankweb.core.Credit;
import com.example.bankweb.dto.UserDto;
import com.example.bankweb.service.abstracts.ISmsService;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import org.springframework.stereotype.Service;

@Service
public class SmsService implements ISmsService {
    public void sendSMS(UserDto userDto, Credit credit) {

        String message = credit.isCreditStatus()? userDto.getFirstName()+" "+ userDto.getLastName()+
                ",your credit application has been accepted "+"your credit limit :"+credit.getCreditLimit() :
                userDto.getFirstName()+" "+ userDto.getLastName()+
                        ",your credit application has been denied "+"cause your credit score lower than 500, " +
                        "your credit score: "+ userDto.getCreditScore();
        Twilio.init("AC43562429e11ae427cf684abe2bb11c5b","13d32b30f301cec587f944f293aba7a9");
        Message.creator(new com.twilio.type.PhoneNumber(userDto.getPhoneNumber()),
                new com.twilio.type.PhoneNumber("+17409971847"), message).create();
    }
}
