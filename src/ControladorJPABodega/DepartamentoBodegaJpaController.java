/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladorJPABodega;

import ControladorJPABodega.exceptions.NonexistentEntityException;
import ControladorJPABodega.exceptions.PreexistingEntityException;
import Entidades_Bodega.DepartamentoBodega;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entidades_Bodega.IpsBodega;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author USER
 */
public class DepartamentoBodegaJpaController implements Serializable {

    public DepartamentoBodegaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(DepartamentoBodega departamentoBodega) throws PreexistingEntityException, Exception {
        if (departamentoBodega.getIpsBodegaList() == null) {
            departamentoBodega.setIpsBodegaList(new ArrayList<IpsBodega>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<IpsBodega> attachedIpsBodegaList = new ArrayList<IpsBodega>();
            for (IpsBodega ipsBodegaListIpsBodegaToAttach : departamentoBodega.getIpsBodegaList()) {
                ipsBodegaListIpsBodegaToAttach = em.getReference(ipsBodegaListIpsBodegaToAttach.getClass(), ipsBodegaListIpsBodegaToAttach.getIpsKey());
                attachedIpsBodegaList.add(ipsBodegaListIpsBodegaToAttach);
            }
            departamentoBodega.setIpsBodegaList(attachedIpsBodegaList);
            em.persist(departamentoBodega);
            for (IpsBodega ipsBodegaListIpsBodega : departamentoBodega.getIpsBodegaList()) {
                DepartamentoBodega oldDepartamentoOfIpsBodegaListIpsBodega = ipsBodegaListIpsBodega.getDepartamento();
                ipsBodegaListIpsBodega.setDepartamento(departamentoBodega);
                ipsBodegaListIpsBodega = em.merge(ipsBodegaListIpsBodega);
                if (oldDepartamentoOfIpsBodegaListIpsBodega != null) {
                    oldDepartamentoOfIpsBodegaListIpsBodega.getIpsBodegaList().remove(ipsBodegaListIpsBodega);
                    oldDepartamentoOfIpsBodegaListIpsBodega = em.merge(oldDepartamentoOfIpsBodegaListIpsBodega);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDepartamentoBodega(departamentoBodega.getCodDpto()) != null) {
                throw new PreexistingEntityException("DepartamentoBodega " + departamentoBodega + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DepartamentoBodega departamentoBodega) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DepartamentoBodega persistentDepartamentoBodega = em.find(DepartamentoBodega.class, departamentoBodega.getCodDpto());
            List<IpsBodega> ipsBodegaListOld = persistentDepartamentoBodega.getIpsBodegaList();
            List<IpsBodega> ipsBodegaListNew = departamentoBodega.getIpsBodegaList();
            List<IpsBodega> attachedIpsBodegaListNew = new ArrayList<IpsBodega>();
            for (IpsBodega ipsBodegaListNewIpsBodegaToAttach : ipsBodegaListNew) {
                ipsBodegaListNewIpsBodegaToAttach = em.getReference(ipsBodegaListNewIpsBodegaToAttach.getClass(), ipsBodegaListNewIpsBodegaToAttach.getIpsKey());
                attachedIpsBodegaListNew.add(ipsBodegaListNewIpsBodegaToAttach);
            }
            ipsBodegaListNew = attachedIpsBodegaListNew;
            departamentoBodega.setIpsBodegaList(ipsBodegaListNew);
            departamentoBodega = em.merge(departamentoBodega);
            for (IpsBodega ipsBodegaListOldIpsBodega : ipsBodegaListOld) {
                if (!ipsBodegaListNew.contains(ipsBodegaListOldIpsBodega)) {
                    ipsBodegaListOldIpsBodega.setDepartamento(null);
                    ipsBodegaListOldIpsBodega = em.merge(ipsBodegaListOldIpsBodega);
                }
            }
            for (IpsBodega ipsBodegaListNewIpsBodega : ipsBodegaListNew) {
                if (!ipsBodegaListOld.contains(ipsBodegaListNewIpsBodega)) {
                    DepartamentoBodega oldDepartamentoOfIpsBodegaListNewIpsBodega = ipsBodegaListNewIpsBodega.getDepartamento();
                    ipsBodegaListNewIpsBodega.setDepartamento(departamentoBodega);
                    ipsBodegaListNewIpsBodega = em.merge(ipsBodegaListNewIpsBodega);
                    if (oldDepartamentoOfIpsBodegaListNewIpsBodega != null && !oldDepartamentoOfIpsBodegaListNewIpsBodega.equals(departamentoBodega)) {
                        oldDepartamentoOfIpsBodegaListNewIpsBodega.getIpsBodegaList().remove(ipsBodegaListNewIpsBodega);
                        oldDepartamentoOfIpsBodegaListNewIpsBodega = em.merge(oldDepartamentoOfIpsBodegaListNewIpsBodega);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = departamentoBodega.getCodDpto();
                if (findDepartamentoBodega(id) == null) {
                    throw new NonexistentEntityException("The departamentoBodega with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DepartamentoBodega departamentoBodega;
            try {
                departamentoBodega = em.getReference(DepartamentoBodega.class, id);
                departamentoBodega.getCodDpto();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The departamentoBodega with id " + id + " no longer exists.", enfe);
            }
            List<IpsBodega> ipsBodegaList = departamentoBodega.getIpsBodegaList();
            for (IpsBodega ipsBodegaListIpsBodega : ipsBodegaList) {
                ipsBodegaListIpsBodega.setDepartamento(null);
                ipsBodegaListIpsBodega = em.merge(ipsBodegaListIpsBodega);
            }
            em.remove(departamentoBodega);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<DepartamentoBodega> findDepartamentoBodegaEntities() {
        return findDepartamentoBodegaEntities(true, -1, -1);
    }

    public List<DepartamentoBodega> findDepartamentoBodegaEntities(int maxResults, int firstResult) {
        return findDepartamentoBodegaEntities(false, maxResults, firstResult);
    }

    private List<DepartamentoBodega> findDepartamentoBodegaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(DepartamentoBodega.class));
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

    public DepartamentoBodega findDepartamentoBodega(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DepartamentoBodega.class, id);
        } finally {
            em.close();
        }
    }

    public int getDepartamentoBodegaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DepartamentoBodega> rt = cq.from(DepartamentoBodega.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
     public DepartamentoBodega consultarPorNombre(String nombre) {

         DepartamentoBodega depto=new DepartamentoBodega();
         List lista;
         lista=findDepartamentoBodegaEntities();
         
          for (int i = 0; i < lista.size(); i++) {
              DepartamentoBodega d=(DepartamentoBodega) lista.get(i);
              if(d.getNombre().toString().equalsIgnoreCase(nombre)){                  
                  depto=d;
              }
          }

        return depto;
    }
    
}
