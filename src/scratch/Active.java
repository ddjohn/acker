package scratch;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.JComponent;
import org.jfree.chart.ChartPanel;

import se.avelon.edge.eventhandling.EventManager;

public class Active implements ActionListener {
	static Active instance = null;
	ChartPanel chart = null;

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
			chart.setDefaultDirectoryForSaveAs(new File("./save"));
			try {
				chart.doSaveAs();
			}
			catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if(c.getName().compareTo("menu.file.print") == 0) {
			chart.createChartPrintJob();		
		}
	}

	public void setActiveChart(ChartPanel chart) {
		this.chart = chart;		
	}
}
