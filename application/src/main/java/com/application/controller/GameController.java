package com.application.controller;

import com.application.viewmodel.Card;
import com.application.viewmodel.GameState;
import com.application.viewmodel.enums.Suit;
import com.application.viewmodel.exceptions.RuleViolation;
import com.application.service.DeckService;
import com.application.service.GameStateService;
import com.application.multimodule.service.FrontendModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;


import java.util.Collections;
import java.util.List;

@RestController
public class GameController {

    @Autowired
    DeckService deckService;

    @Autowired
    GameStateService gameStateService;

    @Autowired
    private FrontendModuleService frontendModuleService;

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
        GameState gameState = gameStateService.makeTurn(card, playerId);
        getGameState(gameState);
        return gameState;
    }

    //TODO: does not work for now. Still need to fix it
    @MessageMapping("/getGameState")
    @SendTo("/getGameState")
    public GameState getGameState(GameState message)  {
        return message;
    }

    @GetMapping("/testFrontendService")
    public String makeTurn(){
        return frontendModuleService.message();
    }

}