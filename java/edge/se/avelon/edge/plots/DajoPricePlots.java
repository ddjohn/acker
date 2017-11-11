package se.avelon.edge.plots;

import java.awt.Color;
import java.util.Vector;
import se.avelon.edge.datasets.price.DajoCandlestickDataset;
import se.avelon.edge.datasets.price.DajoDonchianDataset;
import se.avelon.edge.datasets.price.DajoIndexDataset;
import se.avelon.edge.datasets.price.DajoMeanDataset;
import se.avelon.edge.datasets.price.DajoOHLCDataset;
import se.avelon.edge.datasets.price.DajoSplineDataset;
import se.avelon.edge.helpers.DajoNumberAxis;
import se.avelon.edge.helpers.DajoXYPlot;
import se.avelon.edge.omx.datafeed.CandleFeedData;

public class DajoPricePlots extends DajoXYPlot {

	private static final long serialVersionUID = 1L;

	private static final DajoIndexDataset        index = new DajoIndexDataset();
	private static final DajoSplineDataset      spline = new DajoSplineDataset();
	private static final DajoCandlestickDataset candle = new DajoCandlestickDataset();
	private static final DajoDonchianDataset  donchian = new DajoDonchianDataset(20);
	private static final DajoOHLCDataset          ohlc = new DajoOHLCDataset();
	private static final DajoMeanDataset        mean20 = new DajoMeanDataset(20);
	private static final DajoMeanDataset        mean50 = new DajoMeanDataset(50);
	private static final DajoMeanDataset       mean200 = new DajoMeanDataset(200);

	@SuppressWarnings("deprecation")
	public DajoPricePlots() {
		super();

		DajoNumberAxis nAxis = new DajoNumberAxis("Price");

		nAxis.setAutoRangeIncludesZero(false);
		nAxis.setUpperMargin(0.0D);		
		nAxis.setLowerMargin(0.0D);
		nAxis.setAutoRange(false);
		this.setRangeAxis(nAxis);

		this.setDataset(0, null);
		this.setRenderer(0, index);
		index.setPaint(Color.red);

		this.setDataset(1, null);
		this.setRenderer(1, spline);
		index.setPaint(Color.blue);

		this.setDataset(2, null);
		this.setRenderer(2, donchian);
		donchian.setPaint(Color.black);
	
		this.setDataset(3, null);
		this.setRenderer(3, ohlc);
		ohlc.setPaint(Color.black);

		this.setDataset(4, null);
		this.setRenderer(4, candle);
		candle.setPaint(Color.black);

		this.setDataset(5, null);
		this.setRenderer(5, mean20);
		//candle.setPaint(Color.black);

		this.setDataset(6, null);
		this.setRenderer(6, mean50);
		//candle.setPaint(Color.black);

		this.setDataset(7, null);
		this.setRenderer(7, mean200);
		//candle.setPaint(Color.black);
	}

	public void set(Vector<CandleFeedData> data) {
		index.set(data);
		spline.set(data);
		candle.set(data);
		donchian.set(data);
		ohlc.set(data);
		mean20.set(data);
		mean50.set(data);
		mean200.set(data);
	}

	public void show(int ds, int serie) {
		//	this.getRenderer(renderer).setSeriesVisible(serie, false);	
		switch(ds) {
		case 0: { 
			this.setDataset(0, index);
			break;
		}
		case 1: { 
			this.setDataset(1, spline);
			break;
		}
		case 2: { 
			this.setDataset(2, donchian);
			break;
		}
		case 3: { 
			this.setDataset(3, ohlc);
			break;
		}
		case 4: { 
			this.setDataset(4, candle);
			break;
		}
		case 5: { 
			this.setDataset(5, mean20);
			break;
		}
		case 6: { 
			this.setDataset(6, mean50);
			break;
		}
		case 7: { 
			this.setDataset(7, mean200);
			break;
		}
		}
	}
}