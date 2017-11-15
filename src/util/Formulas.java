package util;

import info.MyInformationTable;

import java.text.ParseException;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Formulas {

	public static String getValue(Node node, String name) {
		return node.getAttributes().getNamedItem(name).getNodeValue();
	} 

	public static double mean(NodeList nodes, int days) {
		return Formulas.mean(nodes, days, nodes.getLength()-1);
	}

	public static double mean(NodeList nodes, int days, int id) {
		return Formulas.meanRecurse(nodes, days, id) / days;
	}

	public static double donchianLow(NodeList nodes, int days) {
		return Formulas.donchianLow(nodes, days, nodes.getLength()-1);
	}

	public static double donchianLow(NodeList nodes, int days, int id) {
		return Formulas.lowRecurse(nodes, days, id);
	}

	public static double donchianHigh(NodeList nodes, int days) {
		return  Formulas.donchianHigh(nodes, days, nodes.getLength()-1);
	}

	public static double donchianHigh(NodeList nodes, int days, int id) {
		return  Formulas.highRecurse(nodes, days, id);
	}

	public static double meanRecurse(NodeList nodes, int days, int id) {
		if(days > 0) {
			double recurse = Formulas.meanRecurse(nodes, days-1 , id);
			double cp = recurse / days;
			try {
				cp = MyUtil.parseN(Formulas.getValue(nodes.item(id-days+1), "cp")).doubleValue();
			} 
			catch (ParseException pe) {
				MyInformationTable.getInstance().warning("NOT SOLVED: mean: Data Missing for " + getValue(nodes.item(id-days+1), "dt"));
			} 

			return cp + recurse;
		}
		else {
			return 0.0;
		}
	}

	public static double lowRecurse(NodeList nodes, int days, int id) {
		if(days > 0) {
			double recurse = Formulas.lowRecurse(nodes, days-1 , id);
			double current = recurse; // Do not affect if we do not have a value
			try {
				current = MyUtil.parseN(Formulas.getValue(nodes.item(id-days+1), "lp")).doubleValue();
			} 
			catch (ParseException pe) {
				MyInformationTable.getInstance().warning("NOT SOLVED: mean: Data Missing for " + getValue(nodes.item(id-days+1), "dt"));
			} 
			return recurse > current ? current : recurse;
		}
		else {
			return 99999.0;
		}
	}

	public static double highRecurse(NodeList nodes, int days, int id) {
		if(days > 0) {
			double recurse = Formulas.highRecurse(nodes, days-1 , id);
			double current = recurse; // Do not affect if we do not have a value
			try {
				current = MyUtil.parseN(Formulas.getValue(nodes.item(id-days+1), "hp")).doubleValue();
			} 
			catch (ParseException pe) {
				MyInformationTable.getInstance().warning("NOT SOLVED: mean: Data Missing for " + getValue(nodes.item(id-days+1), "dt"));
			} 


			return recurse < current ? current : recurse;
		}
		else {
			return 0.0;
		}
	}

	static boolean richochette(NodeList nodes) throws ParseException {
		return Formulas.richochette(nodes, nodes.getLength()-1);
	}

	static boolean richochette(NodeList nodes, int id) throws ParseException {
		double high = MyUtil.parseN(Formulas.getValue(nodes.item(id), "hp")).doubleValue();
		double low = MyUtil.parseN(Formulas.getValue(nodes.item(id), "lp")).doubleValue();
		double close = MyUtil.parseN(Formulas.getValue(nodes.item(id), "cp")).doubleValue();

		return 100.0 * (close - low) / ( high - low) <= 10.0 ? true : false;
	}

	public static double stochastic(NodeList nodes) throws ParseException {
		return Formulas.stochastic(nodes, nodes.getLength()-1);
	}

	public static double stochastic(NodeList nodes, int id) throws ParseException {
		return (Formulas.stochasticTmp(nodes, id) + Formulas.stochasticTmp(nodes, id-1) + Formulas.stochasticTmp(nodes, id-2)) / 3;
	}

	public static double stochasticTmp(NodeList nodes, int id) throws ParseException {
		double close = MyUtil.parseN(Formulas.getValue(nodes.item(id), "cp")).doubleValue();
		return 100.0 * (close - Formulas.donchianLow(nodes, 5, id)) / (Formulas.donchianHigh(nodes, 5, id) - Formulas.donchianLow(nodes, 5, id));
	}

	public static double fibonacci(NodeList nodes) throws ParseException {
		return Formulas.fibonacci(nodes, nodes.getLength()-1);
	}

	public static double fibonacci(NodeList nodes, int id) throws ParseException {
		return (Formulas.mean(nodes, 55, id) - Formulas.mean(nodes, 144, id)) / Formulas.mean(nodes, 144, id);
	}

	public static boolean singleRichochette(NodeList nodes) throws ParseException {
		return Formulas.singleRichochette(nodes, nodes.getLength()-1);
	}

	public static boolean singleRichochette(NodeList nodes, int id) throws ParseException {
		double high = MyUtil.parseN(Formulas.getValue(nodes.item(id), "hp")).doubleValue();
		double low = MyUtil.parseN(Formulas.getValue(nodes.item(id), "lp")).doubleValue();
		double close = MyUtil.parseN(Formulas.getValue(nodes.item(id), "cp")).doubleValue();

		return 100.0 * (close - low) / (high - low) < 10 ? true : false;
	}

	public static boolean doubleRichochette(NodeList nodes) throws ParseException {
		return Formulas.doubleRichochette(nodes, nodes.getLength()-1);
	}

	public static boolean doubleRichochette(NodeList nodes, int id) throws ParseException {
		return Formulas.singleRichochette(nodes, id) && Formulas.singleRichochette(nodes, id-1) ? true : false;
	}
}