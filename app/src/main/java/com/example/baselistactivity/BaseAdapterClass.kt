package com.example.baselistactivity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class BaseAdapterClass(var arrayList: ArrayList<String>) : BaseAdapter() {
    override fun getCount(): Int {
       return arrayList.size
    }

    override fun getItem(position: Int): Any {
     return "Any Datatype"
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var initView = LayoutInflater.from(parent?.context).inflate(R.layout.item_adapter, parent, false)
       var tvName: TextView = initView.findViewById(R.id.tvName)

             tvName.setText(arrayList[position])
        return initView

    }
}