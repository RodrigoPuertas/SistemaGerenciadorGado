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
public class UserModel {
    private Integer id;
    private String email;
    private String name;

    public UserModel(){};

    public UserModel(Integer id, String name, String email, UserProfileModel userProfile) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.userProfile = userProfile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    private UserProfileModel userProfile;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserProfileModel getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfileModel userProfile) {
        this.userProfile = userProfile;
    }


}