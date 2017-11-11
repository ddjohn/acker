package info;

import event.EventManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class MyInformationDialog extends JDialog implements ActionListener {
	private static final long serialVersionUID = 1L;

	public MyInformationDialog(JFrame frame) {
		super(frame, "Title", true);

		// Components

		// Properties
		this.setContentPane(new JScrollPane(MyInformationTable.getInstance()));
		this.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		this.pack();
		this.setVisible(false);

		// Registrations

		// Subscriptions
		EventManager.getInstance().subscribeOnAction("toolbar.button.info", this);

		// Actions
	}

	public void actionPerformed(ActionEvent e) {
		this.showInfoDialog();
	}

	private void showInfoDialog() {
		this.pack();
		this.setVisible(true);
	}
}
