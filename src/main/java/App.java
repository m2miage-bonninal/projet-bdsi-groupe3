
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import model.*;
import model.enumeration.Etat;
import repository.impl.*;

public class App {
    public static void main(String[] args) {
        EntityManager entityManager = Persistence.createEntityManagerFactory("JPA-HBM").createEntityManager();

        Scanner scanner = new Scanner(System.in);

        StationRepositoryImpl stationImpl = new StationRepositoryImpl(entityManager);
        ClientAbonneRepositoryImpl clientAbonneImpl = new ClientAbonneRepositoryImpl(entityManager);
        ClientRepositoryImpl clientImpl = new ClientRepositoryImpl(entityManager);
        LocationRepositoryImpl locationImpl = new LocationRepositoryImpl(entityManager);
        VeloRepositoryImpl veloImpl = new VeloRepositoryImpl(entityManager);
        TrajetRepositoryImpl trajetImpl = new TrajetRepositoryImpl(entityManager);

        List<Station> stations = stationImpl.getAll();
        System.out.println("Veuillez choisir une station : ");
        int v = 1;
        for(Station s : stations) {
            System.out.println(v + " - Station : " + s.getAdresse());
            v++;
        }
        Station stationChoisie = stations.get(scanner.nextInt()-1); // à gérer l'erreur où le numéro est invalide

        System.out.println("Bienvenue à la station : " + stationChoisie.getAdresse() 
            + " !\nQue souhaitez-vous faire ?"
            + "\n1 - Emprunt de vélo\n2 - Retour de vélo\n3 - Créer un abonnement\nVeuillez choisir une option :");
        int option = scanner.nextInt();

        switch(option) {

            case 1:
            System.out.println("Vous avez choisi d'emprunter un vélo. Etes-vous\n1 - Abonné\n2 - Non abonné"
                + "\nVotre choix : ");
            int etatAbonnement = scanner.nextInt();
            switch(etatAbonnement) {

                case 1:
                System.out.println("Vous êtes abonné à nos services. Veuillez entrer votre code : ");
                ClientAbonne clientAbonne = clientAbonneImpl.abonneFromCode(scanner.next());
                Emprunt.processusEmprunt();
                break;

                case 2:
                System.out.println("Veuillez entrer votre numéro de carte bancaire : ");
                String numCB = scanner.next();
                String codeADonner = Client.generateCode(clientImpl);
                ClientNonAbonne clientNonAbonne = new ClientNonAbonne(numCB, codeADonner);
                Emprunt.processusEmprunt();
                break;

                default:
                System.out.println("Choix invalide");
                break;
            }
            break;

            case 2:
            // vérifier qu'il y a au moins une borne de libre pour que le client raccroche le vélo 
            // avant de permettre au client de choisir cette option ?
            System.out.println("Vous avez choisi de retourner un vélo. Veuillez entrer le code : ");
            Client client = clientImpl.clientFromCode(scanner.next());
            Location locationEnCours = locationImpl.locationEnCoursFromClient(client);

            System.out.println("Veuillez raccrocher votre vélo à une des bornes libres.");

            System.out.println("(Pour la simulation, rentrez le numéro du vélo à rendre\n" 
                + "Par rendre, on veut dire \"dont l'accrochage à la borne a été détecté\" : )");
            Long numVeloAccroche = scanner.nextLong();
            Velo veloAccroche = veloImpl.findById(numVeloAccroche);
            Trajet trajetFromVeloAccroche = trajetImpl.trajetFromVelo(veloAccroche);

            System.out.println("(Pour la simulation, rentrez le numéro de la bornette libre où vous attachez le vélo.)");
            List<Bornette> bornettesLibres = stationChoisie.getBornettesLibres();
            System.out.println("Les bornettes libres : ");
            int w = 1;
            for(Bornette b : bornettesLibres) {
                System.out.println(w + " - Bornette : " + b.getNumero());
                w++;
            }
            Bornette bornetteChoisie = bornettesLibres.get(scanner.nextInt()-1); // à gérer l'erreur où le numéro est invalide
            
            bornetteChoisie.setVelo(veloAccroche);
            veloAccroche.setBornette(bornetteChoisie);

            trajetFromVeloAccroche.setDuree((int) trajetFromVeloAccroche.getDateheureDebut().until(LocalDateTime.now(), ChronoUnit.MINUTES));
            trajetFromVeloAccroche.setPrimeFromDate(LocalDateTime.now());
            trajetFromVeloAccroche.setStationFin(stationChoisie);

            System.out.println("Veuillez rentrer l'état du vélo que vous venez de raccrocher :\n1 - OK\n2 - En panne\n"
                + "Etat : ");
            int etat = scanner.nextInt();

            switch(etat) {

                case 1: 
                veloAccroche.setEtat(Etat.OK);
                DebitRetour.processusDebitMontant(locationEnCours, trajetFromVeloAccroche);
                break;

                case 2:
                veloAccroche.setEtat(Etat.HS);
                if (trajetFromVeloAccroche.getDuree() >= 5) {
                    System.out.println("Votre trajet avec le vélo en panne a duré plus de 5 minutes, vous serez débité.e de la durée.");
                    DebitRetour.processusDebitMontant(locationEnCours, trajetFromVeloAccroche);
                }
                else {
                    System.out.print("Merci de nous avoir informé de l'état du vélo.\n"
                        + "Votre trajet avec le vélo en panne a duré moins de 5 minutes, vous ne serez pas débité.e.");
                }
                break;

                default:
                System.out.println("Choix invalide");
                break;
            }
            break;

            case 3:
            System.out.println("Vous avez choisi de créer un abonnement.");
            break;

            default:
            System.out.println("Choix invalide");
            break;

        }
    }
}
