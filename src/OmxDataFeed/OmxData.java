package OmxDataFeed;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import other.MyUtil;
import se.avelon.utilities.DajoLogger;
import xml.MyXmlUtil;

public class OmxData {
	private static final DajoLogger log = DajoLogger.getLogger(OmxCandleFeedAdapter.class);

	private static final String nasdaq = "http://www.nasdaqomxnordic.com/webproxy/DataFeedProxy.aspx?";
	static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	public static Document getDailyData(String instrument, Date fromDate, Date toDate) {
		final long startTime = System.nanoTime();

		try {
			URL url = new URL(nasdaq + 
					"SubSystem=History&" +
					"Action=GetDataSeries&" +
					"Hi.a=0,1,2,3,4,8,30,31&" +
					"Instrument=" + instrument + "&" +
					"AppendIntraDay=yes&" +
					"FromDate=" +  dateFormat.format(fromDate) + "&" +  
					"ToDate=" +  dateFormat.format(toDate));
			log.info("post=" + url);

			CharSequence chars = MyUtil.getURLContent(url);
			return MyXmlUtil.createDocumentFromString(chars.toString());
		}
		catch(Exception e)  {
			return null;
		}
		finally {
			final long endTime = System.nanoTime();
			log.info("Collecting data series for " + instrument + " in " +  (endTime - startTime)/1000/1000 + " ms");
		}
	}
	
	public static Document getStock(String[] markets) {
		final long startTime = System.nanoTime();

		String tmp = "";
		for(int i = 0; i < markets.length; i++) {
			if(i == 0) {
				tmp += markets[i];
			}
			else {
				tmp += "," + markets[i];
			}
		}
		
		try {
			URL url = new URL(nasdaq + 
					"SubSystem=Prices&" +
					"Action=GetMarket&" +
					"Exchange=NMF&" +
					"Inst.an=fnm,id&" +
					"Market=" + tmp);
			log.info("post=" + url);
			CharSequence chars = MyUtil.getURLContent(url);
			return MyXmlUtil.createDocumentFromString(chars.toString());
		}
		catch(Exception e)  {
			return null;
		}
		finally {
			final long endTime = System.nanoTime();
			log.info("Collecting prices for " + tmp + " in " +  (endTime - startTime)/1000/1000 + " ms");
		}
	}
/*
	public static Document getStockData(MyTimeData time, String instrument) {
		final long startTime = System.nanoTime();

		try {
			String dateString = MyUtil.getOldDate(time.type, time.value);
			URL url = new URL(nasdaq + 
					"SubSystem=History&" +
					"Action=GetDataSeries&" +
					//"Inst.an=0,1,2,3,4,5,114&" +
					"Instrument=" + instrument + "&" +
					"AppendIntraDay=yes&" +
					"FromDate=" + dateString + "&" +  
					"ToDate=" + MyUtil.getTodaysDate());
			log.info("post=" + url);

			CharSequence chars = MyUtil.getURLContent(url);
			return MyXmlUtil.createDocumentFromString(chars.toString());
		}
		catch(Exception e)  {
			return null;
		}
		finally {
			final long endTime = System.nanoTime();
			log.info("Collecting data series for " + instrument + " in " +  (endTime - startTime)/1000/1000 + " ms");
		}
	}
*/
	public static Document getMarkets() {
		final long startTime = System.nanoTime();

		try {
			URL url = new URL(nasdaq + 
					"SubSystem=Prices&" +
					"Action=GetInstrument&" +
					"Inst.a=1,2,37,20,21&" +
					"Instrument=SE0001809476,DX0000001376,SE0000337842,FI0008900212,IS0000018885");
			log.info("post=" + url);

			CharSequence chars = MyUtil.getURLContent(url);
			return MyXmlUtil.createDocumentFromString(chars.toString());
		}
		catch(Exception e)  {
			return null;
		}
		finally {
			final long endTime = System.nanoTime();
			log.info("Collecting data for markets in " +  (endTime - startTime)/1000/1000 + " ms");
		}
	}

