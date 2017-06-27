/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.InstituicaoDAO;
import br.edu.ifsul.modelo.Instituicao;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author ubiratan
 */
@ManagedBean(name = "controleInstituicao")
@SessionScoped
public class ControleInstituição implements Serializable{
    
    private InstituicaoDAO<Instituicao> dao;
    private Instituicao objeto;
    
    public ControleInstituição(){
        dao = new InstituicaoDAO<>();
    }
    
    public String listar(){
        return "/privado/instituicao/listar?faces-redirect=true";
    }
    
    public String novo(){
        objeto = new Instituicao();
        return "formulario";
    }
    
    public String salvar(){
        boolean persistiu;        
        if(objeto.getId() == null){
            persistiu = getDao().persist(objeto);
        }else{
            persistiu = getDao().merge(objeto);
        }        
        if(persistiu){
            Util.mensagemInformacao(getDao().getMensagem());
            return "listar";
        }else{
            Util.mensagemErro(getDao().getMensagem());
            return "formulario";
        }
    }
    
    public String cancelar(){
        return "listar";
    }
    
    public String editar(Integer id){
        try {
            objeto = getDao().localizar(id);
            return "formulario";
        } catch (Exception e) {
            Util.mensagemErro("Erro ao recuperar objeto: "+Util.getMensagemErro(e));
            return "listar";
        }
    }
    
    public void remover(Integer id){
        objeto = getDao().localizar(id);
        if(getDao().remover(objeto)){
            Util.mensagemInformacao(getDao().getMensagem());
        }else{
            Util.mensagemErro(getDao().getMensagem());
        }
    }

    public InstituicaoDAO<Instituicao> getDao() {
        return dao;
    }

    public void setDao(InstituicaoDAO<Instituicao> dao) {
        this.dao = dao;
    }

    public Instituicao getObjeto() {
        return objeto;
    }

    public void setObjeto(Instituicao objeto) {
        this.objeto = objeto;
    }
    
}
