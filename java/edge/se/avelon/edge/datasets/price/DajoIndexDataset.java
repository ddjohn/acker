package se.avelon.edge.datasets.price;

import java.util.Vector;
import org.jfree.chart.labels.XYToolTipGenerator;
import org.jfree.data.xy.XYDataset;

import se.avelon.edge.omx.datafeed.CandleFeedData;
import se.avelon.edge.utilities.DajoLogger;

public class DajoIndexDataset extends MyXYLineDataset implements XYToolTipGenerator  {

	private static final long serialVersionUID = 1L;
	private static final DajoLogger log = DajoLogger.getLogger(DajoIndexDataset.class);

	private Vector<CandleFeedData> data;

	@SuppressWarnings("deprecation")
	public DajoIndexDataset() {
		this.setToolTipGenerator(this);
	}

	public void set(Vector<CandleFeedData> data) {
		log.fine("Set " + data.size() + " items");
		this.data = data;
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

		return data.get(item).closingPrice;
	}

	public String generateToolTip(XYDataset ds, int serie, int item) {
		return data.get(item).name + "=>" + this.getYValue(serie, item);
	}
}
