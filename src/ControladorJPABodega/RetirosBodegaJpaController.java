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
import Entidades_Bodega.Dates;
import Entidades_Bodega.DemografiaPacienteBodega;
import Entidades_Bodega.PacienteBodega;
import Entidades_Bodega.RetirosBodega;
import Entidades_Bodega.RetirosBodegaPK;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author USER
 */
public class RetirosBodegaJpaController implements Serializable {

    public RetirosBodegaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(RetirosBodega retirosBodega) throws PreexistingEntityException, Exception {
        if (retirosBodega.getRetirosBodegaPK() == null) {
            retirosBodega.setRetirosBodegaPK(new RetirosBodegaPK());
        }
        retirosBodega.getRetirosBodegaPK().setFechaKey(retirosBodega.getDates().getDateId());
        retirosBodega.getRetirosBodegaPK().setDemografiaPacienteKey(retirosBodega.getDemografiaPacienteBodega().getDemografiaPacienteKey());
        retirosBodega.getRetirosBodegaPK().setPacienteKey(retirosBodega.getPacienteBodega().getPacienteKey());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Dates dates = retirosBodega.getDates();
            if (dates != null) {
                dates = em.getReference(dates.getClass(), dates.getDateId());
                retirosBodega.setDates(dates);
            }
            DemografiaPacienteBodega demografiaPacienteBodega = retirosBodega.getDemografiaPacienteBodega();
            if (demografiaPacienteBodega != null) {
                demografiaPacienteBodega = em.getReference(demografiaPacienteBodega.getClass(), demografiaPacienteBodega.getDemografiaPacienteKey());
                retirosBodega.setDemografiaPacienteBodega(demografiaPacienteBodega);
            }
            PacienteBodega pacienteBodega = retirosBodega.getPacienteBodega();
            if (pacienteBodega != null) {
                pacienteBodega = em.getReference(pacienteBodega.getClass(), pacienteBodega.getPacienteKey());
                retirosBodega.setPacienteBodega(pacienteBodega);
            }
            em.persist(retirosBodega);
            if (dates != null) {
                dates.getRetirosBodegaList().add(retirosBodega);
                dates = em.merge(dates);
            }
            if (demografiaPacienteBodega != null) {
                demografiaPacienteBodega.getRetirosBodegaList().add(retirosBodega);
                demografiaPacienteBodega = em.merge(demografiaPacienteBodega);
            }
            if (pacienteBodega != null) {
                pacienteBodega.getRetirosBodegaList().add(retirosBodega);
                pacienteBodega = em.merge(pacienteBodega);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findRetirosBodega(retirosBodega.getRetirosBodegaPK()) != null) {
                throw new PreexistingEntityException("RetirosBodega " + retirosBodega + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(RetirosBodega retirosBodega) throws NonexistentEntityException, Exception {
        retirosBodega.getRetirosBodegaPK().setFechaKey(retirosBodega.getDates().getDateId());
        retirosBodega.getRetirosBodegaPK().setDemografiaPacienteKey(retirosBodega.getDemografiaPacienteBodega().getDemografiaPacienteKey());
        retirosBodega.getRetirosBodegaPK().setPacienteKey(retirosBodega.getPacienteBodega().getPacienteKey());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            RetirosBodega persistentRetirosBodega = em.find(RetirosBodega.class, retirosBodega.getRetirosBodegaPK());
            Dates datesOld = persistentRetirosBodega.getDates();
            Dates datesNew = retirosBodega.getDates();
            DemografiaPacienteBodega demografiaPacienteBodegaOld = persistentRetirosBodega.getDemografiaPacienteBodega();
            DemografiaPacienteBodega demografiaPacienteBodegaNew = retirosBodega.getDemografiaPacienteBodega();
            PacienteBodega pacienteBodegaOld = persistentRetirosBodega.getPacienteBodega();
            PacienteBodega pacienteBodegaNew = retirosBodega.getPacienteBodega();
            if (datesNew != null) {
                datesNew = em.getReference(datesNew.getClass(), datesNew.getDateId());
                retirosBodega.setDates(datesNew);
            }
            if (demografiaPacienteBodegaNew != null) {
                demografiaPacienteBodegaNew = em.getReference(demografiaPacienteBodegaNew.getClass(), demografiaPacienteBodegaNew.getDemografiaPacienteKey());
                retirosBodega.setDemografiaPacienteBodega(demografiaPacienteBodegaNew);
            }
            if (pacienteBodegaNew != null) {
                pacienteBodegaNew = em.getReference(pacienteBodegaNew.getClass(), pacienteBodegaNew.getPacienteKey());
                retirosBodega.setPacienteBodega(pacienteBodegaNew);
            }
            retirosBodega = em.merge(retirosBodega);
            if (datesOld != null && !datesOld.equals(datesNew)) {
                datesOld.getRetirosBodegaList().remove(retirosBodega);
                datesOld = em.merge(datesOld);
            }
            if (datesNew != null && !datesNew.equals(datesOld)) {
                datesNew.getRetirosBodegaList().add(retirosBodega);
                datesNew = em.merge(datesNew);
            }
            if (demografiaPacienteBodegaOld != null && !demografiaPacienteBodegaOld.equals(demografiaPacienteBodegaNew)) {
                demografiaPacienteBodegaOld.getRetirosBodegaList().remove(retirosBodega);
                demografiaPacienteBodegaOld = em.merge(demografiaPacienteBodegaOld);
            }
            if (demografiaPacienteBodegaNew != null && !demografiaPacienteBodegaNew.equals(demografiaPacienteBodegaOld)) {
                demografiaPacienteBodegaNew.getRetirosBodegaList().add(retirosBodega);
                demografiaPacienteBodegaNew = em.merge(demografiaPacienteBodegaNew);
            }
            if (pacienteBodegaOld != null && !pacienteBodegaOld.equals(pacienteBodegaNew)) {
                pacienteBodegaOld.getRetirosBodegaList().remove(retirosBodega);
                pacienteBodegaOld = em.merge(pacienteBodegaOld);
            }
            if (pacienteBodegaNew != null && !pacienteBodegaNew.equals(pacienteBodegaOld)) {
                pacienteBodegaNew.getRetirosBodegaList().add(retirosBodega);
                pacienteBodegaNew = em.merge(pacienteBodegaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                RetirosBodegaPK id = retirosBodega.getRetirosBodegaPK();
                if (findRetirosBodega(id) == null) {
                    throw new NonexistentEntityException("The retirosBodega with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(RetirosBodegaPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            RetirosBodega retirosBodega;
            try {
                retirosBodega = em.getReference(RetirosBodega.class, id);
                retirosBodega.getRetirosBodegaPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The retirosBodega with id " + id + " no longer exists.", enfe);
            }
            Dates dates = retirosBodega.getDates();
            if (dates != null) {
                dates.getRetirosBodegaList().remove(retirosBodega);
                dates = em.merge(dates);
            }
            DemografiaPacienteBodega demografiaPacienteBodega = retirosBodega.getDemografiaPacienteBodega();
            if (demografiaPacienteBodega != null) {
                demografiaPacienteBodega.getRetirosBodegaList().remove(retirosBodega);
                demografiaPacienteBodega = em.merge(demografiaPacienteBodega);
            }
            PacienteBodega pacienteBodega = retirosBodega.getPacienteBodega();
            if (pacienteBodega != null) {
                pacienteBodega.getRetirosBodegaList().remove(retirosBodega);
                pacienteBodega = em.merge(pacienteBodega);
            }
            em.remove(retirosBodega);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<RetirosBodega> findRetirosBodegaEntities() {
        return findRetirosBodegaEntities(true, -1, -1);
    }

    public List<RetirosBodega> findRetirosBodegaEntities(int maxResults, int firstResult) {
        return findRetirosBodegaEntities(false, maxResults, firstResult);
    }

    private List<RetirosBodega> findRetirosBodegaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(RetirosBodega.class));
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

    public RetirosBodega findRetirosBodega(RetirosBodegaPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(RetirosBodega.class, id);
        } finally {
            em.close();
        }
    }

    public int getRetirosBodegaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<RetirosBodega> rt = cq.from(RetirosBodega.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
