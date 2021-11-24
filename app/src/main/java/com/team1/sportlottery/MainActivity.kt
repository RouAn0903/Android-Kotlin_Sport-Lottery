package com.team1.sportlottery

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val name = findViewById<EditText>(R.id.name)
        val age = findViewById<EditText>(R.id.age)
        val money = findViewById<Spinner>(R.id.money)
        val enter = findViewById<Button>(R.id.enter)

        enter.setOnClickListener{

            if(name.length() < 1 && age.length() < 1){
                name.hint = "請輸入姓名"
                age.hint ="請輸入年齡"
                return@setOnClickListener

            }
            else if(name.length() < 1){
                name.hint = "請輸入姓名"
                return@setOnClickListener
            }
            else if(age.length() < 1) {
                age.hint = "請輸入年齡"
                return@setOnClickListener
            }
            else if( age.text.toString().toInt() < 18){
                Toast.makeText(this, "未滿18歲無法進入", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            else{
                startActivity(Intent(this, SecActivity::class.java))
            }
        }
    }
}

