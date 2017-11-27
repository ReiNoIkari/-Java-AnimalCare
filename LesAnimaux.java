import java.io.*;
import java.util.*;
/**
 *La classe "Les animaux" qui regroupe en elle des animaux de plusieurs especes 
 *permet l'ajout d'especes sans avoir a modifier le main
 *Les especes sont regroupees içi sous forme d'ArrayList, implementees dans un dictionnaire
 *Ce pathway permet d'ajouter des resultats de test, et d'acceder plus generalement aux methodes des Animaux
 *@author Adrien MENDES SANTOS
 *@author Rodolphe TWOREK
 *@see Menu
 *@see Animaux
 */
public class LesAnimaux<T extends Animaux>
{
    private String nom="Pathway";
    /**
     *Variable la plus importante de la classe
     *Hashtable contenant l'ensemble des ArrayList d'especes
     */
    protected Hashtable <String,ArrayList> dico = new Hashtable <String,ArrayList>();

    /**
     *Constructeur du pathway
     *Ajoute dans la Hashtable les ArrayList correspondant aux especes
     *@see Singe
     *@see Souris1
     *@see Souris2
     */
    public LesAnimaux()
    {
	//Ajouter les especes a gerer sous la forme: dico.put("Nom Espece", new ArrayList <NomDeClasse>);
	dico.put("Singes", new ArrayList <Singe>());
	dico.put("Souris G1", new ArrayList <Souris1>());
	dico.put("Souris G2", new ArrayList <Souris2>());
    }


    
    // AJOUTER DES ANIMAUX
    /**
     *Permet de chosir dans quelle espece ajouter des animaux
     *@see #choixEspece()
     *@see #ajouterAnimal(String)
     */
    public void ajouterAnimaux()
    {
	System.out.println("###########################################");
	System.out.println("#         CREATION DE VOTRE LISTE         #");
	System.out.println("###########################################\n");
	System.out.println("Quelle espece desirez-vous ajouter ?");
	String key = this.choixEspece();
	if (key == "QUITTER"){return;}
	else {ajouterAnimal(key);}
    }
    /**
     *Permet de choisir combien d'animaux ajouter dans l'espece precisee en parametre
     *@see Menu#saisie_entier()
     *@see Singe
     *@see Souris1
     *@see Souris2
     *@see #choixNom()
     *@see #choixPoids()
     *@see #choixSexe()
     *@param nomEspece Correspond a la clef de la hashtable dans LesAnimaux
     */
    public void ajouterAnimal(String nomEspece) 
    {
	System.out.println("Combien d'animaux voulez-vous ajouter ?");
	int ans = Menu.saisie_entier();
	//Ajoutez ici vos nouvelles especes en suivant le même format :
	if (nomEspece == "Singes")
	    {
		for (int i=0;i<ans;i++)
		    {
			dico.get("Singes").add(new Singe(choixNom(),choixPoids(),choixSexe()));
		    }
	    }
	else if (nomEspece == "Souris G1")
	    {
		for (int i=0;i<ans;i++)
		    {
			dico.get("Souris G1").add(new Souris1(choixNom(),choixPoids(),choixSexe()));
		    }
	    }
	else if (nomEspece == "Souris G2")
	    {
		for (int i=0;i<ans;i++)
		    {
			dico.get("Souris G2").add(new Souris2(choixNom(),choixPoids(),choixSexe()));
		    }
	    }
    }
    /**
     *Permet de charger des datas depuis un texte vers un nouvel animal
     *@see Animaux#createAsNew(String,String,Double,Double,Double,Boolean,String,String)
     *@see Singe
     *@see Souris1
     *@see Souris2
     *@param attr String[] contenant les differents attributs de l'animal a ajouter :
     *attr[0] : Espece | attr[1] : Nom | 2 : Sexe | 3 : Poids (actuel) | 4 : Poids (Initial) | 5 : Progression |  6 : Stress | 7 : Nom du test | 8 : Resultats
     */
    public void creationAnimal(String[] attr) 
    {
	String result = "NA";
	try
	    {
		result = attr[8];
	    }
	catch (ArrayIndexOutOfBoundsException e)
	    {
		result = "NA";
	    }
	//Ajoutez içi vos especes en suivant le même format :
	if (attr[0].equals("Singes")){
	Singe animal = new Singe("",0.0,"");	
	animal.createAsNew(attr[1], attr[2], Double.parseDouble(attr[3]), Double.parseDouble(attr[4]), Double.parseDouble(attr[5]), Boolean.parseBoolean(attr[6]), attr[7], result);
	dico.get(attr[0]).add(animal);
	}
	else if (attr[0].equals("Souris G1")){
	Souris1 animal = new Souris1("",0.0,"");
	animal.createAsNew(attr[1], attr[2], Double.parseDouble(attr[3]), Double.parseDouble(attr[4]), Double.parseDouble(attr[5]), Boolean.parseBoolean(attr[6]), attr[7], result);
	dico.get(attr[0]).add(animal);
	}
	else if (attr[0].equals("Souris G2")){ 
	Souris2 animal = new Souris2("",0.0,"");
	animal.createAsNew(attr[1], attr[2], Double.parseDouble(attr[3]), Double.parseDouble(attr[4]), Double.parseDouble(attr[5]), Boolean.parseBoolean(attr[6]), attr[7], result);
	dico.get(attr[0]).add(animal);
	}
    }

