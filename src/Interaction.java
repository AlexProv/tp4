import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
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
	private static Writer writer;
	
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
	 * La methode IOCarte te permet de donner les informations sur les cartes (titre, equipe, annee)
	 * @param nb La xeme carte
	 * @return Un objet Carte
	 */
	public static Carte IOcarte(int nb)
	{
		String titre = null;
		int annee = 0;
		String equipe = null;
		try {
			while(titre == null || titre.length() > 50){
				System.out.print("Entrez le titre de la carte " + nb + " : ");
				titre = br.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			while(equipe == null || equipe.length() > 30){
				System.out.print("Entrez l'equipe de la carte " + nb + " : ");
				equipe = br.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		System.out.print("Entrez l'annee de parution de la carte " + nb + " : ");
		try {
			annee = Integer.parseInt(br.readLine());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return new Carte(titre, equipe, annee);
		
		
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
	 * La methode IOSauvegarder permet de sauvegarder un CSV
	 * @param fileContent le contenu du fichier
	 * @throws IOException
	 */
	public static void IOSauvegarder(String fileContent) throws IOException{
		int nb = 1;
	    	while(new File(System.getProperty("user.dir") + "\\src\\test" + nb + ".txt").exists())
	    		nb++;
			writer = new BufferedWriter(new OutputStreamWriter(
			        new FileOutputStream(System.getProperty("user.dir") + "\\src\\test" + nb + ".txt"), "utf-8"));
			writer.write(fileContent);
			System.out.println("Le fichier " + System.getProperty("user.dir") + "\\src\\test" + nb + ".txt a ete cree avec succes.");
	    writer.close();
	}
	/**
	 * La methode IOSauvegarderRapport permet de sauvegarder un rapport (option 5).
	 * @param fileContent Le contenu du fichier.
	 */
	public static void IOSauvegarderRapport(String fileContent){
		System.out.println("Entrez le nom du fichier : ");
		String path = "";
		try {
			path = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path)));
			writer.write(fileContent);
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
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
	 * La methode IOtyperapport permet de savoir si c'est affiche sur un ecran ou un fichier.
	 * @return E ou F
	 */
	public static String IOtypeRapport(){
		String typeRapport = "";
		System.out.print("\nVoulez-vous creer la liste des joueurs dans un fichier ou l'afficher sur l'ecran ? (F/E):");
		try {
			typeRapport = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		typeRapport	= typeRapport.toUpperCase();
		return typeRapport;
	}
	
	/**
	 * La methode confirmationDelete demande si la personne veut vraiment effacer le joueur.
	 * @return Si c'est accepte
	 */
	public static boolean confirmationDelete(){
		System.out.println("Voulez vous effacer l'information de ce joueur ? (O/N)");
		try {
			if( br.readLine().toUpperCase().equals("O")){
				return true;
			}		
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static void deleteConfirme(String nom){
		System.out.println("L'information du joueur "+ nom +" a ete efface du systeme.");
	}
	public static void merci(){
		System.out.println("Merci d'avoir utilise le systeme de gestion d'inventaire de cartes."); 
	}
	
	public static void cleIdentification(){
		System.out.println("La cle d'identification du joueur n'existe pas.");
	}

	public static void modifierJoueur() {
		System.out.println("Maintenant entrez les donnees a modifier : ");
	}
}
