package com.application.controller;

import com.application.model.Card;
import com.application.model.enums.Suit;
import com.application.model.exceptions.RuleViolation;
import com.application.service.DeckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
public class HelloController {

    @Autowired
    DeckService deckService;

    @GetMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @GetMapping("/getCards")
    public List<Card> getCards(@RequestParam(required = false) String amountOfCards) {
        try {
            return deckService.drawDeal();
        } catch (RuleViolation e){
            return Collections.emptyList();
        }
    }

}