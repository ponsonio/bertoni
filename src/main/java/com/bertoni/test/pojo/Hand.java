package com.bertoni.test.pojo;

import java.util.Arrays;

public class Hand {

    private Card[] cards = new Card[5];

    private double winProbability = 0.0;

    public Hand(Card[] cards) {
        this.cards = cards;
    }

    public double getWinProbability() {
        return winProbability;
    }

    public void setWinProbability(double winProbability) {
        this.winProbability = winProbability;
    }

    public Card[] getCards() {
        return cards;
    }

    public void setCards(Card[] cards) {
        this.cards = cards;
    }

    public void sort() {
        Arrays.sort(this.cards);
    }

    @Override
    public String toString() {
        return "Hand{" +
                "cards=" + Arrays.toString(cards) +
                ", winProbability=" + winProbability +
                '}';
    }
}
