package datafeeders;

import java.util.Date;
import java.util.Vector;

public interface CandleFeedInterface {
	
	public String LARGE_CAP = "L:10208";
	public String MID_CAP   = "L:10210";
	public String SMALL_CAP = "L:10212";
	//,"27","30"
	
	Vector<CandleFeedData> getData(String instruments, int calendarType, int calendarValue);
	Vector<StockData> getStocks(String[] markets);
	Vector<CandleFeedData> getData(String instrument, Date date);
}
