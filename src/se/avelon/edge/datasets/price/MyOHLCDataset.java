package se.avelon.edge.datasets.price;

import org.jfree.chart.renderer.xy.HighLowRenderer;
import org.jfree.data.DomainOrder;
import org.jfree.data.general.DatasetChangeListener;
import org.jfree.data.general.DatasetGroup;
import org.jfree.data.xy.OHLCDataset;
import se.avelon.utilities.DajoLogger;

public abstract class MyOHLCDataset extends HighLowRenderer implements OHLCDataset{
	private static final long serialVersionUID = 1L;
	private static final DajoLogger log = DajoLogger.getLogger(MyOHLCDataset.class);

	@Override
	public int getSeriesCount() {		
		return 1;
	}

	@Override
	public Comparable<String> getSeriesKey(int serie) {
		return "OHLC";
	}

	@Override
	public int indexOf(@SuppressWarnings("rawtypes") Comparable serie) {
		return 0;			
	}

	@Override
	public void addChangeListener(DatasetChangeListener arg0) {}

	@Override
	public DatasetGroup getGroup() {
		return null;
	}

	@Override
	public void removeChangeListener(DatasetChangeListener arg0) {}

	@Override
	public void setGroup(DatasetGroup arg0) {}

	@Override
	public DomainOrder getDomainOrder() {
		return DomainOrder.NONE;
	}

	@Override
	public Number getX(int serie, int item) {
		log.fine("getX");

		return Double.valueOf(this.getXValue(serie, item));
	}

	@Override
	public Number getY(int serie, int item) {
		log.fine("getY()");

		return Double.valueOf(this.getYValue(serie, item));
	}

	@Override
	public Number getClose(int serie, int item) {
		log.fine("getClose()");

		return Double.valueOf(this.getCloseValue(serie, item));
	}

	@Override
	public Number getHigh(int serie, int item) {
		log.fine("getY()");

		return Double.valueOf(this.getHighValue(serie, item));
	}

	@Override
	public Number getLow(int serie, int item) {
		log.fine("getLow()");

		return Double.valueOf(this.getLowValue(serie, item));
	}

	@Override
	public Number getOpen(int serie, int item) {
		log.fine("getOpen()");

		return Double.valueOf(this.getOpenValue(serie, item));
	}

	@Override
	public Number getVolume(int serie, int item) {
		log.fine("getVolume()");

		return Double.valueOf(this.getVolumeValue(serie, item));
	}

}
