/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.fesa.gerenciador_gado.Util.DTO;

/**
 *
 * @author Lohan
 */
public class ActionReturnDTO {
    
    //data transfer object
    private Boolean returnAction = false;
    private String message;

    public Boolean getReturnAction() {
        return returnAction;
    }

    public void setReturnAction(Boolean returnAction) {
        this.returnAction = returnAction;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
