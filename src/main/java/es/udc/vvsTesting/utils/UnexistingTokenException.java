package es.udc.vvsTesting.utils;


/**
 *
 * @author Diego Campelo
 */
@SuppressWarnings("serial")
public class UnexistingTokenException extends Exception {

    String token;

    public UnexistingTokenException(String token) {
        this.token = token;
        System.out.println("Token no existente: " + token);
    }
}