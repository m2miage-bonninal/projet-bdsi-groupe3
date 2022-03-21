/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import model.enumeration.VType;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author emerik
 */
@Entity
public class Station {
    
    public Station(){};
    
    public Station(String adresse){
        this.adresse = adresse;
    }
    
    @Id
    @GenericGenerator(name = "kaugen1", strategy = "increment")
    @GeneratedValue(generator = "kaugen1")
    private Long id;
        
    @OneToMany
    private List<Bornette> bornettes;
    
    private String adresse;    
    
    @OneToMany
    private List<HistoriqueVType> historiques;

    public List<HistoriqueVType> getHistoriques() {
        return historiques;
    }

    public void setHistoriques(List<HistoriqueVType> historiques) {
        this.historiques = historiques;
    }
    
    public Long getId(){
        return this.id;
    }
    
    public String getAdresse(){
        return this.adresse;
    }
    
    public List<Bornette> getBornettes(){
        return bornettes;
    }
    
    public void setBornette(List<Bornette> newList){
        bornettes = newList;
    }
    
    public VType getVTypeAt(LocalDateTime ldt){
        VType retour = VType.VNUL;
        
        int i = 0;
        boolean trouve = false;
        while(i < this.getHistoriques().size() && !trouve){
            HistoriqueVType histo = this.getHistoriques().get(i);
            if(histo.getDateHeureDebut().isBefore(ldt) && histo.getDateHeureFin().isAfter(ldt)){
                retour = histo.getvType();
                trouve = true;
            }
            i++;
        }
        return retour;
    }

}
