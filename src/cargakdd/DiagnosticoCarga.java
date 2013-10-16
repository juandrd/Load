/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cargakdd;

import ControladorJPA.FabricaObjetos;
import ControladorJPA.CitasGeneralesJpaController;
import ControladorJPA.HospitalizacionesJpaController;
import ControladorJPA.RemisionesJpaController;
import ControladorJPA.UrgenciasJpaController;
import ControladorJPABodega.exceptions.PreexistingEntityException;
/**
 *
 * @author USER
 */
public class DiagnosticoCarga {
    
    
     private FabricaObjetos mi_fabrica;
     UrgenciasJpaController urgenciaControlador;
     CitasGeneralesJpaController citaControlador;
     HospitalizacionesJpaController hospitalizacionControlador;
     RemisionesJpaController remisionControlador;
     

    public DiagnosticoCarga() {
        mi_fabrica = new FabricaObjetos();
        citaControlador=new CitasGeneralesJpaController(mi_fabrica.getFactory());
        urgenciaControlador=new UrgenciasJpaController(mi_fabrica.getFactory());
        hospitalizacionControlador=new HospitalizacionesJpaController(mi_fabrica.getFactory());
         remisionControlador=new RemisionesJpaController(mi_fabrica.getFactory());
    }
     
     public void carga() throws PreexistingEntityException, Exception{
         
         citaControlador.crearDiagnostico();
         urgenciaControlador.crearDiagnostico();
         hospitalizacionControlador.crearDiagnostico();
         remisionControlador.crearDiagnostico();
         
     }
}
