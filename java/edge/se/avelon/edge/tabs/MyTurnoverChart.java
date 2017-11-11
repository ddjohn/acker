package se.avelon.edge.tabs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import se.avelon.edge.application.DajoToolbar;
import se.avelon.edge.eventhandling.EventManager;
import se.avelon.edge.guifactories.DajoToolbarFactory;
import se.avelon.edge.omx.datafeed.OmxData;
import se.avelon.edge.utilities.DajoLogger;
import se.avelon.edge.utilities.MyXmlUtil;

public class MyTurnoverChart extends MyCommonOmxTable implements ActionListener {
	private static final long serialVersionUID = 1L;
	private final static DajoLogger log = DajoLogger.getLogger(MyTurnoverChart.class);

	private int rows = 5;

	public MyTurnoverChart() {
		super(DajoToolbarFactory.getJToolBar("etc/toolbar_turnover.xml"));
		EventManager.getInstance().subscribeOnAction("toolbar.turnover.max", this);
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
			Document doc = OmxData.getHighestTurnover(rows);
			log.fine("resp=" + MyXmlUtil.documentToString(doc));
			NodeList nodes = MyXmlUtil.xpathSearch(doc, "//response/inst");
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
