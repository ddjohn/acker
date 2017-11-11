package se.avelon.edge.plots;

import java.awt.Color;
import java.util.Vector;
import org.jfree.chart.annotations.XYBoxAnnotation;
import org.jfree.chart.annotations.XYPointerAnnotation;
import org.jfree.ui.RectangleAnchor;
import org.jfree.ui.TextAnchor;
import se.avelon.edge.datasets.percent.DajoStochastic1Dataset;
import se.avelon.edge.datasets.percent.DajoStochastic3Dataset;
import se.avelon.edge.helpers.DajoMarker;
import se.avelon.edge.helpers.DajoNumberAxis;
import se.avelon.edge.helpers.DajoXYPlot;
import se.avelon.edge.omx.datafeed.CandleFeedData;

public class DajoPercentPlot extends DajoXYPlot {

	private static final long serialVersionUID = 1L;

	private static final DajoStochastic1Dataset        stoch1 = new DajoStochastic1Dataset();
	private static final DajoStochastic3Dataset        stoch3 = new DajoStochastic3Dataset();

	@SuppressWarnings("deprecation")
	public DajoPercentPlot() {
		super();

		DajoNumberAxis nAxis = new DajoNumberAxis("Percent");
		//nAxis.setUpperMargin(1.0D);		
		nAxis.setLowerMargin(0.0D);
		nAxis.setAutoRange(true);
		nAxis.setAutoRangeIncludesZero(true);
		this.setRangeAxis(nAxis);

		this.setDataset(0, null);
		this.setRenderer(0, stoch1);
		stoch1.setPaint(Color.red);

		this.setDataset(1, null);
		this.setRenderer(1, stoch3);
		stoch3.setPaint(Color.blue);		

		XYBoxAnnotation a = new XYBoxAnnotation(0, 100, 0, 30);
		this.addAnnotation(a);
		XYPointerAnnotation b = new XYPointerAnnotation("Test", 70, 100, 0);
		this.addAnnotation(b);

		this.addRangeMarker(new DajoMarker(30, "Going up"  , Color.green, RectangleAnchor.BOTTOM_RIGHT, TextAnchor.TOP_RIGHT));
		this.addRangeMarker(new DajoMarker(70, "Going down", Color.red  , RectangleAnchor.BOTTOM_RIGHT, TextAnchor.TOP_RIGHT));
	}

	public void set(Vector<CandleFeedData> data) {
		stoch1.set(data);
		stoch3.set(data);
	}


	public void show(int ds, int serie) {
		switch(ds) {
		case 0: { 
			this.setDataset(0, stoch1);
			break;
		}
		case 1: { 
			this.setDataset(1, stoch3);
			break;
		}
		}
	}
}