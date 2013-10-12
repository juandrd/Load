/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladorJPA;

import ControladorJPA.exceptions.NonexistentEntityException;
import ControladorJPA.exceptions.PreexistingEntityException;
import Entidades_DB.Preexistencias;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author USER
 */
public class PreexistenciasJpaController implements Serializable {

    public PreexistenciasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Preexistencias preexistencias) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(preexistencias);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPreexistencias(preexistencias.getIDUsuario()) != null) {
                throw new PreexistingEntityException("Preexistencias " + preexistencias + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Preexistencias preexistencias) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            preexistencias = em.merge(preexistencias);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = preexistencias.getIDUsuario();
                if (findPreexistencias(id) == null) {
                    throw new NonexistentEntityException("The preexistencias with id " + id + " no longer exists.");
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
            Preexistencias preexistencias;
            try {
                preexistencias = em.getReference(Preexistencias.class, id);
                preexistencias.getIDUsuario();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The preexistencias with id " + id + " no longer exists.", enfe);
            }
            em.remove(preexistencias);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Preexistencias> findPreexistenciasEntities() {
        return findPreexistenciasEntities(true, -1, -1);
    }

    public List<Preexistencias> findPreexistenciasEntities(int maxResults, int firstResult) {
        return findPreexistenciasEntities(false, maxResults, firstResult);
    }

    private List<Preexistencias> findPreexistenciasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Preexistencias.class));
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

    public Preexistencias findPreexistencias(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Preexistencias.class, id);
        } finally {
            em.close();
        }
    }

    public int getPreexistenciasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Preexistencias> rt = cq.from(Preexistencias.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
