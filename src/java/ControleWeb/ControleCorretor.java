/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControleWeb;

import Classes.Corretor;
import ClassesDao.CorretorDao;
import Util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Leandro
 */
@ManagedBean(name="controlecorretor")
@SessionScoped
public class ControleCorretor implements Serializable {
    
    private CorretorDao dao;
    private Corretor corretor;
    
    
    public ControleCorretor()
    {
        dao= new CorretorDao();
    }
    
    public String listar()
    {
        return "/privado/corretor/listar?faces-redirect=true";
    }
    
    public String novo()
    {
        corretor=new Corretor();
        
        return "formulario?faces-redirect=true";
    }
    
    public String salvar()
    {
        if(dao.salvar(corretor))
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
        corretor=dao.localizar(id);
        return "formulario?faces-redirect=true";
    }
    
    public void remover(Integer id)
    {
        corretor=dao.localizar(id);
        if(dao.remover(corretor))
        {
            Util.mensageminformacao(dao.getMensagem());
        }
        else
        {
            Util.mensagemErro(dao.getMensagem());
        }
        
    }

    public CorretorDao getDao() {
        return dao;
    }

    public void setDao(CorretorDao dao) {
        this.dao = dao;
    }

    public Corretor getCorretor() {
        return corretor;
    }

    public void setCorretor(Corretor corretor) {
        this.corretor = corretor;
    }
    
}
