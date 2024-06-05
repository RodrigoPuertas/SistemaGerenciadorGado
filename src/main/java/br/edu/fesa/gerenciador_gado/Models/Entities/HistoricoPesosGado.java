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
public class HistoricoPesosGado {

    private int id;
    private LocalDate dataPesagem;
    private int idGado;
    private double pesoKg;

     public HistoricoPesosGado(LocalDate dataPesagem, int idGado,double pesoKg) {
        setDataPesagem(dataPesagem);
        setIdGado(idGado);
        setPesoKg(pesoKg);
    }
    
    public HistoricoPesosGado(int id, LocalDate dataPesagem, int idGado,double pesoKg) {
        setId(id);
        setDataPesagem(dataPesagem);
        setIdGado(idGado);
        setPesoKg(pesoKg);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDataPesagem() {
        return dataPesagem;
    }

    public void setDataPesagem(LocalDate dataPesagem) {
        this.dataPesagem = dataPesagem;
    }

    public int getIdGado() {
        return idGado;
    }

    public void setIdGado(int idGado) {
        this.idGado = idGado;
    }


    public double getPesoKg() {
        return pesoKg;
    }

    public void setPesoKg(double pesoKg) {
        this.pesoKg = pesoKg;
    }

}
