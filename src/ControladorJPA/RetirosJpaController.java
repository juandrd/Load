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
import Entidades_DB.Cotizante;
import Entidades_DB.Retiros;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author USER
 */
public class RetirosJpaController implements Serializable {

    public RetirosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Retiros retiros) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cotizante IDUsuario = retiros.getIDUsuario();
            if (IDUsuario != null) {
                IDUsuario = em.getReference(IDUsuario.getClass(), IDUsuario.getCedula());
                retiros.setIDUsuario(IDUsuario);
            }
            em.persist(retiros);
            if (IDUsuario != null) {
                IDUsuario.getRetirosList().add(retiros);
                IDUsuario = em.merge(IDUsuario);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findRetiros(retiros.getIDRetiro()) != null) {
                throw new PreexistingEntityException("Retiros " + retiros + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Retiros retiros) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Retiros persistentRetiros = em.find(Retiros.class, retiros.getIDRetiro());
            Cotizante IDUsuarioOld = persistentRetiros.getIDUsuario();
            Cotizante IDUsuarioNew = retiros.getIDUsuario();
            if (IDUsuarioNew != null) {
                IDUsuarioNew = em.getReference(IDUsuarioNew.getClass(), IDUsuarioNew.getCedula());
                retiros.setIDUsuario(IDUsuarioNew);
            }
            retiros = em.merge(retiros);
            if (IDUsuarioOld != null && !IDUsuarioOld.equals(IDUsuarioNew)) {
                IDUsuarioOld.getRetirosList().remove(retiros);
                IDUsuarioOld = em.merge(IDUsuarioOld);
            }
            if (IDUsuarioNew != null && !IDUsuarioNew.equals(IDUsuarioOld)) {
                IDUsuarioNew.getRetirosList().add(retiros);
                IDUsuarioNew = em.merge(IDUsuarioNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = retiros.getIDRetiro();
                if (findRetiros(id) == null) {
                    throw new NonexistentEntityException("The retiros with id " + id + " no longer exists.");
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
            Retiros retiros;
            try {
                retiros = em.getReference(Retiros.class, id);
                retiros.getIDRetiro();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The retiros with id " + id + " no longer exists.", enfe);
            }
            Cotizante IDUsuario = retiros.getIDUsuario();
            if (IDUsuario != null) {
                IDUsuario.getRetirosList().remove(retiros);
                IDUsuario = em.merge(IDUsuario);
            }
            em.remove(retiros);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Retiros> findRetirosEntities() {
        return findRetirosEntities(true, -1, -1);
    }

    public List<Retiros> findRetirosEntities(int maxResults, int firstResult) {
        return findRetirosEntities(false, maxResults, firstResult);
    }

    private List<Retiros> findRetirosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Retiros.class));
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

    public Retiros findRetiros(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Retiros.class, id);
        } finally {
            em.close();
        }
    }

    public int getRetirosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Retiros> rt = cq.from(Retiros.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
