package gui;

import java.util.HashMap;
import javax.swing.table.AbstractTableModel;
import org.w3c.dom.NodeList;

public class MyCommonOmxModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;

	private NodeList nodes = null;
	private static final HashMap<String, String> map = new HashMap<String, String>();
	private boolean editable = false;

	public MyCommonOmxModel() {

		map.put("ch", "Change");
		map.put("chp", "Change (%)");
		map.put("fnm", "Full Name");
		map.put("nm", "Name");
		map.put("id", "Id");
		map.put("lsp", "Last Price");
		map.put("bp", "Buying Price");
		map.put("ap", "Asking Price");
		map.put("bv", "Buying Volume");
		map.put("av", "Asking Volume");
		map.put("cr", "Currency");
		map.put("op", "Opening Price");
		map.put("cp", "Closing Price");
		map.put("lp", "Lowest Price");
		map.put("hp", "Highest Price");
		map.put("dt", "Date");

		map.put("redefined", "Redefined");
		map.put("buy", "Buy");
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	public int getColumnCount() {
		if(nodes == null) {
			return 1;
		}
		else {
			//DAJO Test
			if(nodes == null) return 0;
			if(nodes.item(0) == null) return 0;
			if(nodes.item(0).getAttributes() == null) return 0;
			
			return nodes.item(0).getAttributes().getLength();
		}
	}

	public int getRowCount() {
		if(nodes == null) {
			return 1;
		}
		else {
			return nodes.getLength();	
		}
	}

	public Object getValueAt(int row, int col) {
		if(nodes == null) {
			return "No data";
		}
		else {
			return nodes.item(row).getAttributes().item(col).getNodeValue();
		}
	}

	@Override
	public void setValueAt(Object o, int row, int col) {
		if(nodes == null || editable == false) {
			
		}
		else {
			nodes.item(row).getAttributes().item(col).setNodeValue("" + o);
		}
	}

	@Override
	public String getColumnName(int col) {
		if(nodes == null) {
			return "Ooops";
		}
		else {
			return map.get(nodes.item(0).getAttributes().item(col).getNodeName());
		}
	}

	@Override
	public boolean isCellEditable(int row, int col) { return editable; }

	public void setNodes(NodeList nodes) {
		this.nodes = nodes;
		this.fireTableDataChanged();
		this.fireTableStructureChanged();
	}
}

