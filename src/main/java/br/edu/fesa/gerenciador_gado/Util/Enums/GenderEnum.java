/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package br.edu.fesa.gerenciador_gado.Util.Enums;

/**
 *
 * @author Lohan
 */
public enum GenderEnum {
    Male('M'), Female('F');

    private final char genderChar;

    GenderEnum(char genderChar) {
        this.genderChar = genderChar;
    }

    public char getGenderChar() {
        return genderChar;
    }

    public static GenderEnum fromChar(char genderChar) {
        switch (genderChar) {
            case 'M':
                return Male;
            case 'F':
                return Female;
            default:
                throw new IllegalArgumentException("Unknown gender: " + genderChar);
        }
    }

    public static GenderEnum fromString(String genderString) {
        if (genderString == null || genderString.length() != 1) {
            throw new IllegalArgumentException("Invalid gender string: " + genderString);
        }
        return fromChar(genderString.charAt(0));
    }

}
