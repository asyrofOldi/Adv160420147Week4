package com.ubaya.advweek4160420147.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.ubaya.advweek4160420147.R
import com.ubaya.advweek4160420147.databinding.StudentListItemsBinding
import com.ubaya.advweek4160420147.model.Student
import com.ubaya.advweek4160420147.util.loadImage

class StudentListAdapter(private val studentList: ArrayList<Student>) : RecyclerView.Adapter<StudentListAdapter.StudentViewHolder>() {
    class StudentViewHolder(val binding: StudentListItemsBinding) : RecyclerView.ViewHolder(binding.root)

    fun updateStudentList(newStudentList: ArrayList<Student>) {
        studentList.clear()
        studentList.addAll(newStudentList)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val binding = StudentListItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StudentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = studentList[position]
        with(holder.binding) {
            txtID.text = student.id.toString()
            txtName.text = student.name
            progressBar.visibility = View.VISIBLE

            Picasso.get()
                .load(student.photoUrl)
                .into(imgStory, object : Callback {
                    override fun onSuccess() {
                        progressBar.visibility = View.GONE
                    }

                    override fun onError(e: Exception?) {
                        progressBar.visibility = View.GONE
                        imgStory.visibility = View.INVISIBLE
                        Log.e("PicassoError", "Error loading image: ${e?.message}")
                    }
                })

            holder.binding.btnDetail.setOnClickListener {
                val studentId = studentList[position].id // Assuming you have a list of students and each has an id
                val action = StudentListFragmentDirections.actionStudentDetail(studentId.toString())
                it.findNavController().navigate(action)
            }

        }
    }

        override fun getItemCount() = studentList.size

}

