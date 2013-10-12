/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladorJPABodega;

import ControladorJPABodega.exceptions.IllegalOrphanException;
import ControladorJPABodega.exceptions.NonexistentEntityException;
import ControladorJPABodega.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entidades_Bodega.FormulasBodega;
import Entidades_Bodega.MedicamentoBodega;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author USER
 */
public class MedicamentoBodegaJpaController implements Serializable {

    public MedicamentoBodegaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(MedicamentoBodega medicamentoBodega) throws PreexistingEntityException, Exception {
        if (medicamentoBodega.getFormulasBodegaList() == null) {
            medicamentoBodega.setFormulasBodegaList(new ArrayList<FormulasBodega>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<FormulasBodega> attachedFormulasBodegaList = new ArrayList<FormulasBodega>();
            for (FormulasBodega formulasBodegaListFormulasBodegaToAttach : medicamentoBodega.getFormulasBodegaList()) {
                formulasBodegaListFormulasBodegaToAttach = em.getReference(formulasBodegaListFormulasBodegaToAttach.getClass(), formulasBodegaListFormulasBodegaToAttach.getFormulasBodegaPK());
                attachedFormulasBodegaList.add(formulasBodegaListFormulasBodegaToAttach);
            }
            medicamentoBodega.setFormulasBodegaList(attachedFormulasBodegaList);
            em.persist(medicamentoBodega);
            for (FormulasBodega formulasBodegaListFormulasBodega : medicamentoBodega.getFormulasBodegaList()) {
                MedicamentoBodega oldMedicamentoBodegaOfFormulasBodegaListFormulasBodega = formulasBodegaListFormulasBodega.getMedicamentoBodega();
                formulasBodegaListFormulasBodega.setMedicamentoBodega(medicamentoBodega);
                formulasBodegaListFormulasBodega = em.merge(formulasBodegaListFormulasBodega);
                if (oldMedicamentoBodegaOfFormulasBodegaListFormulasBodega != null) {
                    oldMedicamentoBodegaOfFormulasBodegaListFormulasBodega.getFormulasBodegaList().remove(formulasBodegaListFormulasBodega);
                    oldMedicamentoBodegaOfFormulasBodegaListFormulasBodega = em.merge(oldMedicamentoBodegaOfFormulasBodegaListFormulasBodega);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findMedicamentoBodega(medicamentoBodega.getMedicamentoKey()) != null) {
                throw new PreexistingEntityException("MedicamentoBodega " + medicamentoBodega + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(MedicamentoBodega medicamentoBodega) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            MedicamentoBodega persistentMedicamentoBodega = em.find(MedicamentoBodega.class, medicamentoBodega.getMedicamentoKey());
            List<FormulasBodega> formulasBodegaListOld = persistentMedicamentoBodega.getFormulasBodegaList();
            List<FormulasBodega> formulasBodegaListNew = medicamentoBodega.getFormulasBodegaList();
            List<String> illegalOrphanMessages = null;
            for (FormulasBodega formulasBodegaListOldFormulasBodega : formulasBodegaListOld) {
                if (!formulasBodegaListNew.contains(formulasBodegaListOldFormulasBodega)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain FormulasBodega " + formulasBodegaListOldFormulasBodega + " since its medicamentoBodega field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<FormulasBodega> attachedFormulasBodegaListNew = new ArrayList<FormulasBodega>();
            for (FormulasBodega formulasBodegaListNewFormulasBodegaToAttach : formulasBodegaListNew) {
                formulasBodegaListNewFormulasBodegaToAttach = em.getReference(formulasBodegaListNewFormulasBodegaToAttach.getClass(), formulasBodegaListNewFormulasBodegaToAttach.getFormulasBodegaPK());
                attachedFormulasBodegaListNew.add(formulasBodegaListNewFormulasBodegaToAttach);
            }
            formulasBodegaListNew = attachedFormulasBodegaListNew;
            medicamentoBodega.setFormulasBodegaList(formulasBodegaListNew);
            medicamentoBodega = em.merge(medicamentoBodega);
            for (FormulasBodega formulasBodegaListNewFormulasBodega : formulasBodegaListNew) {
                if (!formulasBodegaListOld.contains(formulasBodegaListNewFormulasBodega)) {
                    MedicamentoBodega oldMedicamentoBodegaOfFormulasBodegaListNewFormulasBodega = formulasBodegaListNewFormulasBodega.getMedicamentoBodega();
                    formulasBodegaListNewFormulasBodega.setMedicamentoBodega(medicamentoBodega);
                    formulasBodegaListNewFormulasBodega = em.merge(formulasBodegaListNewFormulasBodega);
                    if (oldMedicamentoBodegaOfFormulasBodegaListNewFormulasBodega != null && !oldMedicamentoBodegaOfFormulasBodegaListNewFormulasBodega.equals(medicamentoBodega)) {
                        oldMedicamentoBodegaOfFormulasBodegaListNewFormulasBodega.getFormulasBodegaList().remove(formulasBodegaListNewFormulasBodega);
                        oldMedicamentoBodegaOfFormulasBodegaListNewFormulasBodega = em.merge(oldMedicamentoBodegaOfFormulasBodegaListNewFormulasBodega);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = medicamentoBodega.getMedicamentoKey();
                if (findMedicamentoBodega(id) == null) {
                    throw new NonexistentEntityException("The medicamentoBodega with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            MedicamentoBodega medicamentoBodega;
            try {
                medicamentoBodega = em.getReference(MedicamentoBodega.class, id);
                medicamentoBodega.getMedicamentoKey();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The medicamentoBodega with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<FormulasBodega> formulasBodegaListOrphanCheck = medicamentoBodega.getFormulasBodegaList();
            for (FormulasBodega formulasBodegaListOrphanCheckFormulasBodega : formulasBodegaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This MedicamentoBodega (" + medicamentoBodega + ") cannot be destroyed since the FormulasBodega " + formulasBodegaListOrphanCheckFormulasBodega + " in its formulasBodegaList field has a non-nullable medicamentoBodega field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(medicamentoBodega);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<MedicamentoBodega> findMedicamentoBodegaEntities() {
        return findMedicamentoBodegaEntities(true, -1, -1);
    }

    public List<MedicamentoBodega> findMedicamentoBodegaEntities(int maxResults, int firstResult) {
        return findMedicamentoBodegaEntities(false, maxResults, firstResult);
    }

    private List<MedicamentoBodega> findMedicamentoBodegaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(MedicamentoBodega.class));
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

    public MedicamentoBodega findMedicamentoBodega(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(MedicamentoBodega.class, id);
        } finally {
            em.close();
        }
    }

    public int getMedicamentoBodegaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<MedicamentoBodega> rt = cq.from(MedicamentoBodega.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
