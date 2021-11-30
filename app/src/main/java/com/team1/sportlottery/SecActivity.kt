package com.team1.sportlottery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.widget.Button
import android.widget.SeekBar
import android.widget.Toast
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

    private fun showToast(msg: String) =
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()

    //建立 Handler 物件接收訊息
    private val handler = Handler(Looper.getMainLooper()) { msg ->
        //判斷編號，並更新兔子的進度
        if (msg.what == 1)
            car1.progress = progressCar1
        else if (msg.what == 2)
            car2.progress = progressCar2
        else if (msg.what == 3)
            car3.progress = progressCar3
        //若兔子抵達，則顯示兔子勝利
        if (progressCar1 >= 100 && progressCar2 < 100 && progressCar3 < 100) {
            showToast("Car1勝利") //顯示Car1勝利
            btn_start.isEnabled = true //按鈕可操作
        }
        else if (progressCar2 >= 100 && progressCar1 < 100 && progressCar3 < 100) {
            showToast("Car2勝利") //顯示Car2勝利
            btn_start.isEnabled = true //按鈕可操作
        }
        else if (progressCar3 >= 100 && progressCar1 < 100 && progressCar2 < 100){
            showToast("Car3勝利") //顯示Car3勝利
            btn_start.isEnabled = true //按鈕可操作
        }
        true
    }

    private fun runCar1(){
        Thread {
            //兔子有三分之二的機率會偷懶
            val sleepProbability = arrayOf(true, true, false)
            while (progressCar1 < 100 && progressCar2 < 100 && progressCar3 < 100) {
                try {
                    Thread.sleep(100) //延遲 0.1 秒更新賽況
                    if (sleepProbability.random())
                        Thread.sleep(300) //兔子偷懶 0.3 秒
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
                progressCar1 += 3 //每次跑三步
                val msg = Message() //建立 Message 物件
                msg.what = 1 //加入編號
                handler.sendMessage(msg) //傳送兔子的賽況訊息
            }
        }.start() //啟動 Thread

    }

    private fun runCar2(){
        Thread {
            //兔子有三分之二的機率會偷懶
            val sleepProbability = arrayOf(true, true, false)
            while (progressCar1 < 100 && progressCar2 < 100 && progressCar3 < 100) {
                try {
                    Thread.sleep(100) //延遲 0.1 秒更新賽況
                    if (sleepProbability.random())
                        Thread.sleep(300) //兔子偷懶 0.3 秒
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
                progressCar2 += 3 //每次跑三步
                val msg = Message() //建立 Message 物件
                msg.what = 2 //加入編號
                handler.sendMessage(msg) //傳送兔子的賽況訊息
            }
        }.start() //啟動 Thread

    }

    private fun runCar3(){
        Thread {
            //兔子有三分之二的機率會偷懶
            val sleepProbability = arrayOf(true, true, false)
            while (progressCar1 < 100 && progressCar2 < 100 && progressCar3 < 100) {
                try {
                    Thread.sleep(100) //延遲 0.1 秒更新賽況
                    if (sleepProbability.random())
                        Thread.sleep(300) //兔子偷懶 0.3 秒
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
                progressCar3 += 3 //每次跑三步
                val msg = Message() //建立 Message 物件
                msg.what = 3 //加入編號
                handler.sendMessage(msg) //傳送兔子的賽況訊息
            }
        }.start() //啟動 Thread
    }
}