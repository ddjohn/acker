package se.avelon.edge.helpers;

import org.jfree.chart.plot.XYPlot;

public class DajoXYPlot extends XYPlot {

	private static final long serialVersionUID = 1L;

	public void hide(int ds) {
		this.setDataset(ds, null);
	}
}
