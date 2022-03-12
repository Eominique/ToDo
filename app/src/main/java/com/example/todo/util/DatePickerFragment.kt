package com.example.todo.util

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Build
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import java.text.DateFormat
import java.time.Month
import java.util.*

class DatePickerFragment(
    private val deadlineListener: (String) -> Unit
) : DialogFragment(), DatePickerDialog.OnDateSetListener {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calendar = Calendar.getInstance()
        val year = calendar[Calendar.YEAR]
        val month = calendar[Calendar.MONTH]
        val day = calendar[Calendar.DAY_OF_MONTH]

        val datePickerDialog = DatePickerDialog(requireContext(), this, year, month, day)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            datePickerDialog.datePicker.minDate = System.currentTimeMillis() - 1000
        }
        return datePickerDialog
    }

    override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {
        val calendar = Calendar.getInstance()
        calendar[Calendar.YEAR] = p1
        calendar[Calendar.MONTH] = p2
        calendar[Calendar.DAY_OF_MONTH] = p3

        val dateString = DateFormat.getDateInstance(
            DateFormat.FULL
        ).format(calendar.time)
        deadlineListener(dateString)
    }


}