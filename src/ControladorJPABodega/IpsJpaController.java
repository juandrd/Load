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
import Entidades_DB.Drogueria;
import java.util.ArrayList;
import java.util.List;
import Entidades_DB.Cotizante;
import Entidades_DB.Ips;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author USER
 */
public class IpsJpaController implements Serializable {

    public IpsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Ips ips) throws PreexistingEntityException, Exception {
        if (ips.getDrogueriaList() == null) {
            ips.setDrogueriaList(new ArrayList<Drogueria>());
        }
        if (ips.getCotizanteList() == null) {
            ips.setCotizanteList(new ArrayList<Cotizante>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Drogueria> attachedDrogueriaList = new ArrayList<Drogueria>();
            for (Drogueria drogueriaListDrogueriaToAttach : ips.getDrogueriaList()) {
                drogueriaListDrogueriaToAttach = em.getReference(drogueriaListDrogueriaToAttach.getClass(), drogueriaListDrogueriaToAttach.getIDDrogueria());
                attachedDrogueriaList.add(drogueriaListDrogueriaToAttach);
            }
            ips.setDrogueriaList(attachedDrogueriaList);
            List<Cotizante> attachedCotizanteList = new ArrayList<Cotizante>();
            for (Cotizante cotizanteListCotizanteToAttach : ips.getCotizanteList()) {
                cotizanteListCotizanteToAttach = em.getReference(cotizanteListCotizanteToAttach.getClass(), cotizanteListCotizanteToAttach.getCedula());
                attachedCotizanteList.add(cotizanteListCotizanteToAttach);
            }
            ips.setCotizanteList(attachedCotizanteList);
            em.persist(ips);
            for (Drogueria drogueriaListDrogueria : ips.getDrogueriaList()) {
                drogueriaListDrogueria.getIpsList().add(ips);
                drogueriaListDrogueria = em.merge(drogueriaListDrogueria);
            }
            for (Cotizante cotizanteListCotizante : ips.getCotizanteList()) {
                Ips oldIdIpsOfCotizanteListCotizante = cotizanteListCotizante.getIdIps();
                cotizanteListCotizante.setIdIps(ips);
                cotizanteListCotizante = em.merge(cotizanteListCotizante);
                if (oldIdIpsOfCotizanteListCotizante != null) {
                    oldIdIpsOfCotizanteListCotizante.getCotizanteList().remove(cotizanteListCotizante);
                    oldIdIpsOfCotizanteListCotizante = em.merge(oldIdIpsOfCotizanteListCotizante);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findIps(ips.getIdIps()) != null) {
                throw new PreexistingEntityException("Ips " + ips + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Ips ips) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ips persistentIps = em.find(Ips.class, ips.getIdIps());
            List<Drogueria> drogueriaListOld = persistentIps.getDrogueriaList();
            List<Drogueria> drogueriaListNew = ips.getDrogueriaList();
            List<Cotizante> cotizanteListOld = persistentIps.getCotizanteList();
            List<Cotizante> cotizanteListNew = ips.getCotizanteList();
            List<Drogueria> attachedDrogueriaListNew = new ArrayList<Drogueria>();
            for (Drogueria drogueriaListNewDrogueriaToAttach : drogueriaListNew) {
                drogueriaListNewDrogueriaToAttach = em.getReference(drogueriaListNewDrogueriaToAttach.getClass(), drogueriaListNewDrogueriaToAttach.getIDDrogueria());
                attachedDrogueriaListNew.add(drogueriaListNewDrogueriaToAttach);
            }
            drogueriaListNew = attachedDrogueriaListNew;
            ips.setDrogueriaList(drogueriaListNew);
            List<Cotizante> attachedCotizanteListNew = new ArrayList<Cotizante>();
            for (Cotizante cotizanteListNewCotizanteToAttach : cotizanteListNew) {
                cotizanteListNewCotizanteToAttach = em.getReference(cotizanteListNewCotizanteToAttach.getClass(), cotizanteListNewCotizanteToAttach.getCedula());
                attachedCotizanteListNew.add(cotizanteListNewCotizanteToAttach);
            }
            cotizanteListNew = attachedCotizanteListNew;
            ips.setCotizanteList(cotizanteListNew);
            ips = em.merge(ips);
            for (Drogueria drogueriaListOldDrogueria : drogueriaListOld) {
                if (!drogueriaListNew.contains(drogueriaListOldDrogueria)) {
                    drogueriaListOldDrogueria.getIpsList().remove(ips);
                    drogueriaListOldDrogueria = em.merge(drogueriaListOldDrogueria);
                }
            }
            for (Drogueria drogueriaListNewDrogueria : drogueriaListNew) {
                if (!drogueriaListOld.contains(drogueriaListNewDrogueria)) {
                    drogueriaListNewDrogueria.getIpsList().add(ips);
                    drogueriaListNewDrogueria = em.merge(drogueriaListNewDrogueria);
                }
            }
            for (Cotizante cotizanteListOldCotizante : cotizanteListOld) {
                if (!cotizanteListNew.contains(cotizanteListOldCotizante)) {
                    cotizanteListOldCotizante.setIdIps(null);
                    cotizanteListOldCotizante = em.merge(cotizanteListOldCotizante);
                }
            }
            for (Cotizante cotizanteListNewCotizante : cotizanteListNew) {
                if (!cotizanteListOld.contains(cotizanteListNewCotizante)) {
                    Ips oldIdIpsOfCotizanteListNewCotizante = cotizanteListNewCotizante.getIdIps();
                    cotizanteListNewCotizante.setIdIps(ips);
                    cotizanteListNewCotizante = em.merge(cotizanteListNewCotizante);
                    if (oldIdIpsOfCotizanteListNewCotizante != null && !oldIdIpsOfCotizanteListNewCotizante.equals(ips)) {
                        oldIdIpsOfCotizanteListNewCotizante.getCotizanteList().remove(cotizanteListNewCotizante);
                        oldIdIpsOfCotizanteListNewCotizante = em.merge(oldIdIpsOfCotizanteListNewCotizante);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = ips.getIdIps();
                if (findIps(id) == null) {
                    throw new NonexistentEntityException("The ips with id " + id + " no longer exists.");
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
            Ips ips;
            try {
                ips = em.getReference(Ips.class, id);
                ips.getIdIps();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ips with id " + id + " no longer exists.", enfe);
            }
            List<Drogueria> drogueriaList = ips.getDrogueriaList();
            for (Drogueria drogueriaListDrogueria : drogueriaList) {
                drogueriaListDrogueria.getIpsList().remove(ips);
                drogueriaListDrogueria = em.merge(drogueriaListDrogueria);
            }
            List<Cotizante> cotizanteList = ips.getCotizanteList();
            for (Cotizante cotizanteListCotizante : cotizanteList) {
                cotizanteListCotizante.setIdIps(null);
                cotizanteListCotizante = em.merge(cotizanteListCotizante);
            }
            em.remove(ips);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Ips> findIpsEntities() {
        return findIpsEntities(true, -1, -1);
    }

    public List<Ips> findIpsEntities(int maxResults, int firstResult) {
        return findIpsEntities(false, maxResults, firstResult);
    }

    private List<Ips> findIpsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Ips.class));
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

    public Ips findIps(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Ips.class, id);
        } finally {
            em.close();
        }
    }

    public int getIpsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Ips> rt = cq.from(Ips.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
