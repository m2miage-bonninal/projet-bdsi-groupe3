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
        
    /**
     * Renvoie la liste des bornettes d'une station auxquelles sont rattachés des vélos utilisables (état OK) du type donné
     * @param station
     * @param modele
     * @param nbVelo
     * @return 
     */
    @Override
    public List<Bornette> bornetteAvecModele(Station station, Modele modele, int nbVelo) {

        String jql = "select b from Bornette b join b.station s join b.veloAccroche v where b.station = :station and v.modele = :modele";
        List<Bornette> retour = entityManager.createQuery(jql, Bornette.class)
                            .setParameter("station", station)
                            .setParameter("modele", modele)
                            .setMaxResults(nbVelo)
                            .getResultList();
        
        return retour;
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
