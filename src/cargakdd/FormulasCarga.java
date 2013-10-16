/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cargakdd;

import ControladorJPA.FabricaObjetos;
import ControladorJPABodega.*;
import ControladorJPA.FormulasMedicasJpaController;
import ControladorJPABodega.exceptions.PreexistingEntityException;
import Entidades_Bodega.*;
import Entidades_DB.FormulasMedicas;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author USER
 */
public class FormulasCarga {

    private FabricaObjetos mi_fabrica;
    private FabricaBodega fabrica_bodega;
    FormulasMedicasJpaController controladorBD;
    FormulasBodegaJpaController controladorFormulasBodega;
    EntityManager manager;
    EntityManager manager_bodega;

    public FormulasCarga() {
        mi_fabrica = new FabricaObjetos();
        fabrica_bodega = new FabricaBodega();
        manager = mi_fabrica.crear().createEntityManager();
        manager_bodega = fabrica_bodega.crear().createEntityManager();
        controladorBD = new FormulasMedicasJpaController(mi_fabrica.getFactory());
        controladorFormulasBodega = new FormulasBodegaJpaController(fabrica_bodega.getFactory());
    }

    public void carga() throws PreexistingEntityException, Exception {
        List lista;
        PacienteBodegaJpaController paciente = new PacienteBodegaJpaController(fabrica_bodega.getFactory());
        DemografiaPacienteBodegaJpaController demografia = new DemografiaPacienteBodegaJpaController(fabrica_bodega.getFactory());
        DatesJpaController date = new DatesJpaController(fabrica_bodega.getFactory());
        MedicoBodegaJpaController medico = new MedicoBodegaJpaController(fabrica_bodega.getFactory());
        MedicamentoBodegaJpaController medicamento = new MedicamentoBodegaJpaController(fabrica_bodega.getFactory());

        int contador = 0;
        lista = controladorBD.findFormulasMedicasEntities();
        for (int i = 0; i < lista.size(); i++) {

            FormulasMedicas formula = (FormulasMedicas) lista.get(i);

            String user = formula.getIDUsuario().toString();


            //System.err.println("Id " + user);

            PacienteBodega p = paciente.consultarPorId(user);
            // System.out.println("llave " + p.getPacienteKey());
            DemografiaPacienteBodega demog = demografia.findDemografiaPacienteBodega(p.getPacienteKey());

            Dates fecha = date.consultar(formula.getFecha().toString());

//            fecha.setDate(formula.getHoraSolicitud());

            MedicoBodega med = medico.consultar(formula.getIDMedico().getCedula().toString());


            String listaMedicamentos = formula.getMedicamentosRecetados().toString();
             System.out.println("formula "+formula.getCodigoFormula());
            if (listaMedicamentos.contains(";")) {
                String[] medicamentosArray = listaMedicamentos.split(";");
                for (int j = 0; j < medicamentosArray.length; j++) {
                    MedicamentoBodega medicamentoBodega = medicamento.consultar(medicamentosArray[j]);
                  //  System.out.println("medicamento buscado " + medicamentosArray[j]);
                   
                    
                    FormulasBodega formulaNueva = new FormulasBodega();
                    FormulasBodegaPK pk;
                    pk = new FormulasBodegaPK(p.getPacienteKey(), demog.getDemografiaPacienteKey(), fecha.getDateId(),
                            med.getMedicoKey(), medicamentoBodega.getMedicamentoKey(), formula.getCodigoFormula());

                    formulaNueva.setFormulasBodegaPK(pk);
                    formulaNueva.setPacienteBodega(p);
                    formulaNueva.setDemografiaPacienteBodega(demog);
                    formulaNueva.setDates(fecha);
                    formulaNueva.setMedicoBodega(med);
                    formulaNueva.setMedicamentoBodega(medicamentoBodega);
                    formulaNueva.setCostoMedicamento(medicamentoBodega.getPrecio());

                    controladorFormulasBodega.create(formulaNueva);
                    contador++;
                    
                    System.err.println(contador);
                }
            } else {
                MedicamentoBodega medicamentoBodega = medicamento.consultar(listaMedicamentos);

                FormulasBodega formulaNueva = new FormulasBodega();
                FormulasBodegaPK pk;
                pk = new FormulasBodegaPK(p.getPacienteKey(), demog.getDemografiaPacienteKey(), fecha.getDateId(),
                        med.getMedicoKey(), medicamentoBodega.getMedicamentoKey(), formula.getCodigoFormula());

                formulaNueva.setFormulasBodegaPK(pk);
                formulaNueva.setPacienteBodega(p);
                formulaNueva.setDemografiaPacienteBodega(demog);
                formulaNueva.setDates(fecha);
                formulaNueva.setMedicoBodega(med);
                formulaNueva.setMedicamentoBodega(medicamentoBodega);
                formulaNueva.setCostoMedicamento(medicamentoBodega.getPrecio());

                 controladorFormulasBodega.create(formulaNueva);
                contador++;
               
                System.err.println(contador);
            }         



        }
    }
}
