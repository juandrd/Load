/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cargakdd;

import ControladorJPA.FabricaObjetos;
import ControladorJPABodega.FabricaBodega;
import ControladorJPA.PagosJpaController;
import ControladorJPABodega.DatesJpaController;
import ControladorJPABodega.DemografiaPacienteBodegaJpaController;
import ControladorJPABodega.EmpresaBodegaJpaController;
import ControladorJPABodega.PacienteBodegaJpaController;
import ControladorJPABodega.PagosBodegaJpaController;
import ControladorJPABodega.PreexistenciaBodegaJpaController;
import ControladorJPABodega.exceptions.PreexistingEntityException;
import Entidades_Bodega.Dates;
import Entidades_Bodega.DemografiaPacienteBodega;
import Entidades_Bodega.EmpresaBodega;
import Entidades_Bodega.PacienteBodega;
import Entidades_Bodega.PagosBodega;
import Entidades_Bodega.PagosBodegaPK;
import Entidades_Bodega.PreexistenciaBodega;
import Entidades_DB.Pagos;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author USER
 */
public class PagoCarga {

    private FabricaObjetos mi_fabrica;
    private FabricaBodega fabrica_bodega;
    PagosJpaController controladorBD;
    PagosBodegaJpaController controladorPagoBodega;
    EntityManager manager;
    EntityManager manager_bodega;

    public PagoCarga() {
        mi_fabrica = new FabricaObjetos();
        fabrica_bodega = new FabricaBodega();
        manager = mi_fabrica.crear().createEntityManager();
        manager_bodega = fabrica_bodega.crear().createEntityManager();
        controladorBD = new PagosJpaController(mi_fabrica.getFactory());
        controladorPagoBodega = new PagosBodegaJpaController(fabrica_bodega.getFactory());
    }

    public void carga() throws PreexistingEntityException, Exception {
        List lista;
        PacienteBodegaJpaController paciente = new PacienteBodegaJpaController(fabrica_bodega.getFactory());
        DemografiaPacienteBodegaJpaController demografia = new DemografiaPacienteBodegaJpaController(fabrica_bodega.getFactory());
        DatesJpaController date = new DatesJpaController(fabrica_bodega.getFactory());
        EmpresaBodegaJpaController empresa = new EmpresaBodegaJpaController(fabrica_bodega.getFactory());
        PreexistenciaBodegaJpaController preexistencia = new PreexistenciaBodegaJpaController(fabrica_bodega.getFactory());

        int contador = 0;
        lista = controladorBD.findPagosEntities();
        for (int i = 0; i < lista.size(); i++) {

            Pagos pago = (Pagos) lista.get(i);
           // System.out.println("id transaccion " + pago.getIDTransaccion());
            //System.out.println(pago.getIDUsuario());

            String buscar = pago.getIDUsuario().toString();
            String user = "";
            if (buscar.length() == 42) {
                user = pago.getIDUsuario().toString().substring(31, 40);

            }
            if (buscar.length() == 41) {
                user = pago.getIDUsuario().toString().substring(31, 39);

            }

            if (buscar.length() == 40) {
                user = pago.getIDUsuario().toString().substring(31, 38);

            }

            if (buscar.length() == 39) {
                user = pago.getIDUsuario().toString().substring(31, 37);

            }


            //Obtener codigo Depto
            //System.out.println("Id " + user);

            PacienteBodega p = paciente.consultarPorId(user);
            // System.out.println("llave " + p.getPacienteKey());
            DemografiaPacienteBodega demog = demografia.findDemografiaPacienteBodega(p.getPacienteKey());
            Dates f = date.consultar(pago.getFechaPago().toString());

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

            PreexistenciaBodega pre = preexistencia.consultar(user);
            PagosBodega pagoNuevo = new PagosBodega();
            PagosBodegaPK pk;
            if (emp.getEmpresaKey() == 999999) {
                 pk = new PagosBodegaPK(p.getPacienteKey(), demog.getDemografiaPacienteKey(), f.getDateId(), 0,
                        pre.getPreexistenciaKey());

            } else {
                 pk = new PagosBodegaPK(p.getPacienteKey(), demog.getDemografiaPacienteKey(), f.getDateId(), emp.getEmpresaKey(),
                        pre.getPreexistenciaKey());
            } //para aÃ±adir campos

            pagoNuevo.setDates(f);
            pagoNuevo.setDemografiaPacienteBodega(demog);
            pagoNuevo.setPacienteBodega(p);
            pagoNuevo.setEmpresaBodega(emp);
            pagoNuevo.setPreexistenciaBodega(pre);
            pagoNuevo.setValorPagado(pago.getValorPagado());
            pagoNuevo.setPagosBodegaPK(pk);
            controladorPagoBodega.create(pagoNuevo);
            contador++;
            System.err.println(contador);

        }
    }
}
