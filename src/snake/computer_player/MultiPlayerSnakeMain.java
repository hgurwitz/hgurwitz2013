package snake.computer_player;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MultiPlayerSnakeMain extends JFrame {

	private static final long serialVersionUID = 1L;

	public MultiPlayerSnakeMain() {
		setTitle("Snake");
		setLocationRelativeTo(null);
		JPanel panel = new JPanel();
		setSize(MultiPlayerSnakeView.SIDELENGTH, MultiPlayerSnakeView.SIDELENGTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		panel.add(new MultiPlayerSnakeView(new MultiPlayerGameController()));
		panel.setPreferredSize(new Dimension(MultiPlayerSnakeView.SIDELENGTH,
				MultiPlayerSnakeView.SIDELENGTH));
		add(panel);
		pack();
		setVisible(true);
		setResizable(false);
	}

	public static void main(String[] args) {
		new MultiPlayerSnakeMain();
	}

}
