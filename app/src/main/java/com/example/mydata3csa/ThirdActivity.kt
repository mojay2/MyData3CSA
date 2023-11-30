package com.example.mydata3csa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager

class ThirdActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)

        val images: IntArray = intArrayOf(
            R.drawable.stock1,
            R.drawable.stock2,
            R.drawable.stock3,
            R.drawable.stock4,
            R.drawable.stock5,
        )

        val viewPager:ViewPager = findViewById<ViewPager>(R.id.pager) as ViewPager
        val adapter:PagerAdapter = ImagePageAdapter(this, images)
        viewPager.adapter = adapter
    }
}