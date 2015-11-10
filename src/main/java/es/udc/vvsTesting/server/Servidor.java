package es.udc.vvsTesting.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import es.udc.vvsTesting.content.Anuncio;
import es.udc.vvsTesting.content.Content;
import es.udc.vvsTesting.utils.ContentNotFoundException;
import es.udc.vvsTesting.utils.InsufficientPermissionsException;
import es.udc.vvsTesting.utils.UnexistingTokenException;

public class Servidor implements Server {

	private static final Random random = new Random();
	private static final String CHARS = "abcdefghijkmnopqrstuvwxyzABCDEFGHJKLMNOPQRSTUVWXYZ234567890_";

	private String nombre;
	private String tokenAdmin; // token con permisos especiales para agregar y eliminar contenido 
	private List<Content> contentList;
	private Map<String, Integer> tokens;

	public Servidor(String nombre, String tokenAdmin) {
		this.nombre = nombre;
		this.tokenAdmin = tokenAdmin;
		this.tokens = new HashMap<String, Integer>();
		this.contentList = new ArrayList<Content>();
	}

	public String obtenerNombre() {
		return nombre;
	}

	public String alta() {
		String token = "";
		do {
			token = getToken(10);
		} while (tokens.containsKey(token) || token.equals(tokenAdmin)); // genera un token que no exista en el servidor
		tokens.put(token, 10);
		return token;
	}

	public void baja(String token) throws UnexistingTokenException {
		if (!tokens.containsKey(token)) {
			throw new UnexistingTokenException(token); //en caso de que no exista el token, lanza una excepción
		}
		tokens.remove(token);
	}

	public void agregar(Content content, String token)
			throws InsufficientPermissionsException {
		if (token.equals(tokenAdmin))
			this.contentList.add(content);
		else
			throw new InsufficientPermissionsException(token, "agregar",
					content); // solo permite agregar contenido si el token coincide con tokenAdmin, sino lanza excepción

	}

	public void eliminar(Content content, String token)
			throws InsufficientPermissionsException, ContentNotFoundException {
		if (token.equals(tokenAdmin))
			if (this.contentList.contains(content))
				this.contentList.remove(content);
			else
				throw new ContentNotFoundException(content); //no existe el contenido en el servidor
		else
			throw new InsufficientPermissionsException(token, "eliminar",
					content);
	}

	public List<Content> buscar(String subChain, String token)
			throws UnexistingTokenException {
		List<Content> resultado = new ArrayList<Content>();
		List<Content> tmp = new ArrayList<Content>();
		List<Content> tmp2 = new ArrayList<Content>();
		for (Content c : this.contentList) {
			tmp = c.buscar(subChain);
			if (tmp.size() != 0)
				tmp2.addAll(tmp);
		}
		if (this.getTokens().containsKey(token)
				&& (this.getTokens().get(token).intValue() > 0)) {
			int restantes = this.getTokens().get(token).intValue();
			tmp.clear();
			for (int j = 0; j < tmp2.size(); j++) {
				tmp.add(tmp2.get(j));
				restantes--;
				if (restantes == 0)
					break;
			}
			resultado = tmp;
			if (restantes > 0) { //actualiza el token
				this.baja(token);
				this.getTokens().put(token, restantes);
			} else //da de baja el token si ya consumió los 10 contenidos
				this.baja(token);
		} else {
			resultado.add(new Anuncio());
			// meter publi
			int count = 0;
			for (int i = 0; i < tmp2.size(); i++) {
				if (count == 3) {
					resultado.add(new Anuncio());
					count = 0;
				}
				resultado.add(tmp2.get(i));

				count++;
			}
		}
		return resultado;
	}

	private static String getToken(int length) { //crea un token aleatorio
		StringBuilder token = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			token.append(CHARS.charAt(random.nextInt(CHARS.length())));
		}
		return token.toString();
	}

	public Map<String, Integer> getTokens() {
		return tokens;
	}

	public void setTokens(Map<String, Integer> tokens) {
		this.tokens = tokens;
	}

	public List<Content> getContentList() {
		return contentList;
	}

	public void setContentList(List<Content> contentList) {
		this.contentList = contentList;
	}

}
