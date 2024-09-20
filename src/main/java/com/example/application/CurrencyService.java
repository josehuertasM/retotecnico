package com.example.application;

import com.example.domain.CurrencyResponse;
import com.example.domain.ExchangeRate;
import com.example.domain.ExchangeRateResponse;
import com.example.infrastructure.ExchangeRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CurrencyService {
    private final WebClient webClient;
    private final ExchangeRateRepository exchangeRateRepository;

    @Autowired
    public CurrencyService(WebClient.Builder webClientBuilder, ExchangeRateRepository exchangeRateRepository) {
        this.webClient = webClientBuilder.baseUrl("https://open.er-api.com/v6/latest").build();
        this.exchangeRateRepository = exchangeRateRepository;
    }

    public Mono<ExchangeRateResponse> applyExchangeRate(double amount, String fromCurrency, String toCurrency) {
        return webClient.get()
                .uri("/{base}", fromCurrency)
                .retrieve()
                .bodyToMono(CurrencyResponse.class)
                .flatMap(currencyResponse -> {
                    Double rate = currencyResponse.getRates().get(toCurrency);
                    if (rate == null) {
                        return Mono.error(new IllegalArgumentException("Tipo de cambio no disponible"));
                    }
                    double convertedAmount = amount * rate;

                    // Guardar en la base de datos
                    ExchangeRate exchangeRate = new ExchangeRate(amount, fromCurrency, rate, toCurrency, convertedAmount);
                    exchangeRateRepository.save(exchangeRate);

                    // Retornar la respuesta
                    return Mono.just(new ExchangeRateResponse(amount, convertedAmount, fromCurrency, toCurrency, rate));
                });
    }

    // Para listar todos los registros (opcional)
    public Flux<ExchangeRate> getAllExchangeRates() {
        return Flux.fromIterable(exchangeRateRepository.findAll());
    }
}

