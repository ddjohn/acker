package JFreeChartAdapter;

import java.awt.BorderLayout;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import graph.ScatterGraphInterface;

import javax.swing.JPanel;


import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CrosshairState;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.AbstractRenderer;
import org.jfree.chart.renderer.xy.AbstractXYItemRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYItemRendererState;
import org.jfree.data.xy.XYDataset;

public class JFreeScatterChartAdapter extends JPanel {

	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("deprecation")
	public JFreeScatterChartAdapter(String title, String x, String y, ScatterGraphInterface iface) {
		this.setLayout(new BorderLayout());

		JFreeScatterChartDataset ds;
		JFreeChart chart = ChartFactory.createScatterPlot(title, x, y, ds = new JFreeScatterChartDataset(iface), PlotOrientation.VERTICAL, true, true, true);
		chart.setAntiAlias(true);
		chart.setTextAntiAlias(true);

		XYPlot plot = (XYPlot) chart.getPlot();
		XYItemRenderer renderer = plot.getRenderer();
		renderer.setBaseItemLabelGenerator(ds);
		renderer.setBaseItemLabelsVisible(true);
		this.add(new ChartPanel(chart, true));
	}
}
