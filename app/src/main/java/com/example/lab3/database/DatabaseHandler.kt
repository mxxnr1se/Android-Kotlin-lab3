package com.example.lab3.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import com.example.lab3.entities.Tableware

val DATABASE_NAME = "DbTableware"
val COL_ID = "id"
val TABLE_NAME = "Tableware"
val COL_TABLEWARE = "tableware"
val COL_MANUFACTURER = "manufacturer"
val COL_MINVALUE = "minvalue"
val COL_MAXVALUE = "maxvalue"

class DatabaseHandler(var context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {
	override fun onCreate(db: SQLiteDatabase?) {
		val createTable = "CREATE TABLE " + TABLE_NAME + " (" +
				COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
				COL_TABLEWARE + " VARCHAR(255)," +
				COL_MANUFACTURER + " VARCHAR(255)," +
				COL_MINVALUE + " REAL," +
				COL_MAXVALUE + " REAL)" ;

		db?.execSQL(createTable);
	}

	override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
		TODO("Not yet implemented")
	}

	fun insertTableware(tableware: Tableware) {
		val db = this.writableDatabase

		var contentValues = ContentValues()
		contentValues.put(COL_TABLEWARE, tableware.tableware)
		contentValues.put(COL_MANUFACTURER, tableware.manufacturer)
		contentValues.put(COL_MINVALUE, tableware.minvalue)
		contentValues.put(COL_MAXVALUE, tableware.maxvalue)

		var result = db.insert(TABLE_NAME, null, contentValues)
		if (result == (-1).toLong())
			Toast.makeText(context, "Failed to write in Database", Toast.LENGTH_SHORT).show()
		else
			Toast.makeText(context, "Successfully written", Toast.LENGTH_SHORT).show()

		db.close()
	}

	fun clearTableware(){
		val db = this.writableDatabase
		db.execSQL("delete from $TABLE_NAME")
		db.close()
	}

	fun readTableware() : MutableList<Tableware>{
		var list : MutableList<Tableware> = ArrayList()

		val db = this.readableDatabase
		val query = "select * from $TABLE_NAME order by $COL_ID desc"  //limit 5

		val result = db.rawQuery(query, null)

		if (result.moveToFirst()){
			do {
				var tableware = Tableware ()
				tableware.id = result.getString(0).toInt()
				tableware.tableware = result.getString(1).toString()
				tableware.manufacturer = result.getString(2).toString()
				tableware.minvalue = result.getString(3).toDouble()
				tableware.maxvalue = result.getString(4).toDouble()

				list.add(tableware)
			} while (result.moveToNext())
		}

		result.close()
		db.close()
		return list
	}
}