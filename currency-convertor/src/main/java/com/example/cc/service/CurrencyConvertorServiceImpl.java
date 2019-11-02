package com.example.cc.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class CurrencyConvertorServiceImpl implements CurrencyConvertorService {

    @Autowired
    private RestTemplate restTemplate;

    // caching the the exchange rates, TODO - instead using map self expiring container would be better fit
    private static Map<String, Map<String, Double>> baseCurrencyMap = new HashMap<>();

    @Value("${exchange.rate.base.url}")
    private String exchangeRateApiURL;

    /**
     * Converts the amount from one currency type to another
     * @param sourceCurrency
     * @param targetCurrency
     * @param amount
     * @return Double convertedAmount
     */
    @Override
    public Double convertCurrency(String sourceCurrency, String targetCurrency, Double amount) {

        if (sourceCurrency.equalsIgnoreCase(targetCurrency)) {
            return amount;
        }
        findExchangeRate(sourceCurrency);
        Map<String, Double> currencyCodeValueMap = baseCurrencyMap.get(sourceCurrency);
        Double exchangeRate = currencyCodeValueMap.get(targetCurrency);
        return Math.round((exchangeRate * amount) * 100.0) / 100.0;
    }

    /**
     * Call the third party API and caches the exchange rates
     * @param baseCurrency
     * @return Map<String, Double>
     */
    private Map<String, Double> findExchangeRate(final String baseCurrency) {

        if (null != baseCurrencyMap.get(baseCurrency)) {
            // If the exchange rates are already cached
            return baseCurrencyMap.get(baseCurrency);
        }

        String url = exchangeRateApiURL + baseCurrency;
        String jsonBody = restTemplate.getForObject(url, String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = null;
        try {
            rootNode = objectMapper.readTree(jsonBody).get("rates");
        } catch (JsonProcessingException e) {
        }

        String[] codeValue = rootNode.toString().split(",");
        codeValue[0] = codeValue[0].replaceAll("[{}]", "");
        codeValue[codeValue.length - 1] = codeValue[codeValue.length - 1].replaceAll("[{}]", "");

        Map<String, Double> currencyCodeValueMap = new HashMap<>();

        for (int i = 0; i < codeValue.length; i++) {
            String[] currencyValuePair = codeValue[i].split(":");
            currencyCodeValueMap.put(currencyValuePair[0].replaceAll("\"", ""),
                    Double.parseDouble(currencyValuePair[1].replaceAll("\"", "")));
        }

        baseCurrencyMap.put(baseCurrency, currencyCodeValueMap);
        return currencyCodeValueMap;
    }

}
