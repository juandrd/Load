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
import Entidades_Bodega.DemografiaPacienteBodega;
import java.util.ArrayList;
import java.util.List;
import Entidades_Bodega.FormulasBodega;
import Entidades_Bodega.PagosBodega;
import Entidades_Bodega.RetirosBodega;
import Entidades_Bodega.UrgenciasBodega;
import Entidades_Bodega.RemisionesBodega;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author USER
 */
public class DemografiaPacienteBodegaJpaController implements Serializable {

    public DemografiaPacienteBodegaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(DemografiaPacienteBodega demografiaPacienteBodega) throws PreexistingEntityException, Exception {
        if (demografiaPacienteBodega.getCitasBodegaList() == null) {
            demografiaPacienteBodega.setCitasBodegaList(new ArrayList<CitasBodega>());
        }
        if (demografiaPacienteBodega.getFormulasBodegaList() == null) {
            demografiaPacienteBodega.setFormulasBodegaList(new ArrayList<FormulasBodega>());
        }
        if (demografiaPacienteBodega.getPagosBodegaList() == null) {
            demografiaPacienteBodega.setPagosBodegaList(new ArrayList<PagosBodega>());
        }
        if (demografiaPacienteBodega.getRetirosBodegaList() == null) {
            demografiaPacienteBodega.setRetirosBodegaList(new ArrayList<RetirosBodega>());
        }
        if (demografiaPacienteBodega.getUrgenciasBodegaList() == null) {
            demografiaPacienteBodega.setUrgenciasBodegaList(new ArrayList<UrgenciasBodega>());
        }
        if (demografiaPacienteBodega.getRemisionesBodegaList() == null) {
            demografiaPacienteBodega.setRemisionesBodegaList(new ArrayList<RemisionesBodega>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<CitasBodega> attachedCitasBodegaList = new ArrayList<CitasBodega>();
            for (CitasBodega citasBodegaListCitasBodegaToAttach : demografiaPacienteBodega.getCitasBodegaList()) {
                citasBodegaListCitasBodegaToAttach = em.getReference(citasBodegaListCitasBodegaToAttach.getClass(), citasBodegaListCitasBodegaToAttach.getCitasBodegaPK());
                attachedCitasBodegaList.add(citasBodegaListCitasBodegaToAttach);
            }
            demografiaPacienteBodega.setCitasBodegaList(attachedCitasBodegaList);
            List<FormulasBodega> attachedFormulasBodegaList = new ArrayList<FormulasBodega>();
            for (FormulasBodega formulasBodegaListFormulasBodegaToAttach : demografiaPacienteBodega.getFormulasBodegaList()) {
                formulasBodegaListFormulasBodegaToAttach = em.getReference(formulasBodegaListFormulasBodegaToAttach.getClass(), formulasBodegaListFormulasBodegaToAttach.getFormulasBodegaPK());
                attachedFormulasBodegaList.add(formulasBodegaListFormulasBodegaToAttach);
            }
            demografiaPacienteBodega.setFormulasBodegaList(attachedFormulasBodegaList);
            List<PagosBodega> attachedPagosBodegaList = new ArrayList<PagosBodega>();
            for (PagosBodega pagosBodegaListPagosBodegaToAttach : demografiaPacienteBodega.getPagosBodegaList()) {
                pagosBodegaListPagosBodegaToAttach = em.getReference(pagosBodegaListPagosBodegaToAttach.getClass(), pagosBodegaListPagosBodegaToAttach.getPagosBodegaPK());
                attachedPagosBodegaList.add(pagosBodegaListPagosBodegaToAttach);
            }
            demografiaPacienteBodega.setPagosBodegaList(attachedPagosBodegaList);
            List<RetirosBodega> attachedRetirosBodegaList = new ArrayList<RetirosBodega>();
            for (RetirosBodega retirosBodegaListRetirosBodegaToAttach : demografiaPacienteBodega.getRetirosBodegaList()) {
                retirosBodegaListRetirosBodegaToAttach = em.getReference(retirosBodegaListRetirosBodegaToAttach.getClass(), retirosBodegaListRetirosBodegaToAttach.getRetirosBodegaPK());
                attachedRetirosBodegaList.add(retirosBodegaListRetirosBodegaToAttach);
            }
            demografiaPacienteBodega.setRetirosBodegaList(attachedRetirosBodegaList);
            List<UrgenciasBodega> attachedUrgenciasBodegaList = new ArrayList<UrgenciasBodega>();
            for (UrgenciasBodega urgenciasBodegaListUrgenciasBodegaToAttach : demografiaPacienteBodega.getUrgenciasBodegaList()) {
                urgenciasBodegaListUrgenciasBodegaToAttach = em.getReference(urgenciasBodegaListUrgenciasBodegaToAttach.getClass(), urgenciasBodegaListUrgenciasBodegaToAttach.getUrgenciasBodegaPK());
                attachedUrgenciasBodegaList.add(urgenciasBodegaListUrgenciasBodegaToAttach);
            }
            demografiaPacienteBodega.setUrgenciasBodegaList(attachedUrgenciasBodegaList);
            List<RemisionesBodega> attachedRemisionesBodegaList = new ArrayList<RemisionesBodega>();
            for (RemisionesBodega remisionesBodegaListRemisionesBodegaToAttach : demografiaPacienteBodega.getRemisionesBodegaList()) {
                remisionesBodegaListRemisionesBodegaToAttach = em.getReference(remisionesBodegaListRemisionesBodegaToAttach.getClass(), remisionesBodegaListRemisionesBodegaToAttach.getRemisionesBodegaPK());
                attachedRemisionesBodegaList.add(remisionesBodegaListRemisionesBodegaToAttach);
            }
            demografiaPacienteBodega.setRemisionesBodegaList(attachedRemisionesBodegaList);
            em.persist(demografiaPacienteBodega);
            for (CitasBodega citasBodegaListCitasBodega : demografiaPacienteBodega.getCitasBodegaList()) {
                DemografiaPacienteBodega oldDemografiaPacienteBodegaOfCitasBodegaListCitasBodega = citasBodegaListCitasBodega.getDemografiaPacienteBodega();
                citasBodegaListCitasBodega.setDemografiaPacienteBodega(demografiaPacienteBodega);
                citasBodegaListCitasBodega = em.merge(citasBodegaListCitasBodega);
                if (oldDemografiaPacienteBodegaOfCitasBodegaListCitasBodega != null) {
                    oldDemografiaPacienteBodegaOfCitasBodegaListCitasBodega.getCitasBodegaList().remove(citasBodegaListCitasBodega);
                    oldDemografiaPacienteBodegaOfCitasBodegaListCitasBodega = em.merge(oldDemografiaPacienteBodegaOfCitasBodegaListCitasBodega);
                }
            }
            for (FormulasBodega formulasBodegaListFormulasBodega : demografiaPacienteBodega.getFormulasBodegaList()) {
                DemografiaPacienteBodega oldDemografiaPacienteBodegaOfFormulasBodegaListFormulasBodega = formulasBodegaListFormulasBodega.getDemografiaPacienteBodega();
                formulasBodegaListFormulasBodega.setDemografiaPacienteBodega(demografiaPacienteBodega);
                formulasBodegaListFormulasBodega = em.merge(formulasBodegaListFormulasBodega);
                if (oldDemografiaPacienteBodegaOfFormulasBodegaListFormulasBodega != null) {
                    oldDemografiaPacienteBodegaOfFormulasBodegaListFormulasBodega.getFormulasBodegaList().remove(formulasBodegaListFormulasBodega);
                    oldDemografiaPacienteBodegaOfFormulasBodegaListFormulasBodega = em.merge(oldDemografiaPacienteBodegaOfFormulasBodegaListFormulasBodega);
                }
            }
            for (PagosBodega pagosBodegaListPagosBodega : demografiaPacienteBodega.getPagosBodegaList()) {
                DemografiaPacienteBodega oldDemografiaPacienteBodegaOfPagosBodegaListPagosBodega = pagosBodegaListPagosBodega.getDemografiaPacienteBodega();
                pagosBodegaListPagosBodega.setDemografiaPacienteBodega(demografiaPacienteBodega);
                pagosBodegaListPagosBodega = em.merge(pagosBodegaListPagosBodega);
                if (oldDemografiaPacienteBodegaOfPagosBodegaListPagosBodega != null) {
                    oldDemografiaPacienteBodegaOfPagosBodegaListPagosBodega.getPagosBodegaList().remove(pagosBodegaListPagosBodega);
                    oldDemografiaPacienteBodegaOfPagosBodegaListPagosBodega = em.merge(oldDemografiaPacienteBodegaOfPagosBodegaListPagosBodega);
                }
            }
            for (RetirosBodega retirosBodegaListRetirosBodega : demografiaPacienteBodega.getRetirosBodegaList()) {
                DemografiaPacienteBodega oldDemografiaPacienteBodegaOfRetirosBodegaListRetirosBodega = retirosBodegaListRetirosBodega.getDemografiaPacienteBodega();
                retirosBodegaListRetirosBodega.setDemografiaPacienteBodega(demografiaPacienteBodega);
                retirosBodegaListRetirosBodega = em.merge(retirosBodegaListRetirosBodega);
                if (oldDemografiaPacienteBodegaOfRetirosBodegaListRetirosBodega != null) {
                    oldDemografiaPacienteBodegaOfRetirosBodegaListRetirosBodega.getRetirosBodegaList().remove(retirosBodegaListRetirosBodega);
                    oldDemografiaPacienteBodegaOfRetirosBodegaListRetirosBodega = em.merge(oldDemografiaPacienteBodegaOfRetirosBodegaListRetirosBodega);
                }
            }
            for (UrgenciasBodega urgenciasBodegaListUrgenciasBodega : demografiaPacienteBodega.getUrgenciasBodegaList()) {
                DemografiaPacienteBodega oldDemografiaPacienteBodegaOfUrgenciasBodegaListUrgenciasBodega = urgenciasBodegaListUrgenciasBodega.getDemografiaPacienteBodega();
                urgenciasBodegaListUrgenciasBodega.setDemografiaPacienteBodega(demografiaPacienteBodega);
                urgenciasBodegaListUrgenciasBodega = em.merge(urgenciasBodegaListUrgenciasBodega);
                if (oldDemografiaPacienteBodegaOfUrgenciasBodegaListUrgenciasBodega != null) {
                    oldDemografiaPacienteBodegaOfUrgenciasBodegaListUrgenciasBodega.getUrgenciasBodegaList().remove(urgenciasBodegaListUrgenciasBodega);
                    oldDemografiaPacienteBodegaOfUrgenciasBodegaListUrgenciasBodega = em.merge(oldDemografiaPacienteBodegaOfUrgenciasBodegaListUrgenciasBodega);
                }
            }
            for (RemisionesBodega remisionesBodegaListRemisionesBodega : demografiaPacienteBodega.getRemisionesBodegaList()) {
                DemografiaPacienteBodega oldDemografiaPacienteBodegaOfRemisionesBodegaListRemisionesBodega = remisionesBodegaListRemisionesBodega.getDemografiaPacienteBodega();
                remisionesBodegaListRemisionesBodega.setDemografiaPacienteBodega(demografiaPacienteBodega);
                remisionesBodegaListRemisionesBodega = em.merge(remisionesBodegaListRemisionesBodega);
                if (oldDemografiaPacienteBodegaOfRemisionesBodegaListRemisionesBodega != null) {
                    oldDemografiaPacienteBodegaOfRemisionesBodegaListRemisionesBodega.getRemisionesBodegaList().remove(remisionesBodegaListRemisionesBodega);
                    oldDemografiaPacienteBodegaOfRemisionesBodegaListRemisionesBodega = em.merge(oldDemografiaPacienteBodegaOfRemisionesBodegaListRemisionesBodega);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDemografiaPacienteBodega(demografiaPacienteBodega.getDemografiaPacienteKey()) != null) {
                throw new PreexistingEntityException("DemografiaPacienteBodega " + demografiaPacienteBodega + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DemografiaPacienteBodega demografiaPacienteBodega) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DemografiaPacienteBodega persistentDemografiaPacienteBodega = em.find(DemografiaPacienteBodega.class, demografiaPacienteBodega.getDemografiaPacienteKey());
            List<CitasBodega> citasBodegaListOld = persistentDemografiaPacienteBodega.getCitasBodegaList();
            List<CitasBodega> citasBodegaListNew = demografiaPacienteBodega.getCitasBodegaList();
            List<FormulasBodega> formulasBodegaListOld = persistentDemografiaPacienteBodega.getFormulasBodegaList();
            List<FormulasBodega> formulasBodegaListNew = demografiaPacienteBodega.getFormulasBodegaList();
            List<PagosBodega> pagosBodegaListOld = persistentDemografiaPacienteBodega.getPagosBodegaList();
            List<PagosBodega> pagosBodegaListNew = demografiaPacienteBodega.getPagosBodegaList();
            List<RetirosBodega> retirosBodegaListOld = persistentDemografiaPacienteBodega.getRetirosBodegaList();
            List<RetirosBodega> retirosBodegaListNew = demografiaPacienteBodega.getRetirosBodegaList();
            List<UrgenciasBodega> urgenciasBodegaListOld = persistentDemografiaPacienteBodega.getUrgenciasBodegaList();
            List<UrgenciasBodega> urgenciasBodegaListNew = demografiaPacienteBodega.getUrgenciasBodegaList();
            List<RemisionesBodega> remisionesBodegaListOld = persistentDemografiaPacienteBodega.getRemisionesBodegaList();
            List<RemisionesBodega> remisionesBodegaListNew = demografiaPacienteBodega.getRemisionesBodegaList();
            List<String> illegalOrphanMessages = null;
            for (CitasBodega citasBodegaListOldCitasBodega : citasBodegaListOld) {
                if (!citasBodegaListNew.contains(citasBodegaListOldCitasBodega)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain CitasBodega " + citasBodegaListOldCitasBodega + " since its demografiaPacienteBodega field is not nullable.");
                }
            }
            for (FormulasBodega formulasBodegaListOldFormulasBodega : formulasBodegaListOld) {
                if (!formulasBodegaListNew.contains(formulasBodegaListOldFormulasBodega)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain FormulasBodega " + formulasBodegaListOldFormulasBodega + " since its demografiaPacienteBodega field is not nullable.");
                }
            }
            for (PagosBodega pagosBodegaListOldPagosBodega : pagosBodegaListOld) {
                if (!pagosBodegaListNew.contains(pagosBodegaListOldPagosBodega)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain PagosBodega " + pagosBodegaListOldPagosBodega + " since its demografiaPacienteBodega field is not nullable.");
                }
            }
            for (RetirosBodega retirosBodegaListOldRetirosBodega : retirosBodegaListOld) {
                if (!retirosBodegaListNew.contains(retirosBodegaListOldRetirosBodega)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain RetirosBodega " + retirosBodegaListOldRetirosBodega + " since its demografiaPacienteBodega field is not nullable.");
                }
            }
            for (UrgenciasBodega urgenciasBodegaListOldUrgenciasBodega : urgenciasBodegaListOld) {
                if (!urgenciasBodegaListNew.contains(urgenciasBodegaListOldUrgenciasBodega)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain UrgenciasBodega " + urgenciasBodegaListOldUrgenciasBodega + " since its demografiaPacienteBodega field is not nullable.");
                }
            }
            for (RemisionesBodega remisionesBodegaListOldRemisionesBodega : remisionesBodegaListOld) {
                if (!remisionesBodegaListNew.contains(remisionesBodegaListOldRemisionesBodega)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain RemisionesBodega " + remisionesBodegaListOldRemisionesBodega + " since its demografiaPacienteBodega field is not nullable.");
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
            demografiaPacienteBodega.setCitasBodegaList(citasBodegaListNew);
            List<FormulasBodega> attachedFormulasBodegaListNew = new ArrayList<FormulasBodega>();
            for (FormulasBodega formulasBodegaListNewFormulasBodegaToAttach : formulasBodegaListNew) {
                formulasBodegaListNewFormulasBodegaToAttach = em.getReference(formulasBodegaListNewFormulasBodegaToAttach.getClass(), formulasBodegaListNewFormulasBodegaToAttach.getFormulasBodegaPK());
                attachedFormulasBodegaListNew.add(formulasBodegaListNewFormulasBodegaToAttach);
            }
            formulasBodegaListNew = attachedFormulasBodegaListNew;
            demografiaPacienteBodega.setFormulasBodegaList(formulasBodegaListNew);
            List<PagosBodega> attachedPagosBodegaListNew = new ArrayList<PagosBodega>();
            for (PagosBodega pagosBodegaListNewPagosBodegaToAttach : pagosBodegaListNew) {
                pagosBodegaListNewPagosBodegaToAttach = em.getReference(pagosBodegaListNewPagosBodegaToAttach.getClass(), pagosBodegaListNewPagosBodegaToAttach.getPagosBodegaPK());
                attachedPagosBodegaListNew.add(pagosBodegaListNewPagosBodegaToAttach);
            }
            pagosBodegaListNew = attachedPagosBodegaListNew;
            demografiaPacienteBodega.setPagosBodegaList(pagosBodegaListNew);
            List<RetirosBodega> attachedRetirosBodegaListNew = new ArrayList<RetirosBodega>();
            for (RetirosBodega retirosBodegaListNewRetirosBodegaToAttach : retirosBodegaListNew) {
                retirosBodegaListNewRetirosBodegaToAttach = em.getReference(retirosBodegaListNewRetirosBodegaToAttach.getClass(), retirosBodegaListNewRetirosBodegaToAttach.getRetirosBodegaPK());
                attachedRetirosBodegaListNew.add(retirosBodegaListNewRetirosBodegaToAttach);
            }
            retirosBodegaListNew = attachedRetirosBodegaListNew;
            demografiaPacienteBodega.setRetirosBodegaList(retirosBodegaListNew);
            List<UrgenciasBodega> attachedUrgenciasBodegaListNew = new ArrayList<UrgenciasBodega>();
            for (UrgenciasBodega urgenciasBodegaListNewUrgenciasBodegaToAttach : urgenciasBodegaListNew) {
                urgenciasBodegaListNewUrgenciasBodegaToAttach = em.getReference(urgenciasBodegaListNewUrgenciasBodegaToAttach.getClass(), urgenciasBodegaListNewUrgenciasBodegaToAttach.getUrgenciasBodegaPK());
                attachedUrgenciasBodegaListNew.add(urgenciasBodegaListNewUrgenciasBodegaToAttach);
            }
            urgenciasBodegaListNew = attachedUrgenciasBodegaListNew;
            demografiaPacienteBodega.setUrgenciasBodegaList(urgenciasBodegaListNew);
            List<RemisionesBodega> attachedRemisionesBodegaListNew = new ArrayList<RemisionesBodega>();
            for (RemisionesBodega remisionesBodegaListNewRemisionesBodegaToAttach : remisionesBodegaListNew) {
                remisionesBodegaListNewRemisionesBodegaToAttach = em.getReference(remisionesBodegaListNewRemisionesBodegaToAttach.getClass(), remisionesBodegaListNewRemisionesBodegaToAttach.getRemisionesBodegaPK());
                attachedRemisionesBodegaListNew.add(remisionesBodegaListNewRemisionesBodegaToAttach);
            }
            remisionesBodegaListNew = attachedRemisionesBodegaListNew;
            demografiaPacienteBodega.setRemisionesBodegaList(remisionesBodegaListNew);
            demografiaPacienteBodega = em.merge(demografiaPacienteBodega);
            for (CitasBodega citasBodegaListNewCitasBodega : citasBodegaListNew) {
                if (!citasBodegaListOld.contains(citasBodegaListNewCitasBodega)) {
                    DemografiaPacienteBodega oldDemografiaPacienteBodegaOfCitasBodegaListNewCitasBodega = citasBodegaListNewCitasBodega.getDemografiaPacienteBodega();
                    citasBodegaListNewCitasBodega.setDemografiaPacienteBodega(demografiaPacienteBodega);
                    citasBodegaListNewCitasBodega = em.merge(citasBodegaListNewCitasBodega);
                    if (oldDemografiaPacienteBodegaOfCitasBodegaListNewCitasBodega != null && !oldDemografiaPacienteBodegaOfCitasBodegaListNewCitasBodega.equals(demografiaPacienteBodega)) {
                        oldDemografiaPacienteBodegaOfCitasBodegaListNewCitasBodega.getCitasBodegaList().remove(citasBodegaListNewCitasBodega);
                        oldDemografiaPacienteBodegaOfCitasBodegaListNewCitasBodega = em.merge(oldDemografiaPacienteBodegaOfCitasBodegaListNewCitasBodega);
                    }
                }
            }
            for (FormulasBodega formulasBodegaListNewFormulasBodega : formulasBodegaListNew) {
                if (!formulasBodegaListOld.contains(formulasBodegaListNewFormulasBodega)) {
                    DemografiaPacienteBodega oldDemografiaPacienteBodegaOfFormulasBodegaListNewFormulasBodega = formulasBodegaListNewFormulasBodega.getDemografiaPacienteBodega();
                    formulasBodegaListNewFormulasBodega.setDemografiaPacienteBodega(demografiaPacienteBodega);
                    formulasBodegaListNewFormulasBodega = em.merge(formulasBodegaListNewFormulasBodega);
                    if (oldDemografiaPacienteBodegaOfFormulasBodegaListNewFormulasBodega != null && !oldDemografiaPacienteBodegaOfFormulasBodegaListNewFormulasBodega.equals(demografiaPacienteBodega)) {
                        oldDemografiaPacienteBodegaOfFormulasBodegaListNewFormulasBodega.getFormulasBodegaList().remove(formulasBodegaListNewFormulasBodega);
                        oldDemografiaPacienteBodegaOfFormulasBodegaListNewFormulasBodega = em.merge(oldDemografiaPacienteBodegaOfFormulasBodegaListNewFormulasBodega);
                    }
                }
            }
            for (PagosBodega pagosBodegaListNewPagosBodega : pagosBodegaListNew) {
                if (!pagosBodegaListOld.contains(pagosBodegaListNewPagosBodega)) {
                    DemografiaPacienteBodega oldDemografiaPacienteBodegaOfPagosBodegaListNewPagosBodega = pagosBodegaListNewPagosBodega.getDemografiaPacienteBodega();
                    pagosBodegaListNewPagosBodega.setDemografiaPacienteBodega(demografiaPacienteBodega);
                    pagosBodegaListNewPagosBodega = em.merge(pagosBodegaListNewPagosBodega);
                    if (oldDemografiaPacienteBodegaOfPagosBodegaListNewPagosBodega != null && !oldDemografiaPacienteBodegaOfPagosBodegaListNewPagosBodega.equals(demografiaPacienteBodega)) {
                        oldDemografiaPacienteBodegaOfPagosBodegaListNewPagosBodega.getPagosBodegaList().remove(pagosBodegaListNewPagosBodega);
                        oldDemografiaPacienteBodegaOfPagosBodegaListNewPagosBodega = em.merge(oldDemografiaPacienteBodegaOfPagosBodegaListNewPagosBodega);
                    }
                }
            }
            for (RetirosBodega retirosBodegaListNewRetirosBodega : retirosBodegaListNew) {
                if (!retirosBodegaListOld.contains(retirosBodegaListNewRetirosBodega)) {
                    DemografiaPacienteBodega oldDemografiaPacienteBodegaOfRetirosBodegaListNewRetirosBodega = retirosBodegaListNewRetirosBodega.getDemografiaPacienteBodega();
                    retirosBodegaListNewRetirosBodega.setDemografiaPacienteBodega(demografiaPacienteBodega);
                    retirosBodegaListNewRetirosBodega = em.merge(retirosBodegaListNewRetirosBodega);
                    if (oldDemografiaPacienteBodegaOfRetirosBodegaListNewRetirosBodega != null && !oldDemografiaPacienteBodegaOfRetirosBodegaListNewRetirosBodega.equals(demografiaPacienteBodega)) {
                        oldDemografiaPacienteBodegaOfRetirosBodegaListNewRetirosBodega.getRetirosBodegaList().remove(retirosBodegaListNewRetirosBodega);
                        oldDemografiaPacienteBodegaOfRetirosBodegaListNewRetirosBodega = em.merge(oldDemografiaPacienteBodegaOfRetirosBodegaListNewRetirosBodega);
                    }
                }
            }
            for (UrgenciasBodega urgenciasBodegaListNewUrgenciasBodega : urgenciasBodegaListNew) {
                if (!urgenciasBodegaListOld.contains(urgenciasBodegaListNewUrgenciasBodega)) {
                    DemografiaPacienteBodega oldDemografiaPacienteBodegaOfUrgenciasBodegaListNewUrgenciasBodega = urgenciasBodegaListNewUrgenciasBodega.getDemografiaPacienteBodega();
                    urgenciasBodegaListNewUrgenciasBodega.setDemografiaPacienteBodega(demografiaPacienteBodega);
                    urgenciasBodegaListNewUrgenciasBodega = em.merge(urgenciasBodegaListNewUrgenciasBodega);
                    if (oldDemografiaPacienteBodegaOfUrgenciasBodegaListNewUrgenciasBodega != null && !oldDemografiaPacienteBodegaOfUrgenciasBodegaListNewUrgenciasBodega.equals(demografiaPacienteBodega)) {
                        oldDemografiaPacienteBodegaOfUrgenciasBodegaListNewUrgenciasBodega.getUrgenciasBodegaList().remove(urgenciasBodegaListNewUrgenciasBodega);
                        oldDemografiaPacienteBodegaOfUrgenciasBodegaListNewUrgenciasBodega = em.merge(oldDemografiaPacienteBodegaOfUrgenciasBodegaListNewUrgenciasBodega);
                    }
                }
            }
            for (RemisionesBodega remisionesBodegaListNewRemisionesBodega : remisionesBodegaListNew) {
                if (!remisionesBodegaListOld.contains(remisionesBodegaListNewRemisionesBodega)) {
                    DemografiaPacienteBodega oldDemografiaPacienteBodegaOfRemisionesBodegaListNewRemisionesBodega = remisionesBodegaListNewRemisionesBodega.getDemografiaPacienteBodega();
                    remisionesBodegaListNewRemisionesBodega.setDemografiaPacienteBodega(demografiaPacienteBodega);
                    remisionesBodegaListNewRemisionesBodega = em.merge(remisionesBodegaListNewRemisionesBodega);
                    if (oldDemografiaPacienteBodegaOfRemisionesBodegaListNewRemisionesBodega != null && !oldDemografiaPacienteBodegaOfRemisionesBodegaListNewRemisionesBodega.equals(demografiaPacienteBodega)) {
                        oldDemografiaPacienteBodegaOfRemisionesBodegaListNewRemisionesBodega.getRemisionesBodegaList().remove(remisionesBodegaListNewRemisionesBodega);
                        oldDemografiaPacienteBodegaOfRemisionesBodegaListNewRemisionesBodega = em.merge(oldDemografiaPacienteBodegaOfRemisionesBodegaListNewRemisionesBodega);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = demografiaPacienteBodega.getDemografiaPacienteKey();
                if (findDemografiaPacienteBodega(id) == null) {
                    throw new NonexistentEntityException("The demografiaPacienteBodega with id " + id + " no longer exists.");
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
            DemografiaPacienteBodega demografiaPacienteBodega;
            try {
                demografiaPacienteBodega = em.getReference(DemografiaPacienteBodega.class, id);
                demografiaPacienteBodega.getDemografiaPacienteKey();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The demografiaPacienteBodega with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<CitasBodega> citasBodegaListOrphanCheck = demografiaPacienteBodega.getCitasBodegaList();
            for (CitasBodega citasBodegaListOrphanCheckCitasBodega : citasBodegaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This DemografiaPacienteBodega (" + demografiaPacienteBodega + ") cannot be destroyed since the CitasBodega " + citasBodegaListOrphanCheckCitasBodega + " in its citasBodegaList field has a non-nullable demografiaPacienteBodega field.");
            }
            List<FormulasBodega> formulasBodegaListOrphanCheck = demografiaPacienteBodega.getFormulasBodegaList();
            for (FormulasBodega formulasBodegaListOrphanCheckFormulasBodega : formulasBodegaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This DemografiaPacienteBodega (" + demografiaPacienteBodega + ") cannot be destroyed since the FormulasBodega " + formulasBodegaListOrphanCheckFormulasBodega + " in its formulasBodegaList field has a non-nullable demografiaPacienteBodega field.");
            }
            List<PagosBodega> pagosBodegaListOrphanCheck = demografiaPacienteBodega.getPagosBodegaList();
            for (PagosBodega pagosBodegaListOrphanCheckPagosBodega : pagosBodegaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This DemografiaPacienteBodega (" + demografiaPacienteBodega + ") cannot be destroyed since the PagosBodega " + pagosBodegaListOrphanCheckPagosBodega + " in its pagosBodegaList field has a non-nullable demografiaPacienteBodega field.");
            }
            List<RetirosBodega> retirosBodegaListOrphanCheck = demografiaPacienteBodega.getRetirosBodegaList();
            for (RetirosBodega retirosBodegaListOrphanCheckRetirosBodega : retirosBodegaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This DemografiaPacienteBodega (" + demografiaPacienteBodega + ") cannot be destroyed since the RetirosBodega " + retirosBodegaListOrphanCheckRetirosBodega + " in its retirosBodegaList field has a non-nullable demografiaPacienteBodega field.");
            }
            List<UrgenciasBodega> urgenciasBodegaListOrphanCheck = demografiaPacienteBodega.getUrgenciasBodegaList();
            for (UrgenciasBodega urgenciasBodegaListOrphanCheckUrgenciasBodega : urgenciasBodegaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This DemografiaPacienteBodega (" + demografiaPacienteBodega + ") cannot be destroyed since the UrgenciasBodega " + urgenciasBodegaListOrphanCheckUrgenciasBodega + " in its urgenciasBodegaList field has a non-nullable demografiaPacienteBodega field.");
            }
            List<RemisionesBodega> remisionesBodegaListOrphanCheck = demografiaPacienteBodega.getRemisionesBodegaList();
            for (RemisionesBodega remisionesBodegaListOrphanCheckRemisionesBodega : remisionesBodegaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This DemografiaPacienteBodega (" + demografiaPacienteBodega + ") cannot be destroyed since the RemisionesBodega " + remisionesBodegaListOrphanCheckRemisionesBodega + " in its remisionesBodegaList field has a non-nullable demografiaPacienteBodega field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(demografiaPacienteBodega);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<DemografiaPacienteBodega> findDemografiaPacienteBodegaEntities() {
        return findDemografiaPacienteBodegaEntities(true, -1, -1);
    }

    public List<DemografiaPacienteBodega> findDemografiaPacienteBodegaEntities(int maxResults, int firstResult) {
        return findDemografiaPacienteBodegaEntities(false, maxResults, firstResult);
    }

    private List<DemografiaPacienteBodega> findDemografiaPacienteBodegaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(DemografiaPacienteBodega.class));
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

    public DemografiaPacienteBodega findDemografiaPacienteBodega(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DemografiaPacienteBodega.class, id);
        } finally {
            em.close();
        }
    }

    public int getDemografiaPacienteBodegaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DemografiaPacienteBodega> rt = cq.from(DemografiaPacienteBodega.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
