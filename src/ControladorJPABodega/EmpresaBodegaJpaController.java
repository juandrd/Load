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
import Entidades_Bodega.PagosBodega;
import java.util.ArrayList;
import java.util.List;
import Entidades_Bodega.UrgenciasBodega;
import Entidades_Bodega.AfiliacionesBodega;
import Entidades_Bodega.EmpresaBodega;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author USER
 */
public class EmpresaBodegaJpaController implements Serializable {

    public EmpresaBodegaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(EmpresaBodega empresaBodega) throws PreexistingEntityException, Exception {
        if (empresaBodega.getPagosBodegaList() == null) {
            empresaBodega.setPagosBodegaList(new ArrayList<PagosBodega>());
        }
        if (empresaBodega.getUrgenciasBodegaList() == null) {
            empresaBodega.setUrgenciasBodegaList(new ArrayList<UrgenciasBodega>());
        }
        if (empresaBodega.getAfiliacionesBodegaList() == null) {
            empresaBodega.setAfiliacionesBodegaList(new ArrayList<AfiliacionesBodega>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<PagosBodega> attachedPagosBodegaList = new ArrayList<PagosBodega>();
            for (PagosBodega pagosBodegaListPagosBodegaToAttach : empresaBodega.getPagosBodegaList()) {
                pagosBodegaListPagosBodegaToAttach = em.getReference(pagosBodegaListPagosBodegaToAttach.getClass(), pagosBodegaListPagosBodegaToAttach.getPagosBodegaPK());
                attachedPagosBodegaList.add(pagosBodegaListPagosBodegaToAttach);
            }
            empresaBodega.setPagosBodegaList(attachedPagosBodegaList);
            List<UrgenciasBodega> attachedUrgenciasBodegaList = new ArrayList<UrgenciasBodega>();
            for (UrgenciasBodega urgenciasBodegaListUrgenciasBodegaToAttach : empresaBodega.getUrgenciasBodegaList()) {
                urgenciasBodegaListUrgenciasBodegaToAttach = em.getReference(urgenciasBodegaListUrgenciasBodegaToAttach.getClass(), urgenciasBodegaListUrgenciasBodegaToAttach.getUrgenciasBodegaPK());
                attachedUrgenciasBodegaList.add(urgenciasBodegaListUrgenciasBodegaToAttach);
            }
            empresaBodega.setUrgenciasBodegaList(attachedUrgenciasBodegaList);
            List<AfiliacionesBodega> attachedAfiliacionesBodegaList = new ArrayList<AfiliacionesBodega>();
            for (AfiliacionesBodega afiliacionesBodegaListAfiliacionesBodegaToAttach : empresaBodega.getAfiliacionesBodegaList()) {
                afiliacionesBodegaListAfiliacionesBodegaToAttach = em.getReference(afiliacionesBodegaListAfiliacionesBodegaToAttach.getClass(), afiliacionesBodegaListAfiliacionesBodegaToAttach.getAfiliacionesBodegaPK());
                attachedAfiliacionesBodegaList.add(afiliacionesBodegaListAfiliacionesBodegaToAttach);
            }
            empresaBodega.setAfiliacionesBodegaList(attachedAfiliacionesBodegaList);
            em.persist(empresaBodega);
            for (PagosBodega pagosBodegaListPagosBodega : empresaBodega.getPagosBodegaList()) {
                EmpresaBodega oldEmpresaBodegaOfPagosBodegaListPagosBodega = pagosBodegaListPagosBodega.getEmpresaBodega();
                pagosBodegaListPagosBodega.setEmpresaBodega(empresaBodega);
                pagosBodegaListPagosBodega = em.merge(pagosBodegaListPagosBodega);
                if (oldEmpresaBodegaOfPagosBodegaListPagosBodega != null) {
                    oldEmpresaBodegaOfPagosBodegaListPagosBodega.getPagosBodegaList().remove(pagosBodegaListPagosBodega);
                    oldEmpresaBodegaOfPagosBodegaListPagosBodega = em.merge(oldEmpresaBodegaOfPagosBodegaListPagosBodega);
                }
            }
            for (UrgenciasBodega urgenciasBodegaListUrgenciasBodega : empresaBodega.getUrgenciasBodegaList()) {
                EmpresaBodega oldEmpresaBodegaOfUrgenciasBodegaListUrgenciasBodega = urgenciasBodegaListUrgenciasBodega.getEmpresaBodega();
                urgenciasBodegaListUrgenciasBodega.setEmpresaBodega(empresaBodega);
                urgenciasBodegaListUrgenciasBodega = em.merge(urgenciasBodegaListUrgenciasBodega);
                if (oldEmpresaBodegaOfUrgenciasBodegaListUrgenciasBodega != null) {
                    oldEmpresaBodegaOfUrgenciasBodegaListUrgenciasBodega.getUrgenciasBodegaList().remove(urgenciasBodegaListUrgenciasBodega);
                    oldEmpresaBodegaOfUrgenciasBodegaListUrgenciasBodega = em.merge(oldEmpresaBodegaOfUrgenciasBodegaListUrgenciasBodega);
                }
            }
            for (AfiliacionesBodega afiliacionesBodegaListAfiliacionesBodega : empresaBodega.getAfiliacionesBodegaList()) {
                EmpresaBodega oldEmpresaBodegaOfAfiliacionesBodegaListAfiliacionesBodega = afiliacionesBodegaListAfiliacionesBodega.getEmpresaBodega();
                afiliacionesBodegaListAfiliacionesBodega.setEmpresaBodega(empresaBodega);
                afiliacionesBodegaListAfiliacionesBodega = em.merge(afiliacionesBodegaListAfiliacionesBodega);
                if (oldEmpresaBodegaOfAfiliacionesBodegaListAfiliacionesBodega != null) {
                    oldEmpresaBodegaOfAfiliacionesBodegaListAfiliacionesBodega.getAfiliacionesBodegaList().remove(afiliacionesBodegaListAfiliacionesBodega);
                    oldEmpresaBodegaOfAfiliacionesBodegaListAfiliacionesBodega = em.merge(oldEmpresaBodegaOfAfiliacionesBodegaListAfiliacionesBodega);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEmpresaBodega(empresaBodega.getEmpresaKey()) != null) {
                throw new PreexistingEntityException("EmpresaBodega " + empresaBodega + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EmpresaBodega empresaBodega) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EmpresaBodega persistentEmpresaBodega = em.find(EmpresaBodega.class, empresaBodega.getEmpresaKey());
            List<PagosBodega> pagosBodegaListOld = persistentEmpresaBodega.getPagosBodegaList();
            List<PagosBodega> pagosBodegaListNew = empresaBodega.getPagosBodegaList();
            List<UrgenciasBodega> urgenciasBodegaListOld = persistentEmpresaBodega.getUrgenciasBodegaList();
            List<UrgenciasBodega> urgenciasBodegaListNew = empresaBodega.getUrgenciasBodegaList();
            List<AfiliacionesBodega> afiliacionesBodegaListOld = persistentEmpresaBodega.getAfiliacionesBodegaList();
            List<AfiliacionesBodega> afiliacionesBodegaListNew = empresaBodega.getAfiliacionesBodegaList();
            List<String> illegalOrphanMessages = null;
            for (PagosBodega pagosBodegaListOldPagosBodega : pagosBodegaListOld) {
                if (!pagosBodegaListNew.contains(pagosBodegaListOldPagosBodega)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain PagosBodega " + pagosBodegaListOldPagosBodega + " since its empresaBodega field is not nullable.");
                }
            }
            for (UrgenciasBodega urgenciasBodegaListOldUrgenciasBodega : urgenciasBodegaListOld) {
                if (!urgenciasBodegaListNew.contains(urgenciasBodegaListOldUrgenciasBodega)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain UrgenciasBodega " + urgenciasBodegaListOldUrgenciasBodega + " since its empresaBodega field is not nullable.");
                }
            }
            for (AfiliacionesBodega afiliacionesBodegaListOldAfiliacionesBodega : afiliacionesBodegaListOld) {
                if (!afiliacionesBodegaListNew.contains(afiliacionesBodegaListOldAfiliacionesBodega)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain AfiliacionesBodega " + afiliacionesBodegaListOldAfiliacionesBodega + " since its empresaBodega field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<PagosBodega> attachedPagosBodegaListNew = new ArrayList<PagosBodega>();
            for (PagosBodega pagosBodegaListNewPagosBodegaToAttach : pagosBodegaListNew) {
                pagosBodegaListNewPagosBodegaToAttach = em.getReference(pagosBodegaListNewPagosBodegaToAttach.getClass(), pagosBodegaListNewPagosBodegaToAttach.getPagosBodegaPK());
                attachedPagosBodegaListNew.add(pagosBodegaListNewPagosBodegaToAttach);
            }
            pagosBodegaListNew = attachedPagosBodegaListNew;
            empresaBodega.setPagosBodegaList(pagosBodegaListNew);
            List<UrgenciasBodega> attachedUrgenciasBodegaListNew = new ArrayList<UrgenciasBodega>();
            for (UrgenciasBodega urgenciasBodegaListNewUrgenciasBodegaToAttach : urgenciasBodegaListNew) {
                urgenciasBodegaListNewUrgenciasBodegaToAttach = em.getReference(urgenciasBodegaListNewUrgenciasBodegaToAttach.getClass(), urgenciasBodegaListNewUrgenciasBodegaToAttach.getUrgenciasBodegaPK());
                attachedUrgenciasBodegaListNew.add(urgenciasBodegaListNewUrgenciasBodegaToAttach);
            }
            urgenciasBodegaListNew = attachedUrgenciasBodegaListNew;
            empresaBodega.setUrgenciasBodegaList(urgenciasBodegaListNew);
            List<AfiliacionesBodega> attachedAfiliacionesBodegaListNew = new ArrayList<AfiliacionesBodega>();
            for (AfiliacionesBodega afiliacionesBodegaListNewAfiliacionesBodegaToAttach : afiliacionesBodegaListNew) {
                afiliacionesBodegaListNewAfiliacionesBodegaToAttach = em.getReference(afiliacionesBodegaListNewAfiliacionesBodegaToAttach.getClass(), afiliacionesBodegaListNewAfiliacionesBodegaToAttach.getAfiliacionesBodegaPK());
                attachedAfiliacionesBodegaListNew.add(afiliacionesBodegaListNewAfiliacionesBodegaToAttach);
            }
            afiliacionesBodegaListNew = attachedAfiliacionesBodegaListNew;
            empresaBodega.setAfiliacionesBodegaList(afiliacionesBodegaListNew);
            empresaBodega = em.merge(empresaBodega);
            for (PagosBodega pagosBodegaListNewPagosBodega : pagosBodegaListNew) {
                if (!pagosBodegaListOld.contains(pagosBodegaListNewPagosBodega)) {
                    EmpresaBodega oldEmpresaBodegaOfPagosBodegaListNewPagosBodega = pagosBodegaListNewPagosBodega.getEmpresaBodega();
                    pagosBodegaListNewPagosBodega.setEmpresaBodega(empresaBodega);
                    pagosBodegaListNewPagosBodega = em.merge(pagosBodegaListNewPagosBodega);
                    if (oldEmpresaBodegaOfPagosBodegaListNewPagosBodega != null && !oldEmpresaBodegaOfPagosBodegaListNewPagosBodega.equals(empresaBodega)) {
                        oldEmpresaBodegaOfPagosBodegaListNewPagosBodega.getPagosBodegaList().remove(pagosBodegaListNewPagosBodega);
                        oldEmpresaBodegaOfPagosBodegaListNewPagosBodega = em.merge(oldEmpresaBodegaOfPagosBodegaListNewPagosBodega);
                    }
                }
            }
            for (UrgenciasBodega urgenciasBodegaListNewUrgenciasBodega : urgenciasBodegaListNew) {
                if (!urgenciasBodegaListOld.contains(urgenciasBodegaListNewUrgenciasBodega)) {
                    EmpresaBodega oldEmpresaBodegaOfUrgenciasBodegaListNewUrgenciasBodega = urgenciasBodegaListNewUrgenciasBodega.getEmpresaBodega();
                    urgenciasBodegaListNewUrgenciasBodega.setEmpresaBodega(empresaBodega);
                    urgenciasBodegaListNewUrgenciasBodega = em.merge(urgenciasBodegaListNewUrgenciasBodega);
                    if (oldEmpresaBodegaOfUrgenciasBodegaListNewUrgenciasBodega != null && !oldEmpresaBodegaOfUrgenciasBodegaListNewUrgenciasBodega.equals(empresaBodega)) {
                        oldEmpresaBodegaOfUrgenciasBodegaListNewUrgenciasBodega.getUrgenciasBodegaList().remove(urgenciasBodegaListNewUrgenciasBodega);
                        oldEmpresaBodegaOfUrgenciasBodegaListNewUrgenciasBodega = em.merge(oldEmpresaBodegaOfUrgenciasBodegaListNewUrgenciasBodega);
                    }
                }
            }
            for (AfiliacionesBodega afiliacionesBodegaListNewAfiliacionesBodega : afiliacionesBodegaListNew) {
                if (!afiliacionesBodegaListOld.contains(afiliacionesBodegaListNewAfiliacionesBodega)) {
                    EmpresaBodega oldEmpresaBodegaOfAfiliacionesBodegaListNewAfiliacionesBodega = afiliacionesBodegaListNewAfiliacionesBodega.getEmpresaBodega();
                    afiliacionesBodegaListNewAfiliacionesBodega.setEmpresaBodega(empresaBodega);
                    afiliacionesBodegaListNewAfiliacionesBodega = em.merge(afiliacionesBodegaListNewAfiliacionesBodega);
                    if (oldEmpresaBodegaOfAfiliacionesBodegaListNewAfiliacionesBodega != null && !oldEmpresaBodegaOfAfiliacionesBodegaListNewAfiliacionesBodega.equals(empresaBodega)) {
                        oldEmpresaBodegaOfAfiliacionesBodegaListNewAfiliacionesBodega.getAfiliacionesBodegaList().remove(afiliacionesBodegaListNewAfiliacionesBodega);
                        oldEmpresaBodegaOfAfiliacionesBodegaListNewAfiliacionesBodega = em.merge(oldEmpresaBodegaOfAfiliacionesBodegaListNewAfiliacionesBodega);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = empresaBodega.getEmpresaKey();
                if (findEmpresaBodega(id) == null) {
                    throw new NonexistentEntityException("The empresaBodega with id " + id + " no longer exists.");
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
            EmpresaBodega empresaBodega;
            try {
                empresaBodega = em.getReference(EmpresaBodega.class, id);
                empresaBodega.getEmpresaKey();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The empresaBodega with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<PagosBodega> pagosBodegaListOrphanCheck = empresaBodega.getPagosBodegaList();
            for (PagosBodega pagosBodegaListOrphanCheckPagosBodega : pagosBodegaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This EmpresaBodega (" + empresaBodega + ") cannot be destroyed since the PagosBodega " + pagosBodegaListOrphanCheckPagosBodega + " in its pagosBodegaList field has a non-nullable empresaBodega field.");
            }
            List<UrgenciasBodega> urgenciasBodegaListOrphanCheck = empresaBodega.getUrgenciasBodegaList();
            for (UrgenciasBodega urgenciasBodegaListOrphanCheckUrgenciasBodega : urgenciasBodegaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This EmpresaBodega (" + empresaBodega + ") cannot be destroyed since the UrgenciasBodega " + urgenciasBodegaListOrphanCheckUrgenciasBodega + " in its urgenciasBodegaList field has a non-nullable empresaBodega field.");
            }
            List<AfiliacionesBodega> afiliacionesBodegaListOrphanCheck = empresaBodega.getAfiliacionesBodegaList();
            for (AfiliacionesBodega afiliacionesBodegaListOrphanCheckAfiliacionesBodega : afiliacionesBodegaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This EmpresaBodega (" + empresaBodega + ") cannot be destroyed since the AfiliacionesBodega " + afiliacionesBodegaListOrphanCheckAfiliacionesBodega + " in its afiliacionesBodegaList field has a non-nullable empresaBodega field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(empresaBodega);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EmpresaBodega> findEmpresaBodegaEntities() {
        return findEmpresaBodegaEntities(true, -1, -1);
    }

    public List<EmpresaBodega> findEmpresaBodegaEntities(int maxResults, int firstResult) {
        return findEmpresaBodegaEntities(false, maxResults, firstResult);
    }

    private List<EmpresaBodega> findEmpresaBodegaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EmpresaBodega.class));
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

    public EmpresaBodega findEmpresaBodega(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EmpresaBodega.class, id);
        } finally {
            em.close();
        }
    }

    public int getEmpresaBodegaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EmpresaBodega> rt = cq.from(EmpresaBodega.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
      public EmpresaBodega consultar(String f) {
          
          
         EmpresaBodega empresa=new EmpresaBodega();
         List lista;
         lista=findEmpresaBodegaEntities();
         
          for (int i = 0; i < lista.size(); i++) {
              EmpresaBodega emp=(EmpresaBodega) lista.get(i);             
              
             
              if(emp.getNit().toString().equalsIgnoreCase(f)){                  
                  empresa=emp;                 
                  break;
              }
          }

        return empresa;
    }
}
