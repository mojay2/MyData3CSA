package com.example.mydata3csa

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private val sharedPrefFile = "prefFile"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val inputId = findViewById<EditText>(R.id.etId)
        val inputName = findViewById<EditText>(R.id.etName)
        val outputId = findViewById<TextView>(R.id.tvYourIdOutput)
        val outputName = findViewById<TextView>(R.id.tvYourNameOutput)
        val btnSave = findViewById<Button>(R.id.btnSave)
        val btnView = findViewById<Button>(R.id.btnView)
        val btnClear = findViewById<Button>(R.id.btnClear)
        val sharedPreferences: SharedPreferences = this.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)

        btnSave.setOnClickListener{
            val id:Int = Integer.parseInt(inputId.text.toString())
            val name:String = inputName.text.toString()
            val editor:SharedPreferences.Editor = sharedPreferences.edit()

            editor.putInt("id_key", id)
            editor.putString("name_key", name)
            editor.apply()
            editor.commit()
        }

        btnView.setOnClickListener {
            val sharedIdValue = sharedPreferences.getInt("id_key", 0)
            val sharedNameValue = sharedPreferences.getString("name_key", "defaultname")

            if (sharedIdValue == 0 && sharedNameValue == "defaultname") {
                outputName.text = "default name: $sharedNameValue"
                outputId.text = "default id: $sharedIdValue"
            } else {
                outputId.text = sharedIdValue.toString()
                outputName.text = sharedNameValue.toString()
            }
        }

        btnClear.setOnClickListener{
            val editor = sharedPreferences.edit()
            editor.clear()
            editor.apply()
            outputId.setText("".toString())
            outputName.setText("".toString())
        }

    }
}