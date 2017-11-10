package se.avelon.edge.tabs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import OmxDataFeed.OmxData;
import scratch.DajoToolbar;
import se.avelon.edge.eventhandling.EventManager;
import se.avelon.edge.guifactories.DajoToolbarFactory;
import se.avelon.utilities.DajoLogger;
import xml.MyXmlUtil;

public class MyWinnersChart extends MyCommonOmxTable implements ActionListener {
	private static final long serialVersionUID = 1L;
	private final static DajoLogger log = DajoLogger.getLogger(MyWinnersChart.class);

	private int rows = 5;

	public MyWinnersChart() {
		super(DajoToolbarFactory.getJToolBar("etc/toolbar_winners.xml"));
		EventManager.getInstance().subscribeOnAction("toolbar.winners.max", this);
		this.habba();
	}

	public void actionPerformed(ActionEvent ae) {
		JComboBox box = (JComboBox)ae.getSource();
		Object selectionValues[] = box.getSelectedObjects();
		for(Object selectionValue : selectionValues) {
			rows = (Integer) selectionValue;
		}
		this.habba();
	}

	private void habba() {
		try {
			Document doc = OmxData.getWinners(rows);
			log.fine("resp=" + MyXmlUtil.documentToString(doc));
			NodeList nodes = MyXmlUtil.xpathSearch(doc, "//response/winners/inst");
			this.setNodes(nodes);
		} 
		catch (TransformerException e) {
			e.printStackTrace();
		} 
		catch (XPathExpressionException e) {
			e.printStackTrace();
		} 
	}
}
