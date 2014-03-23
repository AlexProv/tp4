import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.List;


public class Inventaire{
	
	private static GestionInventaire gestionInventaire;
	/**
	 * affiche le menu console
	 * @throws IOException si il y a une erreur en lecture de la ligne
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
		
		int i = Integer.parseInt(s.replaceAll("\\s+", ""));
		
		switch(i){ 
			case 1 : Interaction.IOmenu(i); ajouterJoueur(); break;
			case 2 : Interaction.IOmenu(i); afficherJoueur(); break;
			case 3 : Interaction.IOmenu(i); miseAJour(); break;
			case 4 : Interaction.IOmenu(i); effaceJoueur(); break;
			case 5 : Interaction.IOmenu(i); rapport(); break;
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
	
	
	/**
	 * imprime les informations dans un fichier
	 * @throws IOException
	 * @throws InventaireException 
	 */
	private static void sauvegarderFichier() throws IOException, InventaireException{
		System.out.println("Sauvegarde effectuee avec succes!");
	}
	
	/**
	 * met a jour les informations du joueur et les cartes en les supprimant et les recreant
	 * @throws InventaireException
	 */
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
	

	
	/**
	 * ecrit le rapport a la fenetre
	 */
	private static void rapport(){
		List<String> listId = gestionInventaire.gestionJoueur.listId();
		for (int i = 0; i < listId.size(); i++) {
			gestionInventaire.gestionJoueur.afficherJoueur(listId.get(i));
			gestionInventaire.gestionCarte.afficherCartesJoueur(listId.get(i));
		}
	}
	
	/**
	 * fonction main commence le programe
	 * @param args pour lancer le programme, on prend le fichier ODB de la BD
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
