/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControleWeb;

import Classes.Acessorios;
import Classes.Carro;
import Classes.Pessoa;
import ClassesDao.AcessorioDao;
import ClassesDao.CarroDao;
import ClassesDao.PessoaDao;
import Util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Leandro
 */
@ManagedBean(name="controlecarro")
@ViewScoped
public class ControleCarro implements Serializable {
    
    private Carro carro;
    private CarroDao<Carro> dao;
    private PessoaDao<Pessoa> daoPessoa;
    private AcessorioDao<Acessorios> daoAcessorio;
    private Acessorios acessorio;
    
    
    public ControleCarro()
    {
        dao= new CarroDao<>();
        daoPessoa= new PessoaDao<>();
        daoAcessorio= new AcessorioDao<>();
    }
    
    public void adicionarConjuntos()
    {
        if(acessorio!=null)
        {
            if(!carro.getAcessorios().contains(acessorio))
            {
                carro.getAcessorios().add(acessorio);
                Util.mensageminformacao("Conjunto adicionado com sucesso!");
            }
            else
            {
                Util.mensagemErro("Este conjunto já existe na sua lista!");
            }
        }
        else
        {
            Util.mensagemErro("selecione um conjunto!");
        }
    }
    
    public void removerConjuntos(int index)
    {
        acessorio=carro.getAcessorios().get(index);
        carro.getAcessorios().remove(acessorio);
        Util.mensageminformacao("Conjunto removido com sucesso!");
    }
    
    public String listar()
    {
        return "/privado/carro/listar?faces-redirect=true";
    }
    
    public void novo()
    {
        carro=new Carro();
        
    }
    
    public void salvar()
    {
        boolean persistiu=false;
        
        if(carro.getId()==null)
        {
            persistiu=dao.persist(carro);
        }
        else
        {
            persistiu=dao.merge(carro);
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
        carro=dao.localizar(id);
    }
    
    public void remover(Integer id)
    {
        carro=dao.localizar(id);
        if(dao.remove(carro))
        {
            Util.mensageminformacao(dao.getMensagem());
        }
        else
        {
            Util.mensagemErro(dao.getMensagem());
        }
        
    }

    public Carro getCarro() {
        return carro;
    }

    public void setCarro(Carro carro) {
        this.carro = carro;
    }

    public CarroDao<Carro> getDao() {
        return dao;
    }

    public void setDao(CarroDao<Carro> dao) {
        this.dao = dao;
    }


    public AcessorioDao<Acessorios> getDaoAcessorio() {
        return daoAcessorio;
    }

    public void setDaoAcessorio(AcessorioDao<Acessorios> daoAcessorio) {
        this.daoAcessorio = daoAcessorio;
    }

    public Acessorios getAcessorio() {
        return acessorio;
    }

    public void setAcessorio(Acessorios acessorio) {
        this.acessorio = acessorio;
    }

    public PessoaDao<Pessoa> getDaoPessoa() {
        return daoPessoa;
    }

    public void setDaoPessoa(PessoaDao<Pessoa> daoPessoa) {
        this.daoPessoa = daoPessoa;
    }

    
}
