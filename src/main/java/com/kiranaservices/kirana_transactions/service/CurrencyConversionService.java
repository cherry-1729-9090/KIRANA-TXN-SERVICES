package com.kiranaservices.kirana_transactions.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class CurrencyConversionService {

    private static final String API_URL = "https://api.fxratesapi.com/latest?base=USD";

    @Cacheable("currencyRates")
    public Map<String, Double> getConversionRates() {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> response = restTemplate.getForObject(API_URL, Map.class);
        return (Map<String, Double>) response.get("rates");
    }

    public double convertToINR(String currency, double amount) {
        Map<String, Double> rates = getConversionRates();
        double conversionRate = rates.getOrDefault(currency, 1.0); // Use 1.0 for INR as it's the base
        return amount * conversionRate / rates.get("INR");
    }
}
