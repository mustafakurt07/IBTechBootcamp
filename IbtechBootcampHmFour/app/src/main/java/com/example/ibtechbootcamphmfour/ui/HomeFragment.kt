package com.example.ibtechbootcamphmfour.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.input.input
import com.example.ibtechbootcamphmfour.R
import com.example.ibtechbootcamphmfour.adapter.TodoAdapter
import com.example.ibtechbootcamphmfour.base.BaseCallBack
import com.example.ibtechbootcamphmfour.model.CompleteBody
import com.example.ibtechbootcamphmfour.model.Todo
import com.example.ibtechbootcamphmfour.model.TodoResponse
import com.example.ibtechbootcamphmfour.service.ServiceConnector
import com.example.ibtechbootcamphmfour.utils.gone
import com.example.ibtechbootcamphmfour.utils.statusBarColor
import com.example.ibtechbootcamphmfour.utils.visible
import kotlinx.android.synthetic.main.layout_fragment_home.*
import retrofit2.Call
import kotlin.reflect.KVisibility

class HomeFragment: Fragment(), TodoAdapter.OnClickListener{
    private var todoList = ArrayList<Todo>()
    private var todoAdapter : TodoAdapter? = null
    var limit = 10
    var skip = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        statusBarColor(R.color.purple_700)

        getDataByPagination(limit, skip)

        return inflater.inflate(R.layout.layout_fragment_home, container, false)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        floatingActionButton.setOnClickListener {
            showMaterialDialog()
        }

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1) && newState== RecyclerView.SCROLL_STATE_IDLE) {

                    this@HomeFragment.skip += limit
                    progressBar.visibility = View.VISIBLE
                    getDataByPagination(limit, skip)
                }
            }
        })
    }
    override fun onResume() {
        super.onResume()

        val activity = activity as MainActivity
        activity.onBackPressedDispatcher.addCallback(viewLifecycleOwner, object :
            OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                activity.finish()
            }
        })
    }
    private fun getDataByPagination(limit: Int, skip: Int){

        ServiceConnector.restInterface.getTaskByPagination(limit, skip).enqueue(object :BaseCallBack<TodoResponse>(){
            override fun onSuccess(getResponse: TodoResponse) {
                super.onSuccess(getResponse)

                progressBar.visibility = View.GONE

                todoList.addAll(getResponse.data)

                if(todoList.size == 0){
                    noTaskMessage.visibility = View.VISIBLE
                }

                if (todoAdapter == null){
                    todoAdapter = TodoAdapter(todoList,this@HomeFragment)
                    recyclerView.adapter = todoAdapter
                }

                else{
                    recyclerView.adapter?.notifyDataSetChanged()
                }
            }
            override fun onFailure() {
                super.onFailure()
                noTaskMessage.visibility = View.VISIBLE
                Toast.makeText(requireActivity(), "Something went wrong, no tasks have been fetched", Toast.LENGTH_SHORT).show()
            }
        })
    }

    @SuppressLint("CheckResult")
    private fun showMaterialDialog(){

        val newTodo = Todo()

        MaterialDialog(requireActivity())
            .show {
                title(R.string.new_task)
                input { _, newTaskDesc ->
                    newTodo.description = newTaskDesc.toString()
                    newTodo.completed = false
                }
            }
            .positiveButton(R.string.add){
                ServiceConnector.restInterface.addTask(newTodo).enqueue(object:BaseCallBack<Unit>(){
                    override fun onSuccess(data: Unit) {
                        Toast.makeText(requireActivity(), "Successfully added new task", Toast.LENGTH_SHORT).show()
                        this@HomeFragment.skip = todoList.size
                        getDataByPagination(limit, skip)
                        noTaskMessage.gone()
                    }
                    override fun onFailure(call: Call<Unit>, t: Throwable) {
                        noTaskMessage.visible()
                        Toast.makeText(requireActivity(), "Something went wrong", Toast.LENGTH_SHORT).show()
                        Log.e("Failure", "on Triggered")
                    }
                })
                Log.d("object", newTodo.description + " " + newTodo.completed.toString())
            }
            .negativeButton(R.string.dissmis)
    }
    override fun onCompleteButtonClick(position: Int) {

        val clickedItem = todoList[position]

        clickedItem.completed = !clickedItem.completed

        todoAdapter?.notifyItemChanged(position)

        val completeBody = CompleteBody(clickedItem.completed)

        ServiceConnector.restInterface.updateTaskById(clickedItem._id!!, completeBody).enqueue(object: BaseCallBack<Unit>(){

            override fun onSuccess(data: Unit) {
                super.onSuccess(data)
                if(clickedItem.completed)
                    Toast.makeText(requireContext(), "Task ${position + 1} is completed", Toast.LENGTH_SHORT).show()
                else if (!clickedItem.completed){
                    Toast.makeText(requireContext(), "Task ${position + 1} is uncompleted", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure() {
                super.onFailure()

                Toast.makeText(requireContext(), "Failed to complete the task", Toast.LENGTH_SHORT).show()
            }

        })

    }



    override fun onDeleteButtonClick(position: Int) {
        val clickedItem = todoList[position]

        ServiceConnector.restInterface.deleteTaskById(clickedItem._id!!).enqueue(object: BaseCallBack<Unit>(){

            override fun onSuccess(data: Unit) {
                super.onSuccess(data)

                todoList.removeAt(position)
                todoAdapter?.notifyItemRemoved(position)
                todoAdapter?.notifyItemRangeChanged(position,position)
                Toast.makeText(requireContext(), "Task ${position + 1} is deleted", Toast.LENGTH_SHORT).show()
                if(todoList.size==0)
                {
                    noTaskMessage.visible()
                    noTaskMessage.setText(R.string.havent_task)
                }//butun tasklar silinirse  text basıyoruz hiç task kalmadı dite

            }
            override fun onFailure() {
                super.onFailure()
                Toast.makeText(requireContext(), "Failed to delete the task", Toast.LENGTH_SHORT).show()
            }


        })
    }

}