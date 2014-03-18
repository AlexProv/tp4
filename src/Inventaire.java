import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;


public class Inventaire{
	
	private static JoueurManager jm = new JoueurManager();
	
	/**
	 * affiche le menu console
	 * @throws IOException si il y a une erreure en lecture de la ligne
	 */
	public static void afficherMenu() throws IOException{
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
			case 5 : Interaction.IOmenu(i); rapport(); break;
			case 6 : Interaction.IOmenu(i); sauvegarderFichier(); break;
			case 0 : Interaction.IOmenu(i); sauvegarderFichier(); Interaction.merci(); System.exit(0); break;
			default: System.out.println("Rentrer un chiffre entre 0 et 6 svp") ; break;
		}
		afficherMenu();
	}
	
	/**
	 * menu pour interagir au moment de faire la mise a jours des cartes
	 */
	private static void miseAJour(){
		String s = Interaction.IOCleIdentification();
		String info = jm.afficherJoueur(s);
		if(info == null){
			Interaction.cleIdentification();
			return;
		}
		else
			System.out.println(info);
		Interaction.modifierJoueur();
		jm.modifierJoueur(s);
	}
	
	/**
	 * ajoute un joueur au joueur au programe.
	 */
	private static void ajouterJoueur(){
		jm.ajouterJoueur(Interaction.IOCleIdentification());
	}
	
	/**
	 * affiche les inforamtions sur un joueur
	 */
	private static void afficherJoueur() {
		String info = jm.afficherJoueur(Interaction.IOCleIdentification());
		if(info == null)
			Interaction.cleIdentification();
		else
			System.out.println(info);
	}
	
	/**
	 * efface un joueur du programe
	 */
	private static void effaceJoueur(){
		String id = Interaction.IOCleIdentification();
		if(jm.find(id)!= null){
			System.out.println(jm.afficherJoueur(id));
			jm.retirerJoueur(jm.find(id));
		}else{
			effaceJoueur();
		}
	}
	
	/**
	 * lit un fichier et remplie le joueur manager. 
	 */
	private static void lireFichier(){
		List<String[]> listeJoueurs = Interaction.IOInitialisation();
		for (String[] contenu : listeJoueurs) {
			jm.ajouterJoueur(contenu);
		}
	}
	
	/**
	 * imprime les informations dans un fichier
	 * @throws IOException
	 */
	private static void sauvegarderFichier() throws IOException{
		Interaction.IOSauvegarder(jm.afficherInfoFichierTexte());
	}
	
	/**
	 * ecrit le rapport a la fenetre ou au fichier
	 */
	private static void rapport(){
		String reponse = Interaction.IOtypeRapport();
		String contenuRapport = jm.afficherTout();
		switch (reponse) {
		case "E":
			System.out.print(contenuRapport);
			break;
		case "F":
			Interaction.IOSauvegarderRapport(contenuRapport);
			break;
		}
	}
	
	/**
	 * fonction main commance le programe
	 * @param args pour lancer le programe, on ne s'en sert pas.
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		lireFichier();
		afficherMenu();
		
	}

}
