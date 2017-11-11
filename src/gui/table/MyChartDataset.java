package gui.table;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import log.MyInformationTable;

import org.jfree.data.DomainOrder;
import org.jfree.data.general.DatasetChangeListener;
import org.jfree.data.general.DatasetGroup;
import org.jfree.data.xy.OHLCDataset;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import scratch.utilities.DajoLogger;
import util.MyUtil;

public class MyChartDataset implements OHLCDataset {

	private static final DajoLogger log = DajoLogger.getLogger(MyChartDataset.class);
	
	private NodeList nodes = null;

	public DomainOrder getDomainOrder() {
		return DomainOrder.NONE;
	}

	public int getItemCount(int serie) {
		if(nodes == null) {
			log.fine("getItemCount 0");

			return 0;
		}
		else {
			log.fine("getItemCount " + nodes.getLength());

			return nodes.getLength();
		}
	}

	public Number getX(int serie, int item) {
		return new Double(getXValue(serie, item));
	}

	public double getXValue(int serie, int item) {

		Element elem = (Element) nodes.item(item);
		String d = elem.getAttribute("dt");
		log.fine("getXValue " + d);

	    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	    try {
			Date date = (Date)formatter.parse(d);
			return date.getTime();
		} catch (ParseException e) {
				e.printStackTrace();
				return 0;
		}
		//return item;
	}

	public Number getY(int serie, int item) {
		return serie + item;
	}

	public double getYValue(int serie, int item) {
		MyInformationTable.getInstance().debug("getYValue " + serie + item);
		return serie + item;
	}

	public int getSeriesCount() {
		return 1;
	}

	public Comparable getSeriesKey(int serie) {
		return "Serie";
	}

	public int indexOf(Comparable serie) {
		// TODO Auto-generated method stub
		return 0;
	}

	public void addChangeListener(DatasetChangeListener arg0) {
		// TODO Auto-generated method stub

	}

	public DatasetGroup getGroup() {
		return null;
	}

	public void removeChangeListener(DatasetChangeListener arg0) {
		// TODO Auto-generated method stub

	}

	public void setGroup(DatasetGroup arg0) {
		// TODO Auto-generated method stub

	}

	public Number getClose(int serie, int item) {
		return new Double(getCloseValue(serie, item));
	}

	public double getCloseValue(int serie, int item) {
		Element elem = (Element) nodes.item(item);
		double d = MyUtil.parseN(elem.getAttribute("cp"), 0.0).doubleValue();
		log.fine("getCloseValue " + d);

		return d;
	}

	public Number getHigh(int serie, int item) {
		return new Double(getHighValue(serie, item));
	}

	public double getHighValue(int serie, int item) {

		Element elem = (Element) nodes.item(item);
		double d = MyUtil.parseN(elem.getAttribute("hp"), 0.0).doubleValue();
		log.fine("getHighValue " + d);

		return d;
	}

	public Number getLow(int serie, int item) {
		return new Double(getLowValue(serie, item));
	}

	public double getLowValue(int serie, int item) {

		Element elem = (Element) nodes.item(item);
		double d = MyUtil.parseN(elem.getAttribute("lp"), 0.0).doubleValue();
		log.fine("getLowValue " + d);

		return d;
	}

	public Number getOpen(int serie, int item) {
		return new Double(getOpenValue(serie, item));
	}

	public double getOpenValue(int serie, int item) {

		Element elem = (Element) nodes.item(item);
		double d = MyUtil.parseN(elem.getAttribute("cp"), 0.0).doubleValue();
		log.fine("getOpenValue " + d);

		//return d;
		return Double.NaN;
	}

	public Number getVolume(int serie, int item) {
		return new Double(getVolumeValue(serie, item));
	}

	public double getVolumeValue(int serie, int item) {

		Element elem = (Element) nodes.item(item);
		double d = MyUtil.parseN(elem.getAttribute("tv"), 0.0).doubleValue();
		log.fine("getVolumeValue " + d);

		return d;
	}

	public void setNodes(NodeList nodes) {
		this.nodes = nodes;
	}

	public void debug() {
		for(int i = 0; i < getItemCount(0); i++)  {
			System.out.println(":" + getXValue(0, i) + getYValue(0, i) + getVolumeValue(0, i));
		}
	}
}
