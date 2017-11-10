package se.avelon.edge.datasets.price;

import java.util.Vector;
import org.jfree.chart.labels.XYToolTipGenerator;
import org.jfree.data.xy.XYDataset;
import se.avelon.utilities.DajoLogger;
import datafeeders.CandleFeedData;

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

		return data.get(item).closingPrice;
	}

	@Override
	public String generateToolTip(XYDataset ds, int serie, int item) {
		return data.get(item).name + "=>" + this.getYValue(serie, item);
	}
}
