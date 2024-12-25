package com.swnur.tasktransactionapi.proxy;

import com.swnur.tasktransactionapi.model.ExchangeRateProxyResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "exchangeRateClient", url = "${api.exchangerates.io}")
public interface ExchangeRatesProxy {

    @GetMapping("/latest/{baseCurrencyCode}")
    ExchangeRateProxyResponse getLatestExchangeRate(@PathVariable(name = "baseCurrencyCode") String code);
}
