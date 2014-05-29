package gurwitz.physics2;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class ProjectileGUI extends JFrame {

	public ProjectileGUI() {

		setTitle("Projectile GUI");
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		add(new GraphComponent(), BorderLayout.CENTER);
		setVisible(true);
	}

	public static void main(String[] args) {
		new ProjectileGUI();
	}

}
