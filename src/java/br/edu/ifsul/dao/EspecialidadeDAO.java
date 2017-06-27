/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;


import br.edu.ifsul.modelo.Especialidade;
import java.io.Serializable;

/**
 *
 * @author ubiratan
 */
public class EspecialidadeDAO<T> extends DAOGenerico<Especialidade> implements Serializable{
 
    public EspecialidadeDAO(){
        super();
        super.setClassePersistente(Especialidade.class);
    }
}
