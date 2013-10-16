/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cargakdd;

import ControladorJPA.FabricaObjetos;
import ControladorJPA.MedicamentoJpaController;
import ControladorJPABodega.FabricaBodega;
import ControladorJPABodega.MedicamentoBodegaJpaController;
import ControladorJPABodega.exceptions.PreexistingEntityException;
import Entidades_Bodega.MedicamentoBodega;
import Entidades_DB.Medicamento;
import java.util.List;
import javax.persistence.EntityManager;



/**
 *
 * @author USER
 */
public class MedicamentoCarga {

    private FabricaObjetos mi_fabrica;
    private FabricaBodega fabrica_bodega;
    MedicamentoJpaController controladorBD;
    MedicamentoBodegaJpaController controladorBodega;
    EntityManager manager;
    EntityManager manager_bodega;

    public MedicamentoCarga() {
        mi_fabrica = new FabricaObjetos();
        fabrica_bodega=new FabricaBodega();
        manager = mi_fabrica.crear().createEntityManager();
        manager_bodega=fabrica_bodega.crear().createEntityManager();
        controladorBD=new MedicamentoJpaController(mi_fabrica.getFactory());
        controladorBodega=new MedicamentoBodegaJpaController(fabrica_bodega.getFactory());
        
    }

    public void carga() throws PreexistingEntityException, Exception {
        List lista;
       // lista = manager.createQuery("SELECT * FROM medicamentos m").getResultList();
        lista=controladorBD.findMedicamentoEntities();
        for (int i = 0; i < lista.size(); i++) {
//            System.out.println("-------AQUII----"+i);
            Medicamento m = (Medicamento) lista.get(i);
             System.out.println(m.getCodigo());
            //crear nuevo objeto
             
            MedicamentoBodega medicamentoNuevo = new MedicamentoBodega();
            //para aÃ±adir campos al medicamento
            
            medicamentoNuevo.setMedicamentoKey(i+1);
            medicamentoNuevo.setCodigoMedicamento(m.getCodigo().toString());
            medicamentoNuevo.setNombre(m.getNombreGenerico().toString());
            medicamentoNuevo.setFormaFarmaceutica(m.getFormaFarmaceutica().toString());
            medicamentoNuevo.setPresentacion(m.getPresentacion().toString());
            medicamentoNuevo.setLabRegistro(m.getLaboratorioRegistro());
            medicamentoNuevo.setPrecio(m.getPrecio());
            medicamentoNuevo.setTipo(m.getTipoMedicamento().toString());

            controladorBodega.create(medicamentoNuevo);

        }

    }
}
