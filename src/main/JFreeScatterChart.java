package main;

import JFreeChartAdapter.JFreeScatterChartAdapter;

public class JFreeScatterChart extends JFreeScatterChartAdapter {

	private static final long serialVersionUID = 1L;
	
	public JFreeScatterChart(String title, double[][] values){
		super(title, "X", "Y", new ScatterGraphAdaptation(values));
	}
}
