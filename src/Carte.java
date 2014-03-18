
/**
 * La classe Carte 
 * 
 * @author Mathieu Lavoie, Vincent Gagnon et Alex Provencher
 *
 */
public class Carte{

	private String titre, equipe;
	private int annee;
	
	/**
	 * Le constructeur Carte permet de donner les informations sur une carte
	 * @param titre Le titre de la carte
	 * @param equipe Le nom de l'equipe du joueur de la carte
	 * @param annee L'annee de parution
	 */
	
	public Carte(String titre, String equipe, int annee)
	{
		setEquipe(equipe);
		setTitre(titre);
		setAnnee(annee);
	}
	
	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getEquipe() {
		return equipe;
	}

	public void setEquipe(String equipe) {
		this.equipe = equipe;
	}

	public int getAnnee() {
		return annee;
	}

	public void setAnnee(int annee) {
		this.annee = annee;
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
	
	/**
	 * La methode afficherFichierTexte permet d'obtenir les informations sur la carte sous le format CSV
	 * @return Les donnees d'une carte sous le format CSV
	 */
	public String afficherFichierTexte(){
		String s = ";\"" + titre + "\";\"" + equipe + "\";\"" + annee + "\"";
		return s;
	}

}
