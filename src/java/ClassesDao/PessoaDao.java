/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClassesDao;

import Classes.Pessoa;
import EntityManagerUtil.EntityManagerUtil;
import Util.Util;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Leandro
 */
public class PessoaDao implements Serializable {

    private String mensagem;
    private EntityManager em;

    public PessoaDao() 
    {
        em = EntityManagerUtil.getEntityManager();
    }

    public List<Pessoa> getLista() 
    {
        return em.createQuery("from Pessoa order by nome").getResultList();
    }
    
    public boolean salvar(Pessoa obj) // Adicionar / Atualizar
    {
        
        try{
            em.getTransaction().begin();
            if(obj.getId()==null)
            {
                em.persist(obj);
                mensagem="Pessoa persistido com sucesso";
            }
            else
            {
                em.merge(obj);
                mensagem="Pessoa atualizado com sucesso";
            }
            em.getTransaction().commit();
            return true;
        
        }catch(Exception e)
        {
            if(em.getTransaction().isActive()==false)
            {
                em.getTransaction().commit();
            }
            em.getTransaction().rollback();
            mensagem="Erro ao persistir pessoa:  "+Util.getMensagemErro(e);
            return false;
        }
    }
    
    public boolean remover(Pessoa obj) // Remover/ Apagar
    {
        
        try{
            em.getTransaction().begin();
            em.remove(obj);
            em.getTransaction().commit();
            mensagem="Pessoa removido com sucesso";
            return true;
        
        }catch(Exception e)
        {
            if(em.getTransaction().isActive()==false)
            {
                em.getTransaction().commit();
            }
            em.getTransaction().rollback();
            mensagem="Erro ao remover pessoa:  "+Util.getMensagemErro(e);
            return false;
        }
    }
    
    
    public Pessoa localizar(Integer id)  // Recuperar
    {
        return em.find(Pessoa.class, id);
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

}
