package com.swnur.tasktransactionapi.controller;

import com.swnur.tasktransactionapi.model.CategoryType;
import com.swnur.tasktransactionapi.model.UserCategoryLimit;
import com.swnur.tasktransactionapi.service.UserCategoryLimitService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/limits")
public class LimitController {

    private final UserCategoryLimitService userCategoryLimitService;

    @GetMapping
    public ResponseEntity<List<UserCategoryLimit>> getCategoryLimits() {
        List<UserCategoryLimit> list = userCategoryLimitService.getLimits();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(list);
    }

    @GetMapping("/{userId}/{categoryType}")
    public ResponseEntity<List<UserCategoryLimit>> getCategoryLimitsByCategoryType(
            @PathVariable Long userId,
            @PathVariable CategoryType categoryType){
        List<UserCategoryLimit> list = userCategoryLimitService.getLimitsByCategoryType(userId, categoryType);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(list);
    }

    @PostMapping("/{userId}/{categoryType}/{newLimit}")
    public ResponseEntity<UserCategoryLimit> saveNewLimitInCategory(
            @PathVariable Long userId,
            @PathVariable CategoryType categoryType,
            @PathVariable BigDecimal newLimit) {
        UserCategoryLimit category = userCategoryLimitService.saveNewLimit(userId, categoryType, newLimit);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(category);
    }
}
