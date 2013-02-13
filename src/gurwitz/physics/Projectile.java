package gurwitz.physics;

import java.awt.Color;

public class Projectile {

	
	private double angle;
	private double velocity;
	private Color color;
	private int size;
	private double lifespan;
	
	public Projectile(double angle, double velocity, Color color, int size, double lifespan) {
		this.angle = angle;
		this.velocity = velocity;
		this.color=color;
		this.size=size;
		this.lifespan=lifespan;
	}
	
	public double getX(double time){
		return Math.cos(Math.toRadians(angle))*velocity*time;
	}
	public double getY(double time){
		return Math.sin(Math.toRadians(angle))*velocity*time+(.5*-9.8*time*time);
	}

	public Color getColor() {
		return color;
	}

	

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public double getLifespan() {
		return lifespan;
	}

	
}
