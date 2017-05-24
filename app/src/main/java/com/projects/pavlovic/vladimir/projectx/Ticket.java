package com.projects.pavlovic.vladimir.projectx;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by vladimir.pavlovic on 5/15/2017.
 */

public class Ticket implements Serializable{
    private int mNumberOfMatches;
    private ArrayList<Match> mMatches;
    private boolean mIsSystemTicket;

    public Ticket(int numberOfMatches, boolean isItSystem){
        mNumberOfMatches = numberOfMatches;
        mMatches = new ArrayList<>(numberOfMatches);
        mIsSystemTicket = isItSystem;
        for (int i=1; i<=numberOfMatches; i++){
            String name = "Match #"+i;
            Match match = new Match(name);
            mMatches.add(match);
        }
    }
    public ArrayList<Match> getMatches(){
        return mMatches;
    }
    public void playMatches(){
        for (Match match:mMatches){
            match.playGame();
        }
    }
    public int numberOfWinMatches(){
        int number =0;
        for (Match match:mMatches){
            if (match.isMatchPredictedProperly()){
                ++number;
            }
        }
        return number;
    }
}
