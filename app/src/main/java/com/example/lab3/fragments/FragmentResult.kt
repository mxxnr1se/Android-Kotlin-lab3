package com.example.lab3.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.lab3.R

class FragmentResult : Fragment() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
	}

	fun setSelectedItem(selectedItem: String?) {
		val view = view?.findViewById<TextView>(R.id.textResult)
		view?.text = selectedItem
	}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
	{
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_result, container, false)
	}

	companion object {
		@JvmStatic
		fun newInstance(param1: String, param2: String) = FragmentResult()
	}
}