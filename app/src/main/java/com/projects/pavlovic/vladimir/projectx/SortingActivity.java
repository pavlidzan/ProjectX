package com.projects.pavlovic.vladimir.projectx;

import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class SortingActivity extends AppCompatActivity {

    public static final String SAVED_VALUE = "to be saved";
    public static final String IS_GENERATED = "is generated?";
    public static final String IS_SORTED = "is sorted?";
    Button mGenerateRandomNumbers;
    Button mSortNumbers;
    Spinner mSpinner;
    TextView mGeneratedNumbersTextView;
    TextView mSortedNumbersTextView;
    RandomNumbers mRandomNumbers;
    String mWhatIsSelected;
    boolean mIsNumberGenerated;
    boolean mIsSorted;

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
        if (savedInstanceState != null){
            mRandomNumbers = (RandomNumbers) savedInstanceState.getSerializable(SAVED_VALUE);
            mIsNumberGenerated = savedInstanceState.getBoolean(IS_GENERATED);
            mIsSorted = savedInstanceState.getBoolean(IS_SORTED);
            refreshUi();
        } else {
            mIsNumberGenerated = false;
            mIsSorted = false;
        }

    }

    private void refreshUi() {
        if (mIsNumberGenerated) {
            mGeneratedNumbersTextView.setVisibility(View.VISIBLE);
            mSortNumbers.setVisibility(View.VISIBLE);
        }
        if(mIsSorted){
            mSortedNumbersTextView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {
        bundle.putSerializable(SAVED_VALUE, mRandomNumbers);
        bundle.putBoolean(IS_GENERATED, mIsNumberGenerated);
        bundle.putBoolean(IS_SORTED, mIsSorted);
        super.onSaveInstanceState(bundle);
    }

    public void generateRandomNumbers(View view) {
        mRandomNumbers = new RandomNumbers();
        mGeneratedNumbersTextView.setText(mRandomNumbers.showNumbers());
        mGeneratedNumbersTextView.setVisibility(View.VISIBLE);
        mSortNumbers.setVisibility(View.VISIBLE);
        mIsNumberGenerated = true;
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
        mIsSorted = true;
    }
}
