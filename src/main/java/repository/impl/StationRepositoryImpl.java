/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository.impl;

import java.util.List;
import javax.persistence.EntityManager;
import model.Station;
import repository.api.StationRepository;

/**
 *
 * @author emerik
 */
public class StationRepositoryImpl extends BaseRepositoryImpl implements StationRepository{

        public StationRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }


    @Override
    public void save(Station entity) {
        entityManager.persist(entity);
    }

    @Override
    public void delete(Station entity) {
        entityManager.remove(entity);
    }

    @Override
    public Station findById(Long id) {
        String jql = "select s from station s where s.id = :id";
        Station retour = entityManager.createQuery(jql, Station.class)
                            .setParameter("id", id)
                            .getSingleResult();

        return retour;    }

    @Override
    public List<Station> getAll() {
        String jql = "select s from station s";
        List<Station> retour = (List<Station>) entityManager.createQuery(jql, Station.class)
                                                .getResultList();
        return retour;    }

    @Override
    public List<Station> recupStations() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
