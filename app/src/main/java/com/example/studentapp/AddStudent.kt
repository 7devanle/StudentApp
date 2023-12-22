package com.example.studentapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.studentapp.databinding.ActivityAddStudentBinding
import com.example.studentapp.entities.Student
import java.lang.Exception


class AddStudent : AppCompatActivity() {
    private lateinit var binding: ActivityAddStudentBinding;

    private lateinit var student: Student
    private lateinit var oldStudent: Student
    var isUpdate = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddStudentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        try{
            oldStudent= intent.getSerializableExtra("currenStudent") as Student

            binding.name.setText(oldStudent.name)
            binding.location.setText(oldStudent.location)
            binding.department.setText(oldStudent.department)
            binding.faculty.setText(oldStudent.faculty)
            isUpdate = true;
        }catch (e: Exception){
            e.printStackTrace();
        }
        binding.registerStudentButton.setOnClickListener{
            val name = binding.name.text.toString();
            val photo = R.drawable.face
            val location = binding.location.text.toString();
            val department = binding.department.text.toString();
            val faculty = binding.faculty.text.toString();
            val blackListed = false;

            if(name.isNotEmpty() || location.isNotEmpty() || department.isNotEmpty() || faculty.isNotEmpty()){
                if (isUpdate){
                    student = Student(oldStudent.id, oldStudent.matNo, name, photo,
                        location, department, faculty, false);
                }else{
                    student = Student(null, null, name, photo, location, department, faculty, blackListed)
                }
                val intent  = Intent();
                intent.putExtra("student", student);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }else{
                Toast.makeText(this@AddStudent, "Please enter proper data", Toast.LENGTH_SHORT).show();
                return@setOnClickListener
            }
        }
    }
}