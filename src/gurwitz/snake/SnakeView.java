package gurwitz.snake;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;

public class SnakeView extends JComponent {

	private static final long serialVersionUID = 1L;
	private GameController controller;

	public SnakeView(GameController controller) {
		super();
		setSize(SnakeMain.SIDELENGTH, SnakeMain.SIDELENGTH);
		this.controller = controller;
		addKeyListener(controller.getListener());
		setFocusable(true);
	}

	protected void paintComponent(Graphics g) {

		super.paintComponent(g);
		controller.getFood().paint(g);
		controller.getSnake().paint(g);
		controller.checkGameStuff();

		repaint();
	}

}
