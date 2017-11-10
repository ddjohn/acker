package info;

import java.awt.BorderLayout;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.JTable;

public class MyInformationTable extends JPanel {
	private static final long serialVersionUID = 1L;
	private final Logger log = Logger.getAnonymousLogger();
	
	{
		MyInformationTable.setDefaultFormat();
	}

	MyInformationModel model = new MyInformationModel();
	JTable table = new JTable(model);
	static MyInformationTable instance = null;

	private MyInformationTable() {
		this.setLayout(new BorderLayout());
		this.add(table.getTableHeader(), BorderLayout.NORTH);
		this.add(table, BorderLayout.CENTER);

		table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
	}

	public static MyInformationTable getInstance() {
		if(instance == null) {
			instance = new MyInformationTable();
		}
		return instance;
	}

	public void warning(String msg) {
		log.warning(msg);
		model.warning(msg);		
	}

	public void error(String msg) {
		log.severe(msg);
		model.error(msg);		
	}

	public void info(String msg) {
		log.info(msg);
		model.info(msg);		
	}

	public void debug(String msg) {
		log.fine(msg);
		model.debug(msg);				
	}
	
	public static void setDefaultFormat() {
		//final String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";
		//final Calendar cal = Calendar.getInstance();
		//final SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);

/*		FileHandler handler = null;
		try {
			handler = new FileHandler("logs/my.log");
			Logger logger = Logger.getLogger("");
			logger.addHandler(handler);
		} 
		catch(Exception e) {
			e.printStackTrace();
		} 

		Logger.getLogger("").setLevel(Level.ALL);
		for(Handler h : Logger.getLogger("").getHandlers()) {
			//if(h instanceof FileHandler) {
				//h.setLevel(Level.INFO);
			//}
			System.out.println("handler=" + h);
			h.setFormatter(new Formatter() {

				@Override
				public String format(LogRecord rec) {
					return sdf.format(cal.getTime()) + " " + rec.getMessage() + "\n";// + " (" + rec.getSourceClassName() + ":" + rec.getThreadID() + ")\n";
				}
			});
		}

		if(handler != null) {
			handler.setLevel(Level.ALL);
		}*/
	}
}
