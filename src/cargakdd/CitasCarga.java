/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cargakdd;

import ControladorJPA.FabricaObjetos;
import Entidades_Bodega.CitasBodega;
import Entidades_Bodega.CitasBodegaPK;
import Entidades_DB.Hospitalizaciones;
import Entidades_DB.CitasGenerales;
import ControladorJPABodega.FabricaBodega;
import ControladorJPA.CitasGeneralesJpaController;
import ControladorJPABodega.CitasBodegaJpaController;
import ControladorJPA.HospitalizacionesJpaController;
import ControladorJPABodega.DatesJpaController;
import ControladorJPABodega.DemografiaPacienteBodegaJpaController;
import ControladorJPABodega.DiagnosticoBodegaJpaController;
import ControladorJPABodega.IpsBodegaJpaController;
import ControladorJPABodega.MedicoBodegaJpaController;
import ControladorJPABodega.PacienteBodegaJpaController;
import ControladorJPABodega.PreexistenciaBodegaJpaController;
import ControladorJPABodega.exceptions.PreexistingEntityException;
import Entidades_Bodega.Dates;
import Entidades_Bodega.DemografiaPacienteBodega;
import Entidades_Bodega.DiagnosticoBodega;
import Entidades_Bodega.IpsBodega;
import Entidades_Bodega.MedicoBodega;
import Entidades_Bodega.PacienteBodega;
import Entidades_Bodega.PreexistenciaBodega;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author USER
 */
public class CitasCarga {

    private FabricaObjetos mi_fabrica;
    private FabricaBodega fabrica_bodega;
    CitasGeneralesJpaController controladorCitasBD;
    HospitalizacionesJpaController controladorHospitalizacionBD;
    CitasBodegaJpaController controladorCitasBodega;
    EntityManager manager;
    EntityManager manager_bodega;

    public CitasCarga() {
        mi_fabrica = new FabricaObjetos();
        fabrica_bodega = new FabricaBodega();
        manager = mi_fabrica.crear().createEntityManager();
        manager_bodega = fabrica_bodega.crear().createEntityManager();
        controladorCitasBD = new CitasGeneralesJpaController(mi_fabrica.getFactory());
        controladorHospitalizacionBD = new HospitalizacionesJpaController(mi_fabrica.getFactory());
        controladorCitasBodega = new CitasBodegaJpaController(fabrica_bodega.getFactory());

    }

