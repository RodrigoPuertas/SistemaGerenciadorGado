/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.edu.fesa.gerenciador_gado.DAO;

import br.edu.fesa.gerenciador_gado.exception.PersistenceException;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author paulo
 */
public interface GenericDAO<E> extends Serializable{
    
    public List<E> listar() throws PersistenceException;
    
    public void inserir(E e) throws PersistenceException;

    public void alterar(E e) throws PersistenceException;

    public void remover(E e) throws PersistenceException;

    public E listarPorID(E e) throws PersistenceException;
}

