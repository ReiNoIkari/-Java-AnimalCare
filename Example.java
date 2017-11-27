import java.io.*;
import java.util.*;
    /**
     *Sous-classe appartenant a <code>Animaux</code>, il s'agit d'une classe exemple.
     *Cette classe est içi pour permettre la creation facile de vos nouveaux tests
     *Chaque Sous-classe ayant des tests differents, il faut implementer directement içi les ajout et acces aux resultats setResult() et getResult() 
     *@author Adrien MENDES SANTOS
     *@author Rodolphe TWOREK
     *@see Animaux
     *@see LesAnimaux
     *@see Menu
     */
public class Example extends Animaux
{
    /**
     *L'attribut unique aux sous-classes de Animaux
     *il s'agit d'une ArrayList contenant les resultats aux tests
     */
    ArrayList <Double> result_Example = new ArrayList <Double>(); // C'est içi que vos résultats seront stockés, de base les données sont stockés en Double, il est cependant possible de passer l'arrayList en String ou Integers, mais pour cela certaines fonctions de la classe Animaux ne fonctionneront plus correctement.
    
    /**
     *Constructeur de la classe Example
     *La valeur test est modifiee, le test ici est le test des images
     *@see Animaux
     *@see Animaux#Animaux(String,Double,String)
     *@param name : Nom de l'animal
     *@param weight : poids de l'animal
     *@param sex : Sexe de l'animal
     */
    public Example(String name, double weight, String sex)
    {
	super(name,weight,sex);
	test = "Undefined";  // remplacer içi par le nom de votre test
    }

    /**
     *Affiche l'animal, ainsi que ses resultats quand il y en a
     *@see Animaux
     *@see Animaux#affiche()
     */
    public void affiche() 
    {
	super.affiche(); // A garder pour avoir l'affichage nom poids... et "Résultats :" .
	/*Implémenter içi la manière dont vos résultats doivent êtres affichés
	 *Vos résultats peuvent êtres des nombres ou des lettres, mais ils doivnet êtres stockés dans votre ArrayList de résultats
	 */
	if (result_Example.size() != 0)
	    {
		Double res=200000.0;
		int cpt=0;
		for (Iterator <Double> e = result_Example.iterator(); e.hasNext();)
		    {
			Double item = e.next();
			if (item < res){res=item;}
			if (item != 0) {System.out.print(item);}
			else {System.out.print("Echec");}
			if (e.hasNext()){System.out.print(" / ");}
		    }
		System.out.print("\n");
		System.out.println("Meilleure performance : "+res+" le : "+jours.get(cpt%5));
		cpt++;
	    }
	else {System.out.println("Pas de résultats");}
    }


    /**
     *Donne l'espece a laquelle appartient l'animal
     *NB : l'espece doit etre ecrite EXACTEMENT comme la cle du dico LesAnimaux
     *@see LesAnimaux
     *@see LesAnimaux#dico
     *@see Animaux#getEspece()
     *@return String : L'espece a laquelle appartient l'animal
     */
    public String getEspece(){return "Undefined";}// remplacer içi par le nom de l'espece. NB : L'écriture doit être IDENTIQUE a l'espece notée dans LesAnimaux.java
    //GESTION DES RESULTATS :
    /**
     *Ajoute les resultats du jour pour l'animal
     *@see #result_Example
     *@see Menu#saisie_double()
     */
    public void setResult()
    /*Implémenter içi l'affichage necessaire a la saisie de résultats et l'ajout de ces résultats dans votre ArrayList
     */
    {
	System.out.println("Combien d'essais avant de trouver la nourriture lors du test ? : (0 si echec)");	
	result_Example.add(Menu.saisie_double());	
    }
    /**
     *Lors du chargement d'un fichier de donnees, ajoute tout les resultats a l'animal
     *Il est possible de changer la methode de chargement/sauvegarde des datas en re-implementant Animaux#resultsToString() et Animaux#stringToResult(String) dans cette sous-classe
     *@see Animaux#resultsToString()
     *@see Animaux#stringToResult(String)
     *@see Animaux#loadResult(ArrayList)
     *@see Animaux#createResult(String)
     *@param results : L'ArrayList de resultats recuperee a partir d'un fichier .txt
     */
    public void loadResult(ArrayList results){result_Example=results;} // Uniquement pour le chargements de datas, il n'y a logiquement pas de changements a faire içi. Cette fonction sert définir directement les résultats de votre animal (non Verbose).
    public ArrayList getResult(){return result_Example;} // En théorie pas de changements içi.
    /**
     *calcule et renvoie la progression de votre animal
     *A implementer en fonction du calcul de la progression dans votre test
     *@see Animaux#progress
     *@return Double : Le progres en % de votre animal
     */
    public Double getProgress()
    /*
     *Implémenter içi la manière donc la progression sera calculée, le résultat sera directement retourné
     *L'exemple ci dessous montre un calcul entre la valeur de départ et d'arrivée, et calcule le pourcentage (en négatif si l'animal regresse). Les valeurs nulles (0) indiquent un échec et ne sont donc pas prises en compte.
     */
    {
	Double Depart=0.0;
	for (Iterator <Double> e = result_Example.iterator(); e.hasNext();)
	    {
		Double item = e.next();
		if (item != 0){Depart=item;break;}
	    }
	return (((Depart-result_Example.get(result_Example.size()-1))/Depart)*100);
    }
}
