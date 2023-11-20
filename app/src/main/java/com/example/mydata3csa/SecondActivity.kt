package com.example.mydata3csa

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlin.math.log

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
    }

    fun saveRecord(view: View){
        val u_id = findViewById<EditText>(R.id.etId)
        val u_name = findViewById<EditText>(R.id.etName)
        val u_email = findViewById<EditText>(R.id.etEmail)

        val id = u_id.text.toString()
        val name = u_name.text.toString()
        val email = u_email.text.toString()

        val databaseHandler: DatabaseHandler = DatabaseHandler(this)

        if(id.trim() != "" && name.trim() != "" && email.trim() != ""){
            val status = databaseHandler.addEmployee(EmpModelClass(Integer.parseInt(id), name, email))
            if(status > -1){
                Toast.makeText(this, "Record Saved", Toast.LENGTH_LONG).show()
                u_id.text.clear()
                u_name.text.clear()
                u_email.text.clear()
            }
        } else { //field/s are blank
            Toast.makeText(this, "Some Fields are Blank.", Toast.LENGTH_LONG).show()
        }
    }

    fun viewRecord(view: View) {
        val databaseHandler: DatabaseHandler = DatabaseHandler(this)
        val emp: List<EmpModelClass> = databaseHandler.viewEmployee()
        val empArrayId = Array<String>(emp.size){"0"}
        val empArrayName = Array<String>(emp.size){"null"}
        val empArrayEmail = Array<String>(emp.size){"null"}

        var index = 0
        for(e in emp){
            empArrayId[index] = e.userId.toString()
            empArrayName[index] = e.userName
            empArrayEmail[index] = e.userEmail
            index ++
        }

        val listview = findViewById<ListView>(R.id.lvEmp)
        val x = MyListAdapter(this, empArrayId, empArrayName, empArrayEmail)
        listview.adapter = x
    }

    fun updateRecord(view: View){
        val dialogBuilder = AlertDialog.Builder(this)
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.update_dialog, null)
        dialogBuilder.setView(dialogView)

        val editId = dialogView.findViewById<EditText>(R.id.etUpdateId)
        val editName = dialogView.findViewById<EditText>(R.id.etUpdateName)
        val editEmail = dialogView.findViewById<EditText>(R.id.etUpdateEmail)

        dialogBuilder.setTitle("Update Record")
        dialogBuilder.setMessage("Enter Data Below")
        dialogBuilder.setPositiveButton("Update", DialogInterface.OnClickListener { dialogInterface, i ->
            val updateId = editId.text.toString()
            val updateName = editName.text.toString()
            val updateEmail = editEmail.text.toString()
            val databaseHandler: DatabaseHandler = DatabaseHandler(this)

            if(updateId.trim() != "" && updateName.trim() != "" && updateEmail.trim() != ""){
                val status = databaseHandler.updateEmployee(EmpModelClass(Integer.parseInt(updateId), updateName, updateEmail))
                if(status > -1){
                    Toast.makeText(this, "Record Saved", Toast.LENGTH_LONG).show()
//                    editId.text.clear()
//                    editName.text.clear()
//                    editEmail.text.clear()
                }
            } else { //field/s are blank
                Toast.makeText(this, "Some Fields are Blank.", Toast.LENGTH_LONG).show()
            }
        })

        dialogBuilder.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialogInterface, i ->

        })

        val b = dialogBuilder.create()
        b.show()
    }

    fun deleteRecord(view:View){
        val dialogBuilder = AlertDialog.Builder(this)
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.delete_dialog, null)
        dialogBuilder.setView(dialogView)

        val deleteId = dialogView.findViewById<EditText>(R.id.etDeleteId)

        dialogBuilder.setTitle("Delete Record")
        dialogBuilder.setMessage("Enter ID Below")
        dialogBuilder.setPositiveButton("Update", DialogInterface.OnClickListener { dialogInterface, i ->
            val deleteId = deleteId.text.toString()

            val databaseHandler: DatabaseHandler = DatabaseHandler(this)

            if(deleteId.trim() != ""){
                val status = databaseHandler.deleteEmployee(EmpModelClass(Integer.parseInt(deleteId), "",""))
                if(status > -1){
                    Toast.makeText(this, "Record Saved", Toast.LENGTH_LONG).show()
//                    editId.text.clear()
//                    editName.text.clear()
//                    editEmail.text.clear()
                }
            } else { //field/s are blank
                Toast.makeText(this, "Some Fields are Blank.", Toast.LENGTH_LONG).show()
            }
        })

        dialogBuilder.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialogInterface, i ->

        })

        val b = dialogBuilder.create()
        b.show()
    }
}