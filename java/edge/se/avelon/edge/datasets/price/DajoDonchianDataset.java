package se.avelon.edge.datasets.price;

import java.awt.BasicStroke;
import java.awt.Color;
import java.util.Vector;

import se.avelon.edge.formulas.Formulas;
import se.avelon.edge.omx.datafeed.CandleFeedData;
import se.avelon.edge.utilities.DajoLogger;

public class DajoDonchianDataset extends MyXYLineDataset {

	private static final long serialVersionUID = 1L;
	private static final DajoLogger log = DajoLogger.getLogger(DajoDonchianDataset.class);

	private Vector<CandleFeedData> data;
	private int days;

	@SuppressWarnings("deprecation")
	public DajoDonchianDataset(int days) {
		this.days = days;
		
		this.setShapesVisible(false);
		this.setShapesFilled(false);
		this.setSeriesPaint(0, Color.BLACK);
		this.setSeriesPaint(1, Color.BLACK);
		this.setSeriesPaint(2, Color.BLACK);
		
		this.setSeriesStroke(0, new BasicStroke(2.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
		this.setSeriesStroke(1, new BasicStroke(2.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
		this.setSeriesStroke(2, new BasicStroke(1.0f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER, 10.0f, new float[] {10.0f, 10.0f},  0.0f));
	}


	public void set(Vector<CandleFeedData> data) {
		this.data = data;
		log.fine("Set " + data.size() + " items");
	}

	@Override
	public int getSeriesCount() {		
		return 3;
	}

	@Override
	public Comparable<String> getSeriesKey(int serie) {
		switch(serie) {
		case 0: {
			return "Dochian max";
		}
		case 1: {
			return "Dochian mid";

		}
		case 2: {
			return "Dochian min";
		}
		}
		return "unknown";
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

	public double getXValue(int serie, int item) {
		log.fine("getXValue()");

		return data.get(item).date.getTime();
	}

	public double getYValue(int serie, int item) {
		log.fine("getYValue()");

		if(item < this.days) {
			return Double.NaN;
		}
		
		double min = Formulas.donchianLow(data, this.days, item);
		double max = Formulas.donchianHigh(data, this.days, item);

		switch(serie) {
		case 0: {
			return min;
		}
		case 1: {
			return max;
		}
		case 2: {
			return 0.5*(min + max);
		}
		}
		return Double.NaN;
	}
}
