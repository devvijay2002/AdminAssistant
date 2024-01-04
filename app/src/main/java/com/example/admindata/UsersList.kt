package com.example.admindata

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.admindata.databinding.ActivityUsersListBinding
import com.example.admindata.util.MyPreferences
import com.example.admindata.viewModel.M1ViewModel2

class UsersList : AppCompatActivity() {
    private lateinit var binding: ActivityUsersListBinding
    private lateinit var m1ViewModel2: M1ViewModel2
    private lateinit var recyclerView: RecyclerView
    private lateinit var userListAdapter: UserListAdapter

    private lateinit var context: Context
    private lateinit var myPreferences: MyPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUsersListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
        hitUserListApi()
        observeUserList()
        initRecyclerView()
        context = this
        myPreferences = MyPreferences(context)
    }

    private fun initViews() {
        m1ViewModel2 = ViewModelProvider(this).get(M1ViewModel2::class.java)
    }

    private fun hitUserListApi() {
        m1ViewModel2.hitUserListApi()
    }

    private fun observeUserList() {
        m1ViewModel2.UsersListResp.observe(this, Observer { usersListResp ->
            // Check if the response is successful before accessing the data
            if (usersListResp.isSuccess) {
                // Assuming that result is a List<Result>, update the adapter data
                userListAdapter.updateData(usersListResp.result)
            } else {
                // Handle unsuccessful API response
            }
        })
    }

    private fun initRecyclerView() {
        userListAdapter = UserListAdapter(emptyList()) { username ->
            // Handle item click, e.g., navigate to another activity
            navigateToNextActivity(username)
        }

        recyclerView = findViewById(R.id.userNamesRecycler)
        recyclerView.adapter = userListAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun navigateToNextActivity(username: String) {
        myPreferences.saveString("InstaId", username)
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("userName", username)
        startActivity(intent)
    }
}
