package se.avelon.edge.datasets.price;

import java.util.Vector;
import se.avelon.edge.analysis.Candlestick;
import se.avelon.edge.helpers.DajoAnnotation;
import se.avelon.edge.omx.datafeed.CandleFeedData;
import se.avelon.edge.utilities.DajoLogger;

public class DajoCandlestickDataset extends MyCandlestickDataset {

	private static final long serialVersionUID = 1L;
	private static final DajoLogger log = DajoLogger.getLogger(DajoCandlestickDataset.class);

	private Vector<CandleFeedData> data;

	public DajoCandlestickDataset() {
		this.setDrawVolume(false);
	}

	public void set(Vector<CandleFeedData> data) {
		this.data = data;
		log.fine("Set " + data.size() + " items");

		this.removeAnnotations();

		int item = this.getItemCount(0)-1;
		this.addAnnotation(new DajoAnnotation(Candlestick.lookup(Candlestick.analyze(
				this.getOpenValue(0, item), this.getLowValue(0, item), this.getHighValue(0, item), this.getCloseValue(0, item))), this.getXValue(0,  item), this.getYValue(0,  item),  4.0 * Math.PI / 4.0));
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

	public Number getOpen(int serie, int item) {
		log.fine("getOpen()");

		return Double.valueOf(this.getOpenValue(serie, item));
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
