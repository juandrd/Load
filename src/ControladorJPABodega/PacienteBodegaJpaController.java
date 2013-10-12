/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladorJPABodega;

import ControladorJPABodega.exceptions.IllegalOrphanException;
import ControladorJPABodega.exceptions.NonexistentEntityException;
import ControladorJPABodega.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entidades_Bodega.CitasBodega;
import java.util.ArrayList;
import java.util.List;
import Entidades_Bodega.FormulasBodega;
import Entidades_Bodega.PagosBodega;
import Entidades_Bodega.AfiliacionesBodega;
import Entidades_Bodega.PacienteBodega;
import Entidades_Bodega.RetirosBodega;
import Entidades_Bodega.UrgenciasBodega;
import Entidades_Bodega.RemisionesBodega;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author USER
 */
public class PacienteBodegaJpaController implements Serializable {

    public PacienteBodegaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PacienteBodega pacienteBodega) throws PreexistingEntityException, Exception {
        if (pacienteBodega.getCitasBodegaList() == null) {
            pacienteBodega.setCitasBodegaList(new ArrayList<CitasBodega>());
        }
        if (pacienteBodega.getFormulasBodegaList() == null) {
            pacienteBodega.setFormulasBodegaList(new ArrayList<FormulasBodega>());
        }
        if (pacienteBodega.getPagosBodegaList() == null) {
            pacienteBodega.setPagosBodegaList(new ArrayList<PagosBodega>());
        }
        if (pacienteBodega.getAfiliacionesBodegaList() == null) {
            pacienteBodega.setAfiliacionesBodegaList(new ArrayList<AfiliacionesBodega>());
        }
        if (pacienteBodega.getRetirosBodegaList() == null) {
            pacienteBodega.setRetirosBodegaList(new ArrayList<RetirosBodega>());
        }
        if (pacienteBodega.getUrgenciasBodegaList() == null) {
            pacienteBodega.setUrgenciasBodegaList(new ArrayList<UrgenciasBodega>());
        }
        if (pacienteBodega.getRemisionesBodegaList() == null) {
            pacienteBodega.setRemisionesBodegaList(new ArrayList<RemisionesBodega>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<CitasBodega> attachedCitasBodegaList = new ArrayList<CitasBodega>();
            for (CitasBodega citasBodegaListCitasBodegaToAttach : pacienteBodega.getCitasBodegaList()) {
                citasBodegaListCitasBodegaToAttach = em.getReference(citasBodegaListCitasBodegaToAttach.getClass(), citasBodegaListCitasBodegaToAttach.getCitasBodegaPK());
                attachedCitasBodegaList.add(citasBodegaListCitasBodegaToAttach);
            }
            pacienteBodega.setCitasBodegaList(attachedCitasBodegaList);
            List<FormulasBodega> attachedFormulasBodegaList = new ArrayList<FormulasBodega>();
            for (FormulasBodega formulasBodegaListFormulasBodegaToAttach : pacienteBodega.getFormulasBodegaList()) {
                formulasBodegaListFormulasBodegaToAttach = em.getReference(formulasBodegaListFormulasBodegaToAttach.getClass(), formulasBodegaListFormulasBodegaToAttach.getFormulasBodegaPK());
                attachedFormulasBodegaList.add(formulasBodegaListFormulasBodegaToAttach);
            }
            pacienteBodega.setFormulasBodegaList(attachedFormulasBodegaList);
            List<PagosBodega> attachedPagosBodegaList = new ArrayList<PagosBodega>();
            for (PagosBodega pagosBodegaListPagosBodegaToAttach : pacienteBodega.getPagosBodegaList()) {
                pagosBodegaListPagosBodegaToAttach = em.getReference(pagosBodegaListPagosBodegaToAttach.getClass(), pagosBodegaListPagosBodegaToAttach.getPagosBodegaPK());
                attachedPagosBodegaList.add(pagosBodegaListPagosBodegaToAttach);
            }
            pacienteBodega.setPagosBodegaList(attachedPagosBodegaList);
            List<AfiliacionesBodega> attachedAfiliacionesBodegaList = new ArrayList<AfiliacionesBodega>();
            for (AfiliacionesBodega afiliacionesBodegaListAfiliacionesBodegaToAttach : pacienteBodega.getAfiliacionesBodegaList()) {
                afiliacionesBodegaListAfiliacionesBodegaToAttach = em.getReference(afiliacionesBodegaListAfiliacionesBodegaToAttach.getClass(), afiliacionesBodegaListAfiliacionesBodegaToAttach.getAfiliacionesBodegaPK());
                attachedAfiliacionesBodegaList.add(afiliacionesBodegaListAfiliacionesBodegaToAttach);
            }
            pacienteBodega.setAfiliacionesBodegaList(attachedAfiliacionesBodegaList);
            List<RetirosBodega> attachedRetirosBodegaList = new ArrayList<RetirosBodega>();
            for (RetirosBodega retirosBodegaListRetirosBodegaToAttach : pacienteBodega.getRetirosBodegaList()) {
                retirosBodegaListRetirosBodegaToAttach = em.getReference(retirosBodegaListRetirosBodegaToAttach.getClass(), retirosBodegaListRetirosBodegaToAttach.getRetirosBodegaPK());
                attachedRetirosBodegaList.add(retirosBodegaListRetirosBodegaToAttach);
            }
            pacienteBodega.setRetirosBodegaList(attachedRetirosBodegaList);
            List<UrgenciasBodega> attachedUrgenciasBodegaList = new ArrayList<UrgenciasBodega>();
            for (UrgenciasBodega urgenciasBodegaListUrgenciasBodegaToAttach : pacienteBodega.getUrgenciasBodegaList()) {
                urgenciasBodegaListUrgenciasBodegaToAttach = em.getReference(urgenciasBodegaListUrgenciasBodegaToAttach.getClass(), urgenciasBodegaListUrgenciasBodegaToAttach.getUrgenciasBodegaPK());
                attachedUrgenciasBodegaList.add(urgenciasBodegaListUrgenciasBodegaToAttach);
            }
            pacienteBodega.setUrgenciasBodegaList(attachedUrgenciasBodegaList);
            List<RemisionesBodega> attachedRemisionesBodegaList = new ArrayList<RemisionesBodega>();
            for (RemisionesBodega remisionesBodegaListRemisionesBodegaToAttach : pacienteBodega.getRemisionesBodegaList()) {
                remisionesBodegaListRemisionesBodegaToAttach = em.getReference(remisionesBodegaListRemisionesBodegaToAttach.getClass(), remisionesBodegaListRemisionesBodegaToAttach.getRemisionesBodegaPK());
                attachedRemisionesBodegaList.add(remisionesBodegaListRemisionesBodegaToAttach);
            }
            pacienteBodega.setRemisionesBodegaList(attachedRemisionesBodegaList);
            em.persist(pacienteBodega);
            for (CitasBodega citasBodegaListCitasBodega : pacienteBodega.getCitasBodegaList()) {
                PacienteBodega oldPacienteBodegaOfCitasBodegaListCitasBodega = citasBodegaListCitasBodega.getPacienteBodega();
                citasBodegaListCitasBodega.setPacienteBodega(pacienteBodega);
                citasBodegaListCitasBodega = em.merge(citasBodegaListCitasBodega);
                if (oldPacienteBodegaOfCitasBodegaListCitasBodega != null) {
                    oldPacienteBodegaOfCitasBodegaListCitasBodega.getCitasBodegaList().remove(citasBodegaListCitasBodega);
                    oldPacienteBodegaOfCitasBodegaListCitasBodega = em.merge(oldPacienteBodegaOfCitasBodegaListCitasBodega);
                }
            }
            for (FormulasBodega formulasBodegaListFormulasBodega : pacienteBodega.getFormulasBodegaList()) {
                PacienteBodega oldPacienteBodegaOfFormulasBodegaListFormulasBodega = formulasBodegaListFormulasBodega.getPacienteBodega();
                formulasBodegaListFormulasBodega.setPacienteBodega(pacienteBodega);
                formulasBodegaListFormulasBodega = em.merge(formulasBodegaListFormulasBodega);
                if (oldPacienteBodegaOfFormulasBodegaListFormulasBodega != null) {
                    oldPacienteBodegaOfFormulasBodegaListFormulasBodega.getFormulasBodegaList().remove(formulasBodegaListFormulasBodega);
                    oldPacienteBodegaOfFormulasBodegaListFormulasBodega = em.merge(oldPacienteBodegaOfFormulasBodegaListFormulasBodega);
                }
            }
            for (PagosBodega pagosBodegaListPagosBodega : pacienteBodega.getPagosBodegaList()) {
                PacienteBodega oldPacienteBodegaOfPagosBodegaListPagosBodega = pagosBodegaListPagosBodega.getPacienteBodega();
                pagosBodegaListPagosBodega.setPacienteBodega(pacienteBodega);
                pagosBodegaListPagosBodega = em.merge(pagosBodegaListPagosBodega);
                if (oldPacienteBodegaOfPagosBodegaListPagosBodega != null) {
                    oldPacienteBodegaOfPagosBodegaListPagosBodega.getPagosBodegaList().remove(pagosBodegaListPagosBodega);
                    oldPacienteBodegaOfPagosBodegaListPagosBodega = em.merge(oldPacienteBodegaOfPagosBodegaListPagosBodega);
                }
            }
            for (AfiliacionesBodega afiliacionesBodegaListAfiliacionesBodega : pacienteBodega.getAfiliacionesBodegaList()) {
                PacienteBodega oldPacienteBodegaOfAfiliacionesBodegaListAfiliacionesBodega = afiliacionesBodegaListAfiliacionesBodega.getPacienteBodega();
                afiliacionesBodegaListAfiliacionesBodega.setPacienteBodega(pacienteBodega);
                afiliacionesBodegaListAfiliacionesBodega = em.merge(afiliacionesBodegaListAfiliacionesBodega);
                if (oldPacienteBodegaOfAfiliacionesBodegaListAfiliacionesBodega != null) {
                    oldPacienteBodegaOfAfiliacionesBodegaListAfiliacionesBodega.getAfiliacionesBodegaList().remove(afiliacionesBodegaListAfiliacionesBodega);
                    oldPacienteBodegaOfAfiliacionesBodegaListAfiliacionesBodega = em.merge(oldPacienteBodegaOfAfiliacionesBodegaListAfiliacionesBodega);
                }
            }
            for (RetirosBodega retirosBodegaListRetirosBodega : pacienteBodega.getRetirosBodegaList()) {
                PacienteBodega oldPacienteBodegaOfRetirosBodegaListRetirosBodega = retirosBodegaListRetirosBodega.getPacienteBodega();
                retirosBodegaListRetirosBodega.setPacienteBodega(pacienteBodega);
                retirosBodegaListRetirosBodega = em.merge(retirosBodegaListRetirosBodega);
                if (oldPacienteBodegaOfRetirosBodegaListRetirosBodega != null) {
                    oldPacienteBodegaOfRetirosBodegaListRetirosBodega.getRetirosBodegaList().remove(retirosBodegaListRetirosBodega);
                    oldPacienteBodegaOfRetirosBodegaListRetirosBodega = em.merge(oldPacienteBodegaOfRetirosBodegaListRetirosBodega);
                }
            }
            for (UrgenciasBodega urgenciasBodegaListUrgenciasBodega : pacienteBodega.getUrgenciasBodegaList()) {
                PacienteBodega oldPacienteBodegaOfUrgenciasBodegaListUrgenciasBodega = urgenciasBodegaListUrgenciasBodega.getPacienteBodega();
                urgenciasBodegaListUrgenciasBodega.setPacienteBodega(pacienteBodega);
                urgenciasBodegaListUrgenciasBodega = em.merge(urgenciasBodegaListUrgenciasBodega);
                if (oldPacienteBodegaOfUrgenciasBodegaListUrgenciasBodega != null) {
                    oldPacienteBodegaOfUrgenciasBodegaListUrgenciasBodega.getUrgenciasBodegaList().remove(urgenciasBodegaListUrgenciasBodega);
                    oldPacienteBodegaOfUrgenciasBodegaListUrgenciasBodega = em.merge(oldPacienteBodegaOfUrgenciasBodegaListUrgenciasBodega);
                }
            }
            for (RemisionesBodega remisionesBodegaListRemisionesBodega : pacienteBodega.getRemisionesBodegaList()) {
                PacienteBodega oldPacienteBodegaOfRemisionesBodegaListRemisionesBodega = remisionesBodegaListRemisionesBodega.getPacienteBodega();
                remisionesBodegaListRemisionesBodega.setPacienteBodega(pacienteBodega);
                remisionesBodegaListRemisionesBodega = em.merge(remisionesBodegaListRemisionesBodega);
                if (oldPacienteBodegaOfRemisionesBodegaListRemisionesBodega != null) {
                    oldPacienteBodegaOfRemisionesBodegaListRemisionesBodega.getRemisionesBodegaList().remove(remisionesBodegaListRemisionesBodega);
                    oldPacienteBodegaOfRemisionesBodegaListRemisionesBodega = em.merge(oldPacienteBodegaOfRemisionesBodegaListRemisionesBodega);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPacienteBodega(pacienteBodega.getPacienteKey()) != null) {
                throw new PreexistingEntityException("PacienteBodega " + pacienteBodega + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PacienteBodega pacienteBodega) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PacienteBodega persistentPacienteBodega = em.find(PacienteBodega.class, pacienteBodega.getPacienteKey());
            List<CitasBodega> citasBodegaListOld = persistentPacienteBodega.getCitasBodegaList();
            List<CitasBodega> citasBodegaListNew = pacienteBodega.getCitasBodegaList();
            List<FormulasBodega> formulasBodegaListOld = persistentPacienteBodega.getFormulasBodegaList();
            List<FormulasBodega> formulasBodegaListNew = pacienteBodega.getFormulasBodegaList();
            List<PagosBodega> pagosBodegaListOld = persistentPacienteBodega.getPagosBodegaList();
            List<PagosBodega> pagosBodegaListNew = pacienteBodega.getPagosBodegaList();
            List<AfiliacionesBodega> afiliacionesBodegaListOld = persistentPacienteBodega.getAfiliacionesBodegaList();
            List<AfiliacionesBodega> afiliacionesBodegaListNew = pacienteBodega.getAfiliacionesBodegaList();
            List<RetirosBodega> retirosBodegaListOld = persistentPacienteBodega.getRetirosBodegaList();
            List<RetirosBodega> retirosBodegaListNew = pacienteBodega.getRetirosBodegaList();
            List<UrgenciasBodega> urgenciasBodegaListOld = persistentPacienteBodega.getUrgenciasBodegaList();
            List<UrgenciasBodega> urgenciasBodegaListNew = pacienteBodega.getUrgenciasBodegaList();
            List<RemisionesBodega> remisionesBodegaListOld = persistentPacienteBodega.getRemisionesBodegaList();
            List<RemisionesBodega> remisionesBodegaListNew = pacienteBodega.getRemisionesBodegaList();
            List<String> illegalOrphanMessages = null;
            for (CitasBodega citasBodegaListOldCitasBodega : citasBodegaListOld) {
                if (!citasBodegaListNew.contains(citasBodegaListOldCitasBodega)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain CitasBodega " + citasBodegaListOldCitasBodega + " since its pacienteBodega field is not nullable.");
                }
            }
            for (FormulasBodega formulasBodegaListOldFormulasBodega : formulasBodegaListOld) {
                if (!formulasBodegaListNew.contains(formulasBodegaListOldFormulasBodega)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain FormulasBodega " + formulasBodegaListOldFormulasBodega + " since its pacienteBodega field is not nullable.");
                }
            }
            for (PagosBodega pagosBodegaListOldPagosBodega : pagosBodegaListOld) {
                if (!pagosBodegaListNew.contains(pagosBodegaListOldPagosBodega)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain PagosBodega " + pagosBodegaListOldPagosBodega + " since its pacienteBodega field is not nullable.");
                }
            }
            for (AfiliacionesBodega afiliacionesBodegaListOldAfiliacionesBodega : afiliacionesBodegaListOld) {
                if (!afiliacionesBodegaListNew.contains(afiliacionesBodegaListOldAfiliacionesBodega)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain AfiliacionesBodega " + afiliacionesBodegaListOldAfiliacionesBodega + " since its pacienteBodega field is not nullable.");
                }
            }
            for (RetirosBodega retirosBodegaListOldRetirosBodega : retirosBodegaListOld) {
                if (!retirosBodegaListNew.contains(retirosBodegaListOldRetirosBodega)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain RetirosBodega " + retirosBodegaListOldRetirosBodega + " since its pacienteBodega field is not nullable.");
                }
            }
            for (UrgenciasBodega urgenciasBodegaListOldUrgenciasBodega : urgenciasBodegaListOld) {
                if (!urgenciasBodegaListNew.contains(urgenciasBodegaListOldUrgenciasBodega)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain UrgenciasBodega " + urgenciasBodegaListOldUrgenciasBodega + " since its pacienteBodega field is not nullable.");
                }
            }
            for (RemisionesBodega remisionesBodegaListOldRemisionesBodega : remisionesBodegaListOld) {
                if (!remisionesBodegaListNew.contains(remisionesBodegaListOldRemisionesBodega)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain RemisionesBodega " + remisionesBodegaListOldRemisionesBodega + " since its pacienteBodega field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<CitasBodega> attachedCitasBodegaListNew = new ArrayList<CitasBodega>();
            for (CitasBodega citasBodegaListNewCitasBodegaToAttach : citasBodegaListNew) {
                citasBodegaListNewCitasBodegaToAttach = em.getReference(citasBodegaListNewCitasBodegaToAttach.getClass(), citasBodegaListNewCitasBodegaToAttach.getCitasBodegaPK());
                attachedCitasBodegaListNew.add(citasBodegaListNewCitasBodegaToAttach);
            }
            citasBodegaListNew = attachedCitasBodegaListNew;
            pacienteBodega.setCitasBodegaList(citasBodegaListNew);
            List<FormulasBodega> attachedFormulasBodegaListNew = new ArrayList<FormulasBodega>();
            for (FormulasBodega formulasBodegaListNewFormulasBodegaToAttach : formulasBodegaListNew) {
                formulasBodegaListNewFormulasBodegaToAttach = em.getReference(formulasBodegaListNewFormulasBodegaToAttach.getClass(), formulasBodegaListNewFormulasBodegaToAttach.getFormulasBodegaPK());
                attachedFormulasBodegaListNew.add(formulasBodegaListNewFormulasBodegaToAttach);
            }
            formulasBodegaListNew = attachedFormulasBodegaListNew;
            pacienteBodega.setFormulasBodegaList(formulasBodegaListNew);
            List<PagosBodega> attachedPagosBodegaListNew = new ArrayList<PagosBodega>();
            for (PagosBodega pagosBodegaListNewPagosBodegaToAttach : pagosBodegaListNew) {
                pagosBodegaListNewPagosBodegaToAttach = em.getReference(pagosBodegaListNewPagosBodegaToAttach.getClass(), pagosBodegaListNewPagosBodegaToAttach.getPagosBodegaPK());
                attachedPagosBodegaListNew.add(pagosBodegaListNewPagosBodegaToAttach);
            }
            pagosBodegaListNew = attachedPagosBodegaListNew;
            pacienteBodega.setPagosBodegaList(pagosBodegaListNew);
            List<AfiliacionesBodega> attachedAfiliacionesBodegaListNew = new ArrayList<AfiliacionesBodega>();
            for (AfiliacionesBodega afiliacionesBodegaListNewAfiliacionesBodegaToAttach : afiliacionesBodegaListNew) {
                afiliacionesBodegaListNewAfiliacionesBodegaToAttach = em.getReference(afiliacionesBodegaListNewAfiliacionesBodegaToAttach.getClass(), afiliacionesBodegaListNewAfiliacionesBodegaToAttach.getAfiliacionesBodegaPK());
                attachedAfiliacionesBodegaListNew.add(afiliacionesBodegaListNewAfiliacionesBodegaToAttach);
            }
            afiliacionesBodegaListNew = attachedAfiliacionesBodegaListNew;
            pacienteBodega.setAfiliacionesBodegaList(afiliacionesBodegaListNew);
            List<RetirosBodega> attachedRetirosBodegaListNew = new ArrayList<RetirosBodega>();
            for (RetirosBodega retirosBodegaListNewRetirosBodegaToAttach : retirosBodegaListNew) {
                retirosBodegaListNewRetirosBodegaToAttach = em.getReference(retirosBodegaListNewRetirosBodegaToAttach.getClass(), retirosBodegaListNewRetirosBodegaToAttach.getRetirosBodegaPK());
                attachedRetirosBodegaListNew.add(retirosBodegaListNewRetirosBodegaToAttach);
            }
            retirosBodegaListNew = attachedRetirosBodegaListNew;
            pacienteBodega.setRetirosBodegaList(retirosBodegaListNew);
            List<UrgenciasBodega> attachedUrgenciasBodegaListNew = new ArrayList<UrgenciasBodega>();
            for (UrgenciasBodega urgenciasBodegaListNewUrgenciasBodegaToAttach : urgenciasBodegaListNew) {
                urgenciasBodegaListNewUrgenciasBodegaToAttach = em.getReference(urgenciasBodegaListNewUrgenciasBodegaToAttach.getClass(), urgenciasBodegaListNewUrgenciasBodegaToAttach.getUrgenciasBodegaPK());
                attachedUrgenciasBodegaListNew.add(urgenciasBodegaListNewUrgenciasBodegaToAttach);
            }
            urgenciasBodegaListNew = attachedUrgenciasBodegaListNew;
            pacienteBodega.setUrgenciasBodegaList(urgenciasBodegaListNew);
            List<RemisionesBodega> attachedRemisionesBodegaListNew = new ArrayList<RemisionesBodega>();
            for (RemisionesBodega remisionesBodegaListNewRemisionesBodegaToAttach : remisionesBodegaListNew) {
                remisionesBodegaListNewRemisionesBodegaToAttach = em.getReference(remisionesBodegaListNewRemisionesBodegaToAttach.getClass(), remisionesBodegaListNewRemisionesBodegaToAttach.getRemisionesBodegaPK());
                attachedRemisionesBodegaListNew.add(remisionesBodegaListNewRemisionesBodegaToAttach);
            }
            remisionesBodegaListNew = attachedRemisionesBodegaListNew;
            pacienteBodega.setRemisionesBodegaList(remisionesBodegaListNew);
            pacienteBodega = em.merge(pacienteBodega);
            for (CitasBodega citasBodegaListNewCitasBodega : citasBodegaListNew) {
                if (!citasBodegaListOld.contains(citasBodegaListNewCitasBodega)) {
                    PacienteBodega oldPacienteBodegaOfCitasBodegaListNewCitasBodega = citasBodegaListNewCitasBodega.getPacienteBodega();
                    citasBodegaListNewCitasBodega.setPacienteBodega(pacienteBodega);
                    citasBodegaListNewCitasBodega = em.merge(citasBodegaListNewCitasBodega);
                    if (oldPacienteBodegaOfCitasBodegaListNewCitasBodega != null && !oldPacienteBodegaOfCitasBodegaListNewCitasBodega.equals(pacienteBodega)) {
                        oldPacienteBodegaOfCitasBodegaListNewCitasBodega.getCitasBodegaList().remove(citasBodegaListNewCitasBodega);
                        oldPacienteBodegaOfCitasBodegaListNewCitasBodega = em.merge(oldPacienteBodegaOfCitasBodegaListNewCitasBodega);
                    }
                }
            }
            for (FormulasBodega formulasBodegaListNewFormulasBodega : formulasBodegaListNew) {
                if (!formulasBodegaListOld.contains(formulasBodegaListNewFormulasBodega)) {
                    PacienteBodega oldPacienteBodegaOfFormulasBodegaListNewFormulasBodega = formulasBodegaListNewFormulasBodega.getPacienteBodega();
                    formulasBodegaListNewFormulasBodega.setPacienteBodega(pacienteBodega);
                    formulasBodegaListNewFormulasBodega = em.merge(formulasBodegaListNewFormulasBodega);
                    if (oldPacienteBodegaOfFormulasBodegaListNewFormulasBodega != null && !oldPacienteBodegaOfFormulasBodegaListNewFormulasBodega.equals(pacienteBodega)) {
                        oldPacienteBodegaOfFormulasBodegaListNewFormulasBodega.getFormulasBodegaList().remove(formulasBodegaListNewFormulasBodega);
                        oldPacienteBodegaOfFormulasBodegaListNewFormulasBodega = em.merge(oldPacienteBodegaOfFormulasBodegaListNewFormulasBodega);
                    }
                }
            }
            for (PagosBodega pagosBodegaListNewPagosBodega : pagosBodegaListNew) {
                if (!pagosBodegaListOld.contains(pagosBodegaListNewPagosBodega)) {
                    PacienteBodega oldPacienteBodegaOfPagosBodegaListNewPagosBodega = pagosBodegaListNewPagosBodega.getPacienteBodega();
                    pagosBodegaListNewPagosBodega.setPacienteBodega(pacienteBodega);
                    pagosBodegaListNewPagosBodega = em.merge(pagosBodegaListNewPagosBodega);
                    if (oldPacienteBodegaOfPagosBodegaListNewPagosBodega != null && !oldPacienteBodegaOfPagosBodegaListNewPagosBodega.equals(pacienteBodega)) {
                        oldPacienteBodegaOfPagosBodegaListNewPagosBodega.getPagosBodegaList().remove(pagosBodegaListNewPagosBodega);
                        oldPacienteBodegaOfPagosBodegaListNewPagosBodega = em.merge(oldPacienteBodegaOfPagosBodegaListNewPagosBodega);
                    }
                }
            }
            for (AfiliacionesBodega afiliacionesBodegaListNewAfiliacionesBodega : afiliacionesBodegaListNew) {
                if (!afiliacionesBodegaListOld.contains(afiliacionesBodegaListNewAfiliacionesBodega)) {
                    PacienteBodega oldPacienteBodegaOfAfiliacionesBodegaListNewAfiliacionesBodega = afiliacionesBodegaListNewAfiliacionesBodega.getPacienteBodega();
                    afiliacionesBodegaListNewAfiliacionesBodega.setPacienteBodega(pacienteBodega);
                    afiliacionesBodegaListNewAfiliacionesBodega = em.merge(afiliacionesBodegaListNewAfiliacionesBodega);
                    if (oldPacienteBodegaOfAfiliacionesBodegaListNewAfiliacionesBodega != null && !oldPacienteBodegaOfAfiliacionesBodegaListNewAfiliacionesBodega.equals(pacienteBodega)) {
                        oldPacienteBodegaOfAfiliacionesBodegaListNewAfiliacionesBodega.getAfiliacionesBodegaList().remove(afiliacionesBodegaListNewAfiliacionesBodega);
                        oldPacienteBodegaOfAfiliacionesBodegaListNewAfiliacionesBodega = em.merge(oldPacienteBodegaOfAfiliacionesBodegaListNewAfiliacionesBodega);
                    }
                }
            }
            for (RetirosBodega retirosBodegaListNewRetirosBodega : retirosBodegaListNew) {
                if (!retirosBodegaListOld.contains(retirosBodegaListNewRetirosBodega)) {
                    PacienteBodega oldPacienteBodegaOfRetirosBodegaListNewRetirosBodega = retirosBodegaListNewRetirosBodega.getPacienteBodega();
                    retirosBodegaListNewRetirosBodega.setPacienteBodega(pacienteBodega);
                    retirosBodegaListNewRetirosBodega = em.merge(retirosBodegaListNewRetirosBodega);
                    if (oldPacienteBodegaOfRetirosBodegaListNewRetirosBodega != null && !oldPacienteBodegaOfRetirosBodegaListNewRetirosBodega.equals(pacienteBodega)) {
                        oldPacienteBodegaOfRetirosBodegaListNewRetirosBodega.getRetirosBodegaList().remove(retirosBodegaListNewRetirosBodega);
                        oldPacienteBodegaOfRetirosBodegaListNewRetirosBodega = em.merge(oldPacienteBodegaOfRetirosBodegaListNewRetirosBodega);
                    }
                }
            }
            for (UrgenciasBodega urgenciasBodegaListNewUrgenciasBodega : urgenciasBodegaListNew) {
                if (!urgenciasBodegaListOld.contains(urgenciasBodegaListNewUrgenciasBodega)) {
                    PacienteBodega oldPacienteBodegaOfUrgenciasBodegaListNewUrgenciasBodega = urgenciasBodegaListNewUrgenciasBodega.getPacienteBodega();
                    urgenciasBodegaListNewUrgenciasBodega.setPacienteBodega(pacienteBodega);
                    urgenciasBodegaListNewUrgenciasBodega = em.merge(urgenciasBodegaListNewUrgenciasBodega);
                    if (oldPacienteBodegaOfUrgenciasBodegaListNewUrgenciasBodega != null && !oldPacienteBodegaOfUrgenciasBodegaListNewUrgenciasBodega.equals(pacienteBodega)) {
                        oldPacienteBodegaOfUrgenciasBodegaListNewUrgenciasBodega.getUrgenciasBodegaList().remove(urgenciasBodegaListNewUrgenciasBodega);
                        oldPacienteBodegaOfUrgenciasBodegaListNewUrgenciasBodega = em.merge(oldPacienteBodegaOfUrgenciasBodegaListNewUrgenciasBodega);
                    }
                }
            }
            for (RemisionesBodega remisionesBodegaListNewRemisionesBodega : remisionesBodegaListNew) {
                if (!remisionesBodegaListOld.contains(remisionesBodegaListNewRemisionesBodega)) {
                    PacienteBodega oldPacienteBodegaOfRemisionesBodegaListNewRemisionesBodega = remisionesBodegaListNewRemisionesBodega.getPacienteBodega();
                    remisionesBodegaListNewRemisionesBodega.setPacienteBodega(pacienteBodega);
                    remisionesBodegaListNewRemisionesBodega = em.merge(remisionesBodegaListNewRemisionesBodega);
                    if (oldPacienteBodegaOfRemisionesBodegaListNewRemisionesBodega != null && !oldPacienteBodegaOfRemisionesBodegaListNewRemisionesBodega.equals(pacienteBodega)) {
                        oldPacienteBodegaOfRemisionesBodegaListNewRemisionesBodega.getRemisionesBodegaList().remove(remisionesBodegaListNewRemisionesBodega);
                        oldPacienteBodegaOfRemisionesBodegaListNewRemisionesBodega = em.merge(oldPacienteBodegaOfRemisionesBodegaListNewRemisionesBodega);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = pacienteBodega.getPacienteKey();
                if (findPacienteBodega(id) == null) {
                    throw new NonexistentEntityException("The pacienteBodega with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PacienteBodega pacienteBodega;
            try {
                pacienteBodega = em.getReference(PacienteBodega.class, id);
                pacienteBodega.getPacienteKey();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pacienteBodega with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<CitasBodega> citasBodegaListOrphanCheck = pacienteBodega.getCitasBodegaList();
            for (CitasBodega citasBodegaListOrphanCheckCitasBodega : citasBodegaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This PacienteBodega (" + pacienteBodega + ") cannot be destroyed since the CitasBodega " + citasBodegaListOrphanCheckCitasBodega + " in its citasBodegaList field has a non-nullable pacienteBodega field.");
            }
            List<FormulasBodega> formulasBodegaListOrphanCheck = pacienteBodega.getFormulasBodegaList();
            for (FormulasBodega formulasBodegaListOrphanCheckFormulasBodega : formulasBodegaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This PacienteBodega (" + pacienteBodega + ") cannot be destroyed since the FormulasBodega " + formulasBodegaListOrphanCheckFormulasBodega + " in its formulasBodegaList field has a non-nullable pacienteBodega field.");
            }
            List<PagosBodega> pagosBodegaListOrphanCheck = pacienteBodega.getPagosBodegaList();
            for (PagosBodega pagosBodegaListOrphanCheckPagosBodega : pagosBodegaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This PacienteBodega (" + pacienteBodega + ") cannot be destroyed since the PagosBodega " + pagosBodegaListOrphanCheckPagosBodega + " in its pagosBodegaList field has a non-nullable pacienteBodega field.");
            }
            List<AfiliacionesBodega> afiliacionesBodegaListOrphanCheck = pacienteBodega.getAfiliacionesBodegaList();
            for (AfiliacionesBodega afiliacionesBodegaListOrphanCheckAfiliacionesBodega : afiliacionesBodegaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This PacienteBodega (" + pacienteBodega + ") cannot be destroyed since the AfiliacionesBodega " + afiliacionesBodegaListOrphanCheckAfiliacionesBodega + " in its afiliacionesBodegaList field has a non-nullable pacienteBodega field.");
            }
            List<RetirosBodega> retirosBodegaListOrphanCheck = pacienteBodega.getRetirosBodegaList();
            for (RetirosBodega retirosBodegaListOrphanCheckRetirosBodega : retirosBodegaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This PacienteBodega (" + pacienteBodega + ") cannot be destroyed since the RetirosBodega " + retirosBodegaListOrphanCheckRetirosBodega + " in its retirosBodegaList field has a non-nullable pacienteBodega field.");
            }
            List<UrgenciasBodega> urgenciasBodegaListOrphanCheck = pacienteBodega.getUrgenciasBodegaList();
            for (UrgenciasBodega urgenciasBodegaListOrphanCheckUrgenciasBodega : urgenciasBodegaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This PacienteBodega (" + pacienteBodega + ") cannot be destroyed since the UrgenciasBodega " + urgenciasBodegaListOrphanCheckUrgenciasBodega + " in its urgenciasBodegaList field has a non-nullable pacienteBodega field.");
            }
            List<RemisionesBodega> remisionesBodegaListOrphanCheck = pacienteBodega.getRemisionesBodegaList();
            for (RemisionesBodega remisionesBodegaListOrphanCheckRemisionesBodega : remisionesBodegaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This PacienteBodega (" + pacienteBodega + ") cannot be destroyed since the RemisionesBodega " + remisionesBodegaListOrphanCheckRemisionesBodega + " in its remisionesBodegaList field has a non-nullable pacienteBodega field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(pacienteBodega);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PacienteBodega> findPacienteBodegaEntities() {
        return findPacienteBodegaEntities(true, -1, -1);
    }

    public List<PacienteBodega> findPacienteBodegaEntities(int maxResults, int firstResult) {
        return findPacienteBodegaEntities(false, maxResults, firstResult);
    }

    private List<PacienteBodega> findPacienteBodegaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PacienteBodega.class));
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

    public PacienteBodega findPacienteBodega(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PacienteBodega.class, id);
        } finally {
            em.close();
        }
    }

    public int getPacienteBodegaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PacienteBodega> rt = cq.from(PacienteBodega.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
