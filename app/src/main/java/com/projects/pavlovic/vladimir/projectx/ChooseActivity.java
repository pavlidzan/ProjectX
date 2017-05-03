package com.projects.pavlovic.vladimir.projectx;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class ChooseActivity extends AppCompatActivity {

    Button mSortButton;
    Button mBetButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
        mSortButton = (Button) findViewById(R.id.sorting);
        mBetButton = (Button) findViewById(R.id.betting);
    }

    public void startSorting(View view) {
        Intent intent = new Intent(this, SortingActivity.class);
        startActivity(intent);
    }

    public void startBetting(View view) {
    }
}
