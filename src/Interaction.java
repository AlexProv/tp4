import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * La classe Interaction permet de gerer les entrees/sorties et les messages. 
 * 
 * @author Mathieu Lavoie, Alex Provencher et Vincent Gagnon
 *
 */
public class Interaction {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	/**
	 * La méthode IOMenu permet d'afficher le choix selectionne de l'utilisateur
	 * @param option Le numero du choix dans le menu principal
	 */
	public static void IOmenu(int option)
	{	
		System.out.print("Option selectionne: " + option + ". ");
		switch (option) {
		case 1:
			System.out.print("Ajouter un joueur\n");
			break;
		case 2:
			System.out.print("Afficher l'information d'un joueur\n");
			break;
		case 3:
			System.out.print("Mise a jour de l'information d'un joueur\n");
			break;
		case 5:
			System.out.print("Liste de joueurs\n");
			break;
		case 6:
			System.out.print("Sauvegarder\n");
			break;
		case 0:
			System.out.print("Sortir\n");
			break;
		}
	}
	

	/**
	 * Permet d'obtenir le titre de la carte
	 * 
	 * @param idCarte
	 * @return le titre de la carte
	 */
	public static String IOtitreCarte(int idCarte)
	{
		String titre = null;
		try {
			while(titre == null || titre.length() > 50){
				System.out.print("Entrez le titre de la carte " + idCarte + " : ");
				titre = br.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return titre;
	}
	
	/**
	 * Permet d'obtenir l'equipe de la carte
	 * 
	 * @param idCarte
	 * @return l'equipe de la carte
	 */
	
	public static String IOequipeCarte(int idCarte){
		String equipe = null;
		try {
			while(equipe == null || equipe.length() > 30){
				System.out.print("Entrez l'equipe de la carte " + idCarte + " : ");
				equipe = br.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return equipe;
	}
	
	/**
	 * Permet d'obtenir l'annee de la carte
	 * 
	 * @param idCarte
	 * @return l'annee de la carte
	 */
	
	public static int IOanneeCarte(int idCarte){
		int annee = 0;
		System.out.print("Entrez l'annee de parution de la carte " + idCarte + " : ");
		try {
			annee = Integer.parseInt(br.readLine().replaceAll("\\s+", ""));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return annee;
	}
	
	/**
	 * La methode IOMenu permet d'afficher le menu.
	 */
	public static void IOmenu()
	{
		System.out.println("Application de gestion de cartes de baseball");
		System.out.println("Voici la liste d'operations valides : ");
		System.out.println("1. Ajouter un joueur ");
		System.out.println("2. Afficher l'information d'un joueur"); 
		System.out.println("3. Mise a jour de l'information d'un joueur"); 
		System.out.println("4. Effacer l'information d'un joueur ");
		System.out.println("5. Liste des joueurs ");
		System.out.println("6. Sauvegarde ");
		System.out.println("0. Sortir ");
		
		System.out.print("Votre selection : ");
	}
	
	/**
	 * La methode IOJoueurNom pose la question sur le nom et prenom du joueur
	 * @return Le nom du joueur
	 */
	public static String IOJoueurNom()
	{
		String s=null;
		try {
			while(s == null || s.length() > 128){
				System.out.print("Entrez le nom et prenom du joueur : ");
				s = br.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return s;
	}
	
	/**
	 * La methode IOnbCartes pose la question sur le nombre de cartes
	 * @return Le nombre de cartes
	 */
	public static int IOnbCartes()
	{
		System.out.println("Combien de cartes?");
		String s;
		int i=0;
		try {
			s = br.readLine();
			i = Integer.parseInt(s);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException e){
			i = IOnbCartes();
		}
		return i;
	}
	
	/**
	 * La methode IOCleIdentification permet d'aller chercher la cle d'identification
	 * @return La cle
	 */
	public static String IOCleIdentification(){
		String s=null;
		try {
			while(s == null || s.length() > 16){
				System.out.print("Entrez la cle d'identification du joueur: ");
				s = br.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return s;
	}
	
	
	
	/**
	 * La methode IOInitialisation permet d'aller chercher le fichier test1 dans le dossier src
	 * @return Une liste de tableau String de tous les joueurs
	 */
	public static List<String[]> IOInitialisation(){
		List<String[]> listeJoueurs = new ArrayList<String[]>();
		try {
			File file = new File(System.getProperty("user.dir") + "\\src\\test1.txt");
			if(file.exists()){
				BufferedReader brFile = new BufferedReader(new FileReader(file));
				String line;
				while ((line = brFile.readLine()) != null) {
					String[] contenus = line.split(";");
					for (int i = 0; i < contenus.length; i++) {
						if (contenus[i].contains("\"")) {
							contenus[i] = contenus[i].replace("\"", "");
						}
					}
					listeJoueurs.add(contenus);
				}
				brFile.close();
			}
				
		} catch (IOException e){
			e.printStackTrace();			
		}
		return listeJoueurs;
	}
	
	/**
	 * Message de fin du programme
	 */
	public static void merci(){
		System.out.println("Merci d'avoir utilise le systeme de gestion d'inventaire de cartes."); 
	}
}
