package stocklist;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class MyStockFilterField extends JTextField implements DocumentListener{

	private static final long serialVersionUID = 1L;
	
	private MyStockListModel model = null;

	public MyStockFilterField(int width, MyStockList list) { 
		super(width);

		this.model = (MyStockListModel)list.getModel();
		this.getDocument().addDocumentListener(this);
	}

	public void changedUpdate(DocumentEvent e) {
		model.setFilter(this.getText());
	}

	public void insertUpdate(DocumentEvent e) {
		model.setFilter(this.getText());
	}

	public void removeUpdate(DocumentEvent e) {
		model.setFilter(this.getText());
	}
}
