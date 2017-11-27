import java.io.*;
import java.util.*;
/**
 *La classe Animaux du projet java, qui permet de definir les caracteristiques principales des sujets
 *Des variables specifiques pourront êtres ajoutes dans les sous-classe des especes
 *Cette classe est utilisee par le pathway LesAnimaux
 *@author Adrien MENDES SANTOS
 *@author Rodolphe TWOREK
 *@see LesAnimaux
 *@see Menu
 */
public class Animaux
{
    /**
     *Permet de donner un ID unique a chaque animal
     */
    protected static int cpt=0;
    /**
     *La liste des jours de la semaine, afin de recuperer le jour de la meilleure performance
     */
    protected static List<String> jours = Arrays.asList("Lundi","Mardi","Mercredi","Jeudi","Vendredi");
    /**
     *L'identifiant de l'animal, afin de le selectionner lors d'un choix au sein du programme, il peut changer d'un chargement a un autre
     *@see #nom
     *@see LesAnimaux#signalerDeces(String)
     */
    protected int ID;
    /**
     *Le nom de l'animal, ce nom est l'identifiant reel de l'animal, il ne change pas
     *@see #ID
     */
    protected String nom;
    /**
     *Le sexe de l'animal, sous la forme M/F
     */
    protected String sexe;
    /**
     *Le poids actuel de l'animal (par defaut en kilos)
     */
    protected Double poids;
    /**
     *Le poids de l'animal lors de sa creation, ne varie pas
     */
    protected Double poids_ini;
    /**
     *La progression qu'a effectue l'animal, n'est donnee que lors du bilan
     *@see #getProgress()
     *@see LesAnimaux#bilan()
     */
    protected Double progress=0.0;
    /**
     *Definit si l'animal est mort, les animaux morts ne sont ni affiches, ni sauvegardes
     *@see #deces()
     *@see #isDead()
     */
    protected boolean mort=false;
    /**
     *Definit si l'animal est dans un etat de stress/fatigue
     *Cet etat est vrai quand l'animal perds plus de 10% de son poids en 1 semaine
     *@see #isStressed()
     *@see #stress()
     *@see #stressToString()
     */
    protected boolean stress=false;
    /**
     *Le nom du test que passe l'animal, ce nom est par defaut Undefined, la valeur doit etre changee dans les sous-classes
     *@see #getTest()
     *@see Singe
     *@see Souris1
     *@see Souris2
     */
    protected String test;
    /**
     *Le constructeur de la classe Animaux
     *il determine une grande partie des caracteristiques de l'animal, seul le resultat est manquant
     *Le resultat est une valeur a ajouter dans les sous-classes d'animaux
     *@see Singe
     *@see Souris1
     *@see Souris2
     *@param name : Nom de l'animal
     *@param weight : Poids de l'animal
     *@param sex : M (male) ou F (female) en fonction du sexe
     */
    public Animaux(String name, Double weight, String sex)
    {
	cpt++;
	ID = cpt;
	nom = name;
	poids=weight;
	poids_ini=weight;
	sexe = sex;
	test="Undefined";
    }
    /**
     *Affiche les caracteristiques de l'animal
     *@see #getID()
     *@see #getName()
     *@see #getWeight()
     *@see #stressToString()
     *@see #getSex()
     *@see Singe#affiche()
     *@see Souris1#affiche()
     *@see Souris2#affiche()
     */
    public void affiche() 
    {
	System.out.println("ID : "+this.getID()+" | Nom : "+this.getName()+" | poids : "+this.getWeight()+" kg | Sexe : "+this.getSex()+" | Stress : "+this.stressToString());
	System.out.println("Resultats :");
    }
    /**
     *Donne l'espece a laquelle appartient cet animal
     *NB : les especes des sous-classes devraient être EXACTEMENT les même que dans l'objet LesAnimaux
     *@see LesAnimaux#dico
     *@see Singe
     *@see Souris1
     *@see Souris2
     *@see Singe#getEspece()
     *@see Souris1#getEspece()
     *@see Souris2#getEspece()
     *@return String : A quelle espece appartient l'animal
     */
    public String getEspece(){return "Undefined";}


    
    //DONNEES GENERALES
    /**
     *Donne le nom de l'animal
     *@see #nom
     *@return String : Le nom de l'animal
     */
    public String getName(){return nom;}
    /**
     *Donne le sexe de l'animal
     *@see #sexe
     *@return String : Le sexe de l'animal (M ou F)
     */
    public String getSex(){return sexe;}
    /**
     *Donne le poids actuel de l'animal
     *@see #poids
     *@return Double : Le poids de l'animal
     */
    public Double getWeight(){return poids;}
    /**
     *Donne le test que passe l'animal
     *@see #test
     *@return String : Le test de l'animal (defaut = Undefined)
     */
    public String getTest(){return test;}
    /**
     *Donne l'ID de l'animal
     *@see #ID
     *@return int : L'ID de l'animal
     */
    public int getID(){return ID;};
    /**
     *Definis le nouveau poids de l'animal
     *Si la variation poids initial - poids est de plus de 10%, alors l'animal devient stresse
     *@see #poids
     *@see #poids_ini
     *@see #stress
     *@see #stress()
     *@param poids : Poids de l'animal
     */
    public void setPoids(Double poids)
    {
	poids=poids;
	if ((poids_ini*0.1)<(poids_ini-poids)){this.stress();}
    }


    
    // GESTION DU STATUT (stress, mort)
    /**
     *Renvoie l'etat de l'animal (Stress/fatigue)
     *@return boolean : <code>true</code> si l'animal est stresse
     *                  <code>false</code> dans le cas contraire
     */
    public boolean isStressed(){return stress;}
    /**
     *Renvoie l'etat de stress sous forme de String
     *@return String : Oui si l'animal est stresse
     *                  Non dans le cas contraire
     */
    public String stressToString()
    {
	if (this.isStressed())
	    {return "Oui";}
	return "Non";
    }
    /**
     *Renvoie l'etat de l'animal (Mort/Vivant)
     *@return boolean <code>true</code> si l'animal est mort
     *                <code>false</code> dans le cas contraire
     */
    public boolean isDead(){return mort;}
    /**
     *Definit l'animal comme stresse
     *@see #stress
     */
    public void stress(){stress=true;}
    /**
     *Definit l'animal comme mort
     *@see #mort
     */
    public void deces(){mort=true;}


    
    // GESTION DES RESULTATS - PROGRES
    /**
     *Fonction non implementee dans Animaux, cette fonction ajoute des resultats a l'ArrayList resultat de votre sous-classe T extends Animaux
     *@see Singe
     *@see Singe#setResult()
     *@see Souris1
     *@see Souris1#setResult()
     *@see Souris2
     *@see Souris2#setResult()
     */
    public void setResult(){;} // A implementer dans votre Sous-classe d'espece
    /**
     *Version verbose de setResult(), affiche le nom de l'animal et de son test
     *@see #setResult()
     *@see #getName()
     *@see #getTest()
     */
    public void ajoutResultats()
    {
	System.out.println("Ajout des resultats de "+getName()+" pour le test "+getTest()+" :");
	setResult();
    }
    /**
     *Recupere la progression de l'animal, cette fonction doit etre implementee dans votre sous-classe T extends Animaux
     *@return Double : La progression de votre animal (Defaut = 0.0)
     */
    public Double getProgress(){return 0.0;}
    /**
     *Renvoie l'ArrayList contenant les resultats de la semaine
     *@return ArrayList : les resultats de votre animal
     */
    public ArrayList getResult(){return new ArrayList <Object>();} // Retourner votre ArrayList de resultats dans vos sous-classes


    
    // CHARGER-SAUVEGARDER
    /**
     *Doit retourner l'ensemble de vos resultats de votre animal sous forme de string 
     *l'implementation dans votre sous-classe pourra etre modifiee en fonction du separateur souhaite (ou aucun)
     *@see Singe#loadResult(ArrayList)
     *@see Souris1#loadResult(ArrayList)
     *@see Souris2#loadResult(ArrayList)
     *@return String : Vos resultats sous forme de string, chaque valeur separee par "," (par defaut)
     */
    public String resultsToString()
    {
	String results="";
	if (getResult().isEmpty()){ return "NA";}
	for (Iterator e = getResult().iterator(); e.hasNext();)
	    {
		results+=e.next().toString();
		if (e.hasNext()) //Si ce n'est pas le dernier resultat 
		    { results+=",";}
	    }
	return results;
    }
    /**
     *Recupere les resultats d'un fichier .txt et les remet sous forme d'ArrayList
     *l'implementation dans votre sous-classe pourra etre modifiee en fonction du separateur souhaite (ou aucun)
     *@see Singe#loadResult(ArrayList)
     *@see Souris1#loadResult(ArrayList)
     *@see Souris2#loadResult(ArrayList)
     *@param values : vos resultats sous forme de String, separes par votre separateur 
     *@return ArrayList : votre ArrayList de resultats
     */
    public ArrayList stringToResult(String values)
    {
	ArrayList animalResult = new ArrayList();
	if (values.equals("NA")){return animalResult;}
	for (String e : values.split(","))
	    {
		Double item = Double.parseDouble(e);
		animalResult.add(item);
	    }
	return animalResult;
    }
    /**
     *Permet la sauvegarde de votre animal dans le fichier.txt
     *@see LesAnimaux#save(BufferedWriter)
     *@see Menu#sauvegarde(LesAnimaux,int)
     *@see #getEspece()
     *@see #getName()
     *@see #getWeight()
     *@see #getProgress()
     *@see #isStressed()
     *@see #getTest()
     *@see #resultsToString()
     *@see #getSex()
     *@param buff : Le buffer qui va ecrire dans le fichier de votre choix
     */
    public void save(BufferedWriter buff)
    {
	try
	    {		buff.write(getEspece()+";"+getName()+";"+getSex()+";"+(new Double(getWeight())).toString()+";"+(new Double(poids_ini)).toString()+";"+(new Double(getProgress())).toString()+";"+(new Boolean(isStressed())).toString()+";"+getTest()+";"+resultsToString());
		buff.newLine();
	    }
	catch (IOException e)
	    {
		e.printStackTrace();
	    }
    }



