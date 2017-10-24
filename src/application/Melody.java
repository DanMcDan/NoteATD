package application;

import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;

import exceptions.InvalidNoteException;
import util.Note;

public class Melody {
	private Note n;
	
	/**
	 * Public constructor for the melody object.
	 * @param s s represents the note's string value, input by the user
	 * @throws InvalidNoteException Notes with invalid values throw an InvalidNoteException
	 */
	public Melody(String s) throws InvalidNoteException{
		n = new Note(s); 
	}
	
	/**
	 * Public constructor for the melody object.
	 * @param semitones Represents the number of semitones above or below the concert pitch, input by the user
	 * @throws InvalidNoteException Numbers which lie outside the bounds throw an InvalidNoteException
	 */
	public Melody(int semitones) throws InvalidNoteException {
		n = new Note(semitones);
	}
	
	/**
	 * Public constructor for the melody object.
	 * @param freq freq represents the frequency of the note, input by the user
	 * @throws InvalidNoteException Numbers which lie outside the bounds throw an InvalidNoteException
	 */
	public Melody(double freq) throws InvalidNoteException {
		n = new Note(freq);
	}
	
	/**
	 * public method which takes the note input by the user, and plays it, and the note which is an octave above it.
	 */
	public void playMelody() {
		
		try
		{
			Note nOctave = new Note(n.getSemitones() + 12);
			
			System.out.println(n.getMIDIAbsoluteNumber() + " is an octave down from: " + nOctave.getMIDIAbsoluteNumber());
			
			Synthesizer midiSynth = MidiSystem.getSynthesizer();
			midiSynth.open();
			
	        MidiChannel mChannel = midiSynth.getChannels()[1];
	        
	        midiSynth.loadInstrument(midiSynth.getDefaultSoundbank().getInstruments()[12]);

			
	        
	        mChannel.noteOn(n.getMIDIAbsoluteNumber(), 100);
	        Thread.sleep(500);
	        mChannel.noteOn(nOctave.getMIDIAbsoluteNumber(), 100);
	        Thread.sleep(2000);
	        mChannel.noteOff(n.getMIDIAbsoluteNumber(), 30);
	        mChannel.noteOff(nOctave.getMIDIAbsoluteNumber(), 30);
	        midiSynth.close();
		
		} catch (InvalidNoteException e) {
			System.out.println("Octave up not available");
		}
		catch (MidiUnavailableException e) {e.printStackTrace();}
		catch (InterruptedException e) {e.printStackTrace();}
	}
	
}
