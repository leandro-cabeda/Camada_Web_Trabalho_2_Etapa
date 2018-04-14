/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControleWeb;

import Classes.Cobertura;
import Classes.Seguro;
import ClassesDao.CoberturaDao;
import ClassesDao.SeguroDao;
import Util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Leandro
 */
@ManagedBean(name="controlecobertura")
@ViewScoped
public class ControleCobertura implements Serializable {
    
    private CoberturaDao<Cobertura> dao;
    private Cobertura cobertura;
    private SeguroDao<Seguro> daoSeguro;
    
    
    public ControleCobertura()
    {
        dao= new CoberturaDao<>();
        daoSeguro=new SeguroDao<>();
    }
    
    public String listar()
    {
        return "/privado/cobertura/listar?faces-redirect=true";
    }
    
    public void novo()
    {
        cobertura=new Cobertura();
        
    }
    
    public void salvar()
    {
        boolean persistiu=false;
        
        if(cobertura.getId()==null)
        {
            persistiu=dao.persist(cobertura);
        }
        else
        {
            persistiu=dao.merge(cobertura);
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
        cobertura=dao.localizar(id);
    }
    
    public void remover(Integer id)
    {
        cobertura=dao.localizar(id);
        if(dao.remove(cobertura))
        {
            Util.mensageminformacao(dao.getMensagem());
        }
        else
        {
            Util.mensagemErro(dao.getMensagem());
        }
        
    }

    public CoberturaDao<Cobertura> getDao() {
        return dao;
    }

    public void setDao(CoberturaDao<Cobertura> dao) {
        this.dao = dao;
    }

    public Cobertura getCobertura() {
        return cobertura;
    }

    public void setCobertura(Cobertura cobertura) {
        this.cobertura = cobertura;
    }

    public SeguroDao<Seguro> getDaoSeguro() {
        return daoSeguro;
    }

    public void setDaoSeguro(SeguroDao<Seguro> daoSeguro) {
        this.daoSeguro = daoSeguro;
    }

   

    
}
