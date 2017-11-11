package JFreeChartAdapter;

import java.awt.BorderLayout;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import graph.CandleGraphInterface;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.CandlestickRenderer;
import org.jfree.chart.renderer.xy.XYAreaRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.Range;

public class JFreeCandleChartAdapter extends JPanel {

	private static final long serialVersionUID = 1L;
	private ChartPanel panel = null;
	JFreeChart chart = null;

	public JFreeCandleChartAdapter(String title, String x, String y, CandleGraphInterface iface) {
		this.setLayout(new BorderLayout());

		chart = ChartFactory.createCandlestickChart(title, x, y, /*ds =*/ new JFreeCandleChartDataset(iface), true);
		chart.setAntiAlias(true);
		chart.setTextAntiAlias(true);

		XYPlot xyplot = (XYPlot)chart.getPlot();
		NumberAxis numberaxis = (NumberAxis)xyplot.getRangeAxis();
		numberaxis.setAutoRangeIncludesZero(false);
		numberaxis.setUpperMargin(0.0D);
		numberaxis.setLowerMargin(0.0D);
		numberaxis.setAutoRange(false);

//		 CandlestickRenderer renderer = (CandlestickRenderer) xyplot.getRenderer();
//		renderer.setAutoWidthMethod(CandlestickRenderer.WIDTHMETHOD_SMALLEST);

		DateAxis domain = (DateAxis)xyplot.getDomainAxis();
		domain.setDateFormatOverride(new SimpleDateFormat("d-MMM-yyyy"));
	//	chart.setBackgroundImage(JFreeChart.INFO.getLogo());

		domain.setAutoRange(false);

		this.add(panel = new ChartPanel(chart, true));
		panel.setMouseWheelEnabled(true); 
	}

	public void doSaveAs() {
		try {
			panel.setDefaultDirectoryForSaveAs(new File("./save"));
			panel.doSaveAs();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	public void print() {
		panel.createChartPrintJob();		
	}
	private static NumberAxis newTweakedNumberAxis(String label) {
		return new NumberAxis(label) {

			private static final long serialVersionUID = 1L;

			@Override
			public void setRange(Range sRange) {
				System.out.println("Range()");

				super.setRange(sRange.getLength() == 0 ? new Range(sRange.getLowerBound(), sRange.getLowerBound() + 1e-10): sRange);
			}
		};
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
}
