package com.example.bankweb.service.abstracts;

import com.example.bankweb.Request.ResetPasswordRequest;
import com.example.bankweb.core.Role;
import com.example.bankweb.core.User;
import com.example.bankweb.dto.UserDto;
import com.example.bankweb.repository.IUserRepository;
import com.example.bankweb.service.mapping.UserAndUserDtoMapping;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.text.ParseException;
import java.util.Date;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest
class IUserServiceTest {
    @Autowired
    private IUserService underTest;
    @Autowired
    private UserAndUserDtoMapping userAndUserDtoMapping;
    @Autowired
    private IUserRepository userRepository;

    @Test
    void itShouldFindUserByIdNumber() throws ParseException {
        //given
        String given = "00000000000";
        UserDto userDto;

        if (userRepository.findUserByIdNumber(given)==null){
            userDto = UserDto.builder()
                    .idNumber(given)
                    .username("testUsername0")
                    .password("testPassword0")
                    .firstName("testName0")
                    .lastName("testLastname0")
                    .phoneNumber("+900000000000")
                    .income(17000)
                    .creditScore(400)
                    .birthDate("01/01/2001")
                    .build();
            User user = userAndUserDtoMapping.userDtoToUser(userDto);
            userRepository.save(user);
        }

        //when
        UserDto result = underTest.findUserByIdNumber(given);
        //then
        assertThat(result.getIdNumber().equals(given));

    }

    @Test
    void itShouldFindUserByUsername() throws ParseException {
        //given
        String given = "testUsername1";
        if(userRepository.findUserByUsername(given)==null){
            UserDto userDto = UserDto.builder()
                    .idNumber("00000000001")
                    .username(given)
                    .password("testPassword1")
                    .firstName("testName1")
                    .lastName("testLastname1")
                    .phoneNumber("+900000000001")
                    .income(17001)
                    .creditScore(501)
                    .birthDate("01/01/2001")
                    .build();
            User user = userAndUserDtoMapping.userDtoToUser(userDto);
            userRepository.save(user);

        }
       //when
        UserDto result = underTest.findUserByUsername(given);
        //then
        assertThat(result.getUsername().equals(given));

    }

    @Test
    void itShouldFindUserByPhoneNumber() throws ParseException {
        //given
        String given = "+900000000002";
        if(userRepository.findUserByPhoneNumber(given)==null) {
            UserDto userDto = UserDto.builder()
                    .idNumber("00000000002")
                    .username("testUsername2")
                    .password("testPassword2")
                    .firstName("testName2")
                    .lastName("testLastname2")
                    .phoneNumber(given)
                    .income(17002)
                    .creditScore(502)
                    .birthDate("01/01/2001")
                    .build();
            User user = userAndUserDtoMapping.userDtoToUser(userDto);
            userRepository.save(user);
        }
        //when
        UserDto result = underTest.findUserByPhoneNumber(given);
        //then
        assertThat(result.getPhoneNumber().equals(given));

    }

    @Test
    void itShouldSaveUser() {
        //given
        User given = User.builder()
                .idNumber("00000000004")
                .username("testUsername4")
                .password("testPassword4")
                .firstName("testName4")
                .lastName("testLastmaöe4")
                .phoneNumber("+900000000004")
                .income(1004)
                .creditScore(700)
                .birthDate(new Date())
                .role(Role.USER)
                .build();
        underTest.saveUser(given);
        User result = userRepository.findUserByIdNumber(given.getIdNumber()).orElse(null);
        assertThat(result).toString().equals(given);
    }

    @Test
    void itShouldResetUserPassword() throws ParseException {
        //given
        ResetPasswordRequest given = new ResetPasswordRequest(
                "testUsername3","00000000003","testNewPassword3"
        );
        if(userRepository.findUserByIdNumber(given.getIdNumber())==null) {
            User user = User.builder()
                    .idNumber(given.getIdNumber())
                    .username(given.getUsername())
                    .password("testOldPassword")
                    .firstName("testName3")
                    .lastName("testLastname3")
                    .phoneNumber("+900000000003")
                    .income(17002)
                    .creditScore(502)
                    .birthDate(new Date())
                    .build();
            userRepository.save(user);
        }
        //when
        underTest.resetPassword(given);
        UserDto result = underTest.findUserByIdNumber(given.getIdNumber());
        //then
        assertThat(result.getPassword().equals(given.getNewPassword()));

    }

}