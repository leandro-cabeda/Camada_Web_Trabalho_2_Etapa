/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClassesDao;

import Classes.Sinistro;
import DaoGenerico.DaoGenerico;
import java.io.Serializable;


/**
 *
 * @author Leandro
 */
public class SinistroDao <T> extends DaoGenerico<Sinistro>  implements Serializable {

    public SinistroDao()
    {
        super();
        // define a classe persistente
        ClassePersistente=Sinistro.class;
        
        // define o atributo padrão ao inicializar da ordem
        ordem="descricao";
    }
}
