/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClassesDao;
import Classes.Corretor;
import DaoGenerico.DaoGenerico;
import java.io.Serializable;
import javax.persistence.Query;

/**
 *
 * @author Leandro
 */
public class CorretorDao <T> extends DaoGenerico<Corretor>  implements Serializable {

    public CorretorDao()
    {
        super();
        // define a classe persistente
        ClassePersistente=Corretor.class;
        
        // define o atributo padrão ao inicializar da ordem
        ordem="nome";
    }
    
    public boolean login(String usuario, String senha)
    {
        Query query=em.createQuery("from Corretor where upper(nomeUsuario)=:usuario and upper(senha)=:senha");
        query.setParameter("usuario",usuario.toUpperCase());
        query.setParameter("senha",senha.toUpperCase());
        
        if(!query.getResultList().isEmpty())
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public Corretor lozalicaPorNomeUsuario(String usuario)
    {
        Query query=em.createQuery("from Corretor where upper(nomeUsuario)=:usuario");
        
        query.setParameter("usuario",usuario.toUpperCase());
        
        return (Corretor)query.getSingleResult();
    }

}
