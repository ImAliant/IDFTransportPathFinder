# Projet de GLA
Version 2024

## Description
Ceci est l'archetype de projet de Génie Logiciel Avancé (GLA).

Il s'agit d'un logiciel de planification d’itinéraire urbain. Implémenté
en Java, ce logiciel permettra de lire une carte des transports, puis, en donnant les coordonnées GPS de départ et d’arrivée, calculera le trajet le plus court (en temps, en distance, etc...) pour réaliser ce déplacement.

## Lancement du programme
Ce projet utilise [maven](https://maven.apache.org/) de Apache pour la gestion de construction.

Afin de compiler sans lancer les tets unitaires, éxecutez simplement
```
mvn install -DskipTests


```

Afin de compiler et lancer les tests, éxecutez simplement
```
mvn verify

```

Dans sa version initiale, le programme fournit est un simple code qui se lance en terminal ou en application graphique.

Une fois le programme compilé, vous trouverez un jar executable dans le dossier target. Au nom de jar près (version changeante), vous pourrez l'exécuter avec:
```
java -jar target/project-2024.1.0.0-SNAPSHOT-jar-with-dependencies.jar 
```

L'option de lancement `--info` causera l'affichage dans la console d'informations de l'application.

L'option de lancement `--gui` causera l'ouverture d'une fenêtre affichant le logo de l'Université de Paris.

L'option de lancement `--extractor` permettra de lancer un extracteur de données.

