package log;

import java.util.logging.Logger;

public class MyInformationTable {
	private final static Logger log = Logger.getAnonymousLogger();
	private static MyInformationTable instance = null;
	
	private MyInformationTable() {}
	
	public static MyInformationTable getInstance() {
		if(instance == null) {
			instance = new MyInformationTable();
		}
		return instance;
	}
	
	public void info(String msg) {
		log.info(msg);
	}

	public void debug(String msg) {
		log.fine(msg);
	}
}
