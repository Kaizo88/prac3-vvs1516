package es.udc.vvsTesting.utils;

public class InvalidSongsDurationException extends Exception{
	int duration;
	
	public InvalidSongsDurationException(int duration){
		super("La duración debe ser postiva. La duracion "
				+ "pasada son "+Integer.toString(duration)+" segundos");
	}
	
	

}
