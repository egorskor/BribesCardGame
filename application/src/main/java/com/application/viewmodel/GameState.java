package com.application.viewmodel;

import lombok.*;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GameState {
    List<String> playerIds;
    Map<String, Card> currentTurn;
    List<Bribe> bribes;
    int amountOfCards;
    int currentBribeNumber;
}
