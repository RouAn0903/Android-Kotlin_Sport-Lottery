package com.team1.sportlottery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.SeekBar
import java.util.zip.Adler32

class SecActivity : AppCompatActivity() {

    private var progressCar1 =0
    private var progressCar2 =0
    private var progressCar3 =0

    private lateinit var btn_start: Button
    private lateinit var car1: SeekBar
    private lateinit var car2: SeekBar
    private lateinit var car3: SeekBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sec)

        btn_start = findViewById(R.id.btn_start)
        car1 = findViewById(R.id.car1)
        car2 = findViewById(R.id.car2)
        car3 = findViewById(R.id.car3)

        btn_start.setOnClickListener{
            btn_start.isEnabled = false
            progressCar1 = 0
            progressCar2 = 0
            progressCar3 = 0

            car1.progress = 0
            car2.progress = 0
            car3.progress = 0

            runCar1()
            runCar2()
            runCar3()
        }

    }

    private fun runCar1(){

    }

    private fun runCar2(){

    }

    private fun runCar3(){

    }
}