package stocklist;

import java.util.ArrayList;
import javax.swing.AbstractListModel;
import scratch.utilities.DajoLogger;

public class MyStockListModel extends AbstractListModel {

	private static final long serialVersionUID = 1L;
	private final static DajoLogger log = DajoLogger.getLogger(MyStockListModel.class);
	
	private ArrayList<Object> items = new ArrayList<Object>();
	private ArrayList<Object> filter = new ArrayList<Object>();

	public Object getElementAt(int index) {
		return filter.get(index);
	}

	public int getSize() {
		return filter.size();
	}

	public void setFilter(String f) {
		log.fine("Set stock filter");
		filter.clear();

		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).toString().toLowerCase().indexOf(f.toLowerCase(), 0) != -1) {
				filter.add(items.get(i));
			}
		}
		this.fireContentsChanged(this, 0, getSize());
	}

	public void addElement(Object o) {
		items.add(o);
		this.setFilter("");
	}
}
