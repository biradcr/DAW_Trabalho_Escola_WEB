/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;


import br.edu.ifsul.modelo.Instituicao;
import java.io.Serializable;

/**
 *
 * @author ubiratan
 */
public class InstituicaoDAO<T> extends DAOGenerico<Instituicao> implements Serializable{
 
    public InstituicaoDAO(){
        super();
        super.setClassePersistente(Instituicao.class);
    }
}
