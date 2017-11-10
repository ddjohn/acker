package stocklist;

import java.util.Vector;

import javax.swing.JList;

public class MyStockList extends JList {

	private static final long serialVersionUID = 1L;

	MyStockList() {
		super(new MyStockListModel());
	}
	
    public void addItem(Object o) {
        ((MyStockListModel)this.getModel()).addElement(o);
    }
     
    public void setListData(Vector objects) {
    	for(Object o : objects) {
    		this.addItem(o);
    	}
    }
}
