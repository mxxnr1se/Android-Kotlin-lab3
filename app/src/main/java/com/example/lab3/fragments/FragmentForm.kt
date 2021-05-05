package com.example.lab3.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.lab3.R
import kotlin.math.round
import com.example.lab3.database.DatabaseHandler
import com.example.lab3.entities.Tableware
import java.text.NumberFormat
import java.util.*

class FragmentForm : Fragment() {

	internal interface OnFragmentFormDataListener {
		fun onSendData(data: String?)
	}

	private var fragmentFormDataListener: OnFragmentFormDataListener? = null

	override fun onAttach(context: android.content.Context) {
		super.onAttach(context)
		fragmentFormDataListener = try {
			context as OnFragmentFormDataListener
		} catch (e: ClassCastException) {
			throw ClassCastException("$context interface? OnFragmentInteractionListener")
		}
	}

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
	): View? {        // Inflate the layout for this fragment
		val view = inflater.inflate(R.layout.fragment_form, container, false)

		val spinner = view?.findViewById<Spinner>(R.id.droplist)
		ArrayAdapter.createFromResource(requireContext(), R.array.spinner_values, R.layout.spinner_item)
			.also { adapter ->
				adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
				spinner?.adapter = adapter
			}

		val rangeSlider = view.findViewById<com.google.android.material.slider.RangeSlider>(R.id.range_slider)

		var minvalue = 0.0
		var maxvalue = 1000.0

		rangeSlider.setLabelFormatter { value: Float ->
			val format = NumberFormat.getCurrencyInstance()
			format.maximumFractionDigits = 2
			format.currency = Currency.getInstance("USD")
			format.format(value.toDouble())
		}

		rangeSlider.addOnChangeListener { slider, value, fromUser ->
			slider.values[slider.activeThumbIndex]
			minvalue = round(rangeSlider.values[0].toDouble() * 100) / 100
			maxvalue = round(rangeSlider.values[1].toDouble() * 100) / 100
		}



		val okButton = view?.findViewById<Button>(R.id.ok)
		okButton?.setOnClickListener {
			val spinner = view.findViewById<Spinner>(R.id.droplist)
			val checkbox_m1 = view.findViewById<CheckBox>(R.id.checkbox_m1)
			val checkbox_m2 = view.findViewById<CheckBox>(R.id.checkbox_m2)
			val checkbox_m3 = view.findViewById<CheckBox>(R.id.checkbox_m3)
			var flag = 0
			var checkboxValues = ""

			var textResult = ""

			if (checkbox_m1.isChecked) {
				checkboxValues += "\n\t\t" + checkbox_m1.text.toString()
				flag = 1
			}
			if (checkbox_m2.isChecked) {
				checkboxValues += "\n\t\t" + checkbox_m2.text.toString()
				flag = 1
			}
			if (checkbox_m3.isChecked) {
				checkboxValues += "\n\t\t" + checkbox_m3.text.toString()
				flag = 1
			}

			if (spinner.selectedItem.toString() == "") Toast.makeText(
				context, "Tableware doesn't checked", Toast.LENGTH_SHORT
			).show()
			else if (flag == 0) Toast.makeText(context, "Checkbox doesn't checked", Toast.LENGTH_SHORT).show()
			else {
				textResult =
					"Tableware: " + "\n\t\t" + spinner.selectedItem.toString() + "\nManufacturer:" + checkboxValues + "\n" + minvalue + " - " + maxvalue + " USD"

				var tableware = Tableware(
					spinner.selectedItem.toString(),
					checkboxValues,
					minvalue,
					maxvalue
				)
				var db = DatabaseHandler(requireContext())
				db.insertTableware(tableware)
			}
			fragmentFormDataListener?.onSendData(textResult)
		}

		return view
	}

	companion object {
		@JvmStatic
		fun newInstance(param1: String, param2: String) = FragmentForm()
	}
}