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
import Entidades_Bodega.UrgenciasBodega;
import Entidades_Bodega.UrgenciasBodegaPK;
import Entidades_DB.Urgencias;
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
        EmpresaBodegaJpaController empresa = new EmpresaBodegaJpaController(fabrica_bodega.getFactory());
        PreexistenciaBodegaJpaController preexistencia = new PreexistenciaBodegaJpaController(fabrica_bodega.getFactory());
        ServicioPosBodegaJpaController servicioPos=new ServicioPosBodegaJpaController(fabrica_bodega.getFactory());
        DiagnosticoBodegaJpaController diagnostico=new DiagnosticoBodegaJpaController(fabrica_bodega.getFactory());
        MedicoBodegaJpaController medico=new MedicoBodegaJpaController(fabrica_bodega.getFactory());
        IpsBodegaJpaController ips=new IpsBodegaJpaController(fabrica_bodega.getFactory());
        
       
        
        int contador = 0;
        lista = controladorBD.findUrgenciasEntities();
        for (int i = 0; i < lista.size(); i++) {

            Urgencias u = (Urgencias) lista.get(i);
           // System.out.println("id transaccion " + pago.getIDTransaccion());
            System.out.println(u.getCodigoUrgencia());
            
            
            //-------------VOY ACAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA MARTESSS LLEGOOOO DE LA FINCAAA
            //---------RECORDAR CALCULAR FECHA Y YA SALE 
            
//            
//            String buscar = u.getIDUsuario().toString();
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
//
//
//            //Obtener codigo Depto
//            //System.out.println("Id " + user);
//
//            PacienteBodega p = paciente.consultarPorId(user);
//            // System.out.println("llave " + p.getPacienteKey());
//            DemografiaPacienteBodega demog = demografia.findDemografiaPacienteBodega(p.getPacienteKey());
//            Dates f = date.consultar(u.getFechaPago().toString());
//
//            ConexionDB conexion = new ConexionDB();
//            conexion.getConexion();
//            List empresaLista;
//            EmpresaBodega emp;
//          
//            try {
//                empresaLista = conexion.consultarEmpresa(user);
//                emp = empresa.consultar(empresaLista.get(0).toString());
//            } catch (Exception e) {
//                empresaLista = new LinkedList();
//                emp = new EmpresaBodega();
//                emp.setEmpresaKey(999999);            
//                
//            }
//
//            PreexistenciaBodega pre = preexistencia.consultar(user);
//            PagosBodega pagoNuevo = new PagosBodega();
//            PagosBodegaPK pk;
//            if (emp.getEmpresaKey() == 999999) {
//                 pk = new PagosBodegaPK(p.getPacienteKey(), demog.getDemografiaPacienteKey(), f.getDateId(), 0,
//                        pre.getPreexistenciaKey());
//
//            } else {
//                 pk = new PagosBodegaPK(p.getPacienteKey(), demog.getDemografiaPacienteKey(), f.getDateId(), emp.getEmpresaKey(),
//                        pre.getPreexistenciaKey());
//            } //para aÃ±adir campos
//
//            pagoNuevo.setDates(f);
//            pagoNuevo.setDemografiaPacienteBodega(demog);
//            pagoNuevo.setPacienteBodega(p);
//            pagoNuevo.setEmpresaBodega(emp);
//            pagoNuevo.setPreexistenciaBodega(pre);
//            pagoNuevo.setValorPagado(u.getValorPagado());
//            pagoNuevo.setPagosBodegaPK(pk);
//            controladorPagoBodega.create(pagoNuevo);
//            contador++;
//            System.err.println(contador);
//
        }
    }
    
}
