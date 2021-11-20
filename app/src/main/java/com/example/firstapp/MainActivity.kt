package com.example.firstapp

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val BtnDatePicker = findViewById<Button>(R.id.btnDatePicker)
        BtnDatePicker.setOnClickListener {
            clickDatePicker()
        }
    }
    private fun clickDatePicker() {
        val myCalendar = Calendar.getInstance()
        var year = myCalendar.get(Calendar.YEAR)
        var month = myCalendar.get(Calendar.MONTH)
        var day = myCalendar.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this,
            {
                    view, setYear, setMonth, setDayOfMonth ->
                    Toast.makeText(this,"the chosen year is $setYear and the month is $setMonth and the day is $setDayOfMonth",Toast.LENGTH_SHORT).show()
                    val selectedDate = "$setDayOfMonth/${setMonth + 1}/$setYear"
                    val TvSelectedDate = findViewById<TextView>(R.id.tvSelectedDate)
                    TvSelectedDate.text = selectedDate

                    val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                    val theDate = sdf.parse(selectedDate)
                    val selectedDateInMinutes = theDate!!.time / 60000
                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                    val currentDateInMinutes = currentDate!!.time / 60000
                    val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes

                    val TvSelectedDateInMinutes = findViewById<TextView>(R.id.tvSelectedDateInMinutes)
                    TvSelectedDateInMinutes.text = differenceInMinutes.toString()

            },year,month,day)

        dpd.datePicker.maxDate = (Date().time - 8640000)
        dpd.show()
    }
}