    /**
     *Affiche le bilan complet pour toutes les especes, calcule les progressions moyennes par espece et cherche le meilleur animal
     *@see #affichageBilan(ArrayList)
     *@see #moyenneEspece(String)
     *@see #trouverMeilleur(ArrayList, Double)
     *@see Animaux
     *@see Animaux#affiche()
     *@see Menu
     *@see Menu#enterToContinue()
     */
    public void bilan()
    {
	ArrayList <T> meilleurs_animaux= new ArrayList <T>();
	Double meilleur_progress=affichageBilan(meilleurs_animaux);
	System.out.println("\n\n\n\nProgression des especes :");
	{Enumeration <String> e = dico.keys(); // Affiche la progression moyenne pour chaque espece
		while(e.hasMoreElements())
		    {
			String param = e.nextElement();
			System.out.println(param+" : "+moyenneEspece(param)+"%");
		    }
	}
	System.out.println("\nAffichage du meilleur animal : ");
	T Animal_ultime=trouverMeilleur(meilleurs_animaux,meilleur_progress); // Recupere LE meilleur animal
	System.out.println("Espece : "+Animal_ultime.getEspece());
	Animal_ultime.affiche();
	System.out.println("Progression : "+Animal_ultime.getProgress()+" %");
	Menu.enterToContinue();
    }
    /**
     *Demande un nom
     *@see Menu
     *@see Menu#saisie_chaine()
     *@return String Le nom de l'animal a ajouter
     */
    public String choixNom()
    {
	System.out.println("Quel nom ? ");
	return Menu.saisie_chaine();
    }
    /**
     *Demande un poids en kg
     *@see Menu
     *@see Menu#saisie_double()
     *@return Double Le poids de l'animal a ajouter
     */
    public double choixPoids()
    {
	System.out.println("Quel poids (kg) ? ");
	return Menu.saisie_double();
    }
    /**
     *Demande le sexe
     *@see Menu
     *@see Menu#saisie_entier()
     *@return String Le sexe de l'animal a ajouter
     */
    public String choixSexe()
    {
	while(true)
	    {
		System.out.println("Quel sexe (1 : M, 2 : F) ? ");
		int ans = Menu.saisie_entier();
		switch (ans)
		    {
		    case 1 : return "M";
		    case 2 : return "F";
		    }
	    }
    }


    
    // MODIFIER LES VALEURS D'UN ANIMAL
    /**
     *Ajoute le poids de tout les animaux d'une espece en affichant leurs noms
     *@see Menu
     *@see Menu#saisie_double()
     *@see Animaux
     *@see Animaux#getName()
     *@see Animaux#setPoids(Double)
     *@param key La clef correspondant a l'espece dans la Hashtable LesAnimaux
     */
    public void ajouterPoids(String key)
    {
     	for (Iterator <T> e = dico.get(key).iterator(); e.hasNext();)
     	    {
     		T item = e.next();
		System.out.println("Quel est le poids de "+item.getName()+" (kg)?");
		item.setPoids(Menu.saisie_double());
     	    }
    }
    /**
     *Ajoute les resultats de tout les animaux d'une espece
     *@see Animaux
     *@see Animaux#ajoutResultats()
     *@param key La clef correspondant a l'espece dans la Hashtable LesAnimaux
     */
    public void ajouterResultats(String key)
    {
     	for (Iterator <T> e = dico.get(key).iterator(); e.hasNext();)
     	    {
     		T item = e.next();
		item.ajoutResultats();
     	    }
    }
    /**
     *Affiche tout les animaux, puis demande l'Identifiant du mort
     *@see Animaux
     *@see Animaux#affiche()
     *@see Animaux#getID()
     *@see Animaux#deces()
     *@see Menu
     *@see Menu#saisie_entier()
     *@param key La clef correspondant a l'espece dans la Hashtable LesAnimaux
     */
    public void signalerDeces(String key)
    {
	for (Iterator <T> e = dico.get(key).iterator(); e.hasNext();) 
	    {
		e.next().affiche();
	    }
	System.out.println("Choisissez l'ID de l'animal mort :");
	int ID = Menu.saisie_entier();
	for (Iterator <T> e = dico.get(key).iterator(); e.hasNext();) 
	    {
		T item = e.next();
		if (item.getID()==ID){item.deces();}
	    }
    }


    
    // AFFICHAGES ESPECES/ANIMAUX
    /**
     *Affiche les animaux dans une ou toute les especes
     *@see #afficherEspece(String)
     *@see Menu
     *@see Menu#enterToContinue()
     *@param key La clef correspondant a l'espece dans la Hashtable LesAnimaux 
     *       ou "TOUT IMPRIMER" signifiant d'afficher toutes les especes
     */
    public void afficherAnimaux(String key)
    {
	if (key == "TOUT IMPRIMER")
	    {Enumeration <String> e = dico.keys();
		while(e.hasMoreElements())
		    {
			String param = e.nextElement();
			afficherEspece(param);
			Menu.enterToContinue();
		    }
	    }
	else
	    {
		afficherEspece(key);
	    }
    }
    /**
     *Affiche tout les animaux vivants dans une espece
     *@see Animaux
     *@see Animaux#affiche()
     *@see Animaux#isDead()
     *@param key La clef correspondant a l'espece dans la Hashtable LesAnimaux
     */
    public void afficherEspece(String key)
    {
	for (Iterator <T> e = dico.get(key).iterator(); e.hasNext();) 
	    {
		T item = e.next();
		if (!(item.isDead())){item.affiche();}
	    }
    }
    /**
     *Affiche les differentes especes de la Hashtable LesAnimaux et demande a l'utilisateur laquelle il desire
     *@see Menu
     *@see Menu#saisie_entier()
     *@return String La clef correspondant a l'espece dans la Hashtable LesAnimaux
     */
    public String choixEspece()
    {
	Enumeration<String> e = dico.keys();
	while(e.hasMoreElements())
	    {
		String param = e.nextElement();
		System.out.println(param+" ? (1 : oui, 2 : non, 0 : Quitter)");
		int ans = Menu.saisie_entier();
		switch (ans)
		    {
		    case 1 : return param;
		    case 2 : break;
		    case 0 : return "QUITTER";
		    }
	    }
	return "QUITTER";
    }


    
    // BILAN
    /**
     *Affiche pour chaque espece les animaux ainsi que leurs progressions, stocke en parrallele la meilleure progression
     *@see Animaux
     *@see Animaux#getProgress()
     *@see Animaux#affiche()
     *@param meilleurs_animaux : une ArrayList d'animaux
     *@return Double La meilleure progression
     */
    public Double affichageBilan(ArrayList <T> meilleurs_animaux)
    {
	Double Best = 0.0;
	{Enumeration <String> e = dico.keys(); // Affichage des animaux par espece, et stockage de la meilleure progression
	    while(e.hasMoreElements())
		{
		    String dicoKey = e.nextElement();
		    System.out.println("\n\nAnimaux dans l'espece : "+dicoKey+"\n\n");
		    for (Iterator <T> anim = dico.get(dicoKey).iterator(); anim.hasNext();) 
			{
			    T item = anim.next();
			    item.affiche();
			    Double Progress=item.getProgress();
			    System.out.println("Progression : "+Progress+" %");
			    if (Progress>Best) {Best=Progress;}
			    Menu.enterToContinue();
			    meilleurs_animaux.add(item);
			}
		}
	}
	    
	return Best;
    }
    
