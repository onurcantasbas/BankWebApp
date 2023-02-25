package com.example.bankweb.service.concretes;
import com.example.bankweb.Request.ResetPasswordRequest;
import com.example.bankweb.core.User;
import com.example.bankweb.dto.UserDto;
import com.example.bankweb.exception.GlobalExceptionHandler;
import com.example.bankweb.exception.userProcessException.IdNumberDuplicateException;
import com.example.bankweb.exception.userProcessException.IdNumberNotFoundException;
import com.example.bankweb.exception.userProcessException.UsernameDuplicateException;
import com.example.bankweb.exception.userProcessException.UsernameIdNumberNotMatchException;
import com.example.bankweb.repository.IUserRepository;
import com.example.bankweb.service.abstracts.IUserService;
import com.example.bankweb.service.mapping.UserAndUserDtoMapping;
import org.apache.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.text.ParseException;

@Service
public class UserService implements IUserService {
    private final IUserRepository userRepository;
    private final UserAndUserDtoMapping userAndUserDtoMapping;
    private static final Logger LOG = Logger.getLogger(String.valueOf(UserService.class));

    public UserService(IUserRepository userRepository, UserAndUserDtoMapping userAndUserDtoMapping) {
        this.userRepository = userRepository;
        this.userAndUserDtoMapping = userAndUserDtoMapping;
    }
    public UserDto findUserByIdNumber(String idNumber){
        User user = userRepository.findUserByIdNumber(idNumber).orElse(null);
        if(user!=null){
            return userAndUserDtoMapping.userToUserDto(user);
        } else return null;
    }
    public UserDto findUserByUsername(String username){
        User user = userRepository.findUserByUsername(username).orElse(null);
        if(user!=null){
            return userAndUserDtoMapping.userToUserDto(user);
        } else return null;
    }
    public UserDto findUserByPhoneNumber(String phoneNumber){
        User user = userRepository.findUserByPhoneNumber(phoneNumber).orElse(null);
        if(user!=null){
            return userAndUserDtoMapping.userToUserDto(user);
        } else return null;
    }
    public void resetPassword(ResetPasswordRequest resetPasswordRequest) throws ParseException {

        UserDto userDto = findUserByIdNumber(resetPasswordRequest.getIdNumber());

        if(userDto != null){
            System.out.println(userDto.getUsername()+"   "+ resetPasswordRequest.getUsername());
            if (userDto.getUsername().equals(resetPasswordRequest.getUsername())){
                userDto.setPassword(resetPasswordRequest.getNewPassword());
                updateUser(userDto);
                LOG.info("User "+userDto.getUsername()+" changed password.");
            }else throw new UsernameIdNumberNotMatchException("Username and id number not match.");
        }else throw new IdNumberNotFoundException("There is no record with id number you entered.");
    }
    public void saveUser(User user){
        userRepository.save(user);
    }
    @Override
    public void updateUser(UserDto userDto) throws ParseException {
        userRepository.saveAndFlush(userAndUserDtoMapping.userDtoToUser(userDto));
    }
}
