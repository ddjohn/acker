package gui.table;

import gui.MyCommonOmxTable;
import info.MyInformationTable;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;
import net.OmxData;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import util.MyXmlUtil;

public class MyMarketChart extends MyCommonOmxTable {
	private static final long serialVersionUID = 1L;

	public MyMarketChart() {
		super(null);
		
		this.add(new JButton("Refresh"), BorderLayout.SOUTH);

		try {
			Document doc = OmxData.getMarkets();
			MyInformationTable.getInstance().debug("resp=" + MyXmlUtil.documentToString(doc));
			NodeList nodes = MyXmlUtil.xpathSearch(doc, "//response/inst");
			this.setNodes(nodes);
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}
