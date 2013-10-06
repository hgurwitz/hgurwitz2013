package snake.sound;

import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class GameOverSoundPlayer extends SoundPlayer {

	public static void main(String[] args) {
		try {
			GameOverSoundPlayer test = new GameOverSoundPlayer();
			test.play();
			Thread.sleep(10000);

		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

	public GameOverSoundPlayer() throws UnsupportedAudioFileException,
			IOException, LineUnavailableException {
		super("./GameOver.wav");

	}
}
