/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;


import br.edu.ifsul.modelo.Aluno;
import java.io.Serializable;

/**
 *
 * @author ubiratan
 * @param <T>
 */

public class AlunoDAO<T> extends DAOGenerico<Aluno> implements Serializable{
  
    
    public AlunoDAO(){
        super();
        super.setClassePersistente(Aluno.class);       
        //super.setOrdem("nome");
    }   
}
