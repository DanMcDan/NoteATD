package application;

import java.util.Scanner;

import exceptions.InvalidNoteException;

public class MelodyDriver {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		String in = null;
		Melody m;
		
		while (in == null) {
			System.out.println("Enter a number to select:\n"
					+ "1.\tEnter a note using frequency\n"
					+ "2.\tEnter a note using musical notation (ie: c#4)\n"
					+ "3.\tEnter a note by specifying a number of semitones\n"
					+ "4.\tQuit\n");
		
			in = s.nextLine();
			
			try
			{
			
				if (in.equals("1")) {
					
					System.out.println("Enter the frequency:");
					in = s.nextLine();
					
					m = new Melody(Double.parseDouble(in));
					m.playMelody();
					in = null;
					
				} else if (in.equals("2")) {
					
					System.out.println("Enter the note:");
					in = s.nextLine();
					
					m = new Melody(in);
					m.playMelody();
					in = null;
					
				} else if (in.equals("3")) {
					
					System.out.println("Enter the number of semitones:");
					in = s.nextLine();
					
					m = new Melody(Integer.parseInt(in));
					m.playMelody();
					in = null;
					
				} else if (!in.equals("4")) {
					System.out.println("Please enter a valid number");
					in = null;
				}
			
			}
			catch(InvalidNoteException e) {System.out.println("Not valid input note");}
			catch(NumberFormatException e){System.out.println("Not a valid number");}
		
		}
		s.close();
	}
}
