package se.avelon.edge.application;

import java.io.IOException;
import javax.swing.JEditorPane;

public class DajoWelcome extends JEditorPane {

	private static final long serialVersionUID = 1L;

	public DajoWelcome() {
		super();
		
		this.setContentType("text/html");
		this.setEditable(false);
		try {
			this.setPage("file:doc/welcome.html");
		} 
		catch (IOException e) {
			this.setText("<H1>Welcome</H1>" + e);
			e.printStackTrace();
		}
	}
}
