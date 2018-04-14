/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControleWeb;

import Classes.Acessorios;
import ClassesDao.AcessorioDao;
import Util.Util;
import Util.UtilRelatorios;
import java.io.Serializable;
import java.util.HashMap;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Leandro
 */
@ManagedBean(name="controleacessorio")
@ViewScoped
public class ControleAcessorio implements Serializable {
    
    private AcessorioDao<Acessorios> dao;
    private Acessorios acessorio;
    
    
    public ControleAcessorio()
    {
        dao= new AcessorioDao<>();
    }
    
    public void imprimeAcessorio()
    {
        HashMap param= new HashMap();
        UtilRelatorios.imprimeRelatorio("relatorioAcessorio", param,dao.getListaTodos());
    }
    
    public String listar()
    {
        return "/privado/acessorio/listar?faces-redirect=true";
    }
    
    public void novo()
    {
        acessorio=new Acessorios();
        
    }
    
    public void salvar()
    {
        boolean persistiu=false;
        
        if(acessorio.getId()==null)
        {
            persistiu=dao.persist(acessorio);
        }
        else
        {
            persistiu=dao.merge(acessorio);
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
        acessorio=dao.localizar(id);
    }
    
    public void remover(Integer id)
    {
        acessorio=dao.localizar(id);
        if(dao.remove(acessorio))
        {
            Util.mensageminformacao(dao.getMensagem());
        }
        else
        {
            Util.mensagemErro(dao.getMensagem());
        }
        
    }

    public AcessorioDao<Acessorios> getDao() {
        return dao;
    }

    public void setDao(AcessorioDao<Acessorios> dao) {
        this.dao = dao;
    }

    public Acessorios getAcessorio() {
        return acessorio;
    }

    public void setAcessorio(Acessorios acessorio) {
        this.acessorio = acessorio;
    }

    
}
