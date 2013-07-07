package gurwitz.snake;

import java.awt.Graphics;

import javax.swing.JComponent;

public class SnakeView extends JComponent {

	private static final long serialVersionUID = 1L;
	private SnakeBody snake;
	private MoveTimer timer;
	private int numFoodsAfterWhichToDecreaseTimeIncrement;
	private int decreaseTimeIncrementBy;
	private KeyboardListener listener;

	public static final int LEFT_X = -150;
	public static final int TOP_Y = -150;
	public static final int HEIGHT = TOP_Y + (30 * BodyPiece.SIZE);
	public static final int WIDTH = LEFT_X + (30 * BodyPiece.SIZE);
	private FoodGenerator generator;
	private BodyPiece food;

	public SnakeView() {
		super();

		int firstX = 3 * BodyPiece.SIZE, y = 3 * BodyPiece.SIZE;
		snake = new SnakeBody(new BodyPiece(firstX, y), 5);

		listener = new KeyboardListener(snake);
		addKeyListener(listener);
		setFocusable(true);
		generator = new FoodGenerator(snake);
		food = generator.getNewPieceOfFood();

		repaint();

		numFoodsAfterWhichToDecreaseTimeIncrement = 3;
		decreaseTimeIncrementBy = 50;

		timer = new MoveTimer(500, 100);

	}

	protected void paintComponent(Graphics g) {

		super.paintComponent(g);

		g.translate(getWidth() / 2, getHeight() / 2);

		drawBoard(g);

		food.paint(g);
		snake.paint(g);
		checkGameStuff();

		repaint();
	}

	private void checkGameStuff() {
		if (timer.isTimeToMove()) {
			snake.move();
		}

		if (snake.detectCollisionsWithAPiece(food.getX(), food.getY())) {
			snake.addPiece();
			food = generator.getNewPieceOfFood();
			if ((snake.getLength() - snake.getInitialLength())
					% numFoodsAfterWhichToDecreaseTimeIncrement == 0) {
				timer.setTimeIncrement(timer.getTimeIncrement()
						- decreaseTimeIncrementBy);
			}
		}

		if (snake.detectCollision()) {
			timer.setPaused(true);
		}

	}

	public void drawBoard(Graphics g) {
		g.drawRect(LEFT_X, TOP_Y, WIDTH, HEIGHT);
	}

}
