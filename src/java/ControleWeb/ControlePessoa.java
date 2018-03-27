/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControleWeb;

import Classes.Pessoa;
import ClassesDao.PessoaDao;
import Util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Leandro
 */
@ManagedBean(name="controlepessoa")
@SessionScoped
public class ControlePessoa implements Serializable {
    
    private PessoaDao dao;
    private Pessoa pessoa;
    
    
    public ControlePessoa()
    {
        dao= new PessoaDao();
    }
    
    public String listar()
    {
        return "/privado/pessoa/listar?faces-redirect=true";
    }
    
    public String novo()
    {
        pessoa=new Pessoa();
        
        return "formulario?faces-redirect=true";
    }
    
    public String salvar()
    {
        if(dao.salvar(pessoa))
        {
            Util.mensageminformacao(dao.getMensagem());
            return "listar?faces-redirect=true";
        }
        else
        {
            Util.mensagemErro(dao.getMensagem());
            return "formulario?faces-redirect=true";
        }
    }
    
    public String cancelar()
    {
        return "listar?faces-redirect=true";
    }
    
    public String editar(Integer id)
    {
        pessoa=dao.localizar(id);
        return "formulario?faces-redirect=true";
    }
    
    public void remover(Integer id)
    {
        pessoa=dao.localizar(id);
        if(dao.remover(pessoa))
        {
            Util.mensageminformacao(dao.getMensagem());
        }
        else
        {
            Util.mensagemErro(dao.getMensagem());
        }
        
    }

    public PessoaDao getDao() {
        return dao;
    }

    public void setDao(PessoaDao dao) {
        this.dao = dao;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
    
}
