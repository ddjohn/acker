package se.avelon.edge.stocks;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class DajoStockFilterField extends JTextField implements DocumentListener{

	private static final long serialVersionUID = 1L;
	
	private DajoStockListModel model = null;

	public DajoStockFilterField(int width, DajoStockList list) { 
		super(width);

		this.model = (DajoStockListModel)list.getModel();
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
