/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladorJPABodega;

import ControladorJPABodega.exceptions.NonexistentEntityException;
import ControladorJPABodega.exceptions.PreexistingEntityException;
import Entidades_Bodega.CitasBodega;
import Entidades_Bodega.CitasBodegaPK;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entidades_Bodega.IpsBodega;
import Entidades_Bodega.PreexistenciaBodega;
import Entidades_Bodega.DiagnosticoBodega;
import Entidades_Bodega.Dates;
import Entidades_Bodega.DemografiaPacienteBodega;
import Entidades_Bodega.PacienteBodega;
import Entidades_Bodega.MedicoBodega;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author USER
 */
public class CitasBodegaJpaController implements Serializable {

    public CitasBodegaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CitasBodega citasBodega) throws PreexistingEntityException, Exception {
        if (citasBodega.getCitasBodegaPK() == null) {
            citasBodega.setCitasBodegaPK(new CitasBodegaPK());
        }
        citasBodega.getCitasBodegaPK().setPreexistenciaKey(citasBodega.getPreexistenciaBodega().getPreexistenciaKey());
        citasBodega.getCitasBodegaPK().setDemografiaPacienteKey(citasBodega.getDemografiaPacienteBodega().getDemografiaPacienteKey());
        citasBodega.getCitasBodegaPK().setPacienteKey(citasBodega.getPacienteBodega().getPacienteKey());
        citasBodega.getCitasBodegaPK().setMedicoKey(citasBodega.getMedicoBodega().getMedicoKey());
        citasBodega.getCitasBodegaPK().setFechaAtencionKey(citasBodega.getDates().getDateId());
        citasBodega.getCitasBodegaPK().setDiagnosticoKey(citasBodega.getDiagnosticoBodega().getDiagnosticoKey());
        citasBodega.getCitasBodegaPK().setIpsKey(citasBodega.getIpsBodega().getIpsKey());
        citasBodega.getCitasBodegaPK().setFechaSolicitudKey(citasBodega.getDates1().getDateId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            IpsBodega ipsBodega = citasBodega.getIpsBodega();
            if (ipsBodega != null) {
                ipsBodega = em.getReference(ipsBodega.getClass(), ipsBodega.getIpsKey());
                citasBodega.setIpsBodega(ipsBodega);
            }
            PreexistenciaBodega preexistenciaBodega = citasBodega.getPreexistenciaBodega();
            if (preexistenciaBodega != null) {
                preexistenciaBodega = em.getReference(preexistenciaBodega.getClass(), preexistenciaBodega.getPreexistenciaKey());
                citasBodega.setPreexistenciaBodega(preexistenciaBodega);
            }
            DiagnosticoBodega diagnosticoBodega = citasBodega.getDiagnosticoBodega();
            if (diagnosticoBodega != null) {
                diagnosticoBodega = em.getReference(diagnosticoBodega.getClass(), diagnosticoBodega.getDiagnosticoKey());
                citasBodega.setDiagnosticoBodega(diagnosticoBodega);
            }
            Dates dates = citasBodega.getDates();
            if (dates != null) {
                dates = em.getReference(dates.getClass(), dates.getDateId());
                citasBodega.setDates(dates);
            }
            Dates dates1 = citasBodega.getDates1();
            if (dates1 != null) {
                dates1 = em.getReference(dates1.getClass(), dates1.getDateId());
                citasBodega.setDates1(dates1);
            }
            DemografiaPacienteBodega demografiaPacienteBodega = citasBodega.getDemografiaPacienteBodega();
            if (demografiaPacienteBodega != null) {
                demografiaPacienteBodega = em.getReference(demografiaPacienteBodega.getClass(), demografiaPacienteBodega.getDemografiaPacienteKey());
                citasBodega.setDemografiaPacienteBodega(demografiaPacienteBodega);
            }
            PacienteBodega pacienteBodega = citasBodega.getPacienteBodega();
            if (pacienteBodega != null) {
                pacienteBodega = em.getReference(pacienteBodega.getClass(), pacienteBodega.getPacienteKey());
                citasBodega.setPacienteBodega(pacienteBodega);
            }
            MedicoBodega medicoBodega = citasBodega.getMedicoBodega();
            if (medicoBodega != null) {
                medicoBodega = em.getReference(medicoBodega.getClass(), medicoBodega.getMedicoKey());
                citasBodega.setMedicoBodega(medicoBodega);
            }
            em.persist(citasBodega);
            if (ipsBodega != null) {
                ipsBodega.getCitasBodegaList().add(citasBodega);
                ipsBodega = em.merge(ipsBodega);
            }
            if (preexistenciaBodega != null) {
                preexistenciaBodega.getCitasBodegaList().add(citasBodega);
                preexistenciaBodega = em.merge(preexistenciaBodega);
            }
            if (diagnosticoBodega != null) {
                diagnosticoBodega.getCitasBodegaList().add(citasBodega);
                diagnosticoBodega = em.merge(diagnosticoBodega);
            }
            if (dates != null) {
                dates.getCitasBodegaList().add(citasBodega);
                dates = em.merge(dates);
            }
            if (dates1 != null) {
                dates1.getCitasBodegaList().add(citasBodega);
                dates1 = em.merge(dates1);
            }
            if (demografiaPacienteBodega != null) {
                demografiaPacienteBodega.getCitasBodegaList().add(citasBodega);
                demografiaPacienteBodega = em.merge(demografiaPacienteBodega);
            }
            if (pacienteBodega != null) {
                pacienteBodega.getCitasBodegaList().add(citasBodega);
                pacienteBodega = em.merge(pacienteBodega);
            }
            if (medicoBodega != null) {
                medicoBodega.getCitasBodegaList().add(citasBodega);
                medicoBodega = em.merge(medicoBodega);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCitasBodega(citasBodega.getCitasBodegaPK()) != null) {
                throw new PreexistingEntityException("CitasBodega " + citasBodega + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CitasBodega citasBodega) throws NonexistentEntityException, Exception {
        citasBodega.getCitasBodegaPK().setPreexistenciaKey(citasBodega.getPreexistenciaBodega().getPreexistenciaKey());
        citasBodega.getCitasBodegaPK().setDemografiaPacienteKey(citasBodega.getDemografiaPacienteBodega().getDemografiaPacienteKey());
        citasBodega.getCitasBodegaPK().setPacienteKey(citasBodega.getPacienteBodega().getPacienteKey());
        citasBodega.getCitasBodegaPK().setMedicoKey(citasBodega.getMedicoBodega().getMedicoKey());
        citasBodega.getCitasBodegaPK().setFechaAtencionKey(citasBodega.getDates().getDateId());
        citasBodega.getCitasBodegaPK().setDiagnosticoKey(citasBodega.getDiagnosticoBodega().getDiagnosticoKey());
        citasBodega.getCitasBodegaPK().setIpsKey(citasBodega.getIpsBodega().getIpsKey());
        citasBodega.getCitasBodegaPK().setFechaSolicitudKey(citasBodega.getDates1().getDateId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CitasBodega persistentCitasBodega = em.find(CitasBodega.class, citasBodega.getCitasBodegaPK());
            IpsBodega ipsBodegaOld = persistentCitasBodega.getIpsBodega();
            IpsBodega ipsBodegaNew = citasBodega.getIpsBodega();
            PreexistenciaBodega preexistenciaBodegaOld = persistentCitasBodega.getPreexistenciaBodega();
            PreexistenciaBodega preexistenciaBodegaNew = citasBodega.getPreexistenciaBodega();
            DiagnosticoBodega diagnosticoBodegaOld = persistentCitasBodega.getDiagnosticoBodega();
            DiagnosticoBodega diagnosticoBodegaNew = citasBodega.getDiagnosticoBodega();
            Dates datesOld = persistentCitasBodega.getDates();
            Dates datesNew = citasBodega.getDates();
            Dates dates1Old = persistentCitasBodega.getDates1();
            Dates dates1New = citasBodega.getDates1();
            DemografiaPacienteBodega demografiaPacienteBodegaOld = persistentCitasBodega.getDemografiaPacienteBodega();
            DemografiaPacienteBodega demografiaPacienteBodegaNew = citasBodega.getDemografiaPacienteBodega();
            PacienteBodega pacienteBodegaOld = persistentCitasBodega.getPacienteBodega();
            PacienteBodega pacienteBodegaNew = citasBodega.getPacienteBodega();
            MedicoBodega medicoBodegaOld = persistentCitasBodega.getMedicoBodega();
            MedicoBodega medicoBodegaNew = citasBodega.getMedicoBodega();
            if (ipsBodegaNew != null) {
                ipsBodegaNew = em.getReference(ipsBodegaNew.getClass(), ipsBodegaNew.getIpsKey());
                citasBodega.setIpsBodega(ipsBodegaNew);
            }
            if (preexistenciaBodegaNew != null) {
                preexistenciaBodegaNew = em.getReference(preexistenciaBodegaNew.getClass(), preexistenciaBodegaNew.getPreexistenciaKey());
                citasBodega.setPreexistenciaBodega(preexistenciaBodegaNew);
            }
            if (diagnosticoBodegaNew != null) {
                diagnosticoBodegaNew = em.getReference(diagnosticoBodegaNew.getClass(), diagnosticoBodegaNew.getDiagnosticoKey());
                citasBodega.setDiagnosticoBodega(diagnosticoBodegaNew);
            }
            if (datesNew != null) {
                datesNew = em.getReference(datesNew.getClass(), datesNew.getDateId());
                citasBodega.setDates(datesNew);
            }
            if (dates1New != null) {
                dates1New = em.getReference(dates1New.getClass(), dates1New.getDateId());
                citasBodega.setDates1(dates1New);
            }
            if (demografiaPacienteBodegaNew != null) {
                demografiaPacienteBodegaNew = em.getReference(demografiaPacienteBodegaNew.getClass(), demografiaPacienteBodegaNew.getDemografiaPacienteKey());
                citasBodega.setDemografiaPacienteBodega(demografiaPacienteBodegaNew);
            }
            if (pacienteBodegaNew != null) {
                pacienteBodegaNew = em.getReference(pacienteBodegaNew.getClass(), pacienteBodegaNew.getPacienteKey());
                citasBodega.setPacienteBodega(pacienteBodegaNew);
            }
            if (medicoBodegaNew != null) {
                medicoBodegaNew = em.getReference(medicoBodegaNew.getClass(), medicoBodegaNew.getMedicoKey());
                citasBodega.setMedicoBodega(medicoBodegaNew);
            }
            citasBodega = em.merge(citasBodega);
            if (ipsBodegaOld != null && !ipsBodegaOld.equals(ipsBodegaNew)) {
                ipsBodegaOld.getCitasBodegaList().remove(citasBodega);
                ipsBodegaOld = em.merge(ipsBodegaOld);
            }
            if (ipsBodegaNew != null && !ipsBodegaNew.equals(ipsBodegaOld)) {
                ipsBodegaNew.getCitasBodegaList().add(citasBodega);
                ipsBodegaNew = em.merge(ipsBodegaNew);
            }
            if (preexistenciaBodegaOld != null && !preexistenciaBodegaOld.equals(preexistenciaBodegaNew)) {
                preexistenciaBodegaOld.getCitasBodegaList().remove(citasBodega);
                preexistenciaBodegaOld = em.merge(preexistenciaBodegaOld);
            }
            if (preexistenciaBodegaNew != null && !preexistenciaBodegaNew.equals(preexistenciaBodegaOld)) {
                preexistenciaBodegaNew.getCitasBodegaList().add(citasBodega);
                preexistenciaBodegaNew = em.merge(preexistenciaBodegaNew);
            }
            if (diagnosticoBodegaOld != null && !diagnosticoBodegaOld.equals(diagnosticoBodegaNew)) {
                diagnosticoBodegaOld.getCitasBodegaList().remove(citasBodega);
                diagnosticoBodegaOld = em.merge(diagnosticoBodegaOld);
            }
            if (diagnosticoBodegaNew != null && !diagnosticoBodegaNew.equals(diagnosticoBodegaOld)) {
                diagnosticoBodegaNew.getCitasBodegaList().add(citasBodega);
                diagnosticoBodegaNew = em.merge(diagnosticoBodegaNew);
            }
            if (datesOld != null && !datesOld.equals(datesNew)) {
                datesOld.getCitasBodegaList().remove(citasBodega);
                datesOld = em.merge(datesOld);
            }
            if (datesNew != null && !datesNew.equals(datesOld)) {
                datesNew.getCitasBodegaList().add(citasBodega);
                datesNew = em.merge(datesNew);
            }
            if (dates1Old != null && !dates1Old.equals(dates1New)) {
                dates1Old.getCitasBodegaList().remove(citasBodega);
                dates1Old = em.merge(dates1Old);
            }
            if (dates1New != null && !dates1New.equals(dates1Old)) {
                dates1New.getCitasBodegaList().add(citasBodega);
                dates1New = em.merge(dates1New);
            }
            if (demografiaPacienteBodegaOld != null && !demografiaPacienteBodegaOld.equals(demografiaPacienteBodegaNew)) {
                demografiaPacienteBodegaOld.getCitasBodegaList().remove(citasBodega);
                demografiaPacienteBodegaOld = em.merge(demografiaPacienteBodegaOld);
            }
            if (demografiaPacienteBodegaNew != null && !demografiaPacienteBodegaNew.equals(demografiaPacienteBodegaOld)) {
                demografiaPacienteBodegaNew.getCitasBodegaList().add(citasBodega);
                demografiaPacienteBodegaNew = em.merge(demografiaPacienteBodegaNew);
            }
            if (pacienteBodegaOld != null && !pacienteBodegaOld.equals(pacienteBodegaNew)) {
                pacienteBodegaOld.getCitasBodegaList().remove(citasBodega);
                pacienteBodegaOld = em.merge(pacienteBodegaOld);
            }
            if (pacienteBodegaNew != null && !pacienteBodegaNew.equals(pacienteBodegaOld)) {
                pacienteBodegaNew.getCitasBodegaList().add(citasBodega);
                pacienteBodegaNew = em.merge(pacienteBodegaNew);
            }
            if (medicoBodegaOld != null && !medicoBodegaOld.equals(medicoBodegaNew)) {
                medicoBodegaOld.getCitasBodegaList().remove(citasBodega);
                medicoBodegaOld = em.merge(medicoBodegaOld);
            }
            if (medicoBodegaNew != null && !medicoBodegaNew.equals(medicoBodegaOld)) {
                medicoBodegaNew.getCitasBodegaList().add(citasBodega);
                medicoBodegaNew = em.merge(medicoBodegaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                CitasBodegaPK id = citasBodega.getCitasBodegaPK();
                if (findCitasBodega(id) == null) {
                    throw new NonexistentEntityException("The citasBodega with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(CitasBodegaPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CitasBodega citasBodega;
            try {
                citasBodega = em.getReference(CitasBodega.class, id);
                citasBodega.getCitasBodegaPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The citasBodega with id " + id + " no longer exists.", enfe);
            }
            IpsBodega ipsBodega = citasBodega.getIpsBodega();
            if (ipsBodega != null) {
                ipsBodega.getCitasBodegaList().remove(citasBodega);
                ipsBodega = em.merge(ipsBodega);
            }
            PreexistenciaBodega preexistenciaBodega = citasBodega.getPreexistenciaBodega();
            if (preexistenciaBodega != null) {
                preexistenciaBodega.getCitasBodegaList().remove(citasBodega);
                preexistenciaBodega = em.merge(preexistenciaBodega);
            }
            DiagnosticoBodega diagnosticoBodega = citasBodega.getDiagnosticoBodega();
            if (diagnosticoBodega != null) {
                diagnosticoBodega.getCitasBodegaList().remove(citasBodega);
                diagnosticoBodega = em.merge(diagnosticoBodega);
            }
            Dates dates = citasBodega.getDates();
            if (dates != null) {
                dates.getCitasBodegaList().remove(citasBodega);
                dates = em.merge(dates);
            }
            Dates dates1 = citasBodega.getDates1();
            if (dates1 != null) {
                dates1.getCitasBodegaList().remove(citasBodega);
                dates1 = em.merge(dates1);
            }
            DemografiaPacienteBodega demografiaPacienteBodega = citasBodega.getDemografiaPacienteBodega();
            if (demografiaPacienteBodega != null) {
                demografiaPacienteBodega.getCitasBodegaList().remove(citasBodega);
                demografiaPacienteBodega = em.merge(demografiaPacienteBodega);
            }
            PacienteBodega pacienteBodega = citasBodega.getPacienteBodega();
            if (pacienteBodega != null) {
                pacienteBodega.getCitasBodegaList().remove(citasBodega);
                pacienteBodega = em.merge(pacienteBodega);
            }
            MedicoBodega medicoBodega = citasBodega.getMedicoBodega();
            if (medicoBodega != null) {
                medicoBodega.getCitasBodegaList().remove(citasBodega);
                medicoBodega = em.merge(medicoBodega);
            }
            em.remove(citasBodega);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CitasBodega> findCitasBodegaEntities() {
        return findCitasBodegaEntities(true, -1, -1);
    }

    public List<CitasBodega> findCitasBodegaEntities(int maxResults, int firstResult) {
        return findCitasBodegaEntities(false, maxResults, firstResult);
    }

    private List<CitasBodega> findCitasBodegaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CitasBodega.class));
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

    public CitasBodega findCitasBodega(CitasBodegaPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CitasBodega.class, id);
        } finally {
            em.close();
        }
    }

    public int getCitasBodegaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CitasBodega> rt = cq.from(CitasBodega.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
