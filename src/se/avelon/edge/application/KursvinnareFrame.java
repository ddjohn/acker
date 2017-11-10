package se.avelon.edge.application;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import scratch.DajoStatusBar;
import se.avelon.edge.eventhandling.EventManager;
import se.avelon.edge.guifactories.DajoMenuFactory;
import se.avelon.edge.utilities.DajoConfiguration;
import se.avelon.utilities.DajoLogger;

public class KursvinnareFrame extends DajoFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private static final DajoConfiguration conf = DajoConfiguration.getInstance();
	private static final DajoLogger log = DajoLogger.getLogger(KursvinnareFrame.class);

	private final JMenuBar     menuBar = DajoMenuFactory.getJMenuBar();
	private final JToolBar      topBar = new JToolBar();
	private final JSplitPane splitPane = new DajoSplitPane();
	private final JPanel     statusBar = new DajoStatusBar();

	public KursvinnareFrame() {
		log.constructor("KursvinnareFrame()");

		this.setJMenuBar(menuBar);
		this.getContentPane().add(topBar, BorderLayout.NORTH);
		this.getContentPane().add(splitPane, BorderLayout.CENTER);	
		this.getContentPane().add(statusBar, BorderLayout.SOUTH);

		topBar.add(new JButton("Info"));
		topBar.setFloatable(false);

		// Register components

		// Subscribe on events
		EventManager.getInstance().subscribeOnAction("menu.file.quit", this);
		EventManager.getInstance().subscribeOnAction("menu.help.about", this);
		EventManager.getInstance().subscribeOnAction("menu.help.help", this);

		// Properties
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("icon/stock.png"));
		this.setTitle("Hitta Kursvinnare");
		//this.pack();
		//this.setSize(800, 600);
		this.setMaxSize();
		this.setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		this.setVisible(true);
		
	    Toolkit.getDefaultToolkit().beep();
	}

	private void setMaxSize() {
		Toolkit tk = Toolkit.getDefaultToolkit();  
		int x = (int)tk.getScreenSize().getWidth();  
		int y = (int)tk.getScreenSize().getHeight();  
		log.fine("Set frame size to " + x + "x" + y);
		this.setSize(x, y);  		
	}

	public void actionPerformed(ActionEvent e) {
		JComponent c = (JComponent)e.getSource();
		if(c.getName().compareTo("menu.file.quit") == 0) {
			this.quit();
		}
		else if(c.getName().compareTo("menu.help.about") == 0) {
			JOptionPane.showMessageDialog(this, "Hitta Kursvinnare 2012");
		}
		else if(c.getName().compareTo("menu.help.help") == 0) {
			JOptionPane.showMessageDialog(this, "Help");
		}
	}
}
