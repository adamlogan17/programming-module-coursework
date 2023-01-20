package part01;

/**
 * This models a track which stores the details of a song
 * @author Adam
 *
 */
public class AudioTrack implements ITrack{
	private int trackCode; // the unique identifier of a track
	private static int nextCode = 1; // the next available trackCode
	private String title; // this is the name of the track
	private String artist; // this is the name of the person who wrote the song
	private int duration; // this is how long the track lasts in minutes
	private Genre style; // the genre of the track
	private String encoding; // the file type of the track
	
	private int numOfPlays; // The reason for adding this attribute is
							// to keep a record of how many times a 
							// track has been played and therefore this
							// can be used to display the top 10 in 
							// the console app. This attribute has both
							// getters and setters and are used for the
							// same reason above.
							
	
	/**
	 * This is the constructor which sets the values for the instance of AudioTrack
	 * @param title - this is the name of the track
	 * @param artist - this is the name of the person who wrote the song
	 * @param duration - this is how long the track lasts in minutes
	 * @param style - the genre of the track
	 * @param encoding - the file type of the track
	 */
	public AudioTrack(String title, String artist, int duration, 
			Genre style, String encoding) {
		this.trackCode = useNextCode();
		this.title = title;
		this.artist = artist;
		if (duration > 0) {
			this.duration = duration;
		} else {
			this.duration = 1;
		}
		this.style = style;
		this.encoding = encoding;
		
		this.numOfPlays = 0;
	}
	
	/**
	 * Calculates the next available trackCode
	 * @return - the current track code which can be used
	 */
	private int useNextCode() {
		int code = nextCode;
		nextCode++;
		return code;
	}

	/**
	 * This allows the trackCode to be accessed directly 
	 * without having to manipulate the output of 'getDetails'
	 * 
	 * @return - the unique trackCode for the track
	 */
	public int getTrackCode() {
		return this.trackCode;
	}
	
	/**
	 * This generates a string of 
	 * @return - formatted string of selected attributes
	 */
	public String getDetails() {
		String details = "Track Code: " + getTrackCode() +
				"\n" + "Title: " + getTitle() +
				"\n" + "Artist: " + getArtist() +
				"\n" + "Duration: " + getDuration() + 
				"\n" + "Encoding: " + getEncoding() + 
				"\n" + "Style: " + getStyle();
		return details;
	}

	/**
	 * This is the getter the the attribute 'title'
	 * @return - the track's title
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * This is the getter the the attribute 'artist'
	 * @return - the track's artist
	 */
	public String getArtist() {
		return this.artist;
	}

	/**
	 * This is the getter the the attribute 'duration'
	 * @return - the track's duration
	 */
	public int getDuration() {
		return this.duration;
	}

	/**
	 * This is the getter the the attribute 'style'
	 * @return - the track's style
	 */
	public String getStyle() {
		return this.style.toString();
	}

	/**
	 * This is the getter the the attribute 'encoding'
	 * @return - the track's encoding
	 */
	public String getEncoding() {
		return this.encoding;
	}

	/**
	 * This is the getter the the attribute 'numOfPlays'
	 * @return - the track's number of plays
	 */
	public int getNumOfPlays() {
		return numOfPlays;
	}
	
	/**
	 * This is the setter for 'numOfPlays' and it checks if the plays has increased
	 * as a song cannot be 'unplayed' once it is played 
	 * @param plays - the number of times the song has been played
	 */
	public void setNumOfPlays(int plays) {
		if(plays > this.numOfPlays) {
			this.numOfPlays = plays;
		}
	}

}
