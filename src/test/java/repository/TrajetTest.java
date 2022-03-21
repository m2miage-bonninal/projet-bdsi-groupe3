package repository;
import model.Trajet ;
import repository.api.TrajetRepository ;
import repository.impl.TrajetRepositoryImpl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
public class TrajetTest extends Base {
    TrajetRepository TrajetRepository;

    @BeforeEach
    void before() {
        TrajetRepository = daoFactory.newTrajetRepository(entityManager);
    }

    @AfterEach
    void after() {
        if (entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().rollback();
        }
    }


    
}
