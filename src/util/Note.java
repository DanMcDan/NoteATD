package util;

import exceptions.InvalidNoteException;

public class Note extends NoteADT {
	public Note(String strNote) throws InvalidNoteException {super(strNote);}
	public Note(int semitones) throws InvalidNoteException {super(semitones);}
	public Note(double frequency) throws InvalidNoteException {super(frequency);}

	
	
	@Override
	public double getFrequency() {
		// FIXME:This is just NOT working for whatever reason
		
		double a = 440;
		double b = Math.pow(2d, (midiNoteValue - 69)/12);
		System.out.println(b);
		
		return a * b;
	}

	@Override
	public int getSemitones() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMIDIAbsoluteNumber() {
		// TODO Auto-generated method stub
		return midiNoteValue;
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
	
	public static void main(String[] args) throws InvalidNoteException {
		
		Note n = new Note("a4");
		
		System.out.println(n.getMIDIAbsoluteNumber());
		System.out.println(n.getFrequency());
		
	}

}
