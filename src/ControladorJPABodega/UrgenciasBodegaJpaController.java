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
import Entidades_Bodega.IpsBodega;
import Entidades_Bodega.DiagnosticoBodega;
import Entidades_Bodega.ServicioPosBodega;
import Entidades_Bodega.EmpresaBodega;
import Entidades_Bodega.Dates;
import Entidades_Bodega.DemografiaPacienteBodega;
import Entidades_Bodega.PacienteBodega;
import Entidades_Bodega.UrgenciasBodega;
import Entidades_Bodega.UrgenciasBodegaPK;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author USER
 */
public class UrgenciasBodegaJpaController implements Serializable {

    public UrgenciasBodegaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UrgenciasBodega urgenciasBodega) throws PreexistingEntityException, Exception {
        if (urgenciasBodega.getUrgenciasBodegaPK() == null) {
            urgenciasBodega.setUrgenciasBodegaPK(new UrgenciasBodegaPK());
        }
        urgenciasBodega.getUrgenciasBodegaPK().setDemografiaPacienteKey(urgenciasBodega.getDemografiaPacienteBodega().getDemografiaPacienteKey());
        urgenciasBodega.getUrgenciasBodegaPK().setPacienteKey(urgenciasBodega.getPacienteBodega().getPacienteKey());
        urgenciasBodega.getUrgenciasBodegaPK().setEmpresaKey(urgenciasBodega.getEmpresaBodega().getEmpresaKey());
        urgenciasBodega.getUrgenciasBodegaPK().setPreexistenciaKey(urgenciasBodega.getPreexistenciaBodega().getPreexistenciaKey());
        urgenciasBodega.getUrgenciasBodegaPK().setFechaKey(urgenciasBodega.getDates().getDateId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PreexistenciaBodega preexistenciaBodega = urgenciasBodega.getPreexistenciaBodega();
            if (preexistenciaBodega != null) {
                preexistenciaBodega = em.getReference(preexistenciaBodega.getClass(), preexistenciaBodega.getPreexistenciaKey());
                urgenciasBodega.setPreexistenciaBodega(preexistenciaBodega);
            }
            IpsBodega ipsKey = urgenciasBodega.getIpsKey();
            if (ipsKey != null) {
                ipsKey = em.getReference(ipsKey.getClass(), ipsKey.getIpsKey());
                urgenciasBodega.setIpsKey(ipsKey);
            }
            DiagnosticoBodega diagnosticoKey = urgenciasBodega.getDiagnosticoKey();
            if (diagnosticoKey != null) {
                diagnosticoKey = em.getReference(diagnosticoKey.getClass(), diagnosticoKey.getDiagnosticoKey());
                urgenciasBodega.setDiagnosticoKey(diagnosticoKey);
            }
            ServicioPosBodega servicioPosKey = urgenciasBodega.getServicioPosKey();
            if (servicioPosKey != null) {
                servicioPosKey = em.getReference(servicioPosKey.getClass(), servicioPosKey.getServicioPosKey());
                urgenciasBodega.setServicioPosKey(servicioPosKey);
            }
            EmpresaBodega empresaBodega = urgenciasBodega.getEmpresaBodega();
            if (empresaBodega != null) {
                empresaBodega = em.getReference(empresaBodega.getClass(), empresaBodega.getEmpresaKey());
                urgenciasBodega.setEmpresaBodega(empresaBodega);
            }
            Dates dates = urgenciasBodega.getDates();
            if (dates != null) {
                dates = em.getReference(dates.getClass(), dates.getDateId());
                urgenciasBodega.setDates(dates);
            }
            DemografiaPacienteBodega demografiaPacienteBodega = urgenciasBodega.getDemografiaPacienteBodega();
            if (demografiaPacienteBodega != null) {
                demografiaPacienteBodega = em.getReference(demografiaPacienteBodega.getClass(), demografiaPacienteBodega.getDemografiaPacienteKey());
                urgenciasBodega.setDemografiaPacienteBodega(demografiaPacienteBodega);
            }
            PacienteBodega pacienteBodega = urgenciasBodega.getPacienteBodega();
            if (pacienteBodega != null) {
                pacienteBodega = em.getReference(pacienteBodega.getClass(), pacienteBodega.getPacienteKey());
                urgenciasBodega.setPacienteBodega(pacienteBodega);
            }
            em.persist(urgenciasBodega);
            if (preexistenciaBodega != null) {
                preexistenciaBodega.getUrgenciasBodegaList().add(urgenciasBodega);
                preexistenciaBodega = em.merge(preexistenciaBodega);
            }
            if (ipsKey != null) {
                ipsKey.getUrgenciasBodegaList().add(urgenciasBodega);
                ipsKey = em.merge(ipsKey);
            }
            if (diagnosticoKey != null) {
                diagnosticoKey.getUrgenciasBodegaList().add(urgenciasBodega);
                diagnosticoKey = em.merge(diagnosticoKey);
            }
            if (servicioPosKey != null) {
                servicioPosKey.getUrgenciasBodegaList().add(urgenciasBodega);
                servicioPosKey = em.merge(servicioPosKey);
            }
            if (empresaBodega != null) {
                empresaBodega.getUrgenciasBodegaList().add(urgenciasBodega);
                empresaBodega = em.merge(empresaBodega);
            }
            if (dates != null) {
                dates.getUrgenciasBodegaList().add(urgenciasBodega);
                dates = em.merge(dates);
            }
            if (demografiaPacienteBodega != null) {
                demografiaPacienteBodega.getUrgenciasBodegaList().add(urgenciasBodega);
                demografiaPacienteBodega = em.merge(demografiaPacienteBodega);
            }
            if (pacienteBodega != null) {
                pacienteBodega.getUrgenciasBodegaList().add(urgenciasBodega);
                pacienteBodega = em.merge(pacienteBodega);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findUrgenciasBodega(urgenciasBodega.getUrgenciasBodegaPK()) != null) {
                throw new PreexistingEntityException("UrgenciasBodega " + urgenciasBodega + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UrgenciasBodega urgenciasBodega) throws NonexistentEntityException, Exception {
        urgenciasBodega.getUrgenciasBodegaPK().setDemografiaPacienteKey(urgenciasBodega.getDemografiaPacienteBodega().getDemografiaPacienteKey());
        urgenciasBodega.getUrgenciasBodegaPK().setPacienteKey(urgenciasBodega.getPacienteBodega().getPacienteKey());
        urgenciasBodega.getUrgenciasBodegaPK().setEmpresaKey(urgenciasBodega.getEmpresaBodega().getEmpresaKey());
        urgenciasBodega.getUrgenciasBodegaPK().setPreexistenciaKey(urgenciasBodega.getPreexistenciaBodega().getPreexistenciaKey());
        urgenciasBodega.getUrgenciasBodegaPK().setFechaKey(urgenciasBodega.getDates().getDateId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UrgenciasBodega persistentUrgenciasBodega = em.find(UrgenciasBodega.class, urgenciasBodega.getUrgenciasBodegaPK());
            PreexistenciaBodega preexistenciaBodegaOld = persistentUrgenciasBodega.getPreexistenciaBodega();
            PreexistenciaBodega preexistenciaBodegaNew = urgenciasBodega.getPreexistenciaBodega();
            IpsBodega ipsKeyOld = persistentUrgenciasBodega.getIpsKey();
            IpsBodega ipsKeyNew = urgenciasBodega.getIpsKey();
            DiagnosticoBodega diagnosticoKeyOld = persistentUrgenciasBodega.getDiagnosticoKey();
            DiagnosticoBodega diagnosticoKeyNew = urgenciasBodega.getDiagnosticoKey();
            ServicioPosBodega servicioPosKeyOld = persistentUrgenciasBodega.getServicioPosKey();
            ServicioPosBodega servicioPosKeyNew = urgenciasBodega.getServicioPosKey();
            EmpresaBodega empresaBodegaOld = persistentUrgenciasBodega.getEmpresaBodega();
            EmpresaBodega empresaBodegaNew = urgenciasBodega.getEmpresaBodega();
            Dates datesOld = persistentUrgenciasBodega.getDates();
            Dates datesNew = urgenciasBodega.getDates();
            DemografiaPacienteBodega demografiaPacienteBodegaOld = persistentUrgenciasBodega.getDemografiaPacienteBodega();
            DemografiaPacienteBodega demografiaPacienteBodegaNew = urgenciasBodega.getDemografiaPacienteBodega();
            PacienteBodega pacienteBodegaOld = persistentUrgenciasBodega.getPacienteBodega();
            PacienteBodega pacienteBodegaNew = urgenciasBodega.getPacienteBodega();
            if (preexistenciaBodegaNew != null) {
                preexistenciaBodegaNew = em.getReference(preexistenciaBodegaNew.getClass(), preexistenciaBodegaNew.getPreexistenciaKey());
                urgenciasBodega.setPreexistenciaBodega(preexistenciaBodegaNew);
            }
            if (ipsKeyNew != null) {
                ipsKeyNew = em.getReference(ipsKeyNew.getClass(), ipsKeyNew.getIpsKey());
                urgenciasBodega.setIpsKey(ipsKeyNew);
            }
            if (diagnosticoKeyNew != null) {
                diagnosticoKeyNew = em.getReference(diagnosticoKeyNew.getClass(), diagnosticoKeyNew.getDiagnosticoKey());
                urgenciasBodega.setDiagnosticoKey(diagnosticoKeyNew);
            }
            if (servicioPosKeyNew != null) {
                servicioPosKeyNew = em.getReference(servicioPosKeyNew.getClass(), servicioPosKeyNew.getServicioPosKey());
                urgenciasBodega.setServicioPosKey(servicioPosKeyNew);
            }
            if (empresaBodegaNew != null) {
                empresaBodegaNew = em.getReference(empresaBodegaNew.getClass(), empresaBodegaNew.getEmpresaKey());
                urgenciasBodega.setEmpresaBodega(empresaBodegaNew);
            }
            if (datesNew != null) {
                datesNew = em.getReference(datesNew.getClass(), datesNew.getDateId());
                urgenciasBodega.setDates(datesNew);
            }
            if (demografiaPacienteBodegaNew != null) {
                demografiaPacienteBodegaNew = em.getReference(demografiaPacienteBodegaNew.getClass(), demografiaPacienteBodegaNew.getDemografiaPacienteKey());
                urgenciasBodega.setDemografiaPacienteBodega(demografiaPacienteBodegaNew);
            }
            if (pacienteBodegaNew != null) {
                pacienteBodegaNew = em.getReference(pacienteBodegaNew.getClass(), pacienteBodegaNew.getPacienteKey());
                urgenciasBodega.setPacienteBodega(pacienteBodegaNew);
            }
            urgenciasBodega = em.merge(urgenciasBodega);
            if (preexistenciaBodegaOld != null && !preexistenciaBodegaOld.equals(preexistenciaBodegaNew)) {
                preexistenciaBodegaOld.getUrgenciasBodegaList().remove(urgenciasBodega);
                preexistenciaBodegaOld = em.merge(preexistenciaBodegaOld);
            }
            if (preexistenciaBodegaNew != null && !preexistenciaBodegaNew.equals(preexistenciaBodegaOld)) {
                preexistenciaBodegaNew.getUrgenciasBodegaList().add(urgenciasBodega);
                preexistenciaBodegaNew = em.merge(preexistenciaBodegaNew);
            }
            if (ipsKeyOld != null && !ipsKeyOld.equals(ipsKeyNew)) {
                ipsKeyOld.getUrgenciasBodegaList().remove(urgenciasBodega);
                ipsKeyOld = em.merge(ipsKeyOld);
            }
            if (ipsKeyNew != null && !ipsKeyNew.equals(ipsKeyOld)) {
                ipsKeyNew.getUrgenciasBodegaList().add(urgenciasBodega);
                ipsKeyNew = em.merge(ipsKeyNew);
            }
            if (diagnosticoKeyOld != null && !diagnosticoKeyOld.equals(diagnosticoKeyNew)) {
                diagnosticoKeyOld.getUrgenciasBodegaList().remove(urgenciasBodega);
                diagnosticoKeyOld = em.merge(diagnosticoKeyOld);
            }
            if (diagnosticoKeyNew != null && !diagnosticoKeyNew.equals(diagnosticoKeyOld)) {
                diagnosticoKeyNew.getUrgenciasBodegaList().add(urgenciasBodega);
                diagnosticoKeyNew = em.merge(diagnosticoKeyNew);
            }
            if (servicioPosKeyOld != null && !servicioPosKeyOld.equals(servicioPosKeyNew)) {
                servicioPosKeyOld.getUrgenciasBodegaList().remove(urgenciasBodega);
                servicioPosKeyOld = em.merge(servicioPosKeyOld);
            }
            if (servicioPosKeyNew != null && !servicioPosKeyNew.equals(servicioPosKeyOld)) {
                servicioPosKeyNew.getUrgenciasBodegaList().add(urgenciasBodega);
                servicioPosKeyNew = em.merge(servicioPosKeyNew);
            }
            if (empresaBodegaOld != null && !empresaBodegaOld.equals(empresaBodegaNew)) {
                empresaBodegaOld.getUrgenciasBodegaList().remove(urgenciasBodega);
                empresaBodegaOld = em.merge(empresaBodegaOld);
            }
            if (empresaBodegaNew != null && !empresaBodegaNew.equals(empresaBodegaOld)) {
                empresaBodegaNew.getUrgenciasBodegaList().add(urgenciasBodega);
                empresaBodegaNew = em.merge(empresaBodegaNew);
            }
            if (datesOld != null && !datesOld.equals(datesNew)) {
                datesOld.getUrgenciasBodegaList().remove(urgenciasBodega);
                datesOld = em.merge(datesOld);
            }
            if (datesNew != null && !datesNew.equals(datesOld)) {
                datesNew.getUrgenciasBodegaList().add(urgenciasBodega);
                datesNew = em.merge(datesNew);
            }
            if (demografiaPacienteBodegaOld != null && !demografiaPacienteBodegaOld.equals(demografiaPacienteBodegaNew)) {
                demografiaPacienteBodegaOld.getUrgenciasBodegaList().remove(urgenciasBodega);
                demografiaPacienteBodegaOld = em.merge(demografiaPacienteBodegaOld);
            }
            if (demografiaPacienteBodegaNew != null && !demografiaPacienteBodegaNew.equals(demografiaPacienteBodegaOld)) {
                demografiaPacienteBodegaNew.getUrgenciasBodegaList().add(urgenciasBodega);
                demografiaPacienteBodegaNew = em.merge(demografiaPacienteBodegaNew);
            }
            if (pacienteBodegaOld != null && !pacienteBodegaOld.equals(pacienteBodegaNew)) {
                pacienteBodegaOld.getUrgenciasBodegaList().remove(urgenciasBodega);
                pacienteBodegaOld = em.merge(pacienteBodegaOld);
            }
            if (pacienteBodegaNew != null && !pacienteBodegaNew.equals(pacienteBodegaOld)) {
                pacienteBodegaNew.getUrgenciasBodegaList().add(urgenciasBodega);
                pacienteBodegaNew = em.merge(pacienteBodegaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                UrgenciasBodegaPK id = urgenciasBodega.getUrgenciasBodegaPK();
                if (findUrgenciasBodega(id) == null) {
                    throw new NonexistentEntityException("The urgenciasBodega with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(UrgenciasBodegaPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UrgenciasBodega urgenciasBodega;
            try {
                urgenciasBodega = em.getReference(UrgenciasBodega.class, id);
                urgenciasBodega.getUrgenciasBodegaPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The urgenciasBodega with id " + id + " no longer exists.", enfe);
            }
            PreexistenciaBodega preexistenciaBodega = urgenciasBodega.getPreexistenciaBodega();
            if (preexistenciaBodega != null) {
                preexistenciaBodega.getUrgenciasBodegaList().remove(urgenciasBodega);
                preexistenciaBodega = em.merge(preexistenciaBodega);
            }
            IpsBodega ipsKey = urgenciasBodega.getIpsKey();
            if (ipsKey != null) {
                ipsKey.getUrgenciasBodegaList().remove(urgenciasBodega);
                ipsKey = em.merge(ipsKey);
            }
            DiagnosticoBodega diagnosticoKey = urgenciasBodega.getDiagnosticoKey();
            if (diagnosticoKey != null) {
                diagnosticoKey.getUrgenciasBodegaList().remove(urgenciasBodega);
                diagnosticoKey = em.merge(diagnosticoKey);
            }
            ServicioPosBodega servicioPosKey = urgenciasBodega.getServicioPosKey();
            if (servicioPosKey != null) {
                servicioPosKey.getUrgenciasBodegaList().remove(urgenciasBodega);
                servicioPosKey = em.merge(servicioPosKey);
            }
            EmpresaBodega empresaBodega = urgenciasBodega.getEmpresaBodega();
            if (empresaBodega != null) {
                empresaBodega.getUrgenciasBodegaList().remove(urgenciasBodega);
                empresaBodega = em.merge(empresaBodega);
            }
            Dates dates = urgenciasBodega.getDates();
            if (dates != null) {
                dates.getUrgenciasBodegaList().remove(urgenciasBodega);
                dates = em.merge(dates);
            }
            DemografiaPacienteBodega demografiaPacienteBodega = urgenciasBodega.getDemografiaPacienteBodega();
            if (demografiaPacienteBodega != null) {
                demografiaPacienteBodega.getUrgenciasBodegaList().remove(urgenciasBodega);
                demografiaPacienteBodega = em.merge(demografiaPacienteBodega);
            }
            PacienteBodega pacienteBodega = urgenciasBodega.getPacienteBodega();
            if (pacienteBodega != null) {
                pacienteBodega.getUrgenciasBodegaList().remove(urgenciasBodega);
                pacienteBodega = em.merge(pacienteBodega);
            }
            em.remove(urgenciasBodega);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UrgenciasBodega> findUrgenciasBodegaEntities() {
        return findUrgenciasBodegaEntities(true, -1, -1);
    }

    public List<UrgenciasBodega> findUrgenciasBodegaEntities(int maxResults, int firstResult) {
        return findUrgenciasBodegaEntities(false, maxResults, firstResult);
    }

    private List<UrgenciasBodega> findUrgenciasBodegaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UrgenciasBodega.class));
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

    public UrgenciasBodega findUrgenciasBodega(UrgenciasBodegaPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UrgenciasBodega.class, id);
        } finally {
            em.close();
        }
    }

    public int getUrgenciasBodegaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UrgenciasBodega> rt = cq.from(UrgenciasBodega.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
