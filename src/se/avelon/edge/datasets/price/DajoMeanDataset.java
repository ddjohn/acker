package se.avelon.edge.datasets.price;

import java.util.Vector;
import org.jfree.chart.labels.XYToolTipGenerator;
import org.jfree.data.xy.XYDataset;

import se.avelon.edge.formulas.Formulas;
import se.avelon.utilities.DajoLogger;
import datafeeders.CandleFeedData;

public class DajoMeanDataset extends MyXYLineDataset implements XYToolTipGenerator  {
	private static final long serialVersionUID = 1L;
	private static final DajoLogger log = DajoLogger.getLogger(DajoMeanDataset.class);

	private Vector<CandleFeedData> data;
	private int days;

	@SuppressWarnings("deprecation")
	public DajoMeanDataset(int days) {
		this.days = days;
		
		this.setShapesVisible(false);
		this.setShapesFilled(false);
		this.setToolTipGenerator(this);
	}

	public void set(Vector<CandleFeedData> data) {
		log.fine("Set " + data.size() + " items");
		this.data = data;
	}

	@Override
	public Comparable<String> getSeriesKey(int serie) {
		return "Mean" + days;
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
	public double getXValue(int serie, int item) {
		log.fine("getXValue()");

		return data.get(item).date.getTime();
	}

	@Override
	public double getYValue(int serie, int item) {
		log.fine("getYValue()");

		if(item < this.days) {
			return Double.NaN;
		}

		return Formulas.mean(data, this.days, item);
		//return data.get(item).closingPrice;
	}

	@Override
	public String generateToolTip(XYDataset ds, int serie, int item) {
		return data.get(item).name + "=>" + this.getYValue(serie, item);
	}
}
