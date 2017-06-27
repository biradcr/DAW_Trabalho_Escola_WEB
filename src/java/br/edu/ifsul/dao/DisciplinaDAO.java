/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.jpa.EntityManagerUtil;
import br.edu.ifsul.modelo.Disciplina;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author ubiratan
 */
public class DisciplinaDAO<T> extends DAOGenerico<Disciplina> implements Serializable{
 
    public DisciplinaDAO(){
        super();
        super.setClassePersistente(Disciplina.class);
    }
}
