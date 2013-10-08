package snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JComponent;

public class SnakeView extends JComponent {

	protected static final long serialVersionUID = 1L;
	protected GameController controller;
	public static final int PIECELENGTH = 25;
	public static final int SIDELENGTH = (PIECELENGTH * BodyPiece.SIZE);

	public SnakeView(GameController controller) {
		super();
		this.controller = controller;
		setPreferredSize(new Dimension(SIDELENGTH, SIDELENGTH));
		setFocusable(true);
		addKeyListener(controller.getListener());
		setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
	}

	protected void paintComponent(Graphics g) {

		super.paintComponent(g);
		controller.paint(g);
		controller.checkGameStuff();
		repaint();
	}

}
