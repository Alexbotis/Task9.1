package com.itstep.collections.sample;

import com.itsrep.collections.sample.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlaySiteTest {

    PlySite siteTest = new PlySite();

    {
        List<Game> gamestest = new ArrayList<Game>();

        gamestest.add(Game.Game1);
        gamestest.add(Game.Game2);
        gamestest.add(Game.Game3);
        siteTest.setGames(gamestest);

        Map<String, Player> playerstest = new HashMap<String, Player>();

        Player player1 = new Player("name1");
        Player player2 = new Player("name2");
        Player player3 = new Player("name3");
        playerstest.put("name1", player1);
        playerstest.put("name2", player2);
        playerstest.put("name3", player3);
        siteTest.setPlayers(playerstest);


        // data for gameForAllGamesTest()

        Map<Player, List<Game>> gameByPlayertest = new HashMap<Player, List<Game>>();

        //List<Game> gamestest1- для первого игрока Player player1
        List<Game> gamestest1 = new ArrayList<Game>();
        gamestest1.add(Game.Game2);
        gamestest1.add(Game.Game3);


        //List<Game> gamestest2- для первого игрока Player player2
        List<Game> gamestest2 = new ArrayList<Game>();
        gamestest2.add(Game.Game2);
        gamestest2.add(Game.Game1);

        //List<Game> gamestest3- для первого игрока Player player3
        List<Game> gamestest3 = new ArrayList<Game>();
        gamestest3.add(Game.Game2);
        gamestest3.add(Game.Game3);

        gameByPlayertest.put(player1, gamestest1);
        gameByPlayertest.put(player2, gamestest2);
        gameByPlayertest.put(player3, gamestest3);

        siteTest.setGameByPlayer(gameByPlayertest);




    }


    @Test
    public void testAddPlayer() throws UserAlreadyExistsException {
        //Given
        Player player1 = new Player("name1");

        //When
        siteTest.addPlayer(player1);

        //Then
        //no exceptions thrown and
        Assert.assertTrue(siteTest.getPlayers().size() == 1);
        Assert.assertTrue(siteTest.getPlayers().get("name1") == player1);
    }

    @Test(expected = UserAlreadyExistsException.class)
    public void testAddSecondPlayer() throws UserAlreadyExistsException {
        //Given

        Player player = new Player("name");
        Player player2 = new Player("name");

        //MapLeagueManager underTest = new MapLeagueManager();

        //When
        siteTest.addPlayer(player);
        siteTest.addPlayer(player2);
        //Then
        //exception is thrown
    }

    @Test
    public void putToByPlayerMap() throws UserNotFoundException {


        Player newPlayer = new Player("name1");

        List<Game> listForNewPlayer = new ArrayList<Game>();
        listForNewPlayer.add(Game.Game1);

        siteTest.putToByPlayerMap(newPlayer, listForNewPlayer);

    }

    @Test(expected = UserNotFoundException.class)
    public void putToByPlayerMaptest() throws UserNotFoundException {


        Player newPlayer = new Player("name");// нет имени в playerstest

        List<Game> listForNewPlayer = new ArrayList<Game>();
        listForNewPlayer.add(Game.Game1);

        siteTest.putToByPlayerMap(newPlayer, listForNewPlayer);

    }


    @Test//(expected = UserNotFoundException.class)
    public void addRatingTest() throws UserNotFoundException {// падаует???

        Player newPlayer = new Player("name1");

        List<Game> listForNewPlayer = new ArrayList<Game>();
        listForNewPlayer.add(Game.Game1);

        siteTest.putToByPlayerMap(newPlayer, listForNewPlayer);

        siteTest.addRating(newPlayer, Game.Game1, true);



       // System.out.print(newPlayer.getRaiting().get(Game.Game1));
    }
    @Test
    public void gameForAllGamesTest() {


        siteTest.gameForAllGames();


    }

    @Test
    public void theBestPlaerRatingTest(){ // ????

        Map<Game, Integer> raitingTest1 = new HashMap<Game, Integer>();

        Player player1 = new Player("name1");
        Player player2 = new Player("name2");
        Player player3 = new Player("name3");

        raitingTest1.put(Game.Game2,3);
        raitingTest1.put(Game.Game3,3);

        player1.setRaiting(raitingTest1);

        Map<Game, Integer> raitingTest2 = new HashMap<Game, Integer>();
        raitingTest2.put(Game.Game2,3);
        raitingTest2.put(Game.Game1,3);

        player2.setRaiting(raitingTest2);

        Map<Game, Integer> raitingTest3 = new HashMap<Game, Integer>();

        raitingTest3.put(Game.Game2,4);
        raitingTest3.put(Game.Game3,3);
        player3.setRaiting(raitingTest3);

        siteTest.theBestPlaerRating(Game.Game2);




    }
}
