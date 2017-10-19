package util;

import javax.sound.midi.*;
public class Piano
{
	//attributes
	Synthesizer synthesizer;
	Instrument [] instruments;
	MidiChannel [] channels;
	
	public Piano()
	{
		  try
	       {
	          synthesizer = MidiSystem.getSynthesizer();
	          synthesizer.open();
	       }
	       catch(MidiUnavailableException mue)
	       {
	       }
	       instruments = synthesizer.getDefaultSoundbank().getInstruments();
	       synthesizer.loadInstrument(instruments[30]);
	       channels = synthesizer.getChannels();
	}
    public void playNotes()
    {
    	for(int i=0; i<=127; i++)
    	{
	       channels[1].noteOn(i, 127);
	       //sets the instrument to play the note.
	       channels[1].programChange(12);
	       System.out.println(i);
	       try
	       {
		    Thread.sleep(100);
	       }
	       catch(InterruptedException ie)
	       {
	       }
	       channels[1].noteOff(i,127);
	       
	       try
		{
			Thread.sleep(200);
		} catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	}
    }
    public static void main(String [] args)
    {

    //System.out.println(args[0]);
    	
       Piano p = new Piano();
       p.playNotes();
       System.exit(0);
    }
}