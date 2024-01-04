package com.example

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.NumberPicker
import androidx.fragment.app.DialogFragment
import com.example.admindata.R
import com.example.admindata.ReportActivity
import java.util.Calendar

class MonthYearPickerDialog : DialogFragment() {
    private var listener: OnDateSetListener? = null

    fun setListener(listener: ReportActivity) {
        this.listener = listener
    }

    @SuppressLint("MissingInflateParams")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder: AlertDialog.Builder = AlertDialog.Builder(requireActivity())

        // Get the layout inflater
        val inflater: LayoutInflater = requireActivity().layoutInflater
        val cal: Calendar = Calendar.getInstance()
        val dialog: View = inflater.inflate(R.layout.date_picker_dialog, null)

        val monthPicker = dialog.findViewById<NumberPicker>(R.id.picker_month)
        val yearPicker = dialog.findViewById<NumberPicker>(R.id.picker_year)

        monthPicker.minValue = 1
        monthPicker.maxValue = 12
        monthPicker.value = cal.get(Calendar.MONTH)

       // val year: Int = cal.get(Calendar.YEAR)
        val year: Int =2022
        yearPicker.minValue = year
        yearPicker.maxValue = MAX_YEAR
        yearPicker.value = year

        builder.setView(dialog)
            .setPositiveButton(R.string.ok,
                DialogInterface.OnClickListener { _, _ ->
                    listener?.onDateSet(
                        null,
                        yearPicker.value,
                        monthPicker.value,
                        1
                    )
                })
            .setNegativeButton(R.string.cancel) { dialog, _ ->
                dialog.cancel()
            }

        return builder.create()
    }

    companion object {
        private const val MAX_YEAR = 2099
    }
}
