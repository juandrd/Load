/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cargakdd;

import ControladorJPA.FabricaObjetos;
import ControladorJPABodega.FabricaBodega;
import ControladorJPA.UrgenciasJpaController;
import ControladorJPABodega.DatesJpaController;
import ControladorJPABodega.DemografiaPacienteBodegaJpaController;
import ControladorJPABodega.EmpresaBodegaJpaController;
import ControladorJPABodega.PacienteBodegaJpaController;
import ControladorJPABodega.PreexistenciaBodegaJpaController;
import ControladorJPABodega.UrgenciasBodegaJpaController;
import ControladorJPABodega.exceptions.PreexistingEntityException;
import ControladorJPABodega.ServicioPosBodegaJpaController;
import ControladorJPABodega.DiagnosticoBodegaJpaController;
import ControladorJPABodega.IpsBodegaJpaController;
import ControladorJPABodega.MedicoBodegaJpaController;
import Entidades_Bodega.Dates;
import Entidades_Bodega.DemografiaPacienteBodega;
import Entidades_Bodega.EmpresaBodega;
import Entidades_Bodega.PacienteBodega;
import Entidades_Bodega.PreexistenciaBodega;
import Entidades_Bodega.UrgenciasBodega;
import Entidades_Bodega.ServicioPosBodega;
import Entidades_Bodega.DiagnosticoBodega;
import Entidades_Bodega.MedicoBodega;
import Entidades_Bodega.IpsBodega;
import Entidades_Bodega.UrgenciasBodegaPK;
import Entidades_DB.Urgencias;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author USER
 */
public class UrgenciasCarga {

    private FabricaObjetos mi_fabrica;
    private FabricaBodega fabrica_bodega;
    UrgenciasJpaController controladorBD;
    UrgenciasBodegaJpaController controladorUrgenciaBodega;
    EntityManager manager;
    EntityManager manager_bodega;

    public UrgenciasCarga() {
        mi_fabrica = new FabricaObjetos();
        fabrica_bodega = new FabricaBodega();
        manager = mi_fabrica.crear().createEntityManager();
        manager_bodega = fabrica_bodega.crear().createEntityManager();
        controladorBD = new UrgenciasJpaController(mi_fabrica.getFactory());
        controladorUrgenciaBodega = new UrgenciasBodegaJpaController(fabrica_bodega.getFactory());
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
        EmpresaBodegaJpaController empresa=new EmpresaBodegaJpaController(fabrica_bodega.getFactory());


        int contador = 0;
        lista = controladorBD.findUrgenciasEntities();
        for (int i = 0; i < lista.size(); i++) {

            Urgencias u = (Urgencias) lista.get(i);
           
            String user = u.getIDUsuario().toString();
//            System.out.println(buscar);
//            String user = "";
//            if (buscar.length() == 42) {
//                user = u.getIDUsuario().toString().substring(31, 40);
//
//            }
//            if (buscar.length() == 41) {
//                user = u.getIDUsuario().toString().substring(31, 39);
//
//            }
//
//            if (buscar.length() == 40) {
//                user = u.getIDUsuario().toString().substring(31, 38);
//
//            }
//
//            if (buscar.length() == 39) {
//                user = u.getIDUsuario().toString().substring(31, 37);
//
//            }

            System.out.println("urgencia "+u.getCodigoUrgencia());
            
//            System.err.println("Id " + user);

            PacienteBodega p = paciente.consultarPorId(user);
            // System.out.println("llave " + p.getPacienteKey());
            DemografiaPacienteBodega demog = demografia.findDemografiaPacienteBodega(p.getPacienteKey());

            Dates fecha_solicitud = date.consultar(u.getFechaSolicitud().toString());            
            Dates fecha_atencion = date.consultar(u.getFechaAtencion().toString());
            
            long diasDiferencia=date.diferenciaDias(fecha_atencion, fecha_solicitud);

            System.err.println(u.getIDMedico().getCedula().toString());
            PreexistenciaBodega pre = preexistencia.consultar(user);
            MedicoBodega med=medico.consultar(u.getIDMedico().getCedula().toString());
           
            
            DiagnosticoBodega diag=diagnostico.consultar(u.getDiagnostico().toString());
            String id_ips=med.getIpsId();
            IpsBodega ipsEntidad=ips.consultarPorId(id_ips);
            
            ConexionDB conexion = new ConexionDB();
            conexion.getConexion();
            List empresaLista;
            EmpresaBodega emp;
          
            try {
                empresaLista = conexion.consultarEmpresa(user);
                emp = empresa.consultar(empresaLista.get(0).toString());
            } catch (Exception e) {
                empresaLista = new LinkedList();
                emp = new EmpresaBodega();
                emp.setEmpresaKey(999999);            
                
            }

            
            UrgenciasBodega urgenciaNueva = new UrgenciasBodega();
            UrgenciasBodegaPK pk;
      pk = new UrgenciasBodegaPK(p.getPacienteKey(), demog.getDemografiaPacienteKey(), fecha_solicitud.getDateId(),
                    fecha_atencion.getDateId(), emp.getEmpresaKey(), pre.getPreexistenciaKey());
          
             //para aÃ±adir campos

            urgenciaNueva.setDates(fecha_atencion);
            System.out.println("Id paciente "+p.getPacienteKey());
             System.out.println("Demografia "+demog.getDemografiaPacienteKey());
             System.out.println("Fecha Soli "+fecha_solicitud);
              System.out.println("Fecha Aten "+fecha_atencion);
               System.out.println("Empresa "+emp.getEmpresaKey());
                
               
             
            urgenciaNueva.setDates1(fecha_solicitud);
            
            urgenciaNueva.setDemografiaPacienteBodega(demog);
            urgenciaNueva.setPacienteBodega(p);
            urgenciaNueva.setEmpresaBodega(emp);
            urgenciaNueva.setPreexistenciaBodega(pre);
            urgenciaNueva.setDiagnosticoKey(diag);
            System.out.println("diag "+diag);
            urgenciaNueva.setIpsKey(ipsEntidad);
            urgenciaNueva.setMedicoId(med.getMedicoKey());
            urgenciaNueva.setTiempoEsperaAtencion((int)diasDiferencia);            
           
            controladorUrgenciaBodega.create(urgenciaNueva);
            contador++;
            System.err.println(contador);

        }
    }
}
