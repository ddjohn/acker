package se.avelon.edge.helpers;

import java.awt.Color;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.ui.RectangleAnchor;
import org.jfree.ui.TextAnchor;

public class DajoMarker extends ValueMarker {

	private static final long serialVersionUID = 1L;

	public DajoMarker(double value, Color color) {
		this(value, "" + value, color, RectangleAnchor.BOTTOM_RIGHT, TextAnchor.TOP_RIGHT);
	}

	public DajoMarker(double value, String label, Color color, RectangleAnchor recAnchor, TextAnchor textAnchor) {
		super(value);

		this.setPaint(color);
		this.setLabel(label);
		this.setLabelAnchor(recAnchor);
		this.setLabelTextAnchor(textAnchor);
	}
}
