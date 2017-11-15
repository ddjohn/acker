package util;
import java.text.DecimalFormat;


public class ShortNumbers {


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(ShortNumbers.shorten("7.4"));
		System.out.println(ShortNumbers.shorten("70.4"));
		System.out.println(ShortNumbers.shorten("700.4"));
		System.out.println(ShortNumbers.shorten("7000.4"));
		System.out.println(ShortNumbers.shorten("70000.4"));
		System.out.println(ShortNumbers.shorten("700000.4"));
		System.out.println(ShortNumbers.shorten("7.4%"));
		System.out.println(ShortNumbers.shorten("70.4%"));
		System.out.println(ShortNumbers.shorten("700.4%"));
	}

	
	public static String shorten(String val) {		
		String value = val.replaceAll("[^\\x2E-\\x391]", "");
		String unit = val.replaceAll("[\\x2E-\\x39]", "");

		Double d = Double.parseDouble(value);
		if(d > 1000000) {
			return "" + ShortNumbers.format(d/1000000) + "m " + unit;
		}
		else if(d > 1000) {
			return "" +  ShortNumbers.format(d/1000) + "k " + unit;
		}
		else  {
			return ShortNumbers.format(d) + " " + unit;
		}		
	}

	private static String format(double format) {
		DecimalFormat oneDigit = new DecimalFormat("#,##0.0");
		DecimalFormat twoDigit = new DecimalFormat("#,##0.00");
		DecimalFormat threeDigit = new DecimalFormat("#,##0.000");
		
		if(format < 10) {
			return threeDigit.format(format);
		}
		else if(format < 100) {
			return twoDigit.format(format);
		}
		else {
			return oneDigit.format(format);
		}
	}
}
