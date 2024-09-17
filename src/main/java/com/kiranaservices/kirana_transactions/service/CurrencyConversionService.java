package com.kiranaservices.kirana_transactions.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
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

        if (response != null && response.containsKey("rates")) {
            Map<String, Number> rateMap = (Map<String, Number>) response.get("rates");
            rateMap.forEach((key, value) -> rates.put(key, value.doubleValue()));
        }

        return rates;
    }

    public double convertToINR(String currency, double amount) {
        Map<String, Double> rates = getConversionRates();
        double conversionRate = rates.getOrDefault(currency, 1.0); // Default to 1.0 if currency not found
        double inrRate = rates.getOrDefault("INR", 1.0); // Fetch INR conversion rate

        return (amount * conversionRate) / inrRate;
    }

    @Scheduled(fixedRate = 3600000) // Runs every 1 hour
    @CacheEvict(value = "currencyRates", allEntries = true)
    public void evictCurrencyCache() {
        System.out.println("Currency rates cache cleared!");
    }
}
