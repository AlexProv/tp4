import com.odi.ObjectStore;
import com.odi.Transaction;
/**
 * La classe GestionCarte permet de gerer les cartes
 * 
 * @author Mathieu Lavoie, Alex Provencher et Vincent Gagnon
 *
 */

public class GestionCarte {
private Carte carte;
	
	/**
	 * Le constructeur de GestionCarte qui recoit une Carte
	 * @param carte
	 */
	
	public GestionCarte(Carte carte) {
		this.carte = carte;
	}
	
	/**
	 * Ajoute une carte en ouvrant une Transaction de BDOO
	 * 
	 * @param idJoueur L'Id du joueur
	 * @throws InventaireException
	 */
	public void ajouter(String idJoueur) throws InventaireException {
		Transaction tr = Transaction.begin(ObjectStore.UPDATE);
		try {
			int maxCartes = carte.maxCarte();
			/* Vérifie si la carte existe déja */
			if (carte.existe(maxCartes + 1))
				throw new InventaireException("Carte existe deja: " + maxCartes + 1);
			TupleCarte tCarte = new TupleCarte(idJoueur, maxCartes + 1);
			carte.ajouter(tCarte);
			tr.commit(ObjectStore.RETAIN_HOLLOW);
		} catch (InventaireException e) {
			tr.abort(ObjectStore.RETAIN_HOLLOW);
			throw e;
		}
	}

	/**
	 * Afficher les cartes d'un joueur
	 * 
	 * @param idJoueur l'Id du joueur
	 */
	public void afficherCartesJoueur(String idJoueur) {
		Transaction tr = Transaction.begin(ObjectStore.READONLY);
		carte.afficher(idJoueur);
		tr.commit(ObjectStore.RETAIN_HOLLOW);
		
	}
	
	/**
	 * Efface les cartes d'un joueur
	 * 
	 * @param idJoueur l'Id du joueur
	 * @throws InventaireException
	 */
	public void effacerCartes(String idJoueur) throws InventaireException{
		Transaction tr = Transaction.begin(ObjectStore.UPDATE);
		tr.commit(ObjectStore.RETAIN_HOLLOW);
	}
}
