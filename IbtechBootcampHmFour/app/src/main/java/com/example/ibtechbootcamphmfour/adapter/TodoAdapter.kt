package com.example.ibtechbootcamphmfour.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ibtechbootcamphmfour.R
import com.example.ibtechbootcamphmfour.model.Todo
import kotlinx.android.synthetic.main.todo_row.view.*


class TodoAdapter(
    private val todoList: List<Todo>,
    private val listener: OnClickListener
) : RecyclerView.Adapter<TodoAdapter.ViewHolder>() {


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        val checkState: ImageView = itemView.checkState
        val todoText: TextView = itemView.todoText
        val completeButton: ImageView = itemView.completeButton


        init {
            itemView.completeButton.setOnClickListener(this)
        }

        init {
            itemView.deleteButton.setOnClickListener(this)
        }

          override fun onClick(v: View?) {
              val position=adapterPosition
              if (position != RecyclerView.NO_POSITION) {
                  listener.onDeleteButtonClick(position)
              }
          }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.todo_row,
            parent, false
        )
        return ViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val currentItem = todoList[position]

        fun setImageResourceOfCheckState(): Int {
            return if (!currentItem.completed) {
                R.drawable.ic_circle_checkbox_unchecked
            } else {
                R.drawable.ic_circle_checkbox_checked
            }
        }


        holder.checkState.setImageResource(setImageResourceOfCheckState())

        holder.completeButton.setOnClickListener {
            if (position != RecyclerView.NO_POSITION) {
                listener.onCompleteButtonClick(position)
                changeCheckBoxState(holder.checkState)
            }
        }

        holder.todoText.text = currentItem.description

    }
    private fun changeCheckBoxState(checkState: ImageView) {
        if (checkState.resources.equals(R.drawable.ic_circle_checkbox_unchecked)) {
            checkState.setImageResource(R.drawable.ic_circle_checkbox_checked)
        } else {
            checkState.setImageResource(R.drawable.ic_circle_checkbox_unchecked)
        }
    }

    override fun getItemViewType(position: Int) = position

    override fun getItemCount(): Int {
        return todoList.size
    }
    interface OnClickListener {
        fun onDeleteButtonClick(position: Int)
        fun onCompleteButtonClick(position: Int)
    }

}
