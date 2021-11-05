package part03;

import java.util.ArrayList;

import part01.AudioPlayer;
import part01.AudioTrack;
import part01.Genre;
import part01.Jukebox;

public class JukeboxTester {

	public static void main(String[] args) {

		JukeTCase_1(); // checking if an AudioTrack can be added to Jukebox
		
		JukeTCase_2(); // checking if a 'null' value is rejected by Jukebox
		
		JukeTCase_3(); // checking if all the AudioTracks added to Jukebox is correctly saved to the Jukebox
		
		JukeTCase_4(); // checking the output of 'getTracks()' when there are no tracks in Jukebox
		
		JukeTCase_5(); // checking the output of 'getTrackList()'
		
		JukeTCase_6(); // checking the output of 'getTracksList()' when there are no tracks in Jukebox
		
		JukeTCase_7(); // checks if a valid AudioTrack can be played
		
		JukeTCase_8(); // checks if a 'null' value cannot be played
		
		JukeTCase_9(); // checking the output of '.shuffle()' when given a valid list
		
		JukeTCase_10(); // checking the output of '.shuffle()' when given a list with a null value
		
		JukeTCase_11(); // checking the output of '.shuffle()' when given a null list
		
		JukeTCase_12(); // checking the output of '.shuffle()' when given an empty list
	}

	public static void JukeTCase_1() {
		System.out.println();
		System.out.println();
		
		System.out.println("++++ JukeTCase_1: Add a Track to Jukebox ++++");
		System.out.println();
		
		Jukebox juke = new Jukebox(new AudioPlayer());
		
		AudioTrack track = new AudioTrack("25 or 6 to 4", "Chicago", 5, Genre.ROCK, "mp3");
		
		juke.addTrack(track);
		
		System.out.println("The result is shown below: ");
		System.out.println();
		for(AudioTrack trk: juke.getTracks()) {
			System.out.println(trk.getDetails());
		}
		System.out.println();
		
		System.out.println("+++++++++++++++ End of Test ++++++++++++++++");
	}
	
	public static void JukeTCase_2() {
		System.out.println();
		System.out.println();
		
		System.out.println("++++ JukeTCase_2: Add a null Track to Jukebox ++++");
		System.out.println();
		
		Jukebox juke = new Jukebox(new AudioPlayer());
		
		AudioTrack track = null;
		
		juke.addTrack(track);
		
		System.out.println("The result is shown below: ");
		System.out.println();
		
		for(int i = 0; i<juke.getTracks().length; i++) {
			System.out.println(juke.getTracks()[i]);
			
		}
		System.out.println();
		
		System.out.println("+++++++++++++++++ End of Test ++++++++++++++++++++");
	}
	
	public static void JukeTCase_3() {
		System.out.println();
		System.out.println();
		
		System.out.println("++++ JukeTCase_3: Output an array of AudioTracks within Jukebox ++++");
		System.out.println();
		
		Jukebox juke = new Jukebox(new AudioPlayer());
		
		AudioTrack track1 = new AudioTrack("25 or 6 to 4", "Chicago", 5, Genre.ROCK, "mp3");
		AudioTrack track2 = new AudioTrack("Follow God", "Kanye West", 3, Genre.OTHER, "wav");
		AudioTrack track3 = new AudioTrack("Show Me Love", "Steve Angello", 2, Genre.DANCE, "mp3");
		
		juke.addTrack(track1);
		juke.addTrack(track2);
		juke.addTrack(track3);
		
		System.out.println("The result is shown below: ");
		System.out.println();
		for(AudioTrack trk: juke.getTracks()) {
			System.out.println(trk.getDetails());
			System.out.println();
		}
		System.out.println();
		
		System.out.println("++++++++++++++++++++++++++ End of Test ++++++++++++++++++++++++++++");
	}
	
	public static void JukeTCase_4() {
		System.out.println();
		System.out.println();
		
		System.out.println("++++ JukeTCase_4: Output an array of AudioTracks when none exist in Jukebox ++++");
		System.out.println();
		
		Jukebox juke = new Jukebox(new AudioPlayer());
		
		System.out.println("The result is shown below: ");
		System.out.println();
		System.out.println("Length of array: " + juke.getTracks().length);
		System.out.println();
		
		System.out.println("+++++++++++++++++++++++++++++++++ End of Test +++++++++++++++++++++++++++++++++++");
	}
	
	public static void JukeTCase_5() {
		System.out.println();
		System.out.println();
		
		System.out.println("++++ JukeTCase_5: Output the track names and codes managed by Jukebox ++++");
		System.out.println();
		
		Jukebox juke = new Jukebox(new AudioPlayer());
		
		AudioTrack track1 = new AudioTrack("25 or 6 to 4", "Chicago", 5, Genre.ROCK, "mp3");
		AudioTrack track2 = new AudioTrack("Follow God", "Kanye West", 3, Genre.OTHER, "wav");
		AudioTrack track3 = new AudioTrack("Show Me Love", "Steve Angello", 2, Genre.DANCE, "mp3");
		
		juke.addTrack(track1);
		juke.addTrack(track2);
		juke.addTrack(track3);
		
		System.out.println("The result is shown below: ");
		System.out.println();
		System.out.println(juke.getTrackList());
		System.out.println();
		
		System.out.println("++++++++++++++++++++++++++++++ End of Test +++++++++++++++++++++++++++++++");
	}
	
