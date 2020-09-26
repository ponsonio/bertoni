package com.bertoni.test.pojo;

import java.util.Arrays;

public class TournamentResults {

    private int p1Wins = 0;
    private double p1WinsPercent = 0;
    private int p2Wins = 0;
    private double p2WinsPercent = 0;
    private int ties = 0;
    double[] probs;

    public int getP1Wins() {
        return p1Wins;
    }

    public void setP1Wins(int p1Wins) {
        this.p1Wins = p1Wins;
    }

    public double getP1WinsPercent() {
        return p1WinsPercent;
    }

    public void setP1WinsPercent(double p1WinsPercent) {
        this.p1WinsPercent = p1WinsPercent;
    }

    public int getP2Wins() {
        return p2Wins;
    }

    public void setP2Wins(int p2Wins) {
        this.p2Wins = p2Wins;
    }

    public double getP2WinsPercent() {
        return p2WinsPercent;
    }

    public void setP2WinsPercent(double p2WinsPercent) {
        this.p2WinsPercent = p2WinsPercent;
    }

    public int getTies() {
        return ties;
    }

    public void setTies(int ties) {
        this.ties = ties;
    }

    public double[] getProbs() {
        return probs;
    }

    public void setProbs(double[] probs) {
        this.probs = probs;
    }

    @Override
    public String toString() {
        return "TournamentResults{" +
                "p1Wins=" + p1Wins +
                ", p1WinsPercent=" + p1WinsPercent +
                ", p2Wins=" + p2Wins +
                ", p2WinsPercent=" + p2WinsPercent +
                ", ties=" + ties +
                ", probs=" + Arrays.toString(probs) +
                '}';
    }
}
