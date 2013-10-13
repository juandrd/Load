/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cargakdd;

import ControladorJPA.FabricaObjetos;
import ControladorJPABodega.FabricaBodega;
import javax.persistence.EntityManager;
import ControladorJPA.PreexistenciasJpaController;
import ControladorJPABodega.PreexistenciaBodegaJpaController;
import ControladorJPABodega.exceptions.PreexistingEntityException;
import Entidades_Bodega.PreexistenciaBodega;
import Entidades_DB.Preexistencias;
import java.util.List;

/**
 *
 * @author USER
 */
public class PreexistenciaCarga {

    private FabricaObjetos mi_fabrica;
    private FabricaBodega fabrica_bodega;
    PreexistenciasJpaController controladorBD;
    PreexistenciaBodegaJpaController controladorBodega;
    EntityManager manager;
    EntityManager manager_bodega;

    public PreexistenciaCarga() {
        mi_fabrica = new FabricaObjetos();
        fabrica_bodega = new FabricaBodega();
        manager = mi_fabrica.crear().createEntityManager();
        manager_bodega = fabrica_bodega.crear().createEntityManager();
        controladorBD = new PreexistenciasJpaController(mi_fabrica.getFactory());
        controladorBodega = new PreexistenciaBodegaJpaController(fabrica_bodega.getFactory());
    }

    public void carga() throws PreexistingEntityException, Exception {
        List lista;
        // lista = manager.createQuery("SELECT * FROM medicamentos m").getResultList();
        lista = controladorBD.findPreexistenciasEntities();
        for (int i = 0; i < lista.size(); i++) {
//            System.out.println("-------AQUII----"+i);
            Preexistencias pre = (Preexistencias) lista.get(i);
            System.out.println(pre.getIDUsuario());
            //crear nuevo objeto

            PreexistenciaBodega preexistenciaNueva = new PreexistenciaBodega();
            //para aÃ±adir campos al medicamento

            preexistenciaNueva.setPreexistenciaKey(i + 1);
            preexistenciaNueva.setIdPaciente(pre.getIDUsuario().toString());
            preexistenciaNueva.setFamiliar(pre.getFamiliar().toString());
            preexistenciaNueva.setEnfermedad(pre.getEnfermedad().toString());
           

           controladorBodega.create(preexistenciaNueva);

        }
    }
    
    }
