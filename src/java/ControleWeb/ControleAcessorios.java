/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControleWeb;

import Classes.Acessorios;
import ClassesDao.AcessoriosDao;
import Util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Leandro
 */
@ManagedBean(name="controleacessorios")
@SessionScoped
public class ControleAcessorios implements Serializable {
    
    private AcessoriosDao dao;
    private Acessorios acessorios;
    
    
    public ControleAcessorios()
    {
        dao= new AcessoriosDao();
    }
    
    public String listar()
    {
        return "/privado/acessorios/listar?faces-redirect=true";
    }
    
    public String novo()
    {
        acessorios=new Acessorios();
        
        return "formulario?faces-redirect=true";
    }
    
    public String salvar()
    {
        if(dao.salvar(acessorios))
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
        acessorios=dao.localizar(id);
        return "formulario?faces-redirect=true";
    }
    
    public void remover(Integer id)
    {
       acessorios=dao.localizar(id);
        if(dao.remover(acessorios))
        {
            Util.mensageminformacao(dao.getMensagem());
        }
        else
        {
            Util.mensagemErro(dao.getMensagem());
        }
        
    }

    public AcessoriosDao getDao() {
        return dao;
    }

    public void setDao(AcessoriosDao dao) {
        this.dao = dao;
    }

    public Acessorios getAcessorios() {
        return acessorios;
    }

    public void setAcessorios(Acessorios acessorios) {
        this.acessorios = acessorios;
    }

    
    
}
