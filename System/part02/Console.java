package part02;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import part01.AudioTrack;
import part01.Genre;
import part01.Jukebox;
import part01.Menu;

public class Console {
	
	static Scanner input = new Scanner(System.in); // as many menu objects are being used the
												   // Scanner object must have a global scope
												   // to prevent the system from crashing
								
	public static void main(String[] args) {
		System.out.println("Please select the type of Jukebox\nyou would like to create from the options below: ");
		System.out.println();
		
		String encodingOption = encodingMenu();
		System.out.println();
		
		Jukebox juke;
		
		// creates an instance of jukebox depending on the user's option
		if(encodingOption.equals("mp3")) {
			juke = new Jukebox(new MP3Player());
		} else {
			juke = new Jukebox(new WavPlayer());
		}
			
		String[] options = {"Display All Tracks", "Display Tracks by Artist",
				"Add New Track", "PLay a Track", "Create a Playlist",
				"Shuffle Play", "Display the Top 10", "Exit:"};
		Menu myMenu = new Menu("Jukebox", options);
		
		
		final int QUIT = options.length; // Defines a constant QUIT
		
		restoreJukebox(juke); // restores the values of the last jukebox instance into this new instance
		
		ArrayList<AudioTrack> playlist = new ArrayList<AudioTrack>(); // a collection of tracks which is used to shuffle songs
		
		int choice;
		do {
			choice = myMenu.getUserChoice();
			if (choice != QUIT) {
				playlist = processChoice(choice, juke, playlist); // resets the value of playlist to the most updates version
			}
		}
		while( choice != QUIT );
		
		saveJukeboxState(juke); // when the user quits the instance of the jukebox will be stored
		
		System.out.println("\nGoodbye!");
		
		input.close();
	}
	
	/**
	 * This will execute a method depending on the user's input
	 * @param choice choice - the user's input
	 * @param juke - an object of the Jukebox class
	 * @param numOfPlays - an array of the number if times the tracks have been played
	 * 					   the index corresponds with the index if the track of the array
	 * 					   when 'juke.getTracks() is called
	 * @param playlist - a collection of selected tracks in the jukebox
	 * @return - the most updated version of 'playlist' 
	 */
	private static ArrayList<AudioTrack> processChoice(int choice, Jukebox juke, ArrayList<AudioTrack> playlist) {
		System.out.println(); // produces a space after a choice from the menu
		
		if(juke.getTracks().length == 0 && choice != 3) {
			System.out.println("There are no tracks within Jukebox.");
			System.out.println();
			return playlist;
		}
		
		switch (choice) {
		case 1:
			displayAll(juke.getTracks());
			break;
		case 2:
			displayByArtist(juke.getTracks());
			break;
		case 3:
			addNewTrack(juke);
			break;
		case 4:
			playTrack(juke);
			break;
		case 5:
			playlist = createPlaylist(juke.getTracks(), playlist);
			break;
		case 6:
			System.out.println(juke.shuffle(playlist));
			break;
		case 7:
			displayTopTen(juke.getTracks());
			break;
		}
		System.out.println();
		return playlist;
	}

	/**
	 * This will load the jukebox with the values stored in the file 'StateOfJukebox.csv'
	 * @param juke - the instance if jukebox to be loaded to0
	 */
	private static void restoreJukebox(Jukebox juke) {
		try {
			File myFile = new File("StateOfJukebox.csv");
			Scanner mySc = new Scanner(myFile);

			mySc.nextLine(); // to take out the scanner
			
			while (mySc.hasNextLine()) {
				String record = mySc.nextLine(); // Saves the current line in the Scanner to a String.
				String[] trackDetails = record.split(",");

				Genre style = null;

				// the Jukebox class is initiated with a enum named Genre but the 
				// the value stored in the csv is a string which corresponds to a 
				// specific Genre tyoe
				switch (trackDetails[4]) {
				case "Rock and Roll":
					style = Genre.ROCK;
					break;
				case "Easy Listening Pop":
					style = Genre.POP;
					break;
				case "Techno Dance":
					style = Genre.DANCE;
					break;
				case "Smooth Jazz":
					style = Genre.JAZZ;
					break;
				case "Classical":
					style = Genre.CLASSICAL;
					break;
				case "Unkown Genre":
					style = Genre.OTHER;
					break;
				}
				
				AudioTrack track = new AudioTrack(trackDetails[1], trackDetails[2], Integer.parseInt(trackDetails[3]),
						style, trackDetails[5]);
				
				juke.addTrack(track);
				
				track.setNumOfPlays(Integer.parseInt(trackDetails[6])); 
			}
			
			mySc.close();
		} catch (FileNotFoundException e) {
			System.out.println("There is not a stored instance of Jukebox.");
			System.out.println();
		}
	}
	
