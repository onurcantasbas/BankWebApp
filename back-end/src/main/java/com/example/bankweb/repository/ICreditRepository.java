package com.example.bankweb.repository;

import com.example.bankweb.core.Credit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ICreditRepository extends JpaRepository<Credit,Long> {

    Optional<Credit> findByUserIdNumber(String userIdNumber);
    Optional<List<Credit>> findAllByUserIdNumber(String userIdNumber);
}
