AnimalCare
######################################
#               README               #
######################################
Version : 1.0.0



### Qu'est-ce qu'AnimalCare ?

C'est un programme permettant de gérer une étude menée sur plusieurs espèces,
chacune d'entre elles pouvant avoir des test différents a effectuer.




### Comment utiliser AnimalCare ?

- Execution :

Le programme se lance en utilisant une commande via le terminal (linux):
$java Menu

Le menu principal va ensuite s'afficher et vous permettre de rentrer vos animaux et/ou résultats.

- Notes :

Afin que le programme fonctionne correctement, il est necessaire d'avoir les fichiers Menu.class LesAnimaux.class Animaux.class ainsi que vos classes especes dans le même dossier.
Lors du chargement de vos données, veillez a entrer correctement le nom du fichier sans l'extension .txt

- Tests :

Deux fichiers de test sont disponibles, utilisant les sous-classes Singe, Souris1 et Souris2. Le fichier data1.txt contient les données sans résultats, le fichier data2.txt contient les résultats de fin de semaine.




### Comment modifier AnimalCare ?

Afin d'utiliser le programme pour vos propres animaux, il est possible de créer en quelques minutes de multiples classes en suivant les quelques directives suivantes :

- Dans le fichier LesAnimaux.java , modifier le constructeur LesAnimaux() et les méthodes ajouterAnimal(String NomEspece) et creationAnimal(String [] attr) de manière a ajouter les constructeurs correspondant a vos especes (ces espèces doivent hériter de la classe animal)

- Pour créer votre propre classe d'espece, référrez-vous aux classes déja présentes (Singe, Souris1, Souris2) afin de comprendre l'implémentation, un fichier Example.java vous permet aussi de créer pas à pas votre classe.




### Problemes connus :

- Plantage lors de l'affichage du bilan lorsque des résultats sont ajoutés mais pas dans toutes les espèces.

- Accès aux ajouts de poids/résultats après avoir choisit d'ajouter un animal et d'avoir quitter sans ajouter (pas de plantage)

- Pas de confirmation lors de la sauvegarde (réécrit sur l'ancien fichier)
