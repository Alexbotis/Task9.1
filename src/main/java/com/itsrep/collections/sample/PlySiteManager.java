package com.itsrep.collections.sample;

import java.util.List;

/**
 * Created by Student on 26.02.2019.
 */
public interface PlySiteManager {


    /**
     * Name will be used as unique identifier of player.
     *
     * @param player
     */


    public void addPlayer(Player player) throws UserAlreadyExistsException;

    public void putToByPlayerMap(Player newPlayer, List<Game> listForNewPlayer)throws UserNotFoundException;

    public void addRating(Player player, Game game, boolean rezult) throws UserNotFoundException;

    public void gameForAllGames ();

    public void printPlaerRating(String namePlayer, Game game);

    public void theBestPlaerRating( Game game);

    public void theBestPlaerRatingAllGames();


}
