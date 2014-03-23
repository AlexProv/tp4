import java.sql.SQLException;

/**
 * La classe GestionInventaire permet de controler tous les options du programme
 * 
 * @author Mathieu Lavoie, Alex Provencher et Vincent Gagnon
 *
 */

public class GestionInventaire {
	public Connexion cx;
	public GestionJoueur gestionJoueur;
	public Joueur joueur;
	public GestionCarte gestionCarte;
	public Carte carte;

	/**
	 * Le constructeur de GestionInventaire sert creer les classes pour
	 * les reutiliser dans tous les operations
	 * 
	 * @param bd le fichier de la BD
	 * @throws Exception
	 */
	public GestionInventaire(String bd) throws Exception {
		// allocation des objets pour le traitement des transactions
		cx = new Connexion(bd);
		joueur = new Joueur(cx);
		gestionJoueur = new GestionJoueur(joueur);
		carte = new Carte(cx);
		gestionCarte = new GestionCarte(carte);
	}

	/**
	 * Fermer la connexion
	 * 
	 * @throws SQLException
	 */
	public void fermer() throws SQLException {
		// fermeture de la connexion
		cx.fermer();
	}
}
