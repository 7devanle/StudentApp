package com.example.studentapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.studentapp.entities.Student
import com.example.studentapp.R


class AdminAdapter(private val context: Context, val listener: StudentClickListener)
    :RecyclerView.Adapter<AdminAdapter.AdminViewHolder>(){

    private val studentList = ArrayList<Student>()
    private val fullStudentList = ArrayList<Student>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdminViewHolder {
        return AdminViewHolder(LayoutInflater.from(context).inflate(R.layout.student, parent, false))
    }

    override fun getItemCount(): Int {
        return studentList.size;
    }

    override fun onBindViewHolder(holder: AdminViewHolder, position: Int) {
        val currentStudent = studentList[position]
        holder.studentName.isSelected = true;
        holder.studentName.text = currentStudent.name;
        holder.location.text = currentStudent.location;
        holder.department.text = currentStudent.department;
        holder.faculty.text = currentStudent.faculty;
        holder.studentLayout.setBackgroundColor(colorChange(1));  //color change

        holder.studentLayout.setOnClickListener {
            listener.onStudentClicked(studentList[holder.adapterPosition])
        }

        holder.studentLayout.setOnLongClickListener{
            listener.onLongStudentClicked(studentList[holder.adapterPosition], holder.studentLayout)
            true
        }

    }

    fun updateList(newList: List<Student>){
        fullStudentList.clear();
        fullStudentList.addAll(newList);

        studentList.clear();
        studentList.addAll(fullStudentList)
    }

    fun colorChange(color: Int): Int{

        val colorList = ArrayList<Int>()
        colorList.add(R.color.grey)
        colorList.add(R.color.blue)

        if(color == 0)return colorList[0]
        else return colorList[1]

    }

    fun filterList(search: String){
        studentList.clear();
        for(student in studentList){
            if(student.matNo?.toLowerCase()?.contains(search.toLowerCase()) == true ||
                student.name?.toLowerCase()?.contains((search.toLowerCase())) == true)
                    studentList.add(student);
        }
        notifyDataSetChanged()

    }

    inner class AdminViewHolder(studentView: View): RecyclerView.ViewHolder(studentView){
        val studentLayout = studentView.findViewById<RelativeLayout>(R.id.card_layout)
        val picture = studentView.findViewById<ImageView>(R.id.photo)
        val studentName = studentView.findViewById<TextView>(R.id.name)
        val location = studentView.findViewById<TextView>(R.id.location)
        val department = studentView.findViewById<TextView>(R.id.department)
        val faculty = studentView.findViewById<TextView>(R.id.faculty)

    }

    interface StudentClickListener{
        fun onStudentClicked(student: Student)
        fun onLongStudentClicked(student: Student, cardView: RelativeLayout)
    }
}