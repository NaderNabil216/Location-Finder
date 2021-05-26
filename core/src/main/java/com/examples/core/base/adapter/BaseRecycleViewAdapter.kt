package com.examples.core.base.adapter

import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Nader Nabil on 1/11/2020.
 */

abstract class BaseRecycleViewAdapter<T> : RecyclerView.Adapter<BaseViewHolder<T>>() {

    private var data: MutableList<T>


    init {
        data = ArrayList()
    }

    fun setData(data: List<T>) {
        if (data.isNotEmpty()) {
            this.data = data as MutableList<T>
            notifyDataSetChanged()
        }
    }

    fun clear() {
        this.data.clear()
        notifyDataSetChanged()
    }

    fun getData(): MutableList<T> {
        return this.data
    }

    fun addData(data: List<T>) {
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    fun getItem(position: Int): T {
        return data[position]
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        holder.bindData(getItem(position))
    }
}
