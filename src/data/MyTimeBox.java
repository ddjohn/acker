package data;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import javax.swing.JComboBox;

public class MyTimeBox extends JComboBox {
	private static final long serialVersionUID = 1L;

	public MyTimeBox() {
				
		// Actions
		this.collectTimes();
		
		// Fire action
		for(ActionListener listener : this.getActionListeners()) {
			//listener.actionPerformed(new ActionEvent(this.getItemAt(1), ActionEvent.ACTION_PERFORMED, "toolbar.main.time"));
			listener.actionPerformed(new ActionEvent(this.getItemAt(1), 55, "test"));
		}
//		==== java.awt.event.ActionEvent[ACTION_PERFORMED,cmd=comboBoxChanged,when=1339447607866,modifiers=Button1] on toolbar.main.time
		this.setSelectedIndex(3);
	}

	private void collectTimes() {
		this.addItem(new MyTimeData("1 month", Calendar.MONTH, 1));
		this.addItem(new MyTimeData("1 quarter", Calendar.MONTH, 3));
		this.addItem(new MyTimeData("1 year", Calendar.YEAR, 1));
		this.addItem(new MyTimeData("3 year", Calendar.YEAR, 3));
		this.addItem(new MyTimeData("5 year", Calendar.YEAR, 5));
		this.addItem(new MyTimeData("100 year", Calendar.YEAR, 100));
	}
}
