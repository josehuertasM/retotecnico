package com.example.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class ExchangeRateResponse {

    private double originalAmount;
    private double convertedAmount;
    private String fromCurrency;
    private String toCurrency;
    private double exchangeRate;


}
