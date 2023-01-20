package part03;

import part01.*;

public class AudioPlayerTester {

	public static void main(String[] args) {
		PLayerTCase_1();

		PLayerTCase_2();

	}
	
	public static void PLayerTCase_1() {
		System.out.println();
		System.out.println("++++ PlayerTCase_1: Play Valid Track ++++");
		AudioTrack track = new AudioTrack("25 or 6 to 4", "Chicago", 5, Genre.ROCK, "mp3");
		
		AudioPlayer player = new AudioPlayer();
		
		System.out.println("The result is: " + player.play(track));
		
		System.out.println("++++++++++++++ End of Test ++++++++++++++");
	}
	
	public static void PLayerTCase_2() {
		System.out.println();
		System.out.println("+++++ PlayerTCase_2: Play a Null Track +++++");
		AudioTrack track = null;
		
		AudioPlayer player = new AudioPlayer();
		
		System.out.println("The result is: " + player.play(track));
		
		System.out.println("+++++++++++++++ End of Test ++++++++++++++++");
	}
}
