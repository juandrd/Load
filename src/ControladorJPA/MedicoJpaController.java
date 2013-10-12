/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladorJPA;

import ControladorJPA.exceptions.NonexistentEntityException;
import ControladorJPA.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entidades_DB.CitasGenerales;
import java.util.ArrayList;
import java.util.List;
import Entidades_DB.Urgencias;
import Entidades_DB.Remisiones;
import Entidades_DB.FormulasMedicas;
import Entidades_DB.Hospitalizaciones;
import Entidades_DB.Medico;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author USER
 */
public class MedicoJpaController implements Serializable {

    public MedicoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Medico medico) throws PreexistingEntityException, Exception {
        if (medico.getCitasGeneralesList() == null) {
            medico.setCitasGeneralesList(new ArrayList<CitasGenerales>());
        }
        if (medico.getUrgenciasList() == null) {
            medico.setUrgenciasList(new ArrayList<Urgencias>());
        }
        if (medico.getRemisionesList() == null) {
            medico.setRemisionesList(new ArrayList<Remisiones>());
        }
        if (medico.getRemisionesList1() == null) {
            medico.setRemisionesList1(new ArrayList<Remisiones>());
        }
        if (medico.getFormulasMedicasList() == null) {
            medico.setFormulasMedicasList(new ArrayList<FormulasMedicas>());
        }
        if (medico.getHospitalizacionesList() == null) {
            medico.setHospitalizacionesList(new ArrayList<Hospitalizaciones>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<CitasGenerales> attachedCitasGeneralesList = new ArrayList<CitasGenerales>();
            for (CitasGenerales citasGeneralesListCitasGeneralesToAttach : medico.getCitasGeneralesList()) {
                citasGeneralesListCitasGeneralesToAttach = em.getReference(citasGeneralesListCitasGeneralesToAttach.getClass(), citasGeneralesListCitasGeneralesToAttach.getCodigocita());
                attachedCitasGeneralesList.add(citasGeneralesListCitasGeneralesToAttach);
            }
            medico.setCitasGeneralesList(attachedCitasGeneralesList);
            List<Urgencias> attachedUrgenciasList = new ArrayList<Urgencias>();
            for (Urgencias urgenciasListUrgenciasToAttach : medico.getUrgenciasList()) {
                urgenciasListUrgenciasToAttach = em.getReference(urgenciasListUrgenciasToAttach.getClass(), urgenciasListUrgenciasToAttach.getCodigoUrgencia());
                attachedUrgenciasList.add(urgenciasListUrgenciasToAttach);
            }
            medico.setUrgenciasList(attachedUrgenciasList);
            List<Remisiones> attachedRemisionesList = new ArrayList<Remisiones>();
            for (Remisiones remisionesListRemisionesToAttach : medico.getRemisionesList()) {
                remisionesListRemisionesToAttach = em.getReference(remisionesListRemisionesToAttach.getClass(), remisionesListRemisionesToAttach.getCodigoRemision());
                attachedRemisionesList.add(remisionesListRemisionesToAttach);
            }
            medico.setRemisionesList(attachedRemisionesList);
            List<Remisiones> attachedRemisionesList1 = new ArrayList<Remisiones>();
            for (Remisiones remisionesList1RemisionesToAttach : medico.getRemisionesList1()) {
                remisionesList1RemisionesToAttach = em.getReference(remisionesList1RemisionesToAttach.getClass(), remisionesList1RemisionesToAttach.getCodigoRemision());
                attachedRemisionesList1.add(remisionesList1RemisionesToAttach);
            }
            medico.setRemisionesList1(attachedRemisionesList1);
            List<FormulasMedicas> attachedFormulasMedicasList = new ArrayList<FormulasMedicas>();
            for (FormulasMedicas formulasMedicasListFormulasMedicasToAttach : medico.getFormulasMedicasList()) {
                formulasMedicasListFormulasMedicasToAttach = em.getReference(formulasMedicasListFormulasMedicasToAttach.getClass(), formulasMedicasListFormulasMedicasToAttach.getCodigoFormula());
                attachedFormulasMedicasList.add(formulasMedicasListFormulasMedicasToAttach);
            }
            medico.setFormulasMedicasList(attachedFormulasMedicasList);
            List<Hospitalizaciones> attachedHospitalizacionesList = new ArrayList<Hospitalizaciones>();
            for (Hospitalizaciones hospitalizacionesListHospitalizacionesToAttach : medico.getHospitalizacionesList()) {
                hospitalizacionesListHospitalizacionesToAttach = em.getReference(hospitalizacionesListHospitalizacionesToAttach.getClass(), hospitalizacionesListHospitalizacionesToAttach.getCodigoHospitalizacion());
                attachedHospitalizacionesList.add(hospitalizacionesListHospitalizacionesToAttach);
            }
            medico.setHospitalizacionesList(attachedHospitalizacionesList);
            em.persist(medico);
            for (CitasGenerales citasGeneralesListCitasGenerales : medico.getCitasGeneralesList()) {
                Medico oldIDMedicoOfCitasGeneralesListCitasGenerales = citasGeneralesListCitasGenerales.getIDMedico();
                citasGeneralesListCitasGenerales.setIDMedico(medico);
                citasGeneralesListCitasGenerales = em.merge(citasGeneralesListCitasGenerales);
                if (oldIDMedicoOfCitasGeneralesListCitasGenerales != null) {
                    oldIDMedicoOfCitasGeneralesListCitasGenerales.getCitasGeneralesList().remove(citasGeneralesListCitasGenerales);
                    oldIDMedicoOfCitasGeneralesListCitasGenerales = em.merge(oldIDMedicoOfCitasGeneralesListCitasGenerales);
                }
            }
            for (Urgencias urgenciasListUrgencias : medico.getUrgenciasList()) {
                Medico oldIDMedicoOfUrgenciasListUrgencias = urgenciasListUrgencias.getIDMedico();
                urgenciasListUrgencias.setIDMedico(medico);
                urgenciasListUrgencias = em.merge(urgenciasListUrgencias);
                if (oldIDMedicoOfUrgenciasListUrgencias != null) {
                    oldIDMedicoOfUrgenciasListUrgencias.getUrgenciasList().remove(urgenciasListUrgencias);
                    oldIDMedicoOfUrgenciasListUrgencias = em.merge(oldIDMedicoOfUrgenciasListUrgencias);
                }
            }
            for (Remisiones remisionesListRemisiones : medico.getRemisionesList()) {
                Medico oldIDMedicoRemiteOfRemisionesListRemisiones = remisionesListRemisiones.getIDMedicoRemite();
                remisionesListRemisiones.setIDMedicoRemite(medico);
                remisionesListRemisiones = em.merge(remisionesListRemisiones);
                if (oldIDMedicoRemiteOfRemisionesListRemisiones != null) {
                    oldIDMedicoRemiteOfRemisionesListRemisiones.getRemisionesList().remove(remisionesListRemisiones);
                    oldIDMedicoRemiteOfRemisionesListRemisiones = em.merge(oldIDMedicoRemiteOfRemisionesListRemisiones);
                }
            }
            for (Remisiones remisionesList1Remisiones : medico.getRemisionesList1()) {
                Medico oldIDMedicoOfRemisionesList1Remisiones = remisionesList1Remisiones.getIDMedico();
                remisionesList1Remisiones.setIDMedico(medico);
                remisionesList1Remisiones = em.merge(remisionesList1Remisiones);
                if (oldIDMedicoOfRemisionesList1Remisiones != null) {
                    oldIDMedicoOfRemisionesList1Remisiones.getRemisionesList1().remove(remisionesList1Remisiones);
                    oldIDMedicoOfRemisionesList1Remisiones = em.merge(oldIDMedicoOfRemisionesList1Remisiones);
                }
            }
            for (FormulasMedicas formulasMedicasListFormulasMedicas : medico.getFormulasMedicasList()) {
                Medico oldIDMedicoOfFormulasMedicasListFormulasMedicas = formulasMedicasListFormulasMedicas.getIDMedico();
                formulasMedicasListFormulasMedicas.setIDMedico(medico);
                formulasMedicasListFormulasMedicas = em.merge(formulasMedicasListFormulasMedicas);
                if (oldIDMedicoOfFormulasMedicasListFormulasMedicas != null) {
                    oldIDMedicoOfFormulasMedicasListFormulasMedicas.getFormulasMedicasList().remove(formulasMedicasListFormulasMedicas);
                    oldIDMedicoOfFormulasMedicasListFormulasMedicas = em.merge(oldIDMedicoOfFormulasMedicasListFormulasMedicas);
                }
            }
            for (Hospitalizaciones hospitalizacionesListHospitalizaciones : medico.getHospitalizacionesList()) {
                Medico oldIDMedicoOfHospitalizacionesListHospitalizaciones = hospitalizacionesListHospitalizaciones.getIDMedico();
                hospitalizacionesListHospitalizaciones.setIDMedico(medico);
                hospitalizacionesListHospitalizaciones = em.merge(hospitalizacionesListHospitalizaciones);
                if (oldIDMedicoOfHospitalizacionesListHospitalizaciones != null) {
                    oldIDMedicoOfHospitalizacionesListHospitalizaciones.getHospitalizacionesList().remove(hospitalizacionesListHospitalizaciones);
                    oldIDMedicoOfHospitalizacionesListHospitalizaciones = em.merge(oldIDMedicoOfHospitalizacionesListHospitalizaciones);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findMedico(medico.getCedula()) != null) {
                throw new PreexistingEntityException("Medico " + medico + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Medico medico) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Medico persistentMedico = em.find(Medico.class, medico.getCedula());
            List<CitasGenerales> citasGeneralesListOld = persistentMedico.getCitasGeneralesList();
            List<CitasGenerales> citasGeneralesListNew = medico.getCitasGeneralesList();
            List<Urgencias> urgenciasListOld = persistentMedico.getUrgenciasList();
            List<Urgencias> urgenciasListNew = medico.getUrgenciasList();
            List<Remisiones> remisionesListOld = persistentMedico.getRemisionesList();
            List<Remisiones> remisionesListNew = medico.getRemisionesList();
            List<Remisiones> remisionesList1Old = persistentMedico.getRemisionesList1();
            List<Remisiones> remisionesList1New = medico.getRemisionesList1();
            List<FormulasMedicas> formulasMedicasListOld = persistentMedico.getFormulasMedicasList();
            List<FormulasMedicas> formulasMedicasListNew = medico.getFormulasMedicasList();
            List<Hospitalizaciones> hospitalizacionesListOld = persistentMedico.getHospitalizacionesList();
            List<Hospitalizaciones> hospitalizacionesListNew = medico.getHospitalizacionesList();
            List<CitasGenerales> attachedCitasGeneralesListNew = new ArrayList<CitasGenerales>();
            for (CitasGenerales citasGeneralesListNewCitasGeneralesToAttach : citasGeneralesListNew) {
                citasGeneralesListNewCitasGeneralesToAttach = em.getReference(citasGeneralesListNewCitasGeneralesToAttach.getClass(), citasGeneralesListNewCitasGeneralesToAttach.getCodigocita());
                attachedCitasGeneralesListNew.add(citasGeneralesListNewCitasGeneralesToAttach);
            }
            citasGeneralesListNew = attachedCitasGeneralesListNew;
            medico.setCitasGeneralesList(citasGeneralesListNew);
            List<Urgencias> attachedUrgenciasListNew = new ArrayList<Urgencias>();
            for (Urgencias urgenciasListNewUrgenciasToAttach : urgenciasListNew) {
                urgenciasListNewUrgenciasToAttach = em.getReference(urgenciasListNewUrgenciasToAttach.getClass(), urgenciasListNewUrgenciasToAttach.getCodigoUrgencia());
                attachedUrgenciasListNew.add(urgenciasListNewUrgenciasToAttach);
            }
            urgenciasListNew = attachedUrgenciasListNew;
            medico.setUrgenciasList(urgenciasListNew);
            List<Remisiones> attachedRemisionesListNew = new ArrayList<Remisiones>();
            for (Remisiones remisionesListNewRemisionesToAttach : remisionesListNew) {
                remisionesListNewRemisionesToAttach = em.getReference(remisionesListNewRemisionesToAttach.getClass(), remisionesListNewRemisionesToAttach.getCodigoRemision());
                attachedRemisionesListNew.add(remisionesListNewRemisionesToAttach);
            }
            remisionesListNew = attachedRemisionesListNew;
            medico.setRemisionesList(remisionesListNew);
            List<Remisiones> attachedRemisionesList1New = new ArrayList<Remisiones>();
            for (Remisiones remisionesList1NewRemisionesToAttach : remisionesList1New) {
                remisionesList1NewRemisionesToAttach = em.getReference(remisionesList1NewRemisionesToAttach.getClass(), remisionesList1NewRemisionesToAttach.getCodigoRemision());
                attachedRemisionesList1New.add(remisionesList1NewRemisionesToAttach);
            }
            remisionesList1New = attachedRemisionesList1New;
            medico.setRemisionesList1(remisionesList1New);
            List<FormulasMedicas> attachedFormulasMedicasListNew = new ArrayList<FormulasMedicas>();
            for (FormulasMedicas formulasMedicasListNewFormulasMedicasToAttach : formulasMedicasListNew) {
                formulasMedicasListNewFormulasMedicasToAttach = em.getReference(formulasMedicasListNewFormulasMedicasToAttach.getClass(), formulasMedicasListNewFormulasMedicasToAttach.getCodigoFormula());
                attachedFormulasMedicasListNew.add(formulasMedicasListNewFormulasMedicasToAttach);
            }
            formulasMedicasListNew = attachedFormulasMedicasListNew;
            medico.setFormulasMedicasList(formulasMedicasListNew);
            List<Hospitalizaciones> attachedHospitalizacionesListNew = new ArrayList<Hospitalizaciones>();
            for (Hospitalizaciones hospitalizacionesListNewHospitalizacionesToAttach : hospitalizacionesListNew) {
                hospitalizacionesListNewHospitalizacionesToAttach = em.getReference(hospitalizacionesListNewHospitalizacionesToAttach.getClass(), hospitalizacionesListNewHospitalizacionesToAttach.getCodigoHospitalizacion());
                attachedHospitalizacionesListNew.add(hospitalizacionesListNewHospitalizacionesToAttach);
            }
            hospitalizacionesListNew = attachedHospitalizacionesListNew;
            medico.setHospitalizacionesList(hospitalizacionesListNew);
            medico = em.merge(medico);
            for (CitasGenerales citasGeneralesListOldCitasGenerales : citasGeneralesListOld) {
                if (!citasGeneralesListNew.contains(citasGeneralesListOldCitasGenerales)) {
                    citasGeneralesListOldCitasGenerales.setIDMedico(null);
                    citasGeneralesListOldCitasGenerales = em.merge(citasGeneralesListOldCitasGenerales);
                }
            }
            for (CitasGenerales citasGeneralesListNewCitasGenerales : citasGeneralesListNew) {
                if (!citasGeneralesListOld.contains(citasGeneralesListNewCitasGenerales)) {
                    Medico oldIDMedicoOfCitasGeneralesListNewCitasGenerales = citasGeneralesListNewCitasGenerales.getIDMedico();
                    citasGeneralesListNewCitasGenerales.setIDMedico(medico);
                    citasGeneralesListNewCitasGenerales = em.merge(citasGeneralesListNewCitasGenerales);
                    if (oldIDMedicoOfCitasGeneralesListNewCitasGenerales != null && !oldIDMedicoOfCitasGeneralesListNewCitasGenerales.equals(medico)) {
                        oldIDMedicoOfCitasGeneralesListNewCitasGenerales.getCitasGeneralesList().remove(citasGeneralesListNewCitasGenerales);
                        oldIDMedicoOfCitasGeneralesListNewCitasGenerales = em.merge(oldIDMedicoOfCitasGeneralesListNewCitasGenerales);
                    }
                }
            }
            for (Urgencias urgenciasListOldUrgencias : urgenciasListOld) {
                if (!urgenciasListNew.contains(urgenciasListOldUrgencias)) {
                    urgenciasListOldUrgencias.setIDMedico(null);
                    urgenciasListOldUrgencias = em.merge(urgenciasListOldUrgencias);
                }
            }
            for (Urgencias urgenciasListNewUrgencias : urgenciasListNew) {
                if (!urgenciasListOld.contains(urgenciasListNewUrgencias)) {
                    Medico oldIDMedicoOfUrgenciasListNewUrgencias = urgenciasListNewUrgencias.getIDMedico();
                    urgenciasListNewUrgencias.setIDMedico(medico);
                    urgenciasListNewUrgencias = em.merge(urgenciasListNewUrgencias);
                    if (oldIDMedicoOfUrgenciasListNewUrgencias != null && !oldIDMedicoOfUrgenciasListNewUrgencias.equals(medico)) {
                        oldIDMedicoOfUrgenciasListNewUrgencias.getUrgenciasList().remove(urgenciasListNewUrgencias);
                        oldIDMedicoOfUrgenciasListNewUrgencias = em.merge(oldIDMedicoOfUrgenciasListNewUrgencias);
                    }
                }
            }
            for (Remisiones remisionesListOldRemisiones : remisionesListOld) {
                if (!remisionesListNew.contains(remisionesListOldRemisiones)) {
                    remisionesListOldRemisiones.setIDMedicoRemite(null);
                    remisionesListOldRemisiones = em.merge(remisionesListOldRemisiones);
                }
            }
            for (Remisiones remisionesListNewRemisiones : remisionesListNew) {
                if (!remisionesListOld.contains(remisionesListNewRemisiones)) {
                    Medico oldIDMedicoRemiteOfRemisionesListNewRemisiones = remisionesListNewRemisiones.getIDMedicoRemite();
                    remisionesListNewRemisiones.setIDMedicoRemite(medico);
                    remisionesListNewRemisiones = em.merge(remisionesListNewRemisiones);
                    if (oldIDMedicoRemiteOfRemisionesListNewRemisiones != null && !oldIDMedicoRemiteOfRemisionesListNewRemisiones.equals(medico)) {
                        oldIDMedicoRemiteOfRemisionesListNewRemisiones.getRemisionesList().remove(remisionesListNewRemisiones);
                        oldIDMedicoRemiteOfRemisionesListNewRemisiones = em.merge(oldIDMedicoRemiteOfRemisionesListNewRemisiones);
                    }
                }
            }
            for (Remisiones remisionesList1OldRemisiones : remisionesList1Old) {
                if (!remisionesList1New.contains(remisionesList1OldRemisiones)) {
                    remisionesList1OldRemisiones.setIDMedico(null);
                    remisionesList1OldRemisiones = em.merge(remisionesList1OldRemisiones);
                }
            }
            for (Remisiones remisionesList1NewRemisiones : remisionesList1New) {
                if (!remisionesList1Old.contains(remisionesList1NewRemisiones)) {
                    Medico oldIDMedicoOfRemisionesList1NewRemisiones = remisionesList1NewRemisiones.getIDMedico();
                    remisionesList1NewRemisiones.setIDMedico(medico);
                    remisionesList1NewRemisiones = em.merge(remisionesList1NewRemisiones);
                    if (oldIDMedicoOfRemisionesList1NewRemisiones != null && !oldIDMedicoOfRemisionesList1NewRemisiones.equals(medico)) {
                        oldIDMedicoOfRemisionesList1NewRemisiones.getRemisionesList1().remove(remisionesList1NewRemisiones);
                        oldIDMedicoOfRemisionesList1NewRemisiones = em.merge(oldIDMedicoOfRemisionesList1NewRemisiones);
                    }
                }
            }
            for (FormulasMedicas formulasMedicasListOldFormulasMedicas : formulasMedicasListOld) {
                if (!formulasMedicasListNew.contains(formulasMedicasListOldFormulasMedicas)) {
                    formulasMedicasListOldFormulasMedicas.setIDMedico(null);
                    formulasMedicasListOldFormulasMedicas = em.merge(formulasMedicasListOldFormulasMedicas);
                }
            }
            for (FormulasMedicas formulasMedicasListNewFormulasMedicas : formulasMedicasListNew) {
                if (!formulasMedicasListOld.contains(formulasMedicasListNewFormulasMedicas)) {
                    Medico oldIDMedicoOfFormulasMedicasListNewFormulasMedicas = formulasMedicasListNewFormulasMedicas.getIDMedico();
                    formulasMedicasListNewFormulasMedicas.setIDMedico(medico);
                    formulasMedicasListNewFormulasMedicas = em.merge(formulasMedicasListNewFormulasMedicas);
                    if (oldIDMedicoOfFormulasMedicasListNewFormulasMedicas != null && !oldIDMedicoOfFormulasMedicasListNewFormulasMedicas.equals(medico)) {
                        oldIDMedicoOfFormulasMedicasListNewFormulasMedicas.getFormulasMedicasList().remove(formulasMedicasListNewFormulasMedicas);
                        oldIDMedicoOfFormulasMedicasListNewFormulasMedicas = em.merge(oldIDMedicoOfFormulasMedicasListNewFormulasMedicas);
                    }
                }
            }
            for (Hospitalizaciones hospitalizacionesListOldHospitalizaciones : hospitalizacionesListOld) {
                if (!hospitalizacionesListNew.contains(hospitalizacionesListOldHospitalizaciones)) {
                    hospitalizacionesListOldHospitalizaciones.setIDMedico(null);
                    hospitalizacionesListOldHospitalizaciones = em.merge(hospitalizacionesListOldHospitalizaciones);
                }
            }
            for (Hospitalizaciones hospitalizacionesListNewHospitalizaciones : hospitalizacionesListNew) {
                if (!hospitalizacionesListOld.contains(hospitalizacionesListNewHospitalizaciones)) {
                    Medico oldIDMedicoOfHospitalizacionesListNewHospitalizaciones = hospitalizacionesListNewHospitalizaciones.getIDMedico();
                    hospitalizacionesListNewHospitalizaciones.setIDMedico(medico);
                    hospitalizacionesListNewHospitalizaciones = em.merge(hospitalizacionesListNewHospitalizaciones);
                    if (oldIDMedicoOfHospitalizacionesListNewHospitalizaciones != null && !oldIDMedicoOfHospitalizacionesListNewHospitalizaciones.equals(medico)) {
                        oldIDMedicoOfHospitalizacionesListNewHospitalizaciones.getHospitalizacionesList().remove(hospitalizacionesListNewHospitalizaciones);
                        oldIDMedicoOfHospitalizacionesListNewHospitalizaciones = em.merge(oldIDMedicoOfHospitalizacionesListNewHospitalizaciones);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = medico.getCedula();
                if (findMedico(id) == null) {
                    throw new NonexistentEntityException("The medico with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Medico medico;
            try {
                medico = em.getReference(Medico.class, id);
                medico.getCedula();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The medico with id " + id + " no longer exists.", enfe);
            }
            List<CitasGenerales> citasGeneralesList = medico.getCitasGeneralesList();
            for (CitasGenerales citasGeneralesListCitasGenerales : citasGeneralesList) {
                citasGeneralesListCitasGenerales.setIDMedico(null);
                citasGeneralesListCitasGenerales = em.merge(citasGeneralesListCitasGenerales);
            }
            List<Urgencias> urgenciasList = medico.getUrgenciasList();
            for (Urgencias urgenciasListUrgencias : urgenciasList) {
                urgenciasListUrgencias.setIDMedico(null);
                urgenciasListUrgencias = em.merge(urgenciasListUrgencias);
            }
            List<Remisiones> remisionesList = medico.getRemisionesList();
            for (Remisiones remisionesListRemisiones : remisionesList) {
                remisionesListRemisiones.setIDMedicoRemite(null);
                remisionesListRemisiones = em.merge(remisionesListRemisiones);
            }
            List<Remisiones> remisionesList1 = medico.getRemisionesList1();
            for (Remisiones remisionesList1Remisiones : remisionesList1) {
                remisionesList1Remisiones.setIDMedico(null);
                remisionesList1Remisiones = em.merge(remisionesList1Remisiones);
            }
            List<FormulasMedicas> formulasMedicasList = medico.getFormulasMedicasList();
            for (FormulasMedicas formulasMedicasListFormulasMedicas : formulasMedicasList) {
                formulasMedicasListFormulasMedicas.setIDMedico(null);
                formulasMedicasListFormulasMedicas = em.merge(formulasMedicasListFormulasMedicas);
            }
            List<Hospitalizaciones> hospitalizacionesList = medico.getHospitalizacionesList();
            for (Hospitalizaciones hospitalizacionesListHospitalizaciones : hospitalizacionesList) {
                hospitalizacionesListHospitalizaciones.setIDMedico(null);
                hospitalizacionesListHospitalizaciones = em.merge(hospitalizacionesListHospitalizaciones);
            }
            em.remove(medico);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Medico> findMedicoEntities() {
        return findMedicoEntities(true, -1, -1);
    }

    public List<Medico> findMedicoEntities(int maxResults, int firstResult) {
        return findMedicoEntities(false, maxResults, firstResult);
    }

    private List<Medico> findMedicoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Medico.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Medico findMedico(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Medico.class, id);
        } finally {
            em.close();
        }
    }

    public int getMedicoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Medico> rt = cq.from(Medico.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
