package com.swnur.tasktransactionapi.service;

import com.swnur.tasktransactionapi.exception.InvalidUserAccountException;
import com.swnur.tasktransactionapi.model.User;
import com.swnur.tasktransactionapi.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User findByAccount(BigInteger account) {
        return userRepository.findByAccount(account)
                .orElseThrow(() -> new InvalidUserAccountException("Invalid user account was given " + account));
    }
}
