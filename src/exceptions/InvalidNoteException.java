package exceptions;

public class InvalidNoteException extends Exception{
	private static final long serialVersionUID = 7085887043245033500L;
	
	
	public InvalidNoteException(String msg) {
		super(msg);
	}
}
