/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClassesDao;

import Classes.Acessorios;
import EntityManagerUtil.EntityManagerUtil;
import Util.Util;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Leandro
 */
public class AcessoriosDao {
    
    private String mensagem;
    private EntityManager em;

    public AcessoriosDao() 
    {
        em = EntityManagerUtil.getEntityManager();
    }

    public List<Acessorios> getLista() 
    {
        return em.createQuery("from Acessorios order by id").getResultList();
    }
    
    public boolean salvar(Acessorios obj) // Adicionar / Atualizar
    {
        
        try{
            em.getTransaction().begin();
            if(obj.getId()==null)
            {
                em.persist(obj);
                mensagem="Acessorios persistido com sucesso";
            }
            else
            {
                em.merge(obj);
                mensagem="Acessorios atualizado com sucesso";
                
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
            mensagem="Erro ao persistir Acessorios:  "+Util.getMensagemErro(e);
            return false;
        }
    }
    
    public boolean remover(Acessorios obj) // Remover/ Apagar
    {
        
        try{
            em.getTransaction().begin();
            em.remove(obj);
            em.getTransaction().commit();
            mensagem="Acessorios removido com sucesso";
            return true;
        
        }catch(Exception e)
        {
            if(em.getTransaction().isActive()==false)
            {
                em.getTransaction().commit();
            }
            em.getTransaction().rollback();
            mensagem="Erro ao remover acessorios:  "+Util.getMensagemErro(e);
            return false;
        }
    }
    
    
    public Acessorios localizar(Integer id)  // Recuperar
    {
        return em.find(Acessorios.class, id);
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
