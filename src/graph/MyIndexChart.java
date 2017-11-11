package graph;

import java.util.Vector;

import javax.swing.JCheckBox;

public class MyIndexChart extends MyAbstractChart {
	private static final long serialVersionUID = 1L;
	
	private final static MyIndexGraph graph = new MyIndexGraph();
	
	public MyIndexChart() {
		super(graph);
	}

	public void forwardListenerFromToolbar(Vector<JCheckBox> vector) {
		for(JCheckBox box : vector) {
			box.addItemListener(graph);
		}	
	}
}
