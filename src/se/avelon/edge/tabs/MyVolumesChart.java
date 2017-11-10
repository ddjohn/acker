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

public class MyVolumesChart extends MyCommonOmxTable implements ActionListener {
	private static final long serialVersionUID = 1L;
	private final static DajoLogger log = DajoLogger.getLogger(MyVolumesChart.class);

	private int rows = 5;

	public MyVolumesChart() {
		super(DajoToolbarFactory.getJToolBar("etc/toolbar_volumes.xml"));
		EventManager.getInstance().subscribeOnAction("toolbar.volumes.max", this);
		this.habba();
	}

	@Override
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
			Document doc = OmxData.getMostTraded(rows);
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
