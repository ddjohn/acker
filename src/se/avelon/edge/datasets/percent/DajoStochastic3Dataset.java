package se.avelon.edge.datasets.percent;

import java.text.ParseException;
import java.util.Vector;


import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.data.DomainOrder;
import org.jfree.data.general.DatasetChangeListener;
import org.jfree.data.general.DatasetGroup;
import org.jfree.data.xy.XYDataset;

import se.avelon.edge.formulas.Formulas;
import se.avelon.utilities.DajoLogger;
import datafeeders.CandleFeedData;

public class DajoStochastic3Dataset extends XYSplineRenderer implements XYDataset {

	private static final long serialVersionUID = 1L;
	private static final DajoLogger log = DajoLogger.getLogger(DajoStochastic3Dataset.class);

	private Vector<CandleFeedData> data;

	@SuppressWarnings("deprecation")
	public DajoStochastic3Dataset() {
		this.setShapesVisible(false);
		this.setShapesFilled(false);
	}
	
	public void set(Vector<CandleFeedData> data) {
		this.data = data;
		log.fine("Set " + data.size() + " items");
	}

	public int getSeriesCount() {		
		return 1;
	}

	public Comparable<String> getSeriesKey(int serie) {
		return "Stochastic3";
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

	public int getItemCount(int serie) {
		if(data == null) {
			log.fine("getItemCount()" + 0);
			return 0;
		}
		else {
			log.fine("getItemCount()" + data.size());
			return data.size();
		}
	}

	public Number getX(int serie, int item) {
		log.fine("getX");

		return Double.valueOf(this.getXValue(serie, item));
	}

	public double getXValue(int serie, int item) {
		log.fine("getXValue()");

		return data.get(item).date.getTime();
	}

	public Number getY(int serie, int item) {
		log.fine("getY()");

		return Double.valueOf(this.getYValue(serie, item));
	}

	public double getYValue(int serie, int item) {
		log.fine("getYValue()");

		if(item < 7) {
			return Double.NaN;
		}
		
		try {
			return Formulas.stochastic(data, item);
		} catch (ParseException e) {
			e.printStackTrace();
			return 100;
		}
	}
}
