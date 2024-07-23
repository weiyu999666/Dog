package com.example.dogs

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class DogAdapter(private val context: Context, private val dataSource: ArrayList<Dog>) : BaseAdapter() {

    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return dataSource.size
    }

    override fun getItem(position: Int): Any {
        return dataSource[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val rowView = inflater.inflate(R.layout.list_item_dog, parent, false)

        val textViewName = rowView.findViewById(R.id.textViewName) as TextView
        val textViewAge = rowView.findViewById(R.id.textViewAge) as TextView
        val textViewType = rowView.findViewById(R.id.textViewType) as TextView
        val textViewOwnerName = rowView.findViewById(R.id.textViewOwnerName) as TextView

        val dog = getItem(position) as Dog

        textViewName.text = "Dog Name: ${dog.name}"
        textViewAge.text = "Dog Age: ${dog.age}"
        textViewType.text = "Dog Type: ${dog.type}"
        textViewOwnerName.text = "Dog Owner Name: ${dog.owner}"


        return rowView
    }
}
