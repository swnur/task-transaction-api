package com.swnur.tasktransactionapi.service;

import com.swnur.tasktransactionapi.exception.InvalidUserAccountException;
import com.swnur.tasktransactionapi.model.CategoryType;
import com.swnur.tasktransactionapi.model.User;
import com.swnur.tasktransactionapi.model.UserCategoryLimit;
import com.swnur.tasktransactionapi.repository.UserCategoryLimitRepository;
import com.swnur.tasktransactionapi.repository.UserRepository;
import com.swnur.tasktransactionapi.utils.ValidationUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class UserCategoryLimitService {

    private final UserCategoryLimitRepository userCategoryLimitRepository;
    private final UserRepository userRepository;

    public UserCategoryLimit saveNewLimit(Long userId, CategoryType categoryType, BigDecimal newLimitAmount) {
        ValidationUtils.validateBigDecimalAmount(newLimitAmount);

        UserCategoryLimit newCategoryLimit = new UserCategoryLimit();

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new InvalidUserAccountException("Invalid user id was given, user does not exist"));
        newCategoryLimit.setUser(user);

        UserCategoryLimit category = userCategoryLimitRepository.findLatestUserCategoryLimitByUserIdAndCategoryType(userId, categoryType.toString());
        newCategoryLimit.setCategoryType(categoryType);
        newCategoryLimit.setLimitAmount(newLimitAmount);
        BigDecimal subtractedAmount = category.getLimitAmount().subtract(category.getRemainingLimitAmount());
        newCategoryLimit.setRemainingLimitAmount(newLimitAmount.subtract(subtractedAmount));
        newCategoryLimit.setDateTime(OffsetDateTime.now());

        return userCategoryLimitRepository.save(newCategoryLimit);
    }

    public UserCategoryLimit updateRemainingLimit(Long userId, CategoryType categoryType, BigDecimal transactionAmount) {
        UserCategoryLimit userCategoryLimit = userCategoryLimitRepository
                .findLatestUserCategoryLimitByUserIdAndCategoryType(userId, categoryType.toString());

        userCategoryLimit.setRemainingLimitAmount(
                userCategoryLimit.getRemainingLimitAmount().subtract(transactionAmount)
        );

        return userCategoryLimitRepository.save(userCategoryLimit);
    }

    public List<UserCategoryLimit> getLimits() {
        return userCategoryLimitRepository.findAll();
    }

    public List<UserCategoryLimit> getLimitsByCategoryType(Long userId, CategoryType categoryType) {
        return userCategoryLimitRepository.findAllByUserIdAndCategoryType(userId, categoryType.toString());
    }
}
