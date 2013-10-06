package snake.sound;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundPlayer {

	protected Clip clip;

	public SoundPlayer(String soundFileName)
			throws UnsupportedAudioFileException, IOException,
			LineUnavailableException {

		File soundFile = new File(soundFileName);
		AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
		clip = AudioSystem.getClip();
		clip.open(audioIn);

	}

	public void play() {
		clip.start();
	}
}
