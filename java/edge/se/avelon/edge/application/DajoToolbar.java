package se.avelon.edge.application;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JToolBar;

public class DajoToolbar extends JToolBar {

	private static final long serialVersionUID = 1L;

	public DajoToolbar() {

		JPanel comp = new JPanel();
		comp.setBorder(BorderFactory.createTitledBorder("Index"));
		
		comp.add(new JCheckBox("Enable"));
		comp.add(new JCheckBox("Market trend (200)"));
		comp.add(new JCheckBox("Candle"));
		comp.add(new JCheckBox("Donchian (20)"));
		comp.add(new JCheckBox("Index"));
		comp.add(new JCheckBox("OHLC"));

		this.add(comp);
	}
}
