/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.impl;

import java.util.List;
import javax.persistence.EntityManager;
import model.Client;
import repository.api.ClientRepository;

/**
 *
 * @author aliceb
 */
public class ClientRepositoryImpl extends BaseRepositoryImpl implements ClientRepository{

    public ClientRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }
    /**
     * Retourne le client à partir de son code "secret"
     * @param code
     * @return 
     */
    @Override
    public Client clientFromCode(String code) {
        String jql = "select c from Client c where c.codeSecret = :code";
        Client retour = entityManager.createQuery(jql, Client.class)
                            .setParameter("code", code)
                            .getSingleResult();

        return retour;   
    }

    @Override
    public void save(Client entity) {
        entityManager.persist(entity);
    }

    @Override
    public void delete(Client entity) {
        entityManager.remove(entity);
    }

    @Override
    public Client findById(Long id) {
        String jql = "select c from Client c where c.id = :id";
        Client retour = entityManager.createQuery(jql, Client.class)
                            .setParameter("id", id)
                            .getSingleResult();

        return retour;     
    }

    @Override
    public List<Client> getAll() {
        String jql = "select c from Client c";
        List<Client> retour = (List<Client>) entityManager.createQuery(jql, Client.class)
                                                .getResultList();
        return retour; 
    }

    @Override
    public boolean existsCode(String code) {
       String jql = "select count(*) from Client c where c.codeSecret = :code";
       Long nbCode = entityManager.createQuery(jql, Long.class)
            .setParameter("code", code)
            .getSingleResult();
       
       return nbCode.intValue() > 0;
    }
    
}
