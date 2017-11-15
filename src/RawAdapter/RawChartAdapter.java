package RawAdapter;

import graph.ScatterGraphInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

public class RawChartAdapter extends JPanel {

	private static final long serialVersionUID = 1L;
	ScatterGraphInterface iface;
	public RawChartAdapter(String title, String x, String y, ScatterGraphInterface iface) {
		this.iface = iface;

		this.setBackground(Color.white);
		this.setLayout(new BorderLayout());
	}	

	@Override 
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Dimension dim = this.getSize();
		g.drawLine(0, dim.height/2, dim.width, dim.height/2);
		g.drawLine(dim.width/2, 0, dim.width/2, dim.height);
		g.drawString("Positive market trend", dim.width-155, dim.height/2);
		g.drawString("Negative market trend", 0, dim.height/2);
		g.drawString("Fibonacci", dim.width/2-35, 50);

		for(int item = 0; item < iface.getNumberOfItems(); item++) {
			g.setColor(iface.getTextColor(item));
			g.drawOval(                       (int)(dim.width/2 + 0.02 * dim.width*iface.getX(item)), (int)(dim.height/2 - 2 * dim.height*iface.getY(item)), 3, 3);
			g.drawString(iface.getName(item), (int)(dim.width/2 + 0.02 * dim.width*iface.getX(item)), (int)(dim.height/2 - 2 * dim.height*iface.getY(item))-2);

			//	if(iface.getStyle(item) % ScatterGraphInterface.BIG == 0 || iface.getStyle(item) % ScatterGraphInterface.BIGGER == 0) {
			if((iface.getStyle(item) & ScatterGraphInterface.BIG) > 0 || (iface.getStyle(item) & ScatterGraphInterface.BIGGER) > 0) {
				g.drawOval((int)(dim.width/2 + 0.02 * dim.width*iface.getX(item))-2, (int)(dim.height/2 - 2 * dim.height*iface.getY(item))-1, 7, 7);
			}

			if((iface.getStyle(item) & ScatterGraphInterface.BIGGER) > 0) {
				g.drawOval((int)(dim.width/2 + 0.02 * dim.width*iface.getX(item))-2, (int)(dim.height/2 - 2 * dim.height*iface.getY(item))-1, 9, 9);
			}

			if((iface.getStyle(item) & ScatterGraphInterface.HIGH) > 0) {
				g.drawLine((int)(dim.width/2 + 0.02 * dim.width*iface.getX(item)),   (int)(dim.height/2 - 2 * dim.height*iface.getY(item)),
						(int)(dim.width/2 + 0.02 * dim.width*iface.getX(item)+3), (int)(dim.height/2 - 2 * dim.height*iface.getY(item)));
			}

			if((iface.getStyle(item) & ScatterGraphInterface.LOW) > 0) {
				g.drawLine((int)(dim.width/2 + 0.02 * dim.width*iface.getX(item)),   (int)(dim.height/2 - 2 * dim.height*iface.getY(item)+4),
						(int)(dim.width/2 + 0.02 * dim.width*iface.getX(item)+3), (int)(dim.height/2 - 2 * dim.height*iface.getY(item)+4));
			}
		} 
	}
}