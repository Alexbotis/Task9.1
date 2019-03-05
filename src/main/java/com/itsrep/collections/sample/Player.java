package com.itsrep.collections.sample;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Player {

    private String nickName;// ник
    private Map<Game, Integer> raiting = new HashMap<Game, Integer>();// мапа для рейтинга в каждой игре
    private int raitingByGame;// рейтинг в одной фиксированной  игре для comparator
    private int raitingByAllGame;// рейтинг во всех сыгранных играх  для comparator1

    public String getNickName() {
        return nickName;
    }

    public Player(String nickName) {
        this.nickName = nickName;
    }

    public Map<Game, Integer> getRaiting() {
        return raiting;
    }

    public void setRaiting(Map<Game, Integer> raiting) {
        this.raiting = raiting;
    }

    public int getRaitingByGame() {
        return raitingByGame;
    }

    public void setRaitingByGame(int raitingByGame) {
        this.raitingByGame = raitingByGame;
    }

    public int getRaitingByAllGame() {
        return raitingByAllGame;
    }

    public void setRaitingByAllGame(int raitingByAllGame) {
        this.raitingByAllGame = raitingByAllGame;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String toString() {
        return getNickName().toString();

    }
}
