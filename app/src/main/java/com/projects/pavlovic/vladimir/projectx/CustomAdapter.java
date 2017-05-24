package com.projects.pavlovic.vladimir.projectx;

import android.content.Context;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by vladimir.pavlovic on 5/15/2017.
 */

public class CustomAdapter extends ArrayAdapter<Match> {
    public static final String TAG = "CustomAdapter.class";
    private BettingFragment2.ViewHolder viewHolder;
    public CustomAdapter(Context context, ArrayList<Match> matches) {
        super(context, 0, matches);
    }
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Match match = getItem(position);
        Log.i(TAG, "Match id: "+position);
//        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_list_view, parent, false);
            viewHolder = new BettingFragment2.ViewHolder();
            viewHolder.radioGroup = (RadioGroup)convertView.findViewById(R.id.radioGroup);
            viewHolder.matchName = (TextView) convertView.findViewById(R.id.matchIdTextView);

//        } else {
//            viewHolder = (BettingFragment2.ViewHolder) convertView.getTag();
//            viewHolder.radioGroup.clearCheck();
//        }
        viewHolder.matchName.setText(match.toString());
        Log.i(TAG, "Match tw: "+ viewHolder.matchName.getText());
        Log.i(TAG, "To string: "+ match.toString());
        int whichButton = match.getPredictionToRadioButtons();
        switch (whichButton){
            case 10:
//                    buttonXpp.setChecked(true);
                viewHolder.radioGroup.check(R.id.pp_x);
                break;
            case 11:
                viewHolder.radioGroup.check(R.id.pp_1);
//                    button1pp.setChecked(true);
                break;
            case 12:
                viewHolder.radioGroup.check(R.id.pp_2);
//                    button2pp.setChecked(true);
                break;
            case 0:
//                    buttonXki.setChecked(true);
                viewHolder.radioGroup.check(R.id.ki_x);
                break;
            case 1:
//                    button1ki.setChecked(true);
                viewHolder.radioGroup.check(R.id.ki_1);
                break;
            case 2:
//                    button2ki.setChecked(true);
                viewHolder.radioGroup.check(R.id.ki_2);
                break;
            default:
                viewHolder.radioGroup.clearCheck();
                break;
        }
        Log.i(TAG, "Which button " + whichButton);
        Log.i(TAG, "match prediction: "+match.getPredictionToRadioButtons());

//        RadioGroup radioGroup = (RadioGroup) convertView.findViewById(R.id.radioGroup);
//            button1ki = (RadioButton) convertView.findViewById(R.id.ki_1);
//            button2ki = (RadioButton) convertView.findViewById(R.id.ki_2);
//             buttonXki = (RadioButton) convertView.findViewById(R.id.ki_x);
//             button1pp = (RadioButton) convertView.findViewById(R.id.pp_1);
//             button2pp = (RadioButton) convertView.findViewById(R.id.pp_2);
//             buttonXpp = (RadioButton) convertView.findViewById(R.id.pp_x);
        viewHolder.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.ki_1:
                        match.setPrediction(Match.KI_1);
                        break;
                    case R.id.ki_2:
                        match.setPrediction(Match.KI_2);
                        break;
                    case R.id.ki_x:
                        match.setPrediction(Match.KI_X);
                        break;
                    case R.id.pp_1:
                        match.setPrediction(Match.PP_1);
                        break;
                    case R.id.pp_2:
                        match.setPrediction(Match.PP_2);
                        break;
                    case R.id.pp_x:
                        match.setPrediction(Match.PP_X);
                        break;
                }
                Log.i(TAG, "Prediction in listener: "+match.getPredictionToRadioButtons());
                notifyDataSetChanged();
            }
        });
//        convertView.setTag(viewHolder);
        return convertView;
    }
}
