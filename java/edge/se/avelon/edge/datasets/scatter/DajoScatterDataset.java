package se.avelon.edge.datasets.scatter;

import java.awt.Color;
import java.awt.Font;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Shape;
import java.text.ParseException;
import java.util.Vector;
import org.jfree.chart.labels.XYItemLabelGenerator;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.DomainOrder;
import org.jfree.data.general.DatasetChangeListener;
import org.jfree.data.general.DatasetGroup;
import org.jfree.data.xy.XYDataset;
import org.jfree.util.ShapeUtilities;

import se.avelon.edge.formulas.Formulas;
import se.avelon.edge.omx.datafeed.CandleFeedData;
import se.avelon.edge.shapes.DajoScatterShape;
import se.avelon.edge.utilities.DajoLogger;

public class DajoScatterDataset extends  XYLineAndShapeRenderer implements XYDataset, XYItemLabelGenerator, DajoScatterConstants {

	private static final long serialVersionUID = 1L;
	private static final DajoLogger log = DajoLogger.getLogger(DajoScatterDataset.class);
	private Vector<Vector<CandleFeedData>> data;
	private Vector<Integer> array = new Vector<Integer>();

	@SuppressWarnings("deprecation")
	public DajoScatterDataset() {
		super(false, true);

		this.setItemLabelGenerator(this);
		this.setBaseItemLabelsVisible(true);
		this.setItemLabelFont(new Font("SansSerif", Font.PLAIN, 10));
		this.setSeriesPaint(0, Color.black);
	}

	public int getSeriesCount() {		
		return 1;
	}

	public Comparable<String> getSeriesKey(int serie) {
		return "Sctatter";
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
		log.fine("getItemCount()");

		if(data == null) {
			return 0;
		}
		else {
			return data.size();
		}
	}

	public Number getX(int serie, int item) {
		log.fine("getX");

		return Double.valueOf(this.getXValue(serie, item));
	}

	public double getXValue(int serie, int item) {
		log.fine("getXValue()");

		Vector<CandleFeedData> d = data.elementAt(item);

		double current = d.elementAt(d.size()-1).closingPrice;

		double mean = Formulas.mean(d, 200);

		return 1.0 * (current - mean) / mean;
	}

	public Number getY(int serie, int item) {
		log.fine("getY()");

		return Double.valueOf(this.getYValue(serie, item));
	}

	public double getYValue(int serie, int item) {
		log.fine("getYValue()");

		Vector<CandleFeedData> d = data.elementAt(item);

		return Formulas.fibonacci(d);
	}

