package gurwitz.fire;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class FireGUI extends JFrame {

	public FireGUI() {
		setTitle("Fire GUI");
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		FireView f = new FireView();
		add(f, BorderLayout.CENTER);
		setVisible(true);
		add(f.counter, BorderLayout.SOUTH);
		add(f.timeLabel, BorderLayout.NORTH);

	}

	public static void main(String[] args) {
		new FireGUI();
	}

}
