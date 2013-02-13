package gurwitz.physics;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JComponent;

//hw: continuous and lifespan
//each projectile has a lifespan after which it's removed from board
//(take away trails)
//continuous: have new projectiles keep spouting out from the origin

public class GraphComponent extends JComponent {

	private double time;
	private Projectile[] projectiles;
	private Random random;
	private Color color;
	private ArrayList<Trail> trails;
	
	public GraphComponent() {
		
		super();
		time=0.0;
		trails=new ArrayList<Trail>();
		random=new Random();
		color=new Color(10, 10, 10);
		projectiles = new Projectile[10];
		for (int i = 0; i < 10; i++) {
			color=new Color(random.nextInt(255), 
					random.nextInt(255), 
					random.nextInt(255));
			projectiles[i]=new Projectile(random.nextInt(50)+40,
					random.nextInt(110)+50, color, random.nextInt(18)+5,
					random.nextInt(200)+100);
		}
	}
	
	protected void paintComponent(Graphics g) {
		
		// do all of draw calls in here
		// or in methods called here
		// overrides super method
		
		super.paintComponent(g);
		//change origin to center
		//g.translate(getWidth()/2, getHeight()/2);
		g.translate(0, getHeight());
		time += .005;
		drawGridAndTrails(g);

		for (int j = 0; j < 10; j++) {
			Projectile p = projectiles[j];
			if (p.getLifespan()<time){
				g.setColor(p.getColor());
				int xValue=(int) p.getX(time);
				int yValue=(int) p.getY(time);
				g.fillOval(xValue-5, -yValue-5, p.getSize(), p.getSize());
				trails.add(new Trail(xValue, yValue, p.getSize()));
			}
			else{
				System.out.println(p.getLifespan()+" timed out");
			}

		}
		repaint();

	}

	private void drawGridAndTrails(Graphics g) {
		g.setColor(Color.LIGHT_GRAY);
		
		for (int i=0; i<getWidth(); i+=20){
			//drawing vertical lines
			g.drawLine(i, -getHeight(), i, 0);
		}
		for (int i=0; i<getHeight(); i+=20){
			//drawing horizontal lines
			g.drawLine(0, -i, 5000, -i);
		}
		for (Trail t: trails){
			g.fillOval(t.getX()-5, -t.getY()-5, t.getSize(), t.getSize());
		}
	}
	
	

}
