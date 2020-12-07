package br.com.erudio;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MathController {
	
	private static final String template = "Hello, %s!";
	
	private final AtomicLong counter = new AtomicLong();
	
	@RequestMapping(value="/sum/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double greeting(@PathVariable("numberOne") String numberOne,@PathVariable("numberTwo") String numberTwo) throws Exception {
		
		if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
			throw new Exception();
		}
		
		Double sum = convertToDouble(numberOne) + convertToDouble(numberTwo);
		
		return sum;		
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