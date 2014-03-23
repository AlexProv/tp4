import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.odi.DatabaseRootNotFoundException;
import com.odi.ObjectStore;
import com.odi.Transaction;
import com.odi.util.OSHashMap;

/**
 * La classe Joueur est la classe qui permet de faire les actions sur TupleJoueur
 * 
 * @author Mathieu Lavoie, Alex Provencher et Vincent Gagnon
 *
 */

public class Joueur {
	
	private Map<String, TupleJoueur> allJoueurs;

	/**
	 * Le constructeur de Joueur permet de demarrer une transaction sur la base de
	 * donnees et prend toutes les donnees de ODB
	 * 
	 * @param cx La connexion au fichier ODB
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public Joueur(Connexion cx) throws Exception {

		/* Lire ou creer la collection des livres */
		Transaction tr = Transaction.begin(ObjectStore.UPDATE);
		try {
			try {
				allJoueurs = (Map<String, TupleJoueur>) cx.getDatabase()
						.getRoot("allJoueurs");
			} catch (DatabaseRootNotFoundException e) {
				/* Creation de la racine */
				cx.getDatabase().createRoot("allJoueurs",
						allJoueurs = new OSHashMap<String, TupleJoueur>(10));
			}
			/* Fin de la transaction avec retention des objets creux */
			tr.commit(ObjectStore.RETAIN_HOLLOW);
		} catch (Exception e) {
			tr.abort(ObjectStore.RETAIN_HOLLOW);
			throw e;
		}
	}

	/**
	 * Verifie si un joueur existe
	 * 
	 * @param idJoueur
	 * @return s'il existe
	 */
	public boolean existe(String idJoueur) {
		return allJoueurs.get(idJoueur) != null;
	}

	/**
	 * Ajoute un joueur
	 *
	 * @param j un TupleJoueur
	 */
	
	public void ajouter(TupleJoueur j) {
		allJoueurs.put(j.getClefId(), j);
	}
	
	/**
	 * Afficher un joueur
	 * 
	 * @param idJoueur
	 */
	public void afficher(String idJoueur){
		TupleJoueur tj = allJoueurs.get(idJoueur);
		System.out.println("Clef joueur :" + tj.getClefId());
		System.out.println("Nom joueur : " + tj.getPrenom() + " " + tj.getNom());
		System.out.println("Nombre de cartes : " + tj.getNbCarte());
	}

	/**
	 * 
	 * Efface un joueur
	 * 
	 * @param idJoueur
	 * @return 1 si c'est effacer ou 0 si ca n'a pas fonctionne
	 */
	public int effacer(String idJoueur) {
		Object o = allJoueurs.remove(idJoueur);
	    if (o == null)
	        return 0;
	    else
	        return 1;
	}

	/**
	 * Va chercher la liste des ID des joueurs
	 * 
	 * @return Une liste de ceci
	 */
	public List<String> listId() {
		List<String> list = new ArrayList<String>();
		for (Map.Entry<String, TupleJoueur> entry : allJoueurs.entrySet())
		{
			list.add(entry.getKey());
		}
		return list;
	}

}
