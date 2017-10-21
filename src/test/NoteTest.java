package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import exceptions.InvalidNoteException;
import util.Note;

class NoteTest {

	@Test
	void testGetFrequency() throws InvalidNoteException {
		//fail("Not yet implemented");
		
		Note n = new Note("b#5");
		System.out.println(n.getMIDIAbsoluteNumber());
		System.out.println(n.getFrequency());
		
		Note n2 = new Note(n.getFrequency());
		System.out.println("\nNote 2:");
		System.out.println(n2.getMIDIAbsoluteNumber());
		System.out.println(n2.getFrequency());
		
	}

	@Test
	void testGetSemitones() {
		fail("Not yet implemented");
	}

	@Test
	void testGetMIDIAbsoluteNumber() {
		fail("Not yet implemented");
	}

	@Test
	void testFormOctave() {
		fail("Not yet implemented");
	}

	@Test
	void testModifyBySemitones() {
		fail("Not yet implemented");
	}

	@Test
	void testCompareTo() {
		fail("Not yet implemented");
	}

	@Test
	void testNoteString() {
		fail("Not yet implemented");
	}

	@Test
	void testNoteInt() {
		fail("Not yet implemented");
	}

	@Test
	void testNoteDouble() {
		fail("Not yet implemented");
	}

	@Test
	void testMain() {
		fail("Not yet implemented");
	}

}
