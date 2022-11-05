package com.fronchak.models.entities;

public class Calculator {

	public Double sum(Double numberOne, Double numberTwo) {
		return numberOne + numberTwo;
	}
	
	public Double subtraction(Double numberOne, Double numberTwo) {
		return numberOne - numberTwo;
	}
	
	public Double multiply(Double numberOne, Double numberTwo) {
		return numberOne * numberTwo;
	}
	
	public Double division(Double numberOne, Double numberTwo) {
		return numberOne / numberTwo;
	}
	
	public Double average(Double numberOne, Double numberTwo) {
		return sum(numberOne, numberTwo) / 2;
	}
	
	public Double sqrt(Double number) {
		return Math.sqrt(number);
	}
}
