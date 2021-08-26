package com.application.model;

import lombok.*;

import java.util.ArrayList;
import java.util.HashMap;
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
    int rounds;
    int currentRound;
}
