/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cargakdd;

import ControladorJPA.FabricaObjetos;
import ControladorJPABodega.FabricaBodega;
import javax.persistence.EntityManager;
import ControladorJPA.RetirosJpaController;
import ControladorJPABodega.RetirosBodegaJpaController;
import ControladorJPABodega.PacienteBodegaJpaController;
import ControladorJPABodega.DatesJpaController;
import ControladorJPABodega.DemografiaPacienteBodegaJpaController;
import ControladorJPABodega.exceptions.PreexistingEntityException;
import Entidades_Bodega.Dates;
import Entidades_Bodega.DemografiaPacienteBodega;
import Entidades_Bodega.PacienteBodega;
import Entidades_Bodega.RetirosBodega;
import Entidades_Bodega.RetirosBodegaPK;
import Entidades_DB.Retiros;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author USER
 */
public class RetirosCarga {

    private FabricaObjetos mi_fabrica;
    private FabricaBodega fabrica_bodega;
    RetirosJpaController controladorBD;
    RetirosBodegaJpaController controladorRetiroBodega;
    EntityManager manager;
    EntityManager manager_bodega;

    public RetirosCarga() {
        mi_fabrica = new FabricaObjetos();
        fabrica_bodega = new FabricaBodega();
        manager = mi_fabrica.crear().createEntityManager();
        manager_bodega = fabrica_bodega.crear().createEntityManager();
        controladorBD = new RetirosJpaController(mi_fabrica.getFactory());
        controladorRetiroBodega = new RetirosBodegaJpaController(fabrica_bodega.getFactory());



    }

    public void carga() throws PreexistingEntityException, Exception {
        List lista;
        PacienteBodegaJpaController paciente = new PacienteBodegaJpaController(fabrica_bodega.getFactory());
        DemografiaPacienteBodegaJpaController demografia = new DemografiaPacienteBodegaJpaController(fabrica_bodega.getFactory());
        DatesJpaController date = new DatesJpaController(fabrica_bodega.getFactory());

        // lista = manager.createQuery("SELECT * FROM medicamentos m").getResultList();
        lista = controladorBD.findRetirosEntities();
        for (int i = 0; i < lista.size(); i++) {

            Retiros retiro = (Retiros) lista.get(i);
            System.out.println("cod retiro "+retiro.getIDRetiro()); 
            
           
            String buscar=retiro.getIDUsuario().toString();
            String user="";
              if (buscar.length() == 42) {
                user=retiro.getIDUsuario().toString().substring(31, 40);
               
            }
              if (buscar.length() == 41) {
                user=retiro.getIDUsuario().toString().substring(31, 39);
               
            }
              
              if (buscar.length() == 40) {
                user=retiro.getIDUsuario().toString().substring(31, 38);
               
            }
              
              if (buscar.length() == 39) {
                user=retiro.getIDUsuario().toString().substring(31, 37);
               
            }                           
                           
              
            //Obtener codigo Depto
    
            System.out.println("Id "+user);
            PacienteBodega p = paciente.consultarPorId(user);
            System.out.println("llave "+p.getPacienteKey());
            DemografiaPacienteBodega demog= demografia.findDemografiaPacienteBodega(p.getPacienteKey());
            Dates f=date.consultar(retiro.getFechaRetiro().toString());

            RetirosBodega retiroNuevo = new RetirosBodega();
            RetirosBodegaPK pk=new RetirosBodegaPK(p.getPacienteKey(),demog.getDemografiaPacienteKey(),f.getDateId());
            //para aÃ±adir campos

            retiroNuevo.setDates(f);
            retiroNuevo.setDemografiaPacienteBodega(demog);
            retiroNuevo.setPacienteBodega(p);
            retiroNuevo.setRetirosBodegaPK(pk);


            controladorRetiroBodega.create(retiroNuevo);

        }
    }
}
