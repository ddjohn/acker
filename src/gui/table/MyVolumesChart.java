package gui.table;

import event.EventManager;
import gui.MyCommonOmxTable;
import gui.toolbar.MyToolbar;
import info.MyInformationTable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;
import net.OmxData;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import util.MyXmlUtil;

public class MyVolumesChart extends MyCommonOmxTable implements ActionListener {
	private static final long serialVersionUID = 1L;

	private int rows = 5;

	public MyVolumesChart() {
		super(MyToolbar.getJToolBar("etc/toolbar_volumes.xml"));
		EventManager.getInstance().subscribeOnAction("toolbar.volumes.max", this);
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
			Document doc = OmxData.getMostTraded(rows);
			MyInformationTable.getInstance().debug("resp=" + MyXmlUtil.documentToString(doc));
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
