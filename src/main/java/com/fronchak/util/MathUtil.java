package com.fronchak.util;

public class MathUtil {

	public static boolean isNumeric(Object obj) {
		if(obj == null) return false;
		String number = obj.toString().replaceAll(",", ".");
		return number.matches("[+-]?[0-9]*\\.?[0-9]+");
	}
	
	public static Double converToDouble(Object obj) {
		String number = obj.toString().replaceAll(",", ".");
		return Double.parseDouble(number);
	}
	
}
