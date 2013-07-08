package gurwitz.snake;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class SnakeMain extends JFrame {

	private static final long serialVersionUID = 1L;

	public SnakeMain() {
		System.out
				.println("All compliments regarding old Snakey should be emailed to brachagz@gmail.com ");
		setTitle("Old Snakey");
		setLocationRelativeTo(null);
		JPanel panel = new JPanel();
		setSize(SnakeView.SIDELENGTH, SnakeView.SIDELENGTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		panel.add(new SnakeView(new GameController()));
		panel.setPreferredSize(new Dimension(SnakeView.SIDELENGTH,
				SnakeView.SIDELENGTH));
		add(panel);
		pack();
		setVisible(true);
		setResizable(false);
	}

	public static void main(String[] args) {
		new SnakeMain();
	}

}
