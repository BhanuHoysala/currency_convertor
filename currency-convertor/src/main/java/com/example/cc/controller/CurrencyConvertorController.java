package com.example.cc.controller;

import com.example.cc.service.CurrencyConvertorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("currency")
public class CurrencyConvertorController {

    @Autowired
    private CurrencyConvertorService currencyConvertorService;

    /**
     * API end point to convert from one currency from one to another currency
     */
    @GetMapping("/v1/convert")
    public ResponseEntity convertCurrency(@RequestParam String from,
                                          @RequestParam String to,
                                          @RequestParam Double amount) {

        Double convertedAmount = currencyConvertorService.convertCurrency(from.toUpperCase(), to.toUpperCase(), amount);
        return new ResponseEntity<>(convertedAmount, HttpStatus.OK);
    }

}
