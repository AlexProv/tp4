import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;


public class Inventaire{
	
	private static GestionInventaire gestionInventaire;
	/**
	 * affiche le menu console
	 * @throws IOException si il y a une erreure en lecture de la ligne
	 * @throws InventaireException 
	 * @throws SQLException 
	 */
	public static void afficherMenu() throws IOException, InventaireException, SQLException{
		Interaction.IOmenu();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s="";
		try {
			s = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		int i = Integer.parseInt(s);
		switch(i){ 
			case 1 : Interaction.IOmenu(i); ajouterJoueur(); break;
			case 2 : Interaction.IOmenu(i); afficherJoueur(); break;
			case 3 : Interaction.IOmenu(i); miseAJour(); break;
			case 4 : Interaction.IOmenu(i); effaceJoueur(); break;
//			case 5 : Interaction.IOmenu(i); rapport(); break;
			case 6 : Interaction.IOmenu(i); sauvegarderFichier(); break;
			case 0 : Interaction.IOmenu(i); Interaction.merci(); gestionInventaire.fermer(); System.exit(0); break;
			default: System.out.println("Rentrer un chiffre entre 0 et 6 svp") ; break;
		}
		afficherMenu();
	}
	
/**
	 * ajoute un joueur au joueur au programe.
	 * @throws InventaireException 
	 */
	private static void ajouterJoueur() throws InventaireException{
		String idJoueur = Interaction.IOCleIdentification();
		int nbCartes = gestionInventaire.gestionJoueur.ajouter(idJoueur);
		for (int i = 1; i <= nbCartes; ++i) {
			gestionInventaire.gestionCarte.ajouter(idJoueur);
		}
	}
	
	/**
	 * affiche les inforamtions sur un joueur
	 */
	private static void afficherJoueur() {
		String idJoueur = Interaction.IOCleIdentification();
		gestionInventaire.gestionJoueur.afficherJoueur(idJoueur);
		gestionInventaire.gestionCarte.afficherCartesJoueur(idJoueur);
	}
	
	/**
	 * efface un joueur du programe
	 * @throws InventaireException 
	 */
	private static void effaceJoueur() throws InventaireException{
		String id = Interaction.IOCleIdentification();
		gestionInventaire.gestionJoueur.effacer(id);
		gestionInventaire.gestionCarte.effacerCartes(id);
	}
	
//	/**
//	 * lit un fichier et remplie le joueur manager. 
//	 */
//	private static void lireFichier(){
//		List<String[]> listeJoueurs = Interaction.IOInitialisation();
//		for (String[] contenu : listeJoueurs) {
//			jm.ajouterJoueur(contenu);
//		}
//	}
//	
	/**
	 * imprime les informations dans un fichier
	 * @throws IOException
	 * @throws InventaireException 
	 */
	private static void sauvegarderFichier() throws IOException, InventaireException{
		System.out.println("Sauvegarde effectuee avec succes!");
	}
	
	private static void miseAJour() throws InventaireException
	{
		String idJoueur = Interaction.IOCleIdentification();
		
		gestionInventaire.gestionJoueur.effacer(idJoueur);
		gestionInventaire.gestionCarte.effacerCartes(idJoueur);
		
		int nbCartes = gestionInventaire.gestionJoueur.ajouter(idJoueur);
		for (int i = 1; i <= nbCartes; ++i) {
			gestionInventaire.gestionCarte.ajouter(idJoueur);
		}
	}
	

//	
//	/**
//	 * ecrit le rapport a la fenetre ou au fichier
//	 */
//	private static void rapport(){
//		String reponse = Interaction.IOtypeRapport();
//		String contenuRapport = jm.afficherTout();
//		if(reponse == "E")
//			System.out.print(contenuRapport);
//		else if(reponse == "F")
//			Interaction.IOSauvegarderRapport(contenuRapport);
//	}
	
	/**
	 * fonction main commance le programe
	 * @param args pour lancer le programe, on ne s'en sert pas.
	 * @throws IOException 
	 * @throws InventaireException 
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws IOException, InventaireException, SQLException {
		
		try {
			gestionInventaire = new GestionInventaire(args[0]);
			afficherMenu();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			gestionInventaire.fermer();
		}
		
	}

}
