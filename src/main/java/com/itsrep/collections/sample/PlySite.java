package com.itsrep.collections.sample;

import java.lang.reflect.Array;
import java.util.*;

import static com.sun.org.apache.xml.internal.security.keys.keyresolver.KeyResolver.iterator;


public class PlySite implements PlySiteManager {

    private List<Game> games = new ArrayList<Game>();// лист всех игр на сайте
    private Map<String, Player> players = new HashMap<String, Player>();//мапа ник-игрок
    private Map<Player, List<Game>> gameByPlayer = new HashMap<Player, List<Game>>();// мапа игрок- лист игр которые он выбрал на сайте

    public PlySite (){};

    public Map<String, Player> getPlayers() {
        return players;
    }

    public void setPlayers(Map<String, Player> players) {
        this.players = players;
    }

    public Player[] getAllPlayers() {
        return players.keySet().toArray(new Player[players.size()]);
    }

    public Game[] getSetGames(Player player) {
        return gameByPlayer.get(player).toArray(new Game[gameByPlayer.get(player).size()]);
    }

    public void setGameByPlayer(Map<Player, List<Game>> gameByPlayer) {
        this.gameByPlayer = gameByPlayer;
    }

    public Map<Player, List<Game>> getGameByPlayer() {
        return gameByPlayer;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    public List<Game> getGames() {
        return games;
    }

    public void addPlayer(Player player) throws UserAlreadyExistsException {

        String nickName = player.getNickName();
        Player player1 = players.get(nickName);
        if (player1 != null) {
            throw new UserAlreadyExistsException();
        }
        players.put(nickName, player);
    }

    public void putToByPlayerMap(Player newPlayer, List<Game> listForNewPlayer)
                                 throws UserNotFoundException {

        if (players.containsKey(newPlayer.getNickName()) && games.containsAll(listForNewPlayer)) {

            gameByPlayer.put(newPlayer, listForNewPlayer);
        } else {
            throw new UserNotFoundException();

        }

    }

    public void addRating(Player player, Game game, boolean rezult)
            throws UserNotFoundException {

        if (gameByPlayer.containsKey(player.getNickName()) && gameByPlayer.get(player).contains(game)
                                     && rezult == true)
        // если игрок и игра  в мапе gameByPlayer и результат  игры выигрышный т.е. true
        {
            int valueOfGame = player.getRaiting().get(game) + 1;// то соответствующей игре повышаем рейтинг

            player.getRaiting().put(game, valueOfGame);// и помещаем в мапу raiting новый результат

        } else {
            throw new UserNotFoundException();

        }
    }


    public void gameForAllGames () {

        for (Game game : games) {// для каждой игры в списке игр

          boolean   result= true;// флаг

            for (Player player : gameByPlayer.keySet()) {// для каждого игрока из мапы

                if (!gameByPlayer.get(player).contains(game)){// если игра не содержиться в списке

                    result= false;// флаг меняем
                    break;

                }

            }

            if (result== true){// если флаг true

                System.out.println (game.toString());


            }
        }
    }

    public void printPlaerRating(String namePlayer, Game game) {

        Player player = players.get(namePlayer);// из мапы players получаем player
        int rating = player.getRaiting().get(game);// из мапы raiting получаеь rating

        System.out.println("рейтинг игрока" + namePlayer.toString() + ""
                + game.toString() + " " + rating);


    }



    public void theBestPlaerRating( Game game) {

        List<Player> playersByGame= new ArrayList<Player> ();// вспомогательный лист

        for (Player player :gameByPlayer.keySet() ){ // для каждого игрока из мапы gameByPlayer

            if (gameByPlayer.get(player).contains(game)) {// если игрок играл в данную игру

                playersByGame.add (player); // добавляем его в вспомогательный лист  playersByGame
                player.setRaitingByGame(player.getRaiting().get(game));// в поле RaitingByGame для player добавляем рейтинг
                // по данной игре из мапы raiting

            }
        }
         Comparator<Player> comparator = new Comparator<Player>() {
        public int compare(Player o1, Player o2) {
         return  o2.getRaitingByGame()-o1.getRaitingByGame();// сравнение по полю рейтинга в данной игре
         }
         };

      Collections.sort(playersByGame,comparator);// сортировка по убыванию

       Player [] playersByGameArray=playersByGame.toArray(new Player [playersByGame.size()] );
       // приводим лист в Player [] playersByGameArray

       for (int i=0;i<playersByGameArray.length;i++){

           System.out.print(playersByGameArray[i].toString());
           // выводим на печать первые 10 playersByGameArray

       }

    }
    public void theBestPlaerRatingAllGames(){

        List<Player> listPlayers= new ArrayList <Player> ();// вспомогательный массив

        for (Player player :gameByPlayer.keySet() ){// для каждого игрока из мапы
            listPlayers.add(player);// добавляем его в мапу

        for    (Game game: gameByPlayer.get(player)){ //для каждой игры из листа для игрока gameByPlayer.get(player

            player.setRaitingByAllGame(player.getRaitingByAllGame()+player.getRaiting().get(game));
            // в поле RaitingByAllGame суммируем рейтинги из мапы Raiting по всем играм

    }

    }
        Comparator<Player> comparator1 = new Comparator<Player>() {// компаратор по убыванию

            public int compare(Player o1, Player o2) {
                return o2.getRaitingByAllGame() - o1.getRaitingByAllGame();// по полю RaitingByAll для игрока
            }
        };

        Collections.sort(listPlayers,comparator1);// сортировка по компаратору

        Player [] playersByAllGameArray=listPlayers.toArray(new Player [listPlayers.size()] );
        // переводим в массив   Player [] playersByAllGameArray

        for (int i=0;i<10;i++){ // выводим на печать первых 10

            System.out.print(playersByAllGameArray[i].toString());

        }
        }

    }