	public static Document getWinners(int max) {
		final long startTime = System.nanoTime();

		try {
			URL url = new URL(nasdaq + 
					"SubSystem=Prices&" +
					"Action=Winners&" +
					"Max=" + max + "&" +
					//"Inst.a=1,2,37,20,21&" +
					"Market=L:10214");
			log.info("post=" + url);

			CharSequence chars = MyUtil.getURLContent(url);
			return MyXmlUtil.createDocumentFromString(chars.toString());
		}
		catch(Exception e)  {
			return null;
		}
		finally {
			final long endTime = System.nanoTime();
			log.info("Collecting data for winners in " +  (endTime - startTime)/1000/1000 + " ms");
		}
	}

	public static Document getLoosers(int max) {
		final long startTime = System.nanoTime();

		try {
			URL url = new URL(nasdaq + 
					"SubSystem=Prices&" +
					"Action=Losers&" +
					"Max=" + max + "&" +
					//"Inst.a=1,2,37,20,21&" +
					"Market=L:10214");
			log.info("post=" + url);

			CharSequence chars = MyUtil.getURLContent(url);
			return MyXmlUtil.createDocumentFromString(chars.toString());
		}
		catch(Exception e)  {
			return null;
		}
		finally {
			final long endTime = System.nanoTime();
			log.info("Collecting data for loosers in " +  (endTime - startTime)/1000/1000 + " ms");
		}
	}
	
	public static Document getHighestTurnover(int max) {
		final long startTime = System.nanoTime();

		try {
			URL url = new URL(nasdaq + 
					"SubSystem=Prices&" +
					"Action=GetHighestTurnover&" +
					"Max=" + max + "&" +
					//"Inst.a=1,2,37,20,21&" +
					"Market=L:10214");
			log.info("post=" + url);

			CharSequence chars = MyUtil.getURLContent(url);
			return MyXmlUtil.createDocumentFromString(chars.toString());
		}
		catch(Exception e)  {
			return null;
		}
		finally {
			final long endTime = System.nanoTime();
			log.info("Collecting data for highest turnover in " +  (endTime - startTime)/1000/1000 + " ms");
		}
	}

	public static Document getMostTraded(int max) {
		final long startTime = System.nanoTime();

		try {
			URL url = new URL(nasdaq + 
					"SubSystem=Prices&" +
					"Action=GetMostTraded&" +
					"Max=" + max + "&" +
					//"Inst.a=1,2,37,20,21&" +
					"Market=L:10214");
			log.info("post=" + url);

			CharSequence chars = MyUtil.getURLContent(url);
			return MyXmlUtil.createDocumentFromString(chars.toString());
		}
		catch(Exception e)  {
			return null;
		}
		finally {
			final long endTime = System.nanoTime();
			log.info("Collecting data for most traded in " +  (endTime - startTime)/1000/1000 + " ms");
		}
	}

	public static String getValue(Node node, String name) {
		return node.getAttributes().getNamedItem(name).getNodeValue();
	} 

	public static double getLatestPrice(String item) {
		final long startTime = System.nanoTime();

		try {
			URL url = new URL(nasdaq + 
					"SubSystem=Prices&" +
					"Action=GetInstrument&" +
					"Inst.an=lsp&" +
					"Instrument=" + item);
			log.info("post=" + url);

			CharSequence chars = MyUtil.getURLContent(url);
			Document doc = MyXmlUtil.createDocumentFromString(chars.toString());
			log.info("got=" + MyXmlUtil.documentToString(doc));
			//return Double.parseDouble(doc.getChildNodes().item(0).getAttributes().getNamedItem("lsp").getNodeValue());
			return MyUtil.parseN(getValue(doc.getChildNodes().item(0).getChildNodes().item(0), "lsp")).doubleValue();
		}
		catch(Exception e)  {
			return 0.0;
		}
		finally {
			final long endTime = System.nanoTime();
			log.info("Collecting data for markets in " +  (endTime - startTime)/1000/1000 + " ms");
		}
	}
	
}


/*
 
 
http://www.nasdaqomxnordic.com/webproxy/DataFeedProxy.aspx?SubSystem=History&Action=GetDataSeries&Hi.a=0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58&Instrument=SSE361&AppendIntraDay=yes&FromDate=2011-11-24&ToDate=2012-11-24

*/
