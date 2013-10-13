/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cargakdd;

import ControladorJPA.FabricaObjetos;
import ControladorJPABodega.FabricaBodega;
import ControladorJPA.EmpresaJpaController;
import ControladorJPABodega.EmpresaBodegaJpaController;
import ControladorJPABodega.exceptions.PreexistingEntityException;
import Entidades_Bodega.EmpresaBodega;
import Entidades_Bodega.IpsBodega;
import Entidades_DB.Empresa;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author USER
 */
public class EmpresaCarga {

    private FabricaObjetos mi_fabrica;
    private FabricaBodega fabrica_bodega;
    EmpresaJpaController controladorBD;
    EmpresaBodegaJpaController controladorBodega;
    EntityManager manager;
    EntityManager manager_bodega;

    public EmpresaCarga() {
        mi_fabrica = new FabricaObjetos();
        fabrica_bodega = new FabricaBodega();
        manager = mi_fabrica.crear().createEntityManager();
        manager_bodega = fabrica_bodega.crear().createEntityManager();
        controladorBD = new EmpresaJpaController(mi_fabrica.getFactory());
        controladorBodega = new EmpresaBodegaJpaController(fabrica_bodega.getFactory());
    }
    
     public void carga() throws PreexistingEntityException, Exception {
        List lista;
       
        // lista = manager.createQuery("SELECT * FROM medicamentos m").getResultList();
        lista = controladorBD.findEmpresaEntities();
        for (int i = 0; i < lista.size(); i++) {

            Empresa empresa = (Empresa) lista.get(i);
            System.out.println(empresa.getNit());
                    
           
            EmpresaBodega empresaNueva = new EmpresaBodega();
            //para aÃ±adir campos

            empresaNueva.setEmpresaKey(i + 1);
            empresaNueva.setNit(empresa.getNit().toString());
            empresaNueva.setNombre(empresa.getDireccion().toString());            
            empresaNueva.setDireccion(empresa.getDireccion().toString());
            empresaNueva.setCantidadEmpleados(empresa.getTotalEmpleados());
            empresaNueva.setActividadComercial(empresa.getActividadEconomica().toString());
            empresaNueva.setCiudad(empresa.getCiudad().toString());


            controladorBodega.create(empresaNueva);

        }
    }
}
