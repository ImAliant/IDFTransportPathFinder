# CrazyTrip

Projet de GLA (2024) par le groupe "Les Génies"

## Description

Ce projet s'agit d'un logiciel de planification d’itinéraire urbain. Implémenté en Java qui permettra de lire une carte des transports, puis, en donnant les coordonnées GPS de départ et d’arrivée, calculera un trajet pour réaliser ce déplacement.

## Lancement du programme
Ce projet utilise [maven](https://maven.apache.org/) de Apache pour la gestion de construction.

Afin de compiler sans lancer les tests unitaires, éxecutez simplement :
```
mvn install -DskipTests


```

Afin de compiler et lancer les tests, éxecutez simplement :
```
mvn verify

```

Une fois le programme compilé, vous trouverez un jar executable dans le dossier target. Au nom de jar près (version changeante), vous pourrez l'exécuter avec:
```
java -jar target/crazytrip-2024.1.0.0-SNAPSHOT-jar-with-dependencies.jar
```

L'option de lancement `--info` causera l'affichage dans la console d'informations de l'application.

L'option de lancement `--gui` causera l'ouverture d'une fenêtre affichant le logo de l'Université de Paris.

L'option de lancement `--extractor` permettra de lancer un extracteur de données.

