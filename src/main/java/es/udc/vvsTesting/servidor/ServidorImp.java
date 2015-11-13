package es.udc.vvsTesting.servidor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import es.udc.vvsTesting.contenido.Anuncio;
import es.udc.vvsTesting.contenido.Contenido;
import es.udc.vvsTesting.utils.ContentNotFoundException;
import es.udc.vvsTesting.utils.InsufficientPermissionsException;
import es.udc.vvsTesting.utils.UnexistingTokenException;

public class ServidorImp implements Servidor {

	private static final Random random = new Random();
	private static final String CHARS = "abcdefghijkmnopqrstuvwxyzABCDEFGHJKLMNOPQRSTUVWXYZ234567890_";
	private static final int CONTENIDO_ENTRE_ANUNCIOS = 3;
	private static final int CONTENIDOS_POR_TOKEN = 10;

	private String nombre;
	private String tokenAdmin; // token con permisos especiales para agregar y eliminar contenido 
	private List<Contenido> contentList;
	private Map<String, Integer> tokens;

	public ServidorImp(String nombre, String tokenAdmin) {
		this.nombre = nombre;
		this.tokenAdmin = tokenAdmin;
		this.tokens = new HashMap<String, Integer>();
		this.contentList = new ArrayList<Contenido>();
	}

	public String obtenerNombre() {
		return nombre;
	}

	public String alta() {
		String token = "";
		do {
			token = getToken(10);
		} while (tokens.containsKey(token) || token.equals(tokenAdmin)); // genera un token que no exista en el servidor
		tokens.put(token, CONTENIDOS_POR_TOKEN);
		return token;
	}

	public void baja(String token) throws UnexistingTokenException {
		if (!tokens.containsKey(token)) {
			throw new UnexistingTokenException(token); //en caso de que no exista el token, lanza una excepción
		}
		tokens.remove(token);
	}

	public void agregar(Contenido content, String token)
			throws InsufficientPermissionsException {
		if (token.equals(tokenAdmin))
			this.contentList.add(content);
		else
			throw new InsufficientPermissionsException(token, "agregar",
					content); // solo permite agregar contenido si el token coincide con tokenAdmin, sino lanza excepción

	}

	public void eliminar(Contenido content, String token)
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

	public List<Contenido> buscar(String subChain, String token)
			throws UnexistingTokenException {
		List<Contenido> resultado = new ArrayList<Contenido>();
		List<Contenido> tmp = new ArrayList<Contenido>();
		List<Contenido> tmp2 = new ArrayList<Contenido>();
		for (Contenido c : this.contentList) {
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
				if (count == CONTENIDO_ENTRE_ANUNCIOS ) {
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

	public List<Contenido> getContentList() {
		return contentList;
	}

	public void setContentList(List<Contenido> contentList) {
		this.contentList = contentList;
	}

}
