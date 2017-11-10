package se.avelon.edge.chartpanels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import datafeeders.StockData;
import se.avelon.edge.datasets.stocks.DajoStockDataset;
import se.avelon.edge.eventhandling.EventManager;
import se.avelon.utilities.DajoLogger;

public class DajoMyStocks extends DajoChartPanel implements ListSelectionListener, ActionListener {

	private static final long serialVersionUID = 1L;
	private static final DajoLogger log = DajoLogger.getLogger(DajoMyStocks.class);

	private final DajoStockDataset ds = new DajoStockDataset();
	private StockData stock = null;
	CategoryPlot plot = null;

	public DajoMyStocks() {
		super("etc/toolbars/mystocks.xml");
		log.constructor("DajoChart()");

		plot = new CategoryPlot(ds, new CategoryAxis(), new NumberAxis(), ds);
		this.setChart("Stock Data", plot);

		EventManager.getInstance().subscribeOnList("stock.list", this);
		EventManager.getInstance().subscribeOnAction("toolbar.mystocks.add", this);
		EventManager.getInstance().subscribeOnAction("toolbar.mystocks.remove", this);
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
		}
	}

	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton)e.getSource();

		System.out.println("Got " + button.getName());
		if(button.getName().equals("toolbar.mystocks.add")) {
			System.out.println("Add " + stock);
			ds.add(stock);
			this.fix();
		}
		else if(button.getName().equals("toolbar.mystocks.remove")) {
			System.out.println("Remove " + stock);
			ds.remove(stock);
			this.fix();
		}
	}
}