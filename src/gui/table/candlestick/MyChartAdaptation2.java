package gui.table.candlestick;

import java.util.Date;
import java.util.Vector;
import javax.xml.xpath.XPathExpressionException;
import datafeeders.CandleFeedData;
import graph.CandleGraphInterface;

public class MyChartAdaptation2 implements CandleGraphInterface {
	private Vector<CandleFeedData> data = null;

	public double getY(int item) {
		return data.elementAt(item).closingPrice;
	}

	public Date getX(int item) {
		return data.elementAt(item).date;
	}

	public void set(Vector<CandleFeedData> data) throws XPathExpressionException {
		this.data = data;
	}

	public int getNumberOfItems() {
		if(data != null) {
			return data.size();
		}
		else {
			return 0;
		}
	}

	public double getOpeningPrice(int item) {
		return data.elementAt(item).openingPrice;
	}

	public double getClosingPrice(int item) {
		return data.elementAt(item).closingPrice;
	}

	public double getLowestPrice(int item) {
		return data.elementAt(item).lowestPrice;
	}

	public double getHighestPrice(int item) {
		return data.elementAt(item).highestPrice;
	}

	public double getVolume(int item) {
		return data.elementAt(item).volume;
	}
}
