/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladorJPABodega;

import ControladorJPABodega.exceptions.NonexistentEntityException;
import ControladorJPABodega.exceptions.PreexistingEntityException;
import Entidades_Bodega.ServicioPosBodega;
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
public class ServicioPosBodegaJpaController implements Serializable {

    public ServicioPosBodegaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ServicioPosBodega servicioPosBodega) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(servicioPosBodega);
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

    public void edit(ServicioPosBodega servicioPosBodega) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            servicioPosBodega = em.merge(servicioPosBodega);
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

    public void destroy(Integer id) throws NonexistentEntityException {
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
     public ServicioPosBodega consultar(String f) {          
          
         ServicioPosBodega s=new ServicioPosBodega();
         List lista;
         lista=findServicioPosBodegaEntities();
         
          for (int i = 0; i < lista.size(); i++) {
              ServicioPosBodega sPOS=(ServicioPosBodega) lista.get(i);             
              
             
              if(sPOS.getCodServicio().toString().equalsIgnoreCase(f)){                  
                  s=sPOS;
                //  System.err.println(s);
                  break;
              }
          }

        return s;
    }
}
