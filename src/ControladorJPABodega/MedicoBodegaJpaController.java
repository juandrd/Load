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
import Entidades_Bodega.CitasBodega;
import java.util.ArrayList;
import java.util.List;
import Entidades_Bodega.FormulasBodega;
import Entidades_Bodega.MedicoBodega;
import Entidades_Bodega.RemisionesBodega;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author USER
 */
public class MedicoBodegaJpaController implements Serializable {

    public MedicoBodegaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(MedicoBodega medicoBodega) throws PreexistingEntityException, Exception {
        if (medicoBodega.getCitasBodegaList() == null) {
            medicoBodega.setCitasBodegaList(new ArrayList<CitasBodega>());
        }
        if (medicoBodega.getFormulasBodegaList() == null) {
            medicoBodega.setFormulasBodegaList(new ArrayList<FormulasBodega>());
        }
        if (medicoBodega.getRemisionesBodegaList() == null) {
            medicoBodega.setRemisionesBodegaList(new ArrayList<RemisionesBodega>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<CitasBodega> attachedCitasBodegaList = new ArrayList<CitasBodega>();
            for (CitasBodega citasBodegaListCitasBodegaToAttach : medicoBodega.getCitasBodegaList()) {
                citasBodegaListCitasBodegaToAttach = em.getReference(citasBodegaListCitasBodegaToAttach.getClass(), citasBodegaListCitasBodegaToAttach.getCitasBodegaPK());
                attachedCitasBodegaList.add(citasBodegaListCitasBodegaToAttach);
            }
            medicoBodega.setCitasBodegaList(attachedCitasBodegaList);
            List<FormulasBodega> attachedFormulasBodegaList = new ArrayList<FormulasBodega>();
            for (FormulasBodega formulasBodegaListFormulasBodegaToAttach : medicoBodega.getFormulasBodegaList()) {
                formulasBodegaListFormulasBodegaToAttach = em.getReference(formulasBodegaListFormulasBodegaToAttach.getClass(), formulasBodegaListFormulasBodegaToAttach.getFormulasBodegaPK());
                attachedFormulasBodegaList.add(formulasBodegaListFormulasBodegaToAttach);
            }
            medicoBodega.setFormulasBodegaList(attachedFormulasBodegaList);
            List<RemisionesBodega> attachedRemisionesBodegaList = new ArrayList<RemisionesBodega>();
            for (RemisionesBodega remisionesBodegaListRemisionesBodegaToAttach : medicoBodega.getRemisionesBodegaList()) {
                remisionesBodegaListRemisionesBodegaToAttach = em.getReference(remisionesBodegaListRemisionesBodegaToAttach.getClass(), remisionesBodegaListRemisionesBodegaToAttach.getRemisionesBodegaPK());
                attachedRemisionesBodegaList.add(remisionesBodegaListRemisionesBodegaToAttach);
            }
            medicoBodega.setRemisionesBodegaList(attachedRemisionesBodegaList);
            em.persist(medicoBodega);
            for (CitasBodega citasBodegaListCitasBodega : medicoBodega.getCitasBodegaList()) {
                MedicoBodega oldMedicoBodegaOfCitasBodegaListCitasBodega = citasBodegaListCitasBodega.getMedicoBodega();
                citasBodegaListCitasBodega.setMedicoBodega(medicoBodega);
                citasBodegaListCitasBodega = em.merge(citasBodegaListCitasBodega);
                if (oldMedicoBodegaOfCitasBodegaListCitasBodega != null) {
                    oldMedicoBodegaOfCitasBodegaListCitasBodega.getCitasBodegaList().remove(citasBodegaListCitasBodega);
                    oldMedicoBodegaOfCitasBodegaListCitasBodega = em.merge(oldMedicoBodegaOfCitasBodegaListCitasBodega);
                }
            }
            for (FormulasBodega formulasBodegaListFormulasBodega : medicoBodega.getFormulasBodegaList()) {
                MedicoBodega oldMedicoBodegaOfFormulasBodegaListFormulasBodega = formulasBodegaListFormulasBodega.getMedicoBodega();
                formulasBodegaListFormulasBodega.setMedicoBodega(medicoBodega);
                formulasBodegaListFormulasBodega = em.merge(formulasBodegaListFormulasBodega);
                if (oldMedicoBodegaOfFormulasBodegaListFormulasBodega != null) {
                    oldMedicoBodegaOfFormulasBodegaListFormulasBodega.getFormulasBodegaList().remove(formulasBodegaListFormulasBodega);
                    oldMedicoBodegaOfFormulasBodegaListFormulasBodega = em.merge(oldMedicoBodegaOfFormulasBodegaListFormulasBodega);
                }
            }
            for (RemisionesBodega remisionesBodegaListRemisionesBodega : medicoBodega.getRemisionesBodegaList()) {
                MedicoBodega oldMedicoBodegaOfRemisionesBodegaListRemisionesBodega = remisionesBodegaListRemisionesBodega.getMedicoBodega();
                remisionesBodegaListRemisionesBodega.setMedicoBodega(medicoBodega);
                remisionesBodegaListRemisionesBodega = em.merge(remisionesBodegaListRemisionesBodega);
                if (oldMedicoBodegaOfRemisionesBodegaListRemisionesBodega != null) {
                    oldMedicoBodegaOfRemisionesBodegaListRemisionesBodega.getRemisionesBodegaList().remove(remisionesBodegaListRemisionesBodega);
                    oldMedicoBodegaOfRemisionesBodegaListRemisionesBodega = em.merge(oldMedicoBodegaOfRemisionesBodegaListRemisionesBodega);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findMedicoBodega(medicoBodega.getMedicoKey()) != null) {
                throw new PreexistingEntityException("MedicoBodega " + medicoBodega + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(MedicoBodega medicoBodega) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            MedicoBodega persistentMedicoBodega = em.find(MedicoBodega.class, medicoBodega.getMedicoKey());
            List<CitasBodega> citasBodegaListOld = persistentMedicoBodega.getCitasBodegaList();
            List<CitasBodega> citasBodegaListNew = medicoBodega.getCitasBodegaList();
            List<FormulasBodega> formulasBodegaListOld = persistentMedicoBodega.getFormulasBodegaList();
            List<FormulasBodega> formulasBodegaListNew = medicoBodega.getFormulasBodegaList();
            List<RemisionesBodega> remisionesBodegaListOld = persistentMedicoBodega.getRemisionesBodegaList();
            List<RemisionesBodega> remisionesBodegaListNew = medicoBodega.getRemisionesBodegaList();
            List<String> illegalOrphanMessages = null;
            for (CitasBodega citasBodegaListOldCitasBodega : citasBodegaListOld) {
                if (!citasBodegaListNew.contains(citasBodegaListOldCitasBodega)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain CitasBodega " + citasBodegaListOldCitasBodega + " since its medicoBodega field is not nullable.");
                }
            }
            for (FormulasBodega formulasBodegaListOldFormulasBodega : formulasBodegaListOld) {
                if (!formulasBodegaListNew.contains(formulasBodegaListOldFormulasBodega)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain FormulasBodega " + formulasBodegaListOldFormulasBodega + " since its medicoBodega field is not nullable.");
                }
            }
            for (RemisionesBodega remisionesBodegaListOldRemisionesBodega : remisionesBodegaListOld) {
                if (!remisionesBodegaListNew.contains(remisionesBodegaListOldRemisionesBodega)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain RemisionesBodega " + remisionesBodegaListOldRemisionesBodega + " since its medicoBodega field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<CitasBodega> attachedCitasBodegaListNew = new ArrayList<CitasBodega>();
            for (CitasBodega citasBodegaListNewCitasBodegaToAttach : citasBodegaListNew) {
                citasBodegaListNewCitasBodegaToAttach = em.getReference(citasBodegaListNewCitasBodegaToAttach.getClass(), citasBodegaListNewCitasBodegaToAttach.getCitasBodegaPK());
                attachedCitasBodegaListNew.add(citasBodegaListNewCitasBodegaToAttach);
            }
            citasBodegaListNew = attachedCitasBodegaListNew;
            medicoBodega.setCitasBodegaList(citasBodegaListNew);
            List<FormulasBodega> attachedFormulasBodegaListNew = new ArrayList<FormulasBodega>();
            for (FormulasBodega formulasBodegaListNewFormulasBodegaToAttach : formulasBodegaListNew) {
                formulasBodegaListNewFormulasBodegaToAttach = em.getReference(formulasBodegaListNewFormulasBodegaToAttach.getClass(), formulasBodegaListNewFormulasBodegaToAttach.getFormulasBodegaPK());
                attachedFormulasBodegaListNew.add(formulasBodegaListNewFormulasBodegaToAttach);
            }
            formulasBodegaListNew = attachedFormulasBodegaListNew;
            medicoBodega.setFormulasBodegaList(formulasBodegaListNew);
            List<RemisionesBodega> attachedRemisionesBodegaListNew = new ArrayList<RemisionesBodega>();
            for (RemisionesBodega remisionesBodegaListNewRemisionesBodegaToAttach : remisionesBodegaListNew) {
                remisionesBodegaListNewRemisionesBodegaToAttach = em.getReference(remisionesBodegaListNewRemisionesBodegaToAttach.getClass(), remisionesBodegaListNewRemisionesBodegaToAttach.getRemisionesBodegaPK());
                attachedRemisionesBodegaListNew.add(remisionesBodegaListNewRemisionesBodegaToAttach);
            }
            remisionesBodegaListNew = attachedRemisionesBodegaListNew;
            medicoBodega.setRemisionesBodegaList(remisionesBodegaListNew);
            medicoBodega = em.merge(medicoBodega);
            for (CitasBodega citasBodegaListNewCitasBodega : citasBodegaListNew) {
                if (!citasBodegaListOld.contains(citasBodegaListNewCitasBodega)) {
                    MedicoBodega oldMedicoBodegaOfCitasBodegaListNewCitasBodega = citasBodegaListNewCitasBodega.getMedicoBodega();
                    citasBodegaListNewCitasBodega.setMedicoBodega(medicoBodega);
                    citasBodegaListNewCitasBodega = em.merge(citasBodegaListNewCitasBodega);
                    if (oldMedicoBodegaOfCitasBodegaListNewCitasBodega != null && !oldMedicoBodegaOfCitasBodegaListNewCitasBodega.equals(medicoBodega)) {
                        oldMedicoBodegaOfCitasBodegaListNewCitasBodega.getCitasBodegaList().remove(citasBodegaListNewCitasBodega);
                        oldMedicoBodegaOfCitasBodegaListNewCitasBodega = em.merge(oldMedicoBodegaOfCitasBodegaListNewCitasBodega);
                    }
                }
            }
            for (FormulasBodega formulasBodegaListNewFormulasBodega : formulasBodegaListNew) {
                if (!formulasBodegaListOld.contains(formulasBodegaListNewFormulasBodega)) {
                    MedicoBodega oldMedicoBodegaOfFormulasBodegaListNewFormulasBodega = formulasBodegaListNewFormulasBodega.getMedicoBodega();
                    formulasBodegaListNewFormulasBodega.setMedicoBodega(medicoBodega);
                    formulasBodegaListNewFormulasBodega = em.merge(formulasBodegaListNewFormulasBodega);
                    if (oldMedicoBodegaOfFormulasBodegaListNewFormulasBodega != null && !oldMedicoBodegaOfFormulasBodegaListNewFormulasBodega.equals(medicoBodega)) {
                        oldMedicoBodegaOfFormulasBodegaListNewFormulasBodega.getFormulasBodegaList().remove(formulasBodegaListNewFormulasBodega);
                        oldMedicoBodegaOfFormulasBodegaListNewFormulasBodega = em.merge(oldMedicoBodegaOfFormulasBodegaListNewFormulasBodega);
                    }
                }
            }
            for (RemisionesBodega remisionesBodegaListNewRemisionesBodega : remisionesBodegaListNew) {
                if (!remisionesBodegaListOld.contains(remisionesBodegaListNewRemisionesBodega)) {
                    MedicoBodega oldMedicoBodegaOfRemisionesBodegaListNewRemisionesBodega = remisionesBodegaListNewRemisionesBodega.getMedicoBodega();
                    remisionesBodegaListNewRemisionesBodega.setMedicoBodega(medicoBodega);
                    remisionesBodegaListNewRemisionesBodega = em.merge(remisionesBodegaListNewRemisionesBodega);
                    if (oldMedicoBodegaOfRemisionesBodegaListNewRemisionesBodega != null && !oldMedicoBodegaOfRemisionesBodegaListNewRemisionesBodega.equals(medicoBodega)) {
                        oldMedicoBodegaOfRemisionesBodegaListNewRemisionesBodega.getRemisionesBodegaList().remove(remisionesBodegaListNewRemisionesBodega);
                        oldMedicoBodegaOfRemisionesBodegaListNewRemisionesBodega = em.merge(oldMedicoBodegaOfRemisionesBodegaListNewRemisionesBodega);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = medicoBodega.getMedicoKey();
                if (findMedicoBodega(id) == null) {
                    throw new NonexistentEntityException("The medicoBodega with id " + id + " no longer exists.");
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
            MedicoBodega medicoBodega;
            try {
                medicoBodega = em.getReference(MedicoBodega.class, id);
                medicoBodega.getMedicoKey();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The medicoBodega with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<CitasBodega> citasBodegaListOrphanCheck = medicoBodega.getCitasBodegaList();
            for (CitasBodega citasBodegaListOrphanCheckCitasBodega : citasBodegaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This MedicoBodega (" + medicoBodega + ") cannot be destroyed since the CitasBodega " + citasBodegaListOrphanCheckCitasBodega + " in its citasBodegaList field has a non-nullable medicoBodega field.");
            }
            List<FormulasBodega> formulasBodegaListOrphanCheck = medicoBodega.getFormulasBodegaList();
            for (FormulasBodega formulasBodegaListOrphanCheckFormulasBodega : formulasBodegaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This MedicoBodega (" + medicoBodega + ") cannot be destroyed since the FormulasBodega " + formulasBodegaListOrphanCheckFormulasBodega + " in its formulasBodegaList field has a non-nullable medicoBodega field.");
            }
            List<RemisionesBodega> remisionesBodegaListOrphanCheck = medicoBodega.getRemisionesBodegaList();
            for (RemisionesBodega remisionesBodegaListOrphanCheckRemisionesBodega : remisionesBodegaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This MedicoBodega (" + medicoBodega + ") cannot be destroyed since the RemisionesBodega " + remisionesBodegaListOrphanCheckRemisionesBodega + " in its remisionesBodegaList field has a non-nullable medicoBodega field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(medicoBodega);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<MedicoBodega> findMedicoBodegaEntities() {
        return findMedicoBodegaEntities(true, -1, -1);
    }

    public List<MedicoBodega> findMedicoBodegaEntities(int maxResults, int firstResult) {
        return findMedicoBodegaEntities(false, maxResults, firstResult);
    }

    private List<MedicoBodega> findMedicoBodegaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(MedicoBodega.class));
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

    public MedicoBodega findMedicoBodega(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(MedicoBodega.class, id);
        } finally {
            em.close();
        }
    }

    public int getMedicoBodegaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<MedicoBodega> rt = cq.from(MedicoBodega.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
      public MedicoBodega consultar(String f) {          
          
         MedicoBodega m=new MedicoBodega();
         List lista;
         lista=findMedicoBodegaEntities();
         
          for (int i = 0; i < lista.size(); i++) {
              MedicoBodega medico=(MedicoBodega) lista.get(i);             
              
             
              if(medico.getIdMedico().toString().equalsIgnoreCase(f)){                  
                  m=medico;
                 // System.out.println(m);
                  break;
              }
          }

        return m;
    }
}
