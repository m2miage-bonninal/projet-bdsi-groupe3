package repository;
import model.ClientAbonne ;
import repository.api.ClientAbonneRepository ;
import repository.impl.ClientAbonneRepositoryImpl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
public class ClientAbonneTest extends Base {
    ClientAbonneRepository ClientAbonneRepository;

    @BeforeEach
    void before() {
        ClientAbonneRepository = daoFactory.newClientAbonneRepository(entityManager);
    }

    @AfterEach
    void after() {
        if (entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().rollback();
        }
    }


    
}
