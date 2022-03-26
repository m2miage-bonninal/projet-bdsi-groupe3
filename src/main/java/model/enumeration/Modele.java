/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.enumeration;

/**
 *
 * @author emerik
 */
public enum Modele {
    VTT(1.0),  ROUTE(0.8);
    
    private double coefficient;
    Modele(double coef){
        this.coefficient = coef;
    }
}
