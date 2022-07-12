package com.example.myapp

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import java.lang.Math.pow

class CalculatorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)

        initView()


    }

    private fun initView() {
        val btn16 = findViewById<Button>(R.id.button16)
        val btn2 = findViewById<Button>(R.id.button2)
        val btn3 = findViewById<Button>(R.id.button3)
        val btn4 = findViewById<Button>(R.id.button4)
        val btn5 = findViewById<Button>(R.id.button5)
        val btn6 = findViewById<Button>(R.id.button6)
        val btn7 = findViewById<Button>(R.id.button7)
        val btn8 = findViewById<Button>(R.id.button8)
        val btn9 = findViewById<Button>(R.id.button9)
        val btn10 = findViewById<Button>(R.id.button10)
        val btn11 = findViewById<Button>(R.id.button11)
        val btn12 = findViewById<Button>(R.id.button12)
        val btn13 = findViewById<Button>(R.id.button13)
        val btn14 = findViewById<Button>(R.id.button14)
        val btn15 = findViewById<Button>(R.id.button15)
        val text = findViewById<TextView>(R.id.text_view)

        val input = StringBuilder()
        var a = 0
        var b = 0
        var c = 0
        var check = "0"
        var num = 0
        btn11.setOnClickListener {
            input.append(0)
            text.text = input
            num++
        }
        btn12.setOnClickListener {
            input.append(1)
            text.text = input
            num++
        }
        btn13.setOnClickListener {
            input.append(2)
            text.text = input
            num++
        }
        btn8.setOnClickListener {
            input.append(3)
            text.text = input
            num++
        }
        btn9.setOnClickListener {
            input.append(4)
            text.text = input
            num++
        }
        btn10.setOnClickListener {
            input.append(5)
            text.text = input
            num++
        }
        btn5.setOnClickListener {
            input.append(6)
            text.text = input
            num++
        }
        btn6.setOnClickListener {
            input.append(7)
            text.text = input
            num++
        }
        btn7.setOnClickListener {
            input.append(8)
            text.text = input
            num++
        }
        btn2.setOnClickListener {
            input.append(9)
            text.text = input
            num++
        }
        btn3.setOnClickListener {
            for (i in 0 until num){
                Log.d(TAG, "$input.get(i)")
                a += ((input.get(i).toInt() - 48 )* pow(10.0,((num - 1 - i).toDouble()))).toInt()
            }
            input.clear()
            input.append("+")
            check = "+"
            text.text = input
            input.clear()
            num = 0
        }
        btn14.setOnClickListener {
            for (i in 0 until num){
                Log.d(TAG, "$input.get(i)")
                b += ((input.get(i).toInt() - 48 )* pow(10.0,((num - 1 - i).toDouble()))).toInt()
            }
            c = if(check == "+"){
                a + b
            } else if(check == "x"){
                a * b
            } else if(check == "-"){
                a - b
            } else a / b
            text.text = c.toString()
            input.clear()
            num = 0
        }
        btn15.setOnClickListener {
            for (i in 0 until num){
                Log.d(TAG, "$input.get(i)")
                a += ((input.get(i).toInt() - 48 )* pow(10.0,((num - 1 - i).toDouble()))).toInt()
            }
            input.clear()
            input.append("x")
            check = "x"
            text.text = input
            input.clear()
            num = 0
        }
        btn4.setOnClickListener {
            for (i in 0 until num){
                Log.d(TAG, "$input.get(i)")
                a += ((input.get(i).toInt() - 48 )* pow(10.0,((num - 1 - i).toDouble()))).toInt()
            }
            input.clear()
            input.append("-")
            check = "-"
            text.text = input
            input.clear()
            num = 0
        }
        btn16.setOnClickListener {
            for (i in 0 until num){
                Log.d(TAG, "$input.get(i)")
                a += ((input.get(i).toInt() - 48 )* pow(10.0,((num - 1 - i).toDouble()))).toInt()
            }
            input.clear()
            input.append("÷")
            check = "÷"
            text.text = input
            input.clear()
            num = 0
        }
    }
}