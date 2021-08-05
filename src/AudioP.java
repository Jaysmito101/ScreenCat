import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.*;
import javax.sound.sampled.*;

class AudioP{
	public static void Play(String audioFilePath, int delay){
		File audioFile = new File(audioFilePath);

		try {
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);

			AudioFormat format = audioStream.getFormat();

			DataLine.Info info = new DataLine.Info(Clip.class, format);

			Clip audioClip = (Clip) AudioSystem.getLine(info);


			audioClip.open(audioStream);

			audioClip.start();
			new Thread(new Runnable(){
				@Override
				public void run(){
					try{
						Thread.sleep(delay);
					}catch(Exception ex){
					audioClip.close();
				}
			}
		});

		} catch (UnsupportedAudioFileException ex) {
			System.out.println("The specified audio file is not supported.");
			ex.printStackTrace();
		} catch (LineUnavailableException ex) {
			System.out.println("Audio line for playing back is unavailable.");
			ex.printStackTrace();
		} catch (Exception ex) {
			System.out.println("Error playing the audio file.");
			ex.printStackTrace();
		}
	}
}