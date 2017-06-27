/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.AlunoDAO;
import br.edu.ifsul.dao.DisciplinaDAO;
import br.edu.ifsul.dao.DisciplinaDAO;
import br.edu.ifsul.dao.InstituicaoDAO;
import br.edu.ifsul.modelo.Aluno;
import br.edu.ifsul.modelo.Disciplina;
import br.edu.ifsul.modelo.Disciplina;
import br.edu.ifsul.modelo.Instituicao;
import br.edu.ifsul.util.Util;
import br.edu.ifsul.util.UtilRelatorios;
import java.io.Serializable;
import java.util.HashMap;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author ubiratan
 */
@ManagedBean(name = "controleDisciplina")
@SessionScoped
public class ControleDisciplina implements Serializable {

    private DisciplinaDAO<Disciplina> dao;
    private Disciplina objeto;
    private Aluno aluno;
    private AlunoDAO<Aluno> daoAluno;

    public ControleDisciplina() {
        dao = new DisciplinaDAO();
        daoAluno = new AlunoDAO<>();
    }

    public String listar() {
        return "/privado/disciplina/listar?faces-redirect=true";
    }

    public String novo() {
        objeto = new Disciplina();
        return "formulario";
    }

    public String salvar() {
        boolean persistiu;
        if (objeto.getId() == null) {
            persistiu = getDao().persist(objeto);
        } else {
            persistiu = getDao().merge(objeto);
        }
        if (persistiu) {
            Util.mensagemInformacao(getDao().getMensagem());
            return "listar";
        } else {
            Util.mensagemErro(getDao().getMensagem());
            return "formulario";
        }
    }

    public String cancelar() {
        return "listar";
    }

    public String editar(Integer id) {
        try {
            objeto = getDao().localizar(id);
            return "formulario";
        } catch (Exception e) {
            Util.mensagemErro(e.getMessage());
            return "listar";
        }
    }

    public void remover(Integer id) {
        try {
            objeto = getDao().localizar(id);
            if (getDao().remover(objeto)) {
                Util.mensagemInformacao(getDao().getMensagem());
            } else {
                Util.mensagemErro(getDao().getMensagem());
            }
        } catch (Exception e) {
            Util.mensagemErro(e.getMessage());
        }

    }
    
    public Disciplina getObjeto() {
        return objeto;
    }

    public void setObjeto(Disciplina objeto) {
        this.objeto = objeto;
    }

    public DisciplinaDAO<Disciplina> getDao() {
        return dao;
    }

    public void setDao(DisciplinaDAO<Disciplina> dao) {
        this.dao = dao;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public AlunoDAO<Aluno> getDaoAluno() {
        return daoAluno;
    }

    public void setDaoAluno(AlunoDAO<Aluno> daoAluno) {
        this.daoAluno = daoAluno;
    }
    
    public void adicionarAluno(){
        if(aluno != null){
            if(!objeto.getAlunos().contains(aluno)){
                objeto.getAlunos().add(aluno);
                Util.mensagemInformacao("Aluno adicionado com sucesso");
            }else{
                Util.mensagemErro("Aluno já existe na lista de desejos");
            }
        }
    }
    
    public void removerAluno(int index){
        objeto.getAlunos().remove(index);
        Util.mensagemInformacao("Aluno "+objeto.getNome()+" foi removido com sucesso");
    }
}
