/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cargakdd;

import ControladorJPA.FabricaObjetos;
import ControladorJPABodega.FabricaBodega;
import ControladorJPABodega.ServicioPosBodegaJpaController;
import ControladorJPA.ServiciosPosJpaController;
import ControladorJPABodega.exceptions.PreexistingEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import Entidades_Bodega.ServicioPosBodega;
import Entidades_DB.ServiciosPos;

/**
 *
 * @author USER
 */
public class ServicioPOSCarga {

    private FabricaObjetos mi_fabrica;
    private FabricaBodega fabrica_bodega;
    ServiciosPosJpaController controladorBD;
    ServicioPosBodegaJpaController controladorBodega;
    EntityManager manager;
    EntityManager manager_bodega;

    public ServicioPOSCarga() {
        mi_fabrica = new FabricaObjetos();
        fabrica_bodega = new FabricaBodega();
        manager = mi_fabrica.crear().createEntityManager();
        manager_bodega = fabrica_bodega.crear().createEntityManager();
        controladorBD = new ServiciosPosJpaController(mi_fabrica.getFactory());
        controladorBodega = new ServicioPosBodegaJpaController(fabrica_bodega.getFactory());
    }
    
    public void carga() throws PreexistingEntityException, Exception {
        List lista;
        // lista = manager.createQuery("SELECT * FROM medicamentos m").getResultList();
        lista = controladorBD.findServiciosPosEntities();
        for (int i = 0; i < lista.size(); i++) {
//            System.out.println("-------AQUII----"+i);
            ServiciosPos sPOS = (ServiciosPos) lista.get(i);
            System.out.println(sPOS.getIDServicioPOS());
            //crear nuevo objeto

            ServicioPosBodega preexistenciaNueva = new ServicioPosBodega();
            //para aÃ±adir campos al medicamento

            preexistenciaNueva.setServicioPosKey(i + 1);
            preexistenciaNueva.setCodServicio(sPOS.getIDServicioPOS().toString());
            preexistenciaNueva.setDescripcion(sPOS.getDescripcion().toString());
            preexistenciaNueva.setCosto(Integer.parseInt(sPOS.getCosto().toString()));
           

           controladorBodega.create(preexistenciaNueva);

        }
    }
}
