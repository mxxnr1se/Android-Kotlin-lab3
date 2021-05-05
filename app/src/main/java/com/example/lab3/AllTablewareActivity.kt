package com.example.lab3

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.lab3.database.DatabaseHandler

class AllTablewareActivity : AppCompatActivity() {
	@SuppressLint("RestrictedApi")
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_all_tableware)

		var actionBar = supportActionBar
		actionBar!!.title = "Chosen tableware"
		actionBar.setDefaultDisplayHomeAsUpEnabled(true)

		//show data from db
		var db = DatabaseHandler(this)
		var data = db.readTableware()
		var result = ""

		for (i in 0 until data.size) {

			result += /*data[i].id.toString() + " " +*/ "\t\t" + data[i].tableware + "\n\tManufacturer:" + data[i].manufacturer + "\n\t\t" + data[i].minvalue.toString() + " - " + data[i].maxvalue.toString() + " USD\n\n"

		}

		if (result != "") findViewById<TextView>(R.id.allTableware)?.text = result
		else Toast.makeText(this, "Database is empty", Toast.LENGTH_SHORT).show()
	}

	fun tablewareClear(view: View) {
		var db = DatabaseHandler(this)
		var data = db.readTableware()

		if (data.count() == 0) Toast.makeText(this, "Database is already empty", Toast.LENGTH_SHORT).show()
		else {
			db.clearTableware()
			findViewById<TextView>(R.id.allTableware)?.text = ""
			Toast.makeText(this, "Database was cleared", Toast.LENGTH_SHORT).show()
		}
	}
}