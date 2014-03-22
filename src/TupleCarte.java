
/**
 * La classe Carte 
 * 
 * @author Mathieu Lavoie, Vincent Gagnon et Alex Provencher
 *
 */
public class TupleCarte{
	private String titre, equipe, idJoueur;
	private int annee, idCarte;
	
	/**
	 * Le constructeur Carte permet de donner les informations sur une carte
	 * @param titre Le titre de la carte
	 * @param equipe Le nom de l'equipe du joueur de la carte
	 * @param annee L'annee de parution
	 */
	
	public TupleCarte(String idJoueur, int idCarte)
	{
		this.idJoueur = idJoueur;
		this.idCarte = idCarte;
		this.equipe = Interaction.IOequipeCarte(idCarte);
		this.titre = Interaction.IOtitreCarte(idCarte);
		this.annee = Interaction.IOanneeCarte(idCarte);
	
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

	public String getIdJoueur() {
		return idJoueur;
	}

	public int getIdCarte() {
		return idCarte;
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
