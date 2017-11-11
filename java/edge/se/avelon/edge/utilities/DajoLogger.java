package se.avelon.edge.utilities;

import java.util.logging.Logger;

public class DajoLogger {
	private Logger log = null;

	private DajoLogger(Logger log) {
		this.log = log;
	}

	public static DajoLogger getLogger(Class<?> name) {
		return new DajoLogger(Logger.getLogger(name.getName()));
	}

	public void fine(String s) {
		log.fine(s);
	}

	public void info(String s) {
		log.info("INFO   " + s);
	}

	public void severe(String s) {
		log.severe("ERROR  " + s);
	}

	public void warning(String s) {
		log.warning("WARN   " + s);
	}

	public void constructor(String s) {
		log.fine("CREATE " + s);
	}
}
