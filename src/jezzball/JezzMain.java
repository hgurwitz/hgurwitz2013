package jezzball;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class JezzMain extends JFrame {

	protected static final long serialVersionUID = 1L;
	protected JPanel panel;

	public JezzMain(GameController controller) {
		setTitle("Jezz");
		// setLocationRelativeTo(null);
		setSize(JezzView.SIDELENGTH, JezzView.SIDELENGTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		panel = new JPanel();
		panel.add(new JezzView(controller));
		panel.setPreferredSize(new Dimension(JezzView.SIDELENGTH,
				JezzView.SIDELENGTH));
		add(panel);
		pack();
		setVisible(true);
		setResizable(false);

	}

	public static void main(String[] args) {
		new JezzMain(new GameController());
	}
}
