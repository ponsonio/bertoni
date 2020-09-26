package com.bertoni.test.procesors;

import com.bertoni.test.pojo.Card;
import com.bertoni.test.pojo.Game;
import com.bertoni.test.pojo.Hand;

import java.util.Arrays;

public class GameProcesor {

    /**
     * •	High Card: Highest value card.  (1-20 pts)
     * •	One Pair: Two cards of the same value. (200 pts)
     * •	Two Pairs: Two different pairs. (300 pts)
     * •	Three of a Kind: Three cards of the same value. (400 pts)
     * •	Straight: All cards are consecutive values. (500 pts)
     * •	Flush: All cards of the same suit. (600 pts)
     * •	Full House: Three of a kind and a pair. (700 pts)
     * •	Four of a Kind: Four cards of the same value. (800 pts)
     * •	Straight Flush: All cards are consecutive values of same suit. (900 pts)
     * •	Royal Flush: Ten, Jack, Queen, King, Ace, in same suit.(1000)
     */

    //improve this!!! what a lazy guy!
    double getProbability(int score) {
        if (score == 1000) return 100.00 - 0.000154;
        if (score >= 900 && score < 10000) return 100.00 - 0.00139;
        if (score >= 800 && score < 900) return 100.00 - 0.0240;
        if (score >= 700 && score < 800) return 100.00 - 0.1441;
        if (score >= 600 && score < 700) return 100.00 - 0.1965;
        if (score >= 500 && score < 600) return 100.00 - 0.3925;
        if (score >= 400 && score < 500) return 100.00 - 2.1128;
        if (score >= 300 && score < 400) return 100.00 - 4.7539;
        if (score >= 200 && score < 300) return 100.00 - 42.2569;
        return 50.1177;
    }

    String getWinner(Game g) {
        int sc1 = getScore(g.getH1());
        int sc2 = getScore(g.getH2());

        g.getH1().setWinProbability(getProbability(sc1));
        g.getH2().setWinProbability(getProbability(sc2));

        if (sc1 == sc2) {
            return "TIE";
        }

        return (sc1 > sc2) ? "P1" : "P2";

    }


    int getScore(Hand h) {
        int[] scores = new int[7];
        scores[0] = highCard(h);
        scores[1] = isRoyalSuit(h);
        scores[2] = isStraightFlush(h);
        scores[3] = isFourKind(h);
        scores[4] = pairsTheeScore(h);
        scores[5] = isFlush(h);
        scores[6] = isStraight(h);
        Arrays.sort(scores);
        return scores[6];
    }

    int highCard(Hand h) {
        int max = h.getCards()[0].getValue();
        for (int i = 1; i < 5; i++) {
            max = Math.max(max, h.getCards()[i].getValue());
        }
        return max;
    }

    int isRoyalSuit(Hand hand) {
        boolean isRoyal = true;
        Character type = hand.getCards()[0].getSuit();

        for (int i = 10; i <= 14; i++) {
            boolean hasI = false;
            for (int j = 0; j < hand.getCards().length; j++) {
                Card card = hand.getCards()[j];
                //all on the same suit
                if (card.getSuit() != type) return 0;
                if (i == card.getValue()) {
                    hasI = true;
                    break;
                }
            }
            if (!hasI) {
                isRoyal = false;
                break;
            }

        }

        return isRoyal ? 100 : 0;
    }

    int isStraight(Hand hand) {
        hand.sort();
        int iniVal = hand.getCards()[0].getValue();
        Character suit = hand.getCards()[0].getSuit();

        for (int i = 1; i < hand.getCards().length; i++) {
            Card c = hand.getCards()[i];
            if (c.getValue() != iniVal + 1) return 0;
            iniVal++;
        }
        return 500 + iniVal;
    }

    int isStraightFlush(Hand hand) {
        hand.sort();
        int iniVal = hand.getCards()[0].getValue();
        Character suit = hand.getCards()[0].getSuit();

        for (int i = 1; i < hand.getCards().length; i++) {
            Card c = hand.getCards()[i];
            if (suit != c.getSuit()) return 0;
            if (c.getValue() != iniVal + 1) return 0;
            iniVal++;
        }
        return 900 + iniVal;
    }

    int isFourKind(Hand hand) {
        hand.sort();
        int[] frequencies = new int[15];
        for (Card c : hand.getCards()) {
            frequencies[c.getValue()]++;
        }
        for (int i = 0 ; i < frequencies.length ; i++) {
            if (frequencies[i] == 4) return 800 + i;
        }
        return 0;
    }

    int pairsTheeScore(Hand hand) {
        int[] frequencies = new int[15];
        int pairs = 0;
        int triplets = 0;

        Arrays.fill(frequencies, 0);

        for (Card c : hand.getCards()) {
            frequencies[c.getValue()]++;
        }
        int max = 0 ;

        for (int i = 0; i < frequencies.length; i++) {
            if (frequencies[i] == 3){
                max = Math.max(max, i);
                triplets++;
            }
            if (frequencies[i] == 2) {
                max = Math.max(max, i);
                pairs++;
            }
        }

        if (triplets == 1) {
            if (pairs == 1) {
                //full house
                return 700 + max;
            }
            //one triplet
            return 400 +max;
        }
        //one pair
        if (pairs == 1) return 200 + max;
        //two pair
        if (pairs == 2) return 300 + max;

        return 0;
    }

    int isFlush(Hand hand) {
        Character suit = hand.getCards()[0].getSuit();
        for (int i = 1; i < hand.getCards().length; i++) {
            Card card = hand.getCards()[i];
            if (suit != card.getSuit()) return 0;
        }
        return 600;
    }

}
