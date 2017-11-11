package main;

import event.EventManager;
import gui.MyMain;
import gui.MyStatusBar;
import gui.menu.MyMenu;
import gui.table.MyChart;
import gui.table.MyConfiguration;
import gui.table.MyLosersChart;
import gui.table.MyMarketChart;
import gui.table.MyStocksChart;
import gui.table.MyTurnoverChart;
import gui.table.MyVolumesChart;
import gui.table.MyWinnersChart; 
import gui.table.buy_analysis.BuyAnalysisWithJFreeChart;
import gui.table.buy_analysis.BuyAnalysisWithRaw;
import gui.table.candlestick.MyCandleChart;
import gui.table.candlestick.MyGraphChart;
import gui.table.candlestick.MyLineChart;
import info.MyInformationDialog;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

//import scratch.DajoToolbar;
import stocklist.MyStockListPanel;

public class Kursvinnare extends MyFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private MyMain main;
	private JTabbedPane tabs = new JTabbedPane();

	public Kursvinnare() {
		EventManager.getInstance().registerChange(tabs, "main.tabs");

		//JPanel panel = new JPanel();
		
		JSplitPane pane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new MyStockListPanel(), tabs);
		
//		panel.add(tabs, BorderLayout.CENTER);
//		panel.add(new DajoToolbar(), BorderLayout.SOUTH);
		pane.setPreferredSize(new Dimension(800, 600));
		
		pane.setOneTouchExpandable(true);
		pane.setDividerLocation(150);
		this.add(pane, BorderLayout.CENTER);

		// Components
		this.setJMenuBar(MyMenu.getJMenuBar());

		tabs.addTab("=Main="       , new ImageIcon("icon/tab/office-chart-line.png"), main = new MyMain());
		tabs.addTab("=Candlestick=", new ImageIcon("icon/tab/Candlestick.png"      ), new MyCandleChart()); 
		tabs.addTab("=Graph="      , new ImageIcon("icon/tab/office-chart-line.png"), new MyGraphChart()); 
		tabs.addTab("=Line="      , new ImageIcon("icon/tab/office-chart-line.png"), new MyLineChart()); 
		tabs.add("Combined", new JScrollPane(new Habba()));

		tabs.add("BuyAnalysis (JFreeChart)", new BuyAnalysisWithJFreeChart());
		tabs.add("BuyAnalysis (Raw)", new BuyAnalysisWithRaw());		

		tabs.add("JFreeChart", new MyChart()); 
		tabs.add("MyStocks", new MyStocksChart());
		////tabs.add("Markets", new MyMarketChart());
		tabs.addTab("Winners", new ImageIcon("icon/tab/go-up-7.png"), new MyWinnersChart());
		tabs.addTab("Loosers", new ImageIcon("icon/tab/go-down-7.png"), new MyLosersChart());
		tabs.add("Most Traded", new MyVolumesChart());
		tabs.addTab("Highest Turnover", new ImageIcon("icon/tab/money-2.png"), new MyTurnoverChart());
		tabs.addTab("Config", new ImageIcon("icon/tab/money-2.png"), MyConfiguration.getInstance());

		this.add(new MyStatusBar(), BorderLayout.SOUTH);

		new MyInformationDialog(this);

		// Register components

		// Subscribe on events
		EventManager.getInstance().subscribeOnAction("menu.file.quit", this);
		EventManager.getInstance().subscribeOnAction("menu.help.about", this);
		EventManager.getInstance().subscribeOnAction("menu.help.help", this);
		this.addWindowListener(this);

		// Properties
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("icon/stock.png"));
		this.setTitle("Hitta Kursvinnare");
		this.pack();
		this.setVisible(true);

		//Actions
		Active.getInstance();

		// Start events
		main.startStock(0);
		main.startTime(0);
		main.startEnableIndex();
	}

	public void actionPerformed(ActionEvent e) {
		JComponent c = (JComponent)e.getSource();
		if(c.getName().compareTo("menu.file.quit") == 0) {
			this.quit();
		}
		else if(c.getName().compareTo("menu.help.about") == 0) {
			JOptionPane.showMessageDialog(this, "Hitta Kursvinnare 2012");
		}
		else if(c.getName().compareTo("menu.help.help") == 0) {
			JOptionPane.showMessageDialog(this, "Help");
		}
	}

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		Locale.setDefault(Locale.ENGLISH);

		final String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";
		final Calendar cal = Calendar.getInstance();
		final SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);

		Logger.getLogger("").setLevel(Level.ALL);
		for(Handler h : Logger.getLogger("").getHandlers()) {
			if(h instanceof FileHandler) {
				h.setLevel(Level.INFO);
			}

			h.setFormatter(new Formatter() {

				@Override
				public String format(LogRecord rec) {
					return sdf.format(cal.getTime()) + " " + rec.getMessage() + "\n";// + " (" + rec.getSourceClassName() + ":" + rec.getThreadID() + ")\n";
				}
			});
		}


		//UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		//UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		for(LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
			if ("Nimbus".equals(info.getName())) {
				UIManager.setLookAndFeel(info.getClassName());
				break;
			}
		}

		System.setProperty("http.proxyHost", "www-proxy.ericsson.se");
		System.setProperty("http.proxyPort", "8080");
		MyConfiguration.getInstance();

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new Kursvinnare();
			}
		});
	}
}
