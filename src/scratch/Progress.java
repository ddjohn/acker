package scratch;

import java.awt.BorderLayout;
import java.awt.Dialog;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

public class Progress  implements Runnable {

	class ProgressDialog extends JDialog{
		JLabel label = new JLabel();
		JProgressBar bar = new JProgressBar();

		public ProgressDialog(String text) {
			super((JDialog)null, "Progress", true); 
			this.setLayout(new BorderLayout());
			label.setText(text);
			this.add(label, BorderLayout.NORTH);
			this.add(bar, BorderLayout.CENTER);
			this.pack();

			this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE); 
			this.setEnabled(true);
			this.setVisible(true);
		}
	}

	public Progress(String text) {

		new Thread(this).run();
	}

	public void run() {
		ProgressDialog dlg = new ProgressDialog("Testing");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dlg.setEnabled(false);
		dlg.setVisible(false);
	}

	public static void main(String args[]) {
		Progress progress = new Progress("Starting");

		for(int i = 0; i < 1000; i++) {
			System.out.println(".");
		}
	}
}
