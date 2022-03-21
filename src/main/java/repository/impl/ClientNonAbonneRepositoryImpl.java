/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.impl;

import java.util.List;
import javax.persistence.EntityManager;
import model.ClientNonAbonne;
import repository.api.ClientNonAbonneRepository;

/**
 *
 * @author aliceb
 */
public class ClientNonAbonneRepositoryImpl extends BaseRepositoryImpl implements ClientNonAbonneRepository{

    public ClientNonAbonneRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }
    /**
     * Retourne le client à partir de son code "secret"
     * @param code
     * @return 
     */
    @Override
    public ClientNonAbonne clientFromCode(String code) {

        String jql = "select c from ClientNonAbonne c where c.codeSecret = :code";
        ClientNonAbonne retour = entityManager.createQuery(jql, ClientNonAbonne.class)
                            .setParameter("code", code)
                            .getSingleResult();

        return retour; 
    }

    public void save(ClientNonAbonne entity) {
        entityManager.persist(entity);
    }

    public void delete(ClientNonAbonne entity) {
        entityManager.remove(entity);
    }

    @Override
    public ClientNonAbonne findById(Long id) {
        String jql = "select c from ClientNonAbonne c where c.id = :id";
        ClientNonAbonne retour = entityManager.createQuery(jql, ClientNonAbonne.class)
                            .setParameter("id", id)
                            .getSingleResult();

        return retour; 
    }

    /**
     *
     * @return
     */
    public List<ClientNonAbonne> getAll() {
        String jql = "select c from ClientNonAbonne c";
        List<ClientNonAbonne> retour = (List<ClientNonAbonne>) entityManager.createQuery(jql, ClientNonAbonne.class)
                                                .getResultList();
        return retour;      
    }
    
}
