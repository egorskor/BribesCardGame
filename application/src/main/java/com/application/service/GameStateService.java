package com.application.service;

import com.application.viewmodel.Bribe;
import com.application.viewmodel.Card;
import com.application.viewmodel.GameState;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GameStateService {
//TODO: rename to roundProcessingService?

    GameState gameState;

    public void initGame(int playersAmount, int roundsAmount) {
        List<String> playerIds = new ArrayList<>();
        for (int i = 0; i < playersAmount; i++) {
            playerIds.add(String.valueOf(i));
        }
        gameState = new GameState(playerIds, new HashMap<>(), new ArrayList<>()
                , roundsAmount, 0);
    }

    public GameState makeTurn(Card card, String playerId) {
        gameState.getCurrentTurn().put(playerId, card);

        int indexOfPlayer = gameState.getPlayerIds().indexOf(playerId);
        if (indexOfPlayer == gameState.getPlayerIds().size() - 1) {
            finishBribe();
        }

        return gameState;
    }

    public void finishBribe() {
        calculateBribeResults();

        gameState.getPlayerIds().add(gameState.getPlayerIds().remove(0));

        gameState.getCurrentTurn().clear();

        int currentBribeNumber = gameState.getCurrentBribeNumber();
        if (currentBribeNumber != gameState.getAmountOfCards()) {
            gameState.setCurrentBribeNumber(currentBribeNumber + 1);
        } else {
            finishRound();
        }
    }

    private void finishRound() {

    }

    private void calculateBribeResults() {
        //TODO: add real winner calculation logic
        String player = gameState.getCurrentTurn().keySet().stream().findFirst().orElse(null);
        Bribe bribe = new Bribe(new ArrayList<>(gameState.getCurrentTurn().values()), player);
        gameState.getBribes().add(bribe);
    }
}
