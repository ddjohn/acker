package se.avelon.edge.chartpanels;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Vector;
import org.jfree.chart.plot.XYPlot;
import datafeeders.CandleFeedData;
import datafeeders.CandleFeedInterface;
import factories.InterfaceFactory;
import scratch.Config;
import se.avelon.edge.datasets.scatter.DajoScatterDataset;
import se.avelon.edge.eventhandling.EventManager;
import se.avelon.edge.formulas.Formulas;
import se.avelon.edge.helpers.DajoMarker;
import se.avelon.edge.helpers.DajoNumberAxis;
import se.avelon.utilities.DajoLogger;

public class DajoScatter extends DajoChartPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	private static final DajoLogger log = DajoLogger.getLogger(DajoScatter.class);

	DajoScatterDataset ds = new DajoScatterDataset();
	XYPlot plot = null;
	DajoMarker horizontalMarker = null;
	DajoMarker verticalMarker = null;

	public DajoScatter() {
		super("etc/toolbars/scatter.xml");
		log.constructor("DajoScatter()");

		DajoNumberAxis domain = new DajoNumberAxis("Market Trend (%)");
		domain.setNumberFormatOverride(new DecimalFormat("0%"));

		plot = new XYPlot(ds, domain, new DajoNumberAxis("Fibonacci"), ds);

		this.setChart("Scatter", plot);
		this.removeLegend();
		
		EventManager.getInstance().subscribeOnAction("toolbar.scatter.refresh", this);
	}

	public void actionPerformed(ActionEvent e) {
		log.info("Refresh");

		try {
			Vector<Vector<CandleFeedData>> v = new Vector<Vector<CandleFeedData>>();

			CandleFeedInterface adapt = InterfaceFactory.getFeeder(); //new OmxCandleFeedAdapter();

			for(String stock[] : Config.stocks) {
				log.info("Stock: " + stock[1]);

				Vector<CandleFeedData> data = adapt.getData(stock[0], Calendar.YEAR, 1);
				v.add(data);

			}
			ds.set(v);

			if(verticalMarker != null) {
				plot.removeRangeMarker(verticalMarker);
			}
			if(horizontalMarker != null) {
				plot.removeDomainMarker(horizontalMarker);
			}

			double mean = Formulas.mean(v.elementAt(0), 200);
			double current = v.elementAt(0).elementAt( v.elementAt(0).size()-1).closingPrice;
			double fibonacci = Formulas.fibonacci(v.elementAt(0));

			plot.addRangeMarker(verticalMarker = new DajoMarker(fibonacci, Color.black));
			plot.addDomainMarker(horizontalMarker = new DajoMarker(1.0 * (current - mean) / mean, Color.black));

			this.fix();
		} 
		catch (Exception e1) {
			e1.printStackTrace();
		}
	}
}
