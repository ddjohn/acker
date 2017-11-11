package graph;

import event.EventManager;
import info.MyInformationTable;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.ParseException;

import javax.swing.JCheckBox;

import org.w3c.dom.NodeList;

import util.Formulas;
import util.MyUtil;

public class MyIndexGraph extends MyAbstractGraph implements ItemListener {
	private static final long serialVersionUID = 1L;

	double min, max;
	boolean mean200, mean50, mean20, donch20, index, candle;
	public MyIndexGraph() {

		// Subscribe
		EventManager.getInstance().subscribeOnItem("toolbar.index.mean200", this);
		EventManager.getInstance().subscribeOnItem("toolbar.index.mean50", this);
		EventManager.getInstance().subscribeOnItem("toolbar.index.mean20", this);
		EventManager.getInstance().subscribeOnItem("toolbar.index.donchian20", this);
		EventManager.getInstance().subscribeOnItem("toolbar.index.index", this);
		EventManager.getInstance().subscribeOnItem("toolbar.index.candle", this);
	}

	@Override 
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		if(draw) {			
			Graphics2D g2D = (Graphics2D)g; 

			if(index) {
				g2D.setColor(Color.black);
				g2D.setStroke(new BasicStroke(1F));
				this.drawIndex(g);
			}
			if(candle) {
				g2D.setColor(Color.black);
				g2D.setStroke(new BasicStroke(2F));
				this.drawCandle(g);
			}

			if(mean200) {
				g2D.setColor(Color.green);
				g2D.setStroke(new BasicStroke(1F));
				this.marketTrend(g, 200);
			}
			if(mean50) {
				g2D.setColor(Color.green);
				g2D.setStroke(new BasicStroke(1F));
				this.marketTrend(g, 50);
			}
			if(mean20) {
				g2D.setColor(Color.green);
				g2D.setStroke(new BasicStroke(1F));
				this.marketTrend(g, 20);
			}

			if(donch20) {
				g2D.setStroke(new BasicStroke(1F));
				this.donchian(g2D, 20);
			}
		}
	}

	private void donchian(Graphics2D g, int days) {
		double hi = 0;
		double low = 0;
		double mid = 0;
		
		g.setColor(Color.blue);
		
		for(int i = days; i < nodes.getLength(); i++) {
			double tmpLow = Formulas.donchianLow(nodes, days, i) ;
			double tmpHi = Formulas.donchianHigh(nodes, days, i) ;
			double tmpMid = 0.5 * (tmpLow + tmpHi);	
			
			double oldHi = hi;
			double oldLow = low;
			double oldMid = mid;
			hi = tmpHi;
			low = tmpLow;
			mid = tmpMid;

			if(i != days) {
				g.setStroke(new BasicStroke(2F));
				drawLine(g, ((i-1) * dim.getWidth() / nodes.getLength()), 
						(dim.getHeight()-(oldHi-min)*dim.getHeight()/(max-min)), 
						((i) * dim.getWidth() / nodes.getLength()), 
						(dim.getHeight()-(hi-min)*dim.getHeight()/(max-min))
						);
				drawLine(g, ((i-1) * dim.getWidth() / nodes.getLength()), 
						(dim.getHeight() - (oldLow-min)*dim.getHeight()/(max-min)), 
						((i) * dim.getWidth() / nodes.getLength()), 
						(dim.getHeight() - (low-min)*dim.getHeight()/(max-min))
						);
				g.setStroke(new BasicStroke(1F));
				drawLine(g, ((i-1) * dim.getWidth() / nodes.getLength()), 
						(dim.getHeight() - (oldMid-min)*dim.getHeight()/(max-min)), 
						((i) * dim.getWidth() / nodes.getLength()), 
						(dim.getHeight() - (mid-min)*dim.getHeight()/(max-min))
						);
			}
		}   
	}
	private double getMean(int pos, int days) {
		return Formulas.mean(nodes, days, pos);
	}


	private void marketTrend(Graphics g, int days) {
		double val = 0;

		for(int i = days; i < nodes.getLength(); i++) {
			double oldval = val;
			val = getMean(i, days);

			if(i != days) {
				drawLine(g, ((i-1) * dim.getWidth() / nodes.getLength()), 
						(dim.getHeight()-(oldval-min)*dim.getHeight()/(max-min)), 
						((i) * dim.getWidth() / nodes.getLength()), 
						(dim.getHeight()-(val-min)*dim.getHeight()/(max-min))
						);
			}
		}
	}

	private void drawCandle(Graphics g) {
		for(int i = 1; i < nodes.getLength(); i++) {
			try {
				drawLine(g, ((i) * dim.getWidth() / nodes.getLength()), 
						(dim.getHeight() -(MyUtil.parseN(getValue(nodes.item(i),"lp")).doubleValue()-min)*dim.getHeight()/(max-min)), 
						((i) * dim.getWidth() / nodes.getLength()), 
						(dim.getHeight()-(MyUtil.parseN(getValue(nodes.item(i),"hp")).doubleValue()-min)*dim.getHeight()/(max-min))
						);
				drawLine(g, ((i) * dim.getWidth() / nodes.getLength()), 
						(dim.getHeight() -(MyUtil.parseN(getValue(nodes.item(i),"cp")).doubleValue()-min)*dim.getHeight()/(max-min)), 
						((i+0.5) * dim.getWidth() / nodes.getLength()), 
						(dim.getHeight()-(MyUtil.parseN(getValue(nodes.item(i),"cp")).doubleValue()-min)*dim.getHeight()/(max-min))
						);
			}
			catch (ParseException pe) {
				MyInformationTable.getInstance().warning("candle: Data Missing for " + getValue(nodes.item(i), "dt"));
			}
		}   		
	}


	private void drawIndex(Graphics g) {
		for(int i = 1; i < nodes.getLength(); i++) {
			try {
				drawLine(g, ((i-1) * dim.getWidth() / nodes.getLength()), 
						(dim.getHeight() -(MyUtil.parseN(getValue(nodes.item(i-1),"cp")).doubleValue()-min)*dim.getHeight()/(max-min)), 
						((i) * dim.getWidth() / nodes.getLength()), 
						(dim.getHeight()-(MyUtil.parseN(getValue(nodes.item(i),"cp")).doubleValue()-min)*dim.getHeight()/(max-min))
						);
			}
			catch (ParseException pe) {
				MyInformationTable.getInstance().warning("index: Data Missing for " + getValue(nodes.item(i), "dt"));
			}
		}
	}   

	public MyScale setNodes(NodeList nodes) {
		super.setNodes(nodes);

		max = 0;
		min = 999999999;	

		for (int i = 0; i < nodes.getLength(); i++) {
			try {
				double curmin = MyUtil.parseN(this.getValue(nodes.item(i), "lp")).doubleValue();
				double curmax = MyUtil.parseN(this.getValue(nodes.item(i), "hp")).doubleValue();

				if (curmax > max) max = curmax;
				if (curmin < min) min = curmin;
			} 
			catch (ParseException pe) {
				MyInformationTable.getInstance().warning("max/min: Data Missing for " + getValue(nodes.item(i), "dt"));
			}
		}
		return new MyScale("" + min, "" + max,
				getValue(nodes.item(0),"dt"), getValue(nodes.item(nodes.getLength()-1),"dt"));
	}

	public void itemStateChanged(ItemEvent e) {
		JCheckBox source = (JCheckBox)e.getSource();

		if(source.getName().compareTo("toolbar.index.mean200") == 0) {
			mean200 = e.getStateChange() == ItemEvent.SELECTED;
		}
		if(source.getName().compareTo("toolbar.index.mean50") == 0) {
			mean50 = e.getStateChange() == ItemEvent.SELECTED;
		}
		if(source.getName().compareTo("toolbar.index.mean20") == 0) {
			mean20 = e.getStateChange() == ItemEvent.SELECTED;
		}
		if(source.getName().compareTo("toolbar.index.donchian20") == 0) {
			donch20 = e.getStateChange() == ItemEvent.SELECTED;
		}
		if(source.getName().compareTo("toolbar.index.index") == 0) {
			index = e.getStateChange() == ItemEvent.SELECTED;
		}
		if(source.getName().compareTo("toolbar.index.candle") == 0) {
			candle = e.getStateChange() == ItemEvent.SELECTED;
		}
		this.repaint();		
	} 
}