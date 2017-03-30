package com.example.android.quizapp;

import android.graphics.Color;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.attr.checked;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;
import static android.widget.Toast.makeText;
import static com.example.android.quizapp.R.id.default_activity_button;
import static com.example.android.quizapp.R.id.radio;
import static com.example.android.quizapp.R.id.radioGroup;
import static com.example.android.quizapp.R.id.radioGroup2;
import static com.example.android.quizapp.R.id.result1;
import static com.example.android.quizapp.R.id.resultView;

public class MainActivity extends AppCompatActivity {

    int points = 0;
    static String wrong = "Wrong";
    static String correct = "Correct";
    boolean questionChecked = false;
    CheckBox checkBox1;
    CheckBox checkBox2;
    CheckBox checkBox3;
    TextView resultView;
    TextView result1View;
    TextView result2View;
    TextView result3View;
    TextView result4View;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkBox1 = (CheckBox) findViewById(R.id.q3Answer1);
        checkBox2 = (CheckBox) findViewById(R.id.q3Answer2);
        checkBox3 = (CheckBox) findViewById(R.id.q3Answer3);
        resultView = (TextView) findViewById(R.id.resultView);
        result1View = (TextView) findViewById(R.id.result1);
        result2View = (TextView) findViewById(R.id.result2);
        result3View = (TextView) findViewById(R.id.result3);
        result4View = (TextView) findViewById(R.id.result4);
    }

    public void onSubmitClick(View view) {
        if (!questionChecked) {
            question1Check();
            question2Check();
            question3Check();
            question4Check();
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
                result1 = wrong;
                break;
            case R.id.BooleanField:
                points += 1;
                result1 = correct;
                isCorrect = true;
                break;
            case R.id.stringField:
                result1 = wrong;
                break;
            default:
                result1 = wrong;
                break;
        }
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
                result2 = wrong;
                break;
            case R.id.falseBT:
                result2 = correct;
                isCorrect = true;
                points += 1;
                break;
            default:
                result2 = wrong;
                break;
        }
        result2View.setText(result2);
        if (isCorrect)
            result2View.setTextColor(Color.GREEN);
        else
            result2View.setTextColor(Color.RED);
    }

    private void question3Check() {
        if (!checkBox1.isChecked() && checkBox2.isChecked() && !checkBox3.isChecked()) {
            result3View.setText(correct);
            points += 1;
            result3View.setTextColor(Color.GREEN);
        } else {
            result3View.setText(wrong);
            result3View.setTextColor(Color.RED);
        }
    }

    private void question4Check(){
        EditText editTextView = (EditText)findViewById(R.id.editTextView);
        String answer = editTextView.getText().toString();
        if(answer.contains("int quantity = 4;")){
            result4View.setText(correct);
            points += 1;
            result4View.setTextColor(Color.GREEN);
        }
        else{
            result4View.setText(wrong);
            result4View.setTextColor(Color.RED);
        }

    }

    private void updateScore() {
        resultView.setText("Points: " + points + "/4");
        Toast toast =Toast.makeText(this,"Points: " + points, Toast.LENGTH_SHORT);
        toast.show();
    }

    public void onResetClick(View view) {
        questionChecked = false;
        points = 0;
        checkBox1.setChecked(false);
        checkBox2.setChecked(false);
        checkBox3.setChecked(false);
        //RadioButtons cleanup
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        RadioGroup radioGroup2 = (RadioGroup) findViewById(R.id.radioGroup2);
        radioGroup.clearCheck();
        radioGroup2.clearCheck();
        //TextViews cleanup
        result1View.setText("");
        result2View.setText("");
        result3View.setText("");
        updateScore();
    }

}
