package com.example.lab3.entities

class Tableware {
	var id: Int = 0
	var tableware: String = ""
	var manufacturer: String = ""
	var minvalue: Double = 0.0
	var maxvalue: Double = 0.0

	constructor()

	constructor(
		tableware: String, manufacturer: String, minvalue: Double, maxvalue: Double
	) {
		this.tableware = tableware
		this.manufacturer = manufacturer
		this.minvalue = minvalue
		this.maxvalue = maxvalue
	}
}