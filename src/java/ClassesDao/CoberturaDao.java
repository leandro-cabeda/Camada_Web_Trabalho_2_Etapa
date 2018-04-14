/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClassesDao;

import Classes.Cobertura;
import DaoGenerico.DaoGenerico;
import java.io.Serializable;


/**
 *
 * @author Leandro
 */
public class CoberturaDao <T> extends DaoGenerico<Cobertura>  implements Serializable {

    public CoberturaDao()
    {
        super();
        // define a classe persistente
        ClassePersistente=Cobertura.class;
        
        // define o atributo padrão ao inicializar da ordem
        ordem="descricao";
    }
}
