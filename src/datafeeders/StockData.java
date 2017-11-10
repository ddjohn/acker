package datafeeders;

public class StockData {
	public String stock;
	public String market;
	public String id;
	
	public StockData(String stock, String market, String id) {
		this.stock = stock;
		this.market = market;
		this.id = id;
	}
	
	public String toString() {
		return stock + " (" + market + ")";
	}
}
