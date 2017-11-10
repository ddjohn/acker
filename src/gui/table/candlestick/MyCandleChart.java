package gui.table.candlestick;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.xml.xpath.XPathExpressionException;

import main.Active;
import JFreeChartAdapter.JFreeCandleChartAdapter;
import data.MyTimeData;
import datafeeders.CandleFeedInterface;
import datafeeders.StockData;
import event.EventManager;
import factories.InterfaceFactory;

public class MyCandleChart extends JFreeCandleChartAdapter implements ListSelectionListener, ActionListener {

	private static final long serialVersionUID = 1L;
	private StockData stock;
	private MyTimeData time;
	private static MyChartAdaptation2 adapt = null;

	public MyCandleChart() {
		super("Candlestick", "Date", "SEK", adapt = new MyChartAdaptation2());

		EventManager.getInstance().subscribeOnList("stock.list", this);
		EventManager.getInstance().subscribeOnAction("toolbar.main.time", this);
		
		Active.getInstance().setActiveChart(this);
	}

	public void valueChanged(ListSelectionEvent e) {
		if(e.getValueIsAdjusting() == false) {
			JList list = (JList)e.getSource();
			Object selectionValues[] = list.getSelectedValues();
			for(Object selectionValue : selectionValues) {
				if(selectionValue instanceof StockData) {
					stock = (StockData)selectionValue;
				}
			}

			this.updataNodes();		
		}
	}

	private void updataNodes() {
		if(stock != null && time != null) {
			try {
				CandleFeedInterface candle = InterfaceFactory.getFeeder(); //new OmxCandleFeedAdapter();
				adapt.set(candle.getData(stock.id, time.type, time.value));
				this.fix();
			} 
			catch (XPathExpressionException e) {
				e.printStackTrace();
			} 
		}
	}

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

}