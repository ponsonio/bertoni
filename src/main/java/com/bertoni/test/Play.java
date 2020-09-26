package com.bertoni.test;

import com.bertoni.test.TournamentController;
import org.apache.log4j.Logger;

public class Play {

    final static Logger logger = Logger.getLogger(Play.class);

    public static void main(String[] args) throws Exception{
        //String file = args[0];
        String file = "./src/test/resources/pokerdata.txt";
        logger.info("File to process:" + file);
        TournamentController tournamentController = new TournamentController(file);
        tournamentController.StartTournament();
    }



}
