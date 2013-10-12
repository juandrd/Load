/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladorJPABodega;

import ControladorJPABodega.exceptions.NonexistentEntityException;
import ControladorJPABodega.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entidades_DB.Remisiones;
import Entidades_DB.ServiciosPos;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author USER
 */
public class ServiciosPosJpaController implements Serializable {

    public ServiciosPosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ServiciosPos serviciosPos) throws PreexistingEntityException, Exception {
        if (serviciosPos.getRemisionesList() == null) {
            serviciosPos.setRemisionesList(new ArrayList<Remisiones>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Remisiones> attachedRemisionesList = new ArrayList<Remisiones>();
            for (Remisiones remisionesListRemisionesToAttach : serviciosPos.getRemisionesList()) {
                remisionesListRemisionesToAttach = em.getReference(remisionesListRemisionesToAttach.getClass(), remisionesListRemisionesToAttach.getCodigoRemision());
                attachedRemisionesList.add(remisionesListRemisionesToAttach);
            }
            serviciosPos.setRemisionesList(attachedRemisionesList);
            em.persist(serviciosPos);
            for (Remisiones remisionesListRemisiones : serviciosPos.getRemisionesList()) {
                ServiciosPos oldServicioPOSOfRemisionesListRemisiones = remisionesListRemisiones.getServicioPOS();
                remisionesListRemisiones.setServicioPOS(serviciosPos);
                remisionesListRemisiones = em.merge(remisionesListRemisiones);
                if (oldServicioPOSOfRemisionesListRemisiones != null) {
                    oldServicioPOSOfRemisionesListRemisiones.getRemisionesList().remove(remisionesListRemisiones);
                    oldServicioPOSOfRemisionesListRemisiones = em.merge(oldServicioPOSOfRemisionesListRemisiones);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findServiciosPos(serviciosPos.getIDServicioPOS()) != null) {
                throw new PreexistingEntityException("ServiciosPos " + serviciosPos + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ServiciosPos serviciosPos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ServiciosPos persistentServiciosPos = em.find(ServiciosPos.class, serviciosPos.getIDServicioPOS());
            List<Remisiones> remisionesListOld = persistentServiciosPos.getRemisionesList();
            List<Remisiones> remisionesListNew = serviciosPos.getRemisionesList();
            List<Remisiones> attachedRemisionesListNew = new ArrayList<Remisiones>();
            for (Remisiones remisionesListNewRemisionesToAttach : remisionesListNew) {
                remisionesListNewRemisionesToAttach = em.getReference(remisionesListNewRemisionesToAttach.getClass(), remisionesListNewRemisionesToAttach.getCodigoRemision());
                attachedRemisionesListNew.add(remisionesListNewRemisionesToAttach);
            }
            remisionesListNew = attachedRemisionesListNew;
            serviciosPos.setRemisionesList(remisionesListNew);
            serviciosPos = em.merge(serviciosPos);
            for (Remisiones remisionesListOldRemisiones : remisionesListOld) {
                if (!remisionesListNew.contains(remisionesListOldRemisiones)) {
                    remisionesListOldRemisiones.setServicioPOS(null);
                    remisionesListOldRemisiones = em.merge(remisionesListOldRemisiones);
                }
            }
            for (Remisiones remisionesListNewRemisiones : remisionesListNew) {
                if (!remisionesListOld.contains(remisionesListNewRemisiones)) {
                    ServiciosPos oldServicioPOSOfRemisionesListNewRemisiones = remisionesListNewRemisiones.getServicioPOS();
                    remisionesListNewRemisiones.setServicioPOS(serviciosPos);
                    remisionesListNewRemisiones = em.merge(remisionesListNewRemisiones);
                    if (oldServicioPOSOfRemisionesListNewRemisiones != null && !oldServicioPOSOfRemisionesListNewRemisiones.equals(serviciosPos)) {
                        oldServicioPOSOfRemisionesListNewRemisiones.getRemisionesList().remove(remisionesListNewRemisiones);
                        oldServicioPOSOfRemisionesListNewRemisiones = em.merge(oldServicioPOSOfRemisionesListNewRemisiones);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = serviciosPos.getIDServicioPOS();
                if (findServiciosPos(id) == null) {
                    throw new NonexistentEntityException("The serviciosPos with id " + id + " no longer exists.");
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
            ServiciosPos serviciosPos;
            try {
                serviciosPos = em.getReference(ServiciosPos.class, id);
                serviciosPos.getIDServicioPOS();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The serviciosPos with id " + id + " no longer exists.", enfe);
            }
            List<Remisiones> remisionesList = serviciosPos.getRemisionesList();
            for (Remisiones remisionesListRemisiones : remisionesList) {
                remisionesListRemisiones.setServicioPOS(null);
                remisionesListRemisiones = em.merge(remisionesListRemisiones);
            }
            em.remove(serviciosPos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ServiciosPos> findServiciosPosEntities() {
        return findServiciosPosEntities(true, -1, -1);
    }

    public List<ServiciosPos> findServiciosPosEntities(int maxResults, int firstResult) {
        return findServiciosPosEntities(false, maxResults, firstResult);
    }

    private List<ServiciosPos> findServiciosPosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ServiciosPos.class));
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

    public ServiciosPos findServiciosPos(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ServiciosPos.class, id);
        } finally {
            em.close();
        }
    }

    public int getServiciosPosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ServiciosPos> rt = cq.from(ServiciosPos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
