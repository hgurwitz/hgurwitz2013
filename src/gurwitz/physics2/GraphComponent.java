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
	private Ball ball;

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
		
			color = new Color(random.nextInt(255), random.nextInt(255),
					random.nextInt(255));

			
			  int radius = 10;
		      int x = random.nextInt(800 - radius * 2 - 20) + radius + 10;
		      int y = random.nextInt(600 - radius * 2 - 20) + radius + 10;
		      int speed = 5;
		      int angleInDegree = random.nextInt(360);
		      ball = new Ball(x, y, radius, speed, angleInDegree,color);
		      
	
	}

	protected void paintComponent(Graphics g) {
	
		g.drawLine(0,0,getWidth()-1,getHeight()-1);
		
		
		 ball.draw(g);
		
		ball.moveOneStepWithCollisionDetection(0,0,800,600);

	
		
		time=time+incrementTimeBy;

		repaint();

	}


	

}
