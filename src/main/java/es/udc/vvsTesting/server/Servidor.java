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
import es.udc.vvsTesting.utils.SearchLimitReachedException;
import es.udc.vvsTesting.utils.UnexistingTokenException;

public class Servidor implements Server {

	private static final Random random = new Random();
	private static final String CHARS = "abcdefghijkmnopqrstuvwxyzABCDEFGHJKLMNOPQRSTUVWXYZ234567890_";

	private String nombre;
	private String tokenAdmin;
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
		} while (tokens.containsKey(token) || token.equals(tokenAdmin));
		tokens.put(token, 10);
		return token;
	}

	public void baja(String token) throws UnexistingTokenException {
		if (!tokens.containsKey(token)) {
			throw new UnexistingTokenException(token);
		}
		tokens.remove(token);
	}

	public void agregar(Content content, String token)
			throws InsufficientPermissionsException {
		if (token.equals(tokenAdmin))
			this.contentList.add(content);
		else
			throw new InsufficientPermissionsException(token, "agregar",
					content);

	}

	public void eliminar(Content content, String token)
			throws InsufficientPermissionsException, ContentNotFoundException {
		if (token.equals(tokenAdmin))
			if (this.contentList.contains(content))
				this.contentList.remove(content);
			else
				throw new ContentNotFoundException(content);
		else
			throw new InsufficientPermissionsException(token, "eliminar",
					content);
	}

	public List<Content> buscar(String subChain, String token)
			throws UnexistingTokenException, SearchLimitReachedException {
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
			if(restantes == 0)
				throw new SearchLimitReachedException(token);
			for(int j = 0; j<tmp2.size();j++){
				tmp.add(tmp2.get(j));
				restantes--;
				if(restantes == 0)
					break;
			}
			resultado = tmp;
			if (restantes > 0) {
				this.baja(token);
				this.getTokens().put(token, restantes);
			} else
				this.baja(token);
		} else {
			resultado.add(new Anuncio());
			// meter publi
			int count = 0;
			for (int i = 1; i < tmp2.size(); i++) {
				if (count == 3) {
					resultado.add(new Anuncio());
					count = 0;
				}
				resultado.add(tmp2.get(i-1));
				
				count++;
			}
		}
		return resultado;
	}

	private static String getToken(int length) {
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
