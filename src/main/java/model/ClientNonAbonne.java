/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 *
 * @author emerik
 */
@Entity
@DiscriminatorValue (value = "nonAbonne")
public class ClientNonAbonne extends Client{
    
}
