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
import Entidades_Bodega.PagosBodega;
import Entidades_Bodega.PreexistenciaBodega;
import Entidades_Bodega.UrgenciasBodega;
import Entidades_Bodega.RemisionesBodega;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author USER
 */
public class PreexistenciaBodegaJpaController implements Serializable {

    public PreexistenciaBodegaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PreexistenciaBodega preexistenciaBodega) throws PreexistingEntityException, Exception {
        if (preexistenciaBodega.getCitasBodegaList() == null) {
            preexistenciaBodega.setCitasBodegaList(new ArrayList<CitasBodega>());
        }
        if (preexistenciaBodega.getPagosBodegaList() == null) {
            preexistenciaBodega.setPagosBodegaList(new ArrayList<PagosBodega>());
        }
        if (preexistenciaBodega.getUrgenciasBodegaList() == null) {
            preexistenciaBodega.setUrgenciasBodegaList(new ArrayList<UrgenciasBodega>());
        }
        if (preexistenciaBodega.getRemisionesBodegaList() == null) {
            preexistenciaBodega.setRemisionesBodegaList(new ArrayList<RemisionesBodega>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<CitasBodega> attachedCitasBodegaList = new ArrayList<CitasBodega>();
            for (CitasBodega citasBodegaListCitasBodegaToAttach : preexistenciaBodega.getCitasBodegaList()) {
                citasBodegaListCitasBodegaToAttach = em.getReference(citasBodegaListCitasBodegaToAttach.getClass(), citasBodegaListCitasBodegaToAttach.getCitasBodegaPK());
                attachedCitasBodegaList.add(citasBodegaListCitasBodegaToAttach);
            }
            preexistenciaBodega.setCitasBodegaList(attachedCitasBodegaList);
            List<PagosBodega> attachedPagosBodegaList = new ArrayList<PagosBodega>();
            for (PagosBodega pagosBodegaListPagosBodegaToAttach : preexistenciaBodega.getPagosBodegaList()) {
                pagosBodegaListPagosBodegaToAttach = em.getReference(pagosBodegaListPagosBodegaToAttach.getClass(), pagosBodegaListPagosBodegaToAttach.getPagosBodegaPK());
                attachedPagosBodegaList.add(pagosBodegaListPagosBodegaToAttach);
            }
            preexistenciaBodega.setPagosBodegaList(attachedPagosBodegaList);
            List<UrgenciasBodega> attachedUrgenciasBodegaList = new ArrayList<UrgenciasBodega>();
            for (UrgenciasBodega urgenciasBodegaListUrgenciasBodegaToAttach : preexistenciaBodega.getUrgenciasBodegaList()) {
                urgenciasBodegaListUrgenciasBodegaToAttach = em.getReference(urgenciasBodegaListUrgenciasBodegaToAttach.getClass(), urgenciasBodegaListUrgenciasBodegaToAttach.getUrgenciasBodegaPK());
                attachedUrgenciasBodegaList.add(urgenciasBodegaListUrgenciasBodegaToAttach);
            }
            preexistenciaBodega.setUrgenciasBodegaList(attachedUrgenciasBodegaList);
            List<RemisionesBodega> attachedRemisionesBodegaList = new ArrayList<RemisionesBodega>();
            for (RemisionesBodega remisionesBodegaListRemisionesBodegaToAttach : preexistenciaBodega.getRemisionesBodegaList()) {
                remisionesBodegaListRemisionesBodegaToAttach = em.getReference(remisionesBodegaListRemisionesBodegaToAttach.getClass(), remisionesBodegaListRemisionesBodegaToAttach.getRemisionesBodegaPK());
                attachedRemisionesBodegaList.add(remisionesBodegaListRemisionesBodegaToAttach);
            }
            preexistenciaBodega.setRemisionesBodegaList(attachedRemisionesBodegaList);
            em.persist(preexistenciaBodega);
            for (CitasBodega citasBodegaListCitasBodega : preexistenciaBodega.getCitasBodegaList()) {
                PreexistenciaBodega oldPreexistenciaBodegaOfCitasBodegaListCitasBodega = citasBodegaListCitasBodega.getPreexistenciaBodega();
                citasBodegaListCitasBodega.setPreexistenciaBodega(preexistenciaBodega);
                citasBodegaListCitasBodega = em.merge(citasBodegaListCitasBodega);
                if (oldPreexistenciaBodegaOfCitasBodegaListCitasBodega != null) {
                    oldPreexistenciaBodegaOfCitasBodegaListCitasBodega.getCitasBodegaList().remove(citasBodegaListCitasBodega);
                    oldPreexistenciaBodegaOfCitasBodegaListCitasBodega = em.merge(oldPreexistenciaBodegaOfCitasBodegaListCitasBodega);
                }
            }
            for (PagosBodega pagosBodegaListPagosBodega : preexistenciaBodega.getPagosBodegaList()) {
                PreexistenciaBodega oldPreexistenciaBodegaOfPagosBodegaListPagosBodega = pagosBodegaListPagosBodega.getPreexistenciaBodega();
                pagosBodegaListPagosBodega.setPreexistenciaBodega(preexistenciaBodega);
                pagosBodegaListPagosBodega = em.merge(pagosBodegaListPagosBodega);
                if (oldPreexistenciaBodegaOfPagosBodegaListPagosBodega != null) {
                    oldPreexistenciaBodegaOfPagosBodegaListPagosBodega.getPagosBodegaList().remove(pagosBodegaListPagosBodega);
                    oldPreexistenciaBodegaOfPagosBodegaListPagosBodega = em.merge(oldPreexistenciaBodegaOfPagosBodegaListPagosBodega);
                }
            }
            for (UrgenciasBodega urgenciasBodegaListUrgenciasBodega : preexistenciaBodega.getUrgenciasBodegaList()) {
                PreexistenciaBodega oldPreexistenciaBodegaOfUrgenciasBodegaListUrgenciasBodega = urgenciasBodegaListUrgenciasBodega.getPreexistenciaBodega();
                urgenciasBodegaListUrgenciasBodega.setPreexistenciaBodega(preexistenciaBodega);
                urgenciasBodegaListUrgenciasBodega = em.merge(urgenciasBodegaListUrgenciasBodega);
                if (oldPreexistenciaBodegaOfUrgenciasBodegaListUrgenciasBodega != null) {
                    oldPreexistenciaBodegaOfUrgenciasBodegaListUrgenciasBodega.getUrgenciasBodegaList().remove(urgenciasBodegaListUrgenciasBodega);
                    oldPreexistenciaBodegaOfUrgenciasBodegaListUrgenciasBodega = em.merge(oldPreexistenciaBodegaOfUrgenciasBodegaListUrgenciasBodega);
                }
            }
            for (RemisionesBodega remisionesBodegaListRemisionesBodega : preexistenciaBodega.getRemisionesBodegaList()) {
                PreexistenciaBodega oldPreexistenciaBodegaOfRemisionesBodegaListRemisionesBodega = remisionesBodegaListRemisionesBodega.getPreexistenciaBodega();
                remisionesBodegaListRemisionesBodega.setPreexistenciaBodega(preexistenciaBodega);
                remisionesBodegaListRemisionesBodega = em.merge(remisionesBodegaListRemisionesBodega);
                if (oldPreexistenciaBodegaOfRemisionesBodegaListRemisionesBodega != null) {
                    oldPreexistenciaBodegaOfRemisionesBodegaListRemisionesBodega.getRemisionesBodegaList().remove(remisionesBodegaListRemisionesBodega);
                    oldPreexistenciaBodegaOfRemisionesBodegaListRemisionesBodega = em.merge(oldPreexistenciaBodegaOfRemisionesBodegaListRemisionesBodega);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPreexistenciaBodega(preexistenciaBodega.getPreexistenciaKey()) != null) {
                throw new PreexistingEntityException("PreexistenciaBodega " + preexistenciaBodega + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PreexistenciaBodega preexistenciaBodega) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PreexistenciaBodega persistentPreexistenciaBodega = em.find(PreexistenciaBodega.class, preexistenciaBodega.getPreexistenciaKey());
            List<CitasBodega> citasBodegaListOld = persistentPreexistenciaBodega.getCitasBodegaList();
            List<CitasBodega> citasBodegaListNew = preexistenciaBodega.getCitasBodegaList();
            List<PagosBodega> pagosBodegaListOld = persistentPreexistenciaBodega.getPagosBodegaList();
            List<PagosBodega> pagosBodegaListNew = preexistenciaBodega.getPagosBodegaList();
            List<UrgenciasBodega> urgenciasBodegaListOld = persistentPreexistenciaBodega.getUrgenciasBodegaList();
            List<UrgenciasBodega> urgenciasBodegaListNew = preexistenciaBodega.getUrgenciasBodegaList();
            List<RemisionesBodega> remisionesBodegaListOld = persistentPreexistenciaBodega.getRemisionesBodegaList();
            List<RemisionesBodega> remisionesBodegaListNew = preexistenciaBodega.getRemisionesBodegaList();
            List<String> illegalOrphanMessages = null;
            for (CitasBodega citasBodegaListOldCitasBodega : citasBodegaListOld) {
                if (!citasBodegaListNew.contains(citasBodegaListOldCitasBodega)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain CitasBodega " + citasBodegaListOldCitasBodega + " since its preexistenciaBodega field is not nullable.");
                }
            }
            for (PagosBodega pagosBodegaListOldPagosBodega : pagosBodegaListOld) {
                if (!pagosBodegaListNew.contains(pagosBodegaListOldPagosBodega)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain PagosBodega " + pagosBodegaListOldPagosBodega + " since its preexistenciaBodega field is not nullable.");
                }
            }
            for (UrgenciasBodega urgenciasBodegaListOldUrgenciasBodega : urgenciasBodegaListOld) {
                if (!urgenciasBodegaListNew.contains(urgenciasBodegaListOldUrgenciasBodega)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain UrgenciasBodega " + urgenciasBodegaListOldUrgenciasBodega + " since its preexistenciaBodega field is not nullable.");
                }
            }
            for (RemisionesBodega remisionesBodegaListOldRemisionesBodega : remisionesBodegaListOld) {
                if (!remisionesBodegaListNew.contains(remisionesBodegaListOldRemisionesBodega)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain RemisionesBodega " + remisionesBodegaListOldRemisionesBodega + " since its preexistenciaBodega field is not nullable.");
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
            preexistenciaBodega.setCitasBodegaList(citasBodegaListNew);
            List<PagosBodega> attachedPagosBodegaListNew = new ArrayList<PagosBodega>();
            for (PagosBodega pagosBodegaListNewPagosBodegaToAttach : pagosBodegaListNew) {
                pagosBodegaListNewPagosBodegaToAttach = em.getReference(pagosBodegaListNewPagosBodegaToAttach.getClass(), pagosBodegaListNewPagosBodegaToAttach.getPagosBodegaPK());
                attachedPagosBodegaListNew.add(pagosBodegaListNewPagosBodegaToAttach);
            }
            pagosBodegaListNew = attachedPagosBodegaListNew;
            preexistenciaBodega.setPagosBodegaList(pagosBodegaListNew);
            List<UrgenciasBodega> attachedUrgenciasBodegaListNew = new ArrayList<UrgenciasBodega>();
            for (UrgenciasBodega urgenciasBodegaListNewUrgenciasBodegaToAttach : urgenciasBodegaListNew) {
                urgenciasBodegaListNewUrgenciasBodegaToAttach = em.getReference(urgenciasBodegaListNewUrgenciasBodegaToAttach.getClass(), urgenciasBodegaListNewUrgenciasBodegaToAttach.getUrgenciasBodegaPK());
                attachedUrgenciasBodegaListNew.add(urgenciasBodegaListNewUrgenciasBodegaToAttach);
            }
            urgenciasBodegaListNew = attachedUrgenciasBodegaListNew;
            preexistenciaBodega.setUrgenciasBodegaList(urgenciasBodegaListNew);
            List<RemisionesBodega> attachedRemisionesBodegaListNew = new ArrayList<RemisionesBodega>();
            for (RemisionesBodega remisionesBodegaListNewRemisionesBodegaToAttach : remisionesBodegaListNew) {
                remisionesBodegaListNewRemisionesBodegaToAttach = em.getReference(remisionesBodegaListNewRemisionesBodegaToAttach.getClass(), remisionesBodegaListNewRemisionesBodegaToAttach.getRemisionesBodegaPK());
                attachedRemisionesBodegaListNew.add(remisionesBodegaListNewRemisionesBodegaToAttach);
            }
            remisionesBodegaListNew = attachedRemisionesBodegaListNew;
            preexistenciaBodega.setRemisionesBodegaList(remisionesBodegaListNew);
            preexistenciaBodega = em.merge(preexistenciaBodega);
            for (CitasBodega citasBodegaListNewCitasBodega : citasBodegaListNew) {
                if (!citasBodegaListOld.contains(citasBodegaListNewCitasBodega)) {
                    PreexistenciaBodega oldPreexistenciaBodegaOfCitasBodegaListNewCitasBodega = citasBodegaListNewCitasBodega.getPreexistenciaBodega();
                    citasBodegaListNewCitasBodega.setPreexistenciaBodega(preexistenciaBodega);
                    citasBodegaListNewCitasBodega = em.merge(citasBodegaListNewCitasBodega);
                    if (oldPreexistenciaBodegaOfCitasBodegaListNewCitasBodega != null && !oldPreexistenciaBodegaOfCitasBodegaListNewCitasBodega.equals(preexistenciaBodega)) {
                        oldPreexistenciaBodegaOfCitasBodegaListNewCitasBodega.getCitasBodegaList().remove(citasBodegaListNewCitasBodega);
                        oldPreexistenciaBodegaOfCitasBodegaListNewCitasBodega = em.merge(oldPreexistenciaBodegaOfCitasBodegaListNewCitasBodega);
                    }
                }
            }
            for (PagosBodega pagosBodegaListNewPagosBodega : pagosBodegaListNew) {
                if (!pagosBodegaListOld.contains(pagosBodegaListNewPagosBodega)) {
                    PreexistenciaBodega oldPreexistenciaBodegaOfPagosBodegaListNewPagosBodega = pagosBodegaListNewPagosBodega.getPreexistenciaBodega();
                    pagosBodegaListNewPagosBodega.setPreexistenciaBodega(preexistenciaBodega);
                    pagosBodegaListNewPagosBodega = em.merge(pagosBodegaListNewPagosBodega);
                    if (oldPreexistenciaBodegaOfPagosBodegaListNewPagosBodega != null && !oldPreexistenciaBodegaOfPagosBodegaListNewPagosBodega.equals(preexistenciaBodega)) {
                        oldPreexistenciaBodegaOfPagosBodegaListNewPagosBodega.getPagosBodegaList().remove(pagosBodegaListNewPagosBodega);
                        oldPreexistenciaBodegaOfPagosBodegaListNewPagosBodega = em.merge(oldPreexistenciaBodegaOfPagosBodegaListNewPagosBodega);
                    }
                }
            }
            for (UrgenciasBodega urgenciasBodegaListNewUrgenciasBodega : urgenciasBodegaListNew) {
                if (!urgenciasBodegaListOld.contains(urgenciasBodegaListNewUrgenciasBodega)) {
                    PreexistenciaBodega oldPreexistenciaBodegaOfUrgenciasBodegaListNewUrgenciasBodega = urgenciasBodegaListNewUrgenciasBodega.getPreexistenciaBodega();
                    urgenciasBodegaListNewUrgenciasBodega.setPreexistenciaBodega(preexistenciaBodega);
                    urgenciasBodegaListNewUrgenciasBodega = em.merge(urgenciasBodegaListNewUrgenciasBodega);
                    if (oldPreexistenciaBodegaOfUrgenciasBodegaListNewUrgenciasBodega != null && !oldPreexistenciaBodegaOfUrgenciasBodegaListNewUrgenciasBodega.equals(preexistenciaBodega)) {
                        oldPreexistenciaBodegaOfUrgenciasBodegaListNewUrgenciasBodega.getUrgenciasBodegaList().remove(urgenciasBodegaListNewUrgenciasBodega);
                        oldPreexistenciaBodegaOfUrgenciasBodegaListNewUrgenciasBodega = em.merge(oldPreexistenciaBodegaOfUrgenciasBodegaListNewUrgenciasBodega);
                    }
                }
            }
            for (RemisionesBodega remisionesBodegaListNewRemisionesBodega : remisionesBodegaListNew) {
                if (!remisionesBodegaListOld.contains(remisionesBodegaListNewRemisionesBodega)) {
                    PreexistenciaBodega oldPreexistenciaBodegaOfRemisionesBodegaListNewRemisionesBodega = remisionesBodegaListNewRemisionesBodega.getPreexistenciaBodega();
                    remisionesBodegaListNewRemisionesBodega.setPreexistenciaBodega(preexistenciaBodega);
                    remisionesBodegaListNewRemisionesBodega = em.merge(remisionesBodegaListNewRemisionesBodega);
                    if (oldPreexistenciaBodegaOfRemisionesBodegaListNewRemisionesBodega != null && !oldPreexistenciaBodegaOfRemisionesBodegaListNewRemisionesBodega.equals(preexistenciaBodega)) {
                        oldPreexistenciaBodegaOfRemisionesBodegaListNewRemisionesBodega.getRemisionesBodegaList().remove(remisionesBodegaListNewRemisionesBodega);
                        oldPreexistenciaBodegaOfRemisionesBodegaListNewRemisionesBodega = em.merge(oldPreexistenciaBodegaOfRemisionesBodegaListNewRemisionesBodega);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = preexistenciaBodega.getPreexistenciaKey();
                if (findPreexistenciaBodega(id) == null) {
                    throw new NonexistentEntityException("The preexistenciaBodega with id " + id + " no longer exists.");
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
            PreexistenciaBodega preexistenciaBodega;
            try {
                preexistenciaBodega = em.getReference(PreexistenciaBodega.class, id);
                preexistenciaBodega.getPreexistenciaKey();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The preexistenciaBodega with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<CitasBodega> citasBodegaListOrphanCheck = preexistenciaBodega.getCitasBodegaList();
            for (CitasBodega citasBodegaListOrphanCheckCitasBodega : citasBodegaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This PreexistenciaBodega (" + preexistenciaBodega + ") cannot be destroyed since the CitasBodega " + citasBodegaListOrphanCheckCitasBodega + " in its citasBodegaList field has a non-nullable preexistenciaBodega field.");
            }
            List<PagosBodega> pagosBodegaListOrphanCheck = preexistenciaBodega.getPagosBodegaList();
            for (PagosBodega pagosBodegaListOrphanCheckPagosBodega : pagosBodegaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This PreexistenciaBodega (" + preexistenciaBodega + ") cannot be destroyed since the PagosBodega " + pagosBodegaListOrphanCheckPagosBodega + " in its pagosBodegaList field has a non-nullable preexistenciaBodega field.");
            }
            List<UrgenciasBodega> urgenciasBodegaListOrphanCheck = preexistenciaBodega.getUrgenciasBodegaList();
            for (UrgenciasBodega urgenciasBodegaListOrphanCheckUrgenciasBodega : urgenciasBodegaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This PreexistenciaBodega (" + preexistenciaBodega + ") cannot be destroyed since the UrgenciasBodega " + urgenciasBodegaListOrphanCheckUrgenciasBodega + " in its urgenciasBodegaList field has a non-nullable preexistenciaBodega field.");
            }
            List<RemisionesBodega> remisionesBodegaListOrphanCheck = preexistenciaBodega.getRemisionesBodegaList();
            for (RemisionesBodega remisionesBodegaListOrphanCheckRemisionesBodega : remisionesBodegaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This PreexistenciaBodega (" + preexistenciaBodega + ") cannot be destroyed since the RemisionesBodega " + remisionesBodegaListOrphanCheckRemisionesBodega + " in its remisionesBodegaList field has a non-nullable preexistenciaBodega field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(preexistenciaBodega);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PreexistenciaBodega> findPreexistenciaBodegaEntities() {
        return findPreexistenciaBodegaEntities(true, -1, -1);
    }

    public List<PreexistenciaBodega> findPreexistenciaBodegaEntities(int maxResults, int firstResult) {
        return findPreexistenciaBodegaEntities(false, maxResults, firstResult);
    }

    private List<PreexistenciaBodega> findPreexistenciaBodegaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PreexistenciaBodega.class));
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

    public PreexistenciaBodega findPreexistenciaBodega(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PreexistenciaBodega.class, id);
        } finally {
            em.close();
        }
    }

    public int getPreexistenciaBodegaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PreexistenciaBodega> rt = cq.from(PreexistenciaBodega.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
     public PreexistenciaBodega consultar(String f) {          
          
         PreexistenciaBodega preexistencia=new PreexistenciaBodega();
         List lista;
         lista=findPreexistenciaBodegaEntities();
         
          for (int i = 0; i < lista.size(); i++) {
              PreexistenciaBodega emp=(PreexistenciaBodega) lista.get(i);             
              
             
              if(emp.getIdPaciente().toString().equalsIgnoreCase(f)){                  
                  preexistencia=emp;
                 // System.out.println(preexistencia);
                  break;
              }
          }

        return preexistencia;
    }
    
}
