package com.swnur.tasktransactionapi.repository;

import com.swnur.tasktransactionapi.model.UserCategoryLimit;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserCategoryLimitRepository extends JpaRepository<UserCategoryLimit, Long> {

    @Query(value = "SELECT * " +
            "FROM user_category_limit u " +
            "WHERE u.user_id = :userId AND u.category_name = :categoryType " +
            "ORDER BY u.created_at DESC " +
            "LIMIT 1",
            nativeQuery = true)
    UserCategoryLimit findLatestUserCategoryLimitByUserIdAndCategoryType(@Param("userId") Long userId,
                                                                         @Param("categoryType") String categoryType);

    @Query(value = "SELECT * " +
            "FROM user_category_limit u " +
            "WHERE u.user_id = :userId AND u.category_name = :categoryType",
            nativeQuery = true)
    List<UserCategoryLimit> findAllByUserIdAndCategoryType(@Param("userId") Long userId,
                                                           @Param("categoryType") String categoryType);
}
