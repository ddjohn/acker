package se.avelon.edge.chartpanels;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Date;
import java.util.Vector;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.jfree.chart.plot.CombinedDomainXYPlot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.ui.RectangleAnchor;
import org.jfree.ui.TextAnchor;
import scratch.MyTimeBox;
import scratch.MyTimeData;
import se.avelon.edge.eventhandling.EventManager;
import se.avelon.edge.helpers.DajoDateAxis;
import se.avelon.edge.helpers.DajoMarker;
import se.avelon.edge.plots.DajoPercentPlot;
import se.avelon.edge.plots.DajoPricePlots;
import se.avelon.edge.plots.DajoVolumePlots;
import se.avelon.utilities.DajoLogger;
import time.DateUtil;
import datafeeders.CandleFeedData;
import datafeeders.CandleFeedInterface;
import datafeeders.StockData;
import factories.InterfaceFactory;

public class DajoTechnicalAnalysis extends DajoChartPanel implements ItemListener, ActionListener, ListSelectionListener {

	private static final long serialVersionUID = 1L;
	private static final DajoLogger log = DajoLogger.getLogger(DajoTechnicalAnalysis.class);

	private static final DajoPricePlots    price = new DajoPricePlots();
	private static final DajoVolumePlots  volume = new DajoVolumePlots();
	private static final DajoPercentPlot percent = new DajoPercentPlot();

	private StockData stock = null;
	private MyTimeData time = null;

	private CombinedDomainXYPlot plot = null;
	private DajoMarker marker = null;
	private DajoMarker marker2 = null;
	
	public DajoTechnicalAnalysis() {
		super("etc/toolbars/main.xml");

		log.constructor("DajoChart()");

		DajoDateAxis date = new DajoDateAxis("Date");

		plot = new CombinedDomainXYPlot(date);
		this.setChart("Stock Data", plot);

		EventManager.getInstance().subscribeOnItem("toolbar.index.enable", this);
		EventManager.getInstance().subscribeOnItem("toolbar.index.candlestick", this);
		EventManager.getInstance().subscribeOnItem("toolbar.index.index", this);
		EventManager.getInstance().subscribeOnItem("toolbar.index.spline", this);
		EventManager.getInstance().subscribeOnItem("toolbar.index.donchian20", this);
		EventManager.getInstance().subscribeOnItem("toolbar.index.ohlc", this);
		EventManager.getInstance().subscribeOnItem("toolbar.index.mean20", this);
		EventManager.getInstance().subscribeOnItem("toolbar.index.mean50", this);
		EventManager.getInstance().subscribeOnItem("toolbar.index.mean200", this);

		EventManager.getInstance().subscribeOnItem("toolbar.volume.enable", this);
		EventManager.getInstance().subscribeOnItem("toolbar.volume.volume", this);

		EventManager.getInstance().subscribeOnItem("toolbar.percent.enable", this);
		EventManager.getInstance().subscribeOnItem("toolbar.percent.stochastic1", this);
		EventManager.getInstance().subscribeOnItem("toolbar.percent.stochastic3", this);

		EventManager.getInstance().subscribeOnList("stock.list", this);
		EventManager.getInstance().subscribeOnAction("toolbar.main.time", this);

		//this.startStock(0);
		this.startTime(1);
		this.startEnableIndex();
	}

	public void itemStateChanged(ItemEvent e) {
		JCheckBox source = (JCheckBox)e.getSource();

		if(source.getName().compareTo("toolbar.index.enable") == 0) {
			if(e.getStateChange() == ItemEvent.SELECTED) {
				plot.add(price);
				this.revalidate();
			}
			else {
				plot.remove(price);
				this.revalidate();
			}
		}

		else if(source.getName().compareTo("toolbar.volume.enable") == 0) {
			if(e.getStateChange() == ItemEvent.SELECTED) {
				plot.add(volume);
				this.revalidate();
			}
			else {
				plot.remove(volume);
				this.revalidate();
			}
		}

		else if(source.getName().compareTo("toolbar.percent.enable") == 0) {
			if(e.getStateChange() == ItemEvent.SELECTED) {
				plot.add(percent);
				this.revalidate();
			}
			else {
				plot.remove(percent);
				this.revalidate();
			}
		}

		else if(source.getName().compareTo("toolbar.index.index") == 0) {
			if(e.getStateChange() == ItemEvent.SELECTED) {
				price.show(0, 0);		
			}
			else {
				price.hide(0);
				System.out.println("visible: " + price.toString());
			}
		}

		else if(source.getName().compareTo("toolbar.index.spline") == 0) {
			if(e.getStateChange() == ItemEvent.SELECTED) {
				price.show(1, 0);		
			}
			else {
				price.hide(1);
			}
		}

		else if(source.getName().compareTo("toolbar.index.donchian20") == 0) {
			if(e.getStateChange() == ItemEvent.SELECTED) {
				price.show(2, 0);
				price.show(2, 1);
				price.show(2, 2);
			}
			else {
				price.hide(2);
			}
		}

		else if(source.getName().compareTo("toolbar.index.ohlc") == 0) {
			if(e.getStateChange() == ItemEvent.SELECTED) {
				price.show(3, 0);
			}
			else {
				price.hide(3);
			}
		}

		else if(source.getName().compareTo("toolbar.index.candlestick") == 0) {
			if(e.getStateChange() == ItemEvent.SELECTED) {
				price.show(4, 0);
			}
			else {
				price.hide(4);
			}
		}

		else if(source.getName().compareTo("toolbar.index.mean20") == 0) {
			if(e.getStateChange() == ItemEvent.SELECTED) {
				price.show(5, 0);
			}
			else {
				price.hide(5);
			}
		}

		else if(source.getName().compareTo("toolbar.index.mean50") == 0) {
			if(e.getStateChange() == ItemEvent.SELECTED) {
				price.show(6, 0);
			}
			else {
				price.hide(6);
			}
		}

		else if(source.getName().compareTo("toolbar.index.mean200") == 0) {
			if(e.getStateChange() == ItemEvent.SELECTED) {
				price.show(7, 0);
			}
			else {
				price.hide(7);
			}
		}

		else if(source.getName().compareTo("toolbar.volume.volume") == 0) {
			if(e.getStateChange() == ItemEvent.SELECTED) {
				volume.show(0, 0);		

			}
			else {
				volume.hide(0);
			}
		}

		else if(source.getName().compareTo("toolbar.percent.stochastic1") == 0) {
			if(e.getStateChange() == ItemEvent.SELECTED) {
				percent.show(0, 0);		

			}
			else {
				percent.hide(0);
			}
		}

		else if(source.getName().compareTo("toolbar.percent.stochastic3") == 0) {
			if(e.getStateChange() == ItemEvent.SELECTED) {
				percent.show(1, 0);		

			}
			else {
				percent.hide(1);
			}
		}

		else {
			log.severe("Uknown event " + e);
		}

	}

	public void valueChanged(ListSelectionEvent e) {
		log.info("New stock? " + e);

		if(e.getValueIsAdjusting() == false) {
			JList list = (JList)e.getSource();
			Object selectionValues[] = list.getSelectedValues();
			for(Object selectionValue : selectionValues) {
				if(selectionValue instanceof StockData) {
					stock = (StockData)selectionValue;
				}
			}

			this.updateNodes();		
		}
	}

	private void updateNodes() {
		log.info("New stock and/or time selected time=" + time + " and stock=" + stock);

		if(stock != null && time != null) {
			CandleFeedInterface candle = InterfaceFactory.getFeeder(); //new OmxCandleFeedAdapter();
			//Vector<CandleFeedData> data = candle.getData(stock.id, time.type, time.value);

			Date date = DateUtil.daysAgo(time.type, time.value);
/*
  			Calendar cal = Calendar.getInstance();
 
			cal.setTime(date);
			cal.add(Calendar.DATE, -365); //minus number would decrement the days      
			Date fromDate =  cal.getTime();			
*/
			//Vector<CandleFeedData> data = candle.getData(stock.id, time.type, time.value);
			Vector<CandleFeedData> data = candle.getData(stock.id, date);
			price.set(data);

			if(marker != null) {
				price.removeRangeMarker(marker);
			}
			price.addRangeMarker(marker = new DajoMarker(data.elementAt(0).closingPrice, Color.black));
			marker.setLabelAnchor(RectangleAnchor.TOP_RIGHT);
			marker.setLabelTextAnchor(TextAnchor.BOTTOM_RIGHT);

			if(marker2 != null) {
				price.removeRangeMarker(marker2);
			}
			price.addRangeMarker(marker2 = new DajoMarker(data.elementAt(data.size()-1).closingPrice, Color.black));
			marker2.setLabelAnchor(RectangleAnchor.BOTTOM_RIGHT);
			marker2.setLabelTextAnchor(TextAnchor.TOP_RIGHT);

			volume.set(data);
			percent.set(data);

			this.setTitle(data.get(0).name + " (" + data.get(0).instrument + ")");

			this.fix();
		}		
	}		

	public void actionPerformed(ActionEvent e) {
		log.info("New time? " + e);

		JComboBox box = (JComboBox)e.getSource();
		Object selectionValues[] = box.getSelectedObjects();
		for(Object selectionValue : selectionValues) {
			if(selectionValue instanceof MyTimeData) {
				time = (MyTimeData)selectionValue;
			}
		}
		this.updateNodes();		
	}

	public void startTime(int j) {
		log.info("New start time " + j);
		for(int i = 0; i< toolbar.getComponentCount(); i++) {
			if(toolbar.getComponentAtIndex(i) instanceof MyTimeBox) {
				MyTimeBox box = (MyTimeBox)toolbar.getComponentAtIndex(i);
				box.setSelectedIndex(j);
			}
		}
	}

	public void startEnableIndex() {
		log.info("New start enable");

		for(int i = 0; i < toolbar.getComponentCount(); i++) {

			if(toolbar.getComponentAtIndex(i) instanceof JPanel) {
				JPanel panel = (JPanel) toolbar.getComponentAtIndex(i);

				for(int j = 0; j < panel.getComponentCount(); j++) {

					if(panel.getComponent(j) instanceof JCheckBox) {
						JCheckBox box = (JCheckBox) panel.getComponent(j);

						if(		box.getName().compareTo("toolbar.index.enable")      == 0 || 
								box.getName().compareTo("toolbar.index.candlestick") == 0 || 
								box.getName().compareTo("toolbar.index.donchian20")  == 0 || 
								box.getName().compareTo("toolbar.index.mean20")      == 0) {
							box.setSelected(true);
						}
					}
				}
			}
		}
	}

	public void fix() {
		this.fireChartChanged();

		if(price != null && ((XYPlot)price).getDomainAxis() != null) {
			((XYPlot)price).getDomainAxis().setAutoRange(false);
			((XYPlot)price).getDomainAxis().setAutoRange(true);

			((XYPlot)price).getRangeAxis().setAutoRange(false);
			((XYPlot)price).getRangeAxis().setAutoRange(true);
		}

		if(volume != null && ((XYPlot)volume).getDomainAxis() != null) {
			((XYPlot)volume).getDomainAxis().setAutoRange(false);
			((XYPlot)volume).getDomainAxis().setAutoRange(true);

			((XYPlot)volume).getRangeAxis().setAutoRange(false);
			((XYPlot)volume).getRangeAxis().setAutoRange(true);
		}

		if(percent != null && ((XYPlot)percent).getDomainAxis() != null) {
			((XYPlot)percent).getDomainAxis().setAutoRange(false);
			((XYPlot)percent).getDomainAxis().setAutoRange(true);

			((XYPlot)percent).getRangeAxis().setAutoRange(false);
			((XYPlot)percent).getRangeAxis().setAutoRange(true);
		}

		this.fireChartChanged();
	}
}
