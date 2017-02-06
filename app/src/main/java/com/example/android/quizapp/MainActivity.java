package com.example.android.quizapp;

import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import static android.R.attr.checked;

public class MainActivity extends AppCompatActivity {

    int points = 0;
    boolean questionChecked = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onSubmitClick(View view){
        if(!questionChecked) {
            question1Validation();
            updateScore();
            questionChecked = true;
        }
    }
    private void question1Validation()
    {
        RadioGroup buttonRadio =  (RadioGroup) findViewById(R.id.radioGroup);
        int checked = buttonRadio.getCheckedRadioButtonId();
        String result = getText(R.string.resultQ1).toString();
        // Check which radio button was clicked
        switch(checked) {
            case R.id.intField:
                    result = "Wrong";
                break;
            case R.id.BooleanField:
                    points += 1;
                result = "Correct";
                break;
            case R.id.stringField:
                    result = "Wrong";
                break;
        }
    }

    private void updateScore(){
        TextView resultView = (TextView)findViewById(R.id.resultView);
        resultView.setText("Points: "+points+"/4");
    }

    public void onResetClick(View view){
        questionChecked = false;
        points = 0;
        RadioGroup buttonRadio =  (RadioGroup) findViewById(R.id.radioGroup);
        buttonRadio.clearCheck();
        updateScore();
    }

}
