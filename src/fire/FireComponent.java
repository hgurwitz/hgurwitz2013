package fire;

import gurwitz.physics.Projectile;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.swing.JComponent;

public class FireComponent extends JComponent {

	private double time;
	private double incrementTimeBy;
	private ArrayList<Projectile> projectiles;
	private Random random;
	private Color color;
	protected boolean freeze;
	private double relativeTime;

	public FireComponent() {
		super();
		time = 0.0;
		incrementTimeBy = .05;
		random = new Random();
		projectiles = new ArrayList<Projectile>();
		addNewProjectiles();
		freeze = false;
	}

	private void addNewProjectiles() {
		for (int i = 0; i < 50; i++) {
			color = Color.WHITE;
			Projectile p = new Projectile(random.nextInt(70) + 55,
					random.nextInt(40) + 40, color, random.nextInt(15) + 4,
					random.nextInt(2) + 7, time);

			projectiles.add(p);
		}
	}

	protected void paintComponent(Graphics g) {

		super.paintComponent(g);

		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.translate(getWidth() / 2, (getHeight() / 2) + 100);

		Graphics2D g2 = (Graphics2D) g;
		g2.setComposite(AlphaComposite
				.getInstance(AlphaComposite.SRC_OVER, .3f));

		time += incrementTimeBy;

		addNewProjectiles();

		Iterator<Projectile> iter = projectiles.iterator();
		while (iter.hasNext()) {
			Projectile p = iter.next();
			double relativeTime = time - p.getStartTime();
			if (p.getLifespan() > relativeTime) {
				g.setColor(getColor(p));
				int xValue = (int) p.getX(relativeTime);
				int yValue = (int) p.getY(relativeTime);
				g.fillOval(xValue - 5, -yValue - 5, p.getSize(), p.getSize());
			} else {
				iter.remove();
			}

			repaint();
		}

	}

	private Color getColor(Projectile p) {
		relativeTime = time - p.getStartTime();
		// white=255, 255, 255
		// gray=128, 128, 128
		// red=255, 0, 0
		// orange=255, 200, 0
		// yellow=255, 255 0

		if (relativeTime < 1.0) {
			return new Color(255, 255, random.nextInt(40) + 215);
		} else if (relativeTime < 3) {
			return new Color(255, random.nextInt(40) + 215, 0);
		} else if (relativeTime < 3.9) {
			return new Color(255, random.nextInt(5) + 200, 0);
		} else if (relativeTime < 5.5) {
			return new Color(255, random.nextInt(100), 0);
		} else if (relativeTime < 8) {
			return Color.GRAY;
		} else {
			return Color.BLACK;
		}
	}
}
