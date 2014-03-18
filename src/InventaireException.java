/**
 * L'exception GestionBibliothequeException est lev�e lorsqu'une transaction est
 * inad�quate. Par exemple -- livre inexistant
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
