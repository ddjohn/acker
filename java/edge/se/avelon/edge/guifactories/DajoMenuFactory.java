package se.avelon.edge.guifactories;

import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import se.avelon.edge.eventhandling.EventManager;
import se.avelon.edge.utilities.DajoXmlUtil;

public class DajoMenuFactory {

	public static JMenuBar getJMenuBar() {
		try {
			Document doc =  DajoXmlUtil.getDocument("etc/menu.xml");
			return (JMenuBar)DajoMenuFactory.getComponent(doc.getChildNodes().item(0));
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
		if(node.getNodeName().compareTo("menubar") == 0) {
			comp = new JMenuBar();
		}
		else if(node.getNodeName().compareTo("menu") == 0) {
			comp = new JMenu(node.getAttributes().getNamedItem("name").getNodeValue());
		}
		else if(node.getNodeName().compareTo("menuitem") == 0) {
			comp = new JMenuItem(node.getAttributes().getNamedItem("name").getNodeValue(), new ImageIcon(node.getAttributes().getNamedItem("icon").getNodeValue()));
		}
		else if(node.getNodeName().compareTo("separator") == 0) {
			comp = null;
		}

		if(node.getAttributes().getNamedItem("action") != null) {
			EventManager.getInstance().registerAction(comp, node.getAttributes().getNamedItem("action").getNodeValue());
		}

		NodeList nodes = node.getChildNodes();
		for(int i = 0 ; i < nodes.getLength(); i++) {
			if(comp != null  && nodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
				JComponent c = DajoMenuFactory.getComponent(nodes.item(i));
				if(c == null) {
					((JMenu)comp).addSeparator();
				}
				else {
					comp.add(c);
				}
			}
		}
		return comp;
	}
}