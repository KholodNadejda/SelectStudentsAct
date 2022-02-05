package com.example.selectstudentsact

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.selectstudentsact.databinding.StudentItemBinding

class AdapterStudents: RecyclerView.Adapter<AdapterStudents.StudentsHolder>() {
    val studentList = ArrayList<StudentsTMS>()

    class StudentsHolder(item: View) : RecyclerView.ViewHolder(item) {
        val binding = StudentItemBinding.bind(item)
        fun bind(students: StudentsTMS) = with(binding) {
            imStudent.setImageResource(students.imgID)
            twName.text = students.name
        }

        fun change(student: StudentsTMS) {
            binding.chStd.setOnCheckedChangeListener { buttonView, isChecked ->
                student.switchS = isChecked
            }
            binding.chStd.isChecked = student.switchS
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentsHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.student_item, parent, false)
        return StudentsHolder(view)
    }

    override fun onBindViewHolder(holder: StudentsHolder, position: Int) {
        holder.bind(studentList[position])
        holder.change(studentList[position])
    }

    override fun getItemCount(): Int {
        return studentList.size
    }
}