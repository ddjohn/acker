package se.avelon.edge.utilities;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DajoConfiguration extends JPanel implements ActionListener {
	
	final private static String PROPFILE= "etc/Application.properties";
	final private static String[] attributes = new String[] {"user.name", "user.password", "log_level", "format.date", "format.dateAndTime"};
	private static final long serialVersionUID = 1L;
	private static final Properties properties = new Properties();
	private JPanel panel = new JPanel(new GridLayout(0,2));
	private static DajoConfiguration instance = null;

	private DajoConfiguration() {

		this.add(panel, BorderLayout.NORTH);

		try {
			this.loadProperties(PROPFILE);
		} 
		catch (IOException e) {
			e.printStackTrace();
		}

		for(String attribute : attributes) {
			panel.add(new JLabel(attribute));
			JTextField field;
			panel.add(field = new JTextField(this.getLoadedValues(attribute)));
			field.setName(attribute);
		}

		JButton save, undo;
		panel.add(save = new JButton("Save"));
		panel.add(undo = new JButton("Cancel"));
		save.addActionListener(this);
		undo.addActionListener(this);
	}

	private void loadProperties(String fileName) throws IOException {
		InputStream file = new FileInputStream(fileName);
		properties.load(file);
		file.close();
	}

	private String getLoadedValues(String name) {
		return properties.getProperty(name, "");
	}

	public void actionPerformed(ActionEvent e) {
		System.out.println("e=" + e);

		if(e.getActionCommand().compareTo("Save") == 0) {
			for(Component comp : panel.getComponents()) {
				System.out.println("Comp " + comp);

				if(comp.getName() != null) {
					JTextField field = (JTextField)comp;
					System.out.println("Saving " + field.getName() + "=" + field.getText());
					properties.setProperty(field.getName(), field.getText());
				}
			}
			try {
				this.save(properties, PROPFILE);
			} 
			catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		else {
			for(Component comp : this.getComponents()) {
				if(comp.getName() != null) {
					JTextField field = (JTextField) comp;
					field.setText(this.getLoadedValues(field.getName()));
				}
			}
		}
	}

	private void save(Properties p, String name) throws IOException {
		OutputStream file = new FileOutputStream(name);
		p.store(file, "Properties File to the Test Application");
		file.close();

	}

	public static DajoConfiguration getInstance() {
		if(instance == null)
			instance = new DajoConfiguration();

		return instance;
	}

	public String getValue(String name) {
		// TODO Auto-generated method stub
		return properties.getProperty(name, "");
	}
}
