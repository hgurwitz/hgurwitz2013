package snake;

import java.awt.Graphics;
import java.util.ArrayList;

public class GameController {

	protected MoveTimer timer;
	protected int initialSnakeLength;
	protected FoodGenerator generator;
	protected BodyPiece food;
	protected boolean gameOver = false;
	protected Board board;
	protected ArrayList<Piece> obstacles;
	protected int initialLoc;
	protected KeyboardListener listener;

	public GameController() {
		initialSnakeLength = 7;
		initialLoc = SnakeView.SIDELENGTH / 3;
		initialLoc -= initialLoc % Piece.SIZE;// 100
		generator = new FoodGenerator();
		timer = new MoveTimer(100, 50);
		board = new Board();
		initializeObstacles(8);
		food = generator.getNewPieceOfFood(board);
		board.setFood(food);
		board.setObstacles(obstacles);
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

	public void checkGameStuff() {

	}

	public void pauseAndUnPause() {
		timer.pauseAndUnPause();

	}

	protected void paint(Graphics g) {
		board.paint(g);
	}

	protected void foundFood() {
		/*
		 * try { new FoundFoodSoundPlayer().play(); } catch
		 * (UnsupportedAudioFileException e) { e.printStackTrace(); } catch
		 * (IOException e) { e.printStackTrace(); } catch
		 * (LineUnavailableException e) { e.printStackTrace(); }
		 */

	}

}
