package repository;
import model.Velo ;
import repository.api.VeloRepository ;
import repository.impl.VeloRepositoryImpl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
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


    
}
