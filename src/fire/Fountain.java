package fire;

import gurwitz.physics.Projectile;

import java.awt.Color;
import java.util.Random;

public class Fountain {

	private int x;
	private int y;
	private double startTime;
	private Random random;

	public Fountain(int x, int y, double time) {
		this.x = x;
		this.y = y;
		random = new Random();
		this.startTime = time;
	}

	public Projectile getProjectile() {
		return new Projectile((random.nextInt(70) + 55),
				random.nextInt(40) + 40, Color.WHITE, random.nextInt(15) + 4,
				random.nextInt(2) + 7, 0, x, y);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public double getStartTime() {
		return startTime;
	}

	public void setStartTime(double startTime) {
		this.startTime = startTime;
	}

}