	public static void JukeTCase_6() {
		System.out.println();
		System.out.println();
		
		System.out.println("++++ JukeTCase_6: Output the track names and codes managed by Jukebox when none exist ++++");
		System.out.println();
		
		Jukebox juke = new Jukebox(new AudioPlayer());
		
		System.out.println("The result is shown below: ");
		System.out.println();
		System.out.println(juke.getTrackList());
		System.out.println();
		
		System.out.println("++++++++++++++++++++++++++++++++++++++ End of Test +++++++++++++++++++++++++++++++++++++++");
	}
	
	public static void JukeTCase_7() {
		System.out.println();
		System.out.println();
		
		System.out.println("++++ JukeTCase_7: Play Valid Track ++++");
		System.out.println();
		
		AudioTrack track = new AudioTrack("25 or 6 to 4", "Chicago", 5, Genre.ROCK, "mp3");
		
		Jukebox juke = new Jukebox(new AudioPlayer());
		
		System.out.println("The result is: " + juke.playTrack(track));
		System.out.println();
		
		System.out.println("+++++++++++++ End of Test +++++++++++++");
	}
	
	public static void JukeTCase_8() {
		System.out.println();
		System.out.println();
		
		System.out.println("+++++ JukeTCase_8: Play a Null Track +++++");
		System.out.println();
		
		AudioTrack track = null;
		
		AudioPlayer player = new AudioPlayer();
		
		System.out.println("The result is: " + player.play(track));
		System.out.println();
		
		System.out.println("++++++++++++++ End of Test +++++++++++++++");
	}
	
	public static void JukeTCase_9() {
		System.out.println();
		System.out.println();
		
		System.out.println("++++ JukeTCase_9: Shuffle a Valid Playlist ++++");
		System.out.println();
		
		Jukebox juke = new Jukebox(new AudioPlayer());
		
		ArrayList<AudioTrack> list = new ArrayList<AudioTrack>();
		
		AudioTrack track1 = new AudioTrack("25 or 6 to 4", "Chicago", 5, Genre.ROCK, "mp3");
		AudioTrack track2 = new AudioTrack("Follow God", "Kanye West", 3, Genre.OTHER, "wav");
		AudioTrack track3 = new AudioTrack("Show Me Love", "Steve Angello", 2, Genre.DANCE, "mp3");
		
		list.add(track1);
		list.add(track2);
		list.add(track3);
		
		System.out.println("The result is shown below: ");
		System.out.println();
		System.out.println(juke.shuffle(list));
		System.out.println();
		
		System.out.println("++++++++++++++++ End of Test ++++++++++++++++++");
	}
	
	public static void JukeTCase_10() {
		System.out.println();
		System.out.println();
		
		System.out.println("++++ JukeTCase_10: Shuffle a Invalid Playlist ++++");
		System.out.println();
		
		Jukebox juke = new Jukebox(new AudioPlayer());
		
		ArrayList<AudioTrack> list = new ArrayList<AudioTrack>();
		
		AudioTrack track1 = new AudioTrack("25 or 6 to 4", "Chicago", 5, Genre.ROCK, "mp3");
		AudioTrack track3 = new AudioTrack("Show Me Love", "Steve Angello", 2, Genre.DANCE, "mp3");
		
		list.add(track1);
		list.add(null);
		list.add(track3);
		
		System.out.println("The result is shown below: ");
		System.out.println();
		System.out.println(juke.shuffle(list));
		System.out.println();
		
		System.out.println("+++++++++++++++++ End of Test +++++++++++++++++++++");
	}
	
	public static void JukeTCase_11() {
		System.out.println();
		System.out.println();
		
		System.out.println("++++ JukeTCase_11: Shuffle a Invalid Playlist ++++");
		System.out.println();
		
		Jukebox juke = new Jukebox(new AudioPlayer());
		
		ArrayList<AudioTrack> list = null;
		
		System.out.println("The result is shown below: ");
		System.out.println();
		
		System.out.println(juke.shuffle(list));
		
		System.out.println();
		
		System.out.println("+++++++++++++++++ End of Test +++++++++++++++++++++");
	}
	
	public static void JukeTCase_12() {
		System.out.println();
		System.out.println();
		
		System.out.println("++++ JukeTCase_12: Shuffle a Invalid Playlist ++++");
		System.out.println();
		
		Jukebox juke = new Jukebox(new AudioPlayer());
		
		ArrayList<AudioTrack> list = new ArrayList<AudioTrack>();
		
		System.out.println("The result is shown below: ");
		System.out.println();
		System.out.println(juke.shuffle(list));
		System.out.println();
		
		System.out.println("+++++++++++++++++ End of Test ++++++++++++++++++++");
	}
}
