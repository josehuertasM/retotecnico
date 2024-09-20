package com.example.domain;
import lombok.Getter;

import java.util.Map;

@Getter
public class CurrencyResponse {

    private String base_code;    // Moneda base (por ejemplo, USD)
    private String time_last_update_unix;    // Fecha de la última actualización del tipo de cambio
    private Map<String, Double> rates;  // Mapa de tipos de cambio (clave: moneda destino, valor: tasa de cambio)
}
