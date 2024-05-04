/*
 
Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
*/
package br.edu.fesa.gerenciador_gado.DAO;

import br.edu.fesa.gerenciador_gado.Util.Exceptions.PersistenceException;
import java.io.Serializable;
import java.util.List;

/**
 *
 
@author paulo*/
public interface GenericDAO<E> extends Serializable{

    public List<E> list() throws PersistenceException;

    public void insert(E e) throws PersistenceException;

    public void update(E e) throws PersistenceException;

    public void remove(E e) throws PersistenceException;

    public E listById(E e) throws PersistenceException;
}