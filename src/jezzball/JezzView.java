package jezzball;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JComponent;

public class JezzView extends JComponent {

	protected static final long serialVersionUID = 1L;
	protected GameController controller;
	public static final int PIECELENGTH = 30;
	public static final int SIDELENGTH = (PIECELENGTH * Square.SIZE);

	public JezzView(GameController controller) {
		super();
		this.controller = controller;
		controller.setView(this);
		setPreferredSize(new Dimension(SIDELENGTH, SIDELENGTH));
		setFocusable(true);
		setCursor(Cursor.getPredefinedCursor(Cursor.W_RESIZE_CURSOR));
		addMouseListener(controller.getListener());
		setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));
	}

	protected void paintComponent(Graphics g) {

		super.paintComponent(g);
		controller.paint(g);
		repaint();
	}

}
