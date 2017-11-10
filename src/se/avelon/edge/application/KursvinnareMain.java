package se.avelon.edge.application;

import java.awt.EventQueue;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import se.avelon.edge.utilities.DajoConfiguration;
import se.avelon.utilities.DajoLogger;

public class KursvinnareMain {

	private static final DajoConfiguration conf = DajoConfiguration.getInstance();
	private static final DajoLogger log = DajoLogger.getLogger(KursvinnareMain.class);

	public KursvinnareMain() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {		
		this.setLoggerFormat();
		this.setLocale();
		this.setLookAndFeel();
		this.setProxyInformation();
				
		this.run();
	}

	private void setLocale() {
		log.fine("Setting locale");
		Locale.setDefault(Locale.ENGLISH);		
	}

	private void setLoggerFormat() {		
		final String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";
		final Calendar cal = Calendar.getInstance();
		final SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);

		Logger.getLogger("").setLevel(Level.ALL);
		for(Handler h : Logger.getLogger("").getHandlers()) {
			//if(h instanceof FileHandler) {
			h.setLevel(Level.INFO);
			//}

			h.setFormatter(new Formatter() {

				@Override
				public String format(LogRecord rec) {
					return sdf.format(cal.getTime()) + " " + rec.getMessage() + " (" + rec.getLoggerName() + ":" + rec.getThreadID() + ")\n";
				}
			});
		}
	}

	private void setLookAndFeel() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		log.fine("Set look and feel");
		for(LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
			if ("Nimbus".equals(info.getName())) {
				UIManager.setLookAndFeel(info.getClassName());
				break;
			}
		}
	}

	private void setProxyInformation() {
		log.fine("Set Proxy Information");
		System.setProperty("http.proxyHost", conf.getValue("http.proxyHost"));
		System.setProperty("http.proxyPort", conf.getValue("http.proxyHost"));
	}

	private void run() {
		log.info("Starting application");
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new KursvinnareFrame();
				}
				catch(Throwable t) {
					System.out.println("Pain in the as exception: " + t.toString());
					t.printStackTrace();
				}
			}
		});
	}

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException  {
		new KursvinnareMain();
	}
}
