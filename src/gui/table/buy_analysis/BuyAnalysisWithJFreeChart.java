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
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import JFreeChartAdapter.JFreeScatterChartAdapter;
import util.MyXmlUtil;
import data.MyTimeData;
import event.EventManager;

public class BuyAnalysisWithJFreeChart extends JFreeScatterChartAdapter implements ActionListener {
	

	private static final long serialVersionUID = 1L;
	private static MyScatterChartAdaptation adapt = null;

	public BuyAnalysisWithJFreeChart() {
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
	
	public static Document fix(String name, String id, Document doc) throws Exception {
		Document newDoc = MyXmlUtil.createEmptyDocument();

		Element node = newDoc.createElement("stocks");
		newDoc.appendChild(node);

		Element node2 = newDoc.createElement("stock");
		node2.setAttribute("name", name);
		node2.setAttribute("id", id);
		node.appendChild(node2);

		Node dup = newDoc.importNode(doc.getFirstChild(), true);
		node2.appendChild(dup);

		return newDoc;
	}
}