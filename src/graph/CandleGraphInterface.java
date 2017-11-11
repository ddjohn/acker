package graph;

import java.util.Date;

public interface CandleGraphInterface {
	public double getY(int item);
	public Date getX(int item);
	public int getNumberOfItems();
	public double getOpeningPrice(int item);
	public double getClosingPrice(int item);
	public double getLowestPrice(int item);
	public double getHighestPrice(int item);
	public double getVolume(int item);
}
