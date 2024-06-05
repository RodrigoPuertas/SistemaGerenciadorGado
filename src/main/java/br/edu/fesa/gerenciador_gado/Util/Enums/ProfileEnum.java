/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.fesa.gerenciador_gado.Util.Enums;

/**
 *
 * @author paulo
 */
public enum ProfileEnum {
    ADM(1), FARMER(2), RANCHER(3);

    private final int profileValue;

    ProfileEnum(int value) {
        profileValue = value;
    }

    public int getValue() {
        return profileValue;
    }
    
    public static ProfileEnum fromValue(int value) {
        for (ProfileEnum profile : ProfileEnum.values()) {
            if (profile.getValue() == value) {
                return profile;
            }
        }
        throw new IllegalArgumentException("Valor inválido para ProfileEnum: " + value);
    }
}
