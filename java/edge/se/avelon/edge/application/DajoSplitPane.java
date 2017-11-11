package se.avelon.edge.application;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import se.avelon.edge.chartpanels.DajoTechnicalAnalysis;
import se.avelon.edge.chartpanels.DajoMyStocks;
import se.avelon.edge.chartpanels.DajoScatter;
import se.avelon.edge.eventhandling.EventManager;
import se.avelon.edge.stocks.DajoStockListPanel;
import se.avelon.edge.tabs.MyLosersChart;
import se.avelon.edge.tabs.MyTurnoverChart;
import se.avelon.edge.tabs.MyVolumesChart;
import se.avelon.edge.tabs.MyWinnersChart;
import se.avelon.edge.utilities.DajoConfiguration;
import se.avelon.edge.utilities.DajoLogger;

public class DajoSplitPane extends JSplitPane implements ChangeListener {

	private static final long serialVersionUID = 1L;
	private static final DajoConfiguration conf = DajoConfiguration.getInstance();
	private static final DajoLogger log = DajoLogger.getLogger(DajoSplitPane.class);

	public DajoSplitPane() {
		super(JSplitPane.HORIZONTAL_SPLIT);
		log.info("Construct DajoSplitPane");
		
		JTabbedPane tabs = new JTabbedPane();
		EventManager.getInstance().registerChange(tabs, "main.tabs");
		
		DajoStockListPanel list = new DajoStockListPanel();

		this.add(list);
		this.add(tabs);
		
		this.setOneTouchExpandable(true);
		this.setDividerLocation(150);
	
		tabs.addTab(" Technical Analysis ", new ImageIcon("icon/tab/office-chart-line.png"), new DajoTechnicalAnalysis());
		tabs.addTab(" Scatter ", new ImageIcon("icon/tab/Candlestick.png"      ), new DajoScatter());
		tabs.add(" Mine ", new DajoMyStocks());
		tabs.add(" Welcome ", new DajoWelcome());
		tabs.add(" Configuration ", conf);
		tabs.addTab("Winners", new ImageIcon("icon/tab/go-up-7.png"), new MyWinnersChart());
		tabs.addTab("Loosers", new ImageIcon("icon/tab/go-down-7.png"), new MyLosersChart());
		tabs.add("Most Traded", new MyVolumesChart());
		tabs.addTab("Highest Turnover", new ImageIcon("icon/tab/money-2.png"), new MyTurnoverChart());

		EventManager.getInstance().subscribeOnChange("maint.tabs", this);
		
		list.startup(0);
	}

	public void stateChanged(ChangeEvent e) {
		JTabbedPane sourceTabbedPane = (JTabbedPane) e.getSource();
		int index = sourceTabbedPane.getSelectedIndex();
		log.info("New tab triggered " + sourceTabbedPane.getTitleAt(index));

		JPanel panel = (JPanel) sourceTabbedPane.getComponentAt(index);
		log.info("panel=" + panel);

		if(panel instanceof DajoTechnicalAnalysis) {
			log.info("DajoTechnicalAnalysis");
			//Active.getInstance().setActiveChart(((DajoTechnicalAnalysis)panel).panel);
		}
	}
}
