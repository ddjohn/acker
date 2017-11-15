package JFreeChartAdapter;

import java.awt.BorderLayout;
import java.text.SimpleDateFormat;
import graph.CandleGraphInterface;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartMouseEvent;
import org.jfree.chart.ChartMouseListener;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;

public class JFreeOHLCChartAdapter extends JPanel implements ChartMouseListener, ChangeListener {

	private static final long serialVersionUID = 1L;
	private ChartPanel panel = null;
	JFreeChart chart = null;

	public JFreeOHLCChartAdapter(String title, String x, String y, CandleGraphInterface iface) {
		this.setLayout(new BorderLayout());

		//JFreeTimeChartDataset ds;
		 chart = ChartFactory.createHighLowChart(title, x, y, /*ds =*/ new JFreeCandleChartDataset(iface), true);
		//chart.addChangeListener(this);
			chart.setAntiAlias(true);
			chart.setTextAntiAlias(true);

		XYPlot xyplot = (XYPlot)chart.getPlot();
		NumberAxis numberaxis = (NumberAxis)xyplot.getRangeAxis();
		numberaxis.setAutoRangeIncludesZero(false);
		numberaxis.setUpperMargin(0.0D);
		numberaxis.setLowerMargin(0.0D);
		DateAxis domain = (DateAxis)xyplot.getDomainAxis();
		domain.setDateFormatOverride(new SimpleDateFormat("d-MMM-yyyy"));
	
/*		XYPlot plot = (XYPlot) chart.getPlot();
		XYItemRenderer renderer = plot.getRenderer();
		renderer.setItemLabelGenerator(ds);
		renderer.setBaseItemLabelsVisible(true);*/
		this.add(panel = new ChartPanel(chart, true));
		panel.setMouseWheelEnabled(true);
		panel.addChartMouseListener(this);
	}
	
	public void fix() {
		chart.fireChartChanged();

		((XYPlot)chart.getPlot()).getDomainAxis().setAutoRange(false);
		((XYPlot)chart.getPlot()).getDomainAxis().setAutoRange(true);

		((XYPlot)chart.getPlot()).getRangeAxis().setAutoRange(false);
		((XYPlot)chart.getPlot()).getRangeAxis().setAutoRange(true);

		panel.restoreAutoDomainBounds();
		panel.restoreAutoRangeBounds();
		panel.restoreAutoBounds();

		chart.fireChartChanged();

		panel.disable();
		panel.enable();
		panel.invalidate();
		panel.revalidate();
		panel.repaint();
		panel.setDomainZoomable(true);
		panel.setRangeZoomable(true);
		panel.setVisible(true);
		this.repaint();
		this.setVisible(false);
		this.setVisible(true);
	}

	public void chartMouseClicked(ChartMouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("e=" + e);
	}

	public void chartMouseMoved(ChartMouseEvent e) {
		System.out.println("e=" + e);
	}

	public void stateChanged(ChangeEvent e) {
		System.out.println("e=" + e);
	}
}
