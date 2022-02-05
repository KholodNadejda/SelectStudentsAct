package com.example.selectstudentsact

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import kotlin.random.Random
import kotlin.random.nextInt

class MainActivity : AppCompatActivity() {

    private var launcher: ActivityResultLauncher<Intent>? = null
    private  var data = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
                result: ActivityResult ->
            if (result.resultCode == RESULT_OK)
            {
                // data = result.data?.getStringArrayListExtra("EXTRA") as ArrayList<String>
                data = result.data?.getStringExtra("EXTRA").toString()
            }
        }
    }

    fun onClickSelect (view: View) {
        launcher?.launch(Intent(this, SelectStudents::class.java))
    }

    fun onClickRandom (view: View) {
        Log.d("MyApp", "btRandom $data")
        var imgRandom = findViewById<ImageView>(R.id.imgRandom)
        var tvWho = findViewById<TextView>(R.id.tvWho)
        if (data.isNotEmpty()) {
            val arr = data.split(" ")
            //Toast.makeText(applicationContext, checkStudents[Random.nextInt(checkStudents.size)].name, Toast.LENGTH_LONG)
            imgRandom.setImageResource(Random.nextInt(R.drawable.c0 .. R.drawable.c9))
            tvWho.text = arr[Random.nextInt(arr.size)]
        } else {
            //Toast.makeText(applicationContext, "Choose some students", Toast.LENGTH_LONG)
           // Log.d("MyApp", "Список пуст(((((")
            imgRandom.setImageResource(R.drawable.sad)
            tvWho.text = "Список пуст((((("
        }
    }
}