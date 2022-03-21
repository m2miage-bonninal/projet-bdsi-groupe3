package repository;
import model.Bornette ;
import repository.api.BornetteRepository ;
import repository.impl.BornetteRepositoryImpl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
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


    
}
