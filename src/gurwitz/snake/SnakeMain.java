package gurwitz.snake;

import javax.swing.JFrame;

public class SnakeMain extends JFrame {

	private static final long serialVersionUID = 1L;
	public static final int SIDELENGTH = (15 * BodyPiece.SIZE);

	public SnakeMain() {
		setTitle("Snake");
		setLocationRelativeTo(null);
		setSize(SIDELENGTH + 15, SIDELENGTH + 37);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		add(new SnakeView(new GameController()));
		setVisible(true);

	}

	public static void main(String[] args) {
		new SnakeMain();
	}

}
