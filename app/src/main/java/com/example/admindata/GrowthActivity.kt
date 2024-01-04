package com.example.admindata

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.admindata.databinding.ActivityGrowthBinding
import com.example.admindata.util.MyPreferences
import com.example.admindata.viewModel.M1ViewModel2
import com.example.ott.util.moveActivity
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class GrowthActivity : AppCompatActivity() {
    lateinit var binding: ActivityGrowthBinding
    lateinit var m1ViewModel2:M1ViewModel2

    lateinit var context: Context
    lateinit var myPreferences: MyPreferences
    val instaId = "";

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        binding=ActivityGrowthBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        // Initialize myPreferences here
        context = this
        myPreferences = MyPreferences(context)

        val instaId = myPreferences.getString("InstaId", "")
        binding.userId.text="User I'd : ${instaId}"

        initViews()
        hitGrowthApi()
        observeUserList()
        binding.backBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()

        }
    }

    private fun hitGrowthApi() {
        val instaId = myPreferences.getString("InstaId", "")
        val baseUrl = "https://www.pca-app.somee.com/Assistant/More/UserId/"
        val endUrl = instaId

        m1ViewModel2.hitUserGrowthApi("$baseUrl$endUrl")
    }

    private fun initViews() {
        m1ViewModel2 = ViewModelProvider(this).get(M1ViewModel2::class.java)
    }



    @RequiresApi(Build.VERSION_CODES.O)
    private fun observeUserList() {
        m1ViewModel2.UsersGrowthResp.observe(this, Observer { UsersGrowthResp ->
       if (UsersGrowthResp.isSuccess){
               binding.cFollowers.text="Current Followers : ${UsersGrowthResp.result[0].currentStatus}"
               binding.tGrowth.text="Total Growth : ${UsersGrowthResp.result[0].totalGrowth}"
               binding.gIndex.text="Growth Index : ${UsersGrowthResp.result[0].growthIndex}"
           // Parse the createdAt value into a LocalDateTime
           val formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss")
           val createdAtDateTime = LocalDateTime.parse(UsersGrowthResp.result[0].fromDate, formatter)

           // Format date and time
           val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
           val timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss")

           // Set the formatted values in the TextViews
           binding.fDate.text = "From Date : ${createdAtDateTime.format(dateFormatter)}"
      /*     holder.timeTextView.text = createdAtDateTime.format(timeFormatter)*/

              // binding.gIndex.text="Your Current Followers : ${UsersGrowthResp.result[3].growthIndex}"
       }
        })
    }



}