package com.fronchak.models.services;

import com.fronchak.exceptions.UnsupportedMathOperationException;
import com.fronchak.models.entities.Calculator;
import static com.fronchak.util.MathUtil.*;

public class MathService {

	private final Calculator calculator;
	
	public MathService() {
		this.calculator = new Calculator();
	}
	
	public Double sum(String numberOne, String numberTwo) {
		validadeParametros(numberOne, numberTwo);
		return calculator.sum(converToDouble(numberOne), converToDouble(numberTwo));
	}
	
	private void validadeParametros(String... values) {
		for (String value : values) {
			if (!isNumeric(value)) {
				throw new UnsupportedMathOperationException("Please set a numeric value");
			}
		}
	}
	
	public Double subtraction(String numberOne, String numberTwo) {
		validadeParametros(numberOne, numberTwo);
		return calculator.subtraction(converToDouble(numberOne), converToDouble(numberTwo));
	}
	
	public Double multiply(String numberOne, String numberTwo) {
		validadeParametros(numberOne, numberTwo);
		return calculator.multiply(converToDouble(numberOne), converToDouble(numberTwo));
	}
	
	public Double division(String numberOne, String numberTwo) {
		validadeParametros(numberOne, numberTwo);
		Double numericNumberTwo = converToDouble(numberTwo);
		if (numericNumberTwo.equals(0.0)) {
			throw new UnsupportedMathOperationException("Denominador cannot be zero.");
		}
		return calculator.division(converToDouble(numberOne), converToDouble(numberTwo));
	}
	
	public Double average(String numberOne, String numberTwo) {
		validadeParametros(numberOne, numberTwo);
		return calculator.average(converToDouble(numberOne), converToDouble(numberTwo));
	}
	
	public Double sqrt(String number) {
		validadeParametros(number);
		return calculator.sqrt(converToDouble(number));
	}
}
