package com.projects.pavlovic.vladimir.projectx;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ResultsFragment extends Fragment {

    ListView mListView;
    TextView mCongratulationTextView;
    TextView mInfoTextView;
    Button mPlayAgain;
    private Ticket mTicket;
    private ArrayList<Match> mMatches;
    private int mLeastWonMatches;


    public ResultsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_results, container, false);
        mListView = (ListView) view.findViewById(R.id.resultListView);
        mCongratulationTextView = (TextView) view.findViewById(R.id.congratulationTextView);
        mInfoTextView = (TextView) view.findViewById(R.id.whatNeedToWinTextView);
        mPlayAgain = (Button) view.findViewById(R.id.playAgainButton);
        mPlayAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ChooseActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
        mLeastWonMatches = getActivity().getIntent().getIntExtra(BettingFragment2.LEAST_WON_MATCHES,0);
        mTicket = (Ticket) getActivity().getIntent().getSerializableExtra(BettingFragment2.MATCHES);
        mMatches = mTicket.getMatches();
        ResultAdapter resultAdapter = new ResultAdapter(getActivity(),mMatches);
        mListView.setAdapter(resultAdapter);
        int winMathes = mTicket.numberOfWinMatches();
        if (mLeastWonMatches!=0) {
            if ( winMathes >= mLeastWonMatches) {
                mCongratulationTextView.setText(R.string.congr_won);
            } else {
                mCongratulationTextView.setText(R.string.congr_lose);
            }
        } else if (winMathes==mMatches.size()){
            mCongratulationTextView.setText(R.string.congr_won);
        } else {
            mCongratulationTextView.setText(R.string.congr_lose);
        }
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (mLeastWonMatches == 0){
            mInfoTextView.setText(R.string.result_info);
        } else {
            String infoText = getResources().getString(R.string.win_matches_at_least,mLeastWonMatches);
            mInfoTextView.setText(infoText);
        }
    }

}
