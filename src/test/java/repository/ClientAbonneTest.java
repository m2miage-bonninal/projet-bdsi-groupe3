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
    
    @Test
    void saveClientAbonne(){
        final ClientAbonne client = Fixtures.createClientAbonne(null);
        
        entityManager.getTransaction().begin();
        ClientAbonneRepository.save(client);
        entityManager.getTransaction().commit();
        entityManager.detach(client);
        
        final ClientAbonne pClient = ClientAbonneRepository.findById(client.getId());
        assertThat(pClient.getId()).isEqualTo(client.getId());
    }
    
      
    @Test
    void abonneFromCode(){
        final ClientAbonne client = Fixtures.createClientAbonne(null);
        entityManager.getTransaction().begin();
        ClientAbonneRepository.save(client);
        entityManager.getTransaction().commit();
        entityManager.detach(client);
        
        final ClientAbonne pClient = ClientAbonneRepository.findById(client.getId());
        assertThat(ClientAbonneRepository.abonneFromCode(client.getCodeSecret())).isEqualTo(pClient);
    }

    
}
