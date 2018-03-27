/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClassesDao;

import Classes.Corretor;
import EntityManagerUtil.EntityManagerUtil;
import Util.Util;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Leandro
 */
public class CorretorDao implements Serializable {

    private String mensagem;
    private EntityManager em;

    public CorretorDao() 
    {
        em = EntityManagerUtil.getEntityManager();
    }

    public List<Corretor> getLista() 
    {
        return em.createQuery("from Corretor order by nome").getResultList();
    }
    
    public boolean salvar(Corretor obj) // Adicionar / Atualizar
    {
        
        try{
            em.getTransaction().begin();
            if(obj.getId()==null)
            {
                em.persist(obj);
                mensagem="Corretor persistido com sucesso";
            }
            else
            {
                em.merge(obj);
                mensagem="Corretor atualizado com sucesso";
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
            mensagem="Erro ao persistir corretor:  "+Util.getMensagemErro(e);
            return false;
        }
    }
    
    public boolean remover(Corretor obj) // Remover/ Apagar
    {
        
        try{
            em.getTransaction().begin();
            em.remove(obj);
            em.getTransaction().commit();
            mensagem="Corretor removido com sucesso";
            return true;
        
        }catch(Exception e)
        {
            if(em.getTransaction().isActive()==false)
            {
                em.getTransaction().commit();
            }
            em.getTransaction().rollback();
            mensagem="Erro ao remover corretor:  "+Util.getMensagemErro(e);
            return false;
        }
    }
    
    
    public Corretor localizar(Integer id)  // Recuperar
    {
        return em.find(Corretor.class, id);
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
