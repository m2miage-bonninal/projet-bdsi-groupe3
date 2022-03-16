/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository.api;

import model.ClientAbonne;

/**
 *
 * @author aliceb
 */
public interface ClientAbonneRepository extends Repository<ClientAbonne, Long>{
    
    ClientAbonne abonneFromCode(String code);
    
}
