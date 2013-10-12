/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cargakdd;

import ControladorJPA.FabricaObjetos;

import ControladorJPABodega.FabricaBodega;
import ControladorJPABodega.IpsBodegaJpaController;
import ControladorJPABodega.MedicoBodegaJpaController;
import ControladorJPA.MedicoJpaController;
import ControladorJPABodega.exceptions.PreexistingEntityException;
import Entidades_Bodega.IpsBodega;
import Entidades_Bodega.IpsBodega_;
import Entidades_Bodega.MedicoBodega;
import Entidades_DB.Medico;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author USER
 */
public class MedicoCarga {

    private FabricaObjetos mi_fabrica;
    private FabricaBodega fabrica_bodega;
    MedicoJpaController controladorBD;
    MedicoBodegaJpaController controladorBodega;
    EntityManager manager;
    EntityManager manager_bodega;

    public MedicoCarga() {
        mi_fabrica = new FabricaObjetos();
        fabrica_bodega = new FabricaBodega();
        manager = mi_fabrica.crear().createEntityManager();
        manager_bodega = fabrica_bodega.crear().createEntityManager();
        controladorBD = new MedicoJpaController(mi_fabrica.getFactory());
        controladorBodega = new MedicoBodegaJpaController(fabrica_bodega.getFactory());
       
    
    }

    public void carga() throws PreexistingEntityException, Exception {
        List lista;
        IpsBodegaJpaController controladorIPS = new IpsBodegaJpaController(fabrica_bodega.getFactory());

        lista = controladorBD.findMedicoEntities();
        for (int i = 0; i < lista.size(); i++) {

            Medico m = (Medico) lista.get(i);
           // System.out.println(m.getCedula());
            //crear nuevo objeto
            
            String id=m.getIdIps().toString();
            IpsBodega ips=controladorIPS.consultarPorId(id);
            
            MedicoBodega medicoNuevo = new MedicoBodega();
            //para aÃ±adir campos al medicamento

            medicoNuevo.setMedicoKey(i + 1);
            medicoNuevo.setIdMedico(m.getCedula().toString());
            medicoNuevo.setNombre(m.getNombre().toString());
            medicoNuevo.setEspecialidad(m.getEspecialidad().toString());

                        
            if (!m.getSubespecialidad().equals("")) {
                medicoNuevo.setSubespecialidad(m.getSubespecialidad().toString()); 
                  
            } else {                
                medicoNuevo.setSubespecialidad("No tiene");
            }
            if (!m.getDireccionConsultorio().equals(" ")) {
                medicoNuevo.setDireccion(m.getDireccionConsultorio().toString());
            } else {
                medicoNuevo.setDireccion("Direccion No Registrada");
            }
            medicoNuevo.setIpsNombre(ips.getNombre());
            medicoNuevo.setIpsId(m.getIdIps().toString());

            controladorBodega.create(medicoNuevo);

        }

    }
}
