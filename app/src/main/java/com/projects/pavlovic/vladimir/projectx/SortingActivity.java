package com.projects.pavlovic.vladimir.projectx;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


public class SortingActivity extends AppCompatActivity {

    Button mGenerateRandomNumbers;
    Button mSortNumbers;
    Spinner mSpinner;
    TextView mGeneratedNumbersTextView;
    TextView mSortedNumbersTextView;
    RandomNumbers mRandomNumbers;
    String mWhatIsSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sorting);
        mGeneratedNumbersTextView = (TextView) findViewById(R.id.generatedNumbersText);
        mGenerateRandomNumbers = (Button) findViewById(R.id.button);
        mSpinner = (Spinner) findViewById(R.id.spinner);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mWhatIsSelected = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mSortNumbers = (Button)findViewById(R.id.sort_button);
        mSortedNumbersTextView = (TextView) findViewById(R.id.sorted_numbers_text_view);
    }

    public void generateRandomNumbers(View view) {
        mRandomNumbers = new RandomNumbers();
        mGeneratedNumbersTextView.setText(mRandomNumbers.showNumbers());
        mGeneratedNumbersTextView.setVisibility(View.VISIBLE);
        mSortNumbers.setVisibility(View.VISIBLE);
    }

    public void sortIt(View view) {
        switch (mWhatIsSelected) {
            case "Ascending":
                mRandomNumbers.sortAscending();
                break;
            case "Descending":
                mRandomNumbers.sortDescending();
                break;
            default:
                Toast.makeText(getApplicationContext(),"Pick something",Toast.LENGTH_SHORT).show();
        }
        mSortedNumbersTextView.setText(mRandomNumbers.showNumbers());
        mSortedNumbersTextView.setVisibility(View.VISIBLE);
    }
}
