package gurwitz.physics;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;

public class GraphComponent extends JComponent {

	private Projectile[] projectiles;
	private Color[] colors;

	public GraphComponent() {
		super();
		projectiles = new Projectile[5];
		projectiles[0] = new Projectile(50, 130);
		projectiles[1] = new Projectile(70, 110);
		projectiles[2] = new Projectile(40, 60);
		projectiles[3] = new Projectile(30, 100);
		projectiles[4] = new Projectile(85, 120);
		colors = new Color[5];
		colors[0] = Color.MAGENTA;
		colors[1] = Color.BLUE;
		colors[2] = Color.GRAY;
		colors[3] = Color.RED;
		colors[4] = Color.GREEN;

	}

	protected void paintComponent(Graphics g) {
		// do all of draw calls in here
		// or in methods called here
		// overrides super method
		super.paintComponent(g);
		g.translate(0, getHeight());

		for (int j = 0; j < 5; j++) {
			Projectile p = projectiles[j];
			for (double i = 0; i < 30; i++) { //
				g.setColor(colors[j]);
				int xValue=(int) p.getX(i);
				int yValue=(int) p.getY(i);
				g.drawOval(xValue-5, -yValue-5, 10, 10);
				int nextX = (int) p.getX(i + 1);
				int nextY = (int) p.getY(i + 1);
				g.drawLine(xValue, -yValue, nextX, -nextY);

			}
		}

	}

}
