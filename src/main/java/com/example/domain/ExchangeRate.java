package com.example.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ExchangeRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double originalAmount;
    private String fromCurrency;
    private String toCurrency;
    private double convertedAmount;
    private double exchangeRate;

    public ExchangeRate(double originalAmount, String fromCurrency, double exchangeRate, String toCurrency, double convertedAmount) {
        this.originalAmount = originalAmount;
        this.fromCurrency = fromCurrency;
        this.exchangeRate = exchangeRate;
        this.toCurrency = toCurrency;
        this.convertedAmount = convertedAmount;
    }
}
