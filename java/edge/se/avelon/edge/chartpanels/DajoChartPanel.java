package se.avelon.edge.chartpanels;

import java.awt.BorderLayout;
import java.awt.RenderingHints;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.XYPlot;

import se.avelon.edge.guifactories.DajoToolbarFactory;

public class DajoChartPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private JFreeChart chart = null;
	protected final JToolBar toolbar;
	private ChartPanel panel = null;

	public DajoChartPanel(String file) {
		super(new BorderLayout());

		this.add(panel = new ChartPanel(chart), BorderLayout.CENTER);
		this.add(toolbar = DajoToolbarFactory.getJToolBar(file), BorderLayout.SOUTH);

		toolbar.setFloatable(false);
		panel.setMouseWheelEnabled(true); 
	}

	protected void setChart(String title, Plot plot) {

		chart = new JFreeChart("Scatter", plot);
		chart.setAntiAlias(true);
		chart.setTextAntiAlias(true);
		chart.getRenderingHints().put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		//panel.setMouseWheelEnabled(true); 

		this.add(new ChartPanel(chart), BorderLayout.CENTER);
	}

	protected void removeLegend() {
		chart.removeLegend();
	}

	protected void fireChartChanged() {
		chart.fireChartChanged();		
	}

	protected void setTitle(String string) {
		chart.setTitle(string);
	}

	public void fix() {
		this.fireChartChanged();

		Plot plot = chart.getPlot();
		if(plot != null) {
			if(plot instanceof CategoryPlot) {
				ValueAxis raxis = ((CategoryPlot)plot).getRangeAxis();
				if(raxis != null) {
					raxis.setAutoRange(false);
					raxis.setAutoRange(true);
				}
			}
			
			if(plot instanceof XYPlot) {
				ValueAxis caxis = ((XYPlot)plot).getDomainAxis();
				caxis.setAutoRange(false);
				caxis.setAutoRange(true);

				ValueAxis raxis = ((XYPlot)plot).getRangeAxis();
				if(raxis != null) {
					raxis.setAutoRange(false);
					raxis.setAutoRange(true);
				}
			}
		}
		
		this.fireChartChanged();
	}
}
