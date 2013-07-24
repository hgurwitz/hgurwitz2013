package gurwitz.snake;

import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class GameController {

	private MoveTimer timer;
	private int decreaseTimeIncrementBy;
	private FoodGenerator generator;
	private BodyPiece food;
	private SnakeBody snake;
	private KeyboardListener listener;
	private boolean gameOver = false;

	public GameController() {
		int loc = SnakeView.SIDELENGTH / 2;
		loc -= loc % BodyPiece.SIZE;
		snake = new SnakeBody(new BodyPiece(loc, loc), 7);
		listener = new KeyboardListener(snake, this);
		generator = new FoodGenerator(snake);
		food = generator.getNewPieceOfFood();
		decreaseTimeIncrementBy = 8;
		timer = new MoveTimer(100, 50);
	}

	public KeyboardListener getListener() {
		return listener;
	}

	public BodyPiece getFood() {
		return food;
	}

	public SnakeBody getSnake() {
		return snake;
	}

	public void checkGameStuff() {
		if (!gameOver) {

			if (timer.isTimeToMove()) {
				snake.move();
			}

			if (snake.detectCollisionsWithAPiece(food.getX(), food.getY())) {
				// try {
				// new FoundFoodSoundPlayer().play();
				// } catch (UnsupportedAudioFileException e) {
				// e.printStackTrace();
				// } catch (IOException e) {
				// e.printStackTrace();
				// } catch (LineUnavailableException e) {
				// e.printStackTrace();
				// }
				snake.addPiece();
				food = generator.getNewPieceOfFood();

				// timer.setTimeIncrement(timer.getTimeIncrement()
				// - decreaseTimeIncrementBy);

			}

			if (snake.detectCollision()) {
				gameOver = true;
				timer.setPaused(true);
				// try {
				// new GameOverSoundPlayer().play();
				// } catch (UnsupportedAudioFileException e) {
				// e.printStackTrace();
				// } catch (IOException e) {
				// e.printStackTrace();
				// } catch (LineUnavailableException e) {
				// e.printStackTrace();
				// }
			}
		}

	}

	public void pauseAndUnPause() {
		timer.pauseAndUnPause();

	}
}
