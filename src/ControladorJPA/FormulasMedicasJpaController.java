/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladorJPA;

import ControladorJPA.exceptions.NonexistentEntityException;
import ControladorJPA.exceptions.PreexistingEntityException;
import Entidades_Bodega.DiagnosticoBodega;
import Entidades_DB.FormulasMedicas;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entidades_DB.Medico;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author USER
 */
public class FormulasMedicasJpaController implements Serializable {

    public FormulasMedicasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(FormulasMedicas formulasMedicas) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Medico IDMedico = formulasMedicas.getIDMedico();
            if (IDMedico != null) {
                IDMedico = em.getReference(IDMedico.getClass(), IDMedico.getCedula());
                formulasMedicas.setIDMedico(IDMedico);
            }
            em.persist(formulasMedicas);
            if (IDMedico != null) {
                IDMedico.getFormulasMedicasList().add(formulasMedicas);
                IDMedico = em.merge(IDMedico);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findFormulasMedicas(formulasMedicas.getCodigoFormula()) != null) {
                throw new PreexistingEntityException("FormulasMedicas " + formulasMedicas + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(FormulasMedicas formulasMedicas) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            FormulasMedicas persistentFormulasMedicas = em.find(FormulasMedicas.class, formulasMedicas.getCodigoFormula());
            Medico IDMedicoOld = persistentFormulasMedicas.getIDMedico();
            Medico IDMedicoNew = formulasMedicas.getIDMedico();
            if (IDMedicoNew != null) {
                IDMedicoNew = em.getReference(IDMedicoNew.getClass(), IDMedicoNew.getCedula());
                formulasMedicas.setIDMedico(IDMedicoNew);
            }
            formulasMedicas = em.merge(formulasMedicas);
            if (IDMedicoOld != null && !IDMedicoOld.equals(IDMedicoNew)) {
                IDMedicoOld.getFormulasMedicasList().remove(formulasMedicas);
                IDMedicoOld = em.merge(IDMedicoOld);
            }
            if (IDMedicoNew != null && !IDMedicoNew.equals(IDMedicoOld)) {
                IDMedicoNew.getFormulasMedicasList().add(formulasMedicas);
                IDMedicoNew = em.merge(IDMedicoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = formulasMedicas.getCodigoFormula();
                if (findFormulasMedicas(id) == null) {
                    throw new NonexistentEntityException("The formulasMedicas with id " + id + " no longer exists.");
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
            FormulasMedicas formulasMedicas;
            try {
                formulasMedicas = em.getReference(FormulasMedicas.class, id);
                formulasMedicas.getCodigoFormula();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The formulasMedicas with id " + id + " no longer exists.", enfe);
            }
            Medico IDMedico = formulasMedicas.getIDMedico();
            if (IDMedico != null) {
                IDMedico.getFormulasMedicasList().remove(formulasMedicas);
                IDMedico = em.merge(IDMedico);
            }
            em.remove(formulasMedicas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<FormulasMedicas> findFormulasMedicasEntities() {
        return findFormulasMedicasEntities(true, -1, -1);
    }

    public List<FormulasMedicas> findFormulasMedicasEntities(int maxResults, int firstResult) {
        return findFormulasMedicasEntities(false, maxResults, firstResult);
    }

    private List<FormulasMedicas> findFormulasMedicasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(FormulasMedicas.class));
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

    public FormulasMedicas findFormulasMedicas(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(FormulasMedicas.class, id);
        } finally {
            em.close();
        }
    }

    public int getFormulasMedicasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<FormulasMedicas> rt = cq.from(FormulasMedicas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    
  
}
