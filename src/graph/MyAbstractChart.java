package graph;

import java.awt.BorderLayout;
import javax.swing.JPanel;

import org.w3c.dom.NodeList;

public class MyAbstractChart extends JPanel {
	private static final long serialVersionUID = 1L;

	MyValues values = new MyValues();
	MyDates dates = new MyDates();
	MyAbstractGraph graph;

	MyAbstractChart(MyAbstractGraph graph) {
		super(new BorderLayout());

		this.graph = graph;

		this.add(values, BorderLayout.WEST);
		this.add(graph, BorderLayout.CENTER);
		this.add(dates, BorderLayout.SOUTH);
	}


	public void setNodes(NodeList nodes) {
		MyScale scale = graph.setNodes(nodes);

		values.setLegend(scale.x1, scale.x2);
		dates.setLegend(scale.y1, scale.y2);
	}
}
