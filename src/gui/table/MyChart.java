package gui.table;

import info.MyInformationTable;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;
import net.OmxData;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.CategoryItemLabelGenerator;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.time.MovingAverage;
import org.jfree.ui.ApplicationFrame;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import util.MyXmlUtil;
import data.MyTimeData;
import datafeeders.StockData;

import event.EventManager;

public class MyChart extends ChartPanel implements ListSelectionListener, ActionListener {

	private static final long serialVersionUID = 1L;
	private StockData stock;
	private MyTimeData time;
	private static MyChartDataset ds;
	ChartPanel cp;
	private static JFreeChart jfreechart;
	XYPlot plot = null;
	public MyChart() {
		super(jfreechart = ChartFactory.createCandlestickChart("Candlestick", "Date", "Value", ds = new MyChartDataset(), false));
		 plot = (XYPlot)jfreechart.getPlot();

/*		jfreechart.setTitle("Mungo");
		this.setAutoscrolls(true);
		
		
		this.setDomainZoomable(true);
		this.setRangeZoomable(true);

		XYPlot xyplot = (XYPlot)jfreechart.getPlot();
		NumberAxis numberaxis = (NumberAxis)xyplot.getRangeAxis();
		numberaxis.setAutoRangeIncludesZero(false);
		numberaxis.setUpperMargin(0.0D);
		numberaxis.setLowerMargin(0.0D);

		xyplot.setWeight(5);
		
		JFreeChart chart = this.getChart();
		XYPlot plot = (XYPlot) chart.getPlot();
		DateAxis domain = (DateAxis) plot.getDomainAxis();
		domain.setDateFormatOverride(DateFormat.getDateInstance());
		domain.setDateFormatOverride(new SimpleDateFormat("yyyy-MM-dd"));

		
		plot.setWeight(3);

		
		org.jfree.data.xy.XYDataset xydataset = MovingAverage.createMovingAverage(ds, "-MA5", 5, 5); 
		xyplot.setDataset(1, xydataset); 
		xyplot.setRenderer(1, new StandardXYItemRenderer()); 
		xyplot.mapDatasetToRangeAxis(1, 0); 
		
		NumberAxis axis2 = new NumberAxis("Volume");
		axis2.setFixedDimension(10.0);
		axis2.setAutoRangeIncludesZero(true);
		axis2.setLabelPaint(Color.red);
		axis2.setTickLabelPaint(Color.red);
		plot.setRangeAxis(1, axis2);
		plot.setRangeAxisLocation(1, AxisLocation.TOP_OR_RIGHT);
*/		
		/*     
        CategoryItemRenderer renderer = plot.getRenderer();
        CategoryLabelGenerator generator = new StandardCategoryLabelGenerator(
        "{2}", new DecimalFormat("0.00")
        );
        renderer.setLabelGenerator(generator);
		 */
		//        XYItemRenderer renderer2 = plot.getRenderer();
		//        XYLabelGenerator generator = new StandardXYLabelGenerator(
		//        "{2}", new DecimalFormat("0.00")
		//        );
		//        renderer2.setLabelGenerator(generator);
		/*
        CategoryItemRenderer renderer3 = plot.getRenderer();
        renderer3.setItemLabelsVisible(true);
		 */    
/*		XYItemRenderer renderer4 = plot.getRenderer();
		renderer4.setItemLabelsVisible(true);

		XYItemRenderer renderer = plot.getRenderer();
		renderer.setItemLabelsVisible(null); // clears the ALL series flag
		renderer.setSeriesItemLabelsVisible(0, true);
		renderer.setSeriesItemLabelsVisible(1, false);
		
		//renderer.setUpPaint(new Color(0x00FF00));
		//renderer.setDownPaint(new Color(0xFF0000));
*/

		EventManager.getInstance().subscribeOnList("stock.list", this);
		EventManager.getInstance().subscribeOnAction("toolbar.main.time", this);
	}

	public void valueChanged(ListSelectionEvent e) {

		if(e.getValueIsAdjusting() == false) {
			JList list = (JList)e.getSource();
			Object selectionValues[] = list.getSelectedValues();
			for(Object selectionValue : selectionValues) {
				if(selectionValue instanceof StockData) {
					stock = (StockData) selectionValue;
				}
			}

			this.updataNodes();		
		}
	}

	private void updataNodes() {
		if(stock != null && time != null) {
			try {
				Document doc = OmxData.getStockData(time, stock.id);
				MyInformationTable.getInstance().debug("resp=" + MyXmlUtil.documentToString(doc)); 
				NodeList nodes = MyXmlUtil.xpathSearch(doc, "//response/hi");

				ds.setNodes(nodes);
				/*				this.invalidate();
				this.repaint();
				cp.repaint();
				cp.invalidate();
				this.remove(cp);
				this.add(cp = new ChartPanel(jfreechart), BorderLayout.CENTER);
				 */
			} 
			catch (XPathExpressionException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
			catch (TransformerException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JComboBox box = (JComboBox)e.getSource();
		Object selectionValues[] = box.getSelectedObjects();
		for(Object selectionValue : selectionValues) {
			if(selectionValue instanceof MyTimeData) {
				time = (MyTimeData)selectionValue;
			}
		}
		this.updataNodes();		
	}

	public XYPlot getPlot() {
		return plot;
	}
}