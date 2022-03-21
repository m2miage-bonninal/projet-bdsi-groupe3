package repository;
import model.Station ;
import repository.api.StationRepository ;
import repository.impl.StationRepositoryImpl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
public class StationTest extends Base {
    StationRepository stationRepository;

    @BeforeEach
    void before() {
        stationRepository = daoFactory.newStationRepository(entityManager);
    }

    @AfterEach
    void after() {
        if (entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().rollback();
        }
    }


    void saveStation(){
        final Station station = Fixtures.createStation(null, null);
        entityManager.getTransaction().begin();
        stationRepository.save(station);
        entityManager.getTransaction().commit();
        entityManager.detach(station);
        
        final Station pStation = stationRepository.findById(station.getId());
        assertThat(pStation.getId()).isEqualTo(station.getId());
    }
}
