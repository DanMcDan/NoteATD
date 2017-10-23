package application;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import exceptions.InvalidNoteException;

public class SongDriver {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		String in = null;
		System.out.println("Enter a path to the file containing the song\nOr 'q' to quit");
		
		while (in == null) {
			try
			{
				in = s.nextLine();
				
				if (!in.equalsIgnoreCase("q")) {
					Song song = new Song(in);
					song.playSong();	
				}
			}
			catch (FileNotFoundException e) {
				in = null;
				System.out.println("File not located!\nPlease try again");
			}
			catch (IOException e) {
				in = null;
				System.out.println("File Read error");
			}
		}
		s.close();
	}
}
