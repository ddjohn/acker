package data;

import java.awt.event.ActionListener;
import javax.swing.JComboBox;

public class MyMaxNuberOfRows extends JComboBox implements ActionListener {
	private static final long serialVersionUID = 1L;

	public MyMaxNuberOfRows() {
				
		// Actions
		this.collectRows();
	}

	private void collectRows() {
		this.addItem(5);
		this.addItem(10);
		this.addItem(20);
		this.addItem(100);
	} 
}
