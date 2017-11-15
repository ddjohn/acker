package graph;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import util.ShortNumbers;

public class MyValues extends JPanel {
	private static final long serialVersionUID = 1L;

	String x1 = "NaN";
	String x2 = "NaN";

	Dimension dim = new Dimension(32, 32);

	MyValues() {
		this.setPreferredSize(dim);
		this.setBackground(Color.WHITE);
	}

	@Override 
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		this.setTextWidth(g);

		dim = this.getSize();

		Graphics2D g2D = (Graphics2D)g; 
		g2D.setStroke(new BasicStroke(1F));
		this.drawLegendForIndex(g);
	}

	void setTextWidth(Graphics g) {
	//	int x =  g.getFontMetrics().stringWidth(x1);
	//	int y =  g.getFontMetrics().stringWidth(x2);
		//this.setPreferredSize(new Dimension(x > y ? x : y, 32));
		this.setPreferredSize(new Dimension(64, 0));
	}

	void setLegend(String x1, String x2) {
		this.x1 = ShortNumbers.shorten(x1);
		this.x2 = ShortNumbers.shorten(x2);
		
		this.repaint();
	}

	private void drawLegendForIndex(Graphics g) {
		g.drawString(x2, 0, g.getFontMetrics().getHeight());
		g.drawString(x1, 0, dim.height-0);
	}
}

