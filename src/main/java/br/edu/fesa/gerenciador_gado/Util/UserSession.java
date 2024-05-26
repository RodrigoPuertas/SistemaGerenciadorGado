/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.fesa.gerenciador_gado.Util;

import br.edu.fesa.gerenciador_gado.Models.Entities.User;

/**
 *
 * @author paulo
 */
public final class UserSession {

    private static UserSession instance;
    private User user;

    private UserSession(User user) {
        this.user = user;
    }

     public static UserSession getInstance() {
        // Se a instância ainda não foi criada, crie uma nova com um usuário padrão ou null
        if (instance == null) {
            instance = new UserSession(null); // Você pode definir um usuário padrão ou passar null aqui
        }
        return instance;
    }

    // Método para definir o usuário na sessão do usuário
    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void cleanUserSession() {
        user = null;
    }

    @Override
    public String toString() {
        return "UserSession{" +
                "userName='" + user +
                '}';
    }
}