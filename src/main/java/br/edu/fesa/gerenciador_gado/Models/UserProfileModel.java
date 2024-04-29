/*
 
Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
*/
package br.edu.fesa.gerenciador_gado.Models;

/**
 *
 
@author Lohan
@author Rodrigo Puertas
*/
public class UserProfileModel {
    private Integer id;
    private String descProfile;

    public UserProfileModel(Integer id, String descProfile) {
        this.id = id;
        this.descProfile = descProfile;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescProfile() {
        return descProfile;
    }

    public void setDescProfile(String descProfile) {
        this.descProfile = descProfile;
    }
}