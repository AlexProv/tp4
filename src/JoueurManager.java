import java.util.ArrayList;
import java.util.List;

public class JoueurManager {
	//private SortedSet<Joueur> ListJoueur;
	private List<Joueur> ListJoueur;
	/**
	 * Initialize le joueur manager
	 */
	JoueurManager()
	{
		ListJoueur = new ArrayList<Joueur>();
	}
	
	/**
	 * ajoute un joueur (appelle manuel)
	 * @param id du joueur ajouter
	 */
	public void ajouterJoueur(String id)
	{
		Joueur j = new Joueur(id);
		ListJoueur.add(j);
	}
	
	/**
	 * ajoute un joueur (construction suite a lecutre de fichier)
	 * @param data information sur le joueur 
	 */
	public void ajouterJoueur(String[] data){
		Joueur j = new Joueur(data);
		ListJoueur.add(j);
	}
	
	/**
	 * Retire un joueur de JoueurManager
	 * @param j Objet Joueur a retirer
	 * @return un boolean si l'operation a reusit
	 */
	public boolean retirerJoueur(Joueur j)
	{
		boolean confirm = j.getJeuDeCarte().deleteCarte(j.getPrenom() + " " + j.getNom());
		if(confirm)
			return ListJoueur.remove(j);
		else
			return false;
	}
	
	/**
	 * modifie un joueur particulier  
	 * @param id du joueur a modifier
	 */
	public void modifierJoueur(String id){
		
		ListJoueur.remove(find(id));
		Joueur j = new Joueur(id);
		ListJoueur.add(j);
		
	}
	
	
	/**
	 * trouve la premiere occurance du joueur dans la structure de donnee de JoueurManager.
	 * @param id du joueur a trouver.
	 * @return joueur trouver ou null si aucune occurance
	 */
	public Joueur find(String id)
	{
		for(Joueur j : ListJoueur)
		{
			if(id.equals(j.getClefId()))
				return j;
		}
		return null;
	}
	
	/**
	 * construit une string avec tout les joueurs dans la structure de donnee de JoueurManager
	 * @return String avec tout les joueurs
	 */
	public String afficherTout()
	{
		String total = "";
		for(Joueur j : ListJoueur)
		{
			total += "\n" + j.afficherJoueur();
		}
		return total;
	}
	
	/**
	 * construit une string avec l'inforamtion sur un joueur en particulier
	 * @param id du joueur dont le quel on droit trouver l'information
	 * @return string avec l'information du joueur en question
	 */
	public String afficherJoueur(String id)
	{
		Joueur j = find(id);
		if(j != null)
			return j.afficherJoueur();
		else
			return null;
	}
	
	/**
	 * construit une string pour affichier l'information sur tout les joueurs de la structure de donnee de joueur Manager
	 * specialiser pour le fichier texte
	 * @return String avec toute les informations des joueurs. 
	 */
	public String afficherInfoFichierTexte(){
		String fichierComplet = "";
		for (Joueur j: ListJoueur) {
			fichierComplet += j.afficherFichierTexte();
		}
		return fichierComplet;
	}
	
	
	
}
