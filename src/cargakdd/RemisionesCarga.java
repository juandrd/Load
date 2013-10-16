/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cargakdd;

import ControladorJPA.FabricaObjetos;
import ControladorJPABodega.FabricaBodega;
import ControladorJPABodega.*;
import ControladorJPA.RemisionesJpaController;
import ControladorJPABodega.exceptions.PreexistingEntityException;
import Entidades_Bodega.Dates;
import Entidades_Bodega.DemografiaPacienteBodega;
import Entidades_Bodega.DiagnosticoBodega;
import Entidades_Bodega.EmpresaBodega;
import Entidades_Bodega.IpsBodega;
import Entidades_Bodega.MedicoBodega;
import Entidades_Bodega.PacienteBodega;
import Entidades_Bodega.PreexistenciaBodega;
import Entidades_Bodega.RemisionesBodega;
import Entidades_Bodega.RemisionesBodegaPK;
import Entidades_Bodega.ServicioPosBodega;
import Entidades_DB.Remisiones;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author USER
 */
public class RemisionesCarga {

    private FabricaObjetos mi_fabrica;
    private FabricaBodega fabrica_bodega;
    RemisionesJpaController controladorBD;
    RemisionesBodegaJpaController controladorRemisionesBodega;
    EntityManager manager;
    EntityManager manager_bodega;

    public RemisionesCarga() {
        mi_fabrica = new FabricaObjetos();
        fabrica_bodega = new FabricaBodega();
        manager = mi_fabrica.crear().createEntityManager();
        manager_bodega = fabrica_bodega.crear().createEntityManager();
        controladorBD = new RemisionesJpaController(mi_fabrica.getFactory());
        controladorRemisionesBodega = new RemisionesBodegaJpaController(fabrica_bodega.getFactory());
    }

    public void carga() throws PreexistingEntityException, Exception {
        List lista;

        PacienteBodegaJpaController paciente = new PacienteBodegaJpaController(fabrica_bodega.getFactory());
        DemografiaPacienteBodegaJpaController demografia = new DemografiaPacienteBodegaJpaController(fabrica_bodega.getFactory());
        DatesJpaController date = new DatesJpaController(fabrica_bodega.getFactory());
        PreexistenciaBodegaJpaController preexistencia = new PreexistenciaBodegaJpaController(fabrica_bodega.getFactory());
        DiagnosticoBodegaJpaController diagnostico = new DiagnosticoBodegaJpaController(fabrica_bodega.getFactory());
        MedicoBodegaJpaController medico = new MedicoBodegaJpaController(fabrica_bodega.getFactory());
        IpsBodegaJpaController ips = new IpsBodegaJpaController(fabrica_bodega.getFactory());
        ServicioPosBodegaJpaController servicioPOS = new ServicioPosBodegaJpaController(fabrica_bodega.getFactory());


        int contador = 0;
        lista = controladorBD.findRemisionesEntities();
        for (int i = 0; i < lista.size(); i++) {

            Remisiones remision = (Remisiones) lista.get(i);

            String user = remision.getIDUsuario().toString();

//
//            System.out.println("Remision " + remision.getCodigoRemision());

//            System.err.println("Id " + user);

            PacienteBodega p = paciente.consultarPorId(user);
            DemografiaPacienteBodega demog = demografia.findDemografiaPacienteBodega(p.getPacienteKey());

            Dates fecha_solicitud = date.consultar(remision.getFechaRemision().toString());
            Dates fecha_atencion = date.consultar(remision.getFechaAtencion().toString());

            long diasDiferencia = date.diferenciaDias(fecha_atencion, fecha_solicitud);

           
            PreexistenciaBodega pre = preexistencia.consultar(user);
            MedicoBodega med = medico.consultar(remision.getIDMedico().getCedula().toString());
            MedicoBodega medRemitente = medico.consultar(remision.getIDMedicoRemite().getCedula().toString());


            DiagnosticoBodega diag = diagnostico.consultar(remision.getDiagnostico().toString());
            String id_ips = med.getIpsId();
            IpsBodega ipsEntidad = ips.consultarPorId(id_ips);
            ServicioPosBodega sPOS = servicioPOS.consultar(remision.getServicioPOS().getIDServicioPOS().toString());
//  
//            System.out.println("Id paciente " + p.getPacienteKey());
//            System.out.println("Demografia " + demog.getDemografiaPacienteKey());
//            System.out.println("Fecha Soli " + fecha_solicitud.getDateId());
//            System.out.println("Fecha Aten " + fecha_atencion.getDateId());
//            System.out.println("IPs " + ipsEntidad.getIpsKey());
//            System.out.println("Diag "+diag.getDiagnosticoKey());
//            System.out.println("Preex "+pre.getPreexistenciaKey());
//            System.out.println("SPOS "+sPOS.getServicioPosKey());
//            System.out.println("MEDICO REM "+medRemitente.getMedicoKey());
//            System.out.println("Medico at "+med.getMedicoKey());
            
            RemisionesBodega remisionNueva = new RemisionesBodega();
            RemisionesBodegaPK pk;
            pk = new RemisionesBodegaPK(med.getMedicoKey(), medRemitente.getMedicoKey(), p.getPacienteKey(),
                    demog.getDemografiaPacienteKey(), fecha_solicitud.getDateId(),
                    fecha_atencion.getDateId(), diag.getDiagnosticoKey(), pre.getPreexistenciaKey(), sPOS.getServicioPosKey()
                    , ipsEntidad.getIpsKey());

            //para aÃ±adir campos

       
         
     remisionNueva.setDates(fecha_atencion);
                 remisionNueva.setDates1(fecha_solicitud);
            remisionNueva.setDemografiaPacienteBodega(demog);
            remisionNueva.setPacienteBodega(p);
            remisionNueva.setServicioPosBodega(sPOS);
            remisionNueva.setPreexistenciaBodega(pre);
            remisionNueva.setDiagnosticoBodega(diag);            
            remisionNueva.setIpsBodega(ipsEntidad);
            remisionNueva.setMedicoBodega(medRemitente);
            remisionNueva.setMedicoBodega1(med);
            remisionNueva.setTiempoEsperaRemision((int) diasDiferencia);

           controladorRemisionesBodega.create(remisionNueva);
            contador++;
            System.err.println(contador);

        }
    }
     public long diferenciaDias(Dates fAnterior, Dates fPosterior){
        java.util.Date fechaAnterior = null, fechaPosterior;
        fechaAnterior=fAnterior.getDate();
        fechaPosterior=fPosterior.getDate();
        long diferencia= ( fechaAnterior.getTime() - fechaPosterior.getTime() )/ 86400000/*milisegundos por dia*/; 
       // System.out.println("f anterior "+fechaAnterior+",f posterior "+fechaPosterior+", diferencia "+diferencia);
      
        return diferencia;
     }
    
}
