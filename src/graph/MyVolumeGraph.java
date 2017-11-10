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
import util.MyUtil;

public class MyVolumeGraph extends MyAbstractGraph implements ItemListener {
	private static final long serialVersionUID = 1L;

	long vol;
	boolean volume = false;

	public MyVolumeGraph() {

		EventManager.getInstance().subscribeOnItem("toolbar.volume.volume", this);
	}

	@Override 
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		if(draw) {
			Graphics2D g2D = (Graphics2D)g; 

			if(volume) {
				g.setColor(Color.lightGray);
				g2D.setStroke(new BasicStroke(1F));
				this.drawVolumes(g);
			}
		}
	}

	private void drawVolumes(Graphics g) {
		for(int i = 1; i < nodes.getLength(); i++) {
			try {
				drawLine(g, ((i) * dim.getWidth() / nodes.getLength()), 
						(dim.getHeight()), 
						((i) * dim.getWidth() / nodes.getLength()), 
						(dim.getHeight()-(MyUtil.parseN(getValue(nodes.item(i),"tv")).longValue())*dim.getHeight()/(vol))
						);			
			}
			catch (ParseException pe) {
				MyInformationTable.getInstance().warning("volume: Data Missing for " + getValue(nodes.item(i), "dt"));
			}
		}
	}   

	public MyScale setNodes(NodeList nodes) {
		super.setNodes(nodes);

		vol = 0;

		for (int i = 0; i < nodes.getLength(); i++) {
			long curvol = MyUtil.parseN(this.getValue(nodes.item(i), "tv"), 0).longValue();

			if (curvol > vol) vol = curvol;
		}

		return new MyScale("0", "" + vol, 
				getValue(nodes.item(0),"dt"), getValue(nodes.item(nodes.getLength()-1),"dt"));
	}

	public void itemStateChanged(ItemEvent e) {
		JCheckBox source = (JCheckBox)e.getSource();
		if(source.getName().compareTo("toolbar.volume.volume") == 0) {
			volume = e.getStateChange() == ItemEvent.SELECTED;
		}
		this.repaint();						
	} 
}