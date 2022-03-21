/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import model.enumeration.VType;

/**
 *
 * @author emerik
 */
@Entity
public class HistoriqueVType {
    
    @Id
    private int id;
    
    private VType vType;
    
    private LocalDateTime dateHeureDebut;
    
    private LocalDateTime dateHeureFin;
    
    private Station station;
}
