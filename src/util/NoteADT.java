package util;

import exceptions.*;
/**
  *
  *
  * This is the contract specification for a musical pitch or note.
  * A note can be completely specified as one of the following:
  * 1. A number of cycles per second or frequency (in Hertz).
  * 2. The number of semitones above or below a commonly agreed upon pitch
  *    (concert pitch=440Hz = A = MIDI69) or
  * 3. The common music note name (C, D, E, F, G, A, B) with the
  *    the suffix indicating the octave number [-1,9] and an additional
  *    suffix prefix '#' for sharp notes and 'b' for flat notes or
  * 4. The MIDI absolute note number [0,127] where 60 is middle C.
  *
  * Higher pitches have higher frequencies. Two pitches are an octave apart
  * (12 semitones), if one frequency is twice the other. A semitone is
  * aproximately an increase in pitch of 1.06 times higher.
  */

public abstract class NoteADT implements Comparable<NoteADT>
{


	protected int midiNoteValue = -1;

  /**
   * A semitone is aproximately an increase in pitch of 1.06 times higher.
   */
  public static final double SEMI_TONE_INCREASE_IN_PITCH = Math.pow(2.0,1.0/12.0);
  /**
   * The agreed upon pitch "modern concert pitch"
   */
  public static final double HZ_CONCERT_PITCH = 440.0; //Hz
  public static final int    MIDI_CONCERT_PITCH = 69;
  
  /**
   * The high and low limits on the range of midi numbers.
   */
  public static final int    LOW_MIDI_ABSOLUTE_NUMBER = 0;
  public static final int    HIGH_MIDI_ABSOLUTE_NUMBER = 127;

  
  /**
   * The constructors for a note accept a frequency as a double for Hz or
   * a number of semitones as an int above or below the concert pitch (440Hz)
   * or a music note as a String such as C#4 
   * Imperfect frequencies are tuned to the nearest half pitch
   * Notes that are outside the range of 0 to 127 are invalid
   * notes and the InvalidNoteException is raised.
   */

  
  	/**
  	 * Public constructor for the Note object
  	 * @param frequency represents the frequency of the note intended.
  	 * @throws InvalidNoteException Thrown when the frequency lies out of bounds.
  	 */
	public NoteADT(double frequency) throws InvalidNoteException
	{
		//TODO: must provide the code here for this constructor
		midiNoteValue = (int) Math.round(((Math.log10(frequency / (HZ_CONCERT_PITCH/32)) / Math.log10(2.0)) * 12)+9);
		if (midiNoteValue > HIGH_MIDI_ABSOLUTE_NUMBER || midiNoteValue < 0) {
			midiNoteValue = -1;
			throw new InvalidNoteException("Frequency out of bounds");
		}
	}

	/**
	 * Public constructor for the Note object
	 * @param semitones Represents the number of semitones above or below the concert pitch.
	 * @throws InvalidNoteException Thrown when the frequency lies out of bounds.
	 */
	public NoteADT(int semitones) throws InvalidNoteException
	{
		//TODO: must provide the code here for this constructor
		midiNoteValue = MIDI_CONCERT_PITCH + semitones;
		if (midiNoteValue > HIGH_MIDI_ABSOLUTE_NUMBER || midiNoteValue < LOW_MIDI_ABSOLUTE_NUMBER) {
			midiNoteValue = -1;
			throw new InvalidNoteException("Invalid number of semitones");
		}
	}

