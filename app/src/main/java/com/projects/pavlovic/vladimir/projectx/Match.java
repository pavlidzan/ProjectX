package com.projects.pavlovic.vladimir.projectx;

import android.util.Log;

import java.io.Serializable;
import java.util.Random;

/**
 * Created by vladimir.pavlovic on 5/15/2017.
 */

public class Match implements Serializable{
    public static final int PP_X = 10;
    public static final int PP_1 = 11;
    public static final int PP_2 = 12;
    public static final int KI_X = 0;
    public static final int KI_1 = 1;
    public static final int KI_2 = 2;
    public static final int DEFAULT = 5;

    private int mHalfPrediction;
    private int mEndPrediction;
    private int mHalfResult;
    private int mEndResult;
    private String mName;

    public Match(String name) {
        mHalfPrediction = DEFAULT;
        mEndPrediction = DEFAULT;
        mHalfResult = DEFAULT;
        mEndResult = DEFAULT;
        mName = name;
    }

    public void playGame() {
        Random random = new Random();
        mHalfResult = random.nextInt(2) + 10;
        mEndResult = random.nextInt(2);
    }

    public boolean isMatchPredictedProperly(){
        if (mHalfPrediction!=DEFAULT){
            return mHalfPrediction==mHalfResult;
        } else if (mEndPrediction!=DEFAULT){
            return mEndPrediction==mEndResult;
        }else {
            return false;
        }
    }



    public void setPrediction(int tip) {
        if (tip>=0 && tip<3){
            mEndPrediction = tip;
            mHalfPrediction = DEFAULT;
        } else if (tip >= 10 && tip<13){
            mHalfPrediction = tip;
            mEndPrediction = DEFAULT;
        } else {
            Log.i("Match prediction", "Prediction must be set!");
        }
    }

    public int getPrediction() {
        if (mHalfPrediction!=DEFAULT && mEndPrediction==DEFAULT){
            return stringValue(mHalfPrediction);
        }else if (mHalfPrediction==DEFAULT && mEndPrediction!=DEFAULT){
            return stringValue(mEndPrediction);
        } else{
            return stringValue(DEFAULT);
        }
    }
    public int getResult(){
        if (mHalfPrediction!=DEFAULT && mEndPrediction==DEFAULT){
            return stringValue(mHalfResult);
        }else if (mHalfPrediction==DEFAULT && mEndPrediction!=DEFAULT){
            return stringValue(mEndResult);
        } else{
            return stringValue(DEFAULT);
        }
    }
    public int getPredictionToRadioButtons() {
//        if (mEndPrediction == DEFAULT) {
//            return mHalfPrediction;
//        } else {
//            return mEndPrediction;
//        }
        if (mHalfPrediction!=DEFAULT && mEndPrediction==DEFAULT){
            return mHalfPrediction;
        }else if (mHalfPrediction==DEFAULT && mEndPrediction!=DEFAULT){
            return mEndPrediction;
        } else{
            return DEFAULT;
        }
    }
    @Override
    public String toString() {
        return mName;
    }

    private int stringValue(int number){
        int stringInt;
        switch (number){
            case PP_X: stringInt = R.string.x_first_half;
                break;
            case PP_1: stringInt = R.string.first_half_1;
                break;
            case PP_2: stringInt = R.string.first_half_2;
                break;
            case KI_X: stringInt = R.string.x_ki;
                break;
            case KI_1: stringInt = R.string.fr_1;
                break;
            case KI_2: stringInt = R.string.fr_2;
                break;
            default: stringInt = R.string.wrong_result;
        }
        return stringInt;
    }
}
