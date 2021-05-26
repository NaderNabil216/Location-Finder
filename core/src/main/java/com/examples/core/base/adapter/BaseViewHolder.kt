package com.examples.core.base.adapter

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Nader Nabil on 1/11/2020.
 */
abstract class BaseViewHolder<T>(val context: Context, view: View) : RecyclerView.ViewHolder(view) {
    abstract fun bindData(data: T)
}