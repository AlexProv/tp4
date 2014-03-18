import com.odi.*;

/**
 * Connexion.java
 * 
 * Marc Frappier Université de Sherbrooke
 * 
 * Ouvre et ferme une connexion pour la gestion de la bibliotheque
 */
public class Connexion {

	private Database db;

	private Session session;

	/**
	 * ouverture d'une connexion
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

	/**
	 * retourne la dataBase de la connexion
	 */
	public Database getDatabase() {
		return db;
	}

	/**
	 * fermeture d'une connexion
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
