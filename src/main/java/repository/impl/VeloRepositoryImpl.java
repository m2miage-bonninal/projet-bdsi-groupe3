/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.impl;

import java.util.List;
import javax.persistence.EntityManager;
import model.Station;
import model.Velo;
import repository.api.VeloRepository;

/**
 *
 * @author aliceb
 */
public class VeloRepositoryImpl extends BaseRepositoryImpl implements VeloRepository{
    
    public VeloRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }
        
    @Override
    public List<Velo> velosAtStation(Station station) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void save(Velo entity) {
        entityManager.persist(entity);
    }

    @Override
    public void delete(Velo entity) {
        entityManager.remove(entity);
    }

    @Override
    public Velo findById(Long id) {
        String jql = "select v from velo s where v.id = :id";
        Velo retour = entityManager.createQuery(jql, Velo.class)
                            .setParameter("id", id)
                            .getSingleResult();

        return retour;    }

    @Override
    public List<Velo> getAll() {
        String jql = "select v from velo v";
        List<Velo> retour = (List<Velo>) entityManager.createQuery(jql, Velo.class)
                                                .getResultList();
        return retour;    }
    
}
