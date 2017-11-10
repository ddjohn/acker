package net;

import info.MyInformationTable;
import java.net.URL;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import data.MyTimeData;
import util.MyUtil;
import util.MyXmlUtil;

public class OmxData {
	private static final String nasdaq = "http://www.nasdaqomxnordic.com/webproxy/DataFeedProxy.aspx?";

	public Document getStock(String lists) {
		final long startTime = System.nanoTime();

		try {
			URL url = new URL(nasdaq + 
					"SubSystem=Prices&" +
					"Action=GetMarket&" +
					"Exchange=NMF&" +
					"Inst.an=fnm,id&" +
					"Market=" + lists);
			MyInformationTable.getInstance().info("post=" + url);
			CharSequence chars = MyUtil.getURLContent(url);
			return MyXmlUtil.createDocumentFromString(chars.toString());
		}
		catch(Exception e)  {
			return null;
		}
		finally {
			final long endTime = System.nanoTime();
			MyInformationTable.getInstance().info("Collecting prices for " + lists + " in " +  (endTime - startTime)/1000/1000 + " ms");
		}
	}

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
			MyInformationTable.getInstance().info("post=" + url);

			CharSequence chars = MyUtil.getURLContent(url);
			return MyXmlUtil.createDocumentFromString(chars.toString());
		}
		catch(Exception e)  {
			return null;
		}
		finally {
			final long endTime = System.nanoTime();
			MyInformationTable.getInstance().info("Collecting data series for " + instrument + " in " +  (endTime - startTime)/1000/1000 + " ms");
		}
	}

	public static Document getMarkets() {
		final long startTime = System.nanoTime();

		try {
			URL url = new URL(nasdaq + 
					"SubSystem=Prices&" +
					"Action=GetInstrument&" +
					"Inst.a=1,2,37,20,21&" +
					"Instrument=SE0001809476,DX0000001376,SE0000337842,FI0008900212,IS0000018885");
			MyInformationTable.getInstance().info("post=" + url);

			CharSequence chars = MyUtil.getURLContent(url);
			return MyXmlUtil.createDocumentFromString(chars.toString());
		}
		catch(Exception e)  {
			return null;
		}
		finally {
			final long endTime = System.nanoTime();
			MyInformationTable.getInstance().info("Collecting data for markets in " +  (endTime - startTime)/1000/1000 + " ms");
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
			MyInformationTable.getInstance().info("post=" + url);

			CharSequence chars = MyUtil.getURLContent(url);
			return MyXmlUtil.createDocumentFromString(chars.toString());
		}
		catch(Exception e)  {
			return null;
		}
		finally {
			final long endTime = System.nanoTime();
			MyInformationTable.getInstance().info("Collecting data for winners in " +  (endTime - startTime)/1000/1000 + " ms");
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
			MyInformationTable.getInstance().info("post=" + url);

			CharSequence chars = MyUtil.getURLContent(url);
			return MyXmlUtil.createDocumentFromString(chars.toString());
		}
		catch(Exception e)  {
			return null;
		}
		finally {
			final long endTime = System.nanoTime();
			MyInformationTable.getInstance().info("Collecting data for loosers in " +  (endTime - startTime)/1000/1000 + " ms");
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
			MyInformationTable.getInstance().info("post=" + url);

			CharSequence chars = MyUtil.getURLContent(url);
			return MyXmlUtil.createDocumentFromString(chars.toString());
		}
		catch(Exception e)  {
			return null;
		}
		finally {
			final long endTime = System.nanoTime();
			MyInformationTable.getInstance().info("Collecting data for highest turnover in " +  (endTime - startTime)/1000/1000 + " ms");
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
			MyInformationTable.getInstance().info("post=" + url);

			CharSequence chars = MyUtil.getURLContent(url);
			return MyXmlUtil.createDocumentFromString(chars.toString());
		}
		catch(Exception e)  {
			return null;
		}
		finally {
			final long endTime = System.nanoTime();
			MyInformationTable.getInstance().info("Collecting data for most traded in " +  (endTime - startTime)/1000/1000 + " ms");
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
			MyInformationTable.getInstance().info("post=" + url);

			CharSequence chars = MyUtil.getURLContent(url);
			Document doc = MyXmlUtil.createDocumentFromString(chars.toString());
			MyInformationTable.getInstance().info("got=" + MyXmlUtil.documentToString(doc));
			//return Double.parseDouble(doc.getChildNodes().item(0).getAttributes().getNamedItem("lsp").getNodeValue());
			return MyUtil.parseN(getValue(doc.getChildNodes().item(0).getChildNodes().item(0), "lsp")).doubleValue();
		}
		catch(Exception e)  {
			return 0.0;
		}
		finally {
			final long endTime = System.nanoTime();
			MyInformationTable.getInstance().info("Collecting data for markets in " +  (endTime - startTime)/1000/1000 + " ms");
		}
	}
}
