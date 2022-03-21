package repository;
import java.util.ArrayList;
import java.util.List;
import model.Bornette;
import model.Station;
import model.Velo;
import model.enumeration.Etat;
import model.enumeration.Modele;
import repository.api.BornetteRepository ;
import repository.impl.BornetteRepositoryImpl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import repository.api.StationRepository;
import repository.api.VeloRepository;
public class BornetteTest extends Base {
    BornetteRepository BornetteRepository;

    @BeforeEach
    void before() {
        BornetteRepository = daoFactory.newBornetteRepository(entityManager);
    }

    @AfterEach
    void after() {
        if (entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().rollback();
        }
    }

    @Test
    void saveBornette(){
        Station station = Fixtures.createStation(null, null);
        Velo velo = Fixtures.createVelo(null, Etat.OK, null, null, Modele.ROUTE);
        final Bornette bornette = Fixtures.createBornette(Etat.OK, station, velo);
        entityManager.getTransaction().begin();
        entityManager.persist(velo);
        entityManager.persist(station);
        BornetteRepository.save(bornette);
        entityManager.getTransaction().commit();
        entityManager.detach(bornette);
        
        Bornette pBornette = BornetteRepository.findById(bornette.getNumero());
        assertThat(pBornette).isNotNull().isNotSameAs(bornette);
        assertThat(pBornette.getNumero()).isEqualTo(bornette.getNumero());
    }

    @Test
    void bornetteAvecModele(){
        VeloRepository veloRepository = daoFactory.newVeloRepository(entityManager);
        StationRepository stationRepository = daoFactory.newStationRepository(entityManager);

                
        Velo velo1 = Fixtures.createVelo(null, Etat.HS, null, null, Modele.ROUTE);
        Velo velo2 = Fixtures.createVelo(null, Etat.OK, null, null, Modele.ROUTE);
        Velo velo3 = Fixtures.createVelo(null, Etat.OK, null, null, Modele.VTT);
        Velo velo4 = Fixtures.createVelo(null, Etat.OK, null, null, Modele.ROUTE);
        
        Station station1 = Fixtures.createStation(null, null);
        Station station2 = Fixtures.createStation(null, null);

        final Bornette bornette1 = Fixtures.createBornette(Etat.OK, station1, velo1);
        final Bornette bornette2 = Fixtures.createBornette(Etat.OK, station1, velo2);
        final Bornette bornette3 = Fixtures.createBornette(Etat.OK, station1, velo3);
        final Bornette bornette4 = Fixtures.createBornette(Etat.OK, station2, velo4);
        
        velo1.setBornette(bornette1);
        velo2.setBornette(bornette2);
        velo3.setBornette(bornette3);
        velo4.setBornette(bornette4);

        List<Bornette> list = new ArrayList<>();
        list.add(bornette1);
        list.add(bornette2);
        list.add(bornette3);
        
        station1.setBornette(list);
        
        list = new ArrayList<>();
        list.add(bornette4);
        station2.setBornette(list);
        
        entityManager.getTransaction().begin();
        veloRepository.save(velo1);
        veloRepository.save(velo2);
        veloRepository.save(velo3);
        veloRepository.save(velo4);
        stationRepository.save(station1);
        stationRepository.save(station2);
        BornetteRepository.save(bornette1);
        BornetteRepository.save(bornette2);
        BornetteRepository.save(bornette3);
        BornetteRepository.save(bornette4);

        entityManager.getTransaction().commit();
        
        entityManager.detach(bornette1);
        entityManager.detach(bornette2);
        entityManager.detach(bornette3);
        entityManager.detach(bornette4);
        
        entityManager.detach(station1);
        entityManager.detach(station2);
        
        entityManager.detach(velo1);
        entityManager.detach(velo2);
        entityManager.detach(velo3);
        entityManager.detach(velo4);
        
        Bornette pBornette2 = BornetteRepository.findById(bornette2.getNumero());

        list = new ArrayList<>();
        list.add(pBornette2);
        assertThat(BornetteRepository.bornetteAvecModele(station1, Modele.ROUTE, 1))
                .isEqualTo(list);
    }
    
}
