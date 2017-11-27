import java.io.*;
import java.util.*;
/**
 *Ce fichier contient le main() qui permet l'exploitation du pathway LesAnimaux
 *On pourra modifier ce main si on desire manipuler plusieurs pathway (voir la classe Lesanimaux pour de plus amples informations
 *@author Adrien MENDES SANTOS
 *@author Rodolphe TWOREK
 *@see LesAnimaux
 */
public class Menu
{
    /**
     *Affiche le menu principal et les differentes fonctions du programme
     *@see #ajoutPoids(LesAnimaux,int)
     *@see #ajoutResult(LesAnimaux,int)
     *@see #declaDeces(LesAnimaux,int)
     *@see #affichageAnimaux(LesAnimaux,int)
     *@see #bilanSemaine(LesAnimaux,int,int)
     *@see #charger(LesAnimaux,int)
     *@see #sauvegarde(LesAnimaux,int)
     *@see #saveAndQuit(LesAnimaux,int,int)
     *@see LesAnimaux#ajouterAnimaux()
     *@param argv Inutilise dans ce programme
     */
    public static void main (String [] argv) 
    {
	int OK = 0;
	int results = 0;
	int saved = 0;
	LesAnimaux liste= new LesAnimaux();
	while (true)
	    {
		int ans=afficher_menu();
	        switch (ans)
		    {
		    case 1 : liste.ajouterAnimaux();OK=1;break;
		    case 2 : ajoutPoids(liste,OK);break;
		    case 3 : ajoutResult(liste,OK);results=1;break;
		    case 4 : declaDeces(liste,OK);break;
		    case 5 : affichageAnimaux(liste,OK);break;	
		    case 6 : bilanSemaine(liste,OK,results);break;
		    case 7 : results=charger(liste,results);OK=1;break;
		    case 8 : sauvegarde(liste,OK);break;
		    case 9 : System.out.println("Pour plus d'informations, veuillez consulter le Readme.txt fourni\n");enterToContinue();break;
		    case 0 : saveAndQuit(liste,saved,OK);
		    }
	    }
    }
    public static void header()
    {
	System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
	System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	System.out.println("~               AnimalCare                ~");
	System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    /**
     *Affiche le menu.
     *@return int la fonction du menu a executer
     */
    public static int afficher_menu() 
    {
	header();
	System.out.println("~ Bonjour,                                ~");
	System.out.println("~ Que voulez-vous faire ?                 ~");
	System.out.println("~                                         ~");
	System.out.println("~ 1: Creer une liste d'animaux.           ~");
	System.out.println("~ 2: Faire la pesee journaliere.          ~");
	System.out.println("~ 3: Ajouter des resultats.               ~");
	System.out.println("~ 4: Signaler un deces.                   ~");
	System.out.println("~ 5: Afficher la liste de vos animaux.    ~");
	System.out.println("~ 6: Faire le bilan de la semaine         ~");
	System.out.println("~ 7: Importer des donnees                 ~");
	System.out.println("~ 8: Sauvegarder vos donnees              ~");
	System.out.println("~ 9: Aide                                 ~");
	System.out.println("~ 0: Quitter                              ~");
	System.out.println("~                                         ~");
	System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	return saisie_entier();
    }

    /**
     *Permet la saisie d'un string
     *@return String Le texte tape par l'utilisateur;
     */
    public static String saisie_chaine ()
    {
	try {
	    BufferedReader buff = new BufferedReader
		(new InputStreamReader(System.in));
	    String chaine=buff.readLine();
	    return chaine;
	}
	catch(IOException e)
	    {
		System.out.println(" impossible de travailler" +e);
		return null;
	    }
    }
    
    /**
     *Permet la saisie d'un Integer
     *@return Integer Le nombre tape par l'utilisateur;
     */
    public static int saisie_entier ()
    {
	try{
	    BufferedReader buff = new BufferedReader
		(new InputStreamReader(System.in));
	    while (true)
		{
		    try
			{
			    String chaine=buff.readLine();
			    int num = Integer.parseInt(chaine);
			    return num;
			}
		    catch (NumberFormatException y)
			{System.out.println("Vous devez entrer un chiffre");}
		}
	}
	catch(IOException e){return 0;}
    }
    
    /**
     *Permet la saisie d'un Double.
     *@return Double Le nombre tape par l'utilisateur;
     */
    public static double saisie_double()
    {
	try{
	    BufferedReader buff = new BufferedReader
		(new InputStreamReader(System.in));
	    String chaine=buff.readLine();
	    double num = Double.parseDouble(chaine);
	    return num;
	}
	catch(IOException e){return 0;}
    }
    
    /**
     *Verifie si des animaux ont ete ajoutes.
     *@return true si il n'y a aucun animal
     *@see #enterToContinue()
     *@param OK 1 si il y a des animaux, 0 sinon.
     */
    public static boolean noAnimal(int OK)
    {if (OK == 0)
	    { System.out.println("Vous devez ajouter des animaux");enterToContinue();return true;}
	return false;}
    
    /**
     *Demande a l'utilisateur d'appuyer sur une touche pour continuer l'execution du programme
     *@see #saisie_chaine()
     */
    public static void enterToContinue()
    {
	System.out.println("Appuyez sur entree pour continuer :");
	saisie_chaine();
    }

    /**
     *Affiche les animaux dans 1 ou plusieurs especes stockes dans l'objet LesAnimaux
     *@see LesAnimaux
     *@see LesAnimaux#afficherAnimaux(String)
     *@see LesAnimaux#choixEspece()
     *@see #noAnimal(int)
     *@see #saisie_entier()
     *@see #enterToContinue()
     *@param liste Objet appartenant a la classe LesAnimaux
     *@param OK 1 si il y a des animaux, 0 sinon.
     */
    public static void affichageAnimaux(LesAnimaux liste,int OK)
    {
	if (noAnimal(OK)){return;}
	header();
	System.out.println("Que voulez-vous afficher?\n1: Tout les animaux.\n2: Une espece specifique.");
	while (true)
	    {
		int ans = saisie_entier();
		switch (ans)
		    {
		    case 1 : liste.afficherAnimaux("TOUT IMPRIMER");return;
		    case 2 : liste.afficherAnimaux(liste.choixEspece());enterToContinue();return;
		    }	
	    }
    }

    /**
     *Permet d'ajouter le poids pour une espece
     *@see #noAnimal(int)
     *@see LesAnimaux#choixEspece()
     *@see LesAnimaux#ajouterPoids(String)
     *@param liste Objet appartenant a la classe LesAnimaux
     *@param OK 1 si il y a des animaux, 0 sinon.
     */
    public static void ajoutPoids(LesAnimaux liste,int OK)
    {
	if (noAnimal(OK)){return;}
	header();
	System.out.println("A quelle espece ajouter les poids?");
	String ans = liste.choixEspece();
	if (ans != "QUITTER"){liste.ajouterPoids(ans);}
    }
    
    /**
     *Permet d'ajouter les resultas pour une espece
     *@see #noAnimal(int)
     *@see LesAnimaux#choixEspece()
     *@see LesAnimaux#ajouterResultats(String)
     *@param liste Objet appartenant a la classe LesAnimaux
     *@param OK 1 si il y a des animaux, 0 sinon.
     */
    public static void ajoutResult(LesAnimaux liste,int OK)
    {
	if (noAnimal(OK)){return;}
	header();
       	System.out.println("A quelle espece ajouter les resultats?");
	String ans = liste.choixEspece();
	if (ans != "QUITTER"){liste.ajouterResultats(ans);}
    }

    /**
     *Declare un animal comme etant mort
     *@see #noAnimal(int)
     *@see LesAnimaux#choixEspece()
     *@see LesAnimaux#signalerDeces(String)
     *@param liste Objet appartenant a la classe LesAnimaux
     *@param OK 1 si il y a des animaux, 0 sinon.
     */
    public static void declaDeces(LesAnimaux liste, int OK)
    {
	if (noAnimal(OK)){return;}
	header();
       	System.out.println("Dans quelle espece ?");
	String ans = liste.choixEspece();
	if (ans != "QUITTER"){liste.signalerDeces(ans);}
    }
    
    /**
     *Permet de faire le bilan sur tout les resultats
     *@see LesAnimaux#bilan()
     *@see #enterToContinue()
     *@param liste Objet appartenant a la classe LesAnimaux
     *@param OK 1 si il y a des animaux, 0 sinon.
     *@param results indique la presence ou non de resultats
     */
    public static void bilanSemaine(LesAnimaux liste, int OK, int results)
    {
	if (OK == 0 || results == 0)
	    { System.out.println("Vous devez ajouter des animaux ou vos animaux n'ont pas de resultats a afficher.");enterToContinue();return;}
	header();
	liste.bilan();
    }

    /**
     *Permet de charger des animaux a partir d'un fichier .txt
     *@see LesAnimaux#loadData(String)
     *@see #saisie_chaine()
     *@param liste Objet appartenant a la classe LesAnimaux
     *@param results indique la presence ou non de resultats
     *@return int 1 si les datas contenaient des resultats, sinon 0
     */
    public static int charger(LesAnimaux liste,int results)
    {
	System.out.println("Nom du ficher? (Sans extension)");
	results=liste.loadData(saisie_chaine()+".txt");
	return results;
    }

    /**
     *Permet d'enregistrer des animaux sous forme de fichier .txt
     *@see #saisie_chaine()
     *@see LesAnimaux#save(BufferedWriter)
     *@param liste Objet appartenant a la classe LesAnimaux
     *@param OK 1 si il y a des animaux, 0 sinon.
     */
    public static void sauvegarde(LesAnimaux liste,int OK)
    {
	if (noAnimal(OK)){return;}
	try
	    {
		System.out.println("Sauvegarde");
		System.out.println("Saisissez le nom du fichier de sortie (Sans extension)");
		BufferedWriter buff=new BufferedWriter(new FileWriter(saisie_chaine()+".txt"));
		liste.save(buff);
		buff.flush();
		buff.close();
	    }
	catch (IOException e)
	    {
		e.printStackTrace();
	    }
    }

    /**
     *Confirmation pour quitter le programme
     *@see #sauvegarde(LesAnimaux,int)
     *@see #saisie_entier()
     *@param liste Objet appartenant a la classe LesAnimaux
     *@param OK 1 si il y a des animaux, 0 sinon.
     *@param saved 1 si les donnees ont ete sauvegardees
     */
    public static void saveAndQuit(LesAnimaux liste, int saved, int OK)
    {
	while (true)
	    {
		if (saved == 0)
		    {
			System.out.println("Vos donnees ne sont pas sauvegardees, voulez-vous vraiment quitter?\n1: Oui\n2: Sauvegarder\n0: Annuler");
			int ans = saisie_entier();
			switch (ans)
			    {
			    case 1 : System.exit(0);
			    case 2 : sauvegarde(liste,OK);System.exit(0);
			    case 0 : return;
			    }
		    }
		else {System.exit(0);}
	    }
    }
}
