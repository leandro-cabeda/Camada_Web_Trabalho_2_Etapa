/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClassesDao;

import Classes.Carro;
import DaoGenerico.DaoGenerico;
import java.io.Serializable;


/**
 *
 * @author Leandro
 */
public class CarroDao <T> extends DaoGenerico<Carro>  implements Serializable {

    public CarroDao()
    {
        super();
        // define a classe persistente
        ClassePersistente=Carro.class;
        
        // define o atributo padrão ao inicializar da ordem
        ordem="placa";
    }
}
