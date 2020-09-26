package com.bertoni.test.procesors;

import static org.junit.jupiter.api.Assertions.*;

import com.bertoni.test.pojo.Card;
import com.bertoni.test.pojo.Game;
import com.bertoni.test.pojo.Hand;
import org.junit.jupiter.api.Test;

public class GameProcesorTest {

    private static final int ROYAL_FLUSH_VALUE = 100;
    private static final int STRAIT_FLUSH_VALUE = 900;
    private static final int FOUR_KIND_VALUE = 800;
    private static final int ONE_PAIR_VALUE = 200;
    private static final int TWO_PAIR_VALUE = 300;
    private static final int TRIPLET_VALUE = 400;
    private static final int STRAIT_VALUE = 500;
    private static final int FLUSH_VALUE = 600;
    private static final int FULL_HOUSE_VALUE = 700;
    private static final int ZERO = 0;

    private static final String TIE = "TIE";
    private static final String P2 = "P2";
    private static final String P1 = "P1";

    GameProcesor testee = new GameProcesor();

    //Royal Flush: Ten, Jack, Queen, King, Ace, in same suit.
    //One test per concern
    @Test
    public void testShouldRecognizeRoyalFlush() throws Exception {
        //Arrange
        Card[] cards = new Card[5];
        cards[0] = new Card(10,'S');
        cards[1] = new Card(11,'S');
        cards[2] = new Card(12,'S');
        cards[3] = new Card(13,'S');
        cards[4] = new Card(14,'S');
        Hand hand = new Hand(cards);
        //Act
        int res = testee.isRoyalSuit(hand);
        //Assert
        assertEquals(ROYAL_FLUSH_VALUE, res);
    }

    //Royal Flush: Ten, Jack, Queen, King, Ace, in same suit.
    //One test per concern
    @Test
    public void testShouldRecognizeNotRoyalFlush_DiffSuit() throws Exception {
        //Arrange
        Card[] cards = new Card[5];
        cards[0] = new Card(10,'S');
        cards[1] = new Card(11,'S');
        cards[2] = new Card(12,'S');
        cards[3] = new Card(13,'S');
        cards[4] = new Card(14,'D');
        Hand hand = new Hand(cards);
        //Act
        int res = testee.isRoyalSuit(hand);
        //Assert
        assertEquals(ZERO, res);
    }

    //Royal Flush: Ten, Jack, Queen, King, Ace, in same suit.
    //One test per concern
    @Test
    public void testShouldRecognizeNotRoyalFlush_DiffNums() throws Exception {
        //Arrange
        Card[] cards = new Card[5];
        cards[0] = new Card(9,'S');
        cards[1] = new Card(11,'S');
        cards[2] = new Card(12,'S');
        cards[3] = new Card(13,'S');
        cards[4] = new Card(14,'S');
        Hand hand = new Hand(cards);
        //Act
        int res = testee.isRoyalSuit(hand);
        //Assert
        assertEquals(ZERO, res);
    }

    //Straight Flush: All cards are consecutive values of same suit. (90 pts)
    @Test
    public void testShouldRecognizeStraightFlush() throws Exception {
        //Arrange
        Card[] cards = new Card[5];
        cards[0] = new Card(2,'S');
        cards[1] = new Card(3,'S');
        cards[2] = new Card(4,'S');
        cards[3] = new Card(5,'S');
        cards[4] = new Card(6,'S');
        Hand hand = new Hand(cards);
        //Act
        int res = testee.isStraightFlush(hand);
        //Assert
        assertEquals(STRAIT_FLUSH_VALUE + 6, res);
    }

    @Test
    public void testShouldRecognizeNotStraightFlush_DiffSuit() throws Exception {
        //Arrange
        Card[] cards = new Card[5];
        cards[0] = new Card(2,'S');
        cards[1] = new Card(3,'S');
        cards[2] = new Card(4,'S');
        cards[3] = new Card(5,'S');
        cards[4] = new Card(6,'D');
        Hand hand = new Hand(cards);
        //Act
        int res = testee.isStraightFlush(hand);
        //Assert
        assertEquals(ZERO, res);
    }

