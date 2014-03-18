/**
 * L'exception GestionBibliothequeException est levée lorsqu'une transaction est
 * inadéquate. Par exemple -- livre inexistant
 */

public final class InventaireException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InventaireException(String message) {
		super(message);
	}
}
