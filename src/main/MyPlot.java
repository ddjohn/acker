package main;

import factories.InterfaceFactory;
import graph.CandleGraphInterface;
import gui.table.candlestick.MyChartAdaptation2;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.xml.xpath.XPathExpressionException;

import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;

import datafeeders.CandleFeedInterface;

import JFreeChartAdapter.JFreeCandleChartDataset;
import org.jfree.data.xy.OHLCDataset;

public class MyPlot extends XYPlot {

	private static final long serialVersionUID = 1L;

	public MyPlot() {
		
		MyChartAdaptation2 adapt = new MyChartAdaptation2();
		this.setDataset(new JFreeCandleChartDataset(adapt));
		
		CandleFeedInterface candle = InterfaceFactory.getFeeder(); //new OmxCandleFeedAdapter();

		try {
			adapt.set(candle.getData("SSE3966", Calendar.MONTH, 1));
		} 
		catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
