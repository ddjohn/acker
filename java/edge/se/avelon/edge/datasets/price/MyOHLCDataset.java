package se.avelon.edge.datasets.price;

import org.jfree.chart.renderer.xy.HighLowRenderer;
import org.jfree.data.DomainOrder;
import org.jfree.data.general.DatasetChangeListener;
import org.jfree.data.general.DatasetGroup;
import org.jfree.data.xy.OHLCDataset;

import se.avelon.edge.utilities.DajoLogger;

public abstract class MyOHLCDataset extends HighLowRenderer implements OHLCDataset{
	private static final long serialVersionUID = 1L;
	private static final DajoLogger log = DajoLogger.getLogger(MyOHLCDataset.class);

	public int getSeriesCount() {		
		return 1;
	}

	public Comparable<String> getSeriesKey(int serie) {
		return "OHLC";
	}

	public int indexOf(@SuppressWarnings("rawtypes") Comparable serie) {
		return 0;			
	}

	public void addChangeListener(DatasetChangeListener arg0) {}

	public DatasetGroup getGroup() {
		return null;
	}

	public void removeChangeListener(DatasetChangeListener arg0) {}

	public void setGroup(DatasetGroup arg0) {}

	public DomainOrder getDomainOrder() {
		return DomainOrder.NONE;
	}

	public Number getX(int serie, int item) {
		log.fine("getX");

		return Double.valueOf(this.getXValue(serie, item));
	}

	public Number getY(int serie, int item) {
		log.fine("getY()");

		return Double.valueOf(this.getYValue(serie, item));
	}

	public Number getClose(int serie, int item) {
		log.fine("getClose()");

		return Double.valueOf(this.getCloseValue(serie, item));
	}

	public Number getHigh(int serie, int item) {
		log.fine("getY()");

		return Double.valueOf(this.getHighValue(serie, item));
	}

	public Number getLow(int serie, int item) {
		log.fine("getLow()");

		return Double.valueOf(this.getLowValue(serie, item));
	}

	public Number getOpen(int serie, int item) {
		log.fine("getOpen()");

		return Double.valueOf(this.getOpenValue(serie, item));
	}

	public Number getVolume(int serie, int item) {
		log.fine("getVolume()");

		return Double.valueOf(this.getVolumeValue(serie, item));
	}

}
