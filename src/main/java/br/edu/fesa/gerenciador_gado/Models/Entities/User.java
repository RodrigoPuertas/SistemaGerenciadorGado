/*
 
Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.fesa.gerenciador_gado.Models.Entities;

import br.edu.fesa.gerenciador_gado.Util.Enums.ProfileEnum;

/**
 *
 *
 * @author Lohan
 * @author Rodrigo Puertas
 */
public class User {

    private Integer id;
    private String email;
    private String name;
    private String password; 
    private ProfileEnum profileCode;

    public User() {
    }

    public User(Integer id, String name, String email, ProfileEnum profileCode) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.profileCode = profileCode;
    }

    public User(String name, String email, String password ,ProfileEnum profileCode) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.profileCode = profileCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public ProfileEnum getProfileCode() {
        return profileCode;
    }

    public void setProfileCode(ProfileEnum profileCode) {
        this.profileCode = profileCode;
    }
    
     public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
