/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import model.enumeration.VType;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author emerik
 */
@Entity
public class HistoriqueVType {
    
    public HistoriqueVType(){};
    
    public HistoriqueVType(VType vType, Station station, LocalDateTime ldt){
        this.vType = vType;
        this.station = station;
        this.dateHeureDebut = LocalDateTime.now();
    }
    
    @Id
    @GenericGenerator(name = "kaugen1", strategy = "increment")
    @GeneratedValue(generator = "kaugen1")
    private int id;
    
    private VType vType;
    
    private LocalDateTime dateHeureDebut;
    
    private LocalDateTime dateHeureFin;
    
    private Station station;
}
