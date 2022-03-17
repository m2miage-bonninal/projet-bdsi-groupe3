/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.impl;

import java.util.List;
import javax.persistence.EntityManager;
import model.Bornette;
import model.Station;
import model.enumeration.Modele;
import repository.api.BornetteRepository;

/**
 *
 * @author aliceb
 */
public class BornetteRepositoryImpl extends BaseRepositoryImpl implements BornetteRepository{

    public BornetteRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }
        
    @Override
    public List<Bornette> bornetteAvecModele(Station station, Modele modele, int nbVelo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void save(Bornette entity) {
        entityManager.persist(entity);
    }

    @Override
    public void delete(Bornette entity) {
        entityManager.remove(entity);
    }

    @Override
    public Bornette findById(Long id) {
        String jql = "select b from bornette b where b.id = :id";
        Bornette retour = entityManager.createQuery(jql, Bornette.class)
                            .setParameter("id", id)
                            .getSingleResult();

        return retour;    
    }

    @Override
    public List<Bornette> getAll() {
        String jql = "select b from bornette b";
        List<Bornette> retour = (List<Bornette>) entityManager.createQuery(jql, Bornette.class)
                                                .getResultList();
        return retour;    
    }
    
}
