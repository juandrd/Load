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
import Entidades_Bodega.MedicamentoBodega;
import Entidades_Bodega.MedicoBodega;
import Entidades_Bodega.Dates;
import Entidades_Bodega.DemografiaPacienteBodega;
import Entidades_Bodega.FormulasBodega;
import Entidades_Bodega.FormulasBodegaPK;
import Entidades_Bodega.PacienteBodega;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author USER
 */
public class FormulasBodegaJpaController implements Serializable {

    public FormulasBodegaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(FormulasBodega formulasBodega) throws PreexistingEntityException, Exception {
        if (formulasBodega.getFormulasBodegaPK() == null) {
            formulasBodega.setFormulasBodegaPK(new FormulasBodegaPK());
        }
        formulasBodega.getFormulasBodegaPK().setPacienteKey(formulasBodega.getPacienteBodega().getPacienteKey());
        formulasBodega.getFormulasBodegaPK().setMedicamentoKey(formulasBodega.getMedicamentoBodega().getMedicamentoKey());
        formulasBodega.getFormulasBodegaPK().setFechaKey(formulasBodega.getDates().getDateId());
        formulasBodega.getFormulasBodegaPK().setDemografiaPacienteKey(formulasBodega.getDemografiaPacienteBodega().getDemografiaPacienteKey());
        formulasBodega.getFormulasBodegaPK().setMedicoKey(formulasBodega.getMedicoBodega().getMedicoKey());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            MedicamentoBodega medicamentoBodega = formulasBodega.getMedicamentoBodega();
            if (medicamentoBodega != null) {
                medicamentoBodega = em.getReference(medicamentoBodega.getClass(), medicamentoBodega.getMedicamentoKey());
                formulasBodega.setMedicamentoBodega(medicamentoBodega);
            }
            MedicoBodega medicoBodega = formulasBodega.getMedicoBodega();
            if (medicoBodega != null) {
                medicoBodega = em.getReference(medicoBodega.getClass(), medicoBodega.getMedicoKey());
                formulasBodega.setMedicoBodega(medicoBodega);
            }
            Dates dates = formulasBodega.getDates();
            if (dates != null) {
                dates = em.getReference(dates.getClass(), dates.getDateId());
                formulasBodega.setDates(dates);
            }
            DemografiaPacienteBodega demografiaPacienteBodega = formulasBodega.getDemografiaPacienteBodega();
            if (demografiaPacienteBodega != null) {
                demografiaPacienteBodega = em.getReference(demografiaPacienteBodega.getClass(), demografiaPacienteBodega.getDemografiaPacienteKey());
                formulasBodega.setDemografiaPacienteBodega(demografiaPacienteBodega);
            }
            PacienteBodega pacienteBodega = formulasBodega.getPacienteBodega();
            if (pacienteBodega != null) {
                pacienteBodega = em.getReference(pacienteBodega.getClass(), pacienteBodega.getPacienteKey());
                formulasBodega.setPacienteBodega(pacienteBodega);
            }
            em.persist(formulasBodega);
            if (medicamentoBodega != null) {
                medicamentoBodega.getFormulasBodegaList().add(formulasBodega);
                medicamentoBodega = em.merge(medicamentoBodega);
            }
            if (medicoBodega != null) {
                medicoBodega.getFormulasBodegaList().add(formulasBodega);
                medicoBodega = em.merge(medicoBodega);
            }
            if (dates != null) {
                dates.getFormulasBodegaList().add(formulasBodega);
                dates = em.merge(dates);
            }
            if (demografiaPacienteBodega != null) {
                demografiaPacienteBodega.getFormulasBodegaList().add(formulasBodega);
                demografiaPacienteBodega = em.merge(demografiaPacienteBodega);
            }
            if (pacienteBodega != null) {
                pacienteBodega.getFormulasBodegaList().add(formulasBodega);
                pacienteBodega = em.merge(pacienteBodega);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findFormulasBodega(formulasBodega.getFormulasBodegaPK()) != null) {
                throw new PreexistingEntityException("FormulasBodega " + formulasBodega + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(FormulasBodega formulasBodega) throws NonexistentEntityException, Exception {
        formulasBodega.getFormulasBodegaPK().setPacienteKey(formulasBodega.getPacienteBodega().getPacienteKey());
        formulasBodega.getFormulasBodegaPK().setMedicamentoKey(formulasBodega.getMedicamentoBodega().getMedicamentoKey());
        formulasBodega.getFormulasBodegaPK().setFechaKey(formulasBodega.getDates().getDateId());
        formulasBodega.getFormulasBodegaPK().setDemografiaPacienteKey(formulasBodega.getDemografiaPacienteBodega().getDemografiaPacienteKey());
        formulasBodega.getFormulasBodegaPK().setMedicoKey(formulasBodega.getMedicoBodega().getMedicoKey());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            FormulasBodega persistentFormulasBodega = em.find(FormulasBodega.class, formulasBodega.getFormulasBodegaPK());
            MedicamentoBodega medicamentoBodegaOld = persistentFormulasBodega.getMedicamentoBodega();
            MedicamentoBodega medicamentoBodegaNew = formulasBodega.getMedicamentoBodega();
            MedicoBodega medicoBodegaOld = persistentFormulasBodega.getMedicoBodega();
            MedicoBodega medicoBodegaNew = formulasBodega.getMedicoBodega();
            Dates datesOld = persistentFormulasBodega.getDates();
            Dates datesNew = formulasBodega.getDates();
            DemografiaPacienteBodega demografiaPacienteBodegaOld = persistentFormulasBodega.getDemografiaPacienteBodega();
            DemografiaPacienteBodega demografiaPacienteBodegaNew = formulasBodega.getDemografiaPacienteBodega();
            PacienteBodega pacienteBodegaOld = persistentFormulasBodega.getPacienteBodega();
            PacienteBodega pacienteBodegaNew = formulasBodega.getPacienteBodega();
            if (medicamentoBodegaNew != null) {
                medicamentoBodegaNew = em.getReference(medicamentoBodegaNew.getClass(), medicamentoBodegaNew.getMedicamentoKey());
                formulasBodega.setMedicamentoBodega(medicamentoBodegaNew);
            }
            if (medicoBodegaNew != null) {
                medicoBodegaNew = em.getReference(medicoBodegaNew.getClass(), medicoBodegaNew.getMedicoKey());
                formulasBodega.setMedicoBodega(medicoBodegaNew);
            }
            if (datesNew != null) {
                datesNew = em.getReference(datesNew.getClass(), datesNew.getDateId());
                formulasBodega.setDates(datesNew);
            }
            if (demografiaPacienteBodegaNew != null) {
                demografiaPacienteBodegaNew = em.getReference(demografiaPacienteBodegaNew.getClass(), demografiaPacienteBodegaNew.getDemografiaPacienteKey());
                formulasBodega.setDemografiaPacienteBodega(demografiaPacienteBodegaNew);
            }
            if (pacienteBodegaNew != null) {
                pacienteBodegaNew = em.getReference(pacienteBodegaNew.getClass(), pacienteBodegaNew.getPacienteKey());
                formulasBodega.setPacienteBodega(pacienteBodegaNew);
            }
            formulasBodega = em.merge(formulasBodega);
            if (medicamentoBodegaOld != null && !medicamentoBodegaOld.equals(medicamentoBodegaNew)) {
                medicamentoBodegaOld.getFormulasBodegaList().remove(formulasBodega);
                medicamentoBodegaOld = em.merge(medicamentoBodegaOld);
            }
            if (medicamentoBodegaNew != null && !medicamentoBodegaNew.equals(medicamentoBodegaOld)) {
                medicamentoBodegaNew.getFormulasBodegaList().add(formulasBodega);
                medicamentoBodegaNew = em.merge(medicamentoBodegaNew);
            }
            if (medicoBodegaOld != null && !medicoBodegaOld.equals(medicoBodegaNew)) {
                medicoBodegaOld.getFormulasBodegaList().remove(formulasBodega);
                medicoBodegaOld = em.merge(medicoBodegaOld);
            }
            if (medicoBodegaNew != null && !medicoBodegaNew.equals(medicoBodegaOld)) {
                medicoBodegaNew.getFormulasBodegaList().add(formulasBodega);
                medicoBodegaNew = em.merge(medicoBodegaNew);
            }
            if (datesOld != null && !datesOld.equals(datesNew)) {
                datesOld.getFormulasBodegaList().remove(formulasBodega);
                datesOld = em.merge(datesOld);
            }
            if (datesNew != null && !datesNew.equals(datesOld)) {
                datesNew.getFormulasBodegaList().add(formulasBodega);
                datesNew = em.merge(datesNew);
            }
            if (demografiaPacienteBodegaOld != null && !demografiaPacienteBodegaOld.equals(demografiaPacienteBodegaNew)) {
                demografiaPacienteBodegaOld.getFormulasBodegaList().remove(formulasBodega);
                demografiaPacienteBodegaOld = em.merge(demografiaPacienteBodegaOld);
            }
            if (demografiaPacienteBodegaNew != null && !demografiaPacienteBodegaNew.equals(demografiaPacienteBodegaOld)) {
                demografiaPacienteBodegaNew.getFormulasBodegaList().add(formulasBodega);
                demografiaPacienteBodegaNew = em.merge(demografiaPacienteBodegaNew);
            }
            if (pacienteBodegaOld != null && !pacienteBodegaOld.equals(pacienteBodegaNew)) {
                pacienteBodegaOld.getFormulasBodegaList().remove(formulasBodega);
                pacienteBodegaOld = em.merge(pacienteBodegaOld);
            }
            if (pacienteBodegaNew != null && !pacienteBodegaNew.equals(pacienteBodegaOld)) {
                pacienteBodegaNew.getFormulasBodegaList().add(formulasBodega);
                pacienteBodegaNew = em.merge(pacienteBodegaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                FormulasBodegaPK id = formulasBodega.getFormulasBodegaPK();
                if (findFormulasBodega(id) == null) {
                    throw new NonexistentEntityException("The formulasBodega with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(FormulasBodegaPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            FormulasBodega formulasBodega;
            try {
                formulasBodega = em.getReference(FormulasBodega.class, id);
                formulasBodega.getFormulasBodegaPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The formulasBodega with id " + id + " no longer exists.", enfe);
            }
            MedicamentoBodega medicamentoBodega = formulasBodega.getMedicamentoBodega();
            if (medicamentoBodega != null) {
                medicamentoBodega.getFormulasBodegaList().remove(formulasBodega);
                medicamentoBodega = em.merge(medicamentoBodega);
            }
            MedicoBodega medicoBodega = formulasBodega.getMedicoBodega();
            if (medicoBodega != null) {
                medicoBodega.getFormulasBodegaList().remove(formulasBodega);
                medicoBodega = em.merge(medicoBodega);
            }
            Dates dates = formulasBodega.getDates();
            if (dates != null) {
                dates.getFormulasBodegaList().remove(formulasBodega);
                dates = em.merge(dates);
            }
            DemografiaPacienteBodega demografiaPacienteBodega = formulasBodega.getDemografiaPacienteBodega();
            if (demografiaPacienteBodega != null) {
                demografiaPacienteBodega.getFormulasBodegaList().remove(formulasBodega);
                demografiaPacienteBodega = em.merge(demografiaPacienteBodega);
            }
            PacienteBodega pacienteBodega = formulasBodega.getPacienteBodega();
            if (pacienteBodega != null) {
                pacienteBodega.getFormulasBodegaList().remove(formulasBodega);
                pacienteBodega = em.merge(pacienteBodega);
            }
            em.remove(formulasBodega);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<FormulasBodega> findFormulasBodegaEntities() {
        return findFormulasBodegaEntities(true, -1, -1);
    }

    public List<FormulasBodega> findFormulasBodegaEntities(int maxResults, int firstResult) {
        return findFormulasBodegaEntities(false, maxResults, firstResult);
    }

    private List<FormulasBodega> findFormulasBodegaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(FormulasBodega.class));
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

    public FormulasBodega findFormulasBodega(FormulasBodegaPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(FormulasBodega.class, id);
        } finally {
            em.close();
        }
    }

    public int getFormulasBodegaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<FormulasBodega> rt = cq.from(FormulasBodega.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
