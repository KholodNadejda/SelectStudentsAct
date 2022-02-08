package com.example.selectstudentsact

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.AnimationUtils
import android.view.animation.BounceInterpolator
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import java.util.*
import kotlin.random.Random
import kotlin.random.nextInt

class MainActivity : AppCompatActivity() {

    private var launcher: ActivityResultLauncher<Intent>? = null
    private var data = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        launcher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
                if (result.resultCode == RESULT_OK) {
                    data = result.data?.getStringExtra("EXTRA").toString()
                }
            }
     /*   findViewById<Button>(R.id.tvWho).setOnClickListener {
            launcher?.launch(Intent(this, SelectStudents::class.java))
            ObjectAnimator.ofFloat(it, "rotation")
        }*/
    }

    fun onClickSelect(view: View) {
        launcher?.launch(Intent(this, SelectStudents::class.java))
    }

    fun onClickRandom(view: View) {
      //  val anim = AnimationUtils.loadAnimation(this, R.anim.rotate)
      //  view.startAnimation(anim)
      //  val anim2 = AnimationUtils.loadAnimation(this, R.anim.rotatefrom)
      //  view.startAnimation(anim2)
       // ObjectAnimator.ofFloat(view, "rotation", 0f, 360f).setDuration(500).start()
        val f1 = ObjectAnimator.ofFloat(view, "translationX", 0f, 1100f).apply {
            setDuration(500)
            start()
        }
        val f2 = ObjectAnimator.ofFloat(view, "translationX", -1100f, 0f).apply {
            setDuration(500)
            start()
        }
        AnimatorSet().apply {
            play(f2).after(f1)
            play(f2)
        }.start()
        val sunImageView: ImageView = findViewById<ImageView>(R.id.imgRandom)
        val sunRiseAnimation: Animation = AnimationUtils.loadAnimation(this, R.anim.sun_rise)
        sunImageView.startAnimation(sunRiseAnimation)
        //val pic = ObjectAnimator.ofFloat(view, "rotation", 0f, 360f).setDuration(500)
        //pic.addUpdateListener { animator -> R.id.imgRandom }

        Log.d("MyApp", "btRandom $data")
        var imgRandom = findViewById<ImageView>(R.id.imgRandom)
        var tvWho = findViewById<TextView>(R.id.tvWho)
        if (data.isNotEmpty()) {
            val arr = data.split(" ")
            imgRandom.setImageResource(Random.nextInt(R.drawable.c0..R.drawable.c9))
            tvWho.text = arr[Random.nextInt(arr.size)]
        } else {
            imgRandom.setImageResource(R.drawable.sad)
            tvWho.text = "Список пуст((((("
        }
    }
}