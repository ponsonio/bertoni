package com.bertoni.test;

import com.bertoni.test.file.FileProcesor;
import com.bertoni.test.pojo.Game;
import com.bertoni.test.procesors.GameProcesor;
import com.bertoni.test.procesors.TournamentProcesor;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import com.bertoni.test.pojo.TournamentResults;
import org.apache.log4j.Logger;

public class TournamentController {

    final static Logger logger = Logger.getLogger(TournamentController.class);
    private String file = "";

    //this should be interfaces or abstract clases, so we can invert control if necesary
    TournamentProcesor tournamentProcesor;
    FileProcesor fileProcesor;

    //This is to make code easy to test, as we can inject mocks
    public TournamentController( String file) {
        this.file = file;
        this.fileProcesor = new FileProcesor();
        this.tournamentProcesor = new TournamentProcesor(new GameProcesor());
    }


    //this is to facilitate test, so we can inject dependencies
    public TournamentController(FileProcesor fileProcesor, TournamentProcesor tournamentProcesor) {
        this.fileProcesor = fileProcesor;
        this.tournamentProcesor = tournamentProcesor;
    }

    public void StartTournament() throws Exception{
        try {
            List<Game> games = fileProcesor.getGamesFromFile(file);
            TournamentResults results = tournamentProcesor.processTournament(games);
            printResults(results);
        } catch (Exception e) {
            logger.error("An error happened processing tournament",e);
            throw e;
        }
    }

    private void printResults(TournamentResults tournamentResults) {
        logger.info("Results:");
        logger.info("1: " + tournamentResults.getP1Wins());
        logger.info("2: " + tournamentResults.getP2Wins());
        logger.info("3: " + tournamentResults.getTies());
        logger.info("4: " + Arrays.toString(tournamentResults.getProbs()));
        logger.info("---------PLAYER 1 --------- | ------ PLAYER 2 --------------");
        logger.info("          "+tournamentResults.getP1WinsPercent()
                + "%             |          "+tournamentResults.getP2WinsPercent()+"%");

    }

}
