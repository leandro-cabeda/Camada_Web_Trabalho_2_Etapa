/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControleWeb;

import Classes.Seguro;
import Classes.Sinistro;
import ClassesDao.SinistroDao;
import ClassesDao.SeguroDao;
import Util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Leandro
 */
@ManagedBean(name="controlesinistro")
@ViewScoped
public class ControleSinistro implements Serializable {
    
    private SinistroDao<Sinistro> dao;
    private Sinistro sinistro;
    private SeguroDao<Seguro> daoSeguro;
    
    
    public ControleSinistro()
    {
        dao= new SinistroDao<>();
        daoSeguro=new SeguroDao<>();
    }
    
    public String listar()
    {
        return "/privado/sinistro/listar?faces-redirect=true";
    }
    
    public void novo()
    {
        sinistro=new Sinistro();
        
    }
    
    public void salvar()
    {
        boolean persistiu=false;
        
        if(sinistro.getId()==null)
        {
            persistiu=dao.persist(sinistro);
        }
        else
        {
            persistiu=dao.merge(sinistro);
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
        sinistro=dao.localizar(id);
    }
    
    public void remover(Integer id)
    {
        sinistro=dao.localizar(id);
        if(dao.remove(sinistro))
        {
            Util.mensageminformacao(dao.getMensagem());
        }
        else
        {
            Util.mensagemErro(dao.getMensagem());
        }
        
    }

    public SinistroDao<Sinistro> getDao() {
        return dao;
    }

    public void setDao(SinistroDao<Sinistro> dao) {
        this.dao = dao;
    }

    public Sinistro getSinistro() {
        return sinistro;
    }

    public void setSinistro(Sinistro sinistro) {
        this.sinistro = sinistro;
    }

    public SeguroDao<Seguro> getDaoSeguro() {
        return daoSeguro;
    }

    public void setDaoSeguro(SeguroDao<Seguro> daoSeguro) {
        this.daoSeguro = daoSeguro;
    }
   

    
}
