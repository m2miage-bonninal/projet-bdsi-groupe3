/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository.api;

import model.Client;
import model.Location;

/**
 *
 * @author aliceb
 */
public interface LocationRepository extends Repository<Location, Long>{
    Location locationEnCoursFromClient(Client client);
    
}
