/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.CursoDAO;
import br.edu.ifsul.dao.DisciplinaDAO;
import br.edu.ifsul.dao.InstituicaoDAO;
import br.edu.ifsul.modelo.Curso;
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
@ManagedBean(name = "controleCurso")
@SessionScoped
public class ControleCurso implements Serializable {

    private CursoDAO<Curso> dao;
    private Curso objeto;
    private InstituicaoDAO<Instituicao> daoInst;
    private Boolean novaInformacao;
    private Disciplina disciplina;
    private DisciplinaDAO<Disciplina> daoDisc;

    public ControleCurso() {
        dao = new CursoDAO();
        daoInst = new InstituicaoDAO<>();
        daoDisc = new DisciplinaDAO<>();
    }

    public String listar() {
        return "/privado/curso/listar?faces-redirect=true";
    }

    public String novo() {
        objeto = new Curso();
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
//        try {
        objeto = dao.localizar(id);
        if (dao.remover(objeto)) {
            Util.mensagemInformacao(dao.getMensagem());
        } else {
            Util.mensagemErro(dao.getMensagem());
        }
//        } catch (Exception e) {
//            Util.mensagemErro(e.getMessage());
//        }

    }

    public void adicionarDisciplina() {
        if (novaInformacao) {
            disciplina.setCurso(objeto);
            objeto.getListDisciplina().add(disciplina);
            Util.mensagemInformacao("Disciplina inserida com sucesso");
        } else {
            objeto.getListDisciplina().remove(disciplina);
            objeto.getListDisciplina().add(disciplina);
            Util.mensagemInformacao("Disciplina alterada com sucesso");
        }

    }

    public void novaDisciplina() {
        disciplina = new Disciplina();
        System.out.println("Nova disciplina OK");
        novaInformacao = true;
    }

    public void alterarDisciplina(int index) {
        disciplina = objeto.getListDisciplina().get(index);
        novaInformacao = false;
    }

    public void removerDisciplina(int index) {
        objeto.getListDisciplina().remove(index);
        Util.mensagemInformacao("Disciplina removida com sucesso");
    }

    public Curso getObjeto() {
        return objeto;
    }

    public void setObjeto(Curso objeto) {
        this.objeto = objeto;
    }

    public CursoDAO<Curso> getDao() {
        return dao;
    }

    public void setDao(CursoDAO<Curso> dao) {
        this.dao = dao;
    }

    public InstituicaoDAO<Instituicao> getDaoInstituicao() {
        return daoInst;
    }

    public void setDaoInstituicao(InstituicaoDAO<Instituicao> daoInstituicao) {
        this.daoInst = daoInstituicao;
    }

    public Boolean getNovaInformacao() {
        return novaInformacao;
    }

    public void setNovaInformacao(Boolean novaInformacao) {
        this.novaInformacao = novaInformacao;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public DisciplinaDAO<Disciplina> getDaoDisciplina() {
        return daoDisc;
    }

    public void setDaoDisciplina(DisciplinaDAO<Disciplina> daoDisciplina) {
        this.daoDisc = daoDisciplina;
    }

    public void relatorio() {
        HashMap parametros = new HashMap();
        UtilRelatorios.imprimeRelatorio("relatorioCurso", parametros, dao.getListaTodos());
    }

}