	/**
	 * Stores the current state of the jukebox
	 * @param juke - the Jukebox which is being stored
	 */
	private static void saveJukeboxState(Jukebox juke) {
		try {
			PrintWriter myPw = new PrintWriter("StateOfJukebox.csv");
			myPw.println("Track Code,Track Name,Track Artist,Track Duration, Track Style, Track Encoding, Track Plays");

			AudioTrack[] tracks = juke.getTracks();
 
			for (int i = 0; i < tracks.length; i++) {
				myPw.println(tracks[i].getTrackCode() + "," + tracks[i].getTitle() + "," + tracks[i].getArtist() + ","
						+ tracks[i].getDuration() + "," + tracks[i].getStyle() + "," + tracks[i].getEncoding() + ","
						+  tracks[i].getNumOfPlays());
			}

			myPw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This will check if a particular value is within an ArrayList
	 * @param data - the ArrayList which is being checked
	 * @param target - the value that may or may not be within the ArrayList
	 * @return - whether or not the value is within the list 
	 */
	private static boolean contains(ArrayList<String> data, String target) {
		for(int index=0; index<data.size(); index++) {
			if (data.get(index).equals(target) ) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * This will provide the user with a menu of artists within the array of 
	 * AudioTracks and display the AudioTracks by the selected artist.
	 * @param tracks - the array of AudioTracks that is to be used
	 */
	private static void displayByArtist(AudioTrack[] tracks) {
		ArrayList<String> trackArtists = new ArrayList<String>(); // This is an ArrayList of all the artists
															      // in the AudioTrack[] array with no duplicates.
																  // The reason it is an ArrayList is because 
																  // the size is unknown
		
		// creates an array of either the tracks artist
		for(int i = 0; i<tracks.length; i++) {
			if(!contains(trackArtists, tracks[i].getArtist())) {
				trackArtists.add(tracks[i].getArtist());
			}
		}
		
		String[] validArtists = new String[trackArtists.size()]; // a String array of 'trackArtists' needed 
																 // to be passed into both 'bubbleSort()' and Menu
			
		// this copys the values from 'trackArtists' to 'validArtists'
		for(int i = 0; i<validArtists.length; i++) {
			validArtists[i] = trackArtists.get(i);
		}
		
		
		bubbleSort(validArtists); // sorts the lists in alphabetical order
		
		String title = "Pick an Artist";
		
		Menu artistMenu = new Menu(title, validArtists); // provides a menu in which the user can select an artist
		
		int artistIndex = artistMenu.getUserChoice() - 1; 
		
		String chosenArtist = validArtists[artistIndex];
		
		for(AudioTrack track: tracks) {
			if(track.getArtist().equals(chosenArtist)) {
				System.out.println();
				System.out.println(track.getDetails());
			}
		}
	}

	/**
	 * Provides a menu of the different available encodings
	 * @return - either 'mp3' or 'wav' depending on user input
	 */
	private static String encodingMenu() {
		String title = "Select Encoding";
		
		String[] options = {"mp3", "wav"};
		
		// Define a menu using title & options
		Menu encMenu = new Menu(title, options);
		
		int choice = encMenu.getUserChoice();
		
		return options[choice-1];
	}
	
	/**
	 * This sorts the array of 'AudioTrack' by the number of plays of each track.
	 * It does this using a bubble sort. 
	 * @param data - the array to be sorted
	 */
	public static void sortByPlays(AudioTrack[] data) {
		int swaps;
		do {
			swaps = 0;
			for (int index = 0; index < data.length - 1; index++) {
				if (data[index].getNumOfPlays() < data[index + 1].getNumOfPlays()) {
					AudioTrack temp = data[index];
					data[index] = data[index + 1];
					data[index + 1] = temp;
					swaps++;
				}
			}
		} while (swaps > 0);
	}
	
	/**
	 * This sorts an array of strings in alphabetical order and 
	 * ignores the case of the strings
	 * @param data - the array to be sorted
	 */
	public static void bubbleSort(String[] data) {
		int swaps;
		do {
			swaps = 0;
			for (int index = 0; index < data.length - 1; index++) {
				// '.compareToIgnoreCase' will return a 0 if the strings are the same,
				// a positive integer if the first string is lower in the alphabet than the second string 
				// and a negative integer if the first string is higher in the alphabet than the second string.
				// '.compareToIgnoreCase' is used instead of '.compareTo' as it ignores capitalisation
				if (data[index].compareToIgnoreCase(data[index + 1]) > 0) {
					String temp2 = data[index];
					data[index] = data[index + 1];
					data[index + 1] = temp2;
					swaps++;
				}
			}
		} while (swaps > 0);
	}
	
	/**
	 * Outputs the top 10 played songs. 
	 * If there is less than 10 songs then the remainder of 
	 * slots will display 'No song to display'.
	 * @param tracks - The tracks to be in consideration
	 */
	private static void displayTopTen(AudioTrack[] tracks) {		
		sortByPlays(tracks);

		for (int i = 0; i < 10; i++) {
			try {
				System.out.println((i+1) + ". " + tracks[i].getTitle() + " by " + tracks[i].getArtist() + " has "
						+ tracks[i].getNumOfPlays() + " play(s).");
			}
			// if there is an exception this is due to the jukebox containing less than 10 tracks
			catch (Exception e) { 
				System.out.println((i+1) + ". No song to display.");
			}
		}
		
	}
	
	/**
	 * This will provide the user with a menu of all the available tracks to be added to 
	 * a playlist and stores this in the ArrayList 'playlist'.
	 * The menu that is presented will ONLY display the valid tracks and therefore will 
	 * NOT include the tracks that are already within the playlist.
	 * @param tracks - the AudioTracks that can be added to the playlist
	 * @param playlist - the current playlist (an empty ArrayList if a playlist has not been created)
	 * @return - the updated playlist
	 */
	private static ArrayList<AudioTrack> createPlaylist(AudioTrack[] tracks, ArrayList<AudioTrack> playlist) {
		playlist = new ArrayList<AudioTrack>();
		
		AudioTrack[] tracksToAdd = new AudioTrack[tracks.length];
		
		int count = 0; // stores how many times the user has selected a song
		
		do {
			// copys the values from tracks to tracksToAdd and if there is any issue
			// it simply makes that element null
			for(int i = 0; i<tracksToAdd.length; i++) {
				try {
					tracksToAdd[i] = tracks[i];
				} catch (Exception e) {
					tracksToAdd[i] = null;
				}
			}
			
			count++; // increments count
			
			System.out.println();
			
			// if there are no more tracks to add the loop can be exited
			if(tracks.length == 0) {
				break;
			}
			
			AudioTrack selectedTrack = trackMenu(tracks); // provides the user with a menu of tracks and will return the
														  // 'AudioTrack' that was selected
			
			// if the track that is picked is a null value it is assumed that the user 
			// has finished creating the playlist
			if(selectedTrack==null) {
				break;
			}
			
			playlist.add(selectedTrack); // adds the track to the playlist

			int tracksIndex = 0; // 'tracksIndex' stores the index of the next available space to add a track
			tracks = new AudioTrack[tracksToAdd.length - count]; // as the user has selected a track to be in the 
																 // playlist the length of this list needs to be 
																 // 1 less then the tracks
			
			for(int i = 0; i<tracksToAdd.length; i++) {
				// Adds all other tracks that is not the selected track 
				// and tracks within the playlist to 'tracks'.
				// it also makes sure that the user does not add a 'null' track
				if (!selectedTrack.equals(tracksToAdd[i]) && tracksToAdd[i] != null) {
					tracks[tracksIndex++] = tracksToAdd[i];
				}
			}
		} while(true);
		
		System.out.println();
		if(playlist.size() != 0) {
			System.out.println("The following songs are in the new playlist:");
			for(int i = 0; i<playlist.size(); i++) {
				System.out.println((i+1) + ". " + playlist.get(i).getTitle() +  " by " + playlist.get(i).getArtist());
			}
		}
		else {
			System.out.println("The new playlist is empty.");
		}
		
		return playlist;
	}
	
	/**
	 * Displays all the tracks to the user either by track title
	 * @param tracks - an array of tracks to be displayed to the user
	 */
	private static void displayAll(AudioTrack[] tracks) {
		String[] trackNames = new String[tracks.length];
		
		// creates an array of either the tracks artist or title to be sorted
		for(int i = 0; i<tracks.length; i++) {
			trackNames[i] = tracks[i].getTitle();
		}
		
		// sorts the trackNames
		bubbleSort(trackNames);
		
		for (int i=0; i<trackNames.length; i++) {
			for(AudioTrack track: tracks) {
				// checks if the current track needs to be displayed
				if(trackNames[i].equals(track.getTitle())) {
					// the first element in the array does not need to check if the previous element
					// is the same as it as there is no previous element
					if(i==0) {
						System.out.println(track.getDetails());
						System.out.println();
					} 
					// checks if the previous element is not the same and if so displays the track
					// this is to prevent the same track being displayed twice
					else if (!trackNames[i-1].equals(track.getTitle())) {
						System.out.println(track.getDetails());
						System.out.println();
					}
				}
			}
		}
	}
	
	/**
	 * This allows the user to add a validated track to the jukebox
	 * @param juke - the instance of Jukebox which the record is being added too
	 */
	private static void addNewTrack(Jukebox juke) {
		System.out.println("Please enter a new track.");
		System.out.println("+++++++++++++++++++++++++");
		
		String title = "";
		String artist = "";
		
		// this repeatedly asks the user to enter in a track title and artist until they enter in a valid version
		boolean titleAndArtistValid = true;
		do {
			titleAndArtistValid = true;

			// asks the user to input a title until they enter a non-blank value
			do {
				System.out.print("Title: ");
				title = input.nextLine();
				title = title.trim();
				// the error message only needs to be displayed if the value is blank
				if (title.isBlank()) {
					System.out.println("The value you have entered is blank.");
					System.out.println("Please enter another title.");
					System.out.println();
				}
			} while (title.isBlank());

			// asks the user to input a artist until they enter a non-blank value
			do {
				System.out.print("Artist: ");
				artist = input.nextLine();
				artist = artist.trim();
				// the error message only needs to be displayed if the value is blank
				if (artist.isBlank()) {
					System.out.println("The value you have entered is blank.");
					System.out.println("Please enter another artist.");
					System.out.println();
				}
			} while (artist.isBlank());

			for (AudioTrack track : juke.getTracks()) {
				// a track is considered to be equal if the title and artist is the same (this takes case into consideration)
				if(track.getTitle().compareToIgnoreCase(title) == 0 && track.getArtist().compareToIgnoreCase(artist) == 0) {
					System.out.println("This song is already within the Jukebox.");
					System.out.println("Please enter another song.");
					System.out.println();
					titleAndArtistValid = false;
					break; // exits for loop as no more iterations are needed
				} 
				else if(title.equals("") && artist.equals("")) {
					System.out.println("You have not entered a valid track title or artist.");
					System.out.println("Please enter another song.");
					System.out.println();
					titleAndArtistValid = false;
					break; // exits for loop as no more iterations are needed
				}
			}
		} while(!titleAndArtistValid);
		
		// this repeatedly asks the user to enter the duration until a valid duration is entered
		int duration = 0;
		do {
			System.out.print("Duration: ");
			String strDuration = input.nextLine();
			strDuration = strDuration.trim();
			
			try {
				duration = Integer.parseInt(strDuration);
			} catch (Exception e) {
				System.out.println("Please enter a whole number.");
				System.out.println();
			}
			if (duration > 0) {
				break;
			}
		} while(true);
		
		// uses the menu class to allow the user to pick between a 'wav' or mp3' encoding
		System.out.println();
		String encoding = encodingMenu();
		
		String[] options = {"Rock and Roll", "Easy Listening Pop", 
				"Techno Dance", "Smooth Jazz", 
				"Classical", "Unkown Genre"};
		
		System.out.println();
		
		Menu genreMenu = new Menu("Pick a Genre", options); // creates a menu allowing the user to pick one of the valid styles for the track
		
		int choice = genreMenu.getUserChoice();
		Genre style = processGenreChoice(choice); // takes the option from the menu and converts it to the corresponding Genre
		
		// creates the object of the AudioTrack
		AudioTrack trk = new AudioTrack(title, artist, duration, style, encoding);
		
		// Adds the track to the Jukebox
		juke.addTrack(trk);
		
	}
	
	/**
	 * Takes a value between 1 and 6 and returns the corresponding Genre value. 
	 * This is used within conjunction 
	 * @param choice - the user's input
	 * @return - the selected Genre. Returns null if an invalid option 
	 *           was selected
	 */
	private static Genre processGenreChoice(int choice) {
		switch (choice) {
		case 1:
			return Genre.ROCK;
		case 2:
			return Genre.POP;
		case 3:
			return Genre.DANCE;
		case 4:
			return Genre.JAZZ;
		case 5:
			return Genre.CLASSICAL;
		case 6:
			return Genre.OTHER;
		default:
			return null;
		}
	}
	
	/**
	 * This allows the user to select a song from Jukebox and ot play
	 * the song on the Jukebox.
	 * @param juke - the instance of Jukebox that a track is being played on
	 */
	private static void playTrack(Jukebox juke) {
		AudioTrack[] tracks = juke.getTracks();
		
		AudioTrack track = trackMenu(tracks);
		
		System.out.println();
		
		if(track==null) {
			System.out.println("You have decided not to play a song.");
		} 
		else if(juke.playTrack(track)) {
			System.out.println("The song '" + track.getTitle() + "' by '" + track.getArtist() + "' is playing for " + track.getDuration() + " minutes.");
		}
		else {
			System.out.println("Song could not be played :(.");
		}
	}
	
	/**
	 * Creates a menu in which a user can select an AudioTrack
	 * @param tracks - the tracks that the user can select from
	 * @return - this returns the track object that was selected by the user. 
	 * 			 if this returns 'null' it is assumed the user wishes to quit the menu
	 */
	private static AudioTrack trackMenu(AudioTrack[] tracks) {
		String title = "All Tracks";
		
		AudioTrack[] trackOptions = new AudioTrack[tracks.length+1];
		
		String[] options = new String[tracks.length+1];
		
		for(int i=0; i<tracks.length; i++) {
			options[i] = tracks[i].getTitle() + " by " + tracks[i].getArtist();
			trackOptions[i] = tracks[i];
		}
		
		// this gives the option for the user to not select a track
		options[options.length-1] = "Exit Menu (not a track):";
		trackOptions[trackOptions.length-1] = null;
		
		// Define a menu using title & options
		Menu trckMenu = new Menu(title, options);
		
		int choice = trckMenu.getUserChoice();
		
		return trackOptions[choice-1];
	}
}