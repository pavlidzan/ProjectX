package com.projects.pavlovic.vladimir.projectx;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vladimir.pavlovic on 5/17/2017.
 */

public class ResultAdapter extends ArrayAdapter<Match>{
    public ResultAdapter(Context context, ArrayList<Match> matches) {
        super(context, 0, matches);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Match match=getItem(position);
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.result_custom_list_view, parent,false);
            TextView matchName = (TextView) convertView.findViewById(R.id.resultNameTextView);
            matchName.setText(match.toString());
            TextView prediction = (TextView) convertView.findViewById(R.id.predictionResult);
            prediction.setText(match.getPrediction());
            TextView result = (TextView) convertView.findViewById(R.id.resultResult);
            result.setText(match.getResult());
            CheckedTextView checkedTextView = (CheckedTextView) convertView.findViewById(R.id.checkedTextView);
            boolean isGuessedWright = match.isMatchPredictedProperly();
            checkedTextView.setChecked(true);
            if (isGuessedWright){
                checkedTextView.setCheckMarkDrawable(R.drawable.green_check_mark);
            } else {
                checkedTextView.setCheckMarkDrawable(R.drawable.red_checkmark);
            }
        return convertView;
    }
}
