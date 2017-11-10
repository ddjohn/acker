package se.avelon.edge.datasets.price;

import java.util.Vector;
import se.avelon.utilities.DajoLogger;
import datafeeders.CandleFeedData;

public class DajoSplineDataset extends MyXYSplineRenderer {

	private static final long serialVersionUID = 1L;
	private static final DajoLogger log = DajoLogger.getLogger(DajoSplineDataset.class);

	private Vector<CandleFeedData> data;

	@SuppressWarnings("deprecation")
	public DajoSplineDataset() {
		this.setShapesVisible(false);
		this.setShapesFilled(false);
	}
	
	public void set(Vector<CandleFeedData> data) {
		this.data = data;
		log.fine("Set " + data.size() + " items");
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
}
