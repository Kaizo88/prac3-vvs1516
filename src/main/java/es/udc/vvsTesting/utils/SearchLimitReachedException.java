package es.udc.vvsTesting.utils;

@SuppressWarnings("serial")
public class SearchLimitReachedException extends Exception{
	String token;
	
	public SearchLimitReachedException(String token){
		this.token = token;
		System.out.println("No quedan contenidos disponibles para el token : "+token);
	}
	
}
