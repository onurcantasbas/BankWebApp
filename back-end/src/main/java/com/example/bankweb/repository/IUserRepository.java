package com.example.bankweb.repository;
import com.example.bankweb.core.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User,Long> {
     Optional<User> findUserByIdNumber(String idNumber);
     Optional<User> findUserByUsername(String username);
     Optional<User> findUserByPhoneNumber(String phoneNumber);

     void removeUserByIdNumber(String idNumber);
}
