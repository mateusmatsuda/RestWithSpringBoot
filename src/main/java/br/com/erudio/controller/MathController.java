package br.com.erudio.controller;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.erudio.exception.DivisionByZero;
import br.com.erudio.exception.ResourceNotFoundException;
import br.com.erudio.math.SimpleMath;

@RestController
public class MathController {
	
	private SimpleMath math = new SimpleMath();

	
	@RequestMapping(value="/sum/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double soma(@PathVariable("numberOne") String numberOne,@PathVariable("numberTwo") String numberTwo) 
			throws Exception {
		
		if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
			throw new ResourceNotFoundException("Please, set a numeric value");
		}
		
		Double sum = math.sum(convertToDouble(numberOne), convertToDouble(numberTwo));
		
		return sum;		
	}
	
	@RequestMapping(value="/subtraction/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double subtracao (@PathVariable("numberOne") String numberOne,@PathVariable("numberTwo") String numberTwo) 
			throws Exception{
		
		if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
			throw new ResourceNotFoundException("Please, set a numeric value");
		}
		
		Double sub = math.subtraction(convertToDouble(numberOne), convertToDouble(numberTwo));
		
		return sub;
	}
	
	@RequestMapping(value="/multiplication/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double multiplicacao (@PathVariable("numberOne") String numberOne,@PathVariable("numberTwo") String numberTwo) 
			throws Exception{
		
		if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
			throw new ResourceNotFoundException("Please, set a numeric value");
		}
		
		Double mult = math.multiplication(convertToDouble(numberOne) , convertToDouble(numberTwo));
		
		return mult;
	}

	
	@RequestMapping(value="/division/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double divisao (@PathVariable("numberOne") String numberOne,@PathVariable("numberTwo") String numberTwo) 
			throws Exception{
		
		if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
			throw new ResourceNotFoundException("Please, set a numeric value");
		}
		
		
		if(convertToDouble(numberTwo) == 0) {
			throw new DivisionByZero("Cannot divide by zero");
			
		}
		
		Double div = math.division(convertToDouble(numberOne) , convertToDouble(numberTwo));
		
		return div;
	}
	
	
	
    public static Double convertToDouble(String strNumber) {
        if (strNumber == null) return 0d; 
        String number = strNumber.replaceAll(",", ".");
        if (isNumeric(number)) return Double.parseDouble(number);
        return 0d;
    }

    public static boolean isNumeric(String strNumber) {
        if (strNumber == null) return false; 
        String number = strNumber.replaceAll(",", ".");
        return number.matches("[-+]?[0-9]*\\.?[0-9]+");
    }

}