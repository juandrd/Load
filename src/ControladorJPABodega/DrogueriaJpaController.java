/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladorJPABodega;

import ControladorJPABodega.exceptions.NonexistentEntityException;
import ControladorJPABodega.exceptions.PreexistingEntityException;
import Entidades_DB.Drogueria;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entidades_DB.Ips;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author USER
 */
public class DrogueriaJpaController implements Serializable {

    public DrogueriaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Drogueria drogueria) throws PreexistingEntityException, Exception {
        if (drogueria.getIpsList() == null) {
            drogueria.setIpsList(new ArrayList<Ips>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Ips> attachedIpsList = new ArrayList<Ips>();
            for (Ips ipsListIpsToAttach : drogueria.getIpsList()) {
                ipsListIpsToAttach = em.getReference(ipsListIpsToAttach.getClass(), ipsListIpsToAttach.getIdIps());
                attachedIpsList.add(ipsListIpsToAttach);
            }
            drogueria.setIpsList(attachedIpsList);
            em.persist(drogueria);
            for (Ips ipsListIps : drogueria.getIpsList()) {
                ipsListIps.getDrogueriaList().add(drogueria);
                ipsListIps = em.merge(ipsListIps);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDrogueria(drogueria.getIDDrogueria()) != null) {
                throw new PreexistingEntityException("Drogueria " + drogueria + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Drogueria drogueria) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Drogueria persistentDrogueria = em.find(Drogueria.class, drogueria.getIDDrogueria());
            List<Ips> ipsListOld = persistentDrogueria.getIpsList();
            List<Ips> ipsListNew = drogueria.getIpsList();
            List<Ips> attachedIpsListNew = new ArrayList<Ips>();
            for (Ips ipsListNewIpsToAttach : ipsListNew) {
                ipsListNewIpsToAttach = em.getReference(ipsListNewIpsToAttach.getClass(), ipsListNewIpsToAttach.getIdIps());
                attachedIpsListNew.add(ipsListNewIpsToAttach);
            }
            ipsListNew = attachedIpsListNew;
            drogueria.setIpsList(ipsListNew);
            drogueria = em.merge(drogueria);
            for (Ips ipsListOldIps : ipsListOld) {
                if (!ipsListNew.contains(ipsListOldIps)) {
                    ipsListOldIps.getDrogueriaList().remove(drogueria);
                    ipsListOldIps = em.merge(ipsListOldIps);
                }
            }
            for (Ips ipsListNewIps : ipsListNew) {
                if (!ipsListOld.contains(ipsListNewIps)) {
                    ipsListNewIps.getDrogueriaList().add(drogueria);
                    ipsListNewIps = em.merge(ipsListNewIps);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = drogueria.getIDDrogueria();
                if (findDrogueria(id) == null) {
                    throw new NonexistentEntityException("The drogueria with id " + id + " no longer exists.");
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
            Drogueria drogueria;
            try {
                drogueria = em.getReference(Drogueria.class, id);
                drogueria.getIDDrogueria();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The drogueria with id " + id + " no longer exists.", enfe);
            }
            List<Ips> ipsList = drogueria.getIpsList();
            for (Ips ipsListIps : ipsList) {
                ipsListIps.getDrogueriaList().remove(drogueria);
                ipsListIps = em.merge(ipsListIps);
            }
            em.remove(drogueria);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Drogueria> findDrogueriaEntities() {
        return findDrogueriaEntities(true, -1, -1);
    }

    public List<Drogueria> findDrogueriaEntities(int maxResults, int firstResult) {
        return findDrogueriaEntities(false, maxResults, firstResult);
    }

    private List<Drogueria> findDrogueriaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Drogueria.class));
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

    public Drogueria findDrogueria(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Drogueria.class, id);
        } finally {
            em.close();
        }
    }

    public int getDrogueriaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Drogueria> rt = cq.from(Drogueria.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
