package graph;

import java.awt.Color;

public interface ScatterGraphInterface {
	public static final int NONE     = 0;
	public static final int HIGH     = 1;
	public static final int LOW      = 2;
	public static final int BIG      = 4;
	public static final int BIGGER   = 8;
	
	public double getY(int item);
	public double getX(int item);
	public String getName(int item);
	public Color getTextColor(int item);
	public int getNumberOfItems();
	public int getStyle(int item);
}
