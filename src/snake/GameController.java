package snake;

import java.awt.Graphics;
import java.util.ArrayList;

import snake.enums.Direction;

public class GameController {

	protected MoveTimer timer;
	protected int initialSnakeLength;
	protected FoodGenerator generator;
	protected BodyPiece food;
	protected boolean gameOver = false;
	protected Board board;
	protected ArrayList<Piece> obstacles;
	protected int initialX, initialY;
	protected KeyboardListener listener;
	protected ComputerSnake computerSnake;
	protected SnakeBody playerSnake;

	public GameController() {
		initialSnakeLength = 7;
		initialX = (SnakeView.SIDELENGTH - (initialSnakeLength * Piece.SIZE));
		initialY = (SnakeView.SIDELENGTH / 3);
		initialY -= (initialY % Piece.SIZE);
		generator = new FoodGenerator();
		timer = new MoveTimer(100, 50);
		board = new Board();
		initializeObstacles(8);
		food = generator.getNewPieceOfFood(board);
		board.setFood(food);
		board.setObstacles(obstacles);
	}

	protected void initializePlayerSnake() {
		playerSnake = new SnakeBody(new BodyPiece(initialX, initialY),
				initialSnakeLength);
		listener = new KeyboardListener(playerSnake, this);
		board.setPlayerSnake(playerSnake);
	}

	protected void initializeComputerSnake() {
		computerSnake = new ComputerSnake(new BodyPiece(initialY, initialY,
				Direction.LEFT), initialSnakeLength, board);
		computerSnake.setFood(food);
		board.setComputerSnake(computerSnake);
	}

	private void initializeObstacles(int howMany) {
		obstacles = new ArrayList<Piece>();
		int y = SnakeView.SIDELENGTH / 2;
		y -= y % Piece.SIZE;
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

	public Board getBoard() {
		return board;
	}

	public KeyboardListener getListener() {
		return listener;
	}

	protected void detectedCollision() {
		gameOver = true;
		timer.setPaused(true);
		System.out.println("Detected snake collision");
		/*
		 * try { new GameOverSoundPlayer().play(); } catch
		 * (UnsupportedAudioFileException e) { e.printStackTrace(); } catch
		 * (IOException e) { e.printStackTrace(); } catch
		 * (LineUnavailableException e) { e.printStackTrace(); }
		 */

	}

	public void snakeMove(SnakeBody snake) {
		snake.move();

		if (snake.detectCollisionsWithFood(food.getXY())) {
			foundFood(snake);
		}
		if (snake.detectCollision(board)) {
			System.out.println("Detected snake collision");
			detectedCollision();
		} else {
			if (snake instanceof ComputerSnake) {
				board.setComputerSnake(snake.getHead());
			} else {
				board.setPlayerSnake(snake.getHead());
			}
			board.setEmpty(snake.getOldTail());
		}

	}

	public void pauseAndUnPause() {
		timer.pauseAndUnPause();

	}

	protected void paint(Graphics g) {
		board.paint(g);
	}

	protected void foundFood(SnakeBody s) {
		/*
		 * try { new FoundFoodSoundPlayer().play(); } catch
		 * (UnsupportedAudioFileException e) { e.printStackTrace(); } catch
		 * (IOException e) { e.printStackTrace(); } catch
		 * (LineUnavailableException e) { e.printStackTrace(); }
		 */

		board.setEmpty(food);
		food = generator.getNewPieceOfFood(board);
		board.setFood(food);
		s.addPiece();
		if (computerSnake != null) {
			computerSnake.setFood(food);
		}
		if (s instanceof ComputerSnake) {
			board.setComputerSnake(s.getTail());
		} else {
			board.setPlayerSnake(s.getTail());
		}

	}

	public void checkGameStuff() {

	}
}
