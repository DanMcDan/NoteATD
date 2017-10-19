package util;

import exceptions.InvalidNoteException;

public class Note extends NoteADT{
	public Note(String strNote) throws InvalidNoteException {super(strNote);}
	public Note(int semitones) throws InvalidNoteException {super(semitones);}
	public Note(double frequency) throws InvalidNoteException {super(frequency);}

	
	
	@Override
	public double getFrequency() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getSemitones() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMIDIAbsoluteNumber() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean formOctave(NoteADT note) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void modifyBySemitones(int numberOfSemitones) throws InvalidNoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int compareTo(NoteADT note) {
		// TODO Auto-generated method stub
		return 0;
	}

}
