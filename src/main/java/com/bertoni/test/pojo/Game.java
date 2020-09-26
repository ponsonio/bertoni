package com.bertoni.test.pojo;

public class Game {

    private Hand h1;
    private Hand h2;

    public Game(Hand h1, Hand h2) {
        this.h1 = h1;
        this.h2 = h2;
    }

    public Hand getH1() {
        return h1;
    }

    public void setH1(Hand h1) {
        this.h1 = h1;
    }

    public Hand getH2() {
        return h2;
    }

    public void setH2(Hand h2) {
        this.h2 = h2;
    }

    @Override
    public String toString() {
        return "Game{" +
                "h1=" + h1 +
                ", h2=" + h2 +
                '}';
    }
}
