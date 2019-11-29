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

    private val stops = ArrayList<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ten.setOnClickListener { addStop(10) }
        twenty.setOnClickListener { addStop(20) }
        thirty.setOnClickListener { addStop(30) }
        forty.setOnClickListener { addStop(40) }
        fifty.setOnClickListener { addStop(50) }
        sixty.setOnClickListener { addStop(60) }
        seventy.setOnClickListener { addStop(70) }
        eighty.setOnClickListener { addStop(80) }
        ninety.setOnClickListener { addStop(90) }
        oneHundred.setOnClickListener { addStop(100) }
        clear.setOnClickListener { clear() }
        backup.setOnClickListener { removeStop() }
        payout.setOnClickListener { payout() }
        split.setOnClickListener { split() }
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
        var stopsTotal = 0
        val stopsStringBuilder = StringBuilder()

        stops.forEach {
            stopsTotal += it
            if (!stopsStringBuilder.isEmpty()) {
                stopsStringBuilder.append(" - ")
            }
            stopsStringBuilder.append(it)
        }

        expression.text = stopsStringBuilder.toString()
        result.text = stops.sum().toString()
    }

    private fun payout() {
        val inflater: LayoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.run { inflate(R.layout.payout_popup, null) }
        val popupWindow = PopupWindow(
            view, LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )

        val stopsSum = stops.sum()

        view.findViewById<TextView>(R.id.payoutTotal).text = getString(R.string.pay_full_header, stopsSum)
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

        view.findViewById<TextView>(R.id.popupSplit).setOnClickListener {
            popupWindow.dismiss()
            split()
        }

        view.findViewById<TextView>(R.id.popupClear).setOnClickListener {
            clear()
            popupWindow.dismiss()
        }

        popupWindow.showAtLocation(root_layout, Gravity.CENTER, 0, 0)
    }

    private fun split() {
        val inflater: LayoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.run { inflate(R.layout.split_popup, null) }
        val popupWindow = PopupWindow(
            view, LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )

        val stopsSum = stops.sum()
        val companyPayout = ((stopsSum / 10) / 2) * 10
        val perSharePayout = (stopsSum - companyPayout) / 10

        view.findViewById<TextView>(R.id.payoutTotal).text = getString(R.string.pay_split_header, stopsSum)
        view.findViewById<TextView>(R.id.zeroShares).text = 0.toString()
        view.findViewById<TextView>(R.id.oneShare).text = perSharePayout.toString()
        view.findViewById<TextView>(R.id.twoShares).text = (perSharePayout * 2).toString()
        view.findViewById<TextView>(R.id.threeShares).text = (perSharePayout * 3).toString()
        view.findViewById<TextView>(R.id.fourShares).text = (perSharePayout * 4).toString()
        view.findViewById<TextView>(R.id.fiveShares).text = (perSharePayout * 5).toString()
        view.findViewById<TextView>(R.id.sixShares).text = (perSharePayout * 6).toString()
        view.findViewById<TextView>(R.id.sevenShares).text = (perSharePayout * 7).toString()
        view.findViewById<TextView>(R.id.eightShares).text = (perSharePayout * 8).toString()
        view.findViewById<TextView>(R.id.nineShares).text = (perSharePayout * 9).toString()
        view.findViewById<TextView>(R.id.tenShares).text = (perSharePayout * 10).toString()

        view.findViewById<TextView>(R.id.zeroSharesCompany).text = companyPayout.toString()
        view.findViewById<TextView>(R.id.oneShareCompany).text = (companyPayout + perSharePayout).toString()
        view.findViewById<TextView>(R.id.twoSharesCompany).text = (companyPayout + perSharePayout * 2).toString()
        view.findViewById<TextView>(R.id.threeSharesCompany).text = (companyPayout + perSharePayout * 3).toString()
        view.findViewById<TextView>(R.id.fourSharesCompany).text = (companyPayout + perSharePayout * 4).toString()
        view.findViewById<TextView>(R.id.fiveSharesCompany).text = (companyPayout + perSharePayout * 5).toString()
        view.findViewById<TextView>(R.id.sixSharesCompany).text = (companyPayout + perSharePayout * 6).toString()
        view.findViewById<TextView>(R.id.sevenSharesCompany).text = (companyPayout + perSharePayout * 7).toString()
        view.findViewById<TextView>(R.id.eightSharesCompany).text = (companyPayout + perSharePayout * 8).toString()
        view.findViewById<TextView>(R.id.nineSharesCompany).text = (companyPayout + perSharePayout * 9).toString()
        view.findViewById<TextView>(R.id.tenSharesCompany).text = (companyPayout + perSharePayout * 10).toString()

        view.findViewById<TextView>(R.id.popupBack).setOnClickListener {
            popupWindow.dismiss()
        }

        view.findViewById<TextView>(R.id.popupPayout).setOnClickListener {
            popupWindow.dismiss()
            payout()
        }
        view.findViewById<TextView>(R.id.popupClear).setOnClickListener {
            clear()
            popupWindow.dismiss()
        }

        popupWindow.showAtLocation(root_layout, Gravity.CENTER, 0, 0)
    }
}
