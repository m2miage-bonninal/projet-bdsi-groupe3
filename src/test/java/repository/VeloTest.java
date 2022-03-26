package repository;
import java.util.ArrayList;
import java.util.List;
import model.Bornette;
import model.Station;
import model.Velo ;
import model.enumeration.Etat;
import model.enumeration.Modele;
import static org.assertj.core.api.Assertions.assertThat;
import repository.api.VeloRepository ;
import repository.impl.VeloRepositoryImpl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.api.BornetteRepository;
import repository.api.StationRepository;

public class VeloTest extends Base {
    VeloRepository veloRepository;

    @BeforeEach
    void before() {
        veloRepository = daoFactory.newVeloRepository(entityManager);
    }

    @AfterEach
    void after() {
        if (entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().rollback();
        }
    }
    
    @Test
    void saveVelo(){
        final Velo velo = Fixtures.createVelo(null, Etat.OK, null, null, Modele.ROUTE);
        entityManager.getTransaction().begin();
        veloRepository.save(velo);
        entityManager.getTransaction().commit();
        entityManager.detach(velo);
        
        final Velo pVelo = veloRepository.findById(velo.getNumero());
        assertThat(pVelo).isNotNull().isNotSameAs(velo);
        assertThat(pVelo.getNumero()).isEqualTo(pVelo.getNumero());
    }
    
    @Test
    void velosAtStation(){
        
        final Station station1 = Fixtures.createStation(null, null);
        final Station station2 = Fixtures.createStation(null, null);
        
        final Bornette bornette1 = Fixtures.createBornette(Etat.OK, station1, null);
        final Bornette bornette2 = Fixtures.createBornette(Etat.OK, station1, null);
        final Bornette bornette3 = Fixtures.createBornette(Etat.OK, station1, null);
        final Bornette bornette4 = Fixtures.createBornette(Etat.OK, station1, null);
        final Bornette bornette5 = Fixtures.createBornette(Etat.OK, station2, null);

        Velo velo1 = Fixtures.createVelo(bornette1, Etat.OK, null, null, Modele.VTT);
        Velo velo2 = Fixtures.createVelo(bornette2, Etat.OK, null, null, Modele.VTT);
        Velo velo3 = Fixtures.createVelo(bornette3, Etat.HS, null, null, Modele.VTT);
        Velo velo4 = Fixtures.createVelo(bornette4, Etat.OK, null, null, Modele.ROUTE);
        Velo velo5 = Fixtures.createVelo(bornette5, Etat.OK, null, null, Modele.VTT);

        entityManager.getTransaction().begin();
        veloRepository.save(velo1);
        veloRepository.save(velo2);
        veloRepository.save(velo3);
        veloRepository.save(velo4);
        veloRepository.save(velo5);
        
        StationRepository stationRepository = daoFactory.newStationRepository(entityManager);
        stationRepository.save(station1);
        stationRepository.save(station2);
        
        BornetteRepository bornetteRepository = daoFactory.newBornetteRepository(entityManager);
        bornetteRepository.save(bornette1);
        bornetteRepository.save(bornette2);
        bornetteRepository.save(bornette3);
        bornetteRepository.save(bornette4);
        bornetteRepository.save(bornette5);
        
        entityManager.getTransaction().commit();
        
        entityManager.detach(velo1);
        entityManager.detach(velo2);
        entityManager.detach(velo3);
        entityManager.detach(velo4);
        entityManager.detach(velo5);
        
        entityManager.detach(station1);
        entityManager.detach(station2);

        entityManager.detach(bornette1);
        entityManager.detach(bornette2);
        entityManager.detach(bornette3);
        entityManager.detach(bornette4);
        entityManager.detach(bornette5);

        System.err.println("###"+station1.getId());
        final Station pStation = stationRepository.findById(station1.getId());
        final Velo pVelo1 = veloRepository.findById(velo1.getNumero());
        final Velo pVelo2 = veloRepository.findById(velo2.getNumero());
        final Velo pVelo4 = veloRepository.findById(velo4.getNumero());

        List<Velo> list = new ArrayList<>();
        list.add(pVelo1);
        list.add(pVelo2);
        list.add(pVelo4);
        
        assertThat(veloRepository.velosAtStation(pStation)).isEqualTo(list);
    }
    
}
