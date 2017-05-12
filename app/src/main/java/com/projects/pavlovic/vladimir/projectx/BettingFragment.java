package com.projects.pavlovic.vladimir.projectx;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;


public class BettingFragment extends Fragment {
    public static final String IS_IT_SYSTEM_TICKET = "Is it system ticket?";
    public static final String NUMBER_OF_GAMES = "Number of games.";
    Button mCreateGames;
    CheckBox mSystemTicket;
    EditText mEditText;
    boolean mIsItSystemTicket;
    int mNumberOfGames;
    boolean mDualPane;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_betting, container, false);
        mCreateGames = (Button) view.findViewById(R.id.create_games);
        mSystemTicket = (CheckBox) view.findViewById(R.id.checkBox);
        mEditText = (EditText) view.findViewById(R.id.editText);
        mCreateGames.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                createGames();
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View fragment2 = getActivity().findViewById(R.id.betting_second);
        mDualPane = fragment2 != null && fragment2.getVisibility() == View.VISIBLE;
    }

    private void showSecondFragment(int numberOfGames, boolean isItSystem, boolean isDualPane) {
        if (isDualPane) {
            BettingFragment2 bf2 = BettingFragment2.newInstance(numberOfGames, isItSystem);
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.betting_second, bf2);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
        } else {
            Intent intent = new Intent(getActivity(), BettingActivity2.class);
            intent.putExtra(IS_IT_SYSTEM_TICKET, isItSystem);
            intent.putExtra(NUMBER_OF_GAMES, numberOfGames);
            startActivity(intent);
            Log.i("BettingActivity", "BettingActivity 2 is started");
        }
    }
    private void createGames() {
        mIsItSystemTicket = mSystemTicket.isChecked();
        String string = mEditText.getText().toString();
        if (string.equals("")) {
            mNumberOfGames = 0;
        } else {
            mNumberOfGames = Integer.parseInt(string);
        }
        if (mNumberOfGames == 0 || mNumberOfGames > 20) {
            Toast.makeText(getActivity(), "Max number of games is 20", Toast.LENGTH_SHORT).show();
        } else {
            showSecondFragment(mNumberOfGames, mIsItSystemTicket, mDualPane);
        }
    }
}



