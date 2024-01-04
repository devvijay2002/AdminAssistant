package com.example.admindata

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.modelresp.ReportResp

class ReportAdapter(private val context: Context, private var reportList: List<ReportResp.Result>) :
    RecyclerView.Adapter<ReportAdapter.ViewHolder>() {

    // ViewHolder class
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val date: TextView = itemView.findViewById(R.id.date)
        val status: TextView = itemView.findViewById(R.id.status)
        // Add more views as needed
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.reportitem, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val report = reportList[position]
        holder.date.text = report.date
        holder.status.text = report.status
        // Set more data to other views as needed
    }

    override fun getItemCount(): Int {
        return reportList.size
    }

    fun updateData(newList: List<ReportResp.Result>) {
        reportList = newList
    }
}
