package gurwitz.physics;

public class Trail {
	public int x;
	public int y;
	public int size;

	public Trail(int x, int y, int size){
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