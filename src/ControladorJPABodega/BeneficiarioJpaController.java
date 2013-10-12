/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladorJPABodega;

import ControladorJPABodega.exceptions.NonexistentEntityException;
import ControladorJPABodega.exceptions.PreexistingEntityException;
import Entidades_DB.Beneficiario;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entidades_DB.Cotizante;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author USER
 */
public class BeneficiarioJpaController implements Serializable {

    public BeneficiarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Beneficiario beneficiario) throws PreexistingEntityException, Exception {
        if (beneficiario.getCotizanteList() == null) {
            beneficiario.setCotizanteList(new ArrayList<Cotizante>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Cotizante> attachedCotizanteList = new ArrayList<Cotizante>();
            for (Cotizante cotizanteListCotizanteToAttach : beneficiario.getCotizanteList()) {
                cotizanteListCotizanteToAttach = em.getReference(cotizanteListCotizanteToAttach.getClass(), cotizanteListCotizanteToAttach.getCedula());
                attachedCotizanteList.add(cotizanteListCotizanteToAttach);
            }
            beneficiario.setCotizanteList(attachedCotizanteList);
            em.persist(beneficiario);
            for (Cotizante cotizanteListCotizante : beneficiario.getCotizanteList()) {
                cotizanteListCotizante.getBeneficiarioList().add(beneficiario);
                cotizanteListCotizante = em.merge(cotizanteListCotizante);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findBeneficiario(beneficiario.getIDBeneficiario()) != null) {
                throw new PreexistingEntityException("Beneficiario " + beneficiario + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Beneficiario beneficiario) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Beneficiario persistentBeneficiario = em.find(Beneficiario.class, beneficiario.getIDBeneficiario());
            List<Cotizante> cotizanteListOld = persistentBeneficiario.getCotizanteList();
            List<Cotizante> cotizanteListNew = beneficiario.getCotizanteList();
            List<Cotizante> attachedCotizanteListNew = new ArrayList<Cotizante>();
            for (Cotizante cotizanteListNewCotizanteToAttach : cotizanteListNew) {
                cotizanteListNewCotizanteToAttach = em.getReference(cotizanteListNewCotizanteToAttach.getClass(), cotizanteListNewCotizanteToAttach.getCedula());
                attachedCotizanteListNew.add(cotizanteListNewCotizanteToAttach);
            }
            cotizanteListNew = attachedCotizanteListNew;
            beneficiario.setCotizanteList(cotizanteListNew);
            beneficiario = em.merge(beneficiario);
            for (Cotizante cotizanteListOldCotizante : cotizanteListOld) {
                if (!cotizanteListNew.contains(cotizanteListOldCotizante)) {
                    cotizanteListOldCotizante.getBeneficiarioList().remove(beneficiario);
                    cotizanteListOldCotizante = em.merge(cotizanteListOldCotizante);
                }
            }
            for (Cotizante cotizanteListNewCotizante : cotizanteListNew) {
                if (!cotizanteListOld.contains(cotizanteListNewCotizante)) {
                    cotizanteListNewCotizante.getBeneficiarioList().add(beneficiario);
                    cotizanteListNewCotizante = em.merge(cotizanteListNewCotizante);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = beneficiario.getIDBeneficiario();
                if (findBeneficiario(id) == null) {
                    throw new NonexistentEntityException("The beneficiario with id " + id + " no longer exists.");
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
            Beneficiario beneficiario;
            try {
                beneficiario = em.getReference(Beneficiario.class, id);
                beneficiario.getIDBeneficiario();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The beneficiario with id " + id + " no longer exists.", enfe);
            }
            List<Cotizante> cotizanteList = beneficiario.getCotizanteList();
            for (Cotizante cotizanteListCotizante : cotizanteList) {
                cotizanteListCotizante.getBeneficiarioList().remove(beneficiario);
                cotizanteListCotizante = em.merge(cotizanteListCotizante);
            }
            em.remove(beneficiario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Beneficiario> findBeneficiarioEntities() {
        return findBeneficiarioEntities(true, -1, -1);
    }

    public List<Beneficiario> findBeneficiarioEntities(int maxResults, int firstResult) {
        return findBeneficiarioEntities(false, maxResults, firstResult);
    }

    private List<Beneficiario> findBeneficiarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Beneficiario.class));
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

    public Beneficiario findBeneficiario(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Beneficiario.class, id);
        } finally {
            em.close();
        }
    }

    public int getBeneficiarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Beneficiario> rt = cq.from(Beneficiario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
