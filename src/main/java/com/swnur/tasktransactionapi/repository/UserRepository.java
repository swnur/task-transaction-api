package com.swnur.tasktransactionapi.repository;

import com.swnur.tasktransactionapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByAccount(BigInteger account);
}
