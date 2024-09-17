package com.kiranaservices.kirana_transactions.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class CurrencyConversionService {

    private static final String API_URL = "https://api.fxratesapi.com/latest?base=USD";

    @Cacheable("currencyRates")
    @SuppressWarnings("unchecked")
    public Map<String, Double> getConversionRates() {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> response = restTemplate.getForObject(API_URL, Map.class);
        Map<String, Double> rates = new HashMap<>();
        ((Map<String, Number>) response.get("rates")).forEach((key, value) -> rates.put(key, value.doubleValue()));
        return rates;
    }


    public double convertToINR(String currency, double amount) {
        Map<String, Double> rates = getConversionRates();
        double conversionRate = rates.getOrDefault(currency, 1.0); // Use 1.0 for INR as it's the base
        return amount * conversionRate / rates.get("INR");
    }
}
