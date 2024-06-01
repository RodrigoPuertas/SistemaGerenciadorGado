/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.fesa.gerenciador_gado.Models.Entities;

import br.edu.fesa.gerenciador_gado.Util.Enums.CattleAplicationEnum;
import br.edu.fesa.gerenciador_gado.Util.Enums.GenderEnum;
import br.edu.fesa.gerenciador_gado.Util.Enums.RacaGadoEnum;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Lohan
 */
public class Cattle extends Animal {

    private CattleAplicationEnum aplication;
    private RacaGadoEnum raca;

    public CattleAplicationEnum getAplication() {
        return aplication;
    }

    public void setAplication(CattleAplicationEnum aplication) {
        this.aplication = aplication;
    }

    public RacaGadoEnum getRaca() {
        return raca;
    }

    public void setRaca(RacaGadoEnum raca) {
        this.raca = raca;
    }

    public Cattle(CattleAplicationEnum aplication, RacaGadoEnum raca,GenderEnum gender, LocalDate dataNascimento,
            List<Map<LocalDate, Double>> historicoPeso, String descricao, String observacao) {
        super.setGender(gender);
        super.setDataNascimento(dataNascimento);
        super.setHistoricoPeso(historicoPeso);
        super.setDescricao(descricao);
        super.setObservacao(observacao);
        this.aplication = aplication;
        this.raca = raca;

    }
}
