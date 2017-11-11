package info;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

public class MyInformationModel extends AbstractTableModel{
	private static final long serialVersionUID = 1L;

	Vector<MyInformationData> v = new Vector<MyInformationData>();

	public MyInformationModel() {
		v.add(new MyInformationData(MyInformationData.INFO, "Hello World"));
	}

	public int getColumnCount() {
		return 3;
	}

	public int getRowCount() {
		return v.size();
	}

	public Object getValueAt(int row, int col) {
		MyInformationData info = v.elementAt(row);

		switch(col) {
		case 0:
			return info.time;
		case 1: {
			switch(info.type) {
			case MyInformationData.DEBUG:
				return "DEBUG";
			case MyInformationData.INFO:
				return "INFO";
			case MyInformationData.WARNING:
				return "WARNING";
			case MyInformationData.ERROR:
				return "ERROR";
			}
		}
		case 2:
			return info.slogan;
		}
		return null;
	}

	@Override
	public String getColumnName(int col) {

		switch(col) {
		case 0:
			return "Time";
		case 1:
			return "Severity";
		case 2:
			return "Slogan";
		}
		return null;
	}

	@Override
	public boolean isCellEditable(int row, int col) { return false; }

	public void warning(String string) {
		v.add(new MyInformationData(MyInformationData.WARNING, string));		
	}
	public void error(String string) {
		v.add(new MyInformationData(MyInformationData.ERROR, string));				
	}
	public void info(String string) {
		v.add(new MyInformationData(MyInformationData.INFO, string));						
	}
	public void debug(String string) {
		v.add(new MyInformationData(MyInformationData.DEBUG, string));						
	}
}
