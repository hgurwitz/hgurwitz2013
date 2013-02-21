package fire;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class FireGUI extends JFrame {

	public FireGUI() {
		setTitle("Fire GUI");
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		add(new FireComponent(), BorderLayout.CENTER);
		setVisible(true);
	}

	public static void main(String[] args) {
		new FireGUI();
	}

}
