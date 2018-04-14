/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControleWeb;

import Classes.Carro;
import Classes.Cobertura;
import Classes.Corretor;
import Classes.Seguro;
import Classes.Sinistro;
import ClassesDao.CarroDao;
import ClassesDao.CorretorDao;
import ClassesDao.SeguroDao;
import Util.Util;
import Util.UtilRelatorios;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Leandro
 */
@ManagedBean(name="controleseguro")
@ViewScoped
public class ControleSeguro implements Serializable {
    
    private Seguro seguro;
    private SeguroDao<Seguro> dao;
    private CarroDao<Carro> daoCarro;
    private CorretorDao<Corretor> daoCorretor;
    private Cobertura cobertura;
    private Sinistro sinistro;
    private Boolean novaCobertura;
    private Boolean novoSinistro;
    
    
    public ControleSeguro()
    {
        dao= new SeguroDao<>();
        daoCarro= new CarroDao<>();
        daoCorretor= new CorretorDao<>();
    }
    
    public void imprimeSeguro(Integer id)
    {
        seguro=dao.localizar(id);
        List<Seguro> lista = new ArrayList<>();
        lista.add(seguro);
        HashMap param= new HashMap();
        UtilRelatorios.imprimeRelatorio("relatorioSeguro", param,lista);
    }
    
     public void novaCobertura()
    {
        cobertura=new Cobertura();
        novaCobertura=true;
    }
    
    public void alterarCobertura(int index)
    {
        cobertura=seguro.getCobertura().get(index);
        novaCobertura=false;
    }
    
    public void salvarCobertura()
    {
        if(novaCobertura)
        {
            seguro.adicionarCobertura(cobertura);
        }
        Util.mensageminformacao("Cobertura atualizado com sucesso!");
    }
    
    public void removerCobertura(int index)
    {
        seguro.removerCobertura(index);
        Util.mensageminformacao("Cobertura removido com sucesso!");
    }
    
     public void novoSinistro()
    {
        sinistro=new Sinistro();
        novoSinistro=true;
    }
    
    public void alterarSinistro(int index)
    {
        sinistro=seguro.getSinistro().get(index);
        novoSinistro=false;
    }
    
    public void salvarSinistro()
    {
        if(novoSinistro)
        {
            seguro.adicionarSinistro(sinistro);
        }
        Util.mensageminformacao("Sinistro atualizado com sucesso!");
    }
    
    public void removerSinistro(int index)
    {
        seguro.removerSinistro(index);
        Util.mensageminformacao("Sinistro removido com sucesso!");
    }
    
    public String listar()
    {
        return "/privado/seguro/listar?faces-redirect=true";
    }
    
    public void novo()
    {
        seguro=new Seguro();
    }
    
    public void salvar()
    {
        boolean persistiu=false;
        
        if(seguro.getId()==null)
        {
            persistiu=dao.persist(seguro);
        }
        else
        {
            persistiu=dao.merge(seguro);
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
        seguro=dao.localizar(id);
    }
    
    public void remover(Integer id)
    {
        seguro=dao.localizar(id);
        if(dao.remove(seguro))
        {
            Util.mensageminformacao(dao.getMensagem());
        }
        else
        {
            Util.mensagemErro(dao.getMensagem());
        }
        
    }

    public Seguro getSeguro() {
        return seguro;
    }

    public void setSeguro(Seguro seguro) {
        this.seguro = seguro;
    }

    public SeguroDao<Seguro> getDao() {
        return dao;
    }

    public void setDao(SeguroDao<Seguro> dao) {
        this.dao = dao;
    }

    public CarroDao<Carro> getDaoCarro() {
        return daoCarro;
    }

    public void setDaoCarro(CarroDao<Carro> daoCarro) {
        this.daoCarro = daoCarro;
    }

    public CorretorDao<Corretor> getDaoCorretor() {
        return daoCorretor;
    }

    public void setDaoCorretor(CorretorDao<Corretor> daoCorretor) {
        this.daoCorretor = daoCorretor;
    }

    public Cobertura getCobertura() {
        return cobertura;
    }

    public void setCobertura(Cobertura cobertura) {
        this.cobertura = cobertura;
    }

    public Sinistro getSinistro() {
        return sinistro;
    }

    public void setSinistro(Sinistro sinistro) {
        this.sinistro = sinistro;
    }

    public Boolean getNovaCobertura() {
        return novaCobertura;
    }

    public void setNovaCobertura(Boolean novaCobertura) {
        this.novaCobertura = novaCobertura;
    }

    public Boolean getNovoSinistro() {
        return novoSinistro;
    }

    public void setNovoSinistro(Boolean novoSinistro) {
        this.novoSinistro = novoSinistro;
    }

   

    
    
}
