package com.example.mydata3csa

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

//Retrieves data from db and puts it in a listview
class MyListAdapter(private val context: Activity,
                    private val id:Array<String>,
                    private val name:Array<String>,
                    private val email:Array<String>
                    ): ArrayAdapter<String>(context, R.layout.custom_list) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.custom_list, null, true)

        val idText = rowView.findViewById<TextView>(R.id.tvId)
        val nameText = rowView.findViewById<TextView>(R.id.tvName)
        val emailText = rowView.findViewById<TextView>(R.id.tvEmail)

        idText.text = "Id: ${id}"
        nameText.text = "Name: ${name}"
        emailText.text = "Id: ${email}"

        //returns rowview data from db to listAdapter
        return rowView
    }
}