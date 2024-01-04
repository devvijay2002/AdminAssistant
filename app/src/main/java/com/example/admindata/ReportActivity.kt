package com.example.admindata

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.MonthYearPickerDialog
import com.example.admindata.databinding.ActivityReportBinding
import com.example.admindata.util.MyPreferences
import com.example.admindata.viewModel.M1ViewModel2
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class ReportActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener {
    lateinit var binding: ActivityReportBinding

    private var months = ""
    private var years = ""
    lateinit var m1ViewModel2: M1ViewModel2
    lateinit var reportAdapter: ReportAdapter

    lateinit var context: Context
    lateinit var myPreferences: MyPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityReportBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        init()
        m1ViewModel2 = ViewModelProvider(this).get(M1ViewModel2::class.java)
        initRecyclerView()
        binding.search.setOnClickListener {
            ApiCall()
        }
    }

    private fun init() {
        context = this
        myPreferences = MyPreferences(context)
        months = "01"
        years = "2024"
        binding.btnBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finishAffinity()
        }
/*        binding.month.text = "Jan"
        binding.month.setCompoundDrawablesWithIntrinsicBounds(R.drawable.cal, 0, 0, 0)
        binding.month.setOnClickListener {
            showMonthYearPickerDialog()
        }*/


        binding.year.text = "Jan"+" , "+"2024"
        binding.year.setCompoundDrawablesWithIntrinsicBounds(R.drawable.cal, 0,0 , 0)
        binding.year.setOnClickListener {
            showMonthYearPickerDialog()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun ApiCall() {
        val instaId = myPreferences.getString("InstaId", "")
        val baseUrl = "https://www.pca-app.somee.com/Assistant/More/Report/UserId/"
        m1ViewModel2.hitReportList(baseUrl + instaId, months, years)
        m1ViewModel2.ReportResp.observe(this) { response ->
            if (response.responseCode == 200) {
                if (response.isSuccess) {
                    if (response.result.isEmpty()) {
                        binding.datanotfound.visibility = View.VISIBLE
                        binding.lldatestatus.visibility = View.GONE
                        binding.recyclerView.visibility = View.GONE
                    } else {
                        reportAdapter.updateData(response.result)
                        reportAdapter.notifyDataSetChanged()
                        binding.lldatestatus.visibility = View.VISIBLE
                        binding.datanotfound.visibility = View.GONE
                        binding.recyclerView.visibility = View.VISIBLE
                    }
                }
            } else {
                binding.datanotfound.visibility = View.VISIBLE
                binding.lldatestatus.visibility = View.GONE
                binding.recyclerView.visibility = View.GONE
            }
        }
    }

    private fun initRecyclerView() {
        reportAdapter = ReportAdapter(this, emptyList())
        binding.recyclerView.adapter = reportAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun showMonthYearPickerDialog() {
        val calendar = Calendar.getInstance()
        val dialog = MonthYearPickerDialog()
        dialog.setListener(this)
        dialog.show(supportFragmentManager, "MonthYearPickerDialog")
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onDateSet(p0: DatePicker?, year: Int, monthOfYear: Int, dayOfMonth: Int) {
        val selectedMonth = String.format("%02d", monthOfYear) // Format month as "01", "02", ..., "12"
        val selectedYear = year.toString()

        // Update UI with the selected month and year
       // binding.month.text = getMonthName(selectedMonth.toInt())
        // Save the selected month and year to your variables if needed
        months = selectedMonth
        years = selectedYear;
        binding.year.text = getMonthName(selectedMonth.toInt()) + " , "+years;
        // Perform API call with the selected date
        ApiCall()
    }

    private fun getMonthName(month: Int): String {
        val monthNames = arrayOf(
            "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
        )
        return monthNames[month-1] // Adjusting for 0-based index
    }
}
