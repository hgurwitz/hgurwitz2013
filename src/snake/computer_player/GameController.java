package snake.computer_player;

import java.awt.Color;
import java.util.ArrayList;

public class GameController {

	private MoveTimer timer;
	private int initialSnakeLength;
	private int decreaseTimeIncrementBy;
	private FoodGenerator generator;
	private BodyPiece food;
	private ArrayList<Piece> obstacles;
	private ComputerSnake computerSnake;
	private boolean gameOver = false;
	private int counter;

	public GameController() {
		initialSnakeLength = 7;
		int loc = SnakeView.SIDELENGTH / 3;
		loc -= loc % Piece.SIZE;
		initializeObstacles(8);
		computerSnake = new ComputerSnake(
				new BodyPiece(Color.ORANGE, loc, loc), initialSnakeLength,
				obstacles);
		generator = new FoodGenerator(computerSnake, obstacles);
		food = generator.getNewPieceOfFood();
		computerSnake.setFood(food);
		decreaseTimeIncrementBy = 8;
		timer = new MoveTimer(100, 50);
		counter = 0;
	}

	private void initializeObstacles(int howMany) {
		obstacles = new ArrayList<Piece>();
		int y = SnakeView.SIDELENGTH / 2;
		y += y % Piece.SIZE;
		int x = ((SnakeView.PIECELENGTH - (howMany + 2)) / 2) * Piece.SIZE;
		x -= x % Piece.SIZE;
		for (int i = 1; i <= howMany; i++) {
			Piece p = new Piece(x, y);
			obstacles.add(p);
			x += Piece.SIZE;
			if (i == (howMany / 2)) {
				x += 2 * Piece.SIZE;
			}
		}

	}

	public BodyPiece getFood() {
		return food;
	}

	public ArrayList<Piece> getObstacles() {
		return obstacles;
	}

	public void checkGameStuff() {
		if (!gameOver) {

			if (timer.isTimeToMove()) {
				computerSnake.move();
			}

			if (computerSnake.detectCollisionsWithAPiece(food.getX(),
					food.getY())) {
				foundFood();
			}

			if (computerSnake.detectCollision()) {
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

	private void foundFood() {
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
		computerSnake.addPiece();
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