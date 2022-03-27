import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;

import model.*;
import repository.impl.LocationRepositoryImpl;
import repository.impl.TrajetRepositoryImpl;
import repository.impl.VeloRepositoryImpl;

public class Emprunt {
    
    public static void processusEmprunt(EntityManager entityManager, Client client, Station station, VeloRepositoryImpl veloImpl, LocationRepositoryImpl locationImpl) {

        TrajetRepositoryImpl trajetImpl = new TrajetRepositoryImpl(entityManager);
        Scanner scanner = new Scanner(System.in);

        Location locationEnCours = null;
        if (client.verifLocationEnCours()){
            locationEnCours = locationImpl.locationEnCoursFromClient(client);
        }
        else {
            locationEnCours = new Location(client);
        }
        
        /*
        List<Velo> velosAtStation(Station station);
        // /!\ non endommagés /!\
        // Affiche le nb de vélos par modèle 
        // Choix nb de vélos de ce modèle à louer
        List<Bornette> bornetteAvecModele(Station station, ModeleVelo modele, int nbVelo);
        // renvoie les bornettes des nbVelo premiers vélos du modèle choisit à la station.
        // ou bien : renvoie les numéros des bornes.

        // Afficher les numéros des vélos empruntés en guise de la simulation (à partir de la liste des bornettes)

        MODIF : Nous avons décidé de seulement afficher tous les vélos en bon état à la station en premier lieu, 
        pour se simplifier la tâche. Le client choisit un vélo à la fois, mais peut quand même en louer plusieurs.
        Ces plusieurs vélos qu'il a loués font partie de la même location (si une location est en cours, chaque vélo
        loué se rajoute à la location.)
        */

        List<Velo> velos = veloImpl.velosAtStation(station);
        System.out.println("Les vélos présents à la station " + station.getAdresse() + " :\n");
        int i = 1;
        for(Velo v : velos) {
            System.out.println(i + " - Velo : " + v.getModele() + v.getNumero());
            i++;
        }
        System.out.println("Choisissez le vélo à louer :\n");
        Velo veloChoisi = velos.get(scanner.nextInt()-1);

        Trajet trajet = new Trajet(locationEnCours, station);
        trajet.setVelo(veloChoisi);

        System.out.println("Vous avez loué le vélo de modèle " + veloChoisi.getModele() + " numéro " + veloChoisi.getNumero()
            + "\nVous pouvez le récupérer à la bornette numéro " + veloChoisi.getBornette().getNumero());
        veloChoisi.getBornette().setVelo(null);
        veloChoisi.setBornette(null);

            trajetImpl.save(trajet);
            locationImpl.save(locationEnCours);

    }
}
