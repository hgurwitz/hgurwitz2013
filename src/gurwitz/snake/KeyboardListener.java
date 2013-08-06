package gurwitz.snake;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardListener implements KeyListener {

	private SnakeBody snake;
	private GameController controller;

	public KeyboardListener(SnakeBody snake, GameController controller) {
		super();
		this.snake = snake;
		this.controller = controller;
	}

	@Override
	public void keyPressed(KeyEvent event) {
		int keyCode = event.getKeyCode();
		if (keyCode == KeyEvent.VK_P) {
			controller.pauseAndUnPause();
		} else if (snake != null) {
			switch (keyCode) {
			case KeyEvent.VK_LEFT:
			case KeyEvent.VK_KP_LEFT:
			case KeyEvent.VK_A:
				snake.changeDirection(Direction.LEFT);
				break;
			case KeyEvent.VK_RIGHT:
			case KeyEvent.VK_KP_RIGHT:
			case KeyEvent.VK_D:
				snake.changeDirection(Direction.RIGHT);
				break;
			case KeyEvent.VK_UP:
			case KeyEvent.VK_KP_UP:
			case KeyEvent.VK_W:
				snake.changeDirection(Direction.UP);
				break;
			case KeyEvent.VK_DOWN:
			case KeyEvent.VK_KP_DOWN:
			case KeyEvent.VK_S:
				snake.changeDirection(Direction.DOWN);
				break;
			default:
				break;

			}

		} else {
			System.out.println("error--snake is null");
		}

	}

	public void setSnake(SnakeBody snake) {
		this.snake = snake;
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

}
