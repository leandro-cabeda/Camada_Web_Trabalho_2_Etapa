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
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Leandro
 */
@ManagedBean(name="controlepessoa")
@ViewScoped
public class ControlePessoa implements Serializable {
    
    private PessoaDao<Pessoa> dao;
    private Pessoa pessoa;
    
    
    public ControlePessoa()
    {
        dao= new PessoaDao<>();
    }
    
    public String listar()
    {
        return "/privado/pessoa/listar?faces-redirect=true";
    }
    
    public void novo()
    {
        pessoa=new Pessoa();
        
    }
    
    public void salvar()
    {
        boolean persistiu=false;
        
        if(pessoa.getId()==null)
        {
            persistiu=dao.persist(pessoa);
        }
        else
        {
            persistiu=dao.merge(pessoa);
        }
        if(persistiu)
        {
            Util.mensageminformacao(dao.getMensagem());
        }
        else
        {
            Util.mensagemErro(dao.getMensagem());
        }
    }
    
    
    public void editar(Integer id)
    {
        pessoa=dao.localizar(id);
    }
    
    public void remover(Integer id)
    {
        pessoa=dao.localizar(id);
        if(dao.remove(pessoa))
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
