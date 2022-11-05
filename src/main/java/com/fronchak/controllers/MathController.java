package com.fronchak.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fronchak.models.services.MathService;

@RestController
public class MathController {

	private MathService service;
	
	public MathController() {
		this.service = new MathService();
	}
	
	@RequestMapping(value = "/sum/{numberOne}/{numberTwo}", method=RequestMethod.GET)
	public Double sum(
			@PathVariable(value = "numberOne") String numberOne, 
			@PathVariable(value = "numberTwo") String numberTwo 			
		) {
		return service.sum(numberOne, numberTwo);
	}
	
	@RequestMapping(value = "subtraction/{numberOne}/{numberTwo}", method=RequestMethod.GET)
	public Double subtraction(
			@PathVariable(value = "numberOne") String numberOne,
			@PathVariable(value = "numberTwo") String numberTwo
			) {
		return service.subtraction(numberOne, numberTwo);
	}
	
	@RequestMapping(value = "multiply/{numberOne}/{numberTwo}", method=RequestMethod.GET)
	public Double multiply(
			@PathVariable(value = "numberOne") String numberOne,
			@PathVariable(value = "numberTwo") String numberTwo
			) {
		return service.multiply(numberOne, numberTwo);
	}
	
	@RequestMapping(value = "division/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double division(
			@PathVariable(value = "numberOne") String numberOne,
			@PathVariable(value = "numberTwo") String numberTwo
			) {
		return service.division(numberOne, numberTwo);
	}
	
	@RequestMapping(value = "average/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double average(
			@PathVariable(value = "numberOne") String numberOne,
			@PathVariable(value = "numberTwo") String numberTwo
			) {
		return service.average(numberOne, numberTwo);
	}
	
	@RequestMapping(value = "sqrt/{numberOne}", method = RequestMethod.GET)
	public Double sqrt(@PathVariable(value = "numberOne") String numberOne) {
		return service.sqrt(numberOne);
	}
}
