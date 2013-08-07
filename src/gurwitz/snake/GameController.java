package gurwitz.snake;

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
	private int counter;

	public GameController() {
		initialSnakeLength = 7;
		int loc = SnakeView.SIDELENGTH / 2;
		loc -= loc % BodyPiece.SIZE;
		snake = new SnakeBody(new BodyPiece(loc, loc), initialSnakeLength, null);
		listener = new KeyboardListener(snake, this);
		generator = new FoodGenerator(snake);
		food = generator.getNewPieceOfFood();
		// food = generator.getNewPieceOfFoodAtEdgeOfBoard();
		loc = SnakeView.SIDELENGTH / 4;
		loc -= loc % BodyPiece.SIZE;
		computerSnake = new ComputerSnake(
				new BodyPiece(Color.ORANGE, loc, loc), initialSnakeLength,
				snake, food);
		snake.setOtherSnake(computerSnake);
		decreaseTimeIncrementBy = 8;
		timer = new MoveTimer(100, 50);
		counter = 0;
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
				System.out.println(counter);

				computerSnake.move();
				snake.move();
				System.out.println(counter);
				counter++;
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
				food = generator.getNewPieceOfFood();
				computerSnake.setFood(food);
				snake.addPiece();

				// timer.setTimeIncrement(timer.getTimeIncrement()
				// - decreaseTimeIncrementBy);

			}

			if (computerSnake.detectCollisionsWithAPiece(food.getX(),
					food.getY())) {
				// try {
				// new FoundFoodSoundPlayer().play();
				// } catch (UnsupportedAudioFileException e) {
				// e.printStackTrace();
				// } catch (IOException e) {
				// e.printStackTrace();
				// } catch (LineUnavailableException e) {
				// e.printStackTrace();
				// }

				food = generator.getNewPieceOfFood();
				computerSnake.setFood(food);
				computerSnake.addPiece();
				// timer.setTimeIncrement(timer.getTimeIncrement()
				// - decreaseTimeIncrementBy);

			}

			if (snake.detectCollision()) {// || computerSnake.detectCollision())
											// {
				gameOver = true;
				timer.setPaused(true);
				System.out.println("Detected snake collision");
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

	public ComputerSnake getComputerSnake() {
		return computerSnake;
	}

}
