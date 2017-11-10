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

public class DajoStochastic1Dataset extends XYSplineRenderer implements XYDataset {

	private static final long serialVersionUID = 1L;
	private static final DajoLogger log = DajoLogger.getLogger(DajoStochastic1Dataset.class);

	private Vector<CandleFeedData> data;

	@SuppressWarnings("deprecation")
	public DajoStochastic1Dataset() {
		this.setShapesVisible(false);
		this.setShapesFilled(false);
	}
	
	public void set(Vector<CandleFeedData> data) {
		this.data = data;
		log.fine("Set " + data.size() + " items");
	}

	@Override
	public int getSeriesCount() {		
		return 1;
	}

	@Override
	public Comparable<String> getSeriesKey(int serie) {
		return "Stochastic1";
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

	@Override
	public Number getX(int serie, int item) {
		log.fine("getX");

		return Double.valueOf(this.getXValue(serie, item));
	}

	@Override
	public double getXValue(int serie, int item) {
		log.fine("getXValue()");

		return data.get(item).date.getTime();
	}

	@Override
	public Number getY(int serie, int item) {
		log.fine("getY()");

		return Double.valueOf(this.getYValue(serie, item));
	}

	@Override
	public double getYValue(int serie, int item) {
		log.fine("getYValue()");

		if(item < 7) {
			return Double.NaN;
		}
				try {
			return Formulas.stochasticTmp(data, item);
		} 
		catch (ParseException e) {
			e.printStackTrace();
			return 100;
		}
	}
}
