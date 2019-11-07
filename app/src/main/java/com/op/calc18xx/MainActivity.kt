package com.op.calc18xx

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.view.Gravity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.PopupWindow


class MainActivity : AppCompatActivity() {

    val stops = ArrayList<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvTen.setOnClickListener { addStop(10) }
        tvTwenty.setOnClickListener { addStop(20) }
        tvThirty.setOnClickListener { addStop(30) }
        tvForty.setOnClickListener { addStop(40) }
        tvFifty.setOnClickListener { addStop(50) }
        tvSixty.setOnClickListener { addStop(60) }
        tvSeventy.setOnClickListener { addStop(70) }
        tvEighty.setOnClickListener { addStop(80) }
        tvNinety.setOnClickListener { addStop(90) }
        tvOneHundred.setOnClickListener { addStop(100) }

        tvClear.setOnClickListener { clear() }
        tvBackup.setOnClickListener { removeStop() }

        tvPayout.setOnClickListener { payout() }

    }

    private fun addStop(stopValue: Int) {
        stops.add(stopValue)
        updateDisplay()
    }

    private fun clear() {
        stops.clear()
        updateDisplay()
    }

    private fun removeStop() {
        if (!stops.isEmpty()) {
            stops.removeAt(stops.lastIndex)
            updateDisplay()
        }
    }

    private fun updateDisplay() {
        print("updateDisplay")
        var stopsTotal = 0
        var stopsStringBuilder = StringBuilder()

        stops.forEach {
            stopsTotal += it
            if (!stopsStringBuilder.isEmpty()) {
                stopsStringBuilder.append(" - ")
            }
            stopsStringBuilder.append(it)
        }

        tvExpression.text = stopsStringBuilder.toString()
        tvResult.text = stopsTotal.toString()
    }

    private fun payout() {
        val inflater:LayoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.payout_popup,null)
        val popupWindow = PopupWindow(view, LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT)
        popupWindow.showAtLocation(root_layout, Gravity.CENTER, 0, 0)
    }
}
