package gui.toolbar;

import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import data.MyMaxNuberOfRows;
import data.MyTimeBox;
import util.MyXmlUtil;
import event.EventManager;

public class MyToolbar {

	public static JToolBar getJToolBar(String file) {
		try {
			Document doc =  MyXmlUtil.getDocument(file);
			return (JToolBar)MyToolbar.getComponent(doc.getChildNodes().item(0));
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static JComponent getComponent(Node node) {
		JComponent comp = null;
		if(node.getNodeName().compareTo("toolbar") == 0) {
			comp = new JToolBar();
		}
		else if(node.getNodeName().compareTo("border") == 0) {
			comp = new JPanel();
			comp.setBorder(BorderFactory.createTitledBorder(node.getAttributes().getNamedItem("name").getNodeValue()));
		}
		else if(node.getNodeName().compareTo("label") == 0) {
			comp = new JLabel(node.getAttributes().getNamedItem("name").getNodeValue());
		}
		else if(node.getNodeName().compareTo("mytimebox") == 0) {
			comp =(new MyTimeBox());
			if(node.getAttributes().getNamedItem("action") != null) {
				EventManager.getInstance().registerAction((MyTimeBox)comp, node.getAttributes().getNamedItem("action").getNodeValue());
			}
		}
		else if(node.getNodeName().compareTo("maxnumberofrows") == 0) {
			comp =(new MyMaxNuberOfRows());
			if(node.getAttributes().getNamedItem("action") != null) {
				EventManager.getInstance().registerAction((MyMaxNuberOfRows)comp, node.getAttributes().getNamedItem("action").getNodeValue());
			}
		}
		else if(node.getNodeName().compareTo("checkbox") == 0) {
			comp = new JCheckBox(node.getAttributes().getNamedItem("name").getNodeValue()/*, new ImageIcon(node.getAttributes().getNamedItem("icon").getNodeValue())*/);
			if(node.getAttributes().getNamedItem("action") != null) {
				EventManager.getInstance().registerItem((JCheckBox)comp, node.getAttributes().getNamedItem("action").getNodeValue());
			}
		}
		else if(node.getNodeName().compareTo("button") == 0) {
			comp = new JButton(node.getAttributes().getNamedItem("name").getNodeValue()/*, new ImageIcon(node.getAttributes().getNamedItem("icon").getNodeValue())*/);
			if(node.getAttributes().getNamedItem("action") != null) {
				EventManager.getInstance().registerAction((JButton)comp, node.getAttributes().getNamedItem("action").getNodeValue());
			}
		}

		NodeList nodes = node.getChildNodes();
		for(int i = 0 ; i < nodes.getLength(); i++) {
			if(comp != null  && nodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
				JComponent c = MyToolbar.getComponent(nodes.item(i));
				if(c == null) {
					//((JToolBar)comp).addSeparator();
				}
				else {
					comp.add(c);
				}
			}
		}
		return comp;
	}
}