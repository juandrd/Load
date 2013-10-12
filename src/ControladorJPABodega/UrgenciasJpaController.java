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
import Entidades_DB.Medico;
import Entidades_DB.Urgencias;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author USER
 */
public class UrgenciasJpaController implements Serializable {

    public UrgenciasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Urgencias urgencias) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Medico IDMedico = urgencias.getIDMedico();
            if (IDMedico != null) {
                IDMedico = em.getReference(IDMedico.getClass(), IDMedico.getCedula());
                urgencias.setIDMedico(IDMedico);
            }
            em.persist(urgencias);
            if (IDMedico != null) {
                IDMedico.getUrgenciasList().add(urgencias);
                IDMedico = em.merge(IDMedico);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findUrgencias(urgencias.getCodigoUrgencia()) != null) {
                throw new PreexistingEntityException("Urgencias " + urgencias + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Urgencias urgencias) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Urgencias persistentUrgencias = em.find(Urgencias.class, urgencias.getCodigoUrgencia());
            Medico IDMedicoOld = persistentUrgencias.getIDMedico();
            Medico IDMedicoNew = urgencias.getIDMedico();
            if (IDMedicoNew != null) {
                IDMedicoNew = em.getReference(IDMedicoNew.getClass(), IDMedicoNew.getCedula());
                urgencias.setIDMedico(IDMedicoNew);
            }
            urgencias = em.merge(urgencias);
            if (IDMedicoOld != null && !IDMedicoOld.equals(IDMedicoNew)) {
                IDMedicoOld.getUrgenciasList().remove(urgencias);
                IDMedicoOld = em.merge(IDMedicoOld);
            }
            if (IDMedicoNew != null && !IDMedicoNew.equals(IDMedicoOld)) {
                IDMedicoNew.getUrgenciasList().add(urgencias);
                IDMedicoNew = em.merge(IDMedicoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = urgencias.getCodigoUrgencia();
                if (findUrgencias(id) == null) {
                    throw new NonexistentEntityException("The urgencias with id " + id + " no longer exists.");
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
            Urgencias urgencias;
            try {
                urgencias = em.getReference(Urgencias.class, id);
                urgencias.getCodigoUrgencia();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The urgencias with id " + id + " no longer exists.", enfe);
            }
            Medico IDMedico = urgencias.getIDMedico();
            if (IDMedico != null) {
                IDMedico.getUrgenciasList().remove(urgencias);
                IDMedico = em.merge(IDMedico);
            }
            em.remove(urgencias);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Urgencias> findUrgenciasEntities() {
        return findUrgenciasEntities(true, -1, -1);
    }

    public List<Urgencias> findUrgenciasEntities(int maxResults, int firstResult) {
        return findUrgenciasEntities(false, maxResults, firstResult);
    }

    private List<Urgencias> findUrgenciasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Urgencias.class));
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

    public Urgencias findUrgencias(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Urgencias.class, id);
        } finally {
            em.close();
        }
    }

    public int getUrgenciasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Urgencias> rt = cq.from(Urgencias.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
