package com.swnur.tasktransactionapi.service;

import com.swnur.tasktransactionapi.model.Currency;
import com.swnur.tasktransactionapi.repository.CurrencyRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class CurrencyServiceTest {

    @Mock
    private CurrencyRepository currencyRepository;

    @InjectMocks
    private CurrencyService currencyService;

    @Test
    @DisplayName("Should return existing Currency USD")
    void testFindCurrencyByCode() {
        Currency currency = new Currency(1L, "USD", "Us Dollar", null);

        given(currencyRepository.findByCode(currency.getCode()))
                .willReturn(Optional.of(currency));

        Currency resultCurrency = currencyService.findCurrencyByCurrencyCode("USD");

        assertEquals(currency, resultCurrency);
    }
}