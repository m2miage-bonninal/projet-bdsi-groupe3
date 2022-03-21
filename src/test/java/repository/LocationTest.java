package repository;
import model.Location ;
import repository.api.LocationRepository ;
import repository.impl.LocationRepositoryImpl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
public class LocationTest extends Base {
    LocationRepository LocationRepository;

    @BeforeEach
    void before() {
        LocationRepository = daoFactory.newLocationRepository(entityManager);
    }

    @AfterEach
    void after() {
        if (entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().rollback();
        }
    }


    
}
