/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository.api;

import java.util.List;
import model.Bornette;
import model.Station;
import model.enumeration.Modele;

/**
 *
 * @author aliceb
 */
public interface BornetteRepository extends Repository<Bornette, Long>{
    
    List<Bornette> bornetteAvecModele(Station station, Modele modele, int nbVelo);
}
