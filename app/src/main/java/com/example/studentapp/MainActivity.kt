package com.example.studentapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.PopupMenu
import android.widget.RelativeLayout
import android.widget.SearchView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.studentapp.database.AdminDatabase
import com.example.studentapp.databinding.ActivityMainBinding
import com.example.studentapp.entities.Student
import com.example.studentapp.model.AdminViewModel
import com.example.studentapplication.adapter.AdminAdapter


class MainActivity : AppCompatActivity(), AdminAdapter.StudentClickListener, PopupMenu.OnMenuItemClickListener {
    private lateinit var binding: ActivityMainBinding;
    private lateinit var database: AdminDatabase;
    lateinit var viewModel: AdminViewModel;
    lateinit var adminAdapter: AdminAdapter
    lateinit var selectedStudent: Student

    private val updateStudent = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){result ->
        if(result.resultCode == Activity.RESULT_OK){
            val student = result.data?.getSerializableExtra("student") as? Student
            if(student != null){
                viewModel.updateStudent(student)
            }
        }

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater);
        setContentView(R.layout.activity_main)

        initUI()

        viewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(AdminViewModel::class.java);

        viewModel.allStudents.observe(this){ list->
            list?.let {
                adminAdapter.updateList(list)
            }

        }
        database = AdminDatabase.getDatabase(this)
    }

    private fun initUI(){

        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager=StaggeredGridLayoutManager(1, LinearLayout.VERTICAL)
        adminAdapter= AdminAdapter(this, this)
        binding.recyclerView.adapter = adminAdapter

        val getContent = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->
            if(result.resultCode == Activity.RESULT_OK){
                val student = result.data?.getSerializableExtra("student") as? Student
                if(student != null){
                    viewModel.insertStudent(student);
                }
            }
        }
        binding.floatingActionButton.setOnClickListener{
            val intent = Intent(this, AddStudent::class.java)
            getContent.launch(intent)
        }

        binding.searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if(newText!=null){
                    adminAdapter.filterList(newText)
                }
                return true;
            }

        })
    }

    override fun onStudentClicked(student: Student) {
        val intent = Intent(this@MainActivity, AddStudent::class.java);
        intent.putExtra("currentStudent", student)
        updateStudent.launch(intent)

    }

    override fun onLongStudentClicked(student: Student, cardView: RelativeLayout) {
        selectedStudent = student
        popUpDisplay(cardView)
    }

    private fun popUpDisplay(cardView: RelativeLayout) {
        val popUp = PopupMenu(this, cardView)
        popUp.setOnMenuItemClickListener(this@MainActivity)
        popUp.inflate(R.menu.pop_up_menu);
        popUp.show()
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        if(item?.itemId == R.id.delete_Student){
            viewModel.deleteStudent(selectedStudent)
            return true;
        }
        return false;
    }

}