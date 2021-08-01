@file:Suppress("DEPRECATION")

package com.dobcalculator

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        btnDatePicker.setOnClickListener { view ->
            clickDataPicker(view)
        }
    }

    @SuppressLint("SetTextI18n")
    fun clickDataPicker(view: View) {

        val c = Calendar.getInstance()
        val year =
            c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->

                val selectedDate = "$dayOfMonth/${monthOfYear + 1}/$year"


                pselectedDate.text = selectedDate

                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

                val theDate = sdf.parse(selectedDate)

                val selectedDateToMinutes = theDate!!.time / 60000
                val selectedDateToHours=selectedDateToMinutes /60
                val seletedDateToSeconds=selectedDateToMinutes*60
                val selectedDays= selectedDateToHours/24
                val selectedDay=theDate.date
                val selectedyear=theDate.year
                val selectedMonth=theDate.month


                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

                val currentDateToMinutes = currentDate!!.time / 60000
                val currrenDateToSeconds = currentDateToMinutes*60
                val currentDateToHours=currentDateToMinutes /60
                val currentDays = currentDateToHours/24
                var currentDay=currentDate.date
                var currentYear =currentDate.year
                var currentMonth =currentDate.month

                val differenceInMinutes = currentDateToMinutes - selectedDateToMinutes
                val differenceInHours=currentDateToHours - selectedDateToHours
                val differenceInSeconds=currrenDateToSeconds - seletedDateToSeconds
                val differencInDays = currentDays-selectedDays

                var differenceInYear = currentYear - selectedyear
                if (currentYear==selectedyear)
                {
                    differenceInYear=0
                }

                if (currentMonth>selectedMonth) {
                    differenceInYear = currentYear - selectedyear
                }
                var months = arrayOf(
                    31, 28, 31, 30, 31, 30, 31,
                    31, 30, 31, 30, 31
                )
                if(currentYear%4==0 &&currentYear%100==0 && currentYear%400==0) {
                     months = arrayOf(
                        31, 29, 31, 30, 31, 30, 31,
                        31, 30, 31, 30, 31
                    )
                }
                if (selectedMonth > currentMonth) {
                    currentYear -= 1;
                    currentMonth += 12;
                }
                if (selectedDay > currentDay) {
                    currentDay += months[selectedMonth - 1]
                    currentMonth -= 1;
                }
                val diffrenceMonths= currentMonth-selectedMonth
                val bday=currentDay - selectedDay


                convertMunites.text = differenceInMinutes.toString()
                convertHour.text=differenceInHours.toString()
                convertSeconds.text=differenceInSeconds.toString()
                convertDay.text =differencInDays.toString()
                convertYears.text= "$differenceInYear Y & $diffrenceMonths M & $bday D"
            },
            year,
            month,
            day
        )

        dpd.datePicker.maxDate = Date().time - 86400000
        dpd.show()
    }
}