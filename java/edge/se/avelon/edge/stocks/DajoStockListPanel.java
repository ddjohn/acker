package se.avelon.edge.stocks;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Vector;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


import se.avelon.edge.eventhandling.EventManager;
import se.avelon.edge.omx.datafeed.CandleFeedInterface;
import se.avelon.edge.omx.datafeed.InterfaceFactory;
import se.avelon.edge.omx.datafeed.StockData;
import se.avelon.edge.utilities.DajoLogger;

public class DajoStockListPanel extends JScrollPane implements ChangeListener {
	
	private static final long serialVersionUID = 1L;
	private static final DajoLogger log = DajoLogger.getLogger(DajoStockListPanel.class);
	
	static private JPanel panel = new JPanel();
	private DajoStockList list = new DajoStockList();

	public DajoStockListPanel() {
		super(panel);
		log.constructor("MyStockListPanel()");
		
		panel.setLayout(new BorderLayout());

		panel.add(new DajoStockFilterField(16, list), BorderLayout.NORTH);
		panel.add(list, BorderLayout.CENTER);

		// Properties
		this.setPreferredSize(new Dimension(256,256));
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// Register 
		EventManager.getInstance().registerListSelection(list, "stock.list");
		
		// Subscribe
		EventManager.getInstance().subscribeOnChange("main.tabs", this);

		// Actions
		this.collectStocks();
		
		this.startup(1);
	}

	public void startup(int i) {
		log.info("Send list event with new stock");
		list.setSelectedIndex(i);
	}

	private void collectStocks() {
		String tmp[] = new String[] {CandleFeedInterface.LARGE_CAP, CandleFeedInterface.MID_CAP, CandleFeedInterface.SMALL_CAP};

		CandleFeedInterface iface = InterfaceFactory.getFeeder(); //new OmxCandleFeedAdapter();
		Vector<StockData> stocks = iface.getStocks(tmp);
		stocks.insertElementAt(new StockData("OMX30", "Stockholm", "SE0000337842"), 0);
		list.setListData(stocks);
	}

	public void stateChanged(ChangeEvent e) {
		JTabbedPane sourceTabbedPane = (JTabbedPane) e.getSource();
		int index = sourceTabbedPane.getSelectedIndex();
		log.info("New tab triggered " + sourceTabbedPane.getTitleAt(index));

		if(sourceTabbedPane.getTitleAt(index).compareTo(" Technical Analysis ") == 0) {
			list.setEnabled(true);
		}
		else if(sourceTabbedPane.getTitleAt(index).compareTo(" Scatter ") == 0) {
			list.setEnabled(true);
		}
		else if(sourceTabbedPane.getTitleAt(index).compareTo(" Mine ") == 0) {
			list.setEnabled(true);
		}
		else if(sourceTabbedPane.getTitleAt(index).compareTo("=Line=") == 0) {
			list.setEnabled(true);
		}
		else if(sourceTabbedPane.getTitleAt(index).compareTo("Combined") == 0) {
			list.setEnabled(true);
		}
		else if(sourceTabbedPane.getTitleAt(index).compareTo("Chart") == 0) {
			list.setEnabled(true);
		}
		else {
			list.setEnabled(false);
		}
	} 
}
