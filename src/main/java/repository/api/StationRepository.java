/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository.api;

import java.util.List;
import model.Station;

/**
 *
 * @author emerik
 */
public interface StationRepository extends Repository<Station, Long>{

    List<Station> recupStations();
    
}
