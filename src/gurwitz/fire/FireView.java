package gurwitz.fire;

import gurwitz.physics.Projectile;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.JComponent;
import javax.swing.JLabel;

public class FireView extends JComponent {

	private static final AlphaComposite ALPHA = AlphaComposite.getInstance(
			AlphaComposite.SRC_OVER, .3f);
	protected double time;
	private double incrementTimeBy;
	private Random random;
	private double relativeTime;
	protected JLabel counter;
	protected ArrayList<Projectile> pool;
	protected JLabel timeLabel;
	protected LinkedList<Projectile> projectiles;
	private ArrayList<Fountain> fountains;
	private ArrayList<Color> reds;
	private ArrayList<Color> oranges;
	private ArrayList<Color> yellows;
	private ArrayList<Color> whites;
	private int currColor;
	private Graphics2D g2;
	private Iterator<Projectile> projectileIter;
	private Projectile projectile;

	public FireView() {
		super();
		time = 0.0;
		incrementTimeBy = .05;
		random = new Random();
		counter = new JLabel();
		timeLabel = new JLabel();
		fountains = new ArrayList<Fountain>();
		projectiles = new LinkedList<Projectile>();
		pool = new ArrayList<Projectile>();
		addToPool();
		addMouseListener(new ClickListener());
		setFocusable(true);
		fillColors();
		currColor = 0;

	}

	public void addNewProjectiles(double time) {
		Iterator<Fountain> fountainIter = fountains.iterator();
		while (fountainIter.hasNext()) {
			Fountain f = fountainIter.next();
			relativeTime = time - f.getStartTime();
			if (relativeTime > 5) {
				fountainIter.remove();
			} else {
				for (int i = 0; i < 20; i++) {
					Projectile p = pool.remove(pool.size() - 1);
					p.setStartTime(time);
					p.setStartX(f.getX());
					p.setStartY(getHeight() - f.getY());
					projectiles.add(p);

				}
			}
		}

	}

	private void addToPool() {
		for (int i = 0; i < 100000; i++) {
			pool.add(new Projectile((random.nextInt(70) + 55), random
					.nextInt(40) + 40, Color.WHITE, random.nextInt(15) + 4,
					random.nextInt(2) + 7, 0, 0, 0));

		}
	}

	public void addFountain(int x, int y) {
		fountains.add(new Fountain(x, y, time));
	}

	private void incrementLabels() {
		counter.setText("Number of projectiles: "
				+ String.valueOf(projectiles.size()));
		timeLabel.setText("Time: " + String.valueOf(time));
	}

	protected void paintComponent(Graphics g) {

		super.paintComponent(g);

		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		// g.translate(getWidth() / 2, (getHeight() / 2) + 100);
		g.translate(0, getHeight());
		g2 = (Graphics2D) g;
		g2.setComposite(ALPHA);

		time += incrementTimeBy;

		addNewProjectiles(time);
		projectileIter = projectiles.iterator();
		while (projectileIter.hasNext()) {
			projectile = projectileIter.next();
			double relativeTime = time - projectile.getStartTime();
			if (projectile.getLifespan() > relativeTime) {
				g.setColor(getColor(projectile));
				int xValue = (int) (((int) projectile.getX(relativeTime)));
				int yValue = (int) (((int) projectile.getY(relativeTime)));
				int projectileSize = projectile.getSize();
				g.fillOval(xValue - 5, -yValue - 5, projectileSize,
						projectileSize);
			} else {
				pool.add(projectile);
				projectileIter.remove();

			}

		}

		incrementLabels();
		repaint();
	}

	private Color getColor(Projectile p) {
		relativeTime = time - p.getStartTime();
		if (++currColor > 49) {
			currColor = 0;
		}

		if (relativeTime < 1.0) {
			return whites.get(currColor);
		} else if (relativeTime < 3) {
			return yellows.get(currColor);
		} else if (relativeTime < 4.1) {
			return oranges.get(currColor);
		} else if (relativeTime < 5.7) {
			return reds.get(currColor);
		} else if (relativeTime < 8) {
			return Color.GRAY;
		} else {
			return Color.YELLOW;
		}
	}

	private void fillColors() {
		// white=255, 255, 255
		// gray=128, 128, 128
		// red=255, 0, 0
		// orange=255, 200, 0
		// yellow=255, 255 0

		reds = new ArrayList<Color>();
		whites = new ArrayList<Color>();
		oranges = new ArrayList<Color>();
		yellows = new ArrayList<Color>();
		for (int i = 0; i < 50; i++) {
			reds.add(new Color(255, random.nextInt(100), 0));
			whites.add(new Color(255, 255, random.nextInt(40) + 215));
			oranges.add(new Color(255, random.nextInt(5) + 200, 0));
			yellows.add(new Color(255, random.nextInt(40) + 215, 0));
		}
	}
}