    /**
     *Cherche dans une ArrayList l'animal ayant la meilleure progression
     *@see Animaux
     *@see Animaux#getProgress()
     *@param Anim : une ArrayList d'animaux
     *@param meilleur_progress : la valeur de progression maximale pour tout les animaux
     *@return T extends Animaux : l'animal ayant le meilleur progres
     */
    public T trouverMeilleur(ArrayList <T> Anim, Double meilleur_progress)
    {
	for (Iterator <T> e = Anim.iterator(); e.hasNext();) // On cherche l'animal avec la plus grande progression
	    {
		T animal=e.next();
		Double Progress=animal.getProgress();
		if (Progress>=meilleur_progress)
		    {
			return animal;
		    }
	    }
	return Anim.get(0);
    }

    /**
     *Recupere pour chaque animal d'une espece la moyenne des progressions
     *@see Animaux
     *@see Animaux#getProgress()
     *@param key : La clef de la Hashtable LesAnimaux
     *@return Double : la moyenne des progression pour une espece
     */
    public Double moyenneEspece(String key)
    {
	Double Somme=0.0;
	for (Iterator <T> e = dico.get(key).iterator(); e.hasNext();) // On cherche la progression moyenne dans l'espece
	    {
		Somme+=e.next().getProgress();
	    }
	return (Somme/dico.get(key).size());
    }


    
    // CHARGER-SAUVEGARDER
    /**
     *Permet de stocker tout les animaux de la Hashtable sous forme de fichier .txt
     *@see Animaux
     *@see Animaux#isDead()
     *@see Animaux#save(BufferedWriter)
     *@see Menu#sauvegarde(LesAnimaux,int)
     *@param buff : le buffer qui va ecrire dans notre fichier
     */
    public void save(BufferedWriter buff)
    {
	Enumeration<String> e = dico.keys();
	while(e.hasMoreElements())
	    {
		String dicoKey = e.nextElement();
		for(Iterator <T> array = dico.get(dicoKey).iterator();array.hasNext();)
		    {
			T courant = (T)array.next();
			if (!(courant.isDead()))
			    {courant.save(buff);}
		    }
	    }
    }

    /**
     *Permet de recuperer les donnees d'un fichier texte et de creer les animaux correspondants
     *@see #creationAnimal(String[])
     *@see Menu
     *@see Menu#enterToContinue()
     *@see Menu#charger(LesAnimaux,int)
     *@param fileName : Le nom du fichier de sortie (.txt)
     *@return int : 1 si les datas contiennent des resultats de tests, 0 sinon
     */
    public int loadData(String fileName)
    {
	int animals_have_results = 1;
	try
	    {
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		String line;
		while ((line = br.readLine()) != null) {
		    String[] test = line.split(";");
		    if (test.length==9){
			try
			    {
				if (test[8].equals("NA"))
				    {animals_have_results=0;}
			    }
			catch (ArrayIndexOutOfBoundsException e)
			    {
				;
			    }
			creationAnimal(test);
		    }
		}
		br.close();
	    }
	catch (FileNotFoundException f)
	    {
		System.out.println("Ce nom de fichier est introuvable.");
		Menu.enterToContinue();
	    }
	catch (IOException e)
	    {
		System.out.println(e);
		Menu.enterToContinue();
	    }
	return animals_have_results;
    }
}
