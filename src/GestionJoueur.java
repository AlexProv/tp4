import com.odi.ObjectStore;
import com.odi.Transaction;


public class GestionJoueur {

	private Joueur joueur;
	
	public GestionJoueur(Joueur joueur) {
		this.joueur = joueur;
	}
	
	public void ajouter(String idJoueur) throws InventaireException {
		Transaction tr = Transaction.begin(ObjectStore.UPDATE);
		try {
			/* Vérifie si le livre existe déja */
			if (joueur.existe(idJoueur))
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
