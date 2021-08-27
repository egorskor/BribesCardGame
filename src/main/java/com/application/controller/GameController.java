package com.application.controller;

import com.application.model.Card;
import com.application.model.GameState;
import com.application.model.enums.Suit;
import com.application.model.exceptions.RuleViolation;
import com.application.service.DeckService;
import com.application.service.GameStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
public class GameController {

    @Autowired
    DeckService deckService;

    @Autowired
    GameStateService gameStateService;

    @GetMapping("/initGame")
    public String initGame(){
        try {
            gameStateService.initGame(2, 1);
            return "Game started!";
        } catch (Exception e){
            e.printStackTrace(System.out);
            return "Game start failed " + e.getMessage();
        }
    }

    @GetMapping("/getCards")
    public List<Card> getCards(@RequestParam(required = false) String amountOfCards) {
        try {
            return deckService.drawDeal();
        } catch (RuleViolation e){
            return Collections.emptyList();
        }
    }

    //TODO: Cant work with postman offline. Change it to POST back
    @GetMapping("/makeTurn")
    public GameState makeTurn(@RequestParam String card, @RequestParam String playerId){
        Card c = new Card(Suit.CROSS, Integer.parseInt(card));
        return gameStateService.makeTurn(c, playerId);
    }

    @PostMapping("/makeTurn")
    public GameState makeTurn(@RequestBody Card card, @RequestParam String playerId){
        return gameStateService.makeTurn(card, playerId);
    }
}