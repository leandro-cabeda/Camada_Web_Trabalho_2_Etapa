/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClassesDao;

import Classes.Seguro;
import DaoGenerico.DaoGenerico;
import java.io.Serializable;


/**
 *
 * @author Leandro
 */
public class SeguroDao <T> extends DaoGenerico<Seguro>  implements Serializable {

    public SeguroDao()
    {
        super();
        // define a classe persistente
        ClassePersistente=Seguro.class;
        
        // define o atributo padrão ao inicializar da ordem
        ordem="valorFipe";
    }
}
