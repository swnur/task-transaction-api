package com.swnur.tasktransactionapi.dto;

import com.swnur.tasktransactionapi.model.CategoryType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class UserCategoryLimitRequestDTO {

    @NotNull(message = "'userId' can not be null")
    private Long userId;

    @NotNull(message = "'categoryType' can not be null")
    private CategoryType categoryType;

    @NotNull(message = "'amount' can not be null")
    @DecimalMin(value = "0.00", message = "'amount' can be less than zero")
    private BigDecimal amount;
}
