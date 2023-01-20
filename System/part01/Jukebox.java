package part01;

import java.util.ArrayList;
import java.util.Random;

/**
 * This models a jukebox which manages a collection of AudioTracks
 * @author Adam
 *
 */
public class Jukebox implements IJukebox {
	private ArrayList allTracks = new ArrayList(); // the collection of AudioTracks which the jukebox manages
	private AudioPlayer player; // The instance if AudioPlayer which allows the AudioTracks to be played
	
	/**
	 * Constructor class of the jukebox which initialises the AudioPlayer object used
	 * @param player - the instance of AudioPlayer 
	 */
	public Jukebox(AudioPlayer player) {
		this.player = player;
	}

	/**
	 * Gets the track name and track code for all the tracks managed by jukebox
	 * @return - a comma separated string of all the tracks, track code and track name 
	 */
	public String getTrackList() {
		String trackList = "";
		// checks if the there are tracks within jukebox
		if(this.allTracks.size() != 0) { 
			for (int i=0; allTracks.size()>i; i++) {
				AudioTrack track = (AudioTrack) allTracks.get(i);
				trackList = trackList + track.getTrackCode() + "," + track.getTitle();
				if (i!=allTracks.size()-1) {
					trackList += ","; // adds this comma as long as the final 
				}
			}
		}
		else {
			trackList = "There are no tracks.";
		}
		
		return trackList;
	}

	/**
	 * Plays a track using the associated AudioPlayer
	 * @param trk - the AudioTrack to be played
	 * @return - returns true if the track was played and false otherwise
	 */
	public boolean playTrack(AudioTrack trk) {
		boolean result = player.play(trk);
		return result;
	}
	
	/**
	 * Takes a list of tracks and plays them in a random order
	 * @param list - the list of tracks to be shuffled
	 * @return - a string of the songs played and if a song could not be played another message is saying this
	 */
	public String shuffle(ArrayList<AudioTrack> list) {
		if (list == null) {
			return "A null list has been given";
		}
		else if(list.size() == 0) {
			return "There are no songs to play.";
		}
	
	    String playedSongsNames = "";
	    Random rd = new Random(); // create instance of Random class
	    
	    int[] rdNum = new int[list.size()];
	    
	    int count = 0;
		do {
			boolean contains = false;
			// generate a random number in range 0 to size-1
			int num = rd.nextInt(list.size());
			// increment to bring number in range 1 to size
			num++;
			
			for (int index = 0; index < rdNum.length; index++) {
				if (rdNum[index] == num) {
					contains = true;
				}
			}

			
			if (!contains) {
				rdNum[count] = num;
				count++;
			}
	    }
		
	    while (count < list.size());
	
		for (int i = 0; i < list.size(); i++) {
			AudioTrack song = list.get(rdNum[i]-1);
			if(this.playTrack(song)) {
				playedSongsNames += "Playing '" + song.getTitle() + "' by '" + song.getArtist() +  "'" + "\n";
			} 
			else {
				playedSongsNames += "Track could not be played." + "\n";
			}
    		
		}
		return playedSongsNames;
	}

	/**
	 * Adds a track to be managed by jukebox
	 * @param trk - track to be added
	 */
	public void addTrack(AudioTrack trk) {
		if(trk != null) {
			this.allTracks.add(trk);
		}	
	}
		

	/**
	 * This gets all the tracks that is managed by jukebox
	 * @return - array of tracks managed by jukebox
	 */
	public AudioTrack[] getTracks() {
		AudioTrack[] tracks = new AudioTrack[allTracks.size()];
		for(int i = 0; allTracks.size()>i; i++) {
			tracks[i] = (AudioTrack) allTracks.get(i);
		}
		return tracks;
	}

}