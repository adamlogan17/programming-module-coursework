package part01;

/**
 * This models a device which can play music
 * @author Adam
 *
 */
public class AudioPlayer implements IPlayer {
	private AudioTrack currentTrack; // the track that is currently being played by the player

	/**
	 * Plays an AudioTrack
	 * @param trk - the track to be played
	 * @return - returns true if the track can play and false otherwise
	 */
	public boolean play(AudioTrack trk) {
		if (trk == null) {
			return false;
		} else {
			this.currentTrack = trk;
			trk.setNumOfPlays(trk.getNumOfPlays()+1); // updates numOfPlays if the song is in fact played
			return true;
		}
	}

}
