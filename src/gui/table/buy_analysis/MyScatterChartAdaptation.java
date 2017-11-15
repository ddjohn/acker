package gui.table.buy_analysis;

import java.awt.Color;
import java.text.ParseException;

import javax.xml.transform.TransformerException;

import log.MyInformationTable;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import util.Formulas;
import util.MyUtil;
import util.MyXmlUtil;

import graph.ScatterGraphInterface;

public class MyScatterChartAdaptation implements ScatterGraphInterface {
	private NodeList nodes = null;

	public double getY(int item) {
		Node respondNode = nodes.item(item).getChildNodes().item(0);
		NodeList nodes = respondNode.getChildNodes();

		try {
			double fibonacci = Formulas.fibonacci(nodes);

			return fibonacci;
		} 
		catch (ParseException e) {
			e.printStackTrace();
			return 0;
		}
	}

	public double getX(int item) {
		Node respondNode = nodes.item(item).getChildNodes().item(0);
		NodeList nodes = respondNode.getChildNodes();

		try {
			double current = MyUtil.parseN(getValue(nodes.item(nodes.getLength()-1), "cp")).doubleValue();

			// Get market trend
			double trend = Formulas.mean(nodes, 200);

			return 100.0 * (current - trend) / trend;
		} 
		catch (ParseException e) {
			e.printStackTrace();
			return 0;
		}
	}

	public String getName(int item) {
		Node respondNode = nodes.item(item).getChildNodes().item(0);
		return getValue(respondNode.getParentNode(), "name");
	}

	public Color getTextColor(int item) {
		Node respondNode = nodes.item(item).getChildNodes().item(0);
		NodeList nodes = respondNode.getChildNodes();

		try {
			double oscillator = Formulas.stochastic(nodes);

			if(oscillator >= 70) {
				return Color.red;
			}
			if(oscillator <= 30) {
				return Color.green;
			}
			return Color.black;
		} 
		catch (ParseException e) {
			e.printStackTrace();
			return Color.blue;
		}
	}

	public int getNumberOfItems() {
		if(nodes == null) {
			return 0;
		}

		if(nodes == null) {
			MyInformationTable.getInstance().debug("getItemCount 0");
			return 0;
		}
		else {
			MyInformationTable.getInstance().debug("getItemCount " + nodes.getLength());
			return nodes.getLength();
		}
	}

	public void set(Document doc) {
		Node root = doc.getChildNodes().item(0);
		this.nodes = root.getChildNodes();	
		try {
			MyInformationTable.getInstance().debug("xml=" + MyXmlUtil.documentToString(doc));
		} 
		catch (TransformerException e) {
			e.printStackTrace();
		}
	}

	public String getValue(Node node, String name) {
		return node.getAttributes().getNamedItem(name).getNodeValue();
	}

	public int getStyle(int item) {
		Node respondNode = nodes.item(item).getChildNodes().item(0);
		NodeList nodes = respondNode.getChildNodes();

		int style = ScatterGraphInterface.NONE;

		try {
			double low = MyUtil.parseN(getValue(nodes.item(nodes.getLength()-1), "lp")).doubleValue();
			double high = MyUtil.parseN(getValue(nodes.item(nodes.getLength()-1), "hp")).doubleValue();

			if(Formulas.singleRichochette(nodes)) {
				style |= ScatterGraphInterface.BIG;
			}
			if(Formulas.doubleRichochette(nodes)) {
				style |= ScatterGraphInterface.BIGGER;
			}
			if(high >= Formulas.donchianHigh(nodes, 20)) {
				style |= ScatterGraphInterface.HIGH;
			}
			if(low <= Formulas.donchianLow(nodes, 20)) {
				style |= ScatterGraphInterface.LOW;
			}
		} 
		catch (ParseException e1) {
			e1.printStackTrace();
		}
		return style;
	}
} 
