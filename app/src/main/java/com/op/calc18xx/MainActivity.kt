package com.op.calc18xx

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.view.Gravity
import android.content.Context
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.TextView


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
        tvResult.text = stops.sum().toString()
    }

    private fun payout() {
        val inflater:LayoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.payout_popup,null)
        val popupWindow = PopupWindow(view, LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT)

        val stopsSum = stops.sum()

        view.findViewById<TextView>(R.id.payoutTotal).text = stopsSum.toString()
        view.findViewById<TextView>(R.id.twoShares).text = (stopsSum * 2 / 10).toString()
        view.findViewById<TextView>(R.id.threeShares).text = (stopsSum * 3 / 10).toString()
        view.findViewById<TextView>(R.id.fourShares).text = (stopsSum * 4 / 10).toString()
        view.findViewById<TextView>(R.id.fiveShares).text = (stopsSum * 5 / 10).toString()
        view.findViewById<TextView>(R.id.sixShares).text = (stopsSum * 6 / 10).toString()
        view.findViewById<TextView>(R.id.sevenShares).text = (stopsSum * 7 / 10).toString()
        view.findViewById<TextView>(R.id.eightShares).text = (stopsSum * 8 / 10).toString()
        view.findViewById<TextView>(R.id.nineShares).text = (stopsSum * 9 / 10).toString()

        view.findViewById<TextView>(R.id.popupBack).setOnClickListener {
            popupWindow.dismiss()
        }

        view.findViewById<TextView>(R.id.popupClear).setOnClickListener {
            clear()
            popupWindow.dismiss()
        }

        popupWindow.showAtLocation(root_layout, Gravity.CENTER, 0, 0)

    }
}
