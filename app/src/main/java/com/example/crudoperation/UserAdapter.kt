package com.example.crudoperation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.crudoperation.model.User

class UserAdapter(val list:List<User>?): RecyclerView.Adapter<CustomViewHolder>(){

var listener:Listener?=null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
      return CustomViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false))
    }



    override fun getItemCount(): Int = list!!.size



    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
       holder.firstName.text= list?.get(position)?.firstName
       holder.lastName.text= list?.get(position)?.lastName
        holder.edit.setOnClickListener {


        }

    }
}

class CustomViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView) {
    lateinit var firstName:TextView
    lateinit var lastName:TextView
    lateinit var edit:Button
    init {
        firstName=itemView.findViewById(R.id.firstname)
        lastName=itemView.findViewById(R.id.lastname)
       edit=itemView.findViewById(R.id.edit)
    }

}
