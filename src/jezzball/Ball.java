package jezzball;

import java.awt.Color;
import java.awt.Graphics;

//http://www3.ntu.edu.sg/home/ehchua/programming/java/J8a_GameIntro-BouncingBalls.html
public class Ball {

	private double angleRadians;
	private double angleDegrees;
	private double velocity;
	private Color color;
	private int radius;
	private double x, y;
	private double prevX, prevY;
	private Board board;

	public Ball(int x, int y, int radius, double angleDegrees, double velocity,
			Color color, Board board) {
		this.x = x;
		this.y = y;
		prevX = x;
		prevY = y;
		this.velocity = velocity;
		this.angleDegrees = angleDegrees;
		this.angleRadians = Math.toRadians(angleDegrees);
		this.radius = radius;
		this.color = color;
		this.board = board;
	}

	public void paint(Graphics g) {
		g.setColor(color);
		g.fillOval((int) (x - radius), (int) (y - radius), (int) (2 * radius),
				(int) (2 * radius));
	}


	public void moveBallOneStep() {
		System.out.println("x: " + x + " y: " + y);
		int prevWallX = (int) (prevX - (prevX % Square.SIZE)) + radius;
		int prevWallY = (int) (prevY - (prevY % Square.SIZE)) + radius;
		int wallX = (int) (x - (x % Square.SIZE)) + radius;
		int wallY = (int) (y - (y % Square.SIZE)) + radius;
		SquareContents wallContent = board.getContentsOfASquare(wallX, wallY);
		System.out.println("wallX: " + wallX + " wallY: " + wallY);

		System.out.println("content: " + wallContent);
		if ((wallContent != null &&
		 (wallContent.equals(SquareContents.MOVING_WALL) || wallContent
				.equals(SquareContents.WALL))))
		{
			System.out.println("prevX " + prevX + " x " + x + " prevY " + prevY
					+ " y " + y);
			if (prevWallX < wallX || prevWallX > wallX) {
				flipHorizontal();
			}
			if (prevWallY < wallY || prevWallY > wallY) {
				flipVertical();
			}
		}
		updateBallPosition();
	}

	private void flipHorizontal() {
		angleDegrees = 180 - angleDegrees;
	}


	private void flipVertical() {
		angleDegrees = 360 - angleDegrees;
	}



	private void updateBallPosition() {
		prevX = x;
		prevY = y;
		angleRadians = angleDegrees * Math.PI / 180;
		x += Math.cos(angleRadians) * velocity;
		y += Math.sin(angleRadians) * velocity;
	}

	public double getAngle() {
		return angleRadians;
	}

	public void setAngle(double angle) {
		this.angleRadians = angle;
	}

	public double getVelocity() {
		return velocity;
	}

	public void setVelocity(double velocity) {
		this.velocity = velocity;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getRadius() {
		return radius;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

}
