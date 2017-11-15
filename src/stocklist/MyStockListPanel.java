package stocklist;

import event.EventManager;
import factories.InterfaceFactory;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Vector;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import scratch.utilities.DajoLogger;

import datafeeders.CandleFeedInterface;
import datafeeders.StockData;

public class MyStockListPanel extends JScrollPane implements ChangeListener {
	
	private static final long serialVersionUID = 1L;
	private static final DajoLogger log = DajoLogger.getLogger(MyStockListPanel.class);
	
	static private JPanel panel = new JPanel();
	private MyStockList list = new MyStockList();

	public MyStockListPanel() {
		super(panel);
		log.constructor("MyStockListPanel()");
		
		panel.setLayout(new BorderLayout());

		panel.add(new MyStockFilterField(16, list), BorderLayout.NORTH);
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
	}

	public void startup(int i) {
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

		if(sourceTabbedPane.getTitleAt(index).compareTo("=Main=") == 0) {
			list.setEnabled(true);
		}
		else if(sourceTabbedPane.getTitleAt(index).compareTo("=Graph=") == 0) {
			list.setEnabled(true);
		}
		else if(sourceTabbedPane.getTitleAt(index).compareTo("=Candlestick=") == 0) {
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
