package com.example.lab3

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.lab3.fragments.FragmentForm
import com.example.lab3.fragments.FragmentResult

class MainActivity : AppCompatActivity(), FragmentForm.OnFragmentFormDataListener {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
	}

	fun tablewareButton(view: View?) {
		val intent = Intent(this, AllTablewareActivity::class.java)
		startActivity(intent)
	}

	override fun onSendData(data: String?) {
		if (data != "") (supportFragmentManager.findFragmentById(R.id.FragmentResult) as FragmentResult?)?.setSelectedItem(
			data
		)
	}
}