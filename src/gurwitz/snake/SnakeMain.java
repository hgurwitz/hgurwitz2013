package gurwitz.snake;

import javax.swing.JFrame;

public class SnakeMain extends JFrame {

	private static final long serialVersionUID = 1L;

	public SnakeMain() {
		setTitle("Snake");
		setSize(400, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		add(new SnakeView());
		setVisible(true);

	}

	public static void main(String[] args) {
		new SnakeMain();
	}

}
