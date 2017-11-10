package se.avelon.edge.datasets.price;

import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.DomainOrder;
import org.jfree.data.general.DatasetChangeListener;
import org.jfree.data.general.DatasetGroup;
import org.jfree.data.xy.XYDataset;

import se.avelon.utilities.DajoLogger;

public abstract class MyXYLineDataset extends XYLineAndShapeRenderer implements XYDataset {

	private static final long serialVersionUID = 1L;
	private static final DajoLogger log = DajoLogger.getLogger(MyXYLineDataset.class);
	
	public int getSeriesCount() {		
		return 1;
	}

	public Comparable<String> getSeriesKey(int serie) {
		return "Index";
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
}
