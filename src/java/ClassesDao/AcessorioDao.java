/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClassesDao;

import Classes.Acessorios;
import DaoGenerico.DaoGenerico;
import java.io.Serializable;


/**
 *
 * @author Leandro
 */
public class AcessorioDao <T> extends DaoGenerico<Acessorios>  implements Serializable {

    public AcessorioDao()
    {
        super();
        // define a classe persistente
        ClassePersistente=Acessorios.class;
        
        // define o atributo padrão ao inicializar da ordem
        ordem="descricao";
    }
}
