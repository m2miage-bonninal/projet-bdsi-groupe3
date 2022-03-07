# TP JPA

Vous avez deux scéance de TP pour en venir à bout, vous être libre de continuer chez vous.

## Pre requis

* java 11+
* maven 3.6+
* docker (optionel) une base de données locale
* Accès Oracle (optionel) si vous souhaitez l'utiliser pour visualiser vos données.

# Le modèle

Voici le modèle de données.

![model](https://raw.githubusercontent.com/bordigoni/l3-miage-jpa/main/assets/images/model.png)

⚠️Dans ce modèle il manque des informations importantes
1. Il ne peut y avoir qu'**une** promotion ayant un nom donné par an.
2. Le nom d'une matière est unique
3. Les attributs en gras sont obligatoires
4. Vous noterez que chaque relation a une cardinalité.

   Certaines relations sont uni-directionnelles et d'autre non.

   Vous devrez en tenir compte dans votre mapping et dans vos tests.
5. Le champs `Subject.points` représente la pondération de la matière, ex. le nombre de point de l'EU.
6. Le champs `Grade.weight` représente le poids d'une note au sein d'une matière.
   
   Par example s'il y a trois notes pour une matière les poids peuvent être 0.2 0.3 et 0.5 ou encore 4, 4 et 2
8. Le champs `Grade.value` représente la note, elle n'est pas modifiable.
9. La relation `Student.grades` peut se faire en cascade pour l'enregistrement des données
   (pas besoin de sauver une note avant de l'ajouter à un étudiant)

## Ce qui est déjà fait

* **Le modèle est déjà codé sous forme de classes Java.** 
  
  `src/main/java` => `fr.uga.im2ag.l3.miage.db.model`
* **Les classes repository existent et sont à completer** 
 
  `src/main/java` => `fr.uga.im2ag.l3.miage.db.repository.impl`
* **Les classes de tests existent et sont à completer** 

  `src/test/java` =>`fr.uga.im2ag.l3.miage.db.repository`
  

## Ce que vous devez faire

Le but est de :

1. Mapper les classes sur la base, grâce aux annotations JPA. Vous devrez respecter toutes les contraintes décrites du modèle (il pourrait y en avoir d'autre mais il y bien assez à faire).
1. Coder puis tester les Repository avec JUnit grâce une base de données en mémoire (H2). Vous n'avez acune configuration à faire pour la base de données, tout fonctionne sans installation supplémentaire. En revanche vous ne pourrez pas visualiser vos données (à moins de les afficher dans la console).

**Partout ou il a y un methode à implémenter il y a un TODO** 

### le mapping
1. Cloner ou télécharger ce projet : `git clone https://github.com/bordigoni/l3-miage-jpa.git`
    * Pour ceux sont à l'aise avec github et ont un compte, vous pouvez forker et me donner accès à votre repository pour le rendu. Vous devrez alors mettre votre nom dans le README
2. Dans un terminal, à la racine projet, lancez la commande `mvn clean install -DskipTests`
3. Importer le projet dans Eclipse "Import Maven Project"
4. Prenez le temps de regarder les classes et le test existant pour comprendre comment faire l'implémentation. Toutes les méthodes à implémenter sont présentes mais vides.
5. Annoter les classes pour faire le mapping
    * Les classes sont déjà déclarées dans `persistence.xml`
    * Vous être libre dans le choix de la stratégie pour mapper les héritages.
    * Conseil : commentez les relations si besoin et les ajouter de nouveau une à une en les annotant avec `@OneToMany`, `@ManyToOne` etc. afin qu'elles deviennent persistantes.

      Faites le mapping dans l'ordre suivant (dépendences inverse)
        * Subject
        * Grade
        * GraduationClass (vous pouvez commenter la relation vers students dans un premier temps)
        * Person
        * Student
        * Teacher
6. Exécuter `SubjectTest` (partiellement codé), le test devrait passer si le mapping fonctionne.

   ⚠️ certaines erreurs dans le mapping ne feront pas nécessairement planter votre test, vérifier les logs, il ne doit plus y avoir d'erreur.

### Repository et tests
Une fois votre mapping fait vous passez à l'implémentation des Repository (aussi connu sous le nom de DAO)

Voici l'ordre dans lequel l'implémentation doit être réalisée pour optimiser les chances de succès :
* SubjectRepositoryImpl + Test
* GradeRepositoryImpl + Test
* GraduationClassRepositoryImpl + Test
* PersonRepositoryImpl + Test
* StudentRepositoryImpl + Test
* TeacherRepositoryImpl + Test

1. Implémeter un repository
* Implementer une classe `*RepositoryImpl`
* Implementer les tests du Repository (une méthode de test par méthode de Repository).
  Quelques conseils :
    1. Commitez la transaction pour executer les requêtes en base
    2. Pensez à appeler `entityManager.detach(...)` pour retirer les entités avant de les charger de nouveau
    3. Effectuer des assertions simples, mais suffisantes pour valider les opérations.
* Utiliser la classe `Fixtures` pour créer des objets prêts à devenir persistant, les données qu'ils contiennent sont générées aléatoirement.
* Sur l'ensemble de vos méthodes de test, vous devez appeler toutes les méthodes de votre repository.
2. Recommencez pour chaque Repository

Good luck! 🍀

## Rendu

* Mettre votre nom en haut de ce README
* Zipper le répertoire `src` + README.md puis le nommer `l3-miage-jpa-prenom1-nom1-prenom2-nom2.zip`
* Déposer sur le moodle.
* Si vous souhaitez ajouter des commentaires, merci de le faire dans ce README (sections commentaires ci-dessous).

Les sources rendues doivent compiler et les tests doivent passer.
C'est-à-dire que vous ne devez pas ajouter de dépendances dans `pom.xml`, vous êtes en revanche libres d'ajouter des classes, mais ça ne devrait pas être nécessaire.

**Mieux vaut une implémentation partielle, mais bien testé qu'une implémentation complète, peu ou mal testé, car il sera difficile d'évaluer sa qualité.**

## Commentaires

Section en option si vous souhaitez justifier certains de vos choix dans votre implémentation. 

