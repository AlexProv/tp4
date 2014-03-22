import com.odi.ObjectStore;
import com.odi.Transaction;


public class GestionCarte {
private Carte carte;
	
	public GestionCarte(Carte carte) {
		this.carte = carte;
	}
	
	public void ajouter(String idJoueur, int idCarte) throws InventaireException {
		Transaction tr = Transaction.begin(ObjectStore.UPDATE);
		try {
			/* Vérifie si le livre existe déja */
			if (carte.existe(idCarte))
				throw new InventaireException("Carte existe deja: " + idCarte);
			TupleCarte tCarte = new TupleCarte(idJoueur, idCarte);
			carte.ajouter(tCarte);
			tr.commit(ObjectStore.RETAIN_HOLLOW);
		} catch (InventaireException e) {
			tr.abort(ObjectStore.RETAIN_HOLLOW);
			throw e;
		}
	}

	public void afficherCartesJoueur(String idJoueur) {
		Transaction tr = Transaction.begin(ObjectStore.READONLY);
		carte.afficher(idJoueur);
		tr.commit(ObjectStore.RETAIN_HOLLOW);
		
	}
	
	public void effacerCartes(String idJoueur) throws InventaireException{
		Transaction tr = Transaction.begin(ObjectStore.UPDATE);
		try {

			/* Suppression du joueur. */
			boolean effacer = carte.effacerCarte(idJoueur);
			if (!effacer)
				throw new InventaireException("Carte du joueur " + idJoueur + " inexistant");
			else
				tr.commit(ObjectStore.RETAIN_HOLLOW);
		} catch (InventaireException e) {
			tr.abort(ObjectStore.RETAIN_HOLLOW);
			throw e;
		}
	}
}
