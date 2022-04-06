package com.application.viewmodel;

import com.application.viewmodel.enums.Suit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Card {
    Suit suit;
    int number;
}
