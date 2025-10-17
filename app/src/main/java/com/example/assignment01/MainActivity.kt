package com.example.assignment01

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etHours = findViewById<EditText>(R.id.etHours)
        val etRate = findViewById<EditText>(R.id.etRate)
        val etTax = findViewById<EditText>(R.id.etTax)
        val btnCalculate = findViewById<Button>(R.id.btnCalculate)
        val btnAbout = findViewById<Button>(R.id.btnAbout)
        val tvResult = findViewById<TextView>(R.id.tvResult)

        btnCalculate.setOnClickListener {
            val hours = etHours.text.toString().toDoubleOrNull() ?: 0.0
            val rate = etRate.text.toString().toDoubleOrNull() ?: 0.0
            val taxRate = etTax.text.toString().toDoubleOrNull() ?: 0.0

            val pay: Double
            val overtimePay: Double

            if (hours <= 40) {
                pay = hours * rate
                overtimePay = 0.0
            } else {
                pay = 40 * rate
                overtimePay = (hours - 40) * rate * 1.5
            }

            val totalPay = pay + overtimePay
            val tax = pay * taxRate

            val result = """
                Pay: $${"%.2f".format(pay)}
                Overtime Pay: $${"%.2f".format(overtimePay)}
                Total Pay: $${"%.2f".format(totalPay)}
                Tax: $${"%.2f".format(tax)}
            """.trimIndent()

            tvResult.text = result
        }

        btnAbout.setOnClickListener {
            val intent = Intent(this, AboutActivity::class.java)
            startActivity(intent)
        }
    }
}
