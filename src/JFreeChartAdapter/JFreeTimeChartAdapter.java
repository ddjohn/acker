package JFreeChartAdapter;

import java.awt.BorderLayout;

import graph.TimeGraphInterface;

import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

public class JFreeTimeChartAdapter extends JPanel {

	private static final long serialVersionUID = 1L;
	
	public JFreeTimeChartAdapter(String title, TimeGraphInterface iface) {
		this.setLayout(new BorderLayout());

		//JFreeTimeChartDataset ds;
		JFreeChart chart = ChartFactory.createTimeSeriesChart(title, "Y", "X", /*ds =*/ new JFreeTimeChartDataset(iface), true, true, true);
		chart.setAntiAlias(true);
		chart.setTextAntiAlias(true);


/*		XYPlot plot = (XYPlot) chart.getPlot();
		XYItemRenderer renderer = plot.getRenderer();
		renderer.setItemLabelGenerator(ds);
		renderer.setBaseItemLabelsVisible(true);*/
		this.add(new ChartPanel(chart, true));
	}
}
