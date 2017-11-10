package datafeeders;

import java.util.Date;

public class CandleFeedData {
	public Date date;
	public double openingPrice;
	public double closingPrice;
	public double lowestPrice;
	public double highestPrice;
	public double volume;
	public String instrument;
	public String name;

	public CandleFeedData(Date dt, double op, double cp, double hp, double lp, double tv, String ins, String insnm) {
		this.date = dt;
		this.openingPrice = op;
		this.closingPrice = cp;
		this.highestPrice = hp;
		this.lowestPrice = lp;
		this.volume = tv;
		this.instrument = ins;
		this.name = insnm;
	}	
}
