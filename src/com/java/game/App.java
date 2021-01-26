package com.java.game;

import com.java.game.exception.GameException;
import com.java.game.service.Game;

public class App {

    public static void main (String[] args) throws GameException {
        try{
            Game.initiateGamePlay();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

}