	public Paint getItemPaint(int serie, int item) {
		try {
			double oscillator = Formulas.stochastic(data.elementAt(item));

			if(oscillator >= 70) {
				return Color.red;
			}
			else if(oscillator <= 30) {
				return Color.green;
			}
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
		return Color.black;
	}

	public Paint getItemLabelPaint(int serie, int item) {
		return this.getItemPaint(serie,  item);
	}

	public Shape getItemShape(int serie, int item) {

//		if((((Integer)array.elementAt(item)).intValue() & DOUBLE_RICOCHETTE) == DOUBLE_RICOCHETTE) {
//			return ShapeUtilities.createDiamond(5);
//		}
//		if((((Integer)array.elementAt(item)).intValue() & SINGEL_RICOCHETTE) == SINGEL_RICOCHETTE) {
//			return ShapeUtilities.createDiamond(3);
//		}
		//return ShapeUtilities.createDiamond(1);
		return new DajoScatterShape(((Integer)array.elementAt(item)).intValue());
	}
	
	/*
	public Font getItemLabelFont(int serie, int item) {


		System.out.println("> "  + item + "," + serie + "= " + ((Integer)array.elementAt(item)).intValue());

		if((((Integer)array.elementAt(item)).intValue() & DajoScatterDataset.DONHIGH) == DajoScatterDataset.DONHIGH) {
			this.addAnnotation(new DajoAnnotation("", this.getXValue(serie,  item), this.getYValue(serie,  item), DajoAnnotation.pos3));
		}

		if((((Integer)array.elementAt(item)).intValue() & DajoScatterDataset.DONLOW) == DajoScatterDataset.DONLOW) {
			this.addAnnotation(new DajoAnnotation("", this.getXValue(serie,  item), this.getYValue(serie,  item), DajoAnnotation.pos4));
		}

		double low = data.elementAt(item).elementAt(data.size()-1).lowestPrice;
		double high = data.elementAt(item).elementAt(data.size()-1).highestPrice;

		System.out.println("> "  + item + "," + serie);
		System.out.println("> " + low + "," + high +"," +  Formulas.donchianHigh(data.elementAt(item), 20) + "," + Formulas.donchianLow(data.elementAt(item), 20));
		if(high >= Formulas.donchianHigh(data.elementAt(item), 20)) {
			this.addAnnotation(new DajoAnnotation("", this.getXValue(serie,  item), this.getYValue(serie,  item), DajoAnnotation.pos3));
		}

		if(low <= Formulas.donchianLow(data.elementAt(item), 20)) {
			this.addAnnotation(new DajoAnnotation("", this.getXValue(serie,  item), this.getYValue(serie,  item), DajoAnnotation.pos4));
		}

		return super.getItemLabelFont(serie, item);
	}
	 */

	public String generateLabel(XYDataset ds, int serie, int item) {

		/*
 		//double current = data.elementAt(item).elementAt(data.size()-1).closingPrice;


		// Get market trend
		//double trend = Formulas.mean(data.elementAt(item), 200);
		//double fibonacci = Formulas.fibonacci(data.elementAt(item));
		this.setSeriesShape(0, ShapeUtilities.createDiamond(1));


		if(high >= Formulas.donchianHigh(data.elementAt(item), 20)) {
			//this.addAnnotation(new DajoAnnotation("", this.getXValue(serie,  item), this.getYValue(serie,  item), DajoAnnotation.pos3));
		}

		if(low <= Formulas.donchianLow(data.elementAt(item), 20)) {
			//this.addAnnotation(new DajoAnnotation("", this.getXValue(serie,  item), this.getYValue(serie,  item), DajoAnnotation.pos4));
		}
		 */
		return data.elementAt(item).elementAt(0).name;
	}

	public void set(Vector<Vector<CandleFeedData>> data) {
		this.data = data;
		log.info("Set " + data.size() + " items");

		array.clear();
		for(int item = 0; item < data.size(); item++) {
			int value = 0;

			double current = data.elementAt(item).elementAt(data.size()-1).closingPrice;	 
			double trend = Formulas.mean(data.elementAt(item), 200);
			double fibonacci = Formulas.fibonacci(data.elementAt(item));
			double low = data.elementAt(item).elementAt(data.size()-1).lowestPrice;
			double high = data.elementAt(item).elementAt(data.size()-1).highestPrice;
			double donLow = Formulas.donchianLow(data.elementAt(item), 20);
			double donHigh = Formulas.donchianHigh(data.elementAt(item), 20);

			System.out.println("> " + low + "," + high +"," +  Formulas.donchianHigh(data.elementAt(item), 20) + "," + Formulas.donchianLow(data.elementAt(item), 20));

			if(high >= Formulas.donchianHigh(data.elementAt(item), 20)) {
				value |= DONHIGH;
			}

			if(low <= Formulas.donchianLow(data.elementAt(item), 20)) {
				value |= DONLOW;
			}

			try {
				if(Formulas.doubleRichochette(data.elementAt(item))) {
					value |= DOUBLE_RICOCHETTE;
				}
				
				else if(Formulas.singleRichochette(data.elementAt(item))) {
					value |= SINGEL_RICOCHETTE;
				}
			} 
			catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			array.add(value);
		}
	}
}
