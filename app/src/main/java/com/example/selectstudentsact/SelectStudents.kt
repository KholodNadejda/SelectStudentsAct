package com.example.selectstudentsact

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.random.Random
import kotlin.random.nextInt

class SelectStudents : AppCompatActivity() {
    private var mainMenu: Menu? = null
    private val adapter = AdapterStudents()
    private var IdList1 = listOf<String>()
    private var list = ""
    private val i = Intent()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_students)
        arrayStudents()
        init()
    }

    private fun init() {
        val mRecyclerView = findViewById<RecyclerView>(R.id.rcStudents) //id RecyclerView
        val dividerItemDecorationV =
            DividerItemDecoration(this@SelectStudents, LinearLayoutManager.VERTICAL)
        val dividerItemDecorationH =
            DividerItemDecoration(this@SelectStudents, LinearLayoutManager.HORIZONTAL)
        mRecyclerView.addItemDecoration(dividerItemDecorationV)
        mRecyclerView.addItemDecoration(dividerItemDecorationH)
        mRecyclerView.layoutManager = GridLayoutManager(this, 2)
        mRecyclerView.adapter = adapter
        for (i in IdList1.indices) {
            val random = Random
            val student =
                StudentsTMS(random.nextInt(R.drawable.c0..R.drawable.c9), IdList1[i], false)
            adapter.studentList.add(student)
        }
    }

    private fun arrayStudents() {
        IdList1 = listOf(
            resources.getString(R.string.Nikita),
            resources.getString(R.string.Marina),
            resources.getString(R.string.Anna),
            resources.getString(R.string.Egor),
            resources.getString(R.string.Andrey),
            resources.getString(R.string.Natasha),
            resources.getString(R.string.Anton),
            resources.getString(R.string.Dima),
            resources.getString(R.string.Darina),
            resources.getString(R.string.Nadejda),
            resources.getString(R.string.Alexander_K),
            resources.getString(R.string.Anastasia),
            resources.getString(R.string.Stas),
            resources.getString(R.string.Alexander_I),
            resources.getString(R.string.Jaroslav)
        )
    }

    private fun startRandom() {

        var checkStudents = adapter.studentList.filter { it.switchS }
        if (checkStudents.isNotEmpty()) {
            for (i in checkStudents.indices) {
                if (i == 0) {
                    list += checkStudents[i].name
                } else {
                    list = list + " " + checkStudents[i].name
                }
            }
        }

        i.putExtra("EXTRA", list)
        setResult(RESULT_OK, i)
        finish()

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.mAdd -> {
                addStd()
            }
        }
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        mainMenu = menu
        menuInflater.inflate(R.menu.custom_menu, mainMenu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun addStd() {
        val alterDialog = AlertDialog.Builder(this)
        alterDialog.setTitle("Add?")
        alterDialog.setMessage("Are u ready?")
        alterDialog.setPositiveButton("Yep") { _, _ ->
            startRandom()
        }
        alterDialog.setNegativeButton("NO") { _, _ -> }
        alterDialog.show()
    }
}