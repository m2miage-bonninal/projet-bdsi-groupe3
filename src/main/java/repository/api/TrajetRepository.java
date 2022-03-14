/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository.api;

import model.Trajet;
import model.Velo;

/**
 *
 * @author aliceb
 */
public interface TrajetRepository extends Repository<Trajet, Long>{
    Trajet trajetFromVelo(Velo velo);
    
}
