package part02;

import part01.AudioPlayer;
import part01.AudioTrack;

/**
 * This models a player which can only play wav files.
 * This is a child class of AudioPlayer.
 * @author Adam
 *
 */
public class WavPlayer extends AudioPlayer {
	
	/**
	 * Conctructor for MP3Player which calls the super class.
	 */
	public WavPlayer() {
		super();
	}
	
	/**
	 * Plays an AudioTrack if it has the correct encoding
	 * @param trk - the track to be played
	 * @return - returns true if the track can play and false otherwise
	 */
	public boolean play(AudioTrack trk) {
		if (trk.getEncoding().equals("wav")) {
			return super.play(trk);
		}
		else {
			return false;
		}
	}
}
