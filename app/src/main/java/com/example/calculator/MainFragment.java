package com.example.calculator;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class MainFragment extends Fragment implements View.OnClickListener{

    final static String TEXT_VIEW_KEY = "TEXT_VIEW_TEXT";
    private TextView textView;
    private String textExpression;
    private Button mAddictionButton;
    private Button mSubtractButton;
    private Button mMultiplyButton;
    private Button mDivisionButton;
    private Button mDotButton;
    private Button mEqualButton;
    private Button mZeroButton;
    private Button mOneButton;
    private Button mTwoButton;
    private Button mThreeButton;
    private Button mFourButton;
    private Button mFiveButton;
    private Button mSixButton;
    private Button mSevenButton;
    private Button mEightButton;
    private Button mNineButton;
    private Calculator calculator = new Calculator();

    private class ListenerEqual implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            String str = calculator.calc(textExpression);
            textExpression = str;
            textView.setText(textExpression);
        }
    }

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        textView = view.findViewById(R.id.text_view);
        if (savedInstanceState != null){
            textExpression = savedInstanceState.getString(TEXT_VIEW_KEY);
            textView.setText(textExpression);
        }else {
            textExpression = textView.getText().toString();
        }
        mAddictionButton = view.findViewById(R.id.add);
        mSubtractButton = view.findViewById(R.id.subtract);
        mMultiplyButton = view.findViewById(R.id.multiply);
        mDivisionButton = view.findViewById(R.id.division);
        mDotButton = view.findViewById(R.id.dot);
        mEqualButton = view.findViewById(R.id.equality);
        mZeroButton = view.findViewWithTag("0");
        mOneButton = view.findViewWithTag("1");
        mTwoButton = view.findViewWithTag("2");
        mThreeButton = view.findViewWithTag("3");
        mFourButton = view.findViewWithTag("4");
        mFiveButton = view.findViewWithTag("5");
        mSixButton = view.findViewWithTag("6");
        mSevenButton = view.findViewWithTag("7");
        mEightButton = view.findViewWithTag("8");
        mNineButton = view.findViewWithTag("9");

        // Задание листнера через реализованный интерфейс
        mMultiplyButton.setOnClickListener(this);
        mDivisionButton.setOnClickListener(this);
        mDotButton.setOnClickListener(this);
        mZeroButton.setOnClickListener(this);
        mOneButton.setOnClickListener(this);
        mTwoButton.setOnClickListener(this);
        mThreeButton.setOnClickListener(this);
        mFourButton.setOnClickListener(this);
        mFiveButton.setOnClickListener(this);
        mSixButton.setOnClickListener(this);
        mSevenButton.setOnClickListener(this);
        mEightButton.setOnClickListener(this);
        mNineButton.setOnClickListener(this);

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
        mEqualButton.setOnClickListener(new MainFragment.ListenerEqual());
        return view;
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

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString(TEXT_VIEW_KEY,textExpression);
        super.onSaveInstanceState(outState);
    }

    ////Переопределение метода при реализации интерфейса OnClickListener
    @Override
    public void onClick(View v) {
        if(v.getTag() != null){
            String numericStr = (String) v.getTag();
            if(textView.getText().equals("0") && numericStr.equals("0") && !textExpression.contains(".")) {
                textExpression = "0";
            }else {
                textExpression = textExpression + numericStr;
                textView.setText(textExpression);
            }
        }else {
            switch (v.getId()) {
                case R.id.division:
                    if (!(textExpression.endsWith("+") || textExpression.endsWith("*") ||
                            textExpression.endsWith("/") || textExpression.endsWith("-") || textExpression.endsWith("."))) {
                        textExpression = textExpression + "/";
                        textView.setText(textExpression);
                    }
                    break;
                case R.id.multiply:
                    if (!(textExpression.endsWith("+") || textExpression.endsWith("*") ||
                            textExpression.endsWith("/") || textExpression.endsWith("-") || textExpression.endsWith("."))) {
                        textExpression = textExpression + "*";
                        textView.setText(textExpression);
                    }
                    break;
                case R.id.dot:
                    dotClicked();
                    break;
            }
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
