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
@ManagedBean(name="controlelogin")
@SessionScoped
public class ControleLogin implements Serializable {
    
    private CorretorDao<Corretor> dao;
    private Corretor usuarioLogado;
    private String usuario;
    private String senha;

    public ControleLogin() {
        dao=new CorretorDao<>();
    }
    
    public String paginaLogin()
    {
        return "login?faces-redirect=true";
    }
    
    public String efetuarLogin()
    {
        if(dao.login(usuario, senha))
        {
           usuarioLogado=dao.lozalicaPorNomeUsuario(usuario);
           Util.mensageminformacao("Login realizado com sucesso!");
           usuario="";
           senha="";
           return "/index?faces-redirect=true";
        }
        else
        {
            Util.mensagemErro("Usuário ou senha inválidos!");
            return "login?faces-redirect=true";
        }
    }
    
    public String efetuarLogout(){
        usuarioLogado=null;
        Util.mensageminformacao("Logout realizado com sucesso!");
        return "/index?faces-redirect=true";
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public CorretorDao<Corretor> getDao() {
        return dao;
    }

    public void setDao(CorretorDao<Corretor> dao) {
        this.dao = dao;
    }

    public Corretor getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Corretor usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }
    
}
