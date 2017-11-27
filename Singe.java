import java.io.*;
import java.util.*;
    /**
     *Sous-classe appartenant a <code>Animaux</code>, il s'agit d'un singe.
     *Les singes ont des epreuves differentes des autres especes
     *Chaque Sous-classe ayant des tests differents, il faut implementer directement içi les ajout et acces aux resultats setResult() et getResult() 
     *@author Adrien MENDES SANTOS
     *@author Rodolphe TWOREK
     *@see Animaux
     *@see LesAnimaux
     *@see Menu
     */
public class Singe extends Animaux
{
    /**
     *L'attribut unique aux sous-classes de Animaux
     *il s'agit d'une ArrayList contenant les resultats aux tests
     */
    ArrayList <Double> result_image = new ArrayList <Double>();
    /**
     *Constructeur de la classe Singe
     *La valeur test est modifiee, le test ici est le test des images
     *@see Animaux
     *@see Animaux#Animaux(String,Double,String)
     *@param name : Nom de l'animal
     *@param weight : poids de l'animal
     *@param sex : Sexe de l'animal
     */
    public Singe(String name, Double weight, String sex)
    {
	super(name,weight,sex);
	test ="Images";
    }
    
    /**
     *Affiche l'animal, ainsi que ses resultats quand il y en a
     *@see Animaux
     *@see Animaux#affiche()
     */
    public void affiche()
    {
	super.affiche();
	if (result_image.size() != 0)
	    {
		Double res=200000.0;
		int cpt=0;
		int day=0;
		for (Iterator <Double> e = result_image.iterator(); e.hasNext();)
		    {
			Double item = e.next();
			if (item < res){res=item;day=cpt;}
			if (item != 0) {System.out.print(item);}
			else {System.out.print("Echec");}
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
    public String getEspece(){return "Singes";}


    // GESTION DES RESULTATS :
    /**
     *Ajoute les resultats du jour pour l'animal
     *Les singes ont 5 resultats par jour (5 tests en 1h), on en fait donc la moyenne avant d'ajouter le resultat final
     *NB : la valeur 0 est compte ici comme un echec et n'est pas pris en compte dans les resultats (La progression ne se calcule que dans la reussite
     *@see #result_image
     *@see Menu#saisie_double()
     */
    public void setResult()
    {
	Double somme=0.0;
	for (int i=1;i<6;i++)
	    {
		System.out.println("Combien d'essais avant de trouver l'image lors du test "+i+" ? : (0 si echec)");
		Double ans = Menu.saisie_double();
		if (ans !=0){somme+=ans;}
	    }
	result_image.add(somme/5);
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
    public void loadResult(ArrayList results){result_image=results;} // Uniquement pour le chargements de datas
    /**
     *Renvoie l'arraylist contenant vos resultats
     *@return ArrayList : votre ensemble de resultats
     */
    public ArrayList getResult(){return result_image;}
    /**
     *calcule et renvoie la progression de votre animal
     *A implementer en fonction du calcul de la progression dans votre test
     *@see Animaux#progress
     *@return Double : Le progres en % de votre animal
     */
    public Double getProgress()
    {
	Double Depart=0.0;
	for (Iterator <Double> e = result_image.iterator(); e.hasNext();)
	    {
		Double item = e.next();
		if (item != 0){Depart=item;break;}
	    }
	return (((Depart-result_image.get(result_image.size()-1))/Depart)*100);
    }
}
