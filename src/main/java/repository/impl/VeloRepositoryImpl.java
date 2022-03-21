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
        
    /**
     * Liste de tous les vélos disponbles (pas HS) à une station
     * @param station
     * @return 
     */
    @Override
    public List<Velo> velosAtStation(Station station) {
        String jql = "select v from Velo v join v.bornette b join b.station s where s.id = :idStation and etat != 'HS'";
        List<Velo> retour = (List<Velo>) entityManager.createQuery(jql, Velo.class)
                                .setParameter("idStation", station.getId())
                                .getResultList();
        return retour;
        //attention, il faut revoir ca. Pourquoi ne pas partir de la station directement ???
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
        String jql = "select v from Velo s where v.numero = :id";
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
