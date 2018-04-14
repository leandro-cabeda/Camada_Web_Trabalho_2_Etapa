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
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Leandro
 */
@ManagedBean(name="controlecorretor")
@ViewScoped
public class ControleCorretor implements Serializable {
    
    private CorretorDao<Corretor> dao;
    private Corretor corretor;
    
    
    public ControleCorretor()
    {
        dao= new CorretorDao<>();
    }
    
    public String listar()
    {
        return "/privado/corretor/listar?faces-redirect=true";
    }
    
    public void novo()
    {
        corretor=new Corretor();
        
    }
    
    public void salvar()
    {
        boolean persistiu=false;
        
        if(corretor.getId()==null)
        {
            persistiu=dao.persist(corretor);
        }
        else
        {
            persistiu=dao.merge(corretor);
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
        corretor=dao.localizar(id);
    }
    
    public void remover(Integer id)
    {
        corretor=dao.localizar(id);
        if(dao.remove(corretor))
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
