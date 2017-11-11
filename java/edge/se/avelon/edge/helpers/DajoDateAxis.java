package se.avelon.edge.helpers;

import java.awt.Font;
import java.text.SimpleDateFormat;

import org.jfree.chart.axis.DateAxis;
import org.jfree.data.Range;

import se.avelon.edge.annotations.Requirement;
import se.avelon.edge.utilities.DajoConfiguration;
import se.avelon.edge.utilities.DajoLogger;

public class DajoDateAxis extends DateAxis {

	private static final long serialVersionUID = 1L;
	private static final DajoLogger log = DajoLogger.getLogger(DajoDateAxis.class);
	private static final DajoConfiguration conf = DajoConfiguration.getInstance();
	
	@Requirement(tag = "GUI.001", descr = "Font must be able to be configured") 
	private static String fontFamily = conf.getValue("axis.fontFamily");
	private static int fontSize   = Integer.parseInt(conf.getValue("axis.fontSize"));
	private static Font font = new Font(fontFamily, Font.PLAIN, fontSize);
	private static Font font2 = new Font(fontFamily, Font.PLAIN, fontSize+2);

	@Requirement(tag = "GUI.001", descr = "Date format must be able to be configureds") 
	private static String dateFormat = conf.getValue("axis.dateFormat");

	public DajoDateAxis(String title) {
		super(title);

		log.info("Set font on logger to " + font);
		this.setTickLabelFont(font); 
		this.setLabelFont(font2);
		
		log.info("Set date format to " + dateFormat);
		this.setDateFormatOverride(new SimpleDateFormat(dateFormat));
		//date.setTimeline(SegmentedTimeline.newMondayThroughFridayTimeline());
	}

	@Override
	public void setRange(Range sRange) {
		super.setRange(sRange.getLength() == 0 ? new Range(sRange.getLowerBound(), sRange.getLowerBound() + 1): sRange);
	}
}

