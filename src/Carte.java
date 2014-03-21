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
	 * @author Mathieu Lavoie, Vincent Gagnon et Alex Provencher
	 *
	 */
	
	private Map<String, TupleCarte> allCartes;
	private int nbCartes;
	
	/**
	 * Initialize les valeur pour partire un jeu de carte
	 */
	@SuppressWarnings("unchecked")
	public Carte(Connexion cx)
	{
		/* Lire ou creer la collection des livres */
		Transaction tr = Transaction.begin(ObjectStore.UPDATE);
		try {
			try {
				allCartes = (Map<String, TupleCarte>) cx.getDatabase()
						.getRoot("allCartes");
			} catch (DatabaseRootNotFoundException e) {
				/* Creation de la racine */
				cx.getDatabase().createRoot("allCartes",
						allCartes = new OSHashMap<String, TupleCarte>(10));
			}
			/* Fin de la transaction avec retention des objets creux */
			tr.commit(ObjectStore.RETAIN_HOLLOW);
		} catch (Exception e) {
			tr.abort(ObjectStore.RETAIN_HOLLOW);
		}
	}
	
	/**
	 * permet d'ajouter des cartes au jeuDeCartes (par appelle a la console pour les parametres de la cartes)
	 * @param numero de la carte actuel
	 */
	public void ajouter(int k)
	{
		if(nbCartes < 20){
			TupleCarte c = Interaction.IOcarte(k);
			listCartes.add(c);
			nbCartes++;
		}
	}
	/**
	 * permet d'ajouter des cartes au jeuDeCartes
	 * @param Titre de la cartes
	 * @param nom de l'equipe de la carte
	 * @param annee de la carte
	 *//*
	public void ajouter(String titre, String equipe, int annee){
		TupleCarte c = new TupleCarte(titre, equipe, annee);
		listCartes.add(c);
		
		nbCartes++;
	}
	
	*//**
	 * permet de suprimer les carte demande une confirmation 
	 * @param nom du joueur ou un suprime ces cartes
	 *//*
	public boolean deleteCarte(String nom)
	{
		afficherTout();
		if(Interaction.confirmationDelete()){
			listCartes.clear();
			Interaction.deleteConfirme(nom);
			return true;
		}
		else{
			return false;
		}
	}
	
	*//**
	 * constuit une string avec tout les joueur et leur cartes. 
	 * @return retourne tout les joueurs et toutes leurs cartes.
	 *//*
	public String afficherTout() {
		String s = "Le joueur a "+ nbCartes +" cartes enregistre\n";
		for(TupleCarte c : listCartes)
		{
			s += "Carte " + (listCartes.indexOf(c)+1)   + " : \n";
			s += c.afficher();
		}
		return s;
	}*/
	
}
