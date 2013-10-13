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
import Entidades_Bodega.DiagnosticoBodega;
import java.util.ArrayList;
import java.util.List;
import Entidades_Bodega.UrgenciasBodega;
import Entidades_Bodega.RemisionesBodega;
import Entidades_DB.CitasGenerales;
import java.util.LinkedList;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author USER
 */
public class DiagnosticoBodegaJpaController implements Serializable {

    public DiagnosticoBodegaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(DiagnosticoBodega diagnosticoBodega) throws PreexistingEntityException, Exception {
        if (diagnosticoBodega.getCitasBodegaList() == null) {
            diagnosticoBodega.setCitasBodegaList(new ArrayList<CitasBodega>());
        }
        if (diagnosticoBodega.getUrgenciasBodegaList() == null) {
            diagnosticoBodega.setUrgenciasBodegaList(new ArrayList<UrgenciasBodega>());
        }
        if (diagnosticoBodega.getRemisionesBodegaList() == null) {
            diagnosticoBodega.setRemisionesBodegaList(new ArrayList<RemisionesBodega>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<CitasBodega> attachedCitasBodegaList = new ArrayList<CitasBodega>();
            for (CitasBodega citasBodegaListCitasBodegaToAttach : diagnosticoBodega.getCitasBodegaList()) {
                citasBodegaListCitasBodegaToAttach = em.getReference(citasBodegaListCitasBodegaToAttach.getClass(), citasBodegaListCitasBodegaToAttach.getCitasBodegaPK());
                attachedCitasBodegaList.add(citasBodegaListCitasBodegaToAttach);
            }
            diagnosticoBodega.setCitasBodegaList(attachedCitasBodegaList);
            List<UrgenciasBodega> attachedUrgenciasBodegaList = new ArrayList<UrgenciasBodega>();
            for (UrgenciasBodega urgenciasBodegaListUrgenciasBodegaToAttach : diagnosticoBodega.getUrgenciasBodegaList()) {
                urgenciasBodegaListUrgenciasBodegaToAttach = em.getReference(urgenciasBodegaListUrgenciasBodegaToAttach.getClass(), urgenciasBodegaListUrgenciasBodegaToAttach.getUrgenciasBodegaPK());
                attachedUrgenciasBodegaList.add(urgenciasBodegaListUrgenciasBodegaToAttach);
            }
            diagnosticoBodega.setUrgenciasBodegaList(attachedUrgenciasBodegaList);
            List<RemisionesBodega> attachedRemisionesBodegaList = new ArrayList<RemisionesBodega>();
            for (RemisionesBodega remisionesBodegaListRemisionesBodegaToAttach : diagnosticoBodega.getRemisionesBodegaList()) {
                remisionesBodegaListRemisionesBodegaToAttach = em.getReference(remisionesBodegaListRemisionesBodegaToAttach.getClass(), remisionesBodegaListRemisionesBodegaToAttach.getRemisionesBodegaPK());
                attachedRemisionesBodegaList.add(remisionesBodegaListRemisionesBodegaToAttach);
            }
            diagnosticoBodega.setRemisionesBodegaList(attachedRemisionesBodegaList);
            em.persist(diagnosticoBodega);
            for (CitasBodega citasBodegaListCitasBodega : diagnosticoBodega.getCitasBodegaList()) {
                DiagnosticoBodega oldDiagnosticoBodegaOfCitasBodegaListCitasBodega = citasBodegaListCitasBodega.getDiagnosticoBodega();
                citasBodegaListCitasBodega.setDiagnosticoBodega(diagnosticoBodega);
                citasBodegaListCitasBodega = em.merge(citasBodegaListCitasBodega);
                if (oldDiagnosticoBodegaOfCitasBodegaListCitasBodega != null) {
                    oldDiagnosticoBodegaOfCitasBodegaListCitasBodega.getCitasBodegaList().remove(citasBodegaListCitasBodega);
                    oldDiagnosticoBodegaOfCitasBodegaListCitasBodega = em.merge(oldDiagnosticoBodegaOfCitasBodegaListCitasBodega);
                }
            }
            for (UrgenciasBodega urgenciasBodegaListUrgenciasBodega : diagnosticoBodega.getUrgenciasBodegaList()) {
                DiagnosticoBodega oldDiagnosticoKeyOfUrgenciasBodegaListUrgenciasBodega = urgenciasBodegaListUrgenciasBodega.getDiagnosticoKey();
                urgenciasBodegaListUrgenciasBodega.setDiagnosticoKey(diagnosticoBodega);
                urgenciasBodegaListUrgenciasBodega = em.merge(urgenciasBodegaListUrgenciasBodega);
                if (oldDiagnosticoKeyOfUrgenciasBodegaListUrgenciasBodega != null) {
                    oldDiagnosticoKeyOfUrgenciasBodegaListUrgenciasBodega.getUrgenciasBodegaList().remove(urgenciasBodegaListUrgenciasBodega);
                    oldDiagnosticoKeyOfUrgenciasBodegaListUrgenciasBodega = em.merge(oldDiagnosticoKeyOfUrgenciasBodegaListUrgenciasBodega);
                }
            }
            for (RemisionesBodega remisionesBodegaListRemisionesBodega : diagnosticoBodega.getRemisionesBodegaList()) {
                DiagnosticoBodega oldDiagnosticoBodegaOfRemisionesBodegaListRemisionesBodega = remisionesBodegaListRemisionesBodega.getDiagnosticoBodega();
                remisionesBodegaListRemisionesBodega.setDiagnosticoBodega(diagnosticoBodega);
                remisionesBodegaListRemisionesBodega = em.merge(remisionesBodegaListRemisionesBodega);
                if (oldDiagnosticoBodegaOfRemisionesBodegaListRemisionesBodega != null) {
                    oldDiagnosticoBodegaOfRemisionesBodegaListRemisionesBodega.getRemisionesBodegaList().remove(remisionesBodegaListRemisionesBodega);
                    oldDiagnosticoBodegaOfRemisionesBodegaListRemisionesBodega = em.merge(oldDiagnosticoBodegaOfRemisionesBodegaListRemisionesBodega);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDiagnosticoBodega(diagnosticoBodega.getDiagnosticoKey()) != null) {
                throw new PreexistingEntityException("DiagnosticoBodega " + diagnosticoBodega + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DiagnosticoBodega diagnosticoBodega) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DiagnosticoBodega persistentDiagnosticoBodega = em.find(DiagnosticoBodega.class, diagnosticoBodega.getDiagnosticoKey());
            List<CitasBodega> citasBodegaListOld = persistentDiagnosticoBodega.getCitasBodegaList();
            List<CitasBodega> citasBodegaListNew = diagnosticoBodega.getCitasBodegaList();
            List<UrgenciasBodega> urgenciasBodegaListOld = persistentDiagnosticoBodega.getUrgenciasBodegaList();
            List<UrgenciasBodega> urgenciasBodegaListNew = diagnosticoBodega.getUrgenciasBodegaList();
            List<RemisionesBodega> remisionesBodegaListOld = persistentDiagnosticoBodega.getRemisionesBodegaList();
            List<RemisionesBodega> remisionesBodegaListNew = diagnosticoBodega.getRemisionesBodegaList();
            List<String> illegalOrphanMessages = null;
            for (CitasBodega citasBodegaListOldCitasBodega : citasBodegaListOld) {
                if (!citasBodegaListNew.contains(citasBodegaListOldCitasBodega)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain CitasBodega " + citasBodegaListOldCitasBodega + " since its diagnosticoBodega field is not nullable.");
                }
            }
            for (RemisionesBodega remisionesBodegaListOldRemisionesBodega : remisionesBodegaListOld) {
                if (!remisionesBodegaListNew.contains(remisionesBodegaListOldRemisionesBodega)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain RemisionesBodega " + remisionesBodegaListOldRemisionesBodega + " since its diagnosticoBodega field is not nullable.");
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
            diagnosticoBodega.setCitasBodegaList(citasBodegaListNew);
            List<UrgenciasBodega> attachedUrgenciasBodegaListNew = new ArrayList<UrgenciasBodega>();
            for (UrgenciasBodega urgenciasBodegaListNewUrgenciasBodegaToAttach : urgenciasBodegaListNew) {
                urgenciasBodegaListNewUrgenciasBodegaToAttach = em.getReference(urgenciasBodegaListNewUrgenciasBodegaToAttach.getClass(), urgenciasBodegaListNewUrgenciasBodegaToAttach.getUrgenciasBodegaPK());
                attachedUrgenciasBodegaListNew.add(urgenciasBodegaListNewUrgenciasBodegaToAttach);
            }
            urgenciasBodegaListNew = attachedUrgenciasBodegaListNew;
            diagnosticoBodega.setUrgenciasBodegaList(urgenciasBodegaListNew);
            List<RemisionesBodega> attachedRemisionesBodegaListNew = new ArrayList<RemisionesBodega>();
            for (RemisionesBodega remisionesBodegaListNewRemisionesBodegaToAttach : remisionesBodegaListNew) {
                remisionesBodegaListNewRemisionesBodegaToAttach = em.getReference(remisionesBodegaListNewRemisionesBodegaToAttach.getClass(), remisionesBodegaListNewRemisionesBodegaToAttach.getRemisionesBodegaPK());
                attachedRemisionesBodegaListNew.add(remisionesBodegaListNewRemisionesBodegaToAttach);
            }
            remisionesBodegaListNew = attachedRemisionesBodegaListNew;
            diagnosticoBodega.setRemisionesBodegaList(remisionesBodegaListNew);
            diagnosticoBodega = em.merge(diagnosticoBodega);
            for (CitasBodega citasBodegaListNewCitasBodega : citasBodegaListNew) {
                if (!citasBodegaListOld.contains(citasBodegaListNewCitasBodega)) {
                    DiagnosticoBodega oldDiagnosticoBodegaOfCitasBodegaListNewCitasBodega = citasBodegaListNewCitasBodega.getDiagnosticoBodega();
                    citasBodegaListNewCitasBodega.setDiagnosticoBodega(diagnosticoBodega);
                    citasBodegaListNewCitasBodega = em.merge(citasBodegaListNewCitasBodega);
                    if (oldDiagnosticoBodegaOfCitasBodegaListNewCitasBodega != null && !oldDiagnosticoBodegaOfCitasBodegaListNewCitasBodega.equals(diagnosticoBodega)) {
                        oldDiagnosticoBodegaOfCitasBodegaListNewCitasBodega.getCitasBodegaList().remove(citasBodegaListNewCitasBodega);
                        oldDiagnosticoBodegaOfCitasBodegaListNewCitasBodega = em.merge(oldDiagnosticoBodegaOfCitasBodegaListNewCitasBodega);
                    }
                }
            }
            for (UrgenciasBodega urgenciasBodegaListOldUrgenciasBodega : urgenciasBodegaListOld) {
                if (!urgenciasBodegaListNew.contains(urgenciasBodegaListOldUrgenciasBodega)) {
                    urgenciasBodegaListOldUrgenciasBodega.setDiagnosticoKey(null);
                    urgenciasBodegaListOldUrgenciasBodega = em.merge(urgenciasBodegaListOldUrgenciasBodega);
                }
            }
            for (UrgenciasBodega urgenciasBodegaListNewUrgenciasBodega : urgenciasBodegaListNew) {
                if (!urgenciasBodegaListOld.contains(urgenciasBodegaListNewUrgenciasBodega)) {
                    DiagnosticoBodega oldDiagnosticoKeyOfUrgenciasBodegaListNewUrgenciasBodega = urgenciasBodegaListNewUrgenciasBodega.getDiagnosticoKey();
                    urgenciasBodegaListNewUrgenciasBodega.setDiagnosticoKey(diagnosticoBodega);
                    urgenciasBodegaListNewUrgenciasBodega = em.merge(urgenciasBodegaListNewUrgenciasBodega);
                    if (oldDiagnosticoKeyOfUrgenciasBodegaListNewUrgenciasBodega != null && !oldDiagnosticoKeyOfUrgenciasBodegaListNewUrgenciasBodega.equals(diagnosticoBodega)) {
                        oldDiagnosticoKeyOfUrgenciasBodegaListNewUrgenciasBodega.getUrgenciasBodegaList().remove(urgenciasBodegaListNewUrgenciasBodega);
                        oldDiagnosticoKeyOfUrgenciasBodegaListNewUrgenciasBodega = em.merge(oldDiagnosticoKeyOfUrgenciasBodegaListNewUrgenciasBodega);
                    }
                }
            }
            for (RemisionesBodega remisionesBodegaListNewRemisionesBodega : remisionesBodegaListNew) {
                if (!remisionesBodegaListOld.contains(remisionesBodegaListNewRemisionesBodega)) {
                    DiagnosticoBodega oldDiagnosticoBodegaOfRemisionesBodegaListNewRemisionesBodega = remisionesBodegaListNewRemisionesBodega.getDiagnosticoBodega();
                    remisionesBodegaListNewRemisionesBodega.setDiagnosticoBodega(diagnosticoBodega);
                    remisionesBodegaListNewRemisionesBodega = em.merge(remisionesBodegaListNewRemisionesBodega);
                    if (oldDiagnosticoBodegaOfRemisionesBodegaListNewRemisionesBodega != null && !oldDiagnosticoBodegaOfRemisionesBodegaListNewRemisionesBodega.equals(diagnosticoBodega)) {
                        oldDiagnosticoBodegaOfRemisionesBodegaListNewRemisionesBodega.getRemisionesBodegaList().remove(remisionesBodegaListNewRemisionesBodega);
                        oldDiagnosticoBodegaOfRemisionesBodegaListNewRemisionesBodega = em.merge(oldDiagnosticoBodegaOfRemisionesBodegaListNewRemisionesBodega);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = diagnosticoBodega.getDiagnosticoKey();
                if (findDiagnosticoBodega(id) == null) {
                    throw new NonexistentEntityException("The diagnosticoBodega with id " + id + " no longer exists.");
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
            DiagnosticoBodega diagnosticoBodega;
            try {
                diagnosticoBodega = em.getReference(DiagnosticoBodega.class, id);
                diagnosticoBodega.getDiagnosticoKey();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The diagnosticoBodega with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<CitasBodega> citasBodegaListOrphanCheck = diagnosticoBodega.getCitasBodegaList();
            for (CitasBodega citasBodegaListOrphanCheckCitasBodega : citasBodegaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This DiagnosticoBodega (" + diagnosticoBodega + ") cannot be destroyed since the CitasBodega " + citasBodegaListOrphanCheckCitasBodega + " in its citasBodegaList field has a non-nullable diagnosticoBodega field.");
            }
            List<RemisionesBodega> remisionesBodegaListOrphanCheck = diagnosticoBodega.getRemisionesBodegaList();
            for (RemisionesBodega remisionesBodegaListOrphanCheckRemisionesBodega : remisionesBodegaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This DiagnosticoBodega (" + diagnosticoBodega + ") cannot be destroyed since the RemisionesBodega " + remisionesBodegaListOrphanCheckRemisionesBodega + " in its remisionesBodegaList field has a non-nullable diagnosticoBodega field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<UrgenciasBodega> urgenciasBodegaList = diagnosticoBodega.getUrgenciasBodegaList();
            for (UrgenciasBodega urgenciasBodegaListUrgenciasBodega : urgenciasBodegaList) {
                urgenciasBodegaListUrgenciasBodega.setDiagnosticoKey(null);
                urgenciasBodegaListUrgenciasBodega = em.merge(urgenciasBodegaListUrgenciasBodega);
            }
            em.remove(diagnosticoBodega);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<DiagnosticoBodega> findDiagnosticoBodegaEntities() {
        return findDiagnosticoBodegaEntities(true, -1, -1);
    }

    public List<DiagnosticoBodega> findDiagnosticoBodegaEntities(int maxResults, int firstResult) {
        return findDiagnosticoBodegaEntities(false, maxResults, firstResult);
    }

    private List<DiagnosticoBodega> findDiagnosticoBodegaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(DiagnosticoBodega.class));
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

    public DiagnosticoBodega findDiagnosticoBodega(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DiagnosticoBodega.class, id);
        } finally {
            em.close();
        }
    }

    public int getDiagnosticoBodegaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DiagnosticoBodega> rt = cq.from(DiagnosticoBodega.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
     public Boolean consultarPorNombre(String nombre) {         
       
         List lista;
         int contador=0;
        
              lista=findDiagnosticoBodegaEntities();
             System.out.println(lista);           
        
        
         if(lista.isEmpty()){
             return false;
         }
          for (int i = contador; i < lista.size(); i++) {
            DiagnosticoBodega d=(DiagnosticoBodega)lista.get(i);
            if(d.getDescripcion().toString().equalsIgnoreCase(nombre)){
                return true;
            }
           }          

        return false;
    }
    
}
