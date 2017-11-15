package gui.table.buy_analysis;

import gui.table.Config;
import info.MyInformationTable;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import javax.swing.JButton;
import net.OmxData;
import org.w3c.dom.Document;
import RawAdapter.RawChartAdapter;
import util.MyXmlUtil;
import data.MyTimeData;
import event.EventManager;

public class BuyAnalysisWithRaw extends RawChartAdapter implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	private static MyScatterChartAdaptation adapt = null;

	public BuyAnalysisWithRaw() {
		super("Buy Analysis", "Market Trend (%)", "Fibonacci", adapt = new MyScatterChartAdaptation());

		JButton button;
		this.add(button = new JButton("Refresh"), BorderLayout.SOUTH);

		EventManager.getInstance().registerAction(button, "buy3.refresh");
		EventManager.getInstance().subscribeOnAction("buy3.refresh", this);
	}

	public void actionPerformed(ActionEvent e) {
		try {
			Document doc = MyXmlUtil.createDocumentFromString("<stocks/>");

			for(String stock[] : Config.stocks) {
				MyInformationTable.getInstance().debug("Stock: " + stock[1]);
				MyInformationTable.getInstance().debug("getDataSeriers=" + MyXmlUtil.documentToString(BuyAnalysisWithJFreeChart.fix(stock[1], stock[0], OmxData.getStockData(new MyTimeData("1 year", Calendar.YEAR, 1), stock[0]))));
				doc = MyXmlUtil.merge(doc, BuyAnalysisWithJFreeChart.fix(stock[1], stock[0], OmxData.getStockData(new MyTimeData("1 year", Calendar.YEAR, 1), stock[0])));
			}
			MyInformationTable.getInstance().info("> " + MyXmlUtil.documentToString(doc)); 
			adapt.set(doc);
		} 
		catch (Exception e1) {
			e1.printStackTrace();
		} 
	}
}