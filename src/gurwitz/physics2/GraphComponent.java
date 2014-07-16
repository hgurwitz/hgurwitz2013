package gurwitz.physics2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JComponent;
import javax.swing.JLabel;

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
		incrementTimeBy = .0002;
		timeForMore = 0.0;
		trails = new ArrayList<Trail>();
		random = new Random();
		color = new Color(10, 10, 10);
		projectiles = new ArrayList<Projectile>();
		addNewProjectiles();
		freeze = false;
	//	addMouseListener(new MouseClickListener(this));
	//	setFocusable(true);

	}

	private void addNewProjectiles() {
		for (int i = 0; i < 1; i++) {
			color = new Color(random.nextInt(255), random.nextInt(255),
					random.nextInt(255));

			double lifespan = incrementTimeBy * 10000;

			lifespan = random.nextInt((int) lifespan);
			lifespan += (3);
			projectiles.add(new Projectile(random.nextInt(360) + 40, random
					.nextInt(360) + 50, color, 10,
					lifespan, time, 0, 0));
			projectiles.add(new Projectile(0, random
					.nextInt(360) + 50, color, 10,
					lifespan, time, 0, 0));
		/*	projectiles.add(new Projectile(500, random
					.nextInt(360) + 50, color, 10,
					lifespan, time, 0, 0));
			projectiles.add(new Projectile(1000, random
					.nextInt(360) + 50, color, 10,
					lifespan, time, 0, 0));
			projectiles.add(new Projectile(100, random
					.nextInt(360) + 50, color,10,
					lifespan, time, 0, 0));
			projectiles.add(new Projectile(-100, random
					.nextInt(360) + 50, color, 10,
					lifespan, time, 0, 0));
			projectiles.add(new Projectile(100, random
					.nextInt(360) + 50, color, 10,
					lifespan, time, 0, 0));
			
		*/
		}
	}

	protected void paintComponent(Graphics g) {
		//super.paintComponent(g2d);
		
	

		drawGrid(g);
		// trails are slowing the whole thing down
//		drawTrails(g);
		/*
		g.setColor(Color.BLACK);
		System.out.println(getWidth()+" "+getHeight());
	
	// don't show up
	 	g.fillOval(0,0, 5, 5);
	
	//	g.fillOval(100,100, 5, 5);
		g.fillOval(500,500, 5, 5);
		g.fillOval(getWidth(),getHeight(), 5, 5);
		g.fillOval(-getWidth(),-getHeight(), 5, 5);
		g.fillOval(-getWidth(),getHeight(), 5, 5);
		g.fillOval(getWidth(),-getHeight(), 5, 5);
		g.fillOval(-getWidth()/2,-getHeight()/2,  5, 5);
		g.fillOval(getWidth()/2,getHeight()/2,  5, 5);
		g.fillOval(-getWidth()/2,getHeight()/2, 5, 5);
		g.fillOval(getWidth()/2,-getHeight()/2,  5, 5);		
	//	g.fillOval(-500,500, 5, 5); 
		 
		g.fillOval(500,-500, 5, 5); //top right
		g.fillOval(500,500, 5, 5); //top right
		g.fillOval(0,0, 5, 5); //top right
					//392		-281
		g.fillOval(getWidth()/2,-getHeight()/2,  5, 5); // about middle*/
		g.drawLine(0,0,getWidth()-1,getHeight()-1);
		
		

		if (timeForMore > 1.5) {
			// /addNewProjectiles();
			timeForMore = 0;
		}

		for (Projectile p : projectiles) {
			double relativeTime = time - p.getStartTime();
			// if (p.getLifespan() > relativeTime) {
			g.setColor(p.getColor());
			int xValue = (int) p.getX(relativeTime);
			int yValue = (int) p.getY(relativeTime);
			// if touching the edge
		//	int halfwayWidth = getWidth() / 4;
		//	int halfwayHeight = getHeight() / 4;
	/*		if (((xValue == 0) || (yValue == 0))
					|| ((xValue == getWidth()) || (yValue == getHeight()))) {
				p.bounce(xValue, yValue);
			}*/
			g.fillOval(xValue , yValue, p.getSize(), p.getSize());
//			trails.add(new Trail(xValue, yValue, p.getSize()));
			// }
		}
		
		time=time+incrementTimeBy;

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
