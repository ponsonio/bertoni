package com.bertoni.test.pojo;

public class Card implements Comparable<Card> {

    private int value;
    private char suit; //this should be an Enum Diamonds, Spades, Hearts, Clubs

    public Card(){}

    public Card(int value, char suit) {
        this.value = value;
        this.suit = suit;
    }

    public char getSuit() {
        return suit;
    }

    public void setSuit(char type) {
        this.suit = type;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int number) {
        this.value = number;
    }

    @Override
    public int compareTo(Card o) {
        if (this.getValue() < o.getValue()) {
            return -1;
        }
        else if (o.getValue() < this.getValue()) {
            return 1;
        }
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return getValue() == card.getValue() &&
                suit == card.suit;
    }

    @Override
    public String toString() {
        return "Card{" +
                "value=" + value +
                ", suit=" + suit +
                '}';
    }
}
