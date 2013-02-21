package gurwitz.physics;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JComponent;

public class GraphComponent extends JComponent {

	private double time;
	private double incrementTimeBy;
	private double timeForMore;
	private ArrayList<Projectile> projectiles;
	private Random random;
	private Color color;
	private ArrayList<Trail> trails;
	protected boolean freeze;

	public GraphComponent() {

		super();
		time = 0.0;
		incrementTimeBy = .002;
		timeForMore = 0.0;
		trails = new ArrayList<Trail>();
		random = new Random();
		color = new Color(10, 10, 10);
		projectiles = new ArrayList<Projectile>();
		addNewProjectiles();
		freeze = false;
		addMouseListener(new MouseClickListener(this));
		setFocusable(true);

	}

	private void addNewProjectiles() {
		for (int i = 0; i < 10; i++) {
			color = new Color(random.nextInt(255), random.nextInt(255),
					random.nextInt(255));

			double lifespan = incrementTimeBy * 1000;

			lifespan = random.nextInt((int) lifespan);
			lifespan += (3);
			projectiles.add(new Projectile(random.nextInt(360) + 40, random
					.nextInt(360) + 50, color, random.nextInt(15) + 7,
					lifespan, time));
		}
	}

	protected void paintComponent(Graphics g) {

		// do all of draw calls in here
		// or in methods called here
		// overrides super method

		super.paintComponent(g);

		g.translate(getWidth() / 2, getHeight() / 2);

		if (!freeze) {
			time += incrementTimeBy;
			timeForMore += .05;
		}

		drawGrid(g);
		// trails are slowing the whole thing down
		// drawTrails(g);

		if (timeForMore > 1.5) {
			addNewProjectiles();
			timeForMore = 0;
		}

		for (Projectile p : projectiles) {
			double relativeTime = time - p.getStartTime();
			if (p.getLifespan() > relativeTime) {
				g.setColor(p.getColor());
				int xValue = (int) p.getX(relativeTime);
				int yValue = (int) p.getY(relativeTime);
				g.fillOval(xValue - 5, -yValue - 5, p.getSize(), p.getSize());
				// trails.add(new Trail(xValue, yValue, p.getSize()));
			}
		}

		repaint();

	}

	private void drawGrid(Graphics g) {
		g.setColor(Color.LIGHT_GRAY);

		for (int i = -(getWidth() / 2); i < getWidth() / 2; i += 20) {
			// drawing vertical lines
			g.drawLine(i, -getHeight() / 2, i, 0);
			g.drawLine(i, +getHeight() / 2, i, 0);
		}
		for (int i = -(getHeight() / 2); i < getHeight() / 2; i += 20) {
			// drawing horizontal lines
			g.drawLine(0, -i, getWidth() / 2, -i);
			g.drawLine(0, -i, -getWidth() / 2, -i);
		}
	}

	private void drawTrails(Graphics g) {
		for (Trail t : trails) {
			g.fillOval(t.getX() - 5, -t.getY() - 5, t.getSize(), t.getSize());
		}
	}

}
