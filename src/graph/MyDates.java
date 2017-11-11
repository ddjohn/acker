package graph;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class MyDates extends JPanel {
	private static final long serialVersionUID = 1L;

	private String y1 = "NaN";
	private String y2 = "NaN";
	private Dimension dim = new Dimension(32, 32);

	public MyDates() {
		this.setBackground(Color.white);
		this.setPreferredSize(dim);
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
	//	int x =  g.getFontMetrics().stringWidth(y1);
	//	int y =  g.getFontMetrics().stringWidth(y2);
		//this.setPreferredSize(new Dimension(x > y ? x : y, g.getFontMetrics().getHeight()));
		this.setPreferredSize(new Dimension(0, 8 + g.getFontMetrics().getHeight()));
	}

	void setLegend(String y1, String y2) {
		this.y1 = y1;
		this.y2 = y2;
		
		this.repaint();
	}

	private void drawLegendForIndex(Graphics g) {
		g.drawString(y1, 64,  g.getFontMetrics().getHeight());
		g.drawString(y2, dim.width -  g.getFontMetrics().stringWidth(y1),  g.getFontMetrics().getHeight());
	}
}

