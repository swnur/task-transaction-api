package com.swnur.tasktransactionapi.controller;

import com.swnur.tasktransactionapi.dto.UserCategoryLimitRequestDTO;
import com.swnur.tasktransactionapi.model.CategoryType;
import com.swnur.tasktransactionapi.model.UserCategoryLimit;
import com.swnur.tasktransactionapi.service.UserCategoryLimitService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/limits")
public class LimitController {

    private final UserCategoryLimitService userCategoryLimitService;

    @GetMapping
    public ResponseEntity<List<UserCategoryLimit>> getCategoryLimits() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userCategoryLimitService.getLimits());
    }

    @GetMapping("/{userId}/{categoryType}")
    public ResponseEntity<List<UserCategoryLimit>> getCategoryLimitsByCategoryType(
            @NotNull(message = "userId can not be null") @PathVariable Long userId,
            @NotNull(message = "categoryType can not be null") @PathVariable CategoryType categoryType) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userCategoryLimitService.getLimitsByCategoryType(userId, categoryType));
    }

    @PostMapping
    public ResponseEntity<UserCategoryLimit> saveNewLimitInCategory(
            @Valid @RequestBody UserCategoryLimitRequestDTO userCategoryLimitRequestDTO) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userCategoryLimitService.saveNewLimit(userCategoryLimitRequestDTO));
    }
}
