/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladorJPABodega;

import ControladorJPABodega.exceptions.NonexistentEntityException;
import ControladorJPABodega.exceptions.PreexistingEntityException;
import Entidades_Bodega.AfiliacionesBodega;
import Entidades_Bodega.AfiliacionesBodegaPK;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entidades_Bodega.IpsBodega;
import Entidades_Bodega.EmpresaBodega;
import Entidades_Bodega.Dates;
import Entidades_Bodega.DemografiaPacienteBodega;
import Entidades_Bodega.PacienteBodega;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author USER
 */
public class AfiliacionesBodegaJpaController implements Serializable {

    public AfiliacionesBodegaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(AfiliacionesBodega afiliacionesBodega) throws PreexistingEntityException, Exception {
        if (afiliacionesBodega.getAfiliacionesBodegaPK() == null) {
            afiliacionesBodega.setAfiliacionesBodegaPK(new AfiliacionesBodegaPK());
        }
        afiliacionesBodega.getAfiliacionesBodegaPK().setEmpresaKey(afiliacionesBodega.getEmpresaBodega().getEmpresaKey());
        afiliacionesBodega.getAfiliacionesBodegaPK().setIpsKey(afiliacionesBodega.getIpsBodega().getIpsKey());
        afiliacionesBodega.getAfiliacionesBodegaPK().setDemografiaPacienteKey(afiliacionesBodega.getDemografiaPacienteBodega().getDemografiaPacienteKey());
        afiliacionesBodega.getAfiliacionesBodegaPK().setPacienteKey(afiliacionesBodega.getPacienteBodega().getPacienteKey());
        afiliacionesBodega.getAfiliacionesBodegaPK().setFechaKey(afiliacionesBodega.getDates().getDateId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            IpsBodega ipsBodega = afiliacionesBodega.getIpsBodega();
            if (ipsBodega != null) {
                ipsBodega = em.getReference(ipsBodega.getClass(), ipsBodega.getIpsKey());
                afiliacionesBodega.setIpsBodega(ipsBodega);
            }
            EmpresaBodega empresaBodega = afiliacionesBodega.getEmpresaBodega();
            if (empresaBodega != null) {
                empresaBodega = em.getReference(empresaBodega.getClass(), empresaBodega.getEmpresaKey());
                afiliacionesBodega.setEmpresaBodega(empresaBodega);
            }
            Dates dates = afiliacionesBodega.getDates();
            if (dates != null) {
                dates = em.getReference(dates.getClass(), dates.getDateId());
                afiliacionesBodega.setDates(dates);
            }
            DemografiaPacienteBodega demografiaPacienteBodega = afiliacionesBodega.getDemografiaPacienteBodega();
            if (demografiaPacienteBodega != null) {
                demografiaPacienteBodega = em.getReference(demografiaPacienteBodega.getClass(), demografiaPacienteBodega.getDemografiaPacienteKey());
                afiliacionesBodega.setDemografiaPacienteBodega(demografiaPacienteBodega);
            }
            PacienteBodega pacienteBodega = afiliacionesBodega.getPacienteBodega();
            if (pacienteBodega != null) {
                pacienteBodega = em.getReference(pacienteBodega.getClass(), pacienteBodega.getPacienteKey());
                afiliacionesBodega.setPacienteBodega(pacienteBodega);
            }
            em.persist(afiliacionesBodega);
            if (ipsBodega != null) {
                ipsBodega.getAfiliacionesBodegaList().add(afiliacionesBodega);
                ipsBodega = em.merge(ipsBodega);
            }
            if (empresaBodega != null) {
                empresaBodega.getAfiliacionesBodegaList().add(afiliacionesBodega);
                empresaBodega = em.merge(empresaBodega);
            }
            if (dates != null) {
                dates.getAfiliacionesBodegaList().add(afiliacionesBodega);
                dates = em.merge(dates);
            }
            if (demografiaPacienteBodega != null) {
                demografiaPacienteBodega.getAfiliacionesBodegaList().add(afiliacionesBodega);
                demografiaPacienteBodega = em.merge(demografiaPacienteBodega);
            }
            if (pacienteBodega != null) {
                pacienteBodega.getAfiliacionesBodegaList().add(afiliacionesBodega);
                pacienteBodega = em.merge(pacienteBodega);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findAfiliacionesBodega(afiliacionesBodega.getAfiliacionesBodegaPK()) != null) {
                throw new PreexistingEntityException("AfiliacionesBodega " + afiliacionesBodega + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(AfiliacionesBodega afiliacionesBodega) throws NonexistentEntityException, Exception {
        afiliacionesBodega.getAfiliacionesBodegaPK().setEmpresaKey(afiliacionesBodega.getEmpresaBodega().getEmpresaKey());
        afiliacionesBodega.getAfiliacionesBodegaPK().setIpsKey(afiliacionesBodega.getIpsBodega().getIpsKey());
        afiliacionesBodega.getAfiliacionesBodegaPK().setDemografiaPacienteKey(afiliacionesBodega.getDemografiaPacienteBodega().getDemografiaPacienteKey());
        afiliacionesBodega.getAfiliacionesBodegaPK().setPacienteKey(afiliacionesBodega.getPacienteBodega().getPacienteKey());
        afiliacionesBodega.getAfiliacionesBodegaPK().setFechaKey(afiliacionesBodega.getDates().getDateId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AfiliacionesBodega persistentAfiliacionesBodega = em.find(AfiliacionesBodega.class, afiliacionesBodega.getAfiliacionesBodegaPK());
            IpsBodega ipsBodegaOld = persistentAfiliacionesBodega.getIpsBodega();
            IpsBodega ipsBodegaNew = afiliacionesBodega.getIpsBodega();
            EmpresaBodega empresaBodegaOld = persistentAfiliacionesBodega.getEmpresaBodega();
            EmpresaBodega empresaBodegaNew = afiliacionesBodega.getEmpresaBodega();
            Dates datesOld = persistentAfiliacionesBodega.getDates();
            Dates datesNew = afiliacionesBodega.getDates();
            DemografiaPacienteBodega demografiaPacienteBodegaOld = persistentAfiliacionesBodega.getDemografiaPacienteBodega();
            DemografiaPacienteBodega demografiaPacienteBodegaNew = afiliacionesBodega.getDemografiaPacienteBodega();
            PacienteBodega pacienteBodegaOld = persistentAfiliacionesBodega.getPacienteBodega();
            PacienteBodega pacienteBodegaNew = afiliacionesBodega.getPacienteBodega();
            if (ipsBodegaNew != null) {
                ipsBodegaNew = em.getReference(ipsBodegaNew.getClass(), ipsBodegaNew.getIpsKey());
                afiliacionesBodega.setIpsBodega(ipsBodegaNew);
            }
            if (empresaBodegaNew != null) {
                empresaBodegaNew = em.getReference(empresaBodegaNew.getClass(), empresaBodegaNew.getEmpresaKey());
                afiliacionesBodega.setEmpresaBodega(empresaBodegaNew);
            }
            if (datesNew != null) {
                datesNew = em.getReference(datesNew.getClass(), datesNew.getDateId());
                afiliacionesBodega.setDates(datesNew);
            }
            if (demografiaPacienteBodegaNew != null) {
                demografiaPacienteBodegaNew = em.getReference(demografiaPacienteBodegaNew.getClass(), demografiaPacienteBodegaNew.getDemografiaPacienteKey());
                afiliacionesBodega.setDemografiaPacienteBodega(demografiaPacienteBodegaNew);
            }
            if (pacienteBodegaNew != null) {
                pacienteBodegaNew = em.getReference(pacienteBodegaNew.getClass(), pacienteBodegaNew.getPacienteKey());
                afiliacionesBodega.setPacienteBodega(pacienteBodegaNew);
            }
            afiliacionesBodega = em.merge(afiliacionesBodega);
            if (ipsBodegaOld != null && !ipsBodegaOld.equals(ipsBodegaNew)) {
                ipsBodegaOld.getAfiliacionesBodegaList().remove(afiliacionesBodega);
                ipsBodegaOld = em.merge(ipsBodegaOld);
            }
            if (ipsBodegaNew != null && !ipsBodegaNew.equals(ipsBodegaOld)) {
                ipsBodegaNew.getAfiliacionesBodegaList().add(afiliacionesBodega);
                ipsBodegaNew = em.merge(ipsBodegaNew);
            }
            if (empresaBodegaOld != null && !empresaBodegaOld.equals(empresaBodegaNew)) {
                empresaBodegaOld.getAfiliacionesBodegaList().remove(afiliacionesBodega);
                empresaBodegaOld = em.merge(empresaBodegaOld);
            }
            if (empresaBodegaNew != null && !empresaBodegaNew.equals(empresaBodegaOld)) {
                empresaBodegaNew.getAfiliacionesBodegaList().add(afiliacionesBodega);
                empresaBodegaNew = em.merge(empresaBodegaNew);
            }
            if (datesOld != null && !datesOld.equals(datesNew)) {
                datesOld.getAfiliacionesBodegaList().remove(afiliacionesBodega);
                datesOld = em.merge(datesOld);
            }
            if (datesNew != null && !datesNew.equals(datesOld)) {
                datesNew.getAfiliacionesBodegaList().add(afiliacionesBodega);
                datesNew = em.merge(datesNew);
            }
            if (demografiaPacienteBodegaOld != null && !demografiaPacienteBodegaOld.equals(demografiaPacienteBodegaNew)) {
                demografiaPacienteBodegaOld.getAfiliacionesBodegaList().remove(afiliacionesBodega);
                demografiaPacienteBodegaOld = em.merge(demografiaPacienteBodegaOld);
            }
            if (demografiaPacienteBodegaNew != null && !demografiaPacienteBodegaNew.equals(demografiaPacienteBodegaOld)) {
                demografiaPacienteBodegaNew.getAfiliacionesBodegaList().add(afiliacionesBodega);
                demografiaPacienteBodegaNew = em.merge(demografiaPacienteBodegaNew);
            }
            if (pacienteBodegaOld != null && !pacienteBodegaOld.equals(pacienteBodegaNew)) {
                pacienteBodegaOld.getAfiliacionesBodegaList().remove(afiliacionesBodega);
                pacienteBodegaOld = em.merge(pacienteBodegaOld);
            }
            if (pacienteBodegaNew != null && !pacienteBodegaNew.equals(pacienteBodegaOld)) {
                pacienteBodegaNew.getAfiliacionesBodegaList().add(afiliacionesBodega);
                pacienteBodegaNew = em.merge(pacienteBodegaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                AfiliacionesBodegaPK id = afiliacionesBodega.getAfiliacionesBodegaPK();
                if (findAfiliacionesBodega(id) == null) {
                    throw new NonexistentEntityException("The afiliacionesBodega with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(AfiliacionesBodegaPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AfiliacionesBodega afiliacionesBodega;
            try {
                afiliacionesBodega = em.getReference(AfiliacionesBodega.class, id);
                afiliacionesBodega.getAfiliacionesBodegaPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The afiliacionesBodega with id " + id + " no longer exists.", enfe);
            }
            IpsBodega ipsBodega = afiliacionesBodega.getIpsBodega();
            if (ipsBodega != null) {
                ipsBodega.getAfiliacionesBodegaList().remove(afiliacionesBodega);
                ipsBodega = em.merge(ipsBodega);
            }
            EmpresaBodega empresaBodega = afiliacionesBodega.getEmpresaBodega();
            if (empresaBodega != null) {
                empresaBodega.getAfiliacionesBodegaList().remove(afiliacionesBodega);
                empresaBodega = em.merge(empresaBodega);
            }
            Dates dates = afiliacionesBodega.getDates();
            if (dates != null) {
                dates.getAfiliacionesBodegaList().remove(afiliacionesBodega);
                dates = em.merge(dates);
            }
            DemografiaPacienteBodega demografiaPacienteBodega = afiliacionesBodega.getDemografiaPacienteBodega();
            if (demografiaPacienteBodega != null) {
                demografiaPacienteBodega.getAfiliacionesBodegaList().remove(afiliacionesBodega);
                demografiaPacienteBodega = em.merge(demografiaPacienteBodega);
            }
            PacienteBodega pacienteBodega = afiliacionesBodega.getPacienteBodega();
            if (pacienteBodega != null) {
                pacienteBodega.getAfiliacionesBodegaList().remove(afiliacionesBodega);
                pacienteBodega = em.merge(pacienteBodega);
            }
            em.remove(afiliacionesBodega);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<AfiliacionesBodega> findAfiliacionesBodegaEntities() {
        return findAfiliacionesBodegaEntities(true, -1, -1);
    }

    public List<AfiliacionesBodega> findAfiliacionesBodegaEntities(int maxResults, int firstResult) {
        return findAfiliacionesBodegaEntities(false, maxResults, firstResult);
    }

    private List<AfiliacionesBodega> findAfiliacionesBodegaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(AfiliacionesBodega.class));
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

    public AfiliacionesBodega findAfiliacionesBodega(AfiliacionesBodegaPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AfiliacionesBodega.class, id);
        } finally {
            em.close();
        }
    }

    public int getAfiliacionesBodegaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<AfiliacionesBodega> rt = cq.from(AfiliacionesBodega.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
