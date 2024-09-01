package com.example.solva.service;

import com.example.solva.ExchangeRate;
import com.example.solva.repository.ExchangeRateRepository;
import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;


@Service
public class CurrencyService {

    private static final Logger log = LoggerFactory.getLogger(CurrencyService.class);

    @Autowired
    private ExchangeRateRepository exchangeRateRepository;

    private final RestTemplate restTemplate = new RestTemplate();
    private final String API_KEY = "14d306c0ba8e412aa72e830f881e63c8";
    private final String URL = "https://openexchangerates.org/api/latest.json?app_id=";

    public void updateExchangeRates() {
        try {
            String url = URL + API_KEY + "&symbols=USD,KZT,RUB";
            ResponseEntity<JsonNode> response = restTemplate.getForEntity(url, JsonNode.class);

            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                JsonNode rates = response.getBody().get("rates");
                BigDecimal usdToKzt = rates.get("KZT").decimalValue().setScale(2, RoundingMode.HALF_EVEN);
                BigDecimal usdToRub = rates.get("RUB").decimalValue().setScale(2, RoundingMode.HALF_EVEN);

                saveExchangeRate("USD/KZT", usdToKzt, LocalDate.now());
                saveExchangeRate("USD/RUB", usdToRub, LocalDate.now());
            } else {
                log.error("Failed to fetch currency rates: Status Code = {}", response.getStatusCode());
            }
        } catch (Exception e) {
            log.error("Exception occurred while fetching currency rates", e);
        }
    }

    private void saveExchangeRate(String currencyPair, BigDecimal rate, LocalDate date) {
        ExchangeRate exchangeRate = new ExchangeRate();
        exchangeRate.setCurrencyPair(currencyPair);
        exchangeRate.setRate(rate);
        exchangeRate.setDate(date);
        exchangeRateRepository.save(exchangeRate);
        log.info("Saved exchange rate for {}: {}", currencyPair, rate);
    }
}
