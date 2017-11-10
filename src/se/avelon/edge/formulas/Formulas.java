package se.avelon.edge.formulas;

import java.text.ParseException;
import java.util.Vector;
import datafeeders.CandleFeedData;

public class Formulas {

//	private static final DajoLogger log = DajoLogger.getLogger(Formulas.class);

	public static double donchianLow(Vector<CandleFeedData> data, int days) {
		return Formulas.donchianLow(data, days, data.size()-1);
	}

	public static double donchianLow(Vector<CandleFeedData> data, int days, int id) {
		return Formulas.lowRecurse(data, days, id);
	}

	public static double donchianHigh(Vector<CandleFeedData> data, int days) {
		return  Formulas.donchianHigh(data, days, data.size()-1);
	}

	public static double donchianHigh(Vector<CandleFeedData> data, int days, int id) {
		return  Formulas.highRecurse(data, days, id);
	}

	private static double lowRecurse(Vector<CandleFeedData> data, int days, int id) {
		if(days > 0) {
			double recurse = Formulas.lowRecurse(data, days-1 , id);
			double current = recurse; // Do not affect if we do not have a value

			current = data.get(id-days+1).lowestPrice;

			return recurse > current ? current : recurse;
		}
		else {
			return 99999.0;
		}
	}

	private static double highRecurse(Vector<CandleFeedData> data, int days, int id) {
		if(days > 0) {
			double recurse = Formulas.highRecurse(data, days-1 , id);
			double current = recurse; // Do not affect if we do not have a value

			current = data.get(id-days+1).highestPrice;
			return recurse < current ? current : recurse;
		}
		else {
			return 0.0;
		}
	}

//	private static boolean richochette(Vector<CandleFeedData> data) throws ParseException {
//		return Formulas.richochette(data, data.size()-1);
//	}

	public static boolean richochette(Vector<CandleFeedData> data, int id) throws ParseException {
		double high = data.get(id).highestPrice;
		double low = data.get(id).lowestPrice;
		double close = data.get(id).closingPrice;

		return 100.0 * (close - low) / ( high - low) <= 10.0 ? true : false;
	}

	public static double stochastic(Vector<CandleFeedData> data) throws ParseException {
		return Formulas.stochastic(data, data.size()-1);
	}

	public static double stochastic(Vector<CandleFeedData> data, int id) throws ParseException {
		return (Formulas.stochasticTmp(data, id) + Formulas.stochasticTmp(data, id-1) + Formulas.stochasticTmp(data, id-2)) / 3;
	}

	public static double stochasticTmp(Vector<CandleFeedData> data, int id) throws ParseException {
		double close = data.get(id).closingPrice;
		return 100.0 * (close - Formulas.donchianLow(data, 5, id)) / (Formulas.donchianHigh(data, 5, id) - Formulas.donchianLow(data, 5, id));
	}

	public static boolean singleRichochette(Vector<CandleFeedData> data) throws ParseException {
		return Formulas.singleRichochette(data, data.size()-1);
	}

	private static boolean singleRichochette(Vector<CandleFeedData> data, int id) throws ParseException {
		double high = data.get(id).highestPrice;
		double low = data.get(id).lowestPrice;
		double close = data.get(id).closingPrice;

		return 100.0 * (close - low) / (high - low) < 10 ? true : false;
	}

	public static boolean doubleRichochette(Vector<CandleFeedData> data) throws ParseException {
		return Formulas.doubleRichochette(data, data.size()-1);
	}

	private static boolean doubleRichochette(Vector<CandleFeedData> data, int id) throws ParseException {
		return Formulas.singleRichochette(data, id) && Formulas.singleRichochette(data, id-1) ? true : false;
	}

	public static double fibonacci(Vector<CandleFeedData> data) {
		return Formulas.fibonacci(data, data.size()-1);
	}

	public static double fibonacci(Vector<CandleFeedData> data, int id) {
		return (Formulas.mean(data, 55, id) - Formulas.mean(data, 144, id)) / Formulas.mean(data, 144, id);
	}

	public static double mean(Vector<CandleFeedData> data, int days) {
		return Formulas.mean(data, days, data.size()-1);
	}

	public static double mean(Vector<CandleFeedData> data, int days, int id) {
		return Formulas.meanRecurse(data, days, id) / days;
	}

	private static double meanRecurse(Vector<CandleFeedData> data, int days, int id) {
		if(days > 0) {
			double recurse = Formulas.meanRecurse(data, days-1, id);
			double cp = recurse / days;

			cp = data.get(id-days+1).closingPrice;

			return cp + recurse;
		}
		else {
			return 0.0;
		}
	}
}