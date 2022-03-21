package repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import model.Bornette;
import model.Client;
import model.ClientAbonne;
import model.ClientNonAbonne;
import model.HistoriqueVType;
import model.Location;
import model.Station;
import model.Trajet;
import model.Velo;
import model.enumeration.Etat;
import model.enumeration.Modele;

public class Fixtures {

    public static Bornette createBornette(Etat etat, Station station, Velo velo) {
        Bornette bornette = new Bornette();
        bornette.setEtat(etat);
        bornette.setStation(station);
        bornette.setVelo(velo);
        return bornette;
    }
    public static ClientNonAbonne createClientNonAbonne(Location location) {
        ClientNonAbonne clientNA = new ClientNonAbonne();
        clientNA.setCodeSecret("123");
        clientNA.setId("zob");
        clientNA.setLocations(location);
        clientNA.setNumeroCB("3810502195706645");
        return clientNA;
    }

    public static ClientAbonne createClientAbonne(Location location){
        ClientAbonne clientA = new ClientAbonne();
        clientA.setCodeSecret("234");
        clientA.setId("hephep");
        clientA.setLocations(location);
        return clientA;
    }

    public static Station createStation(List<HistoriqueVType> historiques, List<Bornette> bornettes){
        Station station = new Station();
        station.setBornette(bornettes);
        station.setHistoriques(historiques);
        return station;
    }

    public static Location createLocation(Client locataire, List<Trajet> trajets){
        Location location = new Location();
        location.setLocataire(locataire);
        location.setMontant((float)121);
        location.setTrajets(trajets);
        return location;
    }

    public static Trajet createTrajet(LocalDateTime dateheureDebut, Location location, Boolean prime, Station stationDebut, Station stationFin, Velo velo){
        Trajet trajet = new Trajet();
        trajet.setDuree(125);
        trajet.setDateheureDebut(dateheureDebut);
        trajet.setLocation(location);
        trajet.setPrime(prime);
        trajet.setStationDebut(stationDebut);
        trajet.setStationFin(stationFin);
        trajet.setVelo(velo);
        return trajet;
        }

    public static Velo createVelo(Bornette bornette, Etat etat, List<Location> listeLocation, Date misEnService, Modele model){
        Velo velo = new Velo();
        velo.setBornette(bornette);
        velo.setEtat(etat);
        velo.setListeLocation(listeLocation);
        velo.setMisEnService(misEnService);
        velo.setModele(model);
        return velo;
    }



}