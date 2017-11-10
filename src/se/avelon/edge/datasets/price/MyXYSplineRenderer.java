package se.avelon.edge.datasets.price;

import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.data.DomainOrder;
import org.jfree.data.general.DatasetChangeListener;
import org.jfree.data.general.DatasetGroup;
import org.jfree.data.xy.XYDataset;
import se.avelon.utilities.DajoLogger;

public abstract class MyXYSplineRenderer extends XYSplineRenderer implements XYDataset {
	private static final long serialVersionUID = 1L;
	private static final DajoLogger log = DajoLogger.getLogger(MyXYSplineRenderer.class);

	@Override
	public int getSeriesCount() {		
		return 1;
	}

	@Override
	public Comparable<String> getSeriesKey(int serie) {
		return "Spline";
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
}
