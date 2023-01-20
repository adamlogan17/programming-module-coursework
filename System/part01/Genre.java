package part01;

/**
 * This models the possible genres of songs
 * @author Adam
 *
 */
public enum Genre {
	ROCK(0), POP(1), DANCE(2), JAZZ(3), CLASSICAL(4), OTHER(5);
	
	private int gNum;
	private String names[] = {"Rock and Roll", "Easy Listening Pop", 
			"Techno Dance", "Smooth Jazz", 
			"Classical", "Unkown Genre"};
	
	/**
	 * This is the constructor
	 * @param num - the number representing the genre
	 */
	private Genre(int num) {
		gNum = num;
	}
	
	/**
	 * When the user outputs an object of this class this method will run
	 * @return - the value of the enum
	 */
	public String toString() {
		return names[gNum];
	}
}
