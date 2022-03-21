/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository.api;

import model.Client;

/**
 *
 * @author aliceb
 */
public interface ClientNonAbonneRepository extends Repository<Client,Long>{
    Client clientFromCode(String code);
    
}
