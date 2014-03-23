import com.odi.*;

/**
 * 
 * La classe Connexion permet de se connecter a la BDOO
 * 
 * @author Mathieu Lavoie, Alex Provencher et Vincent Gagnon
 *
 */
public class Connexion {

	private Database db;

	private Session session;

	/**
	 * Constructeur de la connexion pour debuter la session et creer la BD s'il n'y a rien
	 * de fait
	 * 
	 * @param dbName nom du fichier de la BDOO
	 * @throws InventaireException
	 */
	public Connexion(String dbName) throws InventaireException {

		try {
			/* Creation de la session et ajout de ce thread a la session. */
			session = Session.create(null, null);
			session.join();

			/* Ouverture de la BD ou creation si necessaire */
			try {
				db = Database.open(dbName, ObjectStore.UPDATE);
			} catch (DatabaseNotFoundException e) {
				db = Database.create(dbName, ObjectStore.ALL_READ
						| ObjectStore.ALL_WRITE);
			}
		} catch (Exception e) {
			System.out.println(e);
			throw new InventaireException("Impossible d'ouvrir la connexion");
		}
	}
	
	public Database getDatabase() {
		return db;
	}

	/**
	 * Ferme la connexion
	 * 
	 */
	public void fermer() {
		try {
			db.close();
		} catch (Exception e) {
		} finally {
			session.terminate();
		}
	}
}
