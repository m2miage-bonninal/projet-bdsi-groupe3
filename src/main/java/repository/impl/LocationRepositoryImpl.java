/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.impl;

import java.util.List;
import javax.persistence.EntityManager;
import model.Client;
import model.Location;
import repository.api.LocationRepository;

/**
 *
 * @author aliceb
 */
public class LocationRepositoryImpl extends BaseRepositoryImpl implements LocationRepository{

    public LocationRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }
        
    /**
     * Retourne la location en cours (vélo pris et non rendu) d'un client s'il en a une
     * @param client
     * @return 
     */
    @Override
    public Location locationEnCoursFromClient(Client client) {

        String jql = "select l from Location l join l.locataire loc join l.trajets t where loc.id = :idClient and t.duree = -1";
        Location retour = entityManager.createQuery(jql, Location.class)
                            .setParameter("idClient", client.getId())
                            .getSingleResult();
        
        return retour;
    }

    @Override
    public void save(Location entity) {
        entityManager.persist(entity);
    }

    @Override
    public void delete(Location entity) {
        entityManager.remove(entity);
    }

    @Override
    public Location findById(Long id) {
        String jql = "select l from Location l where l.id = :id";
        Location retour = entityManager.createQuery(jql, Location.class)
                            .setParameter("id", id)
                            .getSingleResult();

        return retour;    
    }

    @Override
    public List<Location> getAll() {
        String jql = "select l from Location l";
        List<Location> retour = (List<Location>) entityManager.createQuery(jql, Location.class)
                                                .getResultList();
        return retour;    
    }
    
}
