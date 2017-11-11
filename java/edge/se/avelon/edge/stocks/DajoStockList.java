package se.avelon.edge.stocks;

import java.util.Vector;

import javax.swing.JList;

public class DajoStockList extends JList {

	private static final long serialVersionUID = 1L;

	DajoStockList() {
		super(new DajoStockListModel());
	}
	
    public void addItem(Object o) {
        ((DajoStockListModel)this.getModel()).addElement(o);
    }
    
    public void setListData(Vector objects) {
    	for(Object o : objects) {
    		this.addItem(o);
    	}
    }
} 
