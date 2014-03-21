
/**
 * La classe Carte 
 * 
 * @author Mathieu Lavoie, Vincent Gagnon et Alex Provencher
 *
 */
public class TupleCarte{

	private String titre, equipe, idJoueur;
	private int annee;
	
	/**
	 * Le constructeur Carte permet de donner les informations sur une carte
	 * @param titre Le titre de la carte
	 * @param equipe Le nom de l'equipe du joueur de la carte
	 * @param annee L'annee de parution
	 */
	
	public TupleCarte(String titre, String equipe, int annee, String idJoueur)
	{
		this.titre = titre;
		this.equipe = equipe;
		this.idJoueur = idJoueur;
		this.annee = annee;
	
	}
	
	public String getTitre() {
		return titre;
	}
	public String getEquipe() {
		return equipe;
	}
	public int getAnnee() {
		return annee;
	}
	/**
	 * La methode afficher permet d'afficher les informations sur une carte comme dans un rapport
	 * @return Les donnees de la carte
	 */
	public String afficher() {
		String s = "\t Titre : " + titre + "\n";
		s += "\t Equipe : " + equipe + "\n";
		s += "\t Annee de parution : " + annee + "\n";
		return s;
	}

}
