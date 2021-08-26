package com.application.service;

import com.application.model.Bribe;
import com.application.model.Card;
import com.application.model.GameState;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GameStateService {

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
            finishRound();
        }

        return gameState;
    }

    public void finishRound() {
        calculateRoundResults();

        gameState.getPlayerIds().add(gameState.getPlayerIds().remove(0));

        gameState.getCurrentTurn().clear();

        int currentRound = gameState.getCurrentRound();
        if (currentRound != gameState.getRounds()) {
            gameState.setCurrentRound(currentRound + 1);
        } else {
            finishGame();
        }
    }

    private void finishGame() {

    }

    private void calculateRoundResults() {
        //TODO: add real winner calculation logic
        String player = gameState.getCurrentTurn().keySet().stream().findFirst().orElse(null);
        Bribe bribe = new Bribe(new ArrayList<>(gameState.getCurrentTurn().values()), player);
        gameState.getBribes().add(bribe);
    }
}
