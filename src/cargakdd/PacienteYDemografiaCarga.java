/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cargakdd;

import ControladorJPA.CotizanteJpaController;
import ControladorJPA.FabricaObjetos;
import ControladorJPABodega.FabricaBodega;
import ControladorJPA.BeneficiarioJpaController;
import ControladorJPABodega.DemografiaPacienteBodegaJpaController;
import ControladorJPABodega.PacienteBodegaJpaController;
import ControladorJPABodega.exceptions.PreexistingEntityException;
import Entidades_Bodega.DemografiaPacienteBodega;
import Entidades_Bodega.PacienteBodega;
import Entidades_DB.Beneficiario;
import Entidades_DB.Cotizante;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author USER
 */
public class PacienteYDemografiaCarga {

    private FabricaObjetos mi_fabrica;
    private FabricaBodega fabrica_bodega;
    CotizanteJpaController controladorCotizante;
    BeneficiarioJpaController controladorBeneficiario;
    PacienteBodegaJpaController controladorPaciente;
    DemografiaPacienteBodegaJpaController controladorDemografia;
    EntityManager manager;
    EntityManager manager_bodega;

    public PacienteYDemografiaCarga() {

        mi_fabrica = new FabricaObjetos();
        fabrica_bodega = new FabricaBodega();
        manager = mi_fabrica.crear().createEntityManager();
        manager_bodega = fabrica_bodega.crear().createEntityManager();
        controladorCotizante = new CotizanteJpaController(mi_fabrica.getFactory());
        controladorBeneficiario = new BeneficiarioJpaController(mi_fabrica.getFactory());
        controladorPaciente = new PacienteBodegaJpaController(fabrica_bodega.getFactory());
        controladorDemografia = new DemografiaPacienteBodegaJpaController(fabrica_bodega.getFactory());
    }

    public void carga() throws PreexistingEntityException, Exception {
        List listaCotizantes, listaBeneficiarios;
        int contador = 0;
        // lista = manager.createQuery("SELECT * FROM medicamentos m").getResultList();
        listaCotizantes = controladorCotizante.findCotizanteEntities();
        for (int i = 0; i < listaCotizantes.size(); i++) {

            Cotizante cotizante = (Cotizante) listaCotizantes.get(i);
            System.out.println(contador + 1 + "   " + cotizante.getCedula());
            
            String ipsCod=cotizante.getIdIps().toString();
//            System.out.println(cotizante.getIdIps());
//            System.err.println(ipsCod.substring(24, 30));
            //crear nuevo objeto

            PacienteBodega pacienteNuevo = new PacienteBodega();
            DemografiaPacienteBodega demog = new DemografiaPacienteBodega();
            //para aÃ±adir campos

            pacienteNuevo.setPacienteKey(contador + 1);
            pacienteNuevo.setIdPaciente(cotizante.getCedula().toString());
            pacienteNuevo.setTipoDocumento("Cedula");
            pacienteNuevo.setTipoPaciente("Cotizante");
            pacienteNuevo.setIdCotizante(cotizante.getCedula().toString());
            pacienteNuevo.setNombre(cotizante.getNombre().toString());
            pacienteNuevo.setSexo(cotizante.getSexo().toString());
            pacienteNuevo.setFechaNacimiento(cotizante.getFechaNacimiento());
            pacienteNuevo.setFechaAfiliacion(cotizante.getFechaAfiliacion());
            pacienteNuevo.setDiscapacidad(cotizante.getTipoDiscapacidad().toString());

            demog.setDemografiaPacienteKey(contador + 1);
            demog.setEstadoCivil(cotizante.getEstadoCivil().toString());
            demog.setDireccion(cotizante.getDireccion().toString());
            demog.setSalario(Integer.parseInt(cotizante.getSalarioBase().toString()));
            demog.setNivelEscolaridad(cotizante.getNivelEscolaridad().toString());
            demog.setEstrato(cotizante.getEstracto().toString());
            demog.setProvieneOtraEps(cotizante.getProvieneOtraEPS().toString());
            demog.setIps(ipsCod.substring(24, 30));
            demog.setTipoCotizante(cotizante.getTipoCotizante().toString());


            controladorPaciente.create(pacienteNuevo);
            controladorDemografia.create(demog);
            contador++;
        }

        listaBeneficiarios = controladorBeneficiario.findBeneficiarioEntities();
        for (int i = 0; i < listaBeneficiarios.size(); i++) {

            Beneficiario beneficiario = (Beneficiario) listaBeneficiarios.get(i);
        //    System.out.println(contador + 1 + "   " + beneficiario.getIDBeneficiario());

            //crear nuevo objeto

            PacienteBodega pacienteNuevo = new PacienteBodega();
            DemografiaPacienteBodega demog = new DemografiaPacienteBodega();
            //para aÃ±adir campos
            String buscar = beneficiario.getCotizanteList().get(0).toString();
            Cotizante c=new Cotizante();
            if (buscar.length() == 42) {
                 c = controladorCotizante.findCotizante(buscar.substring(31, 40));
                  pacienteNuevo.setIdCotizante(buscar.substring(31, 40));
               
            }
            if (buscar.length() == 41) {
                
                 c = controladorCotizante.findCotizante(buscar.substring(31, 39));
                  pacienteNuevo.setIdCotizante(buscar.substring(31, 39));
            }
            if (buscar.length() == 40) {
                 c = controladorCotizante.findCotizante(buscar.substring(31, 38));
                pacienteNuevo.setIdCotizante(buscar.substring(31, 38));
            }
            if (buscar.length() == 39) {
                 c = controladorCotizante.findCotizante(buscar.substring(31, 37));
                pacienteNuevo.setIdCotizante(buscar.substring(31, 37));
            }

            System.out.println(c.getIdIps());
            pacienteNuevo.setPacienteKey(contador + 1);
            pacienteNuevo.setIdPaciente(beneficiario.getIDBeneficiario().toString());
            pacienteNuevo.setTipoDocumento(beneficiario.getTipoIdentificacion().toString());
            pacienteNuevo.setTipoPaciente("Beneficiario");            
            pacienteNuevo.setNombre(beneficiario.getNombre().toString());
            pacienteNuevo.setSexo(beneficiario.getSexo().toString());
            pacienteNuevo.setFechaNacimiento(beneficiario.getFechaNacimiento());
            pacienteNuevo.setFechaAfiliacion(c.getFechaAfiliacion());
            pacienteNuevo.setDiscapacidad(beneficiario.getTipoDiscapacidad().toString());

            demog.setDemografiaPacienteKey(contador + 1);
            demog.setEstadoCivil(beneficiario.getEstadoCivil().toString());
            demog.setDireccion(c.getDireccion().toString());
            demog.setSalario(0);
            demog.setNivelEscolaridad("No se tiene Registro");
            demog.setEstrato(c.getEstracto().toString());
            demog.setProvieneOtraEps("No se tiene Registro");
            
             String ipsCod=c.getIdIps().toString();
            demog.setIps(ipsCod.substring(24, 30));
            
            demog.setTipoCotizante("No es cotizante");


            controladorPaciente.create(pacienteNuevo);
            controladorDemografia.create(demog);

            contador++;
        }

        System.out.println(contador);
    }
}
