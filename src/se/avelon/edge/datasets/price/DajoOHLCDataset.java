package se.avelon.edge.datasets.price;

import java.awt.BasicStroke;
import java.util.Vector;
import se.avelon.utilities.DajoLogger;
import datafeeders.CandleFeedData;

public class DajoOHLCDataset extends MyOHLCDataset {

	private static final long serialVersionUID = 1L;
	private static final DajoLogger log = DajoLogger.getLogger(DajoOHLCDataset.class);

	private Vector<CandleFeedData> data;
	
	@SuppressWarnings("deprecation")
	public DajoOHLCDataset() {
		this.setStroke(new BasicStroke(2.0f));
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

	public double getCloseValue(int serie, int item) {
		log.fine("getCloseValue()");

		return data.get(item).closingPrice;
	}

	public double getHighValue(int serie, int item) {
		log.fine("getHighValue()");

		return data.get(item).highestPrice;
	}

	public double getLowValue(int serie, int item) {
		log.fine("getLowValue()");

		return data.get(item).lowestPrice;
	}

	public double getOpenValue(int serie, int item) {
		log.fine("getOpenValue()");

		return data.get(item).openingPrice;
	}

	public double getVolumeValue(int serie, int item) {
		log.fine("getVolumeValue()");

		return data.get(item).volume;
	}
}
