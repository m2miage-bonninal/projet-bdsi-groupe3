/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.impl;

import java.util.List;
import javax.persistence.EntityManager;
import model.Trajet;
import model.Velo;
import repository.api.TrajetRepository;

/**
 *
 * @author aliceb
 */
public class TrajetRepositoryImpl extends BaseRepositoryImpl implements TrajetRepository{   
    
    public TrajetRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }
        
    /**
     * Retourne le trajet en cours d'un vélo
     * @param velo
     * @return 
     */
    @Override
    public Trajet trajetFromVelo(Velo velo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void save(Trajet entity) {
        entityManager.persist(entity);
    }

    @Override
    public void delete(Trajet entity) {
        entityManager.remove(entity);
    }

    @Override
    public Trajet findById(Long id) {
        String jql = "select t from trajet t where t.id = :id";
        Trajet retour = entityManager.createQuery(jql, Trajet.class)
                            .setParameter("id", id)
                            .getSingleResult();

        return retour;    }

    @Override
    public List<Trajet> getAll() {
        String jql = "select t from trajet t";
        List<Trajet> retour = (List<Trajet>) entityManager.createQuery(jql, Trajet.class)
                                                .getResultList();
        return retour;    }
    
}
