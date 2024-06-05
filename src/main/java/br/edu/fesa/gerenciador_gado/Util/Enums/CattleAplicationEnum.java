/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package br.edu.fesa.gerenciador_gado.Util.Enums;

/**
 *
 *
 * Author: Lohan
 */
public enum CattleAplicationEnum {
    Corte("Corte"),
    Leiteiro("Leiteiro"),
    Mista("Misto"),
    None("None");

    private final String aplicacaoValue;

    private CattleAplicationEnum(String aplicacaoValue) {
        this.aplicacaoValue = aplicacaoValue;
    }

    public String getValue() {
        return aplicacaoValue;
    }

    public static CattleAplicationEnum fromValue(String value) {
        for (CattleAplicationEnum aplicacao : CattleAplicationEnum.values()) {
            if (aplicacao.getValue().equals(value)) {
                return aplicacao;
            }
        }
        throw new IllegalArgumentException("Valor inválido para CattleAplicationEnum: " + value);
    }

}
