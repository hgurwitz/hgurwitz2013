package gurwitz.concurrent;

public class SleeperThread extends Thread {

	int sleepSeconds;

	public SleeperThread(int seconds) {
		this.sleepSeconds = seconds;
	}

	@Override
	public void run() {
		try {
			System.out.println("Sleeping for " + sleepSeconds + " seconds");
			Thread.sleep(sleepSeconds * 1000);
			System.out.println("Now awake after sleeping for " + sleepSeconds
					+ " seconds");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
