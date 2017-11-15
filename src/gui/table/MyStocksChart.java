package gui.table;

import event.EventManager;
import gui.MyCommonOmxTable;
import gui.toolbar.MyToolbar;
import info.MyInformationTable;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;

import javax.swing.JButton;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;

import net.OmxData;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import util.MyUtil;
import util.MyXmlUtil;

public class MyStocksChart extends MyCommonOmxTable implements ActionListener {
	private static final long serialVersionUID = 1L;
	private Document doc = null;

	public MyStocksChart() {
		super(MyToolbar.getJToolBar("etc/toolbar_mystocks.xml"));
		//super(null);

		EventManager.getInstance().subscribeOnAction("mystocks.refresh", this);

		try {
			doc = MyXmlUtil.getDocument("mystocks.xml");
			MyInformationTable.getInstance().debug("resp=" + MyXmlUtil.documentToString(doc));
			NodeList nodes = MyXmlUtil.xpathSearch(doc, "//stocks/stock");
			this.setEditable(true);
			this.setNodes(nodes);
		} 
		catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	public void actionPerformed(ActionEvent e) {
		try {
			NodeList nodes = MyXmlUtil.xpathSearch(doc, "//stocks/stock");
			for(int i = 0; i < nodes.getLength(); i++) {
				Element elem = (Element) nodes.item(i);
				double redefined = 0.0;
				redefined = MyUtil.parseN(elem.getAttribute("redefined"), 0.0).doubleValue();
				double price = OmxData.getLatestPrice(elem.getAttributes().getNamedItem("id").getNodeValue());
				double percent = (price-redefined)/redefined;
				if(percent > 0.05) {
					elem.setAttribute("lsp", "+ " + price + " (" + percent + ")");
				}
				else if(percent < -0.05) {
					elem.setAttribute("lsp", "- " + price + " (" + percent + ")");
				}
				else {
					elem.setAttribute("lsp", "" + (char)177 + " " + price + " (" + percent + ")");
				}
			}
			this.repaint();
		} 
		catch (XPathExpressionException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
}
