package gurwitz.physics;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JComponent;

public class GraphComponent extends JComponent {

	private double time=0.0;
	private Projectile[] projectiles;
	private Random randomGenerator;
	private Color color;
	private ArrayList<Coordinate> trails;
	
	public GraphComponent() {
		
		super();
		trails=new ArrayList<Coordinate>();
		randomGenerator=new Random();
		color=new Color(10, 10, 10);
		projectiles = new Projectile[10];
		for (int i = 0; i < 10; i++) {
			color=new Color(randomGenerator.nextInt(255), 
					randomGenerator.nextInt(255), 
					randomGenerator.nextInt(255));
			projectiles[i]=new Projectile(randomGenerator.nextInt(50)+40,
					randomGenerator.nextInt(110)+50, color, randomGenerator.nextInt(18)+5);
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
				g.setColor(p.getColor());
				int xValue=(int) p.getX(time);
				int yValue=(int) p.getY(time);
				g.fillOval(xValue-5, -yValue-5, p.getSize(), p.getSize());
				//int nextX = (int) p.getX(time + .1);
				//int nextY = (int) p.getY(time + .1);
				//g.drawLine(xValue, -yValue, nextX, -nextY);
				trails.add(new Coordinate(xValue, yValue, p.getSize()));

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
		for (Coordinate c: trails){
			g.fillOval(c.getX()-5, -c.getY()-5, c.getSize(), c.getSize());
		}
	}
	
	private class Coordinate{
		private int x;
		private int y;
		private int size;
		
		public Coordinate(int x, int y, int size){
			this.x=x;
			this.y=y;
			this.size=size;
		}
		
		public int getX(){
			return x;
		}
		public int getSize(){
			return size;
		}
		
		public int getY(){
			return y;
		}
	}

}
