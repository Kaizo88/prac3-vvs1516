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
/**
 * Implementacion de servidor.
 * @author diego.campelo
 *
 */
public class ServidorImp implements Servidor {

	/**
	 * Generador de randoms.
	 */
	private static final Random RANDOM = new Random();
	/**
	 * Chars para la generación de tokens.
	 */
	private static final String CHARS =
			"abcdefghijkmnopqrstuvwxyzABCDEFGHJKLMNOPQRSTUVWXYZ234567890_";
	/**
	 * Contenidos a insertar antes de cada anuncio.
	 */
	private static final int CONTENIDO_ENTRE_ANUNCIOS = 3;
	/**
	 * Longitud token.
	 */
	private static final int LENGTH_TOKEN = 10;
	/**
	 * Contenidos de los que dispone cada token.
	 */
	private static final int CONTENIDOS_POR_TOKEN = 10;
	/**
	 * nombre del servidor.
	 */
	private String nombre;
	/**
	 * token del admin.
	 */
	private String tokenAdmin;
	/**
	 * Lista de contenidos del servidor.
	 */
	private List<Contenido> contentList;
	/**
	 * Map con los tokens registrados.
	 */
	private Map<String, Integer> tokens;

	/**
	 * Constructor del servidor.
	 * @param nombre nombre del servidor
	 * @param tokenAdmin token del administrador.
	 */
	public ServidorImp(final String nombre, final String tokenAdmin) {
		this.nombre = nombre;
		this.tokenAdmin = tokenAdmin;
		this.tokens = new HashMap<String, Integer>();
		this.contentList = new ArrayList<Contenido>();
	}

	/**
	 * Metodo que obtiene el nombre del servidor.
	 * @return String nombre del servidor
	 * */
	public String obtenerNombre() {
		return nombre;
	}
	/**
	 * Metodo que genera un nuevo token en el servidor.
	 * @return String nuevo token
	 * */
	public String alta() {
		String token = "";
		do {
			token = getToken(LENGTH_TOKEN);
		} while (tokens.containsKey(token) || token.equals(tokenAdmin)); // genera un token que no exista en el servidor
		tokens.put(token, CONTENIDOS_POR_TOKEN);
		return token;
	}

	/**
	 * Metodo que elimina un token en el servidor si el token no existe
	 * devolvera una excepcion.
	 * @param token
	 *            token a eliminar
	 * @throws UnexistingTokenException
	 *             si no existe el token a eliminar
	 * */
	public void baja(final String token) throws UnexistingTokenException {
		if (!tokens.containsKey(token)) {
			throw new UnexistingTokenException(token); //en caso de que no exista el token, lanza una excepción
		}
		tokens.remove(token);
	}

	/**
	 * Metodo que agrega contenido a el servidor si el token no
	 * es el token de administrador devolvera una excepcion.
	 * @param content
	 *            contenido a agregar en el servidor
	 * @param token
	 *            token usado para agregar contenido
	 * @throws InsufficientPermissionsException
	 *             si el token pasado carece de permisos
	 */
	public void agregar(final Contenido content, final String token)
			throws InsufficientPermissionsException {
		if (token.equals(tokenAdmin)) {
			this.contentList.add(content);
		} else {
			throw new InsufficientPermissionsException(token, "agregar",
					content); // solo permite agregar contenido si el token coincide con tokenAdmin, sino lanza excepción
		}
	}

	/**
	 * Metodo que elimina contenido del servidor si el token
	 * no tiene permisos lanza un excepcion, si el
	 * contenido no existe en el servidor lanza una
	 * excepcion.
	 * @param content
	 *            contenido a eliminar del servidor
	 * @param token
	 *            token usado para eliminar contenido
	 * @throws InsufficientPermissionsException
	 *             si el token pasado carece de permisos
	 * @throws ContentNotFoundException
	 *             si el contenido no existe
	 * */
	public void eliminar(final Contenido content, final String token)
			throws InsufficientPermissionsException, ContentNotFoundException {
		if (token.equals(tokenAdmin)) {
			if (this.contentList.contains(content)) {
				this.contentList.remove(content);
			} else {
				throw new ContentNotFoundException(content);
				}
		} else {
			throw new InsufficientPermissionsException(token, "eliminar",
					content);
			}
	}

	/**
	 * Metodo que devuelve una lista de contenido cuyo contenido
	 * contenga la subChain pasada, si el token pasado
	 * para buscar no existe lanza una excepcion.
	 * @param subChain
	 *            String usado para buscar
	 * @param token
	 *            token usado para buscar
	 * @throws UnexistingTokenException
	 *             si el token no existe
	 * @return List<Contenido> con los contenidos de la busqueda.
	 * */
	public List<Contenido> buscar(final String subChain, final String token)
			throws UnexistingTokenException {
		List<Contenido> resultado = new ArrayList<Contenido>();
		List<Contenido> tmp = new ArrayList<Contenido>();
		List<Contenido> tmp2 = new ArrayList<Contenido>();
		for (Contenido c : this.contentList) {
			tmp = c.buscar(subChain);
			if (tmp.size() != 0) {
				tmp2.addAll(tmp);
			}
		}
		if (this.getTokens().containsKey(token)
				&& (this.getTokens().get(token).intValue() > 0)) {
			int restantes = this.getTokens().get(token).intValue();
			tmp.clear();
			for (int j = 0; j < tmp2.size(); j++) {
				tmp.add(tmp2.get(j));
				restantes--;
				if (restantes == 0) {
					break;
				}
			}
			resultado = tmp;
			if (restantes > 0) { //actualiza el token
				this.baja(token);
				this.getTokens().put(token, restantes);
			} else { //da de baja el token si ya consumió los 10 contenidos
				this.baja(token);
			}
		} else {
			if (tmp2.size() != 0) {
				resultado.add(new Anuncio());
				// meter publi
				int count = 0;
				for (int i = 0; i < tmp2.size(); i++) {
					if (count == CONTENIDO_ENTRE_ANUNCIOS) {
						resultado.add(new Anuncio());
						count = 0;
					}
					resultado.add(tmp2.get(i));
					count++;
				}
			}
		}
		return resultado;
	}

	/**
	 * Crea un token aleatorio.
	 * @param length longitud del token a generar
	 * @return String con el token
	 */
	private static String getToken(final int length) { //crea un token aleatorio
		StringBuilder token = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			token.append(CHARS.charAt(RANDOM.nextInt(CHARS.length())));
		}
		return token.toString();
	}

	/**
	 * Getter de Tokens.
	 * @return Map con Tokens
	 */
	public Map<String, Integer> getTokens() {
		return tokens;
	}

	/**
	 * Setter de tokens.
	 * @param tokens a setear
	 */
	public void setTokens(final Map<String, Integer> tokens) {
		this.tokens = tokens;
	}

	/**
	 * Getter de contentList.
	 * @return contentList
	 */
	public List<Contenido> getContentList() {
		return contentList;
	}

	/**
	 * Setter de contentList.
	 * @param contentList contentList a setear
	 */
	public void setContentList(final List<Contenido> contentList) {
		this.contentList = contentList;
	}

}
