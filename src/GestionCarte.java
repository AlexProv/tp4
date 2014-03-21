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
			/* Vérifie si le livre existe déja */
			if (carte.existe(idJoueur))
				throw new InventaireException("Joueur existe deja: " + idJoueur);

			/* Ajout du livre dans la table des livres */
			TupleJoueur t = new TupleJoueur(idJoueur);
			joueur.ajouter(t);
			tr.commit(ObjectStore.RETAIN_HOLLOW);
		} catch (InventaireException e) {
			tr.abort(ObjectStore.RETAIN_HOLLOW);
			throw e;
		}
	}
}
