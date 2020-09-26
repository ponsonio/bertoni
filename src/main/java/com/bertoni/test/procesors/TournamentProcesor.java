package com.bertoni.test.procesors;

import com.bertoni.test.pojo.Game;
import com.bertoni.test.pojo.TournamentResults;

import java.util.List;

public class TournamentProcesor {
    
    GameProcesor gameProcesor;

    public TournamentProcesor(GameProcesor gameProcesor){
        this.gameProcesor = gameProcesor;
    }

    //We process game by game using Game processor TIE, P1, P2 shoudl be an Enum
    public TournamentResults processTournament(List<Game> games) {
        TournamentResults tournamentResults = new TournamentResults();
        double[] probs = new double[games.size() * 2];
        int i = 0;
        for (Game g : games) {
            String res = gameProcesor.getWinner(g);
            probs[i] = g.getH1().getWinProbability();
            probs[i+1] = g.getH2().getWinProbability();
            i +=2;

            if (res.equals("TIE")) {
                tournamentResults.setTies(
                        tournamentResults.getTies() + 1);
                continue;
            }

            if (res.equals("P1")) {
                tournamentResults.setP1Wins(
                        tournamentResults.getP1Wins() + 1);
                continue;
            }

            if (res.equals("P2")) {
                tournamentResults.setP2Wins(
                        tournamentResults.getP2Wins() + 1);
            }
        }
        tournamentResults.setProbs(probs);

        tournamentResults.setP1WinsPercent(
                ((double)tournamentResults.getP1Wins()/games.size()) * 100);

        tournamentResults.setP2WinsPercent(
                ((double)tournamentResults.getP2Wins()/games.size()) * 100);

        return tournamentResults;
    }

}
