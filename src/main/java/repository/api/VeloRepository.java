/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository.api;

import java.util.List;
import model.Station;
import model.Velo;

/**
 *
 * @author aliceb
 */
public interface VeloRepository extends Repository<Velo, Long>{
    List<Velo> velosAtStation(Station station);
    
}
