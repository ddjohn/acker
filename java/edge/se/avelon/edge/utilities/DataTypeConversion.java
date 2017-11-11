package se.avelon.edge.utilities;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;



public class DataTypeConversion {
	private static final DajoLogger log = DajoLogger.getLogger(DataTypeConversion.class);
	
	private static final DecimalFormatSymbols symbols = new DecimalFormatSymbols();
	private static final DecimalFormat df = new DecimalFormat();
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
	{
		symbols.setDecimalSeparator('.');
		symbols.setGroupingSeparator(',');
		df.setDecimalFormatSymbols(symbols);
	}

	public static Date toDate(String number) {
		try {
			return sdf.parse(number);
		} 
		catch (ParseException e) {
			log.severe("Could not parse: " + e.getMessage());
			return null;
		}
	}

	public static double toDouble(String number) {
		try {
			return df.parse(number).doubleValue();
		} 
		catch (ParseException e) {
			log.severe("Could not parse: " + e.getMessage());
			return Double.NaN;
		}
	}

/*	public static Number parse(String number) {
		return df.parse(number);
	}
*/	
/*	public static Number parse(String number, Number def) {
		try {
			return df.parse(number);
		} 
		catch (ParseException e) {
			return def;
		}
	}*/
}
