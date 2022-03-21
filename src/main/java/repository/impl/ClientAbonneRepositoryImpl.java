/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.impl;

import java.util.List;
import javax.persistence.EntityManager;
import model.ClientAbonne;
import repository.api.ClientAbonneRepository;

/**
 *
 * @author aliceb
 */
public class ClientAbonneRepositoryImpl extends BaseRepositoryImpl implements ClientAbonneRepository{

    public ClientAbonneRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }
        
    @Override
    public void save(ClientAbonne entity) {
        entityManager.persist(entity);
    }

    @Override
    public void delete(ClientAbonne entity) {
        entityManager.remove(entity);
    }

    @Override
    public ClientAbonne findById(Long id) {
        String jql = "select c from ClientAbonne c where c.id = :id";
        ClientAbonne retour = entityManager.createQuery(jql, ClientAbonne.class)
                            .setParameter("id", id)
                            .getSingleResult();

        return retour;    
    }

    @Override
    public List<ClientAbonne> getAll() {
        String jql = "select c from ClientAbonne c";
        List<ClientAbonne> retour = (List<ClientAbonne>) entityManager.createQuery(jql, ClientAbonne.class)
                                                .getResultList();
        return retour;    
    }

    @Override
    public ClientAbonne abonneFromCode(String code) {
        String jql = "select c from ClientAbonne c where c.codeSecret = :codeSecret";
        ClientAbonne retour = entityManager.createQuery(jql, ClientAbonne.class)
                            .setParameter("codeSecret", code)
                            .getSingleResult();

        return retour; 
    }
    
}