    @Test
    public void testShouldRecognizeNotStraightFlush_DiffNums() throws Exception {
        //Arrange
        Card[] cards = new Card[5];
        cards[0] = new Card(2,'S');
        cards[1] = new Card(3,'S');
        cards[2] = new Card(4,'S');
        cards[3] = new Card(5,'S');
        cards[4] = new Card(7,'S');
        Hand hand = new Hand(cards);
        //Act
        int res = testee.isStraightFlush(hand);
        //Assert
        assertEquals(ZERO, res);
    }

    //Four of a Kind: Four cards of the same value. (80 pts)
    @Test
    public void testShouldRecognizeFourKind() throws Exception {
        //Arrange
        Card[] cards = new Card[5];
        cards[0] = new Card(2,'D');
        cards[1] = new Card(2,'S');
        cards[3] = new Card(2,'H');
        cards[4] = new Card(2,'C');
        cards[2] = new Card(4,'H');
        Hand hand = new Hand(cards);
        //Act
        int res = testee.isFourKind(hand);
        //Assert
        assertEquals(FOUR_KIND_VALUE+2, res);
    }

    //Four of a Kind: Four cards of the same value. (80 pts)
    @Test
    public void testShouldNotTRecognizeFourKind_DiffValue() throws Exception {
        //Arrange
        Card[] cards = new Card[5];
        cards[0] = new Card(2,'D');
        cards[1] = new Card(2,'S');
        cards[3] = new Card(2,'H');
        cards[4] = new Card(4,'C');
        cards[2] = new Card(4,'H');
        Hand hand = new Hand(cards);
        //Act
        int res = testee.isFourKind(hand);
        //Assert
        assertEquals(ZERO, res);
    }

    @Test
    public void testShouldNotTRecognizeFourKind_FiveEqualsValues() throws Exception {
        //Arrange
        Card[] cards = new Card[5];
        cards[0] = new Card(2,'D');
        cards[1] = new Card(2,'S');
        cards[3] = new Card(2,'H');
        cards[4] = new Card(2,'C');
        cards[2] = new Card(2,'H');
        Hand hand = new Hand(cards);
        //Act
        int res = testee.isFourKind(hand);
        //Assert
        assertEquals(ZERO, res);
    }

    @Test
    public void shouldReturnHighCard()throws Exception {
        //Arrange
        Card[] cards = new Card[5];
        cards[0] = new Card(2,'D');
        cards[1] = new Card(2,'S');
        cards[3] = new Card(2,'H');
        cards[4] = new Card(2,'C');
        cards[2] = new Card(2,'H');
        Hand hand = new Hand(cards);
        //Act
        int res = testee.highCard(hand);
        //Assert
        assertEquals(2, res);
    }

    @Test
    public void shouldAlsoReturnHighCard()throws Exception {
        //Arrange
        Card[] cards = new Card[5];
        cards[0] = new Card(2,'D');
        cards[1] = new Card(2,'S');
        cards[3] = new Card(2,'H');
        cards[4] = new Card(2,'C');
        cards[2] = new Card(3,'H');
        Hand hand = new Hand(cards);
        //Act
        int res = testee.highCard(hand);
        //Assert
        assertEquals(res, 3);
    }

    @Test
    public void shouldRecognizeAPair()throws Exception {
        //Arrange
        Card[] cards = new Card[5];
        cards[0] = new Card(2,'D');
        cards[1] = new Card(2,'S');
        cards[3] = new Card(3,'H');
        cards[4] = new Card(4,'C');
        cards[2] = new Card(5,'H');
        Hand hand = new Hand(cards);
        //Act
        int res = testee.pairsTheeScore(hand);
        //Assert
        assertEquals(ONE_PAIR_VALUE+2, res);
    }

    @Test
    public void shouldRecognizeTwoPair()throws Exception {
        //Arrange
        Card[] cards = new Card[5];
        cards[0] = new Card(2,'D');
        cards[1] = new Card(2,'S');
        cards[3] = new Card(3,'H');
        cards[4] = new Card(3,'C');
        cards[2] = new Card(5,'H');
        Hand hand = new Hand(cards);
        //Act
        int res = testee.pairsTheeScore(hand);
        //Assert
        assertEquals(TWO_PAIR_VALUE + 3, res);
    }

    @Test
    public void shouldRecognizeTripple()throws Exception {
        //Arrange
        Card[] cards = new Card[5];
        cards[0] = new Card(2,'D');
        cards[1] = new Card(2,'S');
        cards[3] = new Card(3,'H');
        cards[4] = new Card(6,'C');
        cards[2] = new Card(2,'H');
        Hand hand = new Hand(cards);
        //Act
        int res = testee.pairsTheeScore(hand);
        //Assert
        assertEquals(TRIPLET_VALUE + 2, res);
    }

