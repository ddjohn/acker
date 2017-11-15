package JFreeChartAdapter;

import java.util.logging.Logger;

import graph.ScatterGraphInterface;

import org.jfree.chart.labels.XYItemLabelGenerator;
import org.jfree.data.DomainOrder;
import org.jfree.data.general.DatasetChangeListener;
import org.jfree.data.general.DatasetGroup;
import org.jfree.data.xy.XYDataset;

public class JFreeScatterChartDataset implements XYDataset, XYItemLabelGenerator {
	
	private static final Logger log = Logger.getAnonymousLogger();
	private ScatterGraphInterface iface;

	public JFreeScatterChartDataset(ScatterGraphInterface iface) {
		this.iface = iface;
	}

	public int getSeriesCount() {		
		return 1;
	}

	public Comparable<String> getSeriesKey(int serie) {
		return "serie";
	}

	public int indexOf(@SuppressWarnings("rawtypes") Comparable serie) {
		return 0;
	}

	public void addChangeListener(DatasetChangeListener arg0) {

	}

	public DatasetGroup getGroup() {
		return null;
	}

	public void removeChangeListener(DatasetChangeListener arg0) {
	}

	public void setGroup(DatasetGroup arg0) {

	}

	public DomainOrder getDomainOrder() {
		return DomainOrder.NONE;
	}

	public int getItemCount(int serie) {
		log.fine("getItemCount()");

		return iface.getNumberOfItems();
	}

	public Number getX(int serie, int item) {
		log.fine("getX");

		return Double.valueOf(this.getXValue(serie, item));
	}

	public double getXValue(int serie, int item) {
		log.fine("getXValue()");

		return iface.getX(item);
	}

	public Number getY(int serie, int item) {
		log.fine("getY()");

		return Double.valueOf(this.getYValue(serie, item));
	}

	public double getYValue(int serie, int item) {
		log.fine("getYValue()");

		return iface.getY(item);
	}

	public String generateLabel(XYDataset ds, int serie, int item) {
		return iface.getName(item);
	}
}
