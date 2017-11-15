package se.avelon.edge.tabs;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.table.TableRowSorter;
import org.w3c.dom.NodeList;

public class MyCommonOmxTable extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private final MyCommonOmxModel model = new MyCommonOmxModel();
	private final JTable table = new JTable(model);

	public MyCommonOmxTable(JToolBar toolbar) {
		super(new BorderLayout());

		if(toolbar != null) {
			this.add(toolbar, BorderLayout.SOUTH);
		}
		
		this.add(table.getTableHeader(), BorderLayout.NORTH);
		this.add(new JScrollPane(table), BorderLayout.CENTER);

		table.setFillsViewportHeight(true);
		table.setRowSorter(new TableRowSorter<MyCommonOmxModel>(model));
	}

	public void setEditable(boolean editable) {
		model.setEditable(editable);
	}

	protected void setNodes(NodeList nodes) {
		model.setNodes(nodes);		
	}
}
