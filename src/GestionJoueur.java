import java.util.List;

import com.odi.ObjectStore;
import com.odi.Transaction;

/**
 * La classe GestionJoueur sert a gerer les transactions des joueurs
 * 
 * @author Mathieu Lavoie, Alex Provencher et Vincent Gagnon
 *
 */

public class GestionJoueur {

	private Joueur joueur;
	
	/**
	 * Le constructeur GestionJoueur permet d'avoir l'objet Joueur
	 * 
	 * @param joueur
	 */
	
	public GestionJoueur(Joueur joueur) {
		this.joueur = joueur;
	}
	
	/**
	 * 
	 * La methode ajouter permet de creer la transaction qui servira a ajouter
	 * dans une map
	 * 
	 * @param idJoueur
	 * @return le nombre de cartes
	 * @throws InventaireException
	 */
	public int ajouter(String idJoueur) throws InventaireException {
		int nbCartes = -1;
		Transaction tr = Transaction.begin(ObjectStore.UPDATE);
		try {
			/* Vérifie si le joueur existe déja */
			if (joueur.existe(idJoueur))
				throw new InventaireException("Joueur existe deja: " + idJoueur);

			/* Ajout du joueur dans la table des joueurs */
			TupleJoueur t = new TupleJoueur(idJoueur);
			nbCartes = t.getNbCarte();
			joueur.ajouter(t);
			tr.commit(ObjectStore.RETAIN_HOLLOW);
		} catch (InventaireException e) {
			tr.abort(ObjectStore.RETAIN_HOLLOW);
			throw e;
		}
		return nbCartes;
	}
	
	
	/**
	 * Creer une transaction pour afficher les joueurs
	 * 
	 * @param idJoueur
	 */
	public void afficherJoueur(String idJoueur){
		Transaction tr = Transaction.begin(ObjectStore.READONLY);
		joueur.afficher(idJoueur);
		tr.commit(ObjectStore.RETAIN_HOLLOW);
	}
	
	/**
	 * Creer la transaction pour effacer un joueur
	 * 
	 * @param idJoueur
	 * @throws InventaireException
	 */
	public void effacer(String idJoueur) throws InventaireException{
		Transaction tr = Transaction.begin(ObjectStore.UPDATE);
		try {
			boolean tupleJoueur = joueur.existe(idJoueur);
			if (!tupleJoueur)
				throw new InventaireException("Joueur inexistant: " + idJoueur);

			/* Suppression du joueur. */
			int nb = joueur.effacer(idJoueur);
			if (nb == 0)
				throw new InventaireException("Joueur " + idJoueur + " inexistant");
			else
				tr.commit(ObjectStore.RETAIN_HOLLOW);
		} catch (InventaireException e) {
			tr.abort(ObjectStore.RETAIN_HOLLOW);
			throw e;
		}
	}

	/**
	 * Une liste de tous les ID
	 * 
	 * @return La liste des ID des joueurs
	 */
	public List<String> listId() {
		Transaction tr = Transaction.begin(ObjectStore.READONLY);
		List<String> list = joueur.listId();
		tr.commit(ObjectStore.RETAIN_HOLLOW);
		return list;
	}

}
