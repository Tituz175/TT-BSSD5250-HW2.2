package com.example.tt_bssd5250_22

import android.content.res.Resources
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.widget.LinearLayoutCompat

class MainActivity : AppCompatActivity() {
    private lateinit var linearLayout: LinearLayoutCompat


    var scoreNum = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val redlinearLayout = LinearLayoutCompat(this).apply {
            setBackgroundColor(Color.GRAY)
            layoutParams = LinearLayoutCompat.LayoutParams(
                LinearLayoutCompat.LayoutParams.MATCH_PARENT,
                0,
                0.2f
            )
            weightSum = 1.0f
            addView(makeButton("red"))
        }
        val bluelinearLayout = LinearLayoutCompat(this).apply {
            setBackgroundColor(Color.GRAY)
            layoutParams = LinearLayoutCompat.LayoutParams(
                LinearLayoutCompat.LayoutParams.MATCH_PARENT,
                0,
                0.2f
            )
//            weightSum = 1.0f
            addView(makeButton("blue"))
        }


        var score = TextView(this).apply {
            text = scoreNum.toString();
            val metrics: DisplayMetrics = Resources.getSystem().displayMetrics
            val pixels = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 128f, metrics)
            textSize = pixels
            textAlignment = View.TEXT_ALIGNMENT_CENTER
//            setBackgroundColor(Color.GREEN)
            layoutParams = RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
            )
            (layoutParams as RelativeLayout.LayoutParams).addRule(RelativeLayout.CENTER_IN_PARENT)
        }

        val relativeLayout = RelativeLayout(this).apply {
//            setBackgroundColor(Color.RED)
            layoutParams = LinearLayoutCompat.LayoutParams(
                LinearLayoutCompat.LayoutParams.MATCH_PARENT,
                0,
                0.5f
            )
            addView(score)
        }

        // Create a ConstraintLayout in which to add the ImageView
        linearLayout = LinearLayoutCompat(this).apply {
//            setBackgroundColor(Color.LTGRAY)
            orientation = LinearLayoutCompat.VERTICAL
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            weightSum = 1.0f
            // Add the ImageView to the layout.
            addView(redlinearLayout)
            addView(bluelinearLayout)
            addView(relativeLayout)
        }

        // Set the layout as the content view.
        setContentView(linearLayout)
    }

    private fun makeButton(color: String): ImageButton {
        val button = if (color == "red") {
            // Instantiate an ImageView and define its properties
            ImageButton(this).apply {
                setImageResource(R.drawable.red)
                background = null
                setOnClickListener{
                    val parent = it.parent as? LinearLayoutCompat
                    parent?.addView(makeButton("blue"))
                    scoreNum++
                    Log.d("MainActivity", "button clicked "+ scoreNum.toString())
//                    (it.parent as LinearLayoutCompat).removeView(it)
                }
                // set the ImageView bounds to match the Drawable's dimensions
                adjustViewBounds = true
                layoutParams = LinearLayoutCompat.LayoutParams(
                    0,
                    LinearLayoutCompat.LayoutParams.WRAP_CONTENT,
                    0.1f
                )
            }
        } else {
            ImageButton(this).apply {
                setImageResource(R.drawable.blue)
                background = null
                setOnClickListener {
                    val parent = it.parent as? LinearLayoutCompat
                    parent?.removeView(it)
                    scoreNum--
                    Log.d("MainActivity", "button clicked "+ scoreNum.toString())
//                    (it.parent as LinearLayoutCompat).removeView(it)
                }
                // set the ImageView bounds to match the Drawable's dimensions
                adjustViewBounds = true
                layoutParams = LinearLayoutCompat.LayoutParams(
                    0,
                    LinearLayoutCompat.LayoutParams.WRAP_CONTENT,
                    0.1f
                )
            }
        }
        return button
    }
}