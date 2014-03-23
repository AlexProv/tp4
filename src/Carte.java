import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.odi.DatabaseRootNotFoundException;
import com.odi.ObjectStore;
import com.odi.Transaction;
import com.odi.util.OSHashMap;


public class Carte {

	/**
	 * La classe Carte 
	 * 
	 * @author Mathieu Lavoie, Alex Provencher et Vincent Gagnon
	 *
	 */
	
	private Map<Integer, TupleCarte> allCartes;
	private int nbCartes;
	
	/**
	 * Initialise la connexion pour les cartes en allant chercher les donnees
	 * du fichier ODB. S'il n'y a pas de donnees, creer le fichier ODB
	 */
	@SuppressWarnings("unchecked")
	public Carte(Connexion cx)
	{
		/* Lire ou creer la collection des livres */
		Transaction tr = Transaction.begin(ObjectStore.UPDATE);
		try {
			try {
				allCartes = (Map<Integer, TupleCarte>) cx.getDatabase()
						.getRoot("allCartes");
			} catch (DatabaseRootNotFoundException e) {
				/* Creation de la racine */
				cx.getDatabase().createRoot("allCartes",
						allCartes = new OSHashMap<Integer, TupleCarte>(10));
			}
			/* Fin de la transaction avec retention des objets creux */
			tr.commit(ObjectStore.RETAIN_HOLLOW);
		} catch (Exception e) {
			tr.abort(ObjectStore.RETAIN_HOLLOW);
		}
	}
	
	/**
	 * permet d'ajouter un carte a la map de Cartes
	 * @param tupleCarte
	 */
	public void ajouter(TupleCarte tupleCarte)
	{
		if(nbCartes < 20){
			allCartes.put(tupleCarte.getIdCarte(), tupleCarte);
			nbCartes++;
		}
	}
	
	/**
	 * Verifie si la carte existe
	 * @param idJoueur la 
	 * @return s'il existe
	 */
	public boolean existe(int idJoueur) {
		return allCartes.get(idJoueur) != null;
	}
	
	/**
	 * permet de supprimer les cartes associées au joueur
	 * @param id id du joueur
	 */
	
	public boolean effacerCarte(String id)
	{
		List<Integer> listCartes = new ArrayList<Integer>();
		for (Map.Entry<Integer, TupleCarte> entry : allCartes.entrySet())
		{
		    if(entry.getValue().getIdJoueur().equals(id)){
		    	int idCarte = entry.getKey();
		    	listCartes.add(idCarte);
		    }
		}
		if(listCartes.isEmpty())
			return false;
		else{
			for (Integer idCarte : listCartes) {
				allCartes.remove(idCarte);
			}
			return true;
		}
	}

	/**
	 * Afficher en console les donnees des cartes par l'ID du joueur
	 * 
	 * @param idJoueur l'ID du joueur
	 */
	public void afficher(String idJoueur) {
		for (Map.Entry<Integer, TupleCarte> entry : allCartes.entrySet())
		{
		    if(entry.getValue().getIdJoueur().equals(idJoueur)){
		    	System.out.println("Numero de carte : " + entry.getValue().getIdCarte());
		    	System.out.println("Nom de carte : " + entry.getValue().getTitre());
		    	System.out.println("Equipe de carte : " + entry.getValue().getEquipe());
		    	System.out.println("Annee de carte : " + entry.getValue().getAnnee());
		    }
		}
	}

	/**
	 * Va chercher l'ID de la carte ayant le maximum
	 * 
	 * @return ID maximum de la liste de cartes
	 */
	public int maxCarte() {
		return allCartes.size();
	}
	
}
