/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladorJPA;

import ControladorJPA.exceptions.NonexistentEntityException;
import ControladorJPA.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entidades_DB.Cotizante;
import Entidades_DB.Empresa;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author USER
 */
public class EmpresaJpaController implements Serializable {

    public EmpresaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Empresa empresa) throws PreexistingEntityException, Exception {
        if (empresa.getCotizanteList() == null) {
            empresa.setCotizanteList(new ArrayList<Cotizante>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Cotizante> attachedCotizanteList = new ArrayList<Cotizante>();
            for (Cotizante cotizanteListCotizanteToAttach : empresa.getCotizanteList()) {
                cotizanteListCotizanteToAttach = em.getReference(cotizanteListCotizanteToAttach.getClass(), cotizanteListCotizanteToAttach.getCedula());
                attachedCotizanteList.add(cotizanteListCotizanteToAttach);
            }
            empresa.setCotizanteList(attachedCotizanteList);
            em.persist(empresa);
            for (Cotizante cotizanteListCotizante : empresa.getCotizanteList()) {
                cotizanteListCotizante.getEmpresaList().add(empresa);
                cotizanteListCotizante = em.merge(cotizanteListCotizante);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEmpresa(empresa.getNit()) != null) {
                throw new PreexistingEntityException("Empresa " + empresa + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Empresa empresa) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empresa persistentEmpresa = em.find(Empresa.class, empresa.getNit());
            List<Cotizante> cotizanteListOld = persistentEmpresa.getCotizanteList();
            List<Cotizante> cotizanteListNew = empresa.getCotizanteList();
            List<Cotizante> attachedCotizanteListNew = new ArrayList<Cotizante>();
            for (Cotizante cotizanteListNewCotizanteToAttach : cotizanteListNew) {
                cotizanteListNewCotizanteToAttach = em.getReference(cotizanteListNewCotizanteToAttach.getClass(), cotizanteListNewCotizanteToAttach.getCedula());
                attachedCotizanteListNew.add(cotizanteListNewCotizanteToAttach);
            }
            cotizanteListNew = attachedCotizanteListNew;
            empresa.setCotizanteList(cotizanteListNew);
            empresa = em.merge(empresa);
            for (Cotizante cotizanteListOldCotizante : cotizanteListOld) {
                if (!cotizanteListNew.contains(cotizanteListOldCotizante)) {
                    cotizanteListOldCotizante.getEmpresaList().remove(empresa);
                    cotizanteListOldCotizante = em.merge(cotizanteListOldCotizante);
                }
            }
            for (Cotizante cotizanteListNewCotizante : cotizanteListNew) {
                if (!cotizanteListOld.contains(cotizanteListNewCotizante)) {
                    cotizanteListNewCotizante.getEmpresaList().add(empresa);
                    cotizanteListNewCotizante = em.merge(cotizanteListNewCotizante);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = empresa.getNit();
                if (findEmpresa(id) == null) {
                    throw new NonexistentEntityException("The empresa with id " + id + " no longer exists.");
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
            Empresa empresa;
            try {
                empresa = em.getReference(Empresa.class, id);
                empresa.getNit();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The empresa with id " + id + " no longer exists.", enfe);
            }
            List<Cotizante> cotizanteList = empresa.getCotizanteList();
            for (Cotizante cotizanteListCotizante : cotizanteList) {
                cotizanteListCotizante.getEmpresaList().remove(empresa);
                cotizanteListCotizante = em.merge(cotizanteListCotizante);
            }
            em.remove(empresa);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Empresa> findEmpresaEntities() {
        return findEmpresaEntities(true, -1, -1);
    }

    public List<Empresa> findEmpresaEntities(int maxResults, int firstResult) {
        return findEmpresaEntities(false, maxResults, firstResult);
    }

    private List<Empresa> findEmpresaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Empresa.class));
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

    public Empresa findEmpresa(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Empresa.class, id);
        } finally {
            em.close();
        }
    }

    public int getEmpresaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Empresa> rt = cq.from(Empresa.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
