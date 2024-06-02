/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.fesa.gerenciador_gado.Models.Entities;

import java.time.LocalDate;

/**
 *
 * @author USER
 */
public class PriceCattle {

    private int id;
    private LocalDate dtPrice;
    private double price;
    
    public PriceCattle(int id, LocalDate dtPrice, double price){
        setId(id);
        setDtPrice(dtPrice);
        setPrice(price);
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDtPrice() {
        return dtPrice;
    }

    public void setDtPrice(LocalDate dt_price) {
        this.dtPrice = dt_price;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
}
