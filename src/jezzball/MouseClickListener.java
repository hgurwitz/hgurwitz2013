package jezzball;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseClickListener implements MouseListener {

	private GameController controller;

	public MouseClickListener(GameController controller) {
		super();
		this.controller = controller;
	}

	public void mouseClicked(MouseEvent arg0) {
		if (arg0.getButton() == (MouseEvent.BUTTON1)) {
			System.out.println("left mouse clicked");
			System.out.println(arg0.getX() + " " + arg0.getY());
			controller.mouseClicked(arg0.getX(), arg0.getY());

		}
		if (arg0.getButton() == (MouseEvent.BUTTON3)) {
			System.out.println("right mouse clicked");
			controller.changeDirection();
		}

	}

	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mousePressed(MouseEvent arg0) {

	}

	public void mouseReleased(MouseEvent arg0) {

	}

}
