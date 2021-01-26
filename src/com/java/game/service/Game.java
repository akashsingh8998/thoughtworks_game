package com.java.game.service;

import com.java.game.exception.GameException;
import com.java.game.utils.Constants;
import com.java.game.utils.GameUtils;

import java.util.Scanner;

public class Game {
    static Scanner getInput = new Scanner(System.in);

    public static void initiateGamePlay() throws GameException {
        do{
            startGamePlay();
            System.out.println(Constants.PLAYAGAIN.getValue());
            String choice = getInput.next();
            //exit the game if user chooses "N" or "n"
            if(choice.equals("N") || choice.equals("n")){
                break;
            }
        }while(true);
    }

    private static void startGamePlay() throws GameException {
        System.out.println(Constants.WELCOME.getValue());
        Boolean isUserActive = true;
        while(true){
            GameUtils game = new GameUtils();
            Boolean isPredictionCorrect = game.getPredictions(isUserActive);
            if(isPredictionCorrect && isUserActive){
                System.out.println(Constants.USER_IS_WINNER.getValue());
                break;
            }else if(isPredictionCorrect && !isUserActive){
                System.out.println(Constants.AI_IS_WINNER.getValue());
                break;
            }else{
                System.out.println(Constants.NO_WINNER.getValue());
                isUserActive = !isUserActive;
                continue;
            }
        }
    }
}
