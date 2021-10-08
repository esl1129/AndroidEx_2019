package com.example.roomexample

import android.os.Bundle
import android.text.Editable
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roomexample.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.runBlocking


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var myDao: MyDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        myDao = MyDatabase.getDatabase(this).getMyDao()
        runBlocking {
            with(myDao, fun MyDAO.() {
                insertStudent(Student(1, "james"))
                insertStudent(Student(2, "john"))
                insertClass(ClassInfo(1, "C-Lang", "Mon 9:00", "E301", 1))
                insertClass(ClassInfo(2, "Android Prog", "Tue 9:00", "E302", 1))
                insertClass(ClassInfo(3, "System Prog", "Wed 9:00", "E303", 1))
                insertClass(ClassInfo(4, "NetWork Prog", "Thu 9:00", "E402", 1))
                insertClass(ClassInfo(5, "Web Framework", "TFri 9:00", "E501", 1))
                insertEnrollment(Enrollment(1, 1))
                insertEnrollment(Enrollment(1, 2))
            })
        }

        fun String.toEditable() : Editable = Editable.Factory.getInstance().newEditable(this)

        binding.studentRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.studentRecyclerView.setHasFixedSize(true)
        binding.studentRecyclerView.itemAnimator = DefaultItemAnimator()

        val allStudents = myDao.getAllStudents()
        allStudents.observe(this, fun(it: List<Student>) {
            val mAdapter = RecyclerAdapter(this,it) {
                val strid = it.id.toString()
                val strname = it.name
                binding.editStudentId.text = strid.toEditable()
                binding.editStudentName.text = strname.toEditable()
                query_student.performClick()
            }
            student_recycler_view.adapter = mAdapter
        })

        binding.queryStudent.setOnClickListener {
            val id = binding.editStudentId.text.toString().toInt()
            runBlocking {
                val results = myDao.getStudentsWithEnrollment(id)
                if (results.isNotEmpty()) {
                    val str = StringBuilder().apply {
                        append(results[0].student.id)
                        append("-")
                        append(results[0].student.name)
                        append(":")
                        for (c in results[0].enrollments) {
                            append(c.cid)
                            val cls_result = myDao.getClassInfo(c.cid)
                            if (cls_result.isNotEmpty())
                                append("(${cls_result[0].name})")
                            append(",")
                        }
                    }
                    binding.textQueryStudent.text = str
                } else {
                    binding.textQueryStudent.text = ""
                }
            }
        }

        binding.addStudent.setOnClickListener {
            val id = binding.editStudentId.text.toString().toInt()
            val name = binding.editStudentName.text.toString()
            if (id > 0 && name.isNotEmpty()) {
                runBlocking {
                    myDao.insertStudent(Student(id, name))
                }
            }
        }

        binding.EnrollStudent.setOnClickListener{
            val sid = binding.editStudentId.text.toString().toInt()
            val cid = (sid % 4) + 1
            if(sid >0 && 0< cid && cid <=5) {
                runBlocking {
                    myDao.insertEnrollment(Enrollment(sid, cid))
                }
            }
        }

        binding.DeleteStudent.setOnClickListener{
            val id = binding.editStudentId.text.toString().toInt()
            val name = binding.editStudentName.text.toString()
            if (id > 0 && name.isNotEmpty()) {
                runBlocking {
                    myDao.deleteStudent(Student(id, name))
                }
            }
        }

    }
}