package com.example.ibtechbootcamphm3.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ibtechbootcamphm3.R
import com.example.ibtechbootcamphm3.model.Data

class AvatarFragmentAdapter(private val context: Context, var avatarList: ArrayList<Data>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var selectedItemPos = -1
    var lastItemSelectedPos = -1
    companion object {
        const val VIEW_TYPE_ONE = 1
        const val VIEW_TYPE_TWO = 2
    }
    private inner class View1ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var message: TextView = itemView.findViewById(R.id.avatar_text)     //  textview here
        fun bind (position: Int) {
            val recyclerViewModel = avatarList[position]
            message.text = recyclerViewModel.text
        }
    }
    private inner class View2ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var avatar: ImageView = itemView.findViewById(R.id.avatar_image)    // imageview here
        fun bind (position: Int) {
            val recyclerViewModel = avatarList[position]
            recyclerViewModel.avatar?.let {
                avatar.setImageResource(it)      //avatar image set
            }
        }

        /*
       checkbox change logic according to user selection
         */
        val checkbox: ImageView = itemView.findViewById(R.id.checkbox_icon)
        fun default() {
            checkbox.setImageResource(0)    //no image source
        }

        fun selected() {
            checkbox.setImageResource(R.drawable.ic_checked)   // checked
        }

        init {
            /*
            avatar selection logic
             */
            itemView.setOnClickListener {
                selectedItemPos = adapterPosition
                lastItemSelectedPos = if(lastItemSelectedPos == -1)
                    selectedItemPos
                else {
                    notifyItemChanged(lastItemSelectedPos)
                    selectedItemPos
                }
                notifyItemChanged(selectedItemPos)
            }
        }
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (avatarList[position].viewType == VIEW_TYPE_ONE && holder is View1ViewHolder) {
            holder.bind(position)
        } else {
            (holder as View2ViewHolder).bind(position)

            if(position == selectedItemPos)
                holder.selected()
            else
                holder.default()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_ONE) {
            View1ViewHolder(
                LayoutInflater.from(context).inflate(R.layout.row_text, parent, false)
            )
        } else {
            View2ViewHolder(
                LayoutInflater.from(context).inflate(R.layout.row_avatar, parent, false)
            )
        }
    }



    override fun getItemCount(): Int =avatarList.size

    override fun getItemViewType(position: Int): Int {
        return avatarList[position].viewType
    }


}