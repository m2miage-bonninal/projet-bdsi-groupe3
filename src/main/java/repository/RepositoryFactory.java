package repository;

import repository.api.BornetteRepository;
import repository.api.ClientAbonneRepository;
import repository.api.ClientRepository;
import repository.api.LocationRepository;
import repository.api.StationRepository;
import repository.api.TrajetRepository;
import repository.api.VeloRepository;
import repository.impl.BornetteRepositoryImpl;
import repository.impl.ClientAbonneRepositoryImpl;
import repository.impl.ClientRepositoryImpl;
import repository.impl.LocationRepositoryImpl;
import repository.impl.StationRepositoryImpl;
import repository.impl.TrajetRepositoryImpl;
import repository.impl.VeloRepositoryImpl;

import javax.persistence.EntityManager;

public class RepositoryFactory {

    public BornetteRepository newBornetteRepository(EntityManager entityManager){
        return new BornetteRepositoryImpl(entityManager) ;
    }
    public ClientAbonneRepository newClientAbonneRepository(EntityManager entityManager){
        return new ClientAbonneRepositoryImpl(entityManager) ;
    }
    public ClientRepository newClientRepository(EntityManager entityManager){
        return new ClientRepositoryImpl() ;
    }
    public LocationRepository newLocationRepository(EntityManager entityManager){
        return new LocationRepositoryImpl(entityManager) ;
    }
    public VeloRepository newVeloRepository(EntityManager entityManager){
        return new VeloRepositoryImpl(entityManager) ;
    }
    public TrajetRepository newTrajetRepository(EntityManager entityManager){
        return new TrajetRepositoryImpl(entityManager) ;
    }
    public StationRepository newStationRepository(EntityManager entityManager){
        return new StationRepositoryImpl(entityManager);
    }

}
