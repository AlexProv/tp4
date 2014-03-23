/**
 * 
 */

/**
 * L'exception InventaireException est levée lorsqu'une transaction est
 * inadéquate. Par exemple une carte inexistante ou une joueur existant deja
 * a la creation
 * 
 * @author Mathieu Lavoie, Alex Provencher et Vincent Gagnon
 *
 */

public final class InventaireException extends Exception {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Prend le message d'erreur et l'envoie a Exception
	 * 
	 * @param message
	 */
	public InventaireException(String message) {
		super(message);
	}
}
