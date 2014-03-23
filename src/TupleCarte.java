
/**
 * La classe TupleCarte 
 * 
 * @author Mathieu Lavoie, Alex Provencher et Vincent Gagnon
 *
 */
public class TupleCarte{
	private String titre, equipe, idJoueur;
	private int annee, idCarte;
	
	/**
	 * Le constructeur TupleCarte a toutes les informations sur une carte
	 * 
	 * @param idJoueur
	 * @param idCarte
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

}
