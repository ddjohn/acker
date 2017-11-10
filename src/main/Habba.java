package main;

import gui.toolbar.MyToolbar;
import java.awt.BorderLayout;
import javax.swing.JToolBar;

public class Habba extends Mungo {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JToolBar toolbar = MyToolbar.getJToolBar("etc/toolbar_main.xml");
	
	public Habba() {
		super("Title", "Date");

		this.add(toolbar, BorderLayout.SOUTH);
	}
}
