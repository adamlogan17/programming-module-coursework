package part03;

import part01.*;

public class AudioTrackTester {

	public static void main(String[] args) {
		TrackTCase_1(); // outputting correct track details
		
		TrackTCase_2(); // outputting correct track code
		
		TrackTCase_3(); // outputting correct title
		
		TrackTCase_4(); // outputting correct artist
		
		TrackTCase_5(); // outputting correct duration
		
		TrackTCase_6(); // outputting correct style
		
		TrackTCase_7(); // outputting correct encoding
		
		TrackTCase_8(); // outputting correct number of plays
	
		TrackTCase_9(); // setting valid number of plays
		
		TrackTCase_10(); // setting a negative value of number of plays
		
		TrackTCase_11(); // setting a value less than the current number of plays

	}
	
	
	public static void TrackTCase_1() {
		System.out.println();
		System.out.println();
		
		System.out.println("++++ TrackTCase_1: Output Track Details ++++");
		System.out.println();
		
		AudioTrack track = new AudioTrack("25 or 6 to 4", "Chicago", 5, Genre.ROCK, "mp3");
		
		System.out.println("The result is shown below: ");
		System.out.println();
		System.out.println(track.getDetails());
		System.out.println();
		
		System.out.println("+++++++++++++++ End of Test ++++++++++++++++");
	}
	
	public static void TrackTCase_2() {
		System.out.println();
		System.out.println();
		
		System.out.println("++++ TrackTCase_2: Output Track Code ++++");
		System.out.println();
		
		AudioTrack track = new AudioTrack("Born To Run", "Bruce Springsteen", 4, Genre.ROCK, "mp3");
		
		System.out.println("The track code is: " + track.getTrackCode());
		System.out.println();
		
		System.out.println("+++++++++++++++ End of Test ++++++++++++++");
	}
	
	public static void TrackTCase_3() {
		System.out.println();
		System.out.println();
		
		System.out.println("++++ TrackTCase_3: Output Track Title ++++");
		System.out.println();
		
		AudioTrack track = new AudioTrack("Born To Run", "Bruce Springsteen", 4, Genre.ROCK, "mp3");
		
		System.out.println("The track title is: " + track.getTitle());
		System.out.println();
		
		System.out.println("+++++++++++++++ End of Test +++++++++++++++");
	}
	
	public static void TrackTCase_4() {
		System.out.println();
		System.out.println();
		
		System.out.println("++++ TrackTCase_4: Output Track Artist ++++");
		System.out.println();
		
		AudioTrack track = new AudioTrack("Born To Run", "Bruce Springsteen", 4, Genre.ROCK, "mp3");
		
		System.out.println("The track artist is: " + track.getArtist());
		System.out.println();
		
		System.out.println("+++++++++++++++ End of Test +++++++++++++++");
	}
	
	public static void TrackTCase_5() {
		System.out.println();
		System.out.println();
		
		System.out.println("++++ TrackTCase_5: Output Track Duration ++++");
		System.out.println();
		
		AudioTrack track = new AudioTrack("Born To Run", "Bruce Springsteen", 4, Genre.ROCK, "mp3");
		
		System.out.println("The track duration is: " + track.getDuration());
		System.out.println();
		
		System.out.println("+++++++++++++++ End of Test +++++++++++++++++");
	}
	
	public static void TrackTCase_6() {
		System.out.println();
		System.out.println();
		
		System.out.println("++++ TrackTCase_6: Output Track Style ++++");
		System.out.println();
		
		AudioTrack track = new AudioTrack("Born To Run", "Bruce Springsteen", 4, Genre.ROCK, "mp3");
		
		System.out.println("The track style is: " + track.getStyle());
		System.out.println();
		
		System.out.println("+++++++++++++++ End of Test ++++++++++++++");
	}
	
	public static void TrackTCase_7() {
		System.out.println();
		System.out.println();
		
		System.out.println("++++ TrackTCase_7: Output Track Encoding ++++");
		System.out.println();
		
		AudioTrack track = new AudioTrack("Born To Run", "Bruce Springsteen", 4, Genre.ROCK, "mp3");
		
		System.out.println("The track encoding is: " + track.getEncoding());
		System.out.println();
		
		System.out.println("+++++++++++++++ End of Test +++++++++++++++");
	}
	
	public static void TrackTCase_8() {
		System.out.println();
		System.out.println();
		
		System.out.println("++++ TrackTCase_8: Output Track number of plays ++++");
		System.out.println();
		
		AudioTrack track = new AudioTrack("Born To Run", "Bruce Springsteen", 4, Genre.ROCK, "mp3");
		
		System.out.println("The track numOfPlays is: " + track.getNumOfPlays());
		System.out.println();
		
		System.out.println("+++++++++++++++++++ End of Test ++++++++++++++++++++");
	}
	
	public static void TrackTCase_9() {
		System.out.println();
		System.out.println();
		
		System.out.println("++++ TrackTCase_9: Set valid number of plays ++++");
		System.out.println();
		
		AudioTrack track = new AudioTrack("Born To Run", "Bruce Springsteen", 4, Genre.ROCK, "mp3");
		
		System.out.println("Before numOfPlays is set: " + track.getNumOfPlays());
		track.setNumOfPlays(6);
		System.out.println("After numOfPlays is set: " + track.getNumOfPlays());
		System.out.println();
		
		System.out.println("+++++++++++++++++ End of Test +++++++++++++++++++");
	}
	
	public static void TrackTCase_10() {
		System.out.println();
		System.out.println();
		
		System.out.println("++++ TrackTCase_10: Set invalid number of plays ++++");
		System.out.println();
		
		AudioTrack track = new AudioTrack("Born To Run", "Bruce Springsteen", 4, Genre.ROCK, "mp3");
		
		System.out.println("Before numOfPlays is set: " + track.getNumOfPlays());
		track.setNumOfPlays(-2);
		
		System.out.println("After numOfPlays is set: " + track.getNumOfPlays());
		System.out.println();
		
		System.out.println("+++++++++++++++++++ End of Test ++++++++++++++++++++");
	}
	
	public static void TrackTCase_11() {
		System.out.println();
		System.out.println();
		
		System.out.println("++++ TrackTCase_11: Set invalid number of plays ++++");
		System.out.println();
		
		AudioTrack track = new AudioTrack("Born To Run", "Bruce Springsteen", 4, Genre.ROCK, "mp3");
		
		System.out.println("Before numOfPlays is set: " + track.getNumOfPlays());
		track.setNumOfPlays(6);
		System.out.println("1st time numOfPlays is set: " + track.getNumOfPlays());
		track.setNumOfPlays(3);
		System.out.println("2nd time numOfPlays is set: " + track.getNumOfPlays());
		System.out.println();
		
		System.out.println("+++++++++++++++++++ End of Test ++++++++++++++++++++");
	}
	

}
