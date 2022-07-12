package com.example.myapp;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class SecondActivity extends AppCompatActivity {

    private Button mButton1, mButton2, mButton3, mButton4,
            mButton5, mButton6, mButton7, mButton8, mButton9,
            mButton10, mButton11, mButton12, mButton13, mButton14,
            mButton15, mButton16, mButton17, mButton18, mButton19, mButton20;
    private StringBuilder input;
    private TextView mTextView;
    private TextView mToLastPage;
    private int frequency;
    private boolean special = true;
    private boolean hasFirstNum = false;
    private boolean isDecimal = false;
    private boolean hasSymbol = false;
    private int whereDecimal;
    private double firstNum;
    private double secondNum;
    private String symbol;
    private TextView mCalendar;
    private TextView mRecord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        //helper帮助类, 加载iconfonts的必要操作
        Typeface iconFont = FontManager.getTypeface(getApplicationContext(), FontManager.ICONFONTS);
        FontManager.markAsIconContainer(findViewById(R.id.icons_container), iconFont);

        initView();
        initInput();
        initEvent();

    }

    private void initEvent() {
        bindNormalClickEvent(mButton1, "9");
        bindNormalClickEvent(mButton4, "6");
        bindNormalClickEvent(mButton5, "7");
        bindNormalClickEvent(mButton7, "3");
        bindNormalClickEvent(mButton6, "8");
        bindNormalClickEvent(mButton8, "4");
        bindNormalClickEvent(mButton9, "5");
        bindNormalClickEvent(mButton10, "0");
        bindNormalClickEvent(mButton11, "1");
        bindNormalClickEvent(mButton12, "2");
        bindSpecialClickEvent(mButton19, ".");
        bindNormalClickEvent(mButton20, "0");
        bindNormalClickEvent(mButton17, "00");
        bindACClickEvent(mButton16);
        bindCalClickEvent(mButton2, "+");
        bindCalClickEvent(mButton3, "-");
        bindCalClickEvent(mButton14, "x");
        bindCalClickEvent(mButton15, "÷");
        toLastPage(mToLastPage);
        onTipping(mCalendar, this);
        onTipping(mRecord, this);
        bindGetResultEvent(mButton13);
    }

    private void initInput() {
        input = new StringBuilder();
        frequency = 0;
    }

    private void initView() {
        mButton1 = findViewById(R.id.button2);
        mButton2 = findViewById(R.id.button3);
        mButton3 = findViewById(R.id.button4);
        mButton4 = findViewById(R.id.button5);
        mButton5 = findViewById(R.id.button6);
        mButton6 = findViewById(R.id.button7);
        mButton7 = findViewById(R.id.button8);
        mButton8 = findViewById(R.id.button9);
        mButton9 = findViewById(R.id.button10);
        mButton10 = findViewById(R.id.button11);
        mButton11 = findViewById(R.id.button12);
        mButton12 = findViewById(R.id.button13);
        mButton13 = findViewById(R.id.button14);
        mButton14 = findViewById(R.id.button15);
        mButton15 = findViewById(R.id.button16);
        mButton16 = findViewById(R.id.button17);
        mButton17 = findViewById(R.id.button18);
        mButton18 = findViewById(R.id.button19);
        mButton19 = findViewById(R.id.button20);
        mButton20 = findViewById(R.id.button21);
        mToLastPage = findViewById(R.id.to_last_page);
        mCalendar = findViewById(R.id.calendar);
        mRecord = findViewById(R.id.record);
        mTextView = findViewById(R.id.text_view);
    }

    public void bindNormalClickEvent(Button btn, String num) {
        btn.setOnClickListener(view -> {
            input.append(num);
            mTextView.setText(input);
            frequency++;
        });
    }

    public void bindCalClickEvent(Button btn, String num) {
        btn.setOnClickListener(view -> {
            symbol = num;
            for (int i = 0; i < frequency; i++) {
                if (isDecimal && ((int) input.charAt(i) - 46) == 0) {
                    whereDecimal = frequency - i - 1;
                    break;
                }
            }
            int temp = frequency;
            for (int i = 0; i < temp; i++) {
                if (isDecimal && ((int) input.charAt(i) - 46) == 0) {
                    frequency++;
                    continue;
                }
                firstNum += ((int) input.charAt(i) - 48) * Math.pow(10, frequency - 1 - i);
            }
            if (isDecimal) whereDecimal++;
            firstNum /= Math.pow(10, whereDecimal);
            hasSymbol = true;
            if (input.length() != 0) {
                input.delete(0, temp);
                input.append(num);
                mTextView.setText(input);
                frequency = 0;
                isDecimal = false;
                whereDecimal = 0;
                special = true;
            }
            Log.d(TAG, "bindCalClickEvent: Fis" + firstNum);
        });

    }

    public void bindSpecialClickEvent(Button btn, String num) {
        btn.setOnClickListener(view -> {
            if (special) {
                input.append(num);
                mTextView.setText(input);
                frequency++;
                special = false;
                isDecimal = true;
            }
        });
    }

    public void bindACClickEvent(Button btn) {
        btn.setOnClickListener(view -> {
            input.setLength(0);
            firstNum = 0;
            secondNum = 0;
            mTextView.setText(input);
            special = true;
            frequency = 0;
            isDecimal = false;
            hasFirstNum = false;
            whereDecimal = 0;
        });
    }

    public void toLastPage(View view) {
        TextView textView = (TextView) view;
        textView.setOnClickListener(view1 -> finish());
    }

    public void onTipping(View view, Context context) {
        view.setOnClickListener(view1 -> Toast.makeText(context, "功能暂未开放", Toast.LENGTH_SHORT).show());
    }

    public void bindGetResultEvent(Button button) {
        button.setOnClickListener(view -> {
            if (hasSymbol) {
                double result;
                input.delete(0, 1);
                for (int i = 0; i < frequency; i++) {
                    if (isDecimal && ((int) input.charAt(i) - 46) == 0) {
                        whereDecimal = frequency - i - 1;
                        break;
                    }
                }
                int temp2 = frequency;
                for (int i = 0; i < temp2; i++) {
                    if (isDecimal && ((int) input.charAt(i) - 46) == 0) {
                        frequency++;
                        continue;
                    }
                    secondNum += ((int) input.charAt(i) - 48) * Math.pow(10, frequency - 1 - i);
                }
                if (isDecimal) whereDecimal++;
                secondNum /= Math.pow(10, whereDecimal);

                if (Objects.equals(symbol, "+")) {
                    result = firstNum + secondNum;
                } else if (Objects.equals(symbol, "x")) {
                    result = firstNum * secondNum;
                } else if (Objects.equals(symbol, "÷")) {
                    result = firstNum / secondNum;
                } else {
                    result = firstNum - secondNum;
                }
                input.setLength(0);
//                input.delete(0, temp2);
                input.append(result);
                mTextView.setText(input);
                Log.d(TAG, "bindCalClickEvent: Sis" + secondNum);
            }
        });
    }
}