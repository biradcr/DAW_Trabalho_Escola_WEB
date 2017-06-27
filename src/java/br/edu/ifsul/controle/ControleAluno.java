/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.AlunoDAO;
import br.edu.ifsul.modelo.Aluno;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
/**
 *
 * @author ubiratan
 */
@ManagedBean(name = "controleAluno")
@SessionScoped
public class ControleAluno implements Serializable{
    
    private AlunoDAO dao;
    private Aluno objeto;
    
    public ControleAluno(){
        dao = new AlunoDAO();
    }
    
    public String listar(){
        return "/privado/aluno/listar?faces-redirect=true";
    }
    
    public void novo(){
        setObjeto(new Aluno());
    }
    
    public void salvar(){
        boolean persistiu;
        if (getObjeto().getId() == null){
            persistiu = getDao().persist(getObjeto());
        } else {
            persistiu = getDao().merge(getObjeto());
        }        
        if(persistiu){
            Util.mensagemInformacao(getDao().getMensagem());            
        } else {
            Util.mensagemErro(getDao().getMensagem());            
        }   
    }
    
    public String cancelar(){
        return "listar";
    }
    
    public void editar(Integer id){
        try {
            getDao().roolback();
            setObjeto(getDao().localizar(id));
        } catch (Exception e) {
            Util.mensagemErro(e.getMessage());
        }
    }
    
    public void remover(Integer id){
        try{
            setObjeto(getDao().localizar(id));
        if( getDao().remover(getObjeto())){
            Util.mensagemInformacao(getDao().getMensagem());
        }else{
            Util.mensagemErro(getDao().getMensagem());
        }
        }catch(Exception e){
            Util.mensagemErro(e.getMessage());
        }
        
    }

    public AlunoDAO<Aluno> getDao() {
        return dao;
    }

    public void setDao(AlunoDAO<Aluno> dao) {
        this.dao = dao;
    }

    public Aluno getObjeto() {
        return objeto;
    }

    public void setObjeto(Aluno objeto) {
        this.objeto = objeto;
    }
    
}
