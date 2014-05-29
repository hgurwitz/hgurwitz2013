package gurwitz.physics2;

public class Parabola {

	private double angle;
	private double velocity;
	
	public Parabola(double angle, double velocity){
		this.angle=angle;
		this.velocity=velocity;
	}
	
	public double getX(double currTime){
		double x;
		x=Math.cos(Math.toRadians(angle));
		x*=(velocity*currTime);
		return x;
	}
	
	public double getY(double currTime){
		double constant=.5*(-9.8);
		double y;
		y=Math.sin(Math.toRadians(angle));
		y*=(velocity*currTime);
		double z=constant*(currTime*currTime);
		y+=z;
		return y;
	}
	
	
	public static void main (String[] args){
		Parabola p=new Parabola(37, 73);
		for (int i=0; i<=10; i++){
			System.out.println(i+" X: "+p.getX(i)+" Y: "+p.getY(i));
		}
	}
	
}
