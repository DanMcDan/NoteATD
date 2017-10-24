package application;

import java.io.FileNotFoundException;
import java.io.IOException;

public class SongDriver {
	public static void main(String[] args) {
		String in = args[0];//"res/music/sweetchildofmine.txt";
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
