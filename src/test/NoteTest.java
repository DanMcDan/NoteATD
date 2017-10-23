package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import exceptions.InvalidNoteException;
import util.Note;

class NoteTest {

	@Test
	void testGetFrequency() throws InvalidNoteException {
		//fail("Not yet implemented");
		
		Note n = new Note("a4");
		assertTrue(n.getFrequency() == 440.0);
		
		
		n = new Note(466.1637615189);
		assertTrue(n.getFrequency() >= 466.1637615181 - 0.000000001);
		assertTrue(n.getFrequency() <= 466.1637615181 + 0.000000001);

		n = new Note(1);
		assertTrue(n.getFrequency() >= 466.1637615181 - 0.000000001);
		assertTrue(n.getFrequency() <= 466.1637615181 + 0.000000001);
	}

	@Test
	void testGetSemitones() throws InvalidNoteException {
		//fail("Not yet implemented");
		
		Note n;
		for (int i = 0; i <= 127; i++) {
			n = new Note(i - 69);
			assertTrue(n.getSemitones() == i-69);
		}
	}

	@Test
	void testGetMIDIAbsoluteNumber() throws InvalidNoteException {
		//fail("Not yet implemented");
		
		Note n = new Note("a4");
		assertTrue(n.getMIDIAbsoluteNumber() == 69);
		
		
		n = new Note(466.1637615189);
		assertTrue(n.getMIDIAbsoluteNumber() == 70);


		n = new Note(1);
		assertTrue(n.getMIDIAbsoluteNumber() == 70);
		
		n = new Note(-1);
		assertTrue(n.getMIDIAbsoluteNumber() == 68);
	}

	@Test
	void testFormOctave() throws InvalidNoteException {
		//fail("Not yet implemented");
		Note n1 = new Note("a4");
		Note n2 = new Note("a5");
		
		assertTrue(n1.formOctave(n2));
		assertTrue(n2.formOctave(n1));
		
		
		n1 = new Note("a#4");
		n2 = new Note("a5");
		
		assertFalse(n1.formOctave(n2));
		assertFalse(n2.formOctave(n1));
		
		n1 = new Note("a#4");
		n2 = new Note("c#5");
		
		assertFalse(n1.formOctave(n2));
		assertFalse(n2.formOctave(n1));
		
	}

	@Test
	void testModifyBySemitones() throws InvalidNoteException {
//		fail("Not yet implemented");
		
		Note n = new Note(440d);
		int number = 10;
		
		n.modifyBySemitones(number);
		assertTrue(n.getSemitones() == number);
		
		number = 5;
		n = new Note(440d);
		
		n.modifyBySemitones(number);
		assertTrue(n.getSemitones() == number);
		
		
		number = -13;
		n = new Note(440d);
		
		
		n.modifyBySemitones(number);
		assertTrue(n.getSemitones() == number);
		
		
		number = -1;
		n = new Note(440d);
		
		
		n.modifyBySemitones(number);
		assertTrue(n.getSemitones() == number);
		
		number = 0;
		n = new Note(440d);
		
		
		n.modifyBySemitones(number);
		assertTrue(n.getSemitones() == number);
	}

	@Test
	void testCompareTo() throws InvalidNoteException {
		//fail("Not yet implemented");
		Note n = new Note("c#3");
		Note n2 = new Note(-19);
		assertTrue(n.compareTo(n2) == -1);
		
		n = new Note(0);
		n2 = new Note(12);
		assertTrue(n.compareTo(n2) == -12);
		
		
		n = new Note(20);
		n2 = new Note(-10);
		assertTrue(n.compareTo(n2) == 30);
	}

	@Test
	void testNoteString() throws InvalidNoteException {
		Note n;
		String[] letters = {"c", "c#", "d", "d#", "e", "f", "f#", "g", "g#", "a", "a#", "b"};
		
		for (int i = 0; i < letters.length; i++) {
			for (int j = -1; j <= ((i > 7) ? 8:9); j++) {				
				n = new Note(letters[i] + j);
				assertTrue(n.getMIDIAbsoluteNumber() == i + (12*(j+1)));
			}
		}
	}

	@Test
	void testNoteInt() throws InvalidNoteException {
		//fail("Not yet implemented");
		Note n;
		for (int i = 0; i <= 127; i++) {
			n = new Note(i - 69);
			assertTrue(n.getSemitones() == i-69);
		}
	}

	@Test
	void testNoteDouble() throws InvalidNoteException {
		Note n;
		Note n2;
		for (int i = 0; i <= 127; i++) {
			n = new Note(i - 69);
			n2 = new Note(n.getFrequency());
			assertTrue(n.getMIDIAbsoluteNumber() == n2.getMIDIAbsoluteNumber());
			assertTrue(n.getFrequency() == n2.getFrequency());
			assertTrue(n.getSemitones() == n2.getSemitones());
		}
	}
}
