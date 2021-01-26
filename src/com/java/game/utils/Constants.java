package com.java.game.utils;

public enum Constants {
    WELCOME("Welcome to the game!"),
    PLAYAGAIN("Do you want to play again?"),
    USER_IS_PREDICTOR("You are the predictor, what is your input?"),
    AI_IS_PREDICTOR("AI is the predictor, what is your input?"),
    AI_PREDICTION("AI: "),
    USER_IS_WINNER("You WIN!!"),
    AI_IS_WINNER("AI WIN!!"),
    NO_WINNER("No winner."),
    INCORRECT_FORMAT("Bad input: correct input should be of the form CC3, where the first two letters indicate [O]pen or [C]losed state for each hand, followed by the prediction (0-4)"),
    OUT_OF_RANGE("Bad input: prediction should be in the range of 0-4."),
    NO_PREDICTION_REQUIRED("Bad input: no prediction expected, you are not the predictor.");

    Constants(String value){
        this.value = value;
    }

    private String value;
    public String getValue(){
        return this.value;
    }
}
