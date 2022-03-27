package repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import model.Client;
import repository.api.ClientRepository;

/**
 *
 * @author aliceb
 */
public class ClientTest extends Base{
    ClientRepository clientRepository;

    @BeforeEach
    void before() {
        clientRepository = daoFactory.newClientRepository(entityManager);
    }

    @AfterEach
    void after() {
        if (entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().rollback();
        }
    }
    
    @Test
    void existsCode(){
        Client client = Fixtures.createClientNonAbonne(null);
        entityManager.getTransaction().begin();
        clientRepository.save(client);
        entityManager.getTransaction().commit();
        
        boolean foundCode = clientRepository.existsCode(client.getCodeSecret());
        assertThat(foundCode).isTrue();
        boolean randomCode = clientRepository.existsCode(Client.generateCode(clientRepository));
        assertThat(randomCode).isFalse();
    }
    
}
