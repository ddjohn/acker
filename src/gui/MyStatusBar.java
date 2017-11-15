package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import scratch.utilities.DajoLogger;
import data.MyTimeData;
import datafeeders.StockData;
import event.EventManager;

public class MyStatusBar extends JPanel implements ItemListener, ListSelectionListener, ActionListener {
	private static final long serialVersionUID = 1L;
	private DajoLogger log = DajoLogger.getLogger(MyStatusBar.class);

	private final JLabel label = new JLabel();
	private boolean index = false, volume = false, percent = false;
	private String stock = "";
	private String time = "";

	public MyStatusBar() {
		log.constructor("MyStatusBar()");

		// Components
		this.add(label);

		// Register components

		// Subscribe
		EventManager.getInstance().subscribeOnItem("toolbar.index.enable", this);
		EventManager.getInstance().subscribeOnItem("toolbar.volume.enable", this);
		EventManager.getInstance().subscribeOnItem("toolbar.percent.enable", this);
		EventManager.getInstance().subscribeOnList("stock.list", this);
		EventManager.getInstance().subscribeOnAction("toolbar.main.time", this);

		// Properties
		this.setBorder(BorderFactory.createTitledBorder("Status"));

		// Actions
		this.setText();
	}

	public void itemStateChanged(ItemEvent e) {
		JComponent source = (JComponent) e.getSource();

		if(source.getName().compareTo("toolbar.index.enable") == 0) {
			index = e.getStateChange() == ItemEvent.SELECTED;
		}

		else if(source.getName().compareTo("toolbar.volume.enable") == 0) {
			volume = e.getStateChange() == ItemEvent.SELECTED;
		}

		else if(source.getName().compareTo("toolbar.percent.enable") == 0) {
			percent = e.getStateChange() == ItemEvent.SELECTED;
		}
		else {
			System.err.println("Unknown event " + e);
		}
		this.setText();
	}

	private void setText() {
		label.setText("Stock " +  stock + ",Time: " + time + ",Showing graphs: " + index + volume + percent);
	}

	public void valueChanged(ListSelectionEvent e) {
		if(e.getValueIsAdjusting() == false) {
			JList list = (JList)e.getSource();
			Object selectionValues[] = list.getSelectedValues();
			for(Object selectionValue : selectionValues) {
				if(selectionValue instanceof StockData) {
					stock = ((StockData)selectionValue).toString();
				}
			}
			this.setText();
		}
	}

	public void actionPerformed(ActionEvent e) {
		JComboBox box = (JComboBox)e.getSource();
		Object selectionValues[] = box.getSelectedObjects();
		for(Object selectionValue : selectionValues) {
			if(selectionValue instanceof MyTimeData) {
				time = ((MyTimeData)selectionValue).toString();
			}
		}
		this.setText();
	}
}

