package application;

import exceptions.InvalidNoteException;

public class MelodyDriver {
	public static void main(String[] args) {
		String in = args[0];
		Melody m;
		
		boolean isNumeric = true;
		for (int i = 0; i < in.length(); i++) {
			if (!Character.isDigit(in.charAt(i))) {
				isNumeric = false;
				break;
			}
		}
		
		try
		{
			if (in.contains(".")) {
				m = new Melody(Double.parseDouble(in));
				m.playMelody();
				in = null;
			} else if (isNumeric) {
				m = new Melody(Integer.parseInt(in));
				m.playMelody();
				in = null;
			} else {
				m = new Melody(in);
				m.playMelody();
				in = null;
			}
		}
		catch(InvalidNoteException e) {System.out.println("Not valid input note");}
		catch(NumberFormatException e){System.out.println("Not a valid number");}
	}
}