	/**
	 * Public constructor for the Note object
	 * @param strNote Represents the note in string form.
	 * @throws InvalidNoteException Thrown when the frequency lies out of bounds.
	 */
	public NoteADT(String strNote) throws InvalidNoteException
	{
		//TODO: must provide the code here for this constructor
		char[] arr = strNote.toLowerCase().toCharArray();
		String	note	= "";
		int		octave	= 0;
		
		
		for(int i = 0; i < arr.length; i++) {
			if (arr[i] >='0' && arr[i] <= '9') {
				if (octave == 0) {
					octave = Character.getNumericValue(arr[i]);
				} else {
					throw new InvalidNoteException("Octave does not exist");
				}
			} else  if (arr[i] != '-'){
				note += arr[i];
			} else if(i == arr.length-1 || arr[i+1] != '1') {
				throw new InvalidNoteException("Octave does not exist");
			} else {
				octave = -1;
				break;
			}
		}
		
		
		if(note.contains("c")) midiNoteValue = 0;
		else if(note.contains("d"))	midiNoteValue = 2;
		else if(note.contains("e"))	midiNoteValue = 4;
		else if(note.contains("f"))	midiNoteValue = 5;
		else if(note.contains("g"))	midiNoteValue = 7;
		else if(note.contains("a"))	midiNoteValue = 9;
		else if(note.contains("b"))	midiNoteValue = 11;
		else throw new InvalidNoteException("Note does not exit [a,b,c,d,e,f,g]");
		
		
		if (note.contains("#"))		midiNoteValue += 1;
		
		//System.out.println(octave);
		midiNoteValue = midiNoteValue + ((octave+1) * 12);
		
		if (midiNoteValue > HIGH_MIDI_ABSOLUTE_NUMBER || midiNoteValue < LOW_MIDI_ABSOLUTE_NUMBER) {
			midiNoteValue = -1;
			throw new InvalidNoteException("Note out of bounds");
		}
	}

  /**
   * Returns the frequency of the current note in cycles per second- Hertz (Hz)
   * 
   * Preconditions: A valid NoteADT object exists.
   * Postconditions: The frequency of NoteADT object in hertz.
   * 
   * @return the frequency of note in Hz.
   */
   public abstract double getFrequency();

  /**
   * Returns the frequency of the current note in semitones (half steps) above or below
   * the concert pitch (the note with frequency of 440Hz).
   * 
   * Preconditions: A valid NoteADT object exists.
   * 
   * Postconditions: The number representing the number of semitones above or 
   * 				below the concert pitch of the current NoteADT object.
   * 
   * @return number of semitones above or below the concert pitch. 
   */
   public abstract int getSemitones();

  /**
   * Returns the frequency of the current note as a MIDI absolute number.
   * 
   * Preconditions: A valid NoteADT object exists.
   * 
   * Postconditions: The number representing the MIDI absolute number of the current NoteADT object.
   *  
   * @return MIDI absolute number
   */
   public abstract int getMIDIAbsoluteNumber();

   /**
    * Method determines if the current NoteADT object forms an octave with the specified NoteADT 
    * object. If the current note is 12 semitones above or 12 semitones below the other note, then
    * the notes form an octave.
    * 
    * Preconditions: A valid NoteADT object exists and a NoteADT object is supplied.
    * 
    * Postconditions: Indicates whether the current NoteADT is exactly 12 semitones above or below the
    *                 NoteADT object supplied. If so, the two notes form an octave. 
    *                 
    * @param note that can possibly form an octave with the current note. 
    * @return boolean true if the current note forms an octave with the note supplied, 
    *        false otherwise.
    */
   public abstract boolean formOctave(NoteADT note);

   /**
    * Raise or lower the current note by any number of semitones.
    * 
    * Preconditions: A valid NoteADT object exists and an integer value is supplied to change the 
    *                note by either raising or lowering.
    * 
    * Postconditions: The current NoteADT is changed by the number of semitones supplied.
    * 
    * @param numberOfSemitones semitones to raise or lower the current note by.
    * @throws InvalidNoteException if the modification results in a note with a midi absolute number
    * out of 0 to 127 range.
    */
    public abstract void modifyBySemitones(int numberOfSemitones)throws InvalidNoteException;

   /**
    * The compareTo method will be implemented by the Comparable interface specifications.
    * It will adhere to the Comparable interface CONTRACT specified in
    * the Java API.
    * Precondition: A valid NoteADT object exists and gets compared to the specified NoteADT object.
    *  
    * Postcondition: Returns a positive value if the current note is greater
	* 				 than note being supplied. 
	*                Returns 0 if both notes are equal. 
	*                Returns a negative value if the current note is less than the note being
	*                supplied.
	* 
	* @param note the note being compared to.
	* @return the result of the comparison as either negative, positive or
	* zero.
    */
    public abstract int compareTo(NoteADT note);
}