    @Test
    public void shouldRecognizeFullHouse()throws Exception {
        //Arrange
        Card[] cards = new Card[5];
        cards[0] = new Card(2,'D');
        cards[1] = new Card(2,'S');
        cards[3] = new Card(3,'H');
        cards[4] = new Card(3,'C');
        cards[2] = new Card(2,'H');
        Hand hand = new Hand(cards);
        //Act
        int res = testee.pairsTheeScore(hand);
        //Assert
        assertEquals(FULL_HOUSE_VALUE + 3, res);
    }

     //Flush: All cards of the same suit. (60 pts)
    @Test
    public void shouldRecognizeFlush()throws Exception {
        //Arrange
        Card[] cards = new Card[5];
        cards[0] = new Card(2,'D');
        cards[1] = new Card(2,'D');
        cards[3] = new Card(4,'D');
        cards[4] = new Card(7,'D');
        cards[2] = new Card(6,'D');
        Hand hand = new Hand(cards);
        //Act
        int res = testee.isFlush(hand);
        //Assert
        assertEquals(FLUSH_VALUE, res);
    }

    @Test
    public void shouldNotRecognizeFlush()throws Exception {
        //Arrange
        Card[] cards = new Card[5];
        cards[0] = new Card(2,'D');
        cards[1] = new Card(2,'D');
        cards[3] = new Card(4,'D');
        cards[4] = new Card(7,'S');
        cards[2] = new Card(6,'D');
        Hand hand = new Hand(cards);
        //Act
        int res = testee.isFlush(hand);
        //Assert
        assertEquals(ZERO, res);
    }


    //straight: All cards are consecutive values. (50 pts)
    @Test
    public void shouldRecognizeStraight()throws Exception {
        //Arrange
        Card[] cards = new Card[5];
        cards[0] = new Card(2,'D');
        cards[1] = new Card(3,'D');
        cards[3] = new Card(4,'S');
        cards[4] = new Card(5,'D');
        cards[2] = new Card(6,'D');
        Hand hand = new Hand(cards);
        //Act
        int res = testee.isStraight(hand);
        //Assert
        assertEquals(STRAIT_VALUE + 6, res);
    }

    //straight: All cards are consecutive values. (50 pts)
    @Test
    public void shouldNotRecognizeStraight()throws Exception {
        //Arrange
        Card[] cards = new Card[5];
        cards[0] = new Card(2,'D');
        cards[1] = new Card(3,'D');
        cards[3] = new Card(4,'S');
        cards[4] = new Card(6,'D');
        cards[2] = new Card(6,'D');
        Hand hand = new Hand(cards);
        //Act
        int res = testee.isStraight(hand);
        //Assert
        assertEquals(ZERO, res);
    }

    //Games tests
    @Test
    public void testShouldTightRoyalFlush() throws Exception {
        //Arrange
        Card[] cards = new Card[5];
        cards[0] = new Card(10,'S');
        cards[1] = new Card(11,'S');
        cards[2] = new Card(12,'S');
        cards[3] = new Card(13,'S');
        cards[4] = new Card(14,'S');
        Hand hand = new Hand(cards);
        //Act
        Game g = new Game(hand,hand);

        String res = testee.getWinner(g);
        //Assert
        assertEquals(TIE, res);
    }

    //Hand: 1
    //Player 1: 5H 5C 6S 7S KD (Pair of Fives)
    //Player: 2C 3S 8S 8D TD (Pair of Eights)
    //Winner: Player 2
    @Test
    public void testHand1ShouldTWinTwo() throws Exception {
        //Arrange
        Card[] cards = new Card[5];
        cards[0] = new Card(5,'H');
        cards[1] = new Card(5,'C');
        cards[2] = new Card(6,'S');
        cards[3] = new Card(7,'S');
        cards[4] = new Card(13,'D');
        Hand hand1 = new Hand(cards);

        cards = new Card[5];
        cards[0] = new Card(2,'C');
        cards[1] = new Card(3,'S');
        cards[2] = new Card(8,'S');
        cards[3] = new Card(8,'D');
        cards[4] = new Card(13,'D');
        Hand hand2 = new Hand(cards);

        //Act
        Game g = new Game(hand1,hand2);

        String res = testee.getWinner(g);
        //Assert
        assertEquals(P2, res);
    }

