import com.odi.ObjectStore;
import com.odi.Transaction;


public class GestionCarte {
private Carte carte;
	
	public GestionCarte(Carte carte) {
		this.carte = carte;
	}
	
	public void ajouter(String idJoueur) throws InventaireException {
		Transaction tr = Transaction.begin(ObjectStore.UPDATE);
		try {
			int maxCartes = carte.maxCarte();
			/* Vérifie si le livre existe déja */
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
