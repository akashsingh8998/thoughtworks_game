package com.java.game.utils;

import com.java.game.exception.GameException;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class GameUtils {
    private static Scanner getInput = new Scanner(System.in);
    private static final List<String> INPUT_POSSIBILITIES = Arrays.asList("O", "C");

    public Boolean getPredictions(Boolean isUserActive) throws GameException {
        String aiPrediction = getAIPrediction(!isUserActive);
        String userPrediction = getUserPrediction(isUserActive);
        System.out.println(Constants.AI_PREDICTION.getValue() + aiPrediction);
        if(isUserActive){
            return checkIfPredictionIsCorrect(userPrediction,aiPrediction);
        }else{
            return checkIfPredictionIsCorrect(aiPrediction,userPrediction);
        }
    }

    private static String getAIPrediction(Boolean isActive) {
        Random rand = new Random();
        String left = INPUT_POSSIBILITIES.get(rand.nextInt(1000) % 2);
        String right = INPUT_POSSIBILITIES.get(rand.nextInt(1000) % 2);
        int count = rand.nextInt(4);
        if(isActive) {
            return left + right + count;
        }
        else {
            return left + right;
        }
    }

    private static String getUserPrediction(Boolean isActive) throws GameException {
        if(isActive){
            System.out.println(Constants.USER_IS_PREDICTOR.getValue());
        }else{
            System.out.println(Constants.AI_IS_PREDICTOR.getValue());
        }
        String userPrediction = getInput.next();
        //validate if users prediction is correct
        checkIfUsersPredictionIsOfCorrectFormat(isActive,userPrediction);
        return userPrediction;
    }

    private static void checkIfUsersPredictionIsOfCorrectFormat(Boolean isActive,String userPrediction) throws GameException {
        Integer expectedPredictionLength = isActive ? 3 : 2;
        if(!expectedPredictionLength.equals(userPrediction.length())){
            if(expectedPredictionLength.equals(2) && userPrediction.length() > 2 && Character.isDigit(userPrediction.charAt(2))) {
                throw new GameException(Constants.NO_PREDICTION_REQUIRED.getValue());
            }
            throw new GameException(Constants.INCORRECT_FORMAT.getValue());
        }
        if(!INPUT_POSSIBILITIES.contains(String.valueOf(userPrediction.charAt(0))) || !INPUT_POSSIBILITIES.contains(String.valueOf(userPrediction.charAt(1)))) {
            throw new GameException(Constants.INCORRECT_FORMAT.getValue());
        }
        if(isActive){
            Integer predictionValue;
            try{
                predictionValue = Integer.parseInt(String.valueOf(userPrediction.charAt(2)));
            }catch (Exception e){
                throw new GameException(Constants.INCORRECT_FORMAT.getValue());
            }
            if(predictionValue > 4){
                throw new GameException(Constants.OUT_OF_RANGE.getValue());
            }
        }
    }

    private static Boolean checkIfPredictionIsCorrect(String predictorInput, String playerInput){
        Integer predictionValue = Integer.parseInt(String.valueOf(predictorInput.charAt(2)));
        String combinedInput = playerInput + predictorInput;
        Integer count = 0;
        for(int i=0;i<combinedInput.length()-1;i++){
            if(String.valueOf(combinedInput.charAt(i)).equals("O")){
                count = count + 1;
            }
        }
        if(predictionValue.equals(count)){
            return true;
        }
        return false;
    }
}
