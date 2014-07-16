package gurwitz.physics;

import java.awt.Color;

public class Projectile {

	private double angle;
	private double velocity;
	private Color color;
	private int size;
	private double lifespan;
	private double startTime;
	private double sin;
	private double cos;
	private int startX;
	private int startY;

	public Projectile(double angleDegrees, double velocity, Color color,
			int size, double lifespan, double startTime, int x, int y) {
		this.angle = Math.toRadians(angleDegrees);
		this.velocity = velocity;
		this.color = color;
		this.size = size;
		this.lifespan = lifespan;
		this.startTime = startTime;
		sin = Math.sin(angle);
		cos = Math.cos(angle);
		this.startX = x;
		this.startY = y;
		int z = "space".length();
	}
	
	

	public double getX(double time) {
		return cos * velocity * time + startX;
	}

	public double getY(double time) {
		return sin * velocity * time + (.5 * -9.8 * time * time) + startY;
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

	public void setVelocity(double velocity) {
		this.velocity = velocity;
	}

	public double getLifespan() {
		return lifespan;
	}

	public double getStartTime() {
		return startTime;
	}

	public double getAngle() {
		return angle;
	}

	public double getVelocity() {
		return velocity;
	}

	public void setStartTime(double startTime) {
		this.startTime = startTime;
	}

	@Override
	public String toString() {
		return "Projectile [angle=" + Math.toDegrees(angle) + ", velocity="
				+ velocity + ", color=" + color + ", size=" + size
				+ ", lifespan=" + lifespan + ", startTime=" + startTime
				+ ", sin=" + sin + ", cos=" + cos + "]";
	}

	public int getStartX() {
		return startX;
	}

	public void setStartX(int x) {
		this.startX = x;
	}

	public int getStartY() {
		return startY;
	}

	public void setStartY(int y) {
		this.startY = y;
	}

	public void bounce(int x, int y) {
		velocity *= -1;
		angle *= 3;
		sin = Math.sin(angle);
		cos = Math.cos(angle);
		this.startX = x;
		this.startY = y;
	}

}
