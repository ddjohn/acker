package se.avelon.edge.helpers;

import java.awt.Color;
import org.jfree.chart.annotations.XYPointerAnnotation;

public class DajoAnnotation extends XYPointerAnnotation {

	private static final long serialVersionUID = 1L;
	
	public static double pos1 = 1.0 * Math.PI / 4.0;
	public static double pos2 = 3.0 * Math.PI / 4.0;
	public static double pos3 = 5.0 * Math.PI / 4.0;
	public static double pos4 = 7.0 * Math.PI / 4.0;

	public DajoAnnotation(String str, double x, double y, double pos) {
		super(str, x, y, pos);
		this.setPaint(Color.gray);
		this.setArrowPaint(Color.gray);
		//this.setArrowLength(50);
		//this.setTipRadius(10.0);
		//this.setBaseRadius(35.0);
		//this.setFont(new Font("SansSerif", Font.PLAIN, 9));
		//this.setPaint(Color.blue);
		//this.setTextAnchor(TextAnchor.HALF_ASCENT_RIGHT);
	}
}
