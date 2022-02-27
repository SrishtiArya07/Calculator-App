package com.example.basiccalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import org.mariuszgromada.math.mxparser.*;

public class MainActivity extends AppCompatActivity {

    private TextView previousCalculation;
    private EditText display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        previousCalculation = findViewById(R.id.displayPreviousCalculation);
        display = findViewById(R.id.displayEditText);

        display.setShowSoftInputOnFocus(false);
    }

    private void updateText(String strToAdd) {
        String oldStr = display.getText().toString();
        int cursorPos = display.getSelectionStart();
        String leftStr = oldStr.substring(0,cursorPos);
        String rightStr = oldStr.substring(cursorPos);

        display.setText(String.format("%s%s%s", leftStr, strToAdd, rightStr));
        display.setSelection(cursorPos + strToAdd.length());
    }

    public void zeroBTNPush(View view){
        updateText(getResources().getString(R.string.zero));
    }

    public void oneBTNPush(View view){
        updateText(getResources().getString(R.string.one));
    }

    public void twoBTNPush(View view){
        updateText(getResources().getString(R.string.two));
    }

    public void threeBTNPush(View view){
        updateText(getResources().getString(R.string.three));
    }

    public void fourBTNPush(View view){
        updateText(getResources().getString(R.string.four));
    }

    public void fiveBTNPush(View view){
        updateText(getResources().getString(R.string.five));
    }

    public void sixBTNPush(View view){
        updateText(getResources().getString(R.string.six));
    }

    public void sevenBTNPush(View view){
        updateText(getResources().getString(R.string.seven));
    }

    public void eightBTNPush(View view){
        updateText(getResources().getString(R.string.eight));
    }

    public void nineBTNPush(View view){
        updateText(getResources().getString(R.string.nine));
    }

    public void plusBTNPush(View view){
        updateText(getResources().getString(R.string.plus));
    }

    public void minusBTNPush(View view){
        updateText(getResources().getString(R.string.minus));
    }

    public void multiplyBTNPush(View view){
        updateText(getResources().getString(R.string.multiply));
    }

    public void divideBTNPush(View view){
        updateText(getResources().getString(R.string.divide));
    }

    public void decimalBTNPush(View view){
        updateText(getResources().getString(R.string.decimal));
    }

    public void modBTNPush(View view){
        updateText(getResources().getString(R.string.mod));
    }

    public void parBTNPush(View view){
        int cursorPos = display.getSelectionStart();
        int openPar = 0;
        int closePar = 0;
        int textLen = display.getText().length();

        for(int i=0;i<cursorPos;i++){
            if(display.getText().toString().substring(i, i+1).equals("(")){
                openPar+=1;
            }
            if(display.getText().toString().substring(i, i+1).equals(")")){
                closePar+=1;
            }
        }
        if(openPar == closePar || display.getText().toString().substring(textLen-1, textLen).equals("(")){
            updateText("(");
        }
        else if(closePar < openPar && !display.getText().toString().substring(textLen-1, textLen).equals("(")){
            updateText(")");
        }
        display.setSelection(cursorPos + 1);
    }

    public void plusMinusBTNPush(View view){

    }

    public void equalsBTNPush(View view){
        String userExp = display.getText().toString();

        previousCalculation.setText(userExp);

        userExp = userExp.replaceAll(getResources().getString(R.string.divide), "/");
        userExp = userExp.replaceAll(getResources().getString(R.string.multiply), "*");

        Expression exp = new Expression(userExp);
        String result = String.valueOf(exp.calculate());

        display.setText(result);
        display.setSelection(result.length());

    }

    public void clearBTNPush(View view){
        display.setText("");
        previousCalculation.setText("");
    }

    public void backspaceBTNPush(View view){
        int cursorPos = display.getSelectionStart();
        int textLen = display.getText().length();

        if(cursorPos != 0 && textLen != 0){
            SpannableStringBuilder selection = (SpannableStringBuilder) display.getText();
            selection.replace(cursorPos-1, cursorPos, "");
            display.setText(selection);
            display.setSelection(cursorPos-1);
        }
    }

}