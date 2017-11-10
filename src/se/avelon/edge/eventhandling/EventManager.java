package se.avelon.edge.eventhandling;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Vector;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import se.avelon.utilities.DajoLogger;

public class EventManager implements ItemListener, ActionListener, ListSelectionListener, ChangeListener {
	
	private DajoLogger log = DajoLogger.getLogger(EventManager.class);
	
	private static EventManager instance = null;
	private final Vector<JCheckBox> items = new Vector<JCheckBox>();
	private final Vector<JList> lists = new Vector<JList>();
	private final Vector<JComponent> actions = new Vector<JComponent>();
	private final Vector<JTabbedPane> panes = new Vector<JTabbedPane>();
	boolean debug = false;

	public static EventManager getInstance() {
		return instance == null ? instance = new EventManager() : instance;
	}

	public void registerItem(JCheckBox item, String name) {
		item.setName(name);
		if(item.getName() != null) {		
			log.fine("Register: " + item.getName());
			items.add(item);	
		}
		else {
			log.severe("No name set, dropping register");			
		}
		if(debug) item.addItemListener(this);
	}

	public void subscribeOnItem(String name, ItemListener listener) {
		boolean found = false;
		for(JCheckBox item : items) {
			if(item.getName().compareTo(name) == 0) {
				item.addItemListener(listener);
				found = true;
			}
		}
		if(found) {
			log.fine("Name " +  name + " found");	
		}
		else {
			log.severe("Name " +  name + " not found");	
		}
	}

	public void subscribeOnChange(String name, ChangeListener listener) {
		boolean found = false;
		for(JTabbedPane pane : panes) {
			if(pane.getName().compareTo(name) == 0) {
				pane.addChangeListener(listener);
				found = true;
			}
		}
		if(found) {
			log.fine("Name " +  name + " found");	
		}
		else {
			log.severe("Name " +  name + " not found");	
		}
	}

	public void registerListSelection(JList item, String name) {
		item.setName(name);
		if(item.getName() != null) {		
			log.fine("Register: " + item.getName());
			lists.add(item);	
		}
		else {
			log.severe("No name set, dropping register");			
		}
		if(debug) item.addListSelectionListener(this);
	}

	public void subscribeOnList(String name, ListSelectionListener listener) {
		boolean found = false;
		for(JList item : lists) {
			if(item.getName().compareTo(name) == 0) {
				item.addListSelectionListener(listener);
				found = true;
			}
		}
		if(found) {
			log.fine("Name " +  name + " found");	
		}
		else {
			log.severe("Name " +  name + " not found");	
		}
	}

	public void registerAction(JComponent item, String name) {
		item.setName(name);
		if(item.getName() != null) {		
			log.fine("Register: " + item.getName());
			actions.add(item);	
		}
		else {
			log.severe("No name set, dropping register");			
		}		
		if(debug) {
			if(item instanceof JComboBox) {
				((JComboBox)item).addActionListener(this);
			}
			else if(item instanceof JButton)  {
				((AbstractButton)item).addActionListener(this);
			}
		}
	}

	public void registerChange(JComponent item, String name) {
		item.setName(name);
		if(item.getName() != null) {		
			log.fine("Register: " + item.getName());
			panes.add((JTabbedPane)item);	
		}
		else {
			log.severe("No name set, dropping register");			
		}		
		if(debug) {
			if(item instanceof JTabbedPane) {
				((JTabbedPane)item).addChangeListener(this);
			}
		}
	}

	public void subscribeOnAction(String name, ActionListener listener) {
		boolean found = false;
		for(JComponent item : actions) {
			if(item instanceof JComboBox) {
				if(item.getName().compareTo(name) == 0) {
					((JComboBox)item).addActionListener(listener);
					found = true;
				}	
			}

			if(item instanceof JButton) {
				if(item.getName().compareTo(name) == 0) {
					((JButton)item).addActionListener(listener);
					found = true;
				}					
			}

			if(item instanceof JMenuItem) {
				if(item.getName().compareTo(name) == 0) {
					((JMenuItem)item).addActionListener(listener);
					found = true;
				}					
			}
		}

		if(found) {
			log.fine("Name " +  name + " found");	
		}
		else {
			log.severe("Name " +  name + " not found");	
		}
	}

	public void actionPerformed(ActionEvent e) {
		log.info("event=" + e);
	}

	public void itemStateChanged(ItemEvent e) {
		log.info("event=" + e);		
	}

	public void valueChanged(ListSelectionEvent e) {
		log.info("event=" + e);		
	}

	public void stateChanged(ChangeEvent e) {
		log.info("event=" + e);				
	}
}
