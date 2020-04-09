package com.example.calculator;

import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    final static String TEXT_VIEW_KEY = "TEXTVIEW_TEXT";
    private TextView textView;
    private String textExpression;
    private Button mAddictionButton;
    private Button mSubtractButton;
    private Button mMultiplyButton;
    private Button mDivisionButton;
    private Button mDotButton;
    private Button mEqualButton;
    private Calculator calculator = new Calculator();

    private class ListenerEqual implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            String str = calculator.calc(textExpression);
            textExpression = str;
            textView.setText(textExpression);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.text_view);
        textExpression = textView.getText().toString();
        mAddictionButton = findViewById(R.id.add);
        mSubtractButton = findViewById(R.id.subtract);
        mMultiplyButton = findViewById(R.id.multiply);
        mDivisionButton = findViewById(R.id.division);
        mDotButton = findViewById(R.id.dot);
        mEqualButton = findViewById(R.id.equality);

        // Задание листнера через реализованный интерфейс
        mMultiplyButton.setOnClickListener(this);
        mDivisionButton.setOnClickListener(this);
        mDotButton.setOnClickListener(this);

        // Устанавливаем листнер с вложенной реализацией
        mAddictionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( !(textExpression.endsWith("+") || textExpression.endsWith("*") ||
                        textExpression.endsWith("/") || textExpression.endsWith("-") || textExpression.endsWith(".")) ) {
                    textExpression = textExpression + "+";
                    textView.setText(textExpression);
                }
            }
        });

        /// Просто задание листнера созданого ранее
        mSubtractButton.setOnClickListener(subListener);
        mEqualButton.setOnClickListener(new ListenerEqual());
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString(TEXT_VIEW_KEY,textExpression);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        textExpression = savedInstanceState.getString(TEXT_VIEW_KEY);
        textView.setText(textExpression);
    }

    /// Создание листнера для того чтобы задать его позже
    View.OnClickListener subListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if( !(textExpression.endsWith("+") || textExpression.endsWith("*") ||
                    textExpression.endsWith("/") || textExpression.endsWith("-") || textExpression.endsWith(".")) ) {
                textExpression = textExpression + "-";
                textView.setText(textExpression);
            }
        }
    };

    // Реализация метода onClicked в xml
    public void numericButtonClicked(View view){
        // получаем тэг нажатой кнопки и составляем строку выражения
        String numericStr = (String) view.getTag();
        if(textView.getText().equals("0") && numericStr.equals("0") && !textExpression.contains(".")) {
            textExpression = "0";
        }else {
            textExpression = textExpression + numericStr;
            textView.setText(textExpression);
        }
    }

    ////Переопределение метода при реализации интерфейса OnClickListener
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.division:
                if( !(textExpression.endsWith("+") || textExpression.endsWith("*") ||
                        textExpression.endsWith("/") || textExpression.endsWith("-") || textExpression.endsWith(".")) ) {
                    textExpression = textExpression + "/";
                    textView.setText(textExpression);
                }
                break;
            case R.id.multiply:
                if( !(textExpression.endsWith("+") || textExpression.endsWith("*") ||
                        textExpression.endsWith("/") || textExpression.endsWith("-") || textExpression.endsWith(".")) ) {
                    textExpression = textExpression + "*";
                    textView.setText(textExpression);
                }
                break;
            case R.id.dot:
                dotClicked();
                break;
        }
    }

    private void dotClicked(){
        if( !(textExpression.endsWith("+") || textExpression.endsWith("*") ||
                textExpression.endsWith("/") || textExpression.endsWith("-") || textExpression.endsWith(".")) ) {
            if(!textExpression.contains(".")) {
                textExpression = textExpression + ".";
                textView.setText(textExpression);
            }
        }
    }
}