    public void carga() throws PreexistingEntityException, Exception {
        List listaCitas, listaHosp;
        PacienteBodegaJpaController paciente = new PacienteBodegaJpaController(fabrica_bodega.getFactory());
        DemografiaPacienteBodegaJpaController demografia = new DemografiaPacienteBodegaJpaController(fabrica_bodega.getFactory());
        DatesJpaController date = new DatesJpaController(fabrica_bodega.getFactory());
        PreexistenciaBodegaJpaController preexistencia = new PreexistenciaBodegaJpaController(fabrica_bodega.getFactory());
        DiagnosticoBodegaJpaController diagnostico = new DiagnosticoBodegaJpaController(fabrica_bodega.getFactory());
        MedicoBodegaJpaController medico = new MedicoBodegaJpaController(fabrica_bodega.getFactory());
        IpsBodegaJpaController ips = new IpsBodegaJpaController(fabrica_bodega.getFactory());


        int contador = 0;
        listaCitas = controladorCitasBD.findCitasGeneralesEntities();
        listaHosp=controladorHospitalizacionBD.findHospitalizacionesEntities();
        for (int i = 0; i < listaCitas.size(); i++) {

            CitasGenerales citaGeneral = (CitasGenerales) listaCitas.get(i);

            String user = citaGeneral.getIDUsuario().toString();

            System.out.println("Cita " + citaGeneral.getCodigocita());

            PacienteBodega p = paciente.consultarPorId(user);
            // System.out.println("llave " + p.getPacienteKey());
            DemografiaPacienteBodega demog = demografia.findDemografiaPacienteBodega(p.getPacienteKey());
            Dates fecha_solicitud = date.consultar(citaGeneral.getFechaSolicitud().toString());
            Dates fecha_atencion = date.consultar(citaGeneral.getFechaAtencion().toString());

            long diasDiferencia = date.diferenciaDias(fecha_atencion, fecha_solicitud);

//            System.err.println(citaGeneral.getIDMedico().getCedula().toString());
            PreexistenciaBodega pre = preexistencia.consultar(user);
            MedicoBodega med = medico.consultar(citaGeneral.getIDMedico().getCedula().toString());


            DiagnosticoBodega diag = diagnostico.consultar(citaGeneral.getDiagnostico().toString());
            String id_ips = med.getIpsId();
            IpsBodega ipsEntidad = ips.consultarPorId(id_ips);


            CitasBodega citaNueva = new CitasBodega();
            CitasBodegaPK pk;
            pk = new CitasBodegaPK(med.getMedicoKey(), p.getPacienteKey(), demog.getDemografiaPacienteKey(), fecha_solicitud.getDateId(),
                    fecha_atencion.getDateId(), diag.getDiagnosticoKey(), pre.getPreexistenciaKey(), ipsEntidad.getIpsKey());

            //para aÃ±adir campos

            System.err.println("tiempo "+diasDiferencia);



            citaNueva.setDates(fecha_atencion);
            citaNueva.setDates1(fecha_solicitud);
            citaNueva.setDemografiaPacienteBodega(demog);
            citaNueva.setPacienteBodega(p);
            citaNueva.setIpsBodega(ipsEntidad);
            citaNueva.setPreexistenciaBodega(pre);
            citaNueva.setDiagnosticoBodega(diag);
            citaNueva.setMedicoBodega(med);
            citaNueva.setTiempoEsperaAtencion((int) diasDiferencia);
            citaNueva.setTipoCita("GENERAL");
            citaNueva.setDuracionHospitalizacion(0);
            citaNueva.setHoraAtencion(citaGeneral.getHoraAtencion());
                      

           controladorCitasBodega.create(citaNueva);
            contador++;
            System.err.println(contador);

        }
        
         for (int i = 0; i < listaHosp.size(); i++) {

            Hospitalizaciones hospitalizacion = (Hospitalizaciones) listaHosp.get(i);

            String user = hospitalizacion.getIDUsuario().toString();

            System.out.println("Hospitalizacion " + hospitalizacion.getCodigoHospitalizacion());

            System.err.println("Id " + user);

            PacienteBodega p = paciente.consultarPorId(user);
            // System.out.println("llave " + p.getPacienteKey());
            DemografiaPacienteBodega demog = demografia.findDemografiaPacienteBodega(p.getPacienteKey());
            Dates fecha_solicitud = date.consultar(hospitalizacion.getFechaSolicitud().toString());
            Dates fecha_atencion = date.consultar(hospitalizacion.getFechaAtencion().toString());

            long diasDiferencia = date.diferenciaDias(fecha_atencion, fecha_solicitud);

//            System.err.println(citaGeneral.getIDMedico().getCedula().toString());
            PreexistenciaBodega pre = preexistencia.consultar(user);
            MedicoBodega med = medico.consultar(hospitalizacion.getIDMedico().getCedula().toString());


            DiagnosticoBodega diag = diagnostico.consultar(hospitalizacion.getDiagnostico().toString());
            String id_ips = med.getIpsId();
            IpsBodega ipsEntidad = ips.consultarPorId(id_ips);   

            CitasBodega citaNueva = new CitasBodega();
            CitasBodegaPK pk;
            pk = new CitasBodegaPK(med.getMedicoKey(), p.getPacienteKey(), demog.getDemografiaPacienteKey(), fecha_solicitud.getDateId(),
                    fecha_atencion.getDateId(), diag.getDiagnosticoKey(), pre.getPreexistenciaKey(), ipsEntidad.getIpsKey());

            //para llenar campos//
            citaNueva.setDates(fecha_atencion);
            citaNueva.setDates1(fecha_solicitud);
            citaNueva.setDemografiaPacienteBodega(demog);
            citaNueva.setPacienteBodega(p);
            citaNueva.setIpsBodega(ipsEntidad);
            citaNueva.setPreexistenciaBodega(pre);
            citaNueva.setDiagnosticoBodega(diag);
            citaNueva.setMedicoBodega(med);
            citaNueva.setTiempoEsperaAtencion((int) diasDiferencia);
            citaNueva.setTipoCita("HOSPITALIZACION");
            citaNueva.setHoraAtencion(hospitalizacion.getHoraAtencion());
            citaNueva.setDuracionHospitalizacion(hospitalizacion.getDuracionHospitalizacion());
                      

           controladorCitasBodega.create(citaNueva);
            contador++;
            System.err.println(contador);

        }
    }
}
