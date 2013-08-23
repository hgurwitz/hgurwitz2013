package snake.single_player;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JComponent;

public class SnakeView extends JComponent {

	private static final long serialVersionUID = 1L;
	private GameController controller;
	public static final int PIECELENGTH = 20;
	public static final int SIDELENGTH = (PIECELENGTH * BodyPiece.SIZE);

	public SnakeView(GameController controller) {
		super();
		this.controller = controller;
		setPreferredSize(new Dimension(SIDELENGTH, SIDELENGTH));
		addKeyListener(controller.getListener());
		setFocusable(true);
		setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
	}

	protected void paintComponent(Graphics g) {

		super.paintComponent(g);
		controller.getFood().paint(g);
		controller.getSnake().paint(g);
		controller.checkGameStuff();

		repaint();
	}
}
