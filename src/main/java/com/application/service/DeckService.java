package com.application.service;

import com.application.model.Card;
import com.application.model.enums.Suit;
import com.application.model.exceptions.RuleViolation;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DeckService {

    private Suit trump = Suit.CROSS;

    public List<Card> drawDeal (){
        List<Card> cards = Arrays.asList(new Card(Suit.CROSS, 10), new Card(Suit.HEART, 2));
        cards.sort(new Comparator<Card>() {
            @Override
            public int compare(Card card, Card t1) {
                return compareCards(card, t1);
            }
        });
        return cards;
    }

    private int compareCards(Card cardOne, Card cardTwo) {
        //TODO: add jokers and fully rewrite this shit
        if (Objects.equals(cardOne.getSuit(), cardTwo.getSuit())){
            return Integer.compare(cardOne.getNumber(), cardTwo.getNumber());
        } else {
            if (trump.equals(cardOne.getSuit())) {
                if (trump.equals(cardTwo.getSuit())) {
                    int compare = Integer.compare(cardOne.getNumber(), cardTwo.getNumber());
                    if (compare == 0) {
                        //equals case above, it a error motherfuckers
                        throw new RuleViolation("Someone breaks the rules, faggots! \n(or our game is broken)");
                    } else return compare;
                } else {
                    return 1;
                }
            } else if (trump.equals(cardTwo.getSuit())){
                return -1;
            } else {
                return 1;
            }
        }
    }
}
