package com.example.android.quizapp;

import android.graphics.Color;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import static android.R.attr.checked;
import static com.example.android.quizapp.R.id.radio;
import static com.example.android.quizapp.R.id.radioGroup;
import static com.example.android.quizapp.R.id.radioGroup2;
import static com.example.android.quizapp.R.id.result1;
import static com.example.android.quizapp.R.id.resultView;

public class MainActivity extends AppCompatActivity {

    int points = 0;
    boolean questionChecked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onSubmitClick(View view) {
        if (!questionChecked) {
            question1Check();
            question2Check();
            question3Check();
            updateScore();
            questionChecked = true;
        }
    }

    private void question1Check() {
        RadioGroup buttonRadio = (RadioGroup) findViewById(radioGroup);
        int checked = buttonRadio.getCheckedRadioButtonId();
        boolean isCorrect = false;
        String result1 = getText(R.string.resultQ1).toString();
        // Check which radio button was clicked
        switch (checked) {
            case R.id.intField:
                result1 = "Wrong";
                break;
            case R.id.BooleanField:
                points += 1;
                result1 = "Correct";
                isCorrect = true;
                break;
            case R.id.stringField:
                result1 = "Wrong";
                break;
        }
        TextView result1View = (TextView) findViewById(R.id.result1);
        result1View.setText(result1);
        if (isCorrect)
            result1View.setTextColor(Color.GREEN);
        else
            result1View.setTextColor(Color.RED);
    }

    private void question2Check() {
        RadioGroup radioGroup2 = (RadioGroup) findViewById(R.id.radioGroup2);
        int checked = radioGroup2.getCheckedRadioButtonId();
        String result2 = getText(R.string.resultQ1).toString();
        boolean isCorrect = false;
        switch (checked) {
            case R.id.trueBT:
                result2 = "Wrong";
                break;
            case R.id.falseBT:
                result2 = "Correct";
                isCorrect = true;
                points += 1;
                break;
        }
        TextView result2View = (TextView) findViewById(R.id.result2);
        result2View.setText(result2);
        if (isCorrect)
            result2View.setTextColor(Color.GREEN);
        else
            result2View.setTextColor(Color.RED);
    }

    private void question3Check() {
        CheckBox checkBox1 = (CheckBox) findViewById(R.id.q3Answer1);
        CheckBox checkBox2 = (CheckBox) findViewById(R.id.q3Answer2);
        CheckBox checkBox3 = (CheckBox) findViewById(R.id.q3Answer3);
        TextView result3View = (TextView) findViewById(R.id.result3);
        if (!checkBox1.isChecked() && checkBox2.isChecked() && !checkBox3.isChecked()) {
            result3View.setText("Correct");
            points += 1;
            result3View.setTextColor(Color.GREEN);
        } else {
            result3View.setText("" + checkBox2.isChecked());
            result3View.setTextColor(Color.RED);
        }
    }

    private void updateScore() {
        TextView resultView = (TextView) findViewById(R.id.resultView);
        resultView.setText("Points: " + points + "/4");
    }

    public void onResetClick(View view) {
        questionChecked = false;
        points = 0;
        //Checkbox cleanup
        CheckBox checkBox1 = (CheckBox) findViewById(R.id.q3Answer1);
        CheckBox checkBox2 = (CheckBox) findViewById(R.id.q3Answer2);
        CheckBox checkBox3 = (CheckBox) findViewById(R.id.q3Answer3);
        checkBox1.setChecked(false);
        checkBox2.setChecked(false);
        checkBox3.setChecked(false);
        //RadioButtons cleanup
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        RadioGroup radioGroup2 = (RadioGroup) findViewById(R.id.radioGroup2);
        radioGroup.clearCheck();
        radioGroup2.clearCheck();
        //TextViews cleanup
        TextView result1View = (TextView) findViewById(R.id.result1);
        TextView result2View = (TextView) findViewById(R.id.result2);
        TextView result3View = (TextView) findViewById(R.id.result3);
        result1View.setText("");
        result2View.setText("");
        result3View.setText("");

        updateScore();
    }

}
