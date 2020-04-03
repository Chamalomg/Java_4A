# Java_4A
Cours de java  sur une appli web en MVC

Comment ouvrir le projet ? 

Importer le projet sur Eclipse, click droit sur RentManager dans l'arbre de fichier, run as > Maven Built
Ouvrir un navigateur et aller sur http://localhost:8080/RentManager/home


Objectifs atteints : 

Lister clients, véhicules, réservation
Supprimer clients, véhicules, réservation
une voiture doit avoir un modèle et un constructeur, son nombre de place doit être compris entre 2 et 9
Un client de moins de 18 ans ne peut pas être créé
Un client dont le nom et prenom est inférieur à 3 lettres ne peut pas être créé
Ajouter Client, ajouter véhicule

Peut mieux faire:

Pas de popup pour faire comprendre les erreurs
Ajouter une navigation dès /RentManager/home via les pictogrammes, liens à ajouter 
Détails Client et Véhicules ne contiennent pas toutes les informations,
il faudrait les transmettre en appelant les service dans doGet de DetailServler


Objectifs non atteints :

Les modifications, il faut donc supprimer l'objet puis le recréer
Si client ou véhicule supprimé, il faut supprimer aussi toutes ses réservations : essai de 
code dans DeleteVehiculeServlet et DeleteClientServlet, non concluant
Véhicule : ne peut pas être réservé 2 fois le même jour
Véhicule : ne peut pas être réservé plus de 30 jours de suite sans pause
Ajout réservation
Détail réservation
SI bug sur le site, par exemple, un véhicule avec 10 places, 
il faut un message d'erreur avec possibilité de revenir en arrière ou au menu principal
