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
import Entidades_Bodega.PreexistenciaBodega;
import Entidades_Bodega.EmpresaBodega;
import Entidades_Bodega.Dates;
import Entidades_Bodega.DemografiaPacienteBodega;
import Entidades_Bodega.PacienteBodega;
import Entidades_Bodega.PagosBodega;
import Entidades_Bodega.PagosBodegaPK;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author USER
 */
public class PagosBodegaJpaController implements Serializable {

    public PagosBodegaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PagosBodega pagosBodega) throws PreexistingEntityException, Exception {
        if (pagosBodega.getPagosBodegaPK() == null) {
            pagosBodega.setPagosBodegaPK(new PagosBodegaPK());
        }
        pagosBodega.getPagosBodegaPK().setPreexistenciaKey(pagosBodega.getPreexistenciaBodega().getPreexistenciaKey());
        pagosBodega.getPagosBodegaPK().setPacienteKey(pagosBodega.getPacienteBodega().getPacienteKey());
        pagosBodega.getPagosBodegaPK().setDemografiaPacienteKey(pagosBodega.getDemografiaPacienteBodega().getDemografiaPacienteKey());
        pagosBodega.getPagosBodegaPK().setEmpresaKey(pagosBodega.getEmpresaBodega().getEmpresaKey());
        pagosBodega.getPagosBodegaPK().setFechaKey(pagosBodega.getDates().getDateId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PreexistenciaBodega preexistenciaBodega = pagosBodega.getPreexistenciaBodega();
            if (preexistenciaBodega != null) {
                preexistenciaBodega = em.getReference(preexistenciaBodega.getClass(), preexistenciaBodega.getPreexistenciaKey());
                pagosBodega.setPreexistenciaBodega(preexistenciaBodega);
            }
            EmpresaBodega empresaBodega = pagosBodega.getEmpresaBodega();
            if (empresaBodega != null) {
                empresaBodega = em.getReference(empresaBodega.getClass(), empresaBodega.getEmpresaKey());
                pagosBodega.setEmpresaBodega(empresaBodega);
            }
            Dates dates = pagosBodega.getDates();
            if (dates != null) {
                dates = em.getReference(dates.getClass(), dates.getDateId());
                pagosBodega.setDates(dates);
            }
            DemografiaPacienteBodega demografiaPacienteBodega = pagosBodega.getDemografiaPacienteBodega();
            if (demografiaPacienteBodega != null) {
                demografiaPacienteBodega = em.getReference(demografiaPacienteBodega.getClass(), demografiaPacienteBodega.getDemografiaPacienteKey());
                pagosBodega.setDemografiaPacienteBodega(demografiaPacienteBodega);
            }
            PacienteBodega pacienteBodega = pagosBodega.getPacienteBodega();
            if (pacienteBodega != null) {
                pacienteBodega = em.getReference(pacienteBodega.getClass(), pacienteBodega.getPacienteKey());
                pagosBodega.setPacienteBodega(pacienteBodega);
            }
            em.persist(pagosBodega);
            if (preexistenciaBodega != null) {
                preexistenciaBodega.getPagosBodegaList().add(pagosBodega);
                preexistenciaBodega = em.merge(preexistenciaBodega);
            }
            if (empresaBodega != null) {
                empresaBodega.getPagosBodegaList().add(pagosBodega);
                empresaBodega = em.merge(empresaBodega);
            }
            if (dates != null) {
                dates.getPagosBodegaList().add(pagosBodega);
                dates = em.merge(dates);
            }
            if (demografiaPacienteBodega != null) {
                demografiaPacienteBodega.getPagosBodegaList().add(pagosBodega);
                demografiaPacienteBodega = em.merge(demografiaPacienteBodega);
            }
            if (pacienteBodega != null) {
                pacienteBodega.getPagosBodegaList().add(pagosBodega);
                pacienteBodega = em.merge(pacienteBodega);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPagosBodega(pagosBodega.getPagosBodegaPK()) != null) {
                throw new PreexistingEntityException("PagosBodega " + pagosBodega + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PagosBodega pagosBodega) throws NonexistentEntityException, Exception {
        pagosBodega.getPagosBodegaPK().setPreexistenciaKey(pagosBodega.getPreexistenciaBodega().getPreexistenciaKey());
        pagosBodega.getPagosBodegaPK().setPacienteKey(pagosBodega.getPacienteBodega().getPacienteKey());
        pagosBodega.getPagosBodegaPK().setDemografiaPacienteKey(pagosBodega.getDemografiaPacienteBodega().getDemografiaPacienteKey());
        pagosBodega.getPagosBodegaPK().setEmpresaKey(pagosBodega.getEmpresaBodega().getEmpresaKey());
        pagosBodega.getPagosBodegaPK().setFechaKey(pagosBodega.getDates().getDateId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PagosBodega persistentPagosBodega = em.find(PagosBodega.class, pagosBodega.getPagosBodegaPK());
            PreexistenciaBodega preexistenciaBodegaOld = persistentPagosBodega.getPreexistenciaBodega();
            PreexistenciaBodega preexistenciaBodegaNew = pagosBodega.getPreexistenciaBodega();
            EmpresaBodega empresaBodegaOld = persistentPagosBodega.getEmpresaBodega();
            EmpresaBodega empresaBodegaNew = pagosBodega.getEmpresaBodega();
            Dates datesOld = persistentPagosBodega.getDates();
            Dates datesNew = pagosBodega.getDates();
            DemografiaPacienteBodega demografiaPacienteBodegaOld = persistentPagosBodega.getDemografiaPacienteBodega();
            DemografiaPacienteBodega demografiaPacienteBodegaNew = pagosBodega.getDemografiaPacienteBodega();
            PacienteBodega pacienteBodegaOld = persistentPagosBodega.getPacienteBodega();
            PacienteBodega pacienteBodegaNew = pagosBodega.getPacienteBodega();
            if (preexistenciaBodegaNew != null) {
                preexistenciaBodegaNew = em.getReference(preexistenciaBodegaNew.getClass(), preexistenciaBodegaNew.getPreexistenciaKey());
                pagosBodega.setPreexistenciaBodega(preexistenciaBodegaNew);
            }
            if (empresaBodegaNew != null) {
                empresaBodegaNew = em.getReference(empresaBodegaNew.getClass(), empresaBodegaNew.getEmpresaKey());
                pagosBodega.setEmpresaBodega(empresaBodegaNew);
            }
            if (datesNew != null) {
                datesNew = em.getReference(datesNew.getClass(), datesNew.getDateId());
                pagosBodega.setDates(datesNew);
            }
            if (demografiaPacienteBodegaNew != null) {
                demografiaPacienteBodegaNew = em.getReference(demografiaPacienteBodegaNew.getClass(), demografiaPacienteBodegaNew.getDemografiaPacienteKey());
                pagosBodega.setDemografiaPacienteBodega(demografiaPacienteBodegaNew);
            }
            if (pacienteBodegaNew != null) {
                pacienteBodegaNew = em.getReference(pacienteBodegaNew.getClass(), pacienteBodegaNew.getPacienteKey());
                pagosBodega.setPacienteBodega(pacienteBodegaNew);
            }
            pagosBodega = em.merge(pagosBodega);
            if (preexistenciaBodegaOld != null && !preexistenciaBodegaOld.equals(preexistenciaBodegaNew)) {
                preexistenciaBodegaOld.getPagosBodegaList().remove(pagosBodega);
                preexistenciaBodegaOld = em.merge(preexistenciaBodegaOld);
            }
            if (preexistenciaBodegaNew != null && !preexistenciaBodegaNew.equals(preexistenciaBodegaOld)) {
                preexistenciaBodegaNew.getPagosBodegaList().add(pagosBodega);
                preexistenciaBodegaNew = em.merge(preexistenciaBodegaNew);
            }
            if (empresaBodegaOld != null && !empresaBodegaOld.equals(empresaBodegaNew)) {
                empresaBodegaOld.getPagosBodegaList().remove(pagosBodega);
                empresaBodegaOld = em.merge(empresaBodegaOld);
            }
            if (empresaBodegaNew != null && !empresaBodegaNew.equals(empresaBodegaOld)) {
                empresaBodegaNew.getPagosBodegaList().add(pagosBodega);
                empresaBodegaNew = em.merge(empresaBodegaNew);
            }
            if (datesOld != null && !datesOld.equals(datesNew)) {
                datesOld.getPagosBodegaList().remove(pagosBodega);
                datesOld = em.merge(datesOld);
            }
            if (datesNew != null && !datesNew.equals(datesOld)) {
                datesNew.getPagosBodegaList().add(pagosBodega);
                datesNew = em.merge(datesNew);
            }
            if (demografiaPacienteBodegaOld != null && !demografiaPacienteBodegaOld.equals(demografiaPacienteBodegaNew)) {
                demografiaPacienteBodegaOld.getPagosBodegaList().remove(pagosBodega);
                demografiaPacienteBodegaOld = em.merge(demografiaPacienteBodegaOld);
            }
            if (demografiaPacienteBodegaNew != null && !demografiaPacienteBodegaNew.equals(demografiaPacienteBodegaOld)) {
                demografiaPacienteBodegaNew.getPagosBodegaList().add(pagosBodega);
                demografiaPacienteBodegaNew = em.merge(demografiaPacienteBodegaNew);
            }
            if (pacienteBodegaOld != null && !pacienteBodegaOld.equals(pacienteBodegaNew)) {
                pacienteBodegaOld.getPagosBodegaList().remove(pagosBodega);
                pacienteBodegaOld = em.merge(pacienteBodegaOld);
            }
            if (pacienteBodegaNew != null && !pacienteBodegaNew.equals(pacienteBodegaOld)) {
                pacienteBodegaNew.getPagosBodegaList().add(pagosBodega);
                pacienteBodegaNew = em.merge(pacienteBodegaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                PagosBodegaPK id = pagosBodega.getPagosBodegaPK();
                if (findPagosBodega(id) == null) {
                    throw new NonexistentEntityException("The pagosBodega with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(PagosBodegaPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PagosBodega pagosBodega;
            try {
                pagosBodega = em.getReference(PagosBodega.class, id);
                pagosBodega.getPagosBodegaPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pagosBodega with id " + id + " no longer exists.", enfe);
            }
            PreexistenciaBodega preexistenciaBodega = pagosBodega.getPreexistenciaBodega();
            if (preexistenciaBodega != null) {
                preexistenciaBodega.getPagosBodegaList().remove(pagosBodega);
                preexistenciaBodega = em.merge(preexistenciaBodega);
            }
            EmpresaBodega empresaBodega = pagosBodega.getEmpresaBodega();
            if (empresaBodega != null) {
                empresaBodega.getPagosBodegaList().remove(pagosBodega);
                empresaBodega = em.merge(empresaBodega);
            }
            Dates dates = pagosBodega.getDates();
            if (dates != null) {
                dates.getPagosBodegaList().remove(pagosBodega);
                dates = em.merge(dates);
            }
            DemografiaPacienteBodega demografiaPacienteBodega = pagosBodega.getDemografiaPacienteBodega();
            if (demografiaPacienteBodega != null) {
                demografiaPacienteBodega.getPagosBodegaList().remove(pagosBodega);
                demografiaPacienteBodega = em.merge(demografiaPacienteBodega);
            }
            PacienteBodega pacienteBodega = pagosBodega.getPacienteBodega();
            if (pacienteBodega != null) {
                pacienteBodega.getPagosBodegaList().remove(pagosBodega);
                pacienteBodega = em.merge(pacienteBodega);
            }
            em.remove(pagosBodega);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PagosBodega> findPagosBodegaEntities() {
        return findPagosBodegaEntities(true, -1, -1);
    }

    public List<PagosBodega> findPagosBodegaEntities(int maxResults, int firstResult) {
        return findPagosBodegaEntities(false, maxResults, firstResult);
    }

    private List<PagosBodega> findPagosBodegaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PagosBodega.class));
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

    public PagosBodega findPagosBodega(PagosBodegaPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PagosBodega.class, id);
        } finally {
            em.close();
        }
    }

    public int getPagosBodegaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PagosBodega> rt = cq.from(PagosBodega.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
