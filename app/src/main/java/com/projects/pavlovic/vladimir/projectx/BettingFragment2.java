package com.projects.pavlovic.vladimir.projectx;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 */
public class BettingFragment2 extends Fragment {
    public static final String MATCHES = "Matches for ticket.";
    public static final String LEAST_WON_MATCHES = "Least won matches.";
    EditText mSystemNumber;
    Button mPlayButton;
    ListView mListView;
    int mNumberOfGames;
    boolean mIsItSystem;
    TextView mTitleForEditText;
    Ticket mTicket;
    int mLeastWanMatches;

    static class ViewHolder{
        RadioGroup radioGroup;
        TextView matchName;
    }
    public static BettingFragment2 newInstance(int numberOfGames, boolean isItSystem){
        BettingFragment2 bf2 = new BettingFragment2();
        Bundle args = new Bundle();
        args.putInt(BettingFragment.NUMBER_OF_GAMES, numberOfGames);
        args.putBoolean(BettingFragment.IS_IT_SYSTEM_TICKET, isItSystem);
        bf2.setArguments(args);
        return bf2;
    }

    public BettingFragment2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(container==null){
            return null;
        }
        View view = inflater.inflate(R.layout.fragment_betting2, container, false);
        mListView = (ListView) view.findViewById(R.id.listView);
        mPlayButton = (Button) view.findViewById(R.id.playButton);
        mSystemNumber = (EditText) view.findViewById(R.id.numberInSystemET);
        mNumberOfGames = getActivity().getIntent().getIntExtra(BettingFragment.NUMBER_OF_GAMES,1);
        mIsItSystem = getActivity().getIntent().getBooleanExtra(BettingFragment.IS_IT_SYSTEM_TICKET, false);
        mTitleForEditText = (TextView) view.findViewById(R.id.picking_system_tv);
        mTicket = new Ticket(mNumberOfGames, mIsItSystem);
        CustomAdapter adapter = new CustomAdapter(getActivity(), mTicket.getMatches());
        mListView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (mIsItSystem){
            mTitleForEditText.setVisibility(View.VISIBLE);
//            mSystemNumber.setHint(R.string.edit_text_hint + mNumberOfGames);
            mSystemNumber.setHint(R.string.edit_text_hint);
            mSystemNumber.setVisibility(View.VISIBLE);
        }

        mPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String leastWonMatches = mSystemNumber.getText().toString();
                if (leastWonMatches.equals("")) {
                    mLeastWanMatches = 0;
                } else {
                    mLeastWanMatches = Integer.parseInt(leastWonMatches);
                }
                mTicket.playMatches();
                Intent intent = new Intent(getActivity(), BettingActivityResults.class);
                intent.putExtra(MATCHES, mTicket);
                intent.putExtra(LEAST_WON_MATCHES, mLeastWanMatches);
                startActivity(intent);
            }
        });

    }
}
