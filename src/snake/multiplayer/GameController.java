package snake.multiplayer;

import java.awt.Color;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JComponent;

public class GameController {

	private MoveTimer timer;
	private int initialSnakeLength;
	private int decreaseTimeIncrementBy;
	private FoodGenerator generator;
	private BodyPiece food;
	private SnakeBody snake;
	private ComputerSnake computerSnake;
	private KeyboardListener listener;
	private boolean gameOver = false;

	public GameController() {
		initialSnakeLength = 7;
		int loc = SnakeView.SIDELENGTH / 2;
		loc -= loc % BodyPiece.SIZE;
		snake = new SnakeBody(new BodyPiece(loc, loc), initialSnakeLength, null);
		listener = new KeyboardListener(snake, this);
		loc = SnakeView.SIDELENGTH / 4;
		loc -= loc % BodyPiece.SIZE;
		computerSnake = new ComputerSnake(
				new BodyPiece(Color.ORANGE, loc, loc), initialSnakeLength,
				snake);
		generator = new FoodGenerator(snake, snake);
		food = generator.getNewPieceOfFood();
		computerSnake.setFood(food);
		snake.setOtherSnake(computerSnake);
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
				computerSnake.move();
				snake.move();
			}

			if (snake.detectCollisionsWithAPiece(food.getX(), food.getY())) {
				foundFood(snake);
			}

			if (computerSnake.detectCollisionsWithAPiece(food.getX(),
					food.getY())) {
				foundFood(computerSnake);
			}

			if (snake.detectCollision() || computerSnake.detectCollision()) {
				detectedCollision();
			}

		}

	}

	private void detectedCollision() {
		gameOver = true;
		timer.setPaused(true);
		System.out.println("Detected snake collision");
		/*
		 * try {
		 * new GameOverSoundPlayer().play();
		 * } catch (UnsupportedAudioFileException e) {
		 * e.printStackTrace();
		 * } catch (IOException e) {
		 * e.printStackTrace();
		 * } catch (LineUnavailableException e) {
		 * e.printStackTrace();
		 * }
		 */
	}

	private void foundFood(SnakeBody snake) {
		/*
		 * try {
		 * new FoundFoodSoundPlayer().play();
		 * } catch (UnsupportedAudioFileException e) {
		 * e.printStackTrace();
		 * } catch (IOException e) {
		 * e.printStackTrace();
		 * } catch (LineUnavailableException e) {
		 * e.printStackTrace();
		 * }
		 */

		food = generator.getNewPieceOfFood();
		computerSnake.setFood(food);
		snake.addPiece();
		// timer.setTimeIncrement(timer.getTimeIncrement()
		// - decreaseTimeIncrementBy);
	}

	public void pauseAndUnPause() {
		timer.pauseAndUnPause();

	}

	public ComputerSnake getComputerSnake() {
		return computerSnake;
	}

}