    // CREATERS : Pour charger les datas depuis un fichier (appeles par la fonction createAsNew())
    /**
     *Permet de changer toutes les valeurs d'un animal, utilise lors du chargement du fichier.txt
     *@see #createName(String)
     *@see #createSex(String)
     *@see #createWeight(Double,Double)
     *@see #createProgress(Double)
     *@see #createStress(Boolean)
     *@see #createTestName(String)
     *@see #createResult(String)
     *@param name : Nom de l'animal
     *@param sex : Sexe de l'animal
     *@param weight : Poids de l'animal
     *@param weightI : Poids initial de l'animal
     *@param progress_value : Progression de l'animal
     *@param stress_value : <code>true</code> or <code>false</code> pour le stress de l'animal
     *@param test_name : Nom du test que l'animal passe
     *@param result_values : Resultats aux test (si effectues)
     */
    public void createAsNew(String name, String sex, Double weight, Double weightI, Double progress_value, Boolean stress_value, String test_name,String result_values)
    {
	createName(name);
	createSex(sex);
	createWeight(weight,weightI);
	createProgress(progress_value);
	createStress(stress_value);
	createTestName(test_name);
	createResult(result_values);
    }
    /**
     *Definit le nom de l'animal
     *@see #createAsNew(String, String, Double, Double, Double, Boolean, String, String)
     *@param name : Nom de l'animal
     */
    public void createName(String name){nom=name;}
    /**
     *Definit le poids et le poids initial de l'animal
     *@see #createAsNew(String, String, Double, Double, Double, Boolean, String, String)
     *@param weight : poids de l'animal
     *@param initialWeigth : poids initial de l'animal
     */
    public void createWeight(Double weight,Double initialWeigth){poids=weight;poids_ini=initialWeigth;}
    /**
     *Definit le sexe de l'animal
     *@see #createAsNew(String, String, Double, Double, Double, Boolean, String, String)
     *@param sex : Sexe de l'animal
     */
    public void createSex(String sex){sexe=sex;}
    /**
     *Definit progrès qu'a fait l'animal
     *@see #createAsNew(String, String, Double, Double, Double, Boolean, String, String)
     *@param prog : progres de l'animal
     */
    public void createProgress(Double prog){progress=prog;}
    /**
     *Definit si l'animal est ou non stresse
     *@see #createAsNew(String, String, Double, Double, Double, Boolean, String, String)
     *@param stress_value :  <code>true</code> or <code>false</code> pour le stress de l'animal
     */
    public void createStress(Boolean stress_value){stress=stress_value;}
    /**
     *Definit le nom du test que passe l'animal
     *@see #createAsNew(String, String, Double, Double, Double, Boolean, String, String)
     *@param testName : Nom du test que l'animal passe
     */
    public void createTestName(String testName){test=testName;}
    /**
     *Definit les resultats de l'animal pour le test
     *pourra être modifiee dans votre sous-classe en fonction de votre implementation pour la sauvegarde (separateurs entre chaque resultats)
     *@see #resultsToString()
     *@see #loadResult(ArrayList)
     *@see #createAsNew(String, String, Double, Double, Double, Boolean, String, String)
     *@see #stringToResult(String)
     *@param result_values : Resultats aux test (si effectues) sous forme de String
     */
    public void createResult(String result_values)
    {
	ArrayList result=stringToResult(result_values);
	loadResult(result);
    }
    /**
     *Definit les resultats que l'animal a eu aux test
     *fonction a implementer dans les sous-classes
     *@see Singe#loadResult(ArrayList)
     *@see Souris1#loadResult(ArrayList)
     *@see Souris2#loadResult(ArrayList)
     *@param results : Resultats aux test (si effectues) sous forme d'ArrayList
     */
    public void loadResult(ArrayList results){;} // a implementer dans les sous-classes
}
