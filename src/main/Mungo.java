package main;

import factories.InterfaceFactory;
import graph.CandleGraphInterface;
import gui.table.candlestick.MyCandleChart;
import gui.table.candlestick.MyChartAdaptation2;
import gui.table.candlestick.MyGraphChart;
import gui.table.candlestick.MyLineChart;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CombinedDomainXYPlot;
import org.jfree.chart.plot.XYPlot;

import JFreeChartAdapter.JFreeCandleChartDataset;
//import OmxDataFeed.OmxCandleFeedAdapter;

public class Mungo extends JPanel {

	private static final long serialVersionUID = 1L;

	public Mungo(String title, String xAxis) {
		this.setLayout(new GridLayout(0,1));
		CombinedDomainXYPlot plot = new CombinedDomainXYPlot(new NumberAxis(xAxis));

		this.add(new MyCandleChart());
		this.add(new MyGraphChart());
		this.add(new MyLineChart());
/*
		this.add(new MyCandleChart(), BorderLayout.CENTER);
		this.add(new MyGraphChart(), BorderLayout.CENTER);
		this.add(new MyLineChart(), BorderLayout.CENTER);
*/
		plot.add(new MyPlot(), 1);
	}
}
