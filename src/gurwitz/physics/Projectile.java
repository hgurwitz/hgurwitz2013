package gurwitz.physics;

public class Projectile {

	
	private double angle;
	private double velocity;
	
	
	public Projectile(double angle, double velocity) {
		this.angle = angle;
		this.velocity = velocity;
	}
	
	public double getX(double time){
		return Math.cos(Math.toRadians(angle))*velocity*time;
	}
	public double getY(double time){
		return Math.sin(Math.toRadians(angle))*velocity*time+(.5*-9.8*time*time);
	}
	
}
