package snake.single_player;

public class GameController {

	private MoveTimer timer;
	private int initialSnakeLength;
	private FoodGenerator generator;
	private BodyPiece food;
	private SnakeBody snake;
	private KeyboardListener listener;
	private boolean gameOver = false;

	public GameController() {
		initialSnakeLength = 7;
		int loc = SnakeView.SIDELENGTH / 2;
		loc -= loc % BodyPiece.SIZE;
		snake = new SnakeBody(new BodyPiece(loc, loc), initialSnakeLength);
		listener = new KeyboardListener(snake, this);
		loc = SnakeView.SIDELENGTH / 4;
		loc -= loc % BodyPiece.SIZE;
		generator = new FoodGenerator(snake);
		food = generator.getNewPieceOfFood();
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
				foundFood(snake);
			}

			if (snake.detectCollision()) {
				detectedCollision();
			}

		}

	}

	private void detectedCollision() {
		gameOver = true;
		timer.setPaused(true);
		/*
		 * try { new GameOverSoundPlayer().play(); } catch
		 * (UnsupportedAudioFileException e) { e.printStackTrace(); } catch
		 * (IOException e) { e.printStackTrace(); } catch
		 * (LineUnavailableException e) { e.printStackTrace(); }
		 */
	}

	private void foundFood(SnakeBody snake) {
		/*
		 * try { new FoundFoodSoundPlayer().play(); } catch
		 * (UnsupportedAudioFileException e) { e.printStackTrace(); } catch
		 * (IOException e) { e.printStackTrace(); } catch
		 * (LineUnavailableException e) { e.printStackTrace(); }
		 */

		food = generator.getNewPieceOfFood();
		snake.addPiece();
		// timer.setTimeIncrement(timer.getTimeIncrement()
		// - decreaseTimeIncrementBy);
	}

	public void pauseAndUnPause() {
		timer.pauseAndUnPause();

	}

}
