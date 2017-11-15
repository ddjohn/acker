package gui.table.candlestick;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;

import log.MyInformationTable;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import datafeeders.CandleFeedData;
import util.MyUtil;
import util.MyXmlUtil;
import graph.TimeGraphInterface;

public class MyChartAdaptation implements TimeGraphInterface {
	private NodeList nodes = null;

	public double getY(int item) {
		try {
			return MyUtil.parseN(getValue(nodes.item(item), "cp")).doubleValue();
		} 
		catch (ParseException e) {
			e.printStackTrace();
			return 0;
		}
	}

	public Date getX(int item) {
		try {
			String str = getValue(nodes.item(item), "dt");
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date date = (Date)formatter.parse(str);
			return date;
		} 
		catch (ParseException e) {
			e.printStackTrace();
			return new Date();
		}
	}

	public void set(Document doc) throws XPathExpressionException {
		nodes = MyXmlUtil.xpathSearch(doc, "//response/hi");
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
}
