package main;

import javax.swing.JFrame;


public class Main extends JFreeScatterChart {

	private static final long serialVersionUID = 1L;
	
	private static double values[][] = {
			{2.0, 1.0},
			{1.0, 1.5},
			{3.0, 2.0}
	};
	
	public Main() {
		super("Test", values);
	}

	public static void main(String args[]) {
		JFrame f = new JFrame();
		f.getContentPane().add(new Main());
		f.pack();
		f.setVisible(true);
	}
}
