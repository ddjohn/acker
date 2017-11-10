package se.avelon.edge.datasets.stocks;

import java.util.List;
import java.util.Vector;
import org.jfree.chart.renderer.category.BoxAndWhiskerRenderer;
import org.jfree.data.general.DatasetChangeListener;
import org.jfree.data.general.DatasetGroup;
import org.jfree.data.statistics.BoxAndWhiskerCategoryDataset;
import se.avelon.utilities.DajoLogger;
import datafeeders.StockData;

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

	@Override
	public void addChangeListener(DatasetChangeListener listener) {}

	@Override
	public DatasetGroup getGroup() {
		return null;
	}

	@Override
	public void removeChangeListener(DatasetChangeListener listener) {}

	@Override
	public void setGroup(DatasetGroup arg0) {}

	@Override
	public int getColumnIndex(@SuppressWarnings("rawtypes") Comparable col) {
		log.fine("getColumnIndex()");
		return v.indexOf(col);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Comparable getColumnKey(int col) {
		log.fine("getColumnKey()");

		//return "Col" + col;
		return v.get(col).toString();
	}

	@Override
	public List<StockData> getColumnKeys() {
		log.fine("getColumnKeys()");
		return v;
	}

	@Override
	public int getRowIndex(@SuppressWarnings("rawtypes") Comparable c) {	
		log.fine("getRowIndex()");
		return v.indexOf(c);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Comparable getRowKey(int item) {
		log.fine("getRowKey()");

		return "My stocks";
	}

	@Override
	public List<String> getRowKeys() {
		log.fine("getRowKeys()");
		List<String> l = new Vector<String>();
		l.add("test");
		return l;
	}

	@Override
	public Number getValue(@SuppressWarnings("rawtypes") Comparable row, @SuppressWarnings("rawtypes") Comparable col) {
		// TODO Auto-generated method stub
		return 7;
	}

	@Override
	public Number getValue(int row, int col) {
		// TODO Auto-generated method stub
		return 7;
	}

	@Override
	public Number getMaxOutlier(int row, int col) {
		// TODO Auto-generated method stub
		return 9;
	}

	@Override
	public Number getMaxOutlier(@SuppressWarnings("rawtypes") Comparable row, @SuppressWarnings("rawtypes") Comparable col) {
		// TODO Auto-generated method stub
		return 9;
	}

	@Override
	public Number getMaxRegularValue(int row, int col) {
		// TODO Auto-generated method stub
		return 11;
	}

	@Override
	public Number getMaxRegularValue(@SuppressWarnings("rawtypes") Comparable row, @SuppressWarnings("rawtypes") Comparable col) {
		// TODO Auto-generated method stub
		return 11;
	}

	@Override
	public Number getMeanValue(int row, int col) {
		// TODO Auto-generated method stub
		return 7;
	}

	@Override
	public Number getMeanValue(@SuppressWarnings("rawtypes") Comparable row, @SuppressWarnings("rawtypes") Comparable col) {
		// TODO Auto-generated method stub
		return 7;
	}

	@Override
	public Number getMedianValue(int row, int col) {
		// TODO Auto-generated method stub
		return 8;
	}

	@Override
	public Number getMedianValue(@SuppressWarnings("rawtypes") Comparable row, @SuppressWarnings("rawtypes") Comparable col) {
		// TODO Auto-generated method stub
		return 8;
	}

	@Override
	public Number getMinOutlier(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return 6;
	}

	@Override
	public Number getMinOutlier(@SuppressWarnings("rawtypes") Comparable arg0, @SuppressWarnings("rawtypes") Comparable arg1) {
		// TODO Auto-generated method stub
		return 6;
	}

	@Override
	public Number getMinRegularValue(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return 6;
	}

	@Override
	public Number getMinRegularValue(@SuppressWarnings("rawtypes") Comparable arg0, @SuppressWarnings("rawtypes") Comparable arg1) {
		// TODO Auto-generated method stub
		return 6;
	}

	@Override
	public List<?> getOutliers(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<?> getOutliers(@SuppressWarnings("rawtypes") Comparable arg0, @SuppressWarnings("rawtypes") Comparable arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Number getQ1Value(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return 6;
	}

	@Override
	public Number getQ1Value(@SuppressWarnings("rawtypes") Comparable arg0, @SuppressWarnings("rawtypes") Comparable arg1) {
		// TODO Auto-generated method stub
		return 6;
	}

	@Override
	public Number getQ3Value(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return 6;
	}

	@Override
	public Number getQ3Value(@SuppressWarnings("rawtypes") Comparable arg0, @SuppressWarnings("rawtypes") Comparable arg1) {
		// TODO Auto-generated method stub
		return 6;
	}

	@Override
	public int getColumnCount() {
		return v.size();
	}

	@Override
	public int getRowCount() {
		return 1;
	}
}
