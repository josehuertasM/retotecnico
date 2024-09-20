package com.example.web;


import com.example.application.CurrencyService;
import com.example.domain.ExchangeRate;
import com.example.domain.ExchangeRateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/exchange")
public class ExchangeRateController {
    @Autowired
    private CurrencyService currencyService;

    @PostMapping("/apply")
    public Mono<ExchangeRateResponse> applyExchangeRate(
            @RequestParam double amount,
            @RequestParam String fromCurrency,
            @RequestParam String toCurrency) {
        return currencyService.applyExchangeRate(amount, fromCurrency, toCurrency);
    }

    // Endpoint opcional para listar todos los cambios
    @GetMapping("/list")
    public Flux<ExchangeRate> listAll() {
        return currencyService.getAllExchangeRates();
    }
}
