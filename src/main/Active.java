package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JComponent;

import org.jfree.chart.ChartPanel;

import event.EventManager;
import gui.table.candlestick.MyCandleChart;

public class Active implements ActionListener {
	static Active instance = null;
	MyCandleChart chart = null;

	private Active() {
		EventManager.getInstance().subscribeOnAction("menu.file.save", this);
		EventManager.getInstance().subscribeOnAction("menu.file.saveas", this);
		EventManager.getInstance().subscribeOnAction("menu.file.print", this);
		EventManager.getInstance().subscribeOnAction("menu.file.preview", this);
	}

	public static Active getInstance() {
		if(instance == null) {
			instance = new Active();
		}
		return instance;
	}

	public void actionPerformed(ActionEvent e) {
		System.out.println("2e=" + e.getActionCommand());
		JComponent c = (JComponent)e.getSource();

		if(c.getName().compareTo("menu.file.saveas") == 0) {
			chart.doSaveAs();
	}
		if(c.getName().compareTo("menu.file.print") == 0) {
			chart.print();
	}

	}

	public void setActiveChart(MyCandleChart chart) {
		this.chart = chart;		
		
	}
}
