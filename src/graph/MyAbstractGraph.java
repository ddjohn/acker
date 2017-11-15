package graph;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.text.ParseException;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import util.MyUtil;

public class MyAbstractGraph extends JPanel {
	private static final long serialVersionUID = 1L;

	protected Dimension dim = new Dimension(640, 400);
	protected double scale = 0.05;
	protected boolean draw = false;
	NodeList nodes;

	MyAbstractGraph() {
		this.setBackground(Color.WHITE);
		this.setBorder(BorderFactory.createEtchedBorder());
		this.setPreferredSize(dim);
	}

	public MyScale setNodes(NodeList nodes) {
		this.nodes = nodes;

		draw = true;
		this.repaint();

		return new MyScale("NaN", "NaN", "NaN", "NaN");
	}

	@Override 
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		dim = this.getSize();
	}
	
	protected double getHigh(int pos, int days) {
		double high = 0;
		for(int j =  pos - days + 1; j <= pos; j++) {
			try {
				double tmp = MyUtil.parseN(getValue(nodes.item(j), "cp")).doubleValue();
				if(tmp > high) high= tmp;
			} 
			catch(ParseException e) {} 
		}
		return high;
	}


	protected double getLow(int pos, int days) {
		double low = 999999;
		for(int j =  pos - days + 1; j <= pos; j++) {
			try {
				double tmp = MyUtil.parseN(getValue(nodes.item(j), "cp")).doubleValue();
				if(tmp < low) low= tmp;
			} 
			catch(ParseException e) {} 
		}
		return low;
	}

	protected void drawLine(Graphics g, double i, double j, double k, double l) {
		g.drawLine((int)i, (int)(dim.height*scale+(1-scale)*j)-4, (int)k,  (int)(dim.height*scale+(1-scale)*l)-4);
	}

	public String getValue(Node node, String name) {
		return node.getAttributes().getNamedItem(name).getNodeValue();
	} 
}
