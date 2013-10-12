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
import Entidades_Bodega.Dates;
import Entidades_Bodega.RetirosBodega;
import Entidades_Bodega.UrgenciasBodega;
import Entidades_Bodega.RemisionesBodega;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author USER
 */
public class DatesJpaController implements Serializable {

    public DatesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Dates dates) throws PreexistingEntityException, Exception {
        if (dates.getCitasBodegaList() == null) {
            dates.setCitasBodegaList(new ArrayList<CitasBodega>());
        }
        if (dates.getFormulasBodegaList() == null) {
            dates.setFormulasBodegaList(new ArrayList<FormulasBodega>());
        }
        if (dates.getPagosBodegaList() == null) {
            dates.setPagosBodegaList(new ArrayList<PagosBodega>());
        }
        if (dates.getAfiliacionesBodegaList() == null) {
            dates.setAfiliacionesBodegaList(new ArrayList<AfiliacionesBodega>());
        }
        if (dates.getRetirosBodegaList() == null) {
            dates.setRetirosBodegaList(new ArrayList<RetirosBodega>());
        }
        if (dates.getUrgenciasBodegaList() == null) {
            dates.setUrgenciasBodegaList(new ArrayList<UrgenciasBodega>());
        }
        if (dates.getRemisionesBodegaList() == null) {
            dates.setRemisionesBodegaList(new ArrayList<RemisionesBodega>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<CitasBodega> attachedCitasBodegaList = new ArrayList<CitasBodega>();
            for (CitasBodega citasBodegaListCitasBodegaToAttach : dates.getCitasBodegaList()) {
                citasBodegaListCitasBodegaToAttach = em.getReference(citasBodegaListCitasBodegaToAttach.getClass(), citasBodegaListCitasBodegaToAttach.getCitasBodegaPK());
                attachedCitasBodegaList.add(citasBodegaListCitasBodegaToAttach);
            }
            dates.setCitasBodegaList(attachedCitasBodegaList);
            List<FormulasBodega> attachedFormulasBodegaList = new ArrayList<FormulasBodega>();
            for (FormulasBodega formulasBodegaListFormulasBodegaToAttach : dates.getFormulasBodegaList()) {
                formulasBodegaListFormulasBodegaToAttach = em.getReference(formulasBodegaListFormulasBodegaToAttach.getClass(), formulasBodegaListFormulasBodegaToAttach.getFormulasBodegaPK());
                attachedFormulasBodegaList.add(formulasBodegaListFormulasBodegaToAttach);
            }
            dates.setFormulasBodegaList(attachedFormulasBodegaList);
            List<PagosBodega> attachedPagosBodegaList = new ArrayList<PagosBodega>();
            for (PagosBodega pagosBodegaListPagosBodegaToAttach : dates.getPagosBodegaList()) {
                pagosBodegaListPagosBodegaToAttach = em.getReference(pagosBodegaListPagosBodegaToAttach.getClass(), pagosBodegaListPagosBodegaToAttach.getPagosBodegaPK());
                attachedPagosBodegaList.add(pagosBodegaListPagosBodegaToAttach);
            }
            dates.setPagosBodegaList(attachedPagosBodegaList);
            List<AfiliacionesBodega> attachedAfiliacionesBodegaList = new ArrayList<AfiliacionesBodega>();
            for (AfiliacionesBodega afiliacionesBodegaListAfiliacionesBodegaToAttach : dates.getAfiliacionesBodegaList()) {
                afiliacionesBodegaListAfiliacionesBodegaToAttach = em.getReference(afiliacionesBodegaListAfiliacionesBodegaToAttach.getClass(), afiliacionesBodegaListAfiliacionesBodegaToAttach.getAfiliacionesBodegaPK());
                attachedAfiliacionesBodegaList.add(afiliacionesBodegaListAfiliacionesBodegaToAttach);
            }
            dates.setAfiliacionesBodegaList(attachedAfiliacionesBodegaList);
            List<RetirosBodega> attachedRetirosBodegaList = new ArrayList<RetirosBodega>();
            for (RetirosBodega retirosBodegaListRetirosBodegaToAttach : dates.getRetirosBodegaList()) {
                retirosBodegaListRetirosBodegaToAttach = em.getReference(retirosBodegaListRetirosBodegaToAttach.getClass(), retirosBodegaListRetirosBodegaToAttach.getRetirosBodegaPK());
                attachedRetirosBodegaList.add(retirosBodegaListRetirosBodegaToAttach);
            }
            dates.setRetirosBodegaList(attachedRetirosBodegaList);
            List<UrgenciasBodega> attachedUrgenciasBodegaList = new ArrayList<UrgenciasBodega>();
            for (UrgenciasBodega urgenciasBodegaListUrgenciasBodegaToAttach : dates.getUrgenciasBodegaList()) {
                urgenciasBodegaListUrgenciasBodegaToAttach = em.getReference(urgenciasBodegaListUrgenciasBodegaToAttach.getClass(), urgenciasBodegaListUrgenciasBodegaToAttach.getUrgenciasBodegaPK());
                attachedUrgenciasBodegaList.add(urgenciasBodegaListUrgenciasBodegaToAttach);
            }
            dates.setUrgenciasBodegaList(attachedUrgenciasBodegaList);
            List<RemisionesBodega> attachedRemisionesBodegaList = new ArrayList<RemisionesBodega>();
            for (RemisionesBodega remisionesBodegaListRemisionesBodegaToAttach : dates.getRemisionesBodegaList()) {
                remisionesBodegaListRemisionesBodegaToAttach = em.getReference(remisionesBodegaListRemisionesBodegaToAttach.getClass(), remisionesBodegaListRemisionesBodegaToAttach.getRemisionesBodegaPK());
                attachedRemisionesBodegaList.add(remisionesBodegaListRemisionesBodegaToAttach);
            }
            dates.setRemisionesBodegaList(attachedRemisionesBodegaList);
            em.persist(dates);
            for (CitasBodega citasBodegaListCitasBodega : dates.getCitasBodegaList()) {
                Dates oldDatesOfCitasBodegaListCitasBodega = citasBodegaListCitasBodega.getDates();
                citasBodegaListCitasBodega.setDates(dates);
                citasBodegaListCitasBodega = em.merge(citasBodegaListCitasBodega);
                if (oldDatesOfCitasBodegaListCitasBodega != null) {
                    oldDatesOfCitasBodegaListCitasBodega.getCitasBodegaList().remove(citasBodegaListCitasBodega);
                    oldDatesOfCitasBodegaListCitasBodega = em.merge(oldDatesOfCitasBodegaListCitasBodega);
                }
            }
            for (FormulasBodega formulasBodegaListFormulasBodega : dates.getFormulasBodegaList()) {
                Dates oldDatesOfFormulasBodegaListFormulasBodega = formulasBodegaListFormulasBodega.getDates();
                formulasBodegaListFormulasBodega.setDates(dates);
                formulasBodegaListFormulasBodega = em.merge(formulasBodegaListFormulasBodega);
                if (oldDatesOfFormulasBodegaListFormulasBodega != null) {
                    oldDatesOfFormulasBodegaListFormulasBodega.getFormulasBodegaList().remove(formulasBodegaListFormulasBodega);
                    oldDatesOfFormulasBodegaListFormulasBodega = em.merge(oldDatesOfFormulasBodegaListFormulasBodega);
                }
            }
            for (PagosBodega pagosBodegaListPagosBodega : dates.getPagosBodegaList()) {
                Dates oldDatesOfPagosBodegaListPagosBodega = pagosBodegaListPagosBodega.getDates();
                pagosBodegaListPagosBodega.setDates(dates);
                pagosBodegaListPagosBodega = em.merge(pagosBodegaListPagosBodega);
                if (oldDatesOfPagosBodegaListPagosBodega != null) {
                    oldDatesOfPagosBodegaListPagosBodega.getPagosBodegaList().remove(pagosBodegaListPagosBodega);
                    oldDatesOfPagosBodegaListPagosBodega = em.merge(oldDatesOfPagosBodegaListPagosBodega);
                }
            }
            for (AfiliacionesBodega afiliacionesBodegaListAfiliacionesBodega : dates.getAfiliacionesBodegaList()) {
                Dates oldDatesOfAfiliacionesBodegaListAfiliacionesBodega = afiliacionesBodegaListAfiliacionesBodega.getDates();
                afiliacionesBodegaListAfiliacionesBodega.setDates(dates);
                afiliacionesBodegaListAfiliacionesBodega = em.merge(afiliacionesBodegaListAfiliacionesBodega);
                if (oldDatesOfAfiliacionesBodegaListAfiliacionesBodega != null) {
                    oldDatesOfAfiliacionesBodegaListAfiliacionesBodega.getAfiliacionesBodegaList().remove(afiliacionesBodegaListAfiliacionesBodega);
                    oldDatesOfAfiliacionesBodegaListAfiliacionesBodega = em.merge(oldDatesOfAfiliacionesBodegaListAfiliacionesBodega);
                }
            }
            for (RetirosBodega retirosBodegaListRetirosBodega : dates.getRetirosBodegaList()) {
                Dates oldDatesOfRetirosBodegaListRetirosBodega = retirosBodegaListRetirosBodega.getDates();
                retirosBodegaListRetirosBodega.setDates(dates);
                retirosBodegaListRetirosBodega = em.merge(retirosBodegaListRetirosBodega);
                if (oldDatesOfRetirosBodegaListRetirosBodega != null) {
                    oldDatesOfRetirosBodegaListRetirosBodega.getRetirosBodegaList().remove(retirosBodegaListRetirosBodega);
                    oldDatesOfRetirosBodegaListRetirosBodega = em.merge(oldDatesOfRetirosBodegaListRetirosBodega);
                }
            }
            for (UrgenciasBodega urgenciasBodegaListUrgenciasBodega : dates.getUrgenciasBodegaList()) {
                Dates oldDatesOfUrgenciasBodegaListUrgenciasBodega = urgenciasBodegaListUrgenciasBodega.getDates();
                urgenciasBodegaListUrgenciasBodega.setDates(dates);
                urgenciasBodegaListUrgenciasBodega = em.merge(urgenciasBodegaListUrgenciasBodega);
                if (oldDatesOfUrgenciasBodegaListUrgenciasBodega != null) {
                    oldDatesOfUrgenciasBodegaListUrgenciasBodega.getUrgenciasBodegaList().remove(urgenciasBodegaListUrgenciasBodega);
                    oldDatesOfUrgenciasBodegaListUrgenciasBodega = em.merge(oldDatesOfUrgenciasBodegaListUrgenciasBodega);
                }
            }
            for (RemisionesBodega remisionesBodegaListRemisionesBodega : dates.getRemisionesBodegaList()) {
                Dates oldDatesOfRemisionesBodegaListRemisionesBodega = remisionesBodegaListRemisionesBodega.getDates();
                remisionesBodegaListRemisionesBodega.setDates(dates);
                remisionesBodegaListRemisionesBodega = em.merge(remisionesBodegaListRemisionesBodega);
                if (oldDatesOfRemisionesBodegaListRemisionesBodega != null) {
                    oldDatesOfRemisionesBodegaListRemisionesBodega.getRemisionesBodegaList().remove(remisionesBodegaListRemisionesBodega);
                    oldDatesOfRemisionesBodegaListRemisionesBodega = em.merge(oldDatesOfRemisionesBodegaListRemisionesBodega);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDates(dates.getDateId()) != null) {
                throw new PreexistingEntityException("Dates " + dates + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Dates dates) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Dates persistentDates = em.find(Dates.class, dates.getDateId());
            List<CitasBodega> citasBodegaListOld = persistentDates.getCitasBodegaList();
            List<CitasBodega> citasBodegaListNew = dates.getCitasBodegaList();
            List<FormulasBodega> formulasBodegaListOld = persistentDates.getFormulasBodegaList();
            List<FormulasBodega> formulasBodegaListNew = dates.getFormulasBodegaList();
            List<PagosBodega> pagosBodegaListOld = persistentDates.getPagosBodegaList();
            List<PagosBodega> pagosBodegaListNew = dates.getPagosBodegaList();
            List<AfiliacionesBodega> afiliacionesBodegaListOld = persistentDates.getAfiliacionesBodegaList();
            List<AfiliacionesBodega> afiliacionesBodegaListNew = dates.getAfiliacionesBodegaList();
            List<RetirosBodega> retirosBodegaListOld = persistentDates.getRetirosBodegaList();
            List<RetirosBodega> retirosBodegaListNew = dates.getRetirosBodegaList();
            List<UrgenciasBodega> urgenciasBodegaListOld = persistentDates.getUrgenciasBodegaList();
            List<UrgenciasBodega> urgenciasBodegaListNew = dates.getUrgenciasBodegaList();
            List<RemisionesBodega> remisionesBodegaListOld = persistentDates.getRemisionesBodegaList();
            List<RemisionesBodega> remisionesBodegaListNew = dates.getRemisionesBodegaList();
            List<String> illegalOrphanMessages = null;
            for (CitasBodega citasBodegaListOldCitasBodega : citasBodegaListOld) {
                if (!citasBodegaListNew.contains(citasBodegaListOldCitasBodega)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain CitasBodega " + citasBodegaListOldCitasBodega + " since its dates field is not nullable.");
                }
            }
            for (FormulasBodega formulasBodegaListOldFormulasBodega : formulasBodegaListOld) {
                if (!formulasBodegaListNew.contains(formulasBodegaListOldFormulasBodega)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain FormulasBodega " + formulasBodegaListOldFormulasBodega + " since its dates field is not nullable.");
                }
            }
            for (PagosBodega pagosBodegaListOldPagosBodega : pagosBodegaListOld) {
                if (!pagosBodegaListNew.contains(pagosBodegaListOldPagosBodega)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain PagosBodega " + pagosBodegaListOldPagosBodega + " since its dates field is not nullable.");
                }
            }
            for (AfiliacionesBodega afiliacionesBodegaListOldAfiliacionesBodega : afiliacionesBodegaListOld) {
                if (!afiliacionesBodegaListNew.contains(afiliacionesBodegaListOldAfiliacionesBodega)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain AfiliacionesBodega " + afiliacionesBodegaListOldAfiliacionesBodega + " since its dates field is not nullable.");
                }
            }
            for (RetirosBodega retirosBodegaListOldRetirosBodega : retirosBodegaListOld) {
                if (!retirosBodegaListNew.contains(retirosBodegaListOldRetirosBodega)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain RetirosBodega " + retirosBodegaListOldRetirosBodega + " since its dates field is not nullable.");
                }
            }
            for (UrgenciasBodega urgenciasBodegaListOldUrgenciasBodega : urgenciasBodegaListOld) {
                if (!urgenciasBodegaListNew.contains(urgenciasBodegaListOldUrgenciasBodega)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain UrgenciasBodega " + urgenciasBodegaListOldUrgenciasBodega + " since its dates field is not nullable.");
                }
            }
            for (RemisionesBodega remisionesBodegaListOldRemisionesBodega : remisionesBodegaListOld) {
                if (!remisionesBodegaListNew.contains(remisionesBodegaListOldRemisionesBodega)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain RemisionesBodega " + remisionesBodegaListOldRemisionesBodega + " since its dates field is not nullable.");
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
            dates.setCitasBodegaList(citasBodegaListNew);
            List<FormulasBodega> attachedFormulasBodegaListNew = new ArrayList<FormulasBodega>();
            for (FormulasBodega formulasBodegaListNewFormulasBodegaToAttach : formulasBodegaListNew) {
                formulasBodegaListNewFormulasBodegaToAttach = em.getReference(formulasBodegaListNewFormulasBodegaToAttach.getClass(), formulasBodegaListNewFormulasBodegaToAttach.getFormulasBodegaPK());
                attachedFormulasBodegaListNew.add(formulasBodegaListNewFormulasBodegaToAttach);
            }
            formulasBodegaListNew = attachedFormulasBodegaListNew;
            dates.setFormulasBodegaList(formulasBodegaListNew);
            List<PagosBodega> attachedPagosBodegaListNew = new ArrayList<PagosBodega>();
            for (PagosBodega pagosBodegaListNewPagosBodegaToAttach : pagosBodegaListNew) {
                pagosBodegaListNewPagosBodegaToAttach = em.getReference(pagosBodegaListNewPagosBodegaToAttach.getClass(), pagosBodegaListNewPagosBodegaToAttach.getPagosBodegaPK());
                attachedPagosBodegaListNew.add(pagosBodegaListNewPagosBodegaToAttach);
            }
            pagosBodegaListNew = attachedPagosBodegaListNew;
            dates.setPagosBodegaList(pagosBodegaListNew);
            List<AfiliacionesBodega> attachedAfiliacionesBodegaListNew = new ArrayList<AfiliacionesBodega>();
            for (AfiliacionesBodega afiliacionesBodegaListNewAfiliacionesBodegaToAttach : afiliacionesBodegaListNew) {
                afiliacionesBodegaListNewAfiliacionesBodegaToAttach = em.getReference(afiliacionesBodegaListNewAfiliacionesBodegaToAttach.getClass(), afiliacionesBodegaListNewAfiliacionesBodegaToAttach.getAfiliacionesBodegaPK());
                attachedAfiliacionesBodegaListNew.add(afiliacionesBodegaListNewAfiliacionesBodegaToAttach);
            }
            afiliacionesBodegaListNew = attachedAfiliacionesBodegaListNew;
            dates.setAfiliacionesBodegaList(afiliacionesBodegaListNew);
            List<RetirosBodega> attachedRetirosBodegaListNew = new ArrayList<RetirosBodega>();
            for (RetirosBodega retirosBodegaListNewRetirosBodegaToAttach : retirosBodegaListNew) {
                retirosBodegaListNewRetirosBodegaToAttach = em.getReference(retirosBodegaListNewRetirosBodegaToAttach.getClass(), retirosBodegaListNewRetirosBodegaToAttach.getRetirosBodegaPK());
                attachedRetirosBodegaListNew.add(retirosBodegaListNewRetirosBodegaToAttach);
            }
            retirosBodegaListNew = attachedRetirosBodegaListNew;
            dates.setRetirosBodegaList(retirosBodegaListNew);
            List<UrgenciasBodega> attachedUrgenciasBodegaListNew = new ArrayList<UrgenciasBodega>();
            for (UrgenciasBodega urgenciasBodegaListNewUrgenciasBodegaToAttach : urgenciasBodegaListNew) {
                urgenciasBodegaListNewUrgenciasBodegaToAttach = em.getReference(urgenciasBodegaListNewUrgenciasBodegaToAttach.getClass(), urgenciasBodegaListNewUrgenciasBodegaToAttach.getUrgenciasBodegaPK());
                attachedUrgenciasBodegaListNew.add(urgenciasBodegaListNewUrgenciasBodegaToAttach);
            }
            urgenciasBodegaListNew = attachedUrgenciasBodegaListNew;
            dates.setUrgenciasBodegaList(urgenciasBodegaListNew);
            List<RemisionesBodega> attachedRemisionesBodegaListNew = new ArrayList<RemisionesBodega>();
            for (RemisionesBodega remisionesBodegaListNewRemisionesBodegaToAttach : remisionesBodegaListNew) {
                remisionesBodegaListNewRemisionesBodegaToAttach = em.getReference(remisionesBodegaListNewRemisionesBodegaToAttach.getClass(), remisionesBodegaListNewRemisionesBodegaToAttach.getRemisionesBodegaPK());
                attachedRemisionesBodegaListNew.add(remisionesBodegaListNewRemisionesBodegaToAttach);
            }
            remisionesBodegaListNew = attachedRemisionesBodegaListNew;
            dates.setRemisionesBodegaList(remisionesBodegaListNew);
            dates = em.merge(dates);
            for (CitasBodega citasBodegaListNewCitasBodega : citasBodegaListNew) {
                if (!citasBodegaListOld.contains(citasBodegaListNewCitasBodega)) {
                    Dates oldDatesOfCitasBodegaListNewCitasBodega = citasBodegaListNewCitasBodega.getDates();
                    citasBodegaListNewCitasBodega.setDates(dates);
                    citasBodegaListNewCitasBodega = em.merge(citasBodegaListNewCitasBodega);
                    if (oldDatesOfCitasBodegaListNewCitasBodega != null && !oldDatesOfCitasBodegaListNewCitasBodega.equals(dates)) {
                        oldDatesOfCitasBodegaListNewCitasBodega.getCitasBodegaList().remove(citasBodegaListNewCitasBodega);
                        oldDatesOfCitasBodegaListNewCitasBodega = em.merge(oldDatesOfCitasBodegaListNewCitasBodega);
                    }
                }
            }
            for (FormulasBodega formulasBodegaListNewFormulasBodega : formulasBodegaListNew) {
                if (!formulasBodegaListOld.contains(formulasBodegaListNewFormulasBodega)) {
                    Dates oldDatesOfFormulasBodegaListNewFormulasBodega = formulasBodegaListNewFormulasBodega.getDates();
                    formulasBodegaListNewFormulasBodega.setDates(dates);
                    formulasBodegaListNewFormulasBodega = em.merge(formulasBodegaListNewFormulasBodega);
                    if (oldDatesOfFormulasBodegaListNewFormulasBodega != null && !oldDatesOfFormulasBodegaListNewFormulasBodega.equals(dates)) {
                        oldDatesOfFormulasBodegaListNewFormulasBodega.getFormulasBodegaList().remove(formulasBodegaListNewFormulasBodega);
                        oldDatesOfFormulasBodegaListNewFormulasBodega = em.merge(oldDatesOfFormulasBodegaListNewFormulasBodega);
                    }
                }
            }
            for (PagosBodega pagosBodegaListNewPagosBodega : pagosBodegaListNew) {
                if (!pagosBodegaListOld.contains(pagosBodegaListNewPagosBodega)) {
                    Dates oldDatesOfPagosBodegaListNewPagosBodega = pagosBodegaListNewPagosBodega.getDates();
                    pagosBodegaListNewPagosBodega.setDates(dates);
                    pagosBodegaListNewPagosBodega = em.merge(pagosBodegaListNewPagosBodega);
                    if (oldDatesOfPagosBodegaListNewPagosBodega != null && !oldDatesOfPagosBodegaListNewPagosBodega.equals(dates)) {
                        oldDatesOfPagosBodegaListNewPagosBodega.getPagosBodegaList().remove(pagosBodegaListNewPagosBodega);
                        oldDatesOfPagosBodegaListNewPagosBodega = em.merge(oldDatesOfPagosBodegaListNewPagosBodega);
                    }
                }
            }
            for (AfiliacionesBodega afiliacionesBodegaListNewAfiliacionesBodega : afiliacionesBodegaListNew) {
                if (!afiliacionesBodegaListOld.contains(afiliacionesBodegaListNewAfiliacionesBodega)) {
                    Dates oldDatesOfAfiliacionesBodegaListNewAfiliacionesBodega = afiliacionesBodegaListNewAfiliacionesBodega.getDates();
                    afiliacionesBodegaListNewAfiliacionesBodega.setDates(dates);
                    afiliacionesBodegaListNewAfiliacionesBodega = em.merge(afiliacionesBodegaListNewAfiliacionesBodega);
                    if (oldDatesOfAfiliacionesBodegaListNewAfiliacionesBodega != null && !oldDatesOfAfiliacionesBodegaListNewAfiliacionesBodega.equals(dates)) {
                        oldDatesOfAfiliacionesBodegaListNewAfiliacionesBodega.getAfiliacionesBodegaList().remove(afiliacionesBodegaListNewAfiliacionesBodega);
                        oldDatesOfAfiliacionesBodegaListNewAfiliacionesBodega = em.merge(oldDatesOfAfiliacionesBodegaListNewAfiliacionesBodega);
                    }
                }
            }
            for (RetirosBodega retirosBodegaListNewRetirosBodega : retirosBodegaListNew) {
                if (!retirosBodegaListOld.contains(retirosBodegaListNewRetirosBodega)) {
                    Dates oldDatesOfRetirosBodegaListNewRetirosBodega = retirosBodegaListNewRetirosBodega.getDates();
                    retirosBodegaListNewRetirosBodega.setDates(dates);
                    retirosBodegaListNewRetirosBodega = em.merge(retirosBodegaListNewRetirosBodega);
                    if (oldDatesOfRetirosBodegaListNewRetirosBodega != null && !oldDatesOfRetirosBodegaListNewRetirosBodega.equals(dates)) {
                        oldDatesOfRetirosBodegaListNewRetirosBodega.getRetirosBodegaList().remove(retirosBodegaListNewRetirosBodega);
                        oldDatesOfRetirosBodegaListNewRetirosBodega = em.merge(oldDatesOfRetirosBodegaListNewRetirosBodega);
                    }
                }
            }
            for (UrgenciasBodega urgenciasBodegaListNewUrgenciasBodega : urgenciasBodegaListNew) {
                if (!urgenciasBodegaListOld.contains(urgenciasBodegaListNewUrgenciasBodega)) {
                    Dates oldDatesOfUrgenciasBodegaListNewUrgenciasBodega = urgenciasBodegaListNewUrgenciasBodega.getDates();
                    urgenciasBodegaListNewUrgenciasBodega.setDates(dates);
                    urgenciasBodegaListNewUrgenciasBodega = em.merge(urgenciasBodegaListNewUrgenciasBodega);
                    if (oldDatesOfUrgenciasBodegaListNewUrgenciasBodega != null && !oldDatesOfUrgenciasBodegaListNewUrgenciasBodega.equals(dates)) {
                        oldDatesOfUrgenciasBodegaListNewUrgenciasBodega.getUrgenciasBodegaList().remove(urgenciasBodegaListNewUrgenciasBodega);
                        oldDatesOfUrgenciasBodegaListNewUrgenciasBodega = em.merge(oldDatesOfUrgenciasBodegaListNewUrgenciasBodega);
                    }
                }
            }
            for (RemisionesBodega remisionesBodegaListNewRemisionesBodega : remisionesBodegaListNew) {
                if (!remisionesBodegaListOld.contains(remisionesBodegaListNewRemisionesBodega)) {
                    Dates oldDatesOfRemisionesBodegaListNewRemisionesBodega = remisionesBodegaListNewRemisionesBodega.getDates();
                    remisionesBodegaListNewRemisionesBodega.setDates(dates);
                    remisionesBodegaListNewRemisionesBodega = em.merge(remisionesBodegaListNewRemisionesBodega);
                    if (oldDatesOfRemisionesBodegaListNewRemisionesBodega != null && !oldDatesOfRemisionesBodegaListNewRemisionesBodega.equals(dates)) {
                        oldDatesOfRemisionesBodegaListNewRemisionesBodega.getRemisionesBodegaList().remove(remisionesBodegaListNewRemisionesBodega);
                        oldDatesOfRemisionesBodegaListNewRemisionesBodega = em.merge(oldDatesOfRemisionesBodegaListNewRemisionesBodega);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = dates.getDateId();
                if (findDates(id) == null) {
                    throw new NonexistentEntityException("The dates with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Dates dates;
            try {
                dates = em.getReference(Dates.class, id);
                dates.getDateId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The dates with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<CitasBodega> citasBodegaListOrphanCheck = dates.getCitasBodegaList();
            for (CitasBodega citasBodegaListOrphanCheckCitasBodega : citasBodegaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Dates (" + dates + ") cannot be destroyed since the CitasBodega " + citasBodegaListOrphanCheckCitasBodega + " in its citasBodegaList field has a non-nullable dates field.");
            }
            List<FormulasBodega> formulasBodegaListOrphanCheck = dates.getFormulasBodegaList();
            for (FormulasBodega formulasBodegaListOrphanCheckFormulasBodega : formulasBodegaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Dates (" + dates + ") cannot be destroyed since the FormulasBodega " + formulasBodegaListOrphanCheckFormulasBodega + " in its formulasBodegaList field has a non-nullable dates field.");
            }
            List<PagosBodega> pagosBodegaListOrphanCheck = dates.getPagosBodegaList();
            for (PagosBodega pagosBodegaListOrphanCheckPagosBodega : pagosBodegaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Dates (" + dates + ") cannot be destroyed since the PagosBodega " + pagosBodegaListOrphanCheckPagosBodega + " in its pagosBodegaList field has a non-nullable dates field.");
            }
            List<AfiliacionesBodega> afiliacionesBodegaListOrphanCheck = dates.getAfiliacionesBodegaList();
            for (AfiliacionesBodega afiliacionesBodegaListOrphanCheckAfiliacionesBodega : afiliacionesBodegaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Dates (" + dates + ") cannot be destroyed since the AfiliacionesBodega " + afiliacionesBodegaListOrphanCheckAfiliacionesBodega + " in its afiliacionesBodegaList field has a non-nullable dates field.");
            }
            List<RetirosBodega> retirosBodegaListOrphanCheck = dates.getRetirosBodegaList();
            for (RetirosBodega retirosBodegaListOrphanCheckRetirosBodega : retirosBodegaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Dates (" + dates + ") cannot be destroyed since the RetirosBodega " + retirosBodegaListOrphanCheckRetirosBodega + " in its retirosBodegaList field has a non-nullable dates field.");
            }
            List<UrgenciasBodega> urgenciasBodegaListOrphanCheck = dates.getUrgenciasBodegaList();
            for (UrgenciasBodega urgenciasBodegaListOrphanCheckUrgenciasBodega : urgenciasBodegaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Dates (" + dates + ") cannot be destroyed since the UrgenciasBodega " + urgenciasBodegaListOrphanCheckUrgenciasBodega + " in its urgenciasBodegaList field has a non-nullable dates field.");
            }
            List<RemisionesBodega> remisionesBodegaListOrphanCheck = dates.getRemisionesBodegaList();
            for (RemisionesBodega remisionesBodegaListOrphanCheckRemisionesBodega : remisionesBodegaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Dates (" + dates + ") cannot be destroyed since the RemisionesBodega " + remisionesBodegaListOrphanCheckRemisionesBodega + " in its remisionesBodegaList field has a non-nullable dates field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(dates);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Dates> findDatesEntities() {
        return findDatesEntities(true, -1, -1);
    }

    public List<Dates> findDatesEntities(int maxResults, int firstResult) {
        return findDatesEntities(false, maxResults, firstResult);
    }

    private List<Dates> findDatesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Dates.class));
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

    public Dates findDates(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Dates.class, id);
        } finally {
            em.close();
        }
    }

    public int getDatesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Dates> rt = cq.from(Dates.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
