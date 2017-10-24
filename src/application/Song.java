package application;

import java.io.*;
import java.util.ArrayList;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;
import exceptions.InvalidNoteException;
import util.Note;

public class Song {
	private File file;

	/**
	 * Public constructor for the Song object
	 * @param filePath Represents the path to the file containing the music text
	 * @throws FileNotFoundException Thrown when the file can't be found
	 */
	public Song(String filePath) throws FileNotFoundException {
		file = new File(filePath);
	}

	/**
	 * Public method for playing song in the specified file.
	 * @throws IOException
	 */
	public void playSong() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(file));
		ArrayList<String> songNotes = new ArrayList<String>();
		String s;
		while ((s = br.readLine()) != null) {
			String[] instructions = s.split(",");
			for (int i = 0; i < instructions.length; i++) {
				songNotes.add(instructions[i]);
				System.out.println(instructions[i]);
			}
		}
		br.close();

		try {
			Synthesizer midiSynth = MidiSystem.getSynthesizer();
			midiSynth.open();
			MidiChannel mChannel = midiSynth.getChannels()[1];
			midiSynth.loadInstrument(midiSynth.getDefaultSoundbank().getInstruments()[0]);

			mChannel.programChange(45);
			for (int i = 0; i < songNotes.size(); i++) {

				int waitLen = songNotes.get(i).endsWith("-") ? 400 : 200;

				if (songNotes.get(i).endsWith("-"))
					songNotes.set(i, songNotes.get(i).trim().substring(0, songNotes.get(i).length() - 1));

				System.out.println(songNotes.get(i));

				if (songNotes.get(i).contains("r")) {

					Thread.sleep(waitLen);

				} else {
					try {
						Note n = new Note(songNotes.get(i));
						mChannel.noteOn(n.getMIDIAbsoluteNumber(), 127);
						Thread.sleep(waitLen);
						mChannel.noteOff(n.getMIDIAbsoluteNumber(), 200);
					} catch (InvalidNoteException e) {
						System.out.println("invalid note");
					}
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (MidiUnavailableException e) {
			e.printStackTrace();
		}

	}
}
