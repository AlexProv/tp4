/**
 * La classe TupleJoueur
 * 
 * @author Mathieu Lavoie, Alex Provencher et Vincent Gagnon
 *
 */

public class TupleJoueur implements Comparable<TupleJoueur>{
	
	private String clefId;
	private String nom;
	private String prenom;
	private int nbCarte;

	/**
	 * Le constructeur TupleJoueur permet d'obtenir toutes les informations sur
	 * un joueur
	 * @param id L'ID du joueur
	 */
	public TupleJoueur(String id)
	{
		
		this.clefId = id;
		
		String nomComplet = Interaction.IOJoueurNom();
		String[] nomArray = nomComplet.split(" ");
		this.prenom = nomArray[0];
		this.nom = nomArray[1];
		this.nbCarte = Interaction.IOnbCartes();
	}

	public int getNbCarte() {
		return nbCarte;
	}
	public String getClefId() {
		return clefId;
	}
	public String getNom() {
		return nom;
	}
	public String getPrenom() {
		return prenom;
	}
	
	public int compareTo(TupleJoueur n) {
		return n.clefId.compareTo(this.clefId) ;
	}
	
}
