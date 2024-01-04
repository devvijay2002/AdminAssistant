package com.example.admindata

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
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
        if (report.status < 0.toString()) {
            // Set red text color
            holder.status.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.red_background_color))
        }else if (report.status > 0.toString()) {
            holder.status.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.darkgreen))
        }else {
            // Set default text color for other cases
            holder.status.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.black))
        }

    }

    override fun getItemCount(): Int {
        return reportList.size
    }

    fun updateData(newList: List<ReportResp.Result>) {
        reportList = newList
    }
}
