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
import Entidades_Bodega.IpsBodega;
import Entidades_Bodega.ServicioPosBodega;
import Entidades_Bodega.PreexistenciaBodega;
import Entidades_Bodega.DiagnosticoBodega;
import Entidades_Bodega.Dates;
import Entidades_Bodega.DemografiaPacienteBodega;
import Entidades_Bodega.PacienteBodega;
import Entidades_Bodega.MedicoBodega;
import Entidades_Bodega.RemisionesBodega;
import Entidades_Bodega.RemisionesBodegaPK;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author USER
 */
public class RemisionesBodegaJpaController implements Serializable {

    public RemisionesBodegaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(RemisionesBodega remisionesBodega) throws PreexistingEntityException, Exception {
        if (remisionesBodega.getRemisionesBodegaPK() == null) {
            remisionesBodega.setRemisionesBodegaPK(new RemisionesBodegaPK());
        }
        remisionesBodega.getRemisionesBodegaPK().setServicioPosKey(remisionesBodega.getServicioPosBodega().getServicioPosKey());
        remisionesBodega.getRemisionesBodegaPK().setMedicoKey(remisionesBodega.getMedicoBodega().getMedicoKey());
        remisionesBodega.getRemisionesBodegaPK().setPreexistenciaKey(remisionesBodega.getPreexistenciaBodega().getPreexistenciaKey());
        remisionesBodega.getRemisionesBodegaPK().setDiagnosticoKey(remisionesBodega.getDiagnosticoBodega().getDiagnosticoKey());
        remisionesBodega.getRemisionesBodegaPK().setIpsKey(remisionesBodega.getIpsBodega().getIpsKey());
        remisionesBodega.getRemisionesBodegaPK().setDemografiaPacienteKey(remisionesBodega.getDemografiaPacienteBodega().getDemografiaPacienteKey());
        remisionesBodega.getRemisionesBodegaPK().setFechaKey(remisionesBodega.getDates().getDateId());
        remisionesBodega.getRemisionesBodegaPK().setPacienteKey(remisionesBodega.getPacienteBodega().getPacienteKey());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            IpsBodega ipsBodega = remisionesBodega.getIpsBodega();
            if (ipsBodega != null) {
                ipsBodega = em.getReference(ipsBodega.getClass(), ipsBodega.getIpsKey());
                remisionesBodega.setIpsBodega(ipsBodega);
            }
            ServicioPosBodega servicioPosBodega = remisionesBodega.getServicioPosBodega();
            if (servicioPosBodega != null) {
                servicioPosBodega = em.getReference(servicioPosBodega.getClass(), servicioPosBodega.getServicioPosKey());
                remisionesBodega.setServicioPosBodega(servicioPosBodega);
            }
            PreexistenciaBodega preexistenciaBodega = remisionesBodega.getPreexistenciaBodega();
            if (preexistenciaBodega != null) {
                preexistenciaBodega = em.getReference(preexistenciaBodega.getClass(), preexistenciaBodega.getPreexistenciaKey());
                remisionesBodega.setPreexistenciaBodega(preexistenciaBodega);
            }
            DiagnosticoBodega diagnosticoBodega = remisionesBodega.getDiagnosticoBodega();
            if (diagnosticoBodega != null) {
                diagnosticoBodega = em.getReference(diagnosticoBodega.getClass(), diagnosticoBodega.getDiagnosticoKey());
                remisionesBodega.setDiagnosticoBodega(diagnosticoBodega);
            }
            Dates dates = remisionesBodega.getDates();
            if (dates != null) {
                dates = em.getReference(dates.getClass(), dates.getDateId());
                remisionesBodega.setDates(dates);
            }
            DemografiaPacienteBodega demografiaPacienteBodega = remisionesBodega.getDemografiaPacienteBodega();
            if (demografiaPacienteBodega != null) {
                demografiaPacienteBodega = em.getReference(demografiaPacienteBodega.getClass(), demografiaPacienteBodega.getDemografiaPacienteKey());
                remisionesBodega.setDemografiaPacienteBodega(demografiaPacienteBodega);
            }
            PacienteBodega pacienteBodega = remisionesBodega.getPacienteBodega();
            if (pacienteBodega != null) {
                pacienteBodega = em.getReference(pacienteBodega.getClass(), pacienteBodega.getPacienteKey());
                remisionesBodega.setPacienteBodega(pacienteBodega);
            }
            MedicoBodega medicoBodega = remisionesBodega.getMedicoBodega();
            if (medicoBodega != null) {
                medicoBodega = em.getReference(medicoBodega.getClass(), medicoBodega.getMedicoKey());
                remisionesBodega.setMedicoBodega(medicoBodega);
            }
            em.persist(remisionesBodega);
            if (ipsBodega != null) {
                ipsBodega.getRemisionesBodegaList().add(remisionesBodega);
                ipsBodega = em.merge(ipsBodega);
            }
            if (servicioPosBodega != null) {
                servicioPosBodega.getRemisionesBodegaList().add(remisionesBodega);
                servicioPosBodega = em.merge(servicioPosBodega);
            }
            if (preexistenciaBodega != null) {
                preexistenciaBodega.getRemisionesBodegaList().add(remisionesBodega);
                preexistenciaBodega = em.merge(preexistenciaBodega);
            }
            if (diagnosticoBodega != null) {
                diagnosticoBodega.getRemisionesBodegaList().add(remisionesBodega);
                diagnosticoBodega = em.merge(diagnosticoBodega);
            }
            if (dates != null) {
                dates.getRemisionesBodegaList().add(remisionesBodega);
                dates = em.merge(dates);
            }
            if (demografiaPacienteBodega != null) {
                demografiaPacienteBodega.getRemisionesBodegaList().add(remisionesBodega);
                demografiaPacienteBodega = em.merge(demografiaPacienteBodega);
            }
            if (pacienteBodega != null) {
                pacienteBodega.getRemisionesBodegaList().add(remisionesBodega);
                pacienteBodega = em.merge(pacienteBodega);
            }
            if (medicoBodega != null) {
                medicoBodega.getRemisionesBodegaList().add(remisionesBodega);
                medicoBodega = em.merge(medicoBodega);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findRemisionesBodega(remisionesBodega.getRemisionesBodegaPK()) != null) {
                throw new PreexistingEntityException("RemisionesBodega " + remisionesBodega + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(RemisionesBodega remisionesBodega) throws NonexistentEntityException, Exception {
        remisionesBodega.getRemisionesBodegaPK().setServicioPosKey(remisionesBodega.getServicioPosBodega().getServicioPosKey());
        remisionesBodega.getRemisionesBodegaPK().setMedicoKey(remisionesBodega.getMedicoBodega().getMedicoKey());
        remisionesBodega.getRemisionesBodegaPK().setPreexistenciaKey(remisionesBodega.getPreexistenciaBodega().getPreexistenciaKey());
        remisionesBodega.getRemisionesBodegaPK().setDiagnosticoKey(remisionesBodega.getDiagnosticoBodega().getDiagnosticoKey());
        remisionesBodega.getRemisionesBodegaPK().setIpsKey(remisionesBodega.getIpsBodega().getIpsKey());
        remisionesBodega.getRemisionesBodegaPK().setDemografiaPacienteKey(remisionesBodega.getDemografiaPacienteBodega().getDemografiaPacienteKey());
        remisionesBodega.getRemisionesBodegaPK().setFechaKey(remisionesBodega.getDates().getDateId());
        remisionesBodega.getRemisionesBodegaPK().setPacienteKey(remisionesBodega.getPacienteBodega().getPacienteKey());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            RemisionesBodega persistentRemisionesBodega = em.find(RemisionesBodega.class, remisionesBodega.getRemisionesBodegaPK());
            IpsBodega ipsBodegaOld = persistentRemisionesBodega.getIpsBodega();
            IpsBodega ipsBodegaNew = remisionesBodega.getIpsBodega();
            ServicioPosBodega servicioPosBodegaOld = persistentRemisionesBodega.getServicioPosBodega();
            ServicioPosBodega servicioPosBodegaNew = remisionesBodega.getServicioPosBodega();
            PreexistenciaBodega preexistenciaBodegaOld = persistentRemisionesBodega.getPreexistenciaBodega();
            PreexistenciaBodega preexistenciaBodegaNew = remisionesBodega.getPreexistenciaBodega();
            DiagnosticoBodega diagnosticoBodegaOld = persistentRemisionesBodega.getDiagnosticoBodega();
            DiagnosticoBodega diagnosticoBodegaNew = remisionesBodega.getDiagnosticoBodega();
            Dates datesOld = persistentRemisionesBodega.getDates();
            Dates datesNew = remisionesBodega.getDates();
            DemografiaPacienteBodega demografiaPacienteBodegaOld = persistentRemisionesBodega.getDemografiaPacienteBodega();
            DemografiaPacienteBodega demografiaPacienteBodegaNew = remisionesBodega.getDemografiaPacienteBodega();
            PacienteBodega pacienteBodegaOld = persistentRemisionesBodega.getPacienteBodega();
            PacienteBodega pacienteBodegaNew = remisionesBodega.getPacienteBodega();
            MedicoBodega medicoBodegaOld = persistentRemisionesBodega.getMedicoBodega();
            MedicoBodega medicoBodegaNew = remisionesBodega.getMedicoBodega();
            if (ipsBodegaNew != null) {
                ipsBodegaNew = em.getReference(ipsBodegaNew.getClass(), ipsBodegaNew.getIpsKey());
                remisionesBodega.setIpsBodega(ipsBodegaNew);
            }
            if (servicioPosBodegaNew != null) {
                servicioPosBodegaNew = em.getReference(servicioPosBodegaNew.getClass(), servicioPosBodegaNew.getServicioPosKey());
                remisionesBodega.setServicioPosBodega(servicioPosBodegaNew);
            }
            if (preexistenciaBodegaNew != null) {
                preexistenciaBodegaNew = em.getReference(preexistenciaBodegaNew.getClass(), preexistenciaBodegaNew.getPreexistenciaKey());
                remisionesBodega.setPreexistenciaBodega(preexistenciaBodegaNew);
            }
            if (diagnosticoBodegaNew != null) {
                diagnosticoBodegaNew = em.getReference(diagnosticoBodegaNew.getClass(), diagnosticoBodegaNew.getDiagnosticoKey());
                remisionesBodega.setDiagnosticoBodega(diagnosticoBodegaNew);
            }
            if (datesNew != null) {
                datesNew = em.getReference(datesNew.getClass(), datesNew.getDateId());
                remisionesBodega.setDates(datesNew);
            }
            if (demografiaPacienteBodegaNew != null) {
                demografiaPacienteBodegaNew = em.getReference(demografiaPacienteBodegaNew.getClass(), demografiaPacienteBodegaNew.getDemografiaPacienteKey());
                remisionesBodega.setDemografiaPacienteBodega(demografiaPacienteBodegaNew);
            }
            if (pacienteBodegaNew != null) {
                pacienteBodegaNew = em.getReference(pacienteBodegaNew.getClass(), pacienteBodegaNew.getPacienteKey());
                remisionesBodega.setPacienteBodega(pacienteBodegaNew);
            }
            if (medicoBodegaNew != null) {
                medicoBodegaNew = em.getReference(medicoBodegaNew.getClass(), medicoBodegaNew.getMedicoKey());
                remisionesBodega.setMedicoBodega(medicoBodegaNew);
            }
            remisionesBodega = em.merge(remisionesBodega);
            if (ipsBodegaOld != null && !ipsBodegaOld.equals(ipsBodegaNew)) {
                ipsBodegaOld.getRemisionesBodegaList().remove(remisionesBodega);
                ipsBodegaOld = em.merge(ipsBodegaOld);
            }
            if (ipsBodegaNew != null && !ipsBodegaNew.equals(ipsBodegaOld)) {
                ipsBodegaNew.getRemisionesBodegaList().add(remisionesBodega);
                ipsBodegaNew = em.merge(ipsBodegaNew);
            }
            if (servicioPosBodegaOld != null && !servicioPosBodegaOld.equals(servicioPosBodegaNew)) {
                servicioPosBodegaOld.getRemisionesBodegaList().remove(remisionesBodega);
                servicioPosBodegaOld = em.merge(servicioPosBodegaOld);
            }
            if (servicioPosBodegaNew != null && !servicioPosBodegaNew.equals(servicioPosBodegaOld)) {
                servicioPosBodegaNew.getRemisionesBodegaList().add(remisionesBodega);
                servicioPosBodegaNew = em.merge(servicioPosBodegaNew);
            }
            if (preexistenciaBodegaOld != null && !preexistenciaBodegaOld.equals(preexistenciaBodegaNew)) {
                preexistenciaBodegaOld.getRemisionesBodegaList().remove(remisionesBodega);
                preexistenciaBodegaOld = em.merge(preexistenciaBodegaOld);
            }
            if (preexistenciaBodegaNew != null && !preexistenciaBodegaNew.equals(preexistenciaBodegaOld)) {
                preexistenciaBodegaNew.getRemisionesBodegaList().add(remisionesBodega);
                preexistenciaBodegaNew = em.merge(preexistenciaBodegaNew);
            }
            if (diagnosticoBodegaOld != null && !diagnosticoBodegaOld.equals(diagnosticoBodegaNew)) {
                diagnosticoBodegaOld.getRemisionesBodegaList().remove(remisionesBodega);
                diagnosticoBodegaOld = em.merge(diagnosticoBodegaOld);
            }
            if (diagnosticoBodegaNew != null && !diagnosticoBodegaNew.equals(diagnosticoBodegaOld)) {
                diagnosticoBodegaNew.getRemisionesBodegaList().add(remisionesBodega);
                diagnosticoBodegaNew = em.merge(diagnosticoBodegaNew);
            }
            if (datesOld != null && !datesOld.equals(datesNew)) {
                datesOld.getRemisionesBodegaList().remove(remisionesBodega);
                datesOld = em.merge(datesOld);
            }
            if (datesNew != null && !datesNew.equals(datesOld)) {
                datesNew.getRemisionesBodegaList().add(remisionesBodega);
                datesNew = em.merge(datesNew);
            }
            if (demografiaPacienteBodegaOld != null && !demografiaPacienteBodegaOld.equals(demografiaPacienteBodegaNew)) {
                demografiaPacienteBodegaOld.getRemisionesBodegaList().remove(remisionesBodega);
                demografiaPacienteBodegaOld = em.merge(demografiaPacienteBodegaOld);
            }
            if (demografiaPacienteBodegaNew != null && !demografiaPacienteBodegaNew.equals(demografiaPacienteBodegaOld)) {
                demografiaPacienteBodegaNew.getRemisionesBodegaList().add(remisionesBodega);
                demografiaPacienteBodegaNew = em.merge(demografiaPacienteBodegaNew);
            }
            if (pacienteBodegaOld != null && !pacienteBodegaOld.equals(pacienteBodegaNew)) {
                pacienteBodegaOld.getRemisionesBodegaList().remove(remisionesBodega);
                pacienteBodegaOld = em.merge(pacienteBodegaOld);
            }
            if (pacienteBodegaNew != null && !pacienteBodegaNew.equals(pacienteBodegaOld)) {
                pacienteBodegaNew.getRemisionesBodegaList().add(remisionesBodega);
                pacienteBodegaNew = em.merge(pacienteBodegaNew);
            }
            if (medicoBodegaOld != null && !medicoBodegaOld.equals(medicoBodegaNew)) {
                medicoBodegaOld.getRemisionesBodegaList().remove(remisionesBodega);
                medicoBodegaOld = em.merge(medicoBodegaOld);
            }
            if (medicoBodegaNew != null && !medicoBodegaNew.equals(medicoBodegaOld)) {
                medicoBodegaNew.getRemisionesBodegaList().add(remisionesBodega);
                medicoBodegaNew = em.merge(medicoBodegaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                RemisionesBodegaPK id = remisionesBodega.getRemisionesBodegaPK();
                if (findRemisionesBodega(id) == null) {
                    throw new NonexistentEntityException("The remisionesBodega with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(RemisionesBodegaPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            RemisionesBodega remisionesBodega;
            try {
                remisionesBodega = em.getReference(RemisionesBodega.class, id);
                remisionesBodega.getRemisionesBodegaPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The remisionesBodega with id " + id + " no longer exists.", enfe);
            }
            IpsBodega ipsBodega = remisionesBodega.getIpsBodega();
            if (ipsBodega != null) {
                ipsBodega.getRemisionesBodegaList().remove(remisionesBodega);
                ipsBodega = em.merge(ipsBodega);
            }
            ServicioPosBodega servicioPosBodega = remisionesBodega.getServicioPosBodega();
            if (servicioPosBodega != null) {
                servicioPosBodega.getRemisionesBodegaList().remove(remisionesBodega);
                servicioPosBodega = em.merge(servicioPosBodega);
            }
            PreexistenciaBodega preexistenciaBodega = remisionesBodega.getPreexistenciaBodega();
            if (preexistenciaBodega != null) {
                preexistenciaBodega.getRemisionesBodegaList().remove(remisionesBodega);
                preexistenciaBodega = em.merge(preexistenciaBodega);
            }
            DiagnosticoBodega diagnosticoBodega = remisionesBodega.getDiagnosticoBodega();
            if (diagnosticoBodega != null) {
                diagnosticoBodega.getRemisionesBodegaList().remove(remisionesBodega);
                diagnosticoBodega = em.merge(diagnosticoBodega);
            }
            Dates dates = remisionesBodega.getDates();
            if (dates != null) {
                dates.getRemisionesBodegaList().remove(remisionesBodega);
                dates = em.merge(dates);
            }
            DemografiaPacienteBodega demografiaPacienteBodega = remisionesBodega.getDemografiaPacienteBodega();
            if (demografiaPacienteBodega != null) {
                demografiaPacienteBodega.getRemisionesBodegaList().remove(remisionesBodega);
                demografiaPacienteBodega = em.merge(demografiaPacienteBodega);
            }
            PacienteBodega pacienteBodega = remisionesBodega.getPacienteBodega();
            if (pacienteBodega != null) {
                pacienteBodega.getRemisionesBodegaList().remove(remisionesBodega);
                pacienteBodega = em.merge(pacienteBodega);
            }
            MedicoBodega medicoBodega = remisionesBodega.getMedicoBodega();
            if (medicoBodega != null) {
                medicoBodega.getRemisionesBodegaList().remove(remisionesBodega);
                medicoBodega = em.merge(medicoBodega);
            }
            em.remove(remisionesBodega);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<RemisionesBodega> findRemisionesBodegaEntities() {
        return findRemisionesBodegaEntities(true, -1, -1);
    }

    public List<RemisionesBodega> findRemisionesBodegaEntities(int maxResults, int firstResult) {
        return findRemisionesBodegaEntities(false, maxResults, firstResult);
    }

    private List<RemisionesBodega> findRemisionesBodegaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(RemisionesBodega.class));
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

    public RemisionesBodega findRemisionesBodega(RemisionesBodegaPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(RemisionesBodega.class, id);
        } finally {
            em.close();
        }
    }

    public int getRemisionesBodegaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<RemisionesBodega> rt = cq.from(RemisionesBodega.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
