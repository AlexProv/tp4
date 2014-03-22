import java.sql.SQLException;

public class GestionInventaire {
	public Connexion cx;
	public GestionJoueur gestionJoueur;
	public Joueur joueur;
	public GestionCarte gestionCarte;
	public Carte carte;

	/**
	 * Ouvre une connexion avec la BD relationnelle et alloue les gestionnaires
	 * de transactions et de tables.
	 * 
	 * <pre>
	 * 
	 * @param serveur SQL
	 * @param bd nom de la bade de données
	 * @param user user id pour établir une connexion avec le serveur SQL
	 * @param password mot de passe pour le user id
	 * </pre>
	 */
	public GestionInventaire(String bd) throws Exception {
		// allocation des objets pour le traitement des transactions
		cx = new Connexion(bd);
		joueur = new Joueur(cx);
		gestionJoueur = new GestionJoueur(joueur);
		carte = new Carte(cx);
		gestionCarte = new GestionCarte(carte);
	}

	public void fermer() throws SQLException {
		// fermeture de la connexion
		cx.fermer();
	}
}
