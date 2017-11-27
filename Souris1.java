import java.io.*;
import java.util.*;
    /**
     *Sous-classe appartenant a <code>Animaux</code>, il s'agit d'une souris.
     *Les souris du groupe 1 ont des epreuves differentes des autres especes
     *Chaque Sous-classe ayant des tests differents, il faut implementer directement içi les ajout et acces aux resultats setResult() et getResult() 
     *@author Adrien MENDES SANTOS
     *@author Rodolphe TWOREK
     *@see Animaux
     *@see LesAnimaux
     *@see Menu
     */
public class Souris1 extends Animaux
{
    /**
     *L'attribut unique aux sous-classes de Animaux
     *il s'agit d'une ArrayList contenant les resultats aux tests
     */
    ArrayList <Double> result_labyrinthe = new ArrayList <Double>();
    /**
     *Constructeur de la classe Souris1
     *La valeur test est modifiee, le test ici est le test des images
     *@see Animaux
     *@see Animaux#Animaux(String,Double,String)
     *@param name : Nom de l'animal
     *@param weight : poids de l'animal
     *@param sex : Sexe de l'animal
     */
    public Souris1(String name, double weight, String sex)
    {
	super(name,weight,sex);
	test ="Labyrinthe";
    }
    
    /**
     *Affiche l'animal, ainsi que ses resultats quand il y en a
     *@see Animaux
     *@see Animaux#affiche()
     */
    public void affiche()
    {
	super.affiche();
	if (result_labyrinthe.size() != 0)
	    {
		Double res=200000.0;
		int cpt=0;
		int day=0;
		for (Iterator <Double> e = result_labyrinthe.iterator(); e.hasNext();)
		    {
			Double item = e.next();
			if (item < res){res=item;day=cpt;}
			System.out.print(item);
			if (e.hasNext()){System.out.print(" / ");}
			cpt++;
		    }
		System.out.print("\n");
			System.out.println("Meilleure performance : "+res+" le : "+jours.get(day%5));
	    }
	else {System.out.println("Pas de resultats");}
    }


    /**
     *Donne l'espece a laquelle appartient l'animal
     *NB : l'espece doit etre ecrite EXACTEMENT comme la cle du dico LesAnimaux
     *@see LesAnimaux
     *@see LesAnimaux#dico
     *@see Animaux#getEspece()
     *@return String : L'espece a laquelle appartient l'animal
     */
    public String getEspece(){return "Souris G1";}

    
    //GESTION DES RESULTATS :
    /**
     *Ajoute les resultats du jour pour l'animal
     *@see #result_labyrinthe
     *@see Menu#saisie_double()
     */
    public void setResult()
    {
	System.out.println("Quel temps faut-il a "+getName()+" pour parcourir le labyrinthe (en Secondes) :");
	result_labyrinthe.add(Menu.saisie_double());
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
    public void loadResult(ArrayList results){result_labyrinthe=results;} // Uniquement pour le chargements de datas
    /**
     *Renvoie l'arraylist contenant vos resultats
     *@return ArrayList : votre ensemble de resultats
     */
    public ArrayList getResult(){return result_labyrinthe;}
    /**
     *calcule et renvoie la progression de votre animal
     *A implementer en fonction du calcul de la progression dans votre test
     *@see Animaux#progress
     *@return Double : Le progres en % de votre animal
     */
    public Double getProgress()	
    {
	
	return (((result_labyrinthe.get(0)-result_labyrinthe.get(result_labyrinthe.size()-1))/result_labyrinthe.get(0))*100);
    }
}
