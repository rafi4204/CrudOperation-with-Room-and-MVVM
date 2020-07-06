package com.example.crudoperation

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.crudoperation.model.User

class UserAdapter(val list: List<User>?) : RecyclerView.Adapter<CustomViewHolder>() {


    var listener: Listener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        return CustomViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.list_item,
                parent,
                false
            )
        )
    }


    override fun getItemCount(): Int = list!!.size


    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.firstName.text = list?.get(position)?.firstName
        holder.lastName.text = list?.get(position)?.lastName
        holder.age.text = list?.get(position)?.age
        holder.gender.text = list?.get(position)?.gender
        holder.edit.setOnClickListener {
            val user = list?.get(position)
            listener?.updateListener(user)
        }
        holder.delete.setOnClickListener {
            val user = list?.get(position)
            listener?.deleteListener(user)
        }
        holder.iv.setImageBitmap(list?.get(position)?.image?.size?.let {
            BitmapFactory.decodeByteArray(
                list[position].image, 0,it)
        })
    }
}

class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var firstName: TextView = itemView.findViewById(R.id.firstname)
    var lastName: TextView = itemView.findViewById(R.id.lastname)
    var age: TextView = itemView.findViewById(R.id.age)
    var gender: TextView = itemView.findViewById(R.id.gender)
    var edit: Button = itemView.findViewById(R.id.edit)
    var delete: Button = itemView.findViewById(R.id.delete)
    var iv: ImageView = itemView.findViewById(R.id.iv)


}
