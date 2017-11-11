package se.avelon.edge.datasets.stocks;

import java.util.List;
import java.util.Vector;
import org.jfree.chart.renderer.category.BoxAndWhiskerRenderer;
import org.jfree.data.general.DatasetChangeListener;
import org.jfree.data.general.DatasetGroup;
import org.jfree.data.statistics.BoxAndWhiskerCategoryDataset;

import se.avelon.edge.omx.datafeed.StockData;
import se.avelon.edge.utilities.DajoLogger;

class StockData2 {
	StockData data;
	double price;
}

public class DajoStockDataset extends BoxAndWhiskerRenderer implements BoxAndWhiskerCategoryDataset {

	private static final long serialVersionUID = 1L;
	private static final DajoLogger log = DajoLogger.getLogger(DajoStockDataset.class);

	private final Vector<StockData> v = new Vector<StockData>();
	//private final Vector<StockData2> v2 = new Vector<StockData2>();

	public DajoStockDataset() {}

	public void add(StockData stock) {
		//doble price = Candle
		v.add(stock);		
	}

	public void remove(StockData stock) {
		v.remove(stock);		
	}

	public void addChangeListener(DatasetChangeListener listener) {}

	public DatasetGroup getGroup() {
		return null;
	}

	public void removeChangeListener(DatasetChangeListener listener) {}

	public void setGroup(DatasetGroup arg0) {}

	public int getColumnIndex(@SuppressWarnings("rawtypes") Comparable col) {
		log.fine("getColumnIndex()");
		return v.indexOf(col);
	}

	@SuppressWarnings("rawtypes")
	public Comparable getColumnKey(int col) {
		log.fine("getColumnKey()");

		//return "Col" + col;
		return v.get(col).toString();
	}

	public List<StockData> getColumnKeys() {
		log.fine("getColumnKeys()");
		return v;
	}

	public int getRowIndex(@SuppressWarnings("rawtypes") Comparable c) {	
		log.fine("getRowIndex()");
		return v.indexOf(c);
	}

	@SuppressWarnings("rawtypes")
	public Comparable getRowKey(int item) {
		log.fine("getRowKey()");

		return "My stocks";
	}

	public List<String> getRowKeys() {
		log.fine("getRowKeys()");
		List<String> l = new Vector<String>();
		l.add("test");
		return l;
	}

	public Number getValue(@SuppressWarnings("rawtypes") Comparable row, @SuppressWarnings("rawtypes") Comparable col) {
		// TODO Auto-generated method stub
		return 7;
	}

	public Number getValue(int row, int col) {
		// TODO Auto-generated method stub
		return 7;
	}

	public Number getMaxOutlier(int row, int col) {
		// TODO Auto-generated method stub
		return 9;
	}

	public Number getMaxOutlier(@SuppressWarnings("rawtypes") Comparable row, @SuppressWarnings("rawtypes") Comparable col) {
		// TODO Auto-generated method stub
		return 9;
	}

	public Number getMaxRegularValue(int row, int col) {
		// TODO Auto-generated method stub
		return 11;
	}

	public Number getMaxRegularValue(@SuppressWarnings("rawtypes") Comparable row, @SuppressWarnings("rawtypes") Comparable col) {
		// TODO Auto-generated method stub
		return 11;
	}

	public Number getMeanValue(int row, int col) {
		// TODO Auto-generated method stub
		return 7;
	}

	public Number getMeanValue(@SuppressWarnings("rawtypes") Comparable row, @SuppressWarnings("rawtypes") Comparable col) {
		// TODO Auto-generated method stub
		return 7;
	}

	public Number getMedianValue(int row, int col) {
		// TODO Auto-generated method stub
		return 8;
	}

	public Number getMedianValue(@SuppressWarnings("rawtypes") Comparable row, @SuppressWarnings("rawtypes") Comparable col) {
		// TODO Auto-generated method stub
		return 8;
	}

	public Number getMinOutlier(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return 6;
	}

	public Number getMinOutlier(@SuppressWarnings("rawtypes") Comparable arg0, @SuppressWarnings("rawtypes") Comparable arg1) {
		// TODO Auto-generated method stub
		return 6;
	}

	public Number getMinRegularValue(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return 6;
	}

	public Number getMinRegularValue(@SuppressWarnings("rawtypes") Comparable arg0, @SuppressWarnings("rawtypes") Comparable arg1) {
		// TODO Auto-generated method stub
		return 6;
	}

	public List<?> getOutliers(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<?> getOutliers(@SuppressWarnings("rawtypes") Comparable arg0, @SuppressWarnings("rawtypes") Comparable arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	public Number getQ1Value(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return 6;
	}

	public Number getQ1Value(@SuppressWarnings("rawtypes") Comparable arg0, @SuppressWarnings("rawtypes") Comparable arg1) {
		// TODO Auto-generated method stub
		return 6;
	}

	public Number getQ3Value(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return 6;
	}

	public Number getQ3Value(@SuppressWarnings("rawtypes") Comparable arg0, @SuppressWarnings("rawtypes") Comparable arg1) {
		// TODO Auto-generated method stub
		return 6;
	}

	public int getColumnCount() {
		return v.size();
	}

	public int getRowCount() {
		return 1;
	}
}
