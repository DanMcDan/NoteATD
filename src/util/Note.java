package util;

import exceptions.InvalidNoteException;

public class Note extends NoteADT {
	public Note(String strNote) throws InvalidNoteException		{super(strNote);}
	public Note(int semitones) throws InvalidNoteException		{super(semitones);}
	public Note(double frequency) throws InvalidNoteException	{super(frequency);}

	
	
	@Override
	public double getFrequency() {
		return (HZ_CONCERT_PITCH/32d) * Math.pow(2d, (midiNoteValue - 9d)/12d);
	}

	@Override
	public int getSemitones() {
		return midiNoteValue - MIDI_CONCERT_PITCH;
	}

	@Override
	public int getMIDIAbsoluteNumber() {
		return midiNoteValue;
	}

	@Override
	public boolean formOctave(NoteADT note) {
		return Math.abs(midiNoteValue - note.getMIDIAbsoluteNumber()) == 12;
	}

	@Override
	public void modifyBySemitones(int numberOfSemitones) throws InvalidNoteException {
		// TODO Auto-generated method stub
		if (midiNoteValue + numberOfSemitones > 127 || midiNoteValue + numberOfSemitones < 0) {
			throw new InvalidNoteException("Number of semitones produces invalid note");
		} else {
			midiNoteValue += numberOfSemitones;
		}
	}

	@Override
	public int compareTo(NoteADT note) {
		return midiNoteValue - note.getMIDIAbsoluteNumber();
	}

}



