package OmxDataFeed;

import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
import javax.xml.transform.TransformerException;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import convert.DataTypeConversion;
import se.avelon.utilities.DajoLogger;
import time.DateUtil;
import xml.MyXmlUtil;
import datafeeders.CandleFeedData;
import datafeeders.CandleFeedInterface;
import datafeeders.StockData;

/*
 * Example:
 * http://www.nasdaqomxnordic.com/webproxy/DataFeedProxy.aspx?SubSystem=Prices&Action=GetMarket&Exchange=NMF&Inst.an=fnm,id&Market=L:10208,L:10210,L:10212
 * http://www.nasdaqomxnordic.com/webproxy/DataFeedProxy.aspx?SubSystem=Prices&Action=Winners&Max=5&Market=L:10214
 * http://www.nasdaqomxnordic.com/webproxy/DataFeedProxy.aspx?SubSystem=Prices&Action=GetMarket&Exchange=NMF&Inst.an=fnm,id&Market=L:10208,L:10210,L:10212
 */
public class OmxCandleFeedAdapter implements CandleFeedInterface {
	private static final DajoLogger log = DajoLogger.getLogger(OmxCandleFeedAdapter.class);

	public Vector<CandleFeedData> getData(String instrument, int calendarType, int calendarValue) {
		try {
			Vector<CandleFeedData> v = new Vector<CandleFeedData>();

			Date fromDate = DateUtil.daysAgo(calendarType, calendarValue);
	        Date toDate = DateUtil.today();

			Document doc = OmxData.getDailyData(instrument, fromDate, toDate);
			log.fine("xml=" + MyXmlUtil.documentToString(doc));

			NodeList nodes = doc.getDocumentElement().getChildNodes();
			for(int i = 0; i < nodes.getLength(); i++) {
				Node node = nodes.item(i);

				Date dt = DataTypeConversion.toDate(node.getAttributes().getNamedItem("dt").getNodeValue());
				double op = DataTypeConversion.toDouble(node.getAttributes().getNamedItem("op").getNodeValue());
				double cp = DataTypeConversion.toDouble(node.getAttributes().getNamedItem("cp").getNodeValue());
				double hp = DataTypeConversion.toDouble(node.getAttributes().getNamedItem("hp").getNodeValue());
				double lp = DataTypeConversion.toDouble(node.getAttributes().getNamedItem("lp").getNodeValue());
				double tv = DataTypeConversion.toDouble(node.getAttributes().getNamedItem("tv").getNodeValue());
				String ins = node.getAttributes().getNamedItem("ins").getNodeValue();
				String insnm = node.getAttributes().getNamedItem("insnm").getNodeValue();

				v.add(new CandleFeedData(dt, op, cp, hp, lp, tv, ins, insnm));
			}
			return v;
		} 
		catch (DOMException e) {
			System.out.println("a");
			e.printStackTrace();
		} 
		catch (TransformerException e) {
			System.out.println("b");
			e.printStackTrace();
		} 
		return null;
	}

	public Vector<StockData> getStocks(String[] stocks) {
		try {
			Vector<StockData> v = new Vector<StockData>();

			String[] markets = {CandleFeedInterface.LARGE_CAP, CandleFeedInterface.MID_CAP, CandleFeedInterface.SMALL_CAP};

			Document doc = OmxData.getStock(markets);
			log.fine("xml=" + MyXmlUtil.documentToString(doc));

			NodeList nodes = doc.getDocumentElement().getElementsByTagName("inst");
			for(int i = 0; i < nodes.getLength(); i++) {
				Node node = nodes.item(i);

				v.add(new StockData(
						node.getAttributes().getNamedItem("fnm").getNodeValue(),
						node.getParentNode().getParentNode().getAttributes().getNamedItem("nm").getNodeValue(), 
						node.getAttributes().getNamedItem("id").getNodeValue())
						);
			}
			return v;
		} 
		catch (DOMException e) {
			e.printStackTrace();
		} 
		catch (TransformerException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Vector<CandleFeedData> getData(String instrument, Date fromDate) {
		try {
			Vector<CandleFeedData> v = new Vector<CandleFeedData>();

	        Date toDate = DateUtil.today();

			Document doc = OmxData.getDailyData(instrument, fromDate, toDate);
			log.fine("xml=" + MyXmlUtil.documentToString(doc));

			NodeList nodes = doc.getDocumentElement().getChildNodes();
			for(int i = 0; i < nodes.getLength(); i++) {
				Node node = nodes.item(i);

				Date dt = DataTypeConversion.toDate(node.getAttributes().getNamedItem("dt").getNodeValue());
				double op = DataTypeConversion.toDouble(node.getAttributes().getNamedItem("op").getNodeValue());
				double cp = DataTypeConversion.toDouble(node.getAttributes().getNamedItem("cp").getNodeValue());
				double hp = DataTypeConversion.toDouble(node.getAttributes().getNamedItem("hp").getNodeValue());
				double lp = DataTypeConversion.toDouble(node.getAttributes().getNamedItem("lp").getNodeValue());
				double tv = DataTypeConversion.toDouble(node.getAttributes().getNamedItem("tv").getNodeValue());
				String ins = node.getAttributes().getNamedItem("ins").getNodeValue();
				String insnm = node.getAttributes().getNamedItem("insnm").getNodeValue();

				v.add(new CandleFeedData(dt, op, cp, hp, lp, tv, ins, insnm));
			}
			return v;
		} 
		catch (DOMException e) {
			System.out.println("a");
			e.printStackTrace();
		} 
		catch (TransformerException e) {
			System.out.println("b");
			e.printStackTrace();
		} 
		return null;
	}
}
