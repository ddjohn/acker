package gui;

import graph.MyIndexChart;
import graph.MyPercentChart;
import graph.MyVolumeChart;
import gui.toolbar.MyToolbar;
import info.MyInformationTable;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;
import net.OmxData;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import data.MyTimeBox;
import data.MyTimeData;
import datafeeders.StockData;
import event.EventManager;
import scratch.utilities.DajoLogger;
import util.MyXmlUtil;

public class MyMain extends JPanel implements ListSelectionListener, ActionListener, ItemListener {
	
	private static final long serialVersionUID = 1L;
	private static final DajoLogger log = DajoLogger.getLogger(MyMain.class);
	
	private StockData stock = null;
	private MyTimeData time = null;

	// Startup order
	private final JToolBar toolbar = MyToolbar.getJToolBar("etc/toolbar_main.xml");
	private final MyIndexChart index = new MyIndexChart();
	private final MyVolumeChart volume = new MyVolumeChart();
	private final MyPercentChart percent = new MyPercentChart();
	private final JPanel panel = new JPanel(new GridLayout(0,1));

	public MyMain() {
		super(new BorderLayout());

		// Components
		this.add(panel, BorderLayout.CENTER);
		this.add(toolbar, BorderLayout.SOUTH);

		// Register components

		// Subscribe on events
		EventManager.getInstance().subscribeOnList("stock.list", this);
		EventManager.getInstance().subscribeOnItem("toolbar.index.enable", this);
		EventManager.getInstance().subscribeOnItem("toolbar.volume.enable", this);
		EventManager.getInstance().subscribeOnItem("toolbar.percent.enable", this);
		EventManager.getInstance().subscribeOnAction("toolbar.main.time", this);

		// Properties
		panel.setPreferredSize(new Dimension(800,600));

		// Action
	}

	public void valueChanged(ListSelectionEvent e) {

		if(e.getValueIsAdjusting() == false) {
			JList list = (JList)e.getSource();
			Object selectionValues[] = list.getSelectedValues();
			for(Object selectionValue : selectionValues) {
				if(selectionValue instanceof StockData) {
					stock = (StockData) selectionValue;
				}
			}

			this.updateNodes();		
		}
	}

	private void updateNodes() {
		if(stock != null && time != null) {
			try {
				Document doc = OmxData.getStockData(time, stock.id);
				MyInformationTable.getInstance().debug("resp=" + MyXmlUtil.documentToString(doc)); 
				NodeList nodes = MyXmlUtil.xpathSearch(doc, "//response/hi");

				index.setNodes(nodes);
				volume.setNodes(nodes);
				percent.setNodes(nodes);

			} 
			catch (XPathExpressionException e) {
				e.printStackTrace();
			} 
			catch (TransformerException e) {
				e.printStackTrace();
			}
		}		
	}

	public void actionPerformed(ActionEvent e) {
		JComboBox box = (JComboBox)e.getSource();
		Object selectionValues[] = box.getSelectedObjects();
		for(Object selectionValue : selectionValues) {
			if(selectionValue instanceof MyTimeData) {
				time = (MyTimeData)selectionValue;
			}
		}
		this.updateNodes();		
	}

	public void itemStateChanged(ItemEvent e) {
		JCheckBox source = (JCheckBox)e.getSource();

		if(source.getName().compareTo("toolbar.index.enable") == 0) {
			if(e.getStateChange() == ItemEvent.SELECTED) {
				panel.add(index);
				this.revalidate();
			}
			else {
				panel.remove(index);
				this.revalidate();
			}
		}

		else if(source.getName().compareTo("toolbar.volume.enable") == 0) {
			if(e.getStateChange() == ItemEvent.SELECTED) {
				panel.add(volume);
				this.revalidate();
			}
			else {
				panel.remove(volume);
				this.revalidate();
			}
		}

		else if(source.getName().compareTo("toolbar.percent.enable") == 0) {
			if(e.getStateChange() == ItemEvent.SELECTED) {
				panel.add(percent);
				this.revalidate();
			}
			else {
				panel.remove(percent);
				this.revalidate();
			}
		}

		else {
			log.severe("Uknown event " + e);
		}
	}

	public void startStock(int i) {
		//stockList.startup(i);
	}

	public void startTime(int j) {
		for(int i = 0; i< toolbar.getComponentCount(); i++) {
			if(toolbar.getComponentAtIndex(i) instanceof MyTimeBox) {
				MyTimeBox box = (MyTimeBox) toolbar.getComponentAtIndex(i);
				box.setSelectedIndex(j);
			}
		}
	}

	public void startEnableIndex() {
		for(int i = 0; i < toolbar.getComponentCount(); i++) {

			if(toolbar.getComponentAtIndex(i) instanceof JPanel) {
				JPanel panel = (JPanel) toolbar.getComponentAtIndex(i);

				for(int j = 0; j < panel.getComponentCount(); j++) {

					if(panel.getComponent(j) instanceof JCheckBox) {
						JCheckBox box = (JCheckBox) panel.getComponent(j);

						if(box.getName().compareTo("toolbar.index.enable") == 0 || box.getName().compareTo("toolbar.index.candle") == 0)
							box.setSelected(true);
					}
				}
			}
		}
	}
}