/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cargakdd;

import ControladorJPA.FabricaObjetos;
import ControladorJPA.IpsJpaController;
import ControladorJPABodega.DepartamentoBodegaJpaController;
import ControladorJPABodega.FabricaBodega;
import ControladorJPABodega.IpsBodegaJpaController;
import ControladorJPABodega.exceptions.PreexistingEntityException;
import Entidades_Bodega.DepartamentoBodega;
import Entidades_Bodega.IpsBodega;
import Entidades_DB.Ips;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author USER
 */
public class IPSCarga {

    private FabricaObjetos mi_fabrica;
    private FabricaBodega fabrica_bodega;
    IpsJpaController controladorBD;
    IpsBodegaJpaController controladorBodega;
    EntityManager manager;
    EntityManager manager_bodega;

    public IPSCarga() {
        mi_fabrica = new FabricaObjetos();
        fabrica_bodega = new FabricaBodega();
        manager = mi_fabrica.crear().createEntityManager();
        manager_bodega = fabrica_bodega.crear().createEntityManager();
        controladorBD = new IpsJpaController(mi_fabrica.getFactory());
        controladorBodega = new IpsBodegaJpaController(fabrica_bodega.getFactory());

    }

    public void carga() throws PreexistingEntityException, Exception {
        List lista;
        DepartamentoBodegaJpaController controladorDpto = new DepartamentoBodegaJpaController(fabrica_bodega.getFactory());

        // lista = manager.createQuery("SELECT * FROM medicamentos m").getResultList();
        lista = controladorBD.findIpsEntities();
        for (int i = 0; i < lista.size(); i++) {

            Ips ips = (Ips) lista.get(i);
            System.out.println(ips.getIdIps());
            //crear nuevo objeto

            //Obtener codigo Depto
            String deptoABuscar = ips.getDepartamento().toString();
           DepartamentoBodega d=controladorDpto.consultarPorNombre(deptoABuscar);

            IpsBodega ipsNueva = new IpsBodega();
            //para aÃ±adir campos

            ipsNueva.setIpsKey(i + 1);
            ipsNueva.setCodigoIps(ips.getIdIps().toString());
            ipsNueva.setTipo(ips.getTipoIPS().toString());
            ipsNueva.setNombre(ips.getNombre().toString());
            ipsNueva.setDireccion(ips.getDireccion().toString());
            ipsNueva.setMunicipio(ips.getMunicipio());
            ipsNueva.setDepartamento(d);


            controladorBodega.create(ipsNueva);

        }
    }

//    public DepartamentoBodega consultarCodigoDepto(String nombre) {
//        List lista;      
//        String sql_select = "SELECT * FROM departamento_bodega d WHERE nombre='" + nombre + "'";
//
//      //  System.out.println(sql_select);
//        
//        lista = manager_bodega.createQuery(sql_select).getResultList();
//        //else lista = manager.createQuery("SELECT b FROM Buses b ").getResultList();
//
////        for (int i = 0; i < lista.size(); i++) {
//            DepartamentoBodega depto = (DepartamentoBodega) lista.get(0);
//            
////        }
//
//        return depto;
//    }
}
