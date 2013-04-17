package gurwitz.fire;

import java.util.LinkedList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import gurwitz.physics.Projectile;

public class ProjectileCounter extends JPanel {

	public ProjectileCounter(LinkedList<Projectile> list) {
		add(new JLabel(String.valueOf(list.size())));
	}

}
