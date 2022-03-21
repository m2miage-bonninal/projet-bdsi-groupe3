
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import com.github.javafaker.Faker;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;

import model.Client;
import model.ClientAbonne;
import model.ClientNonAbonne;
import model.Station;
import repository.impl.*;

public class App {
    public static void main(String[] args) {
        EntityManager entityManager = Persistence.createEntityManagerFactory("JPA-HBM").createEntityManager();

        Scanner scanner = new Scanner(System.in);

        StationRepositoryImpl stationImpl = new StationRepositoryImpl(entityManager);
        ClientAbonneRepositoryImpl clientAbonneImpl = new ClientAbonneRepositoryImpl(entityManager);
        ClientRepositoryImpl clientImpl = new ClientRepositoryImpl(entityManager);

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
        int cas = scanner.nextInt();
        switch(cas) {
            case 1:
            System.out.println("Vous avez choisi d'emprunter un vélo. Etes-vous\n1 - Abonné\n2 - Non abonné"
                + "\nVotre choix : ");
                int etatAbonnement = scanner.nextInt();
                switch(etatAbonnement) {
                    case 1:
                    System.out.println("Vous êtes abonné à nos services. Veuillez entrer votre code : ");
                    ClientAbonne clientAbonne = clientAbonneImpl.abonneFromCode(scanner.next());
                    Emprunt.processusEmprunt();
                    case 2:
                    System.out.println("Veuillez entrer votre numéro de carte bancaire : ");
                    String numCB = scanner.next();
                    String codeADonner = null;
                    Client client = new ClientNonAbonne();
                    Emprunt.processusEmprunt();
                }
            case 2:
            System.out.println("Vous avez choisi de retourner un vélo. Veuillez entrer le code : ");

        }
    }
}
