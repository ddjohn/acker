import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JProgressBar;

public class Modal extends JDialog {
	JProgressBar bar = new JProgressBar();

	private static final long serialVersionUID = 1L;
	int total = 0;
	int current = 0;

	Modal(JFrame f) {
		super(f);
		this.setTitle("Progress");
		this.setModal(true);
		this.add(bar);
		this.pack();

	
		//	Thread.sleep(500);
		this.register(5);
		//Thread.sleep(500);
		this.init();
		//Thread.sleep(500);
		this.ping("Test a");
		//Thread.sleep(500);
		this.ping("Test b");
		//Thread.sleep(500);
		this.ping("Test c");
		//Thread.sleep(500);
		this.ping("Test d");
		//Thread.sleep(500);
		this.ping("Test e");
		//Thread.sleep(500);
		this.fini();
}

	public static void main(String args[]) throws Throwable {
		JFrame f = new JFrame("Hello World");
		f.setSize(300,200);
		f.setVisible(true);

		Modal m = new Modal(f);
		
		
		System.exit(9);
	}

	private void register(int i) {
		total += i;
		bar.setMaximum(total);
	}

	private void init() {
		total = 0;
		bar.setMinimum(0);
		bar.setMaximum(0);
		bar.setValue(0);
		this.setVisible(true);
	}

	private void fini() {
		// TODO Auto-generated method stub
		this.setVisible(false);
	}

	private void ping(String msg) {
		bar.setValue(++current);
		
		System.out.println(msg);
		this.repaint();
		bar.repaint();
	}
}
