package gurwitz.fire;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ClickListener implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent event) {
		FireView f = (FireView) event.getSource();
		f.addFountain(event.getX(), event.getY());

	}

	@Override
	public void mouseEntered(MouseEvent event) {

	}

	@Override
	public void mouseExited(MouseEvent event) {

	}

	@Override
	public void mousePressed(MouseEvent event) {

	}

	@Override
	public void mouseReleased(MouseEvent event) {

	}

}