    //Hand: 2
    //Player 1: 5D 8C 9S JS AC (Highest card Ace)
    //Player 2: 2C 5C 7D 8S QH (Highest card Queen)
    //Winner: Player 1
    @Test
    public void testHand2ShouldWinOne() throws Exception {
        //Arrange
        Card[] cards = new Card[5];
        cards[0] = new Card(5,'D');
        cards[1] = new Card(8,'C');
        cards[2] = new Card(9,'S');
        cards[3] = new Card(11,'S');
        cards[4] = new Card(14,'C');
        Hand hand1 = new Hand(cards);

        cards = new Card[5];
        cards[0] = new Card(2,'C');
        cards[1] = new Card(5,'C');
        cards[2] = new Card(7,'D');
        cards[3] = new Card(8,'S');
        cards[4] = new Card(12,'H');
        Hand hand2 = new Hand(cards);

        //Act
        Game g = new Game(hand1,hand2);
        String res = testee.getWinner(g);
        //Assert
        assertEquals(P1, res);
    }

    //Hand: 3
    //Player 1: 2D 9C AS AH AC (Three Aces)
    //Player 2: 3D 6D 7D TD QD (Flush with Diamonds)
    //Winner: Player 2
    @Test
    public void testHand3ShouldWinTwo() throws Exception {
        //Arrange
        Card[] cards = new Card[5];
        cards[0] = new Card(2,'D');
        cards[1] = new Card(9,'C');
        cards[2] = new Card(14,'S');
        cards[3] = new Card(14,'H');
        cards[4] = new Card(14,'C');
        Hand hand1 = new Hand(cards);

        cards = new Card[5];
        cards[0] = new Card(3,'D');
        cards[1] = new Card(6,'D');
        cards[2] = new Card(7,'D');
        cards[3] = new Card(8,'D');
        cards[4] = new Card(12,'D');
        Hand hand2 = new Hand(cards);

        //Act
        Game g = new Game(hand1,hand2);
        String res = testee.getWinner(g);
        //Assert
        assertEquals(P2, res);
    }

    //Hand: 4
    //Player 1: 4D 6S 9H QH QC (Pair of Queens, Highest card Nine)
    //Player 2: 3D 6D 7H QD QS (Pair of Queens, Highest card Seven)
    //Winner: Player 1
    @Test
    public void testHand4ShouldWinTwo() throws Exception {
        //Arrange
        Card[] cards = new Card[5];
        cards[0] = new Card(4,'D');
        cards[1] = new Card(6,'S');
        cards[2] = new Card(9,'H');
        cards[3] = new Card(12,'H');
        cards[4] = new Card(12,'C');
        Hand hand1 = new Hand(cards);

        cards = new Card[5];
        cards[0] = new Card(3,'D');
        cards[1] = new Card(6,'D');
        cards[2] = new Card(7,'H');
        cards[3] = new Card(12,'D');
        cards[4] = new Card(13,'S');
        Hand hand2 = new Hand(cards);

        //Act
        Game g = new Game(hand1,hand2);
        String res = testee.getWinner(g);
        //Assert
        assertEquals(P1, res);
    }

    //Hand: 5
    //Player 1: 2H 2D 4C 4D 4S (Full House, with Three Fours)
    //Player 2: 3C 3D 3S 9S 9D (Full House, with Three Threes)
    //Winner: Player 1
    @Test
    public void testHand5ShouldWinTwo() throws Exception {
        //Arrange
        Card[] cards = new Card[5];
        cards[0] = new Card(2,'D');
        cards[1] = new Card(2,'S');
        cards[2] = new Card(4,'H');
        cards[3] = new Card(4,'H');
        cards[4] = new Card(4,'C');
        Hand hand1 = new Hand(cards);

        cards = new Card[5];
        cards[0] = new Card(3,'D');
        cards[1] = new Card(3,'D');
        cards[2] = new Card(3,'H');
        cards[3] = new Card(9,'D');
        cards[4] = new Card(9,'S');
        Hand hand2 = new Hand(cards);

        //Act
        Game g = new Game(hand1,hand2);
        String res = testee.getWinner(g);
        //Assert
        assertEquals(P2, res);
    }

}