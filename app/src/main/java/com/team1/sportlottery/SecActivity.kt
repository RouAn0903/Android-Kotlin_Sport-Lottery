package com.team1.sportlottery

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class SecActivity : AppCompatActivity() {

    private var progressCar1 =0
    private var progressCar2 =0
    private var progressCar3 =0
    private var progressCar4 =0
    private var progressCar5 =0

    private lateinit var btn_start: Button
    private lateinit var btn_again: Button
    private lateinit var btn_finish: Button
    private lateinit var car1: SeekBar
    private lateinit var car2: SeekBar
    private lateinit var car3: SeekBar
    private lateinit var car4: SeekBar
    private lateinit var car5: SeekBar
    private lateinit  var inform : TextView
    private lateinit  var inform_car: TextView
    private lateinit  var inform_money: TextView
    private lateinit  var inform_total: TextView
    private lateinit  var endMsg: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sec)

        btn_start = findViewById(R.id.btn_start)
        btn_again = findViewById(R.id.btn_again)
        btn_finish = findViewById(R.id.btn_finish)
        car1 = findViewById(R.id.car1)
        car2 = findViewById(R.id.car2)
        car3 = findViewById(R.id.car3)
        car4 = findViewById(R.id.car4)
        car5 = findViewById(R.id.car5)
        inform = findViewById<TextView>(R.id.inform)
        inform_car = findViewById<TextView>(R.id.inform_car)
        inform_money = findViewById<TextView>(R.id.inform_money)
        inform_total = findViewById<TextView>(R.id.inform_total)
        endMsg = findViewById<TextView>(R.id.endMsg)
        btn_start.isEnabled = true
        btn_again.isEnabled = false

        intent?.extras?.let {
            inform.text = it.getString("inform")
            inform_car.text = it.getString("inform_car")
            inform_money.text = it.getString("inform_money")
        }

        btn_start.setOnClickListener{
            btn_start.isEnabled = false
            progressCar1 = 0
            progressCar2 = 0
            progressCar3 = 0
            progressCar4 = 0
            progressCar5 = 0

            car1.progress = 0
            car2.progress = 0
            car3.progress = 0
            car4.progress = 0
            car5.progress = 0


            inform_total.text = ""
            endMsg.text = ""

            runCar1()
            runCar2()
            runCar3()
            runCar4()
            runCar5()


        }


        btn_again.setOnClickListener {
            val debt_car = arrayOf("賽車1號","賽車2號","賽車3號", "賽車4號", "賽車5號" )
            var choice_car =0
            val debt_money = arrayOf("100元" , "500元" , "1000元" )
            var choice_money =0

            AlertDialog.Builder(this)
                .setTitle("選擇下注賽車")
                .setSingleChoiceItems(debt_car, 0){ dialogInterface, i ->
                    choice_car = i
                    inform_car.text = "${debt_car[i]}"

                }
                .setPositiveButton("下一步"){ dialog, which ->
                    AlertDialog.Builder(this)
                        .setTitle("選擇下注獎金")
                        .setSingleChoiceItems(debt_money, 0){ dialogInterface, i ->
                            choice_money = i
                            inform_money.text = "${debt_money[i]}"
                            inform_total.text = ""

                        }
                        .setPositiveButton("確定"){  dialog, which ->

                        }.show()
                }.show()

            btn_again.isEnabled = false
            btn_start.isEnabled = true

        }

        btn_finish.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("結束")
                .setMessage("確定要離開嗎?")
                .setNeutralButton("確定"){ dialog, which ->
                    startActivity(Intent(this, MainActivity::class.java))
                }
                .setPositiveButton("返回"){ dialog, which ->
                }.show()
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
        else if (msg.what == 4)
            car4.progress = progressCar4
        else if (msg.what == 5)
            car5.progress = progressCar5
        //若兔子抵達，則顯示兔子勝利
        if (progressCar1 >= 100 && progressCar2 < 100 && progressCar3 < 100 && progressCar4 < 100 && progressCar5 < 100) {
            showToast("賽車1勝利") //顯示賽車1勝利
            if (inform_car.text == "賽車1號"){
                inform_total.text =  when(inform_money.text) {
                    "100元" -> "1000元"
                    "500元" -> "5000元"
                    else ->    "10000元"
                }
                endMsg.setTextColor(Color.RED)
                endMsg.text = "賽車1勝利\n恭喜獲得10倍獎金:${inform_total.text}"
            }
            else{
                inform_total.text = "0"
                endMsg.setTextColor(Color.BLUE)
                endMsg.text = "賽車1勝利\n您的押注金沒收,要再試手氣嗎?"
            }
            btn_again.isEnabled = true //按鈕可操作

        }
        else if (progressCar2 >= 100 && progressCar1 < 100 && progressCar3 < 100 && progressCar4 < 100 && progressCar5 < 100) {
            showToast("賽車2勝利") //顯示賽車2勝利
            if (inform_car.text == "賽車2號"){
                inform_total.text =  when(inform_money.text) {
                    "100元" -> "1000元"
                    "500元" -> "5000元"
                    else ->    "10000元"
                }
                endMsg.setTextColor(Color.RED)
                endMsg.text = "賽車2勝利\n恭喜獲得10倍獎金:${inform_total.text}"
            }
            else{
                inform_total.text = "0"
                endMsg.setTextColor(Color.BLUE)
                endMsg.text = "賽車2勝利\n您的押注金沒收,要再試手氣嗎?"
            }
            btn_again.isEnabled = true //按鈕可操作

        }
        else if (progressCar3 >= 100 && progressCar1 < 100 && progressCar2 < 100 && progressCar4 < 100 && progressCar5 < 100){
            showToast("賽車3勝利") //顯示賽車3勝利
            if (inform_car.text == "賽車3號"){
                inform_total.text =  when(inform_money.text) {
                    "100元" -> "1000元"
                    "500元" -> "5000元"
                    else ->    "10000元"
                }
                endMsg.setTextColor(Color.RED)
                endMsg.text = "賽車3勝利\n恭喜獲得10倍獎金:${inform_total.text}"
            }
            else{
                inform_total.text = "0"
                endMsg.setTextColor(Color.BLUE)
                endMsg.text = "賽車3勝利\n您的押注金沒收,要再試手氣嗎?"
            }
            btn_again.isEnabled = true //按鈕可操作

        }
        else if (progressCar4 >= 100 && progressCar1 < 100 && progressCar2 < 100 && progressCar3 < 100 && progressCar5 < 100){
            showToast("賽車4勝利") //顯示賽車3勝利
            if (inform_car.text == "賽車4號"){
                inform_total.text =  when(inform_money.text) {
                    "100元" -> "300元"
                    "500元" -> "1000元"
                    else -> "3000元"
                }
                endMsg.setTextColor(Color.RED)
                endMsg.text = "賽車4勝利\n恭喜獲得10倍獎金:${inform_total.text}"
            }
            else{
                inform_total.text = "0"
                endMsg.setTextColor(Color.BLUE)
                endMsg.text = "賽車4勝利\n您的押注金沒收,要再試手氣嗎?"
            }
            btn_again.isEnabled = true //按鈕可操作

        }
        else if (progressCar5 >= 100 && progressCar1 < 100 && progressCar2 < 100 && progressCar3 < 100 && progressCar4 < 100){
            showToast("賽車5勝利") //顯示賽車3勝利
            if (inform_car.text == "賽車5號"){
                inform_total.text =  when(inform_money.text) {
                    "100元" -> "1000元"
                    "500元" -> "5000元"
                    else ->    "10000元"
                }
                endMsg.setTextColor(Color.RED)
                endMsg.text = "賽車5勝利\n恭喜獲得10倍獎金:${inform_total.text}"
            }
            else{
                inform_total.text = "0"
                endMsg.setTextColor(Color.BLUE)
                endMsg.text = "賽車5勝利\n您的押注金沒收,要再試手氣嗎?"
            }
            btn_again.isEnabled = true //按鈕可操作

        }

        true

    }

    private fun runCar1(){
        Thread {
            //兔子有三分之二的機率會偷懶
            val sleepProbability = arrayOf(true, true, false)
            while (progressCar1 < 100 && progressCar2 < 100 && progressCar3 < 100 && progressCar4 < 100 && progressCar5 < 100) {
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
            while (progressCar1 < 100 && progressCar2 < 100 && progressCar3 < 100 && progressCar4 < 100 && progressCar5 < 100) {
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
            while (progressCar1 < 100 && progressCar2 < 100 && progressCar3 < 100 && progressCar4 < 100 && progressCar5 < 100) {
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

    private fun runCar4(){
        Thread {
            //兔子有三分之二的機率會偷懶
            val sleepProbability = arrayOf(true, true, false)
            while (progressCar1 < 100 && progressCar2 < 100 && progressCar3 < 100 && progressCar4 < 100 && progressCar5 < 100) {
                try {
                    Thread.sleep(100) //延遲 0.1 秒更新賽況
                    if (sleepProbability.random())
                        Thread.sleep(300) //兔子偷懶 0.3 秒
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
                progressCar4 += 3 //每次跑三步
                val msg = Message() //建立 Message 物件
                msg.what = 4 //加入編號
                handler.sendMessage(msg) //傳送兔子的賽況訊息
            }
        }.start() //啟動 Thread

    }

    private fun runCar5(){
        Thread {
            //兔子有三分之二的機率會偷懶
            val sleepProbability = arrayOf(true, true, false)
            while (progressCar1 < 100 && progressCar2 < 100 && progressCar3 < 100 && progressCar4 < 100 && progressCar5 < 100) {
                try {
                    Thread.sleep(100) //延遲 0.1 秒更新賽況
                    if (sleepProbability.random())
                        Thread.sleep(300) //兔子偷懶 0.3 秒
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
                progressCar5 += 3 //每次跑三步
                val msg = Message() //建立 Message 物件
                msg.what = 5 //加入編號
                handler.sendMessage(msg) //傳送兔子的賽況訊息
            }
        }.start() //啟動 Thread

    }
}