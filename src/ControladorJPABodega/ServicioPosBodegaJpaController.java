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
import Entidades_Bodega.UrgenciasBodega;
import Entidades_Bodega.RemisionesBodega;
import Entidades_Bodega.ServicioPosBodega;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author USER
 */
public class ServicioPosBodegaJpaController implements Serializable {

    public ServicioPosBodegaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ServicioPosBodega servicioPosBodega) throws PreexistingEntityException, Exception {
        if (servicioPosBodega.getCitasBodegaList() == null) {
            servicioPosBodega.setCitasBodegaList(new ArrayList<CitasBodega>());
        }
        if (servicioPosBodega.getUrgenciasBodegaList() == null) {
            servicioPosBodega.setUrgenciasBodegaList(new ArrayList<UrgenciasBodega>());
        }
        if (servicioPosBodega.getRemisionesBodegaList() == null) {
            servicioPosBodega.setRemisionesBodegaList(new ArrayList<RemisionesBodega>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<CitasBodega> attachedCitasBodegaList = new ArrayList<CitasBodega>();
            for (CitasBodega citasBodegaListCitasBodegaToAttach : servicioPosBodega.getCitasBodegaList()) {
                citasBodegaListCitasBodegaToAttach = em.getReference(citasBodegaListCitasBodegaToAttach.getClass(), citasBodegaListCitasBodegaToAttach.getCitasBodegaPK());
                attachedCitasBodegaList.add(citasBodegaListCitasBodegaToAttach);
            }
            servicioPosBodega.setCitasBodegaList(attachedCitasBodegaList);
            List<UrgenciasBodega> attachedUrgenciasBodegaList = new ArrayList<UrgenciasBodega>();
            for (UrgenciasBodega urgenciasBodegaListUrgenciasBodegaToAttach : servicioPosBodega.getUrgenciasBodegaList()) {
                urgenciasBodegaListUrgenciasBodegaToAttach = em.getReference(urgenciasBodegaListUrgenciasBodegaToAttach.getClass(), urgenciasBodegaListUrgenciasBodegaToAttach.getUrgenciasBodegaPK());
                attachedUrgenciasBodegaList.add(urgenciasBodegaListUrgenciasBodegaToAttach);
            }
            servicioPosBodega.setUrgenciasBodegaList(attachedUrgenciasBodegaList);
            List<RemisionesBodega> attachedRemisionesBodegaList = new ArrayList<RemisionesBodega>();
            for (RemisionesBodega remisionesBodegaListRemisionesBodegaToAttach : servicioPosBodega.getRemisionesBodegaList()) {
                remisionesBodegaListRemisionesBodegaToAttach = em.getReference(remisionesBodegaListRemisionesBodegaToAttach.getClass(), remisionesBodegaListRemisionesBodegaToAttach.getRemisionesBodegaPK());
                attachedRemisionesBodegaList.add(remisionesBodegaListRemisionesBodegaToAttach);
            }
            servicioPosBodega.setRemisionesBodegaList(attachedRemisionesBodegaList);
            em.persist(servicioPosBodega);
            for (CitasBodega citasBodegaListCitasBodega : servicioPosBodega.getCitasBodegaList()) {
                ServicioPosBodega oldServicioPosBodegaOfCitasBodegaListCitasBodega = citasBodegaListCitasBodega.getServicioPosBodega();
                citasBodegaListCitasBodega.setServicioPosBodega(servicioPosBodega);
                citasBodegaListCitasBodega = em.merge(citasBodegaListCitasBodega);
                if (oldServicioPosBodegaOfCitasBodegaListCitasBodega != null) {
                    oldServicioPosBodegaOfCitasBodegaListCitasBodega.getCitasBodegaList().remove(citasBodegaListCitasBodega);
                    oldServicioPosBodegaOfCitasBodegaListCitasBodega = em.merge(oldServicioPosBodegaOfCitasBodegaListCitasBodega);
                }
            }
            for (UrgenciasBodega urgenciasBodegaListUrgenciasBodega : servicioPosBodega.getUrgenciasBodegaList()) {
                ServicioPosBodega oldServicioPosKeyOfUrgenciasBodegaListUrgenciasBodega = urgenciasBodegaListUrgenciasBodega.getServicioPosKey();
                urgenciasBodegaListUrgenciasBodega.setServicioPosKey(servicioPosBodega);
                urgenciasBodegaListUrgenciasBodega = em.merge(urgenciasBodegaListUrgenciasBodega);
                if (oldServicioPosKeyOfUrgenciasBodegaListUrgenciasBodega != null) {
                    oldServicioPosKeyOfUrgenciasBodegaListUrgenciasBodega.getUrgenciasBodegaList().remove(urgenciasBodegaListUrgenciasBodega);
                    oldServicioPosKeyOfUrgenciasBodegaListUrgenciasBodega = em.merge(oldServicioPosKeyOfUrgenciasBodegaListUrgenciasBodega);
                }
            }
            for (RemisionesBodega remisionesBodegaListRemisionesBodega : servicioPosBodega.getRemisionesBodegaList()) {
                ServicioPosBodega oldServicioPosBodegaOfRemisionesBodegaListRemisionesBodega = remisionesBodegaListRemisionesBodega.getServicioPosBodega();
                remisionesBodegaListRemisionesBodega.setServicioPosBodega(servicioPosBodega);
                remisionesBodegaListRemisionesBodega = em.merge(remisionesBodegaListRemisionesBodega);
                if (oldServicioPosBodegaOfRemisionesBodegaListRemisionesBodega != null) {
                    oldServicioPosBodegaOfRemisionesBodegaListRemisionesBodega.getRemisionesBodegaList().remove(remisionesBodegaListRemisionesBodega);
                    oldServicioPosBodegaOfRemisionesBodegaListRemisionesBodega = em.merge(oldServicioPosBodegaOfRemisionesBodegaListRemisionesBodega);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findServicioPosBodega(servicioPosBodega.getServicioPosKey()) != null) {
                throw new PreexistingEntityException("ServicioPosBodega " + servicioPosBodega + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ServicioPosBodega servicioPosBodega) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ServicioPosBodega persistentServicioPosBodega = em.find(ServicioPosBodega.class, servicioPosBodega.getServicioPosKey());
            List<CitasBodega> citasBodegaListOld = persistentServicioPosBodega.getCitasBodegaList();
            List<CitasBodega> citasBodegaListNew = servicioPosBodega.getCitasBodegaList();
            List<UrgenciasBodega> urgenciasBodegaListOld = persistentServicioPosBodega.getUrgenciasBodegaList();
            List<UrgenciasBodega> urgenciasBodegaListNew = servicioPosBodega.getUrgenciasBodegaList();
            List<RemisionesBodega> remisionesBodegaListOld = persistentServicioPosBodega.getRemisionesBodegaList();
            List<RemisionesBodega> remisionesBodegaListNew = servicioPosBodega.getRemisionesBodegaList();
            List<String> illegalOrphanMessages = null;
            for (CitasBodega citasBodegaListOldCitasBodega : citasBodegaListOld) {
                if (!citasBodegaListNew.contains(citasBodegaListOldCitasBodega)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain CitasBodega " + citasBodegaListOldCitasBodega + " since its servicioPosBodega field is not nullable.");
                }
            }
            for (RemisionesBodega remisionesBodegaListOldRemisionesBodega : remisionesBodegaListOld) {
                if (!remisionesBodegaListNew.contains(remisionesBodegaListOldRemisionesBodega)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain RemisionesBodega " + remisionesBodegaListOldRemisionesBodega + " since its servicioPosBodega field is not nullable.");
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
            servicioPosBodega.setCitasBodegaList(citasBodegaListNew);
            List<UrgenciasBodega> attachedUrgenciasBodegaListNew = new ArrayList<UrgenciasBodega>();
            for (UrgenciasBodega urgenciasBodegaListNewUrgenciasBodegaToAttach : urgenciasBodegaListNew) {
                urgenciasBodegaListNewUrgenciasBodegaToAttach = em.getReference(urgenciasBodegaListNewUrgenciasBodegaToAttach.getClass(), urgenciasBodegaListNewUrgenciasBodegaToAttach.getUrgenciasBodegaPK());
                attachedUrgenciasBodegaListNew.add(urgenciasBodegaListNewUrgenciasBodegaToAttach);
            }
            urgenciasBodegaListNew = attachedUrgenciasBodegaListNew;
            servicioPosBodega.setUrgenciasBodegaList(urgenciasBodegaListNew);
            List<RemisionesBodega> attachedRemisionesBodegaListNew = new ArrayList<RemisionesBodega>();
            for (RemisionesBodega remisionesBodegaListNewRemisionesBodegaToAttach : remisionesBodegaListNew) {
                remisionesBodegaListNewRemisionesBodegaToAttach = em.getReference(remisionesBodegaListNewRemisionesBodegaToAttach.getClass(), remisionesBodegaListNewRemisionesBodegaToAttach.getRemisionesBodegaPK());
                attachedRemisionesBodegaListNew.add(remisionesBodegaListNewRemisionesBodegaToAttach);
            }
            remisionesBodegaListNew = attachedRemisionesBodegaListNew;
            servicioPosBodega.setRemisionesBodegaList(remisionesBodegaListNew);
            servicioPosBodega = em.merge(servicioPosBodega);
            for (CitasBodega citasBodegaListNewCitasBodega : citasBodegaListNew) {
                if (!citasBodegaListOld.contains(citasBodegaListNewCitasBodega)) {
                    ServicioPosBodega oldServicioPosBodegaOfCitasBodegaListNewCitasBodega = citasBodegaListNewCitasBodega.getServicioPosBodega();
                    citasBodegaListNewCitasBodega.setServicioPosBodega(servicioPosBodega);
                    citasBodegaListNewCitasBodega = em.merge(citasBodegaListNewCitasBodega);
                    if (oldServicioPosBodegaOfCitasBodegaListNewCitasBodega != null && !oldServicioPosBodegaOfCitasBodegaListNewCitasBodega.equals(servicioPosBodega)) {
                        oldServicioPosBodegaOfCitasBodegaListNewCitasBodega.getCitasBodegaList().remove(citasBodegaListNewCitasBodega);
                        oldServicioPosBodegaOfCitasBodegaListNewCitasBodega = em.merge(oldServicioPosBodegaOfCitasBodegaListNewCitasBodega);
                    }
                }
            }
            for (UrgenciasBodega urgenciasBodegaListOldUrgenciasBodega : urgenciasBodegaListOld) {
                if (!urgenciasBodegaListNew.contains(urgenciasBodegaListOldUrgenciasBodega)) {
                    urgenciasBodegaListOldUrgenciasBodega.setServicioPosKey(null);
                    urgenciasBodegaListOldUrgenciasBodega = em.merge(urgenciasBodegaListOldUrgenciasBodega);
                }
            }
            for (UrgenciasBodega urgenciasBodegaListNewUrgenciasBodega : urgenciasBodegaListNew) {
                if (!urgenciasBodegaListOld.contains(urgenciasBodegaListNewUrgenciasBodega)) {
                    ServicioPosBodega oldServicioPosKeyOfUrgenciasBodegaListNewUrgenciasBodega = urgenciasBodegaListNewUrgenciasBodega.getServicioPosKey();
                    urgenciasBodegaListNewUrgenciasBodega.setServicioPosKey(servicioPosBodega);
                    urgenciasBodegaListNewUrgenciasBodega = em.merge(urgenciasBodegaListNewUrgenciasBodega);
                    if (oldServicioPosKeyOfUrgenciasBodegaListNewUrgenciasBodega != null && !oldServicioPosKeyOfUrgenciasBodegaListNewUrgenciasBodega.equals(servicioPosBodega)) {
                        oldServicioPosKeyOfUrgenciasBodegaListNewUrgenciasBodega.getUrgenciasBodegaList().remove(urgenciasBodegaListNewUrgenciasBodega);
                        oldServicioPosKeyOfUrgenciasBodegaListNewUrgenciasBodega = em.merge(oldServicioPosKeyOfUrgenciasBodegaListNewUrgenciasBodega);
                    }
                }
            }
            for (RemisionesBodega remisionesBodegaListNewRemisionesBodega : remisionesBodegaListNew) {
                if (!remisionesBodegaListOld.contains(remisionesBodegaListNewRemisionesBodega)) {
                    ServicioPosBodega oldServicioPosBodegaOfRemisionesBodegaListNewRemisionesBodega = remisionesBodegaListNewRemisionesBodega.getServicioPosBodega();
                    remisionesBodegaListNewRemisionesBodega.setServicioPosBodega(servicioPosBodega);
                    remisionesBodegaListNewRemisionesBodega = em.merge(remisionesBodegaListNewRemisionesBodega);
                    if (oldServicioPosBodegaOfRemisionesBodegaListNewRemisionesBodega != null && !oldServicioPosBodegaOfRemisionesBodegaListNewRemisionesBodega.equals(servicioPosBodega)) {
                        oldServicioPosBodegaOfRemisionesBodegaListNewRemisionesBodega.getRemisionesBodegaList().remove(remisionesBodegaListNewRemisionesBodega);
                        oldServicioPosBodegaOfRemisionesBodegaListNewRemisionesBodega = em.merge(oldServicioPosBodegaOfRemisionesBodegaListNewRemisionesBodega);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = servicioPosBodega.getServicioPosKey();
                if (findServicioPosBodega(id) == null) {
                    throw new NonexistentEntityException("The servicioPosBodega with id " + id + " no longer exists.");
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
            ServicioPosBodega servicioPosBodega;
            try {
                servicioPosBodega = em.getReference(ServicioPosBodega.class, id);
                servicioPosBodega.getServicioPosKey();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The servicioPosBodega with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<CitasBodega> citasBodegaListOrphanCheck = servicioPosBodega.getCitasBodegaList();
            for (CitasBodega citasBodegaListOrphanCheckCitasBodega : citasBodegaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This ServicioPosBodega (" + servicioPosBodega + ") cannot be destroyed since the CitasBodega " + citasBodegaListOrphanCheckCitasBodega + " in its citasBodegaList field has a non-nullable servicioPosBodega field.");
            }
            List<RemisionesBodega> remisionesBodegaListOrphanCheck = servicioPosBodega.getRemisionesBodegaList();
            for (RemisionesBodega remisionesBodegaListOrphanCheckRemisionesBodega : remisionesBodegaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This ServicioPosBodega (" + servicioPosBodega + ") cannot be destroyed since the RemisionesBodega " + remisionesBodegaListOrphanCheckRemisionesBodega + " in its remisionesBodegaList field has a non-nullable servicioPosBodega field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<UrgenciasBodega> urgenciasBodegaList = servicioPosBodega.getUrgenciasBodegaList();
            for (UrgenciasBodega urgenciasBodegaListUrgenciasBodega : urgenciasBodegaList) {
                urgenciasBodegaListUrgenciasBodega.setServicioPosKey(null);
                urgenciasBodegaListUrgenciasBodega = em.merge(urgenciasBodegaListUrgenciasBodega);
            }
            em.remove(servicioPosBodega);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ServicioPosBodega> findServicioPosBodegaEntities() {
        return findServicioPosBodegaEntities(true, -1, -1);
    }

    public List<ServicioPosBodega> findServicioPosBodegaEntities(int maxResults, int firstResult) {
        return findServicioPosBodegaEntities(false, maxResults, firstResult);
    }

    private List<ServicioPosBodega> findServicioPosBodegaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ServicioPosBodega.class));
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

    public ServicioPosBodega findServicioPosBodega(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ServicioPosBodega.class, id);
        } finally {
            em.close();
        }
    }

    public int getServicioPosBodegaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ServicioPosBodega> rt = cq.from(ServicioPosBodega.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
