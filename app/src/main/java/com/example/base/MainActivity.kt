package com.example.base

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

lateinit var database:DatabaseReference

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val signButton = findViewById<Button>(R.id.button)
        val etName = findViewById<TextInputEditText>(R.id.etName)
        val etMail = findViewById<TextInputEditText>(R.id.etMail)
        val userId = findViewById<TextInputEditText>(R.id.etUserName)
        val password = findViewById<TextInputEditText>(R.id.etPass)
        signButton.setOnClickListener {
            val name = etName.text.toString()
            val mail = etMail.text.toString()
            val uniqueID = userId.text.toString()
            val Password = password.text.toString()
            val user = User(name, mail, Password, uniqueID)
            database = FirebaseDatabase.getInstance().getReference("Users")
            database.child(uniqueID).setValue(user).addOnSuccessListener {
                etName.text?.clear()
                etMail.text?.clear()
                userId.text?.clear()
                password.text?.clear()
                Toast.makeText(this, "User Registered", Toast.LENGTH_SHORT).show()
            }.addOnSuccessListener {
                Toast.makeText(this, "ERROR Occured", Toast.LENGTH_SHORT).show()
            }

        }
        signButton.setOnClickListener {
            val intent=Intent(this,bottom_nav::class.java)
            startActivity(intent)
            finish()
        }
    }
    private fun replaceFragment(fragment: Fragment)
    {
        val fragmentManager=supportFragmentManager
        val fragmentTransaction=fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.nav_host_fragment_activity_bottom_nav,fragment)
        fragmentTransaction.commit()
    }
}