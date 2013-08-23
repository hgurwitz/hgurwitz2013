package snake.multiplayer;

public class MoveTimer {

	private int timeIncrement;
	private long lastTime;
	private boolean paused;
	private int minTimeIncrement;

	public boolean isPaused() {
		return paused;
	}

	public void setPaused(boolean paused) {
		this.paused = paused;
	}

	public MoveTimer(int timeIncrement, int minTimeIncrement) {
		lastTime = System.currentTimeMillis();
		this.timeIncrement = timeIncrement;
		this.minTimeIncrement = minTimeIncrement;
		paused = false;
	}

	public boolean isTimeToMove() {
		if (paused) {
			return false;
		}

		long currTime = System.currentTimeMillis();
		if (currTime - lastTime > timeIncrement) {
			lastTime = currTime;
			return true;
		} else
			return false;

	}

	public void setTimeIncrement(int timeIncrement) {
		this.timeIncrement = timeIncrement > minTimeIncrement ? timeIncrement
				: this.timeIncrement;
	}

	public int getTimeIncrement() {
		return timeIncrement;
	}

	public void pauseAndUnPause() {
		paused = !paused;
	}

}
