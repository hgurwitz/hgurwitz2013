package snake;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class SnakeMain extends JFrame {

	protected static final long serialVersionUID = 1L;
	protected JPanel panel;

	public SnakeMain(GameController controller) {
		setTitle("Snake");
		setLocationRelativeTo(null);
		setSize(SnakeView.SIDELENGTH, SnakeView.SIDELENGTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		panel = new JPanel();
		panel.add(new SnakeView(controller));
		panel.setPreferredSize(new Dimension(SnakeView.SIDELENGTH,
				SnakeView.SIDELENGTH));
		add(panel);
		pack();
		setVisible(true);
		setResizable(false);

	}

}
