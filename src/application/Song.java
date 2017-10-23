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
	//private ArrayList<Note> notes = new ArrayList<Note>();
	
	public Song(String filePath) throws FileNotFoundException {
		file = new File(filePath);
//		BufferedReader br = new BufferedReader(new FileReader(new File(filePath)));
//		instructions = s.useDelimiter("\\Z").next().split(",");
//		s.close();
		
		
		
	}
	
	public void playSong() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(file));
		ArrayList<String> songNotes = new ArrayList<String>();
		String s;
		String[] instructions;
		while ((s = br.readLine()) != null) {
			instructions = s.split(",");
			for (int i = 0; i < instructions.length; i++) {
				songNotes.add(instructions[i]);
				System.out.println(instructions[i]);
			}
		}
		br.close();
		
		try
		{
			Synthesizer midiSynth = MidiSystem.getSynthesizer();
			midiSynth.open();
		    MidiChannel mChannel = midiSynth.getChannels()[1];
		    midiSynth.loadInstrument(midiSynth.getDefaultSoundbank().getInstruments()[1]);
		       
		        
			for (int i = 0; i < songNotes.size(); i++) {
				//System.out.println(instructions[i]);
				
				int waitLen = songNotes.get(i).contains("-")? 400:200;
				
				
				songNotes.set(i, songNotes.get(i).trim().replace("-", ""));// = songNotes.get(i).trim().replace("-", "");
				System.out.println(songNotes.get(i));
				
					if (songNotes.get(i).contains("r")) {
					
					Thread.sleep(waitLen);
					
				} else {
					try
					{
						Note n = new Note(songNotes.get(i));
						mChannel.noteOn(n.getMIDIAbsoluteNumber(), 127);
						Thread.sleep(waitLen);
						mChannel.noteOff(n.getMIDIAbsoluteNumber(), 200);
					} catch (InvalidNoteException e) {System.out.println("invalid note");}
				}
			}
		}
		catch(InterruptedException e) {e.printStackTrace();}
		catch (MidiUnavailableException e) {e.printStackTrace();}

	}
}
