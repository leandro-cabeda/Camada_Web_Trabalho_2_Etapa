/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControleWeb;

import Classes.Acessorios;
import Classes.Carro;
import Classes.Pessoa;
import ClassesDao.AcessoriosDao;
import ClassesDao.CarroDao;
import ClassesDao.PessoaDao;
import Util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Leandro
 */
@ManagedBean(name="controlecarro")
@SessionScoped
public class ControleCarro implements Serializable {
    
    private CarroDao dao;
    private Acessorios acessorios;
    private Pessoa pessoa;
    private Carro carro;
    
    
    public ControleCarro()
    {
        dao= new CarroDao();
    }
    
    public String listar()
    {
        return "/privado/carro/listar?faces-redirect=true";
    }
    
    public String novo()
    {
        carro=new Carro();
        
        
        return "formulario?faces-redirect=true";
    }
    
    public String salvar()
    {
        if(dao.salvar(carro))
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
        carro=dao.localizar(id);
        
        return "formulario?faces-redirect=true";
    }
    
    public void remover(Integer id)
    {
        carro=dao.localizar(id);
        if(dao.remover(carro))
        {
            Util.mensageminformacao(dao.getMensagem());
        }
        else
        {
            Util.mensagemErro(dao.getMensagem());
        }
        
    }

    public CarroDao getDao() {
        return dao;
    }

    public void setDao(CarroDao dao) {
        this.dao = dao;
    }

    public Acessorios getAcessorios() {
        return acessorios;
    }

    public void setAcessorios(Acessorios acessorios) {
        this.acessorios = acessorios;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Carro getCarro() {
        return carro;
    }

    public void setCarro(Carro carro) {
        this.carro = carro;
    }

    
    
}
