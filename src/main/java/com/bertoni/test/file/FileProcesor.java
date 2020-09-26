package com.bertoni.test.file;

import com.bertoni.test.pojo.Card;
import com.bertoni.test.pojo.Game;
import com.bertoni.test.pojo.Hand;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner; // Import the Scanner class to read text files
import org.apache.log4j.Logger;


public class FileProcesor {

    final static Logger logger = Logger.getLogger(FileProcesor.class);

    //this methods can be static but as performance it's not a critical issue,
    // but it's not to facilite test, although it's posible to use Powermock
    public  List<Game> getGamesFromFile(String file) throws FileNotFoundException {
        File f = new File(file);
        List<Game> games = new ArrayList<Game>();
        Scanner myReader = new Scanner(f);
        while (myReader.hasNextLine()) {
            String line = myReader.nextLine();
            games.add(processLine(line));
        }
        myReader.close();
        logger.info("games parsed from file: " + games.size());
        return games;

    }


    private Game processLine(String line) {
        String[] tokens = line.split(" ");
        Card[] h1 = new Card[5];
        Card[] h2 = new Card[5];
        for (int i = 0 ; i < 5 ; i++) {
            String c = tokens[i];
            h1[i] = parseCard(c);;

            c = tokens[5+i];
            h2[i] = parseCard(c);
        }

        Game g = new Game(new Hand(h1), new Hand(h2));
        return g;
    }

    private static int cardValue(char c) {
        if (Character.isDigit(c)) {
            return Integer.parseInt(c +"");
        }
        if (c == 'T') return 10;
        if (c == 'J') return 11;
        if (c == 'Q') return 12;
        if (c == 'K') return 13;
        if (c == 'A') return 14;
        return 0;
    }

    private Card parseCard(String token) {
        Card c = new Card();
        c.setValue(cardValue(token.charAt(0)));
        c.setSuit(token.charAt(1));
        return c;
    }

}
