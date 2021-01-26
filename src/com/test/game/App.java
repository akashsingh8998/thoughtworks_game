package com.test.game;

import java.lang.reflect.Method;

import com.java.game.exception.GameException;
import com.java.game.utils.GameUtils;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class App {

    private static Method checkIfPredictionIsCorrectMethod, checkIfUsersPredictionIsOfCorrectFormatMethod, getAIPredictionMethod;

    @BeforeClass
    public static void init() throws Exception {
        checkIfPredictionIsCorrectMethod = GameUtils.class.getDeclaredMethod("checkIfPredictionIsCorrect",String.class,String.class);
        checkIfPredictionIsCorrectMethod.setAccessible(true);
        getAIPredictionMethod = GameUtils.class.getDeclaredMethod("getAIPrediction",Boolean.class);
        getAIPredictionMethod.setAccessible(true);
        checkIfUsersPredictionIsOfCorrectFormatMethod = GameUtils.class.getDeclaredMethod("checkIfUsersPredictionIsOfCorrectFormat",Boolean.class,String.class);
        checkIfUsersPredictionIsOfCorrectFormatMethod.setAccessible(true);
    }

    @Test
    public void predictionIsCorrect() throws Exception {
        Boolean isCorrect  = (Boolean) checkIfPredictionIsCorrectMethod.invoke(null,"OC2","CO");
        Assert.assertEquals(true, isCorrect);
    }

    @Test
    public void predictionIsNotCorrect() throws Exception {
        Boolean isCorrect  = (Boolean) checkIfPredictionIsCorrectMethod.invoke(null,"OC2","CC");
        Assert.assertEquals(false, isCorrect);
    }

    @Test
    public void checkAiPrediction() throws Exception {
        String aiPrediction = (String) getAIPredictionMethod.invoke(null,true);
        Assert.assertEquals(3,aiPrediction.length());
    }

    @Test
    public void checkAiInputs() throws Exception {
        String aiPrediction = (String) getAIPredictionMethod.invoke(null,false);
        Assert.assertEquals(2,aiPrediction.length());
    }

    @Test(expected = Exception.class)
    public void checkIncorrectFormatException() throws Exception {
        Exception exception = (Exception) checkIfUsersPredictionIsOfCorrectFormatMethod.invoke(null,true, "OC24");
        Assert.assertEquals(GameException.class, exception.getCause().getClass());
    }

    @Test(expected = Exception.class)
    public void checkOutOfRangeException() throws Exception {
        Exception exception = (Exception) checkIfUsersPredictionIsOfCorrectFormatMethod.invoke(null,true, "OC6");
        Assert.assertEquals(GameException.class, exception.getCause().getClass());
    }

    @Test(expected = Exception.class)
    public void checkNoPredictionRequiredException() throws Exception {
        Exception exception = (Exception) checkIfUsersPredictionIsOfCorrectFormatMethod.invoke(null,false, "OC3");
        Assert.assertEquals(GameException.class, exception.getCause().getClass());
    }
}
