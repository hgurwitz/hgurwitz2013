package gurwitz.physics;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class ProjectileGUI extends JFrame {

	private Projectile p;

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
