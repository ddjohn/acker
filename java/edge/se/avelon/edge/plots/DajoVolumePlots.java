package se.avelon.edge.plots;

import java.awt.Color;
import java.util.Vector;
import se.avelon.edge.datasets.volume.DajoVolumeDataset;
import se.avelon.edge.helpers.DajoNumberAxis;
import se.avelon.edge.helpers.DajoXYPlot;
import se.avelon.edge.omx.datafeed.CandleFeedData;

public class DajoVolumePlots extends DajoXYPlot {

	private static final long serialVersionUID = 1L;

	private static final DajoVolumeDataset        volume = new DajoVolumeDataset();

	@SuppressWarnings("deprecation")
	public DajoVolumePlots() {
		super();

		DajoNumberAxis nAxis = new DajoNumberAxis("Volume");
		nAxis.setAutoRange(true);
		nAxis.setAutoRangeIncludesZero(true);
		nAxis.setLowerMargin(0.0D);

		//nAxis.setUpperMargin(0.0D);		
		this.setRangeAxis(nAxis);

		this.setDataset(0, null);
		this.setRenderer(0, volume);
		volume.setPaint(Color.black);
	}

	public void set(Vector<CandleFeedData> data) {
		volume.set(data);
	}

	public void show(int ds, int serie) {
		//	this.getRenderer(renderer).setSeriesVisible(serie, false);	
		switch(ds) {
		case 0: { 
			this.setDataset(0, volume);
			break;
		}
		}
	}
}