package repository;
import model.ClientNonAbonne ;
import repository.api.ClientNonAbonneRepository ;
import repository.impl.ClientNonAbonneRepositoryImpl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
public class ClientNonAbonneTest extends Base {
    ClientNonAbonneRepository ClientNonAbonneRepository;

    @BeforeEach
    void before() {
        ClientNonAbonneRepository = daoFactory.newClientNonAbonneRepository(entityManager);
    }

    @AfterEach
    void after() {
        if (entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().rollback();
        }
        
    }
    
    @Test
    void findClientFromCode(){
        ClientNonAbonne client = Fixtures.createClientNonAbonne(null);
        entityManager.getTransaction().begin();
        ClientNonAbonneRepository.save(client);
        entityManager.getTransaction().commit();
        
        entityManager.detach(client);
        
        ClientNonAbonne pClient = ClientNonAbonneRepository.clientFromCode(client.getCodeSecret());
        assertThat(pClient.getId()).isEqualTo(client.getId());
    }
    
    
}
