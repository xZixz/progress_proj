package com.besmall.kien.progressproj.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.besmall.kien.progressproj.R
import com.besmall.kien.progressproj.model.TargetModel

/**
* Created by kien on 09/03/2018.
*/

class TargetAdapter(private var targetModels: List<TargetModel>) : RecyclerView.Adapter<TargetAdapter.TargetViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TargetViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.target_list_item, parent, false)
        return TargetViewHolder(view)
    }

    override fun onBindViewHolder(holder: TargetViewHolder, position: Int) {
        val targetModel = targetModels[position]
        holder.nameView.text = targetModel.targetName
    }

    override fun getItemCount(): Int {
        return targetModels.size
    }

    fun addItems(newList: List<TargetModel>) {
        targetModels = newList
        notifyDataSetChanged()
    }

    class TargetViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var nameView: TextView = itemView.findViewById(R.id.target_name)

    }
}
