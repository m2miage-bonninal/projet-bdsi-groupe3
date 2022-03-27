import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import model.*;
import model.enumeration.Etat;
import model.enumeration.Sexe;
import repository.impl.*;

public class App {
    public static void main(String[] args) {
        EntityManager entityManager = Persistence.createEntityManagerFactory("JPA-HBM").createEntityManager();

        Scanner scanner = new Scanner(System.in);

        entityManager.getTransaction().begin();

        StationRepositoryImpl stationImpl = new StationRepositoryImpl(entityManager);
        ClientAbonneRepositoryImpl clientAbonneImpl = new ClientAbonneRepositoryImpl(entityManager);
        ClientRepositoryImpl clientImpl = new ClientRepositoryImpl(entityManager);
        ClientNonAbonneRepositoryImpl clientNonAbonneImpl = new ClientNonAbonneRepositoryImpl(entityManager);
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
                Emprunt.processusEmprunt(entityManager, clientAbonne, stationChoisie, veloImpl, locationImpl);
                break;

                case 2:
                System.out.println("Veuillez entrer votre numéro de carte bancaire : ");
                String numCB = scanner.next();
                String codeADonner = Client.generateCode(clientImpl);
                ClientNonAbonne clientNonAbonne = new ClientNonAbonne(numCB, codeADonner);
                    clientNonAbonneImpl.save(clientNonAbonne);
                System.out.println("Mémoriser le code suivant pour pouvoir rendre le vélo ultérieurement : " + clientNonAbonne.getCodeSecret());
                Emprunt.processusEmprunt(entityManager, clientNonAbonne, stationChoisie, veloImpl, locationImpl);
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
            trajetFromVeloAccroche.setStationFin(stationChoisie);

            trajetFromVeloAccroche.setDuree((int) trajetFromVeloAccroche.getDateheureDebut().until(LocalDateTime.now(), ChronoUnit.MINUTES));
            trajetFromVeloAccroche.setPrimeFromDate(LocalDateTime.now());
            

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
            // récupérer les données, faire appel au constructeur
            System.out.println("Quel est votre nom ?");
            String nom = scanner.next();

            System.out.println("Quel est votre prénom ?");
            String prenom = scanner.next();

            System.out.println("Sélectionnez votre genre :\n1 - Homme\n2 - Femme\n3 - Non Binaire");
            int sexe = scanner.nextInt();
            Sexe genre = null;
            switch(sexe) {
                case 1:
                genre = Sexe.M;
                break;

                case 2:
                genre = Sexe.F;
                break;

                case 3:
                genre = Sexe.NB;
                break;

                default :
                break;
            }

            System.out.println("Quel est votre date de naissance ? (format jj/mm/aaaa)");
            String ddn = scanner.next();

            System.out.println("Quel est votre adresse ?");
            String adresse = scanner.next();

            System.out.println("Veuillez entrer votre numéro de carte bancaire : ");
            String numCBAbonne = scanner.next();

            String codeAbonneDonne = Client.generateCode(clientImpl);
            ClientAbonne clientNouvAbonne = new ClientAbonne(numCBAbonne, codeAbonneDonne, nom, prenom, ddn, adresse, genre);
                clientAbonneImpl.save(clientNouvAbonne);

            System.out.println(clientNouvAbonne.getNom() + " " + clientNouvAbonne.getPrenom() 
                + ", votre abonnement a été créé avec succès. Il commence le " + clientNouvAbonne.getDateSouscription()
                + " et termine dans un an."); // s'il est possible de récupérer la date de fin de souscription, 
                                              // en ajoutant 1 an à la date de souscription, au lieu d'écrire "dans un an".
            System.out.println("Pour vous authentifier dans nos stations, veuillez mémoriser le code suivant : " + clientNouvAbonne.getCodeSecret());
            break;

            default:
            System.out.println("Choix invalide");
            break;

        }
        
        entityManager.getTransaction().commit();
    }
}
