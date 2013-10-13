/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cargakdd;

import ControladorJPABodega.exceptions.PreexistingEntityException;
import cargaKdd.MedicamentoCarga;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USER
 */
public class CargaKdd {
  
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //CARGA DE LOS MEDICAMENTOS
//        MedicamentoCarga medicamento=new MedicamentoCarga();
//        try {
//            medicamento.carga();
//        } catch (PreexistingEntityException ex) {
//            Logger.getLogger(CargaKdd.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (Exception ex) {
//            Logger.getLogger(CargaKdd.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
        
        //-------------------------------
        
        //CARGA DE IPS
//        IPSCarga ips=new IPSCarga();
//        try {
//            ips.carga();
//        } catch (PreexistingEntityException ex) {
//            Logger.getLogger(CargaKdd.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (Exception ex) {
//            Logger.getLogger(CargaKdd.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
        //-------------------------------
        
        //CARGA DE MEDICO        
             
//        MedicoCarga medico=new MedicoCarga();
//        try {
//            medico.carga();
//        } catch (PreexistingEntityException ex) {
//            Logger.getLogger(CargaKdd.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (Exception ex) {
//            Logger.getLogger(CargaKdd.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
        //-------------------------------
        
        //Carga Paciente y Demografia
         PacienteYDemografiaCarga paciente=new PacienteYDemografiaCarga();
        try {
            paciente.carga();
        } catch (PreexistingEntityException ex) {
            Logger.getLogger(CargaKdd.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(CargaKdd.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
