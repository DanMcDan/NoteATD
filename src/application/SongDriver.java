package application;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import exceptions.InvalidNoteException;

public class SongDriver {
	public static void main(String[] args) {
		String in = "res/music/sweetchildofmine.txt";//args[0];
			try
			{
				Song song = new Song(in);
				song.playSong();	
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
}
