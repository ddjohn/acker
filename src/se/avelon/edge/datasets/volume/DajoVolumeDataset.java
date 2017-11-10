package se.avelon.edge.datasets.volume;

import java.util.Vector;
import org.jfree.chart.labels.XYToolTipGenerator;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.data.DomainOrder;
import org.jfree.data.general.DatasetChangeListener;
import org.jfree.data.general.DatasetGroup;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.data.xy.XYDataset;
import se.avelon.utilities.DajoLogger;
import datafeeders.CandleFeedData;

/**
 * æ—¶é—´æ®µ valueï¼šæè¿°äº†ä¸€ä¸ªæ—¶é—´æ®µå†…çš„å€¼
 * @jdk 1.6
 * @author skzr.org(E-mail:skzr.org@gmail.com)
 * @version 1.0.0
 */
public class DajoVolumeDataset extends XYBarRenderer implements IntervalXYDataset, XYToolTipGenerator {

	private static final long serialVersionUID = 1L;
	private static final DajoLogger log = DajoLogger.getLogger(DajoVolumeDataset.class);

	private Vector<CandleFeedData> data;

	@SuppressWarnings("deprecation")
	public DajoVolumeDataset() {
		this.setToolTipGenerator(this);
		this.setShadowVisible(false);
	}
	
	public void set(Vector<CandleFeedData> data) {
		this.data = data;
		log.fine("Set " + data.size() + " items");
	}

	public int getSeriesCount() {		
		return 1;
	}

	public Comparable<String> getSeriesKey(int serie) {
		return "Volume";
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

	public Double getEndX(int serie, int item) {
		log.fine("getEndX()");

		return this.getEndXValue(serie, item);
	}

	public double getEndXValue(int serie, int item) {

		return data.get(item).date.getTime();
	}

	public Double getEndY(int serie, int item) {
		log.fine("getStartY()");

		return  this.getEndYValue(serie, item);

	}

	public double getEndYValue(int serie, int item) {
		
		return data.get(item).volume;
	}

	public Double getStartX(int serie, int item) {
		log.fine("getStartX()");

		return this.getStartXValue(serie, item);
	}

	public double getStartXValue(int serie, int item) {

		return data.get(item).date.getTime();
	}

	public Double getStartY(int serie, int item) {
		log.fine("getStartY()");

		return  this.getStartYValue(serie, item);
	}

	public double getStartYValue(int serie, int item) {

		return 0;
	}
	
	public Double getX(int serie, int item) {
		log.fine("getX");

		return Double.valueOf(this.getXValue(serie, item));
	}

	public double getXValue(int serie, int item) {
		log.fine("getXValue()");

		return data.get(item).date.getTime();
	}

	public Double getY(int serie, int item) {
		log.fine("getY()");

		return Double.valueOf(this.getYValue(serie, item));
	}

	public double getYValue(int serie, int item) {
		log.fine("getYValue()");

		return data.get(item).volume;
	}

	public String generateToolTip(XYDataset ds, int serie, int item) {
		return data.get(item).name + "=>" + this.getYValue(serie, item);
	}
}
