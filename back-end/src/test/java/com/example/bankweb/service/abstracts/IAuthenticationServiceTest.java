package com.example.bankweb.service.abstracts;


import com.example.bankweb.dto.UserDto;
import com.example.bankweb.exception.userProcessException.IdNumberDuplicateException;
import com.example.bankweb.exception.userProcessException.PhoneNumberDuplicateException;
import com.example.bankweb.exception.userProcessException.UsernameDuplicateException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.text.ParseException;


import static org.assertj.core.api.AssertionsForClassTypes.*;


@SpringBootTest
class IAuthenticationServiceTest {
    @Autowired
    private IAuthenticationService underTest;
    @Autowired
    private IUserService userService;

    @Test
    void register() throws ParseException {
        //given
        String idNumber = "AuthServiceTestIdNumber";
        String userName = "AuthServiceTestUsername";
        String phoneNumber = "AuthServiceTestPhoneNumber";
        UserDto userDto = UserDto.builder()
                .idNumber(idNumber)
                .username(userName)
                .password("AuthServiceTestPassword")
                .firstName("AuthServiceTestFirstName")
                .lastName("AuthServiceTestLastName")
                .phoneNumber(phoneNumber)
                .income(17000)
                .creditScore(400)
                .birthDate("01/01/2001")
                .build();
        if(userService.findUserByIdNumber(idNumber)==null){
            if(userService.findUserByUsername(userName)==null){
                if(userService.findUserByPhoneNumber(phoneNumber)==null){
                    underTest.register(userDto);
                    UserDto result = userService.findUserByIdNumber(idNumber);
                    assertThat(userDto.toString().equals(result.toString()));
                }else
                    assertThatThrownBy(()->underTest.register(userDto))
                            .isInstanceOf(PhoneNumberDuplicateException.class)
                            .hasMessage("The phone number you entered is already in use by another user");
            }else
                assertThatThrownBy(()->underTest.register(userDto))
                        .isInstanceOf(UsernameDuplicateException.class)
                        .hasMessage("The user name you entered is already in use by another user");
        }else {
            assertThatThrownBy(()->underTest.register(userDto))
                    .isInstanceOf(IdNumberDuplicateException.class)
                    .hasMessage("The ID number you entered is already in use by another user");
        }
    }

    @Test
    void authenticate() {

    }
}