package ControladorJPABodega;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;

/**
 *
 * 
 */
public class FabricaBodega {
        @PersistenceUnit
     //crea la fabrica de objetos, utiliza el patron de diseÃ±o factoria
    private EntityManagerFactory factory;
    
    //CONSTRUCTOR DE LA CLASE
    public FabricaBodega()
    {       
        factory = Persistence.createEntityManagerFactory("Bodega_Datos");
    }  
    
    //METODO QUE RETORNA LA FABRICA DE OBJETOS
    public EntityManagerFactory getFactory()
    {
        return factory;
    }
    
    public EntityManagerFactory crear()
    {
        return factory;
    }
    
}
