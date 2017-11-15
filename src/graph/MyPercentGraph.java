package graph;

import event.EventManager;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.ParseException;
import javax.swing.JCheckBox;
import org.w3c.dom.DOMException;
import org.w3c.dom.NodeList;
import util.Formulas;

public class MyPercentGraph extends MyAbstractGraph implements ItemListener {
	private static final long serialVersionUID = 1L;

	boolean stochastic1 = false;
	boolean stochastic3 = false;

	public MyPercentGraph() {
		EventManager.getInstance().subscribeOnItem("toolbar.percent.stochastic1", this);
		EventManager.getInstance().subscribeOnItem("toolbar.percent.stochastic3", this);
	}

	@Override 
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		if(draw) {
			Graphics2D g2D = (Graphics2D)g; 
			g2D.setStroke(new BasicStroke(2F));

			if(stochastic1) {
				g.setColor(Color.lightGray);
				g2D.setStroke(new BasicStroke(1F));
				this.stochasticOscilator1(g, 5, 3);
			}
			if(stochastic3) {
				g.setColor(Color.lightGray);
				g2D.setStroke(new BasicStroke(1F));
				this.stochasticOscilator3(g, 5, 3);
			}
		}
	}

	private void stochasticOscilator1(Graphics g, int days, int utj) {
		double val = 0;

		for(int i = days; i < nodes.getLength(); i++) {

			double oldval = val;
			
			try {
				val = Formulas.stochasticTmp(nodes, i);
			} catch (ParseException e1) {
				val = 50;
			}

			if(val >= 70) {
				g.setColor(Color.red);
			}
			else if (val <= 30){
				g.setColor(Color.green);
			}
			else  {
				g.setColor(Color.gray);
			}

			if(i != days) {
				try {
					drawLine(g, (int)((i-1) * dim.getWidth() / nodes.getLength()), 
							(dim.getHeight()-oldval*dim.getHeight()/100), 
							(int)((i) * dim.getWidth() / nodes.getLength()), 
							(dim.getHeight()-val*dim.getHeight()/100)
							);
				}
				catch (DOMException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private void stochasticOscilator3(Graphics g, int days, int utj) {
		double val = 0;

		for(int i = days; i < nodes.getLength(); i++) {

			double oldval = val;
			
			try {
				val = Formulas.stochastic(nodes, i);
			} catch (ParseException e1) {
				val = 50;
			}

			if(val >= 70) {
				g.setColor(Color.red);
			}
			else if (val <= 30){
				g.setColor(Color.green);
			}
			else  {
				g.setColor(Color.gray);
			}

			if(i != days) {
				try {
					drawLine(g, (int)((i-1) * dim.getWidth() / nodes.getLength()), 
							(dim.getHeight()-oldval*dim.getHeight()/100), 
							(int)((i) * dim.getWidth() / nodes.getLength()), 
							(dim.getHeight()-val*dim.getHeight()/100)
							);
				}
				catch (DOMException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public MyScale setNodes(NodeList nodes) {
		super.setNodes(nodes);

		return new MyScale("0%", "100%", 
				getValue(nodes.item(0),"dt"), getValue(nodes.item(nodes.getLength()-1),"dt"));
	}

	public void itemStateChanged(ItemEvent e) {
		JCheckBox source = (JCheckBox)e.getSource();
		if(source.getName().compareTo("toolbar.percent.stochastic1") == 0) {
			stochastic1 = e.getStateChange() == ItemEvent.SELECTED;
		}
		if(source.getName().compareTo("toolbar.percent.stochastic3") == 0) {
			stochastic3 = e.getStateChange() == ItemEvent.SELECTED;
		}
		this.repaint();				
	} 
}