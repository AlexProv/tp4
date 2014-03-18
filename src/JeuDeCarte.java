import java.util.ArrayList;
import java.util.List;


public class JeuDeCarte {

	/**
	 * La classe Carte 
	 * 
	 * @author Mathieu Lavoie, Vincent Gagnon et Alex Provencher
	 *
	 */
	
	private List<Carte> listCartes;
	private int nbCartes;
	
	/**
	 * Initialize les valeur pour partire un jeu de carte
	 */
	public JeuDeCarte()
	{
		nbCartes=0;
		listCartes = new ArrayList<Carte>();
	}
	
	/**
	 * permet d'ajouter des cartes au jeuDeCartes (par appelle a la console pour les parametres de la cartes)
	 * @param numero de la carte actuel
	 */
	public void ajouter(int k)
	{
		if(nbCartes < 20){
			Carte c = Interaction.IOcarte(k);
			listCartes.add(c);
			nbCartes++;
		}
	}
	/**
	 * permet d'ajouter des cartes au jeuDeCartes
	 * @param Titre de la cartes
	 * @param nom de l'equipe de la carte
	 * @param annee de la carte
	 */
	public void ajouter(String titre, String equipe, int annee){
		Carte c = new Carte(titre, equipe, annee);
		listCartes.add(c);
		
		nbCartes++;
	}
	
	/**
	 * permet de suprimer les carte demande une confirmation 
	 * @param nom du joueur ou un suprime ces cartes
	 */
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
	
	/**
	 * constuit une string avec tout les joueur et leur cartes. 
	 * @return retourne tout les joueurs et toutes leurs cartes.
	 */
	public String afficherTout() {
		String s = "Le joueur a "+ nbCartes +" cartes enregistre\n";
		for(Carte c : listCartes)
		{
			s += "Carte " + (listCartes.indexOf(c)+1)   + " : \n";
			s += c.afficher();
		}
		return s;
	}
	/**
	 * constuit une string avec tout les joueur et leur cartes. avec un type csv
	 * @return retourne tout les joueurs et toutes leurs cartes.
	 */
	public String afficherFichierTexte(){
		String s = "";
		for(Carte c : listCartes){
			s += c.afficherFichierTexte();
		}
		return s;
	}
	
}
