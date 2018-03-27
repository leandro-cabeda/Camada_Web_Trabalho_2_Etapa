/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClassesDao;

import Classes.Acessorios;
import Classes.Carro;
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
public class CarroDao implements Serializable {

    private String mensagem;
    private EntityManager em;

    public CarroDao() 
    {
        em = EntityManagerUtil.getEntityManager();
    }

    public List<Carro> getLista() 
    {
        return em.createQuery("from Carro order by renavam").getResultList();
    }
    public List<Pessoa> getListapessoa() 
    {
        return em.createQuery("from Pessoa order by nome").getResultList();
    }
    
    public List<Acessorios> getListaacessorios() 
    {
        return em.createQuery("from Acessorios order by descricao").getResultList();
    }
    
    
    public boolean salvar(Carro obj) // Adicionar / Atualizar
    {
        
        try{
            em.getTransaction().begin();
            if(obj.getId()==null)
            {
                em.persist(obj);
                mensagem="Carro persistido com sucesso";
            }
            else
            {
                
                em.merge(obj);
                mensagem="Carro atualizado com sucesso";
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
            mensagem="Erro ao persistir carro:  "+Util.getMensagemErro(e);
            return false;
        }
    }
    
    
    public boolean remover(Carro obj) // Remover/ Apagar
    {
        
        try{
            em.getTransaction().begin();
            em.remove(obj);
            em.getTransaction().commit();
            mensagem="Carro removido com sucesso";
            return true;
        
        }catch(Exception e)
        {
            if(em.getTransaction().isActive()==false)
            {
                em.getTransaction().commit();
            }
            em.getTransaction().rollback();
            mensagem="Erro ao remover carro:  "+Util.getMensagemErro(e);
            return false;
        }
    }
    
    
    public Carro localizar(Integer id)  // Recuperar
    {
        return em.find(Carro.class, id);
    }
    
    public Pessoa localizarpessoa(Integer id)  // Recuperar
    {
        return em.find(Pessoa.class, id);
    }
    
    public Acessorios localizaracessorios(Integer id)  // Recuperar
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
