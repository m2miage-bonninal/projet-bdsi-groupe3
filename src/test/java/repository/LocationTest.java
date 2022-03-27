package repository;
import model.Location ;
import model.Station;
import model.Bornette;
import model.Client;
import model.enumeration.Etat;
import model.enumeration.Modele;
import repository.api.BornetteRepository;
import repository.api.ClientRepository;
import repository.api.LocationRepository ;
import repository.api.StationRepository;
import repository.api.TrajetRepository;
import repository.api.VeloRepository;
import repository.impl.LocationRepositoryImpl;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.ArrayList;
public class LocationTest extends Base {
    LocationRepository locationRepository;
    ClientRepository clientRepository ;
    TrajetRepository trajetRepository ;
    VeloRepository veloRepository ;
    BornetteRepository bornetteRepository;
    StationRepository stationRepository ;

    @BeforeEach
    void before() {
        locationRepository = daoFactory.newLocationRepository(entityManager);
        trajetRepository = daoFactory.newTrajetRepository(entityManager);
        clientRepository  = daoFactory.newClientRepository(entityManager);
        veloRepository = daoFactory.newVeloRepository(entityManager) ;
        bornetteRepository = daoFactory.newBornetteRepository(entityManager);
        stationRepository = daoFactory.newStationRepository(entityManager);


    }

    @AfterEach
    void after() {
        if (entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().rollback();
        }
    }

    @Test
    void shouldSaveLocation(){
        // null puis des setter !!!!
        
        final var client1 = Fixtures.createClientAbonne(null);
        final var station1 = Fixtures.createStation(null, null);
        final var bornette1 = Fixtures.createBornette(Etat.OK, station1, null) ; // LE SET EST FAIT 
        final var bornette2 = Fixtures.createBornette(Etat.HS,station1,null) ;
        final var velo1 = Fixtures.createVelo(bornette1, Etat.OK, null, null, Modele.VTT) ;
        bornette1.setVelo(velo1); // ON SET LE VELO A LA BORNETTE
        List<Bornette>  lbornettes = new ArrayList<>();
        lbornettes.add(bornette1);
        lbornettes.add(bornette2);
        station1.setBornette(lbornettes); // ON PEUT LAISSER le premier null 

        LocalDateTime now = LocalDateTime.now() ;
        final var trajet1 = Fixtures.createTrajet(now, null, null, station1, null, velo1);
        
        final var location1 = Fixtures.createLocation(client1, null) ; // deuxieme est historique donc peut rester null
        trajet1.setLocation(location1);
        trajet1.setStationDebut(station1); // DEUXIEME STATION PEUT RESTER VIDE

        
        entityManager.getTransaction().begin();
        clientRepository.save(client1);
        stationRepository.save(station1);
        bornetteRepository.save(bornette1);
        bornetteRepository.save(bornette2);
        veloRepository.save(velo1);
        trajetRepository.save(trajet1);
        locationRepository.save(location1);
        Long nombreid = location1.getId();
        
        // ON DETACHE LES TRUCS
        entityManager.getTransaction().commit();
        entityManager.detach(client1);
        entityManager.detach(station1);
        entityManager.detach(bornette1);
        entityManager.detach(bornette2);
        entityManager.detach(velo1);
        entityManager.detach(trajet1);
        entityManager.detach(location1);


        var plocation = locationRepository.findById(nombreid) ;
        assertThat(plocation.getMontant()).isEqualTo(location1.getMontant());
        assertThat(plocation.getTrajets().size()).isEqualTo(location1.getTrajets().size());
    }
    
    @Test
    void shouldgetLocation(){
        final var client1 = Fixtures.createClientAbonne(null);
        final var station1 = Fixtures.createStation(null, null);
        final var bornette1 = Fixtures.createBornette(Etat.OK, station1, null) ; // LE SET EST FAIT 
        final var bornette2 = Fixtures.createBornette(Etat.HS,station1,null) ;
        final var velo1 = Fixtures.createVelo(bornette1, Etat.OK, null, null, Modele.VTT) ;
        bornette1.setVelo(velo1); // ON SET LE VELO A LA BORNETTE
        List<Bornette>  lbornettes = new ArrayList<>();
        lbornettes.add(bornette1);
        lbornettes.add(bornette2);
        station1.setBornette(lbornettes); // ON PEUT LAISSER le premier null 

        LocalDateTime now = LocalDateTime.now() ;
        final var trajet1 = Fixtures.createTrajet(now, null, null, station1, null, velo1);
        
        final var location1 = Fixtures.createLocation(client1, null) ; // deuxieme est historique donc peut rester null
        trajet1.setLocation(location1);
        trajet1.setStationDebut(station1); // DEUXIEME STATION PEUT RESTER VIDE
        List<Location> llocations = new ArrayList<>();
        llocations.add(location1);
        client1.setLocations(llocations);

        
        entityManager.getTransaction().begin();
        clientRepository.save(client1);
        stationRepository.save(station1);
        bornetteRepository.save(bornette1);
        bornetteRepository.save(bornette2);
        veloRepository.save(velo1);
        trajetRepository.save(trajet1);
        locationRepository.save(location1);
        Long nombreid = location1.getId();
        Long clientid = client1.getId();
        
        // ON DETACHE LES TRUCS
        entityManager.getTransaction().commit();
        entityManager.detach(client1);
        entityManager.detach(station1);
        entityManager.detach(bornette1);
        entityManager.detach(bornette2);
        entityManager.detach(velo1);
        entityManager.detach(trajet1);
        entityManager.detach(location1);

        var plocation = locationRepository.locationEnCoursFromClient(client1);
        assertThat(plocation.getLocataire()).isEqualTo(location1.getLocataire());



    }



    
}
