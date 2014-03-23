import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.odi.DatabaseRootNotFoundException;
import com.odi.ObjectStore;
import com.odi.Transaction;
import com.odi.util.OSHashMap;

public class Joueur {
	// private SortedSet<Joueur> ListJoueur;
	private Map<String, TupleJoueur> allJoueurs;

	/**
	 * Initialize le joueur manager
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
	 * Verifie si un livre existe
	 */
	public boolean existe(String idJoueur) {
		return allJoueurs.get(idJoueur) != null;
	}

	/**
	 * ajoute un joueur (appelle manuel)
	 * 
	 * @param id
	 *            du joueur ajouter
	 */
	public void ajouter(TupleJoueur j) {
		allJoueurs.put(j.getClefId(), j);
	}
	
	public void afficher(String idJoueur){
		TupleJoueur tj = allJoueurs.get(idJoueur);
		System.out.println("Clef joueur :" + tj.getClefId());
		System.out.println("Nom joueur : " + tj.getPrenom() + " " + tj.getNom());
		System.out.println("Nombre de cartes : " + tj.getNbCarte());
	}

	public int effacer(String idJoueur) {
		Object o = allJoueurs.remove(idJoueur);
	    if (o == null)
	        return 0;
	    else
	        return 1;
	}

	public List<String> listId() {
		List<String> list = new ArrayList<String>();
		for (Map.Entry<String, TupleJoueur> entry : allJoueurs.entrySet())
		{
			list.add(entry.getKey());
		}
		return list;
	}

	/**
	 * ajoute un joueur (construction suite a lecutre de fichier)
	 * 
	 * @param data
	 *            information sur le joueur
	 */
	/*
	 * public void ajouterJoueur(String[] data){ TupleJoueur j = new
	 * TupleJoueur(data); ListJoueur.add(j); }
	 *//**
	 * Retire un joueur de JoueurManager
	 * 
	 * @param j
	 *            Objet Joueur a retirer
	 * @return un boolean si l'operation a reusit
	 */
	/*
	 * public boolean retirerJoueur(TupleJoueur j) { boolean confirm =
	 * j.getJeuDeCarte().deleteCarte(j.getPrenom() + " " + j.getNom());
	 * if(confirm) return ListJoueur.remove(j); else return false; }
	 *//**
	 * modifie un joueur particulier
	 * 
	 * @param id
	 *            du joueur a modifier
	 */
	/*
	 * public void modifierJoueur(String id){
	 * 
	 * ListJoueur.remove(find(id)); TupleJoueur j = new TupleJoueur(id);
	 * ListJoueur.add(j);
	 * 
	 * }
	 *//**
	 * trouve la premiere occurance du joueur dans la structure de donnee de
	 * JoueurManager.
	 * 
	 * @param id
	 *            du joueur a trouver.
	 * @return joueur trouver ou null si aucune occurance
	 */
	/*
	 * public TupleJoueur find(String id) { for(TupleJoueur j : ListJoueur) {
	 * if(id.equals(j.getClefId())) return j; } return null; }
	 *//**
	 * construit une string avec tout les joueurs dans la structure de donnee
	 * de JoueurManager
	 * 
	 * @return String avec tout les joueurs
	 */
	/*
	 * public String afficherTout() { String total = ""; for(TupleJoueur j :
	 * ListJoueur) { total += "\n" + j.afficherJoueur(); } return total; }
	 *//**
	 * construit une string avec l'inforamtion sur un joueur en particulier
	 * 
	 * @param id
	 *            du joueur dont le quel on droit trouver l'information
	 * @return string avec l'information du joueur en question
	 */
	/*
	 * public String afficherJoueur(String id) { TupleJoueur j = find(id); if(j
	 * != null) return j.afficherJoueur(); else return null; }
	 *//**
	 * construit une string pour affichier l'information sur tout les joueurs
	 * de la structure de donnee de joueur Manager specialiser pour le fichier
	 * texte
	 * 
	 * @return String avec toute les informations des joueurs.
	 */
	/*
	 * public String afficherInfoFichierTexte(){ String fichierComplet = ""; for
	 * (TupleJoueur j: ListJoueur) { fichierComplet += j.afficherFichierTexte();
	 * } return fichierComplet; }
	 */

}
