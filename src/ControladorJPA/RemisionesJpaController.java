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
import Entidades_DB.ServiciosPos;
import Entidades_DB.Medico;
import Entidades_DB.Remisiones;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author USER
 */
public class RemisionesJpaController implements Serializable {

    public RemisionesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Remisiones remisiones) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ServiciosPos servicioPOS = remisiones.getServicioPOS();
            if (servicioPOS != null) {
                servicioPOS = em.getReference(servicioPOS.getClass(), servicioPOS.getIDServicioPOS());
                remisiones.setServicioPOS(servicioPOS);
            }
            Medico IDMedicoRemite = remisiones.getIDMedicoRemite();
            if (IDMedicoRemite != null) {
                IDMedicoRemite = em.getReference(IDMedicoRemite.getClass(), IDMedicoRemite.getCedula());
                remisiones.setIDMedicoRemite(IDMedicoRemite);
            }
            Medico IDMedico = remisiones.getIDMedico();
            if (IDMedico != null) {
                IDMedico = em.getReference(IDMedico.getClass(), IDMedico.getCedula());
                remisiones.setIDMedico(IDMedico);
            }
            em.persist(remisiones);
            if (servicioPOS != null) {
                servicioPOS.getRemisionesList().add(remisiones);
                servicioPOS = em.merge(servicioPOS);
            }
            if (IDMedicoRemite != null) {
                IDMedicoRemite.getRemisionesList().add(remisiones);
                IDMedicoRemite = em.merge(IDMedicoRemite);
            }
            if (IDMedico != null) {
                IDMedico.getRemisionesList().add(remisiones);
                IDMedico = em.merge(IDMedico);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findRemisiones(remisiones.getCodigoRemision()) != null) {
                throw new PreexistingEntityException("Remisiones " + remisiones + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Remisiones remisiones) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Remisiones persistentRemisiones = em.find(Remisiones.class, remisiones.getCodigoRemision());
            ServiciosPos servicioPOSOld = persistentRemisiones.getServicioPOS();
            ServiciosPos servicioPOSNew = remisiones.getServicioPOS();
            Medico IDMedicoRemiteOld = persistentRemisiones.getIDMedicoRemite();
            Medico IDMedicoRemiteNew = remisiones.getIDMedicoRemite();
            Medico IDMedicoOld = persistentRemisiones.getIDMedico();
            Medico IDMedicoNew = remisiones.getIDMedico();
            if (servicioPOSNew != null) {
                servicioPOSNew = em.getReference(servicioPOSNew.getClass(), servicioPOSNew.getIDServicioPOS());
                remisiones.setServicioPOS(servicioPOSNew);
            }
            if (IDMedicoRemiteNew != null) {
                IDMedicoRemiteNew = em.getReference(IDMedicoRemiteNew.getClass(), IDMedicoRemiteNew.getCedula());
                remisiones.setIDMedicoRemite(IDMedicoRemiteNew);
            }
            if (IDMedicoNew != null) {
                IDMedicoNew = em.getReference(IDMedicoNew.getClass(), IDMedicoNew.getCedula());
                remisiones.setIDMedico(IDMedicoNew);
            }
            remisiones = em.merge(remisiones);
            if (servicioPOSOld != null && !servicioPOSOld.equals(servicioPOSNew)) {
                servicioPOSOld.getRemisionesList().remove(remisiones);
                servicioPOSOld = em.merge(servicioPOSOld);
            }
            if (servicioPOSNew != null && !servicioPOSNew.equals(servicioPOSOld)) {
                servicioPOSNew.getRemisionesList().add(remisiones);
                servicioPOSNew = em.merge(servicioPOSNew);
            }
            if (IDMedicoRemiteOld != null && !IDMedicoRemiteOld.equals(IDMedicoRemiteNew)) {
                IDMedicoRemiteOld.getRemisionesList().remove(remisiones);
                IDMedicoRemiteOld = em.merge(IDMedicoRemiteOld);
            }
            if (IDMedicoRemiteNew != null && !IDMedicoRemiteNew.equals(IDMedicoRemiteOld)) {
                IDMedicoRemiteNew.getRemisionesList().add(remisiones);
                IDMedicoRemiteNew = em.merge(IDMedicoRemiteNew);
            }
            if (IDMedicoOld != null && !IDMedicoOld.equals(IDMedicoNew)) {
                IDMedicoOld.getRemisionesList().remove(remisiones);
                IDMedicoOld = em.merge(IDMedicoOld);
            }
            if (IDMedicoNew != null && !IDMedicoNew.equals(IDMedicoOld)) {
                IDMedicoNew.getRemisionesList().add(remisiones);
                IDMedicoNew = em.merge(IDMedicoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = remisiones.getCodigoRemision();
                if (findRemisiones(id) == null) {
                    throw new NonexistentEntityException("The remisiones with id " + id + " no longer exists.");
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
            Remisiones remisiones;
            try {
                remisiones = em.getReference(Remisiones.class, id);
                remisiones.getCodigoRemision();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The remisiones with id " + id + " no longer exists.", enfe);
            }
            ServiciosPos servicioPOS = remisiones.getServicioPOS();
            if (servicioPOS != null) {
                servicioPOS.getRemisionesList().remove(remisiones);
                servicioPOS = em.merge(servicioPOS);
            }
            Medico IDMedicoRemite = remisiones.getIDMedicoRemite();
            if (IDMedicoRemite != null) {
                IDMedicoRemite.getRemisionesList().remove(remisiones);
                IDMedicoRemite = em.merge(IDMedicoRemite);
            }
            Medico IDMedico = remisiones.getIDMedico();
            if (IDMedico != null) {
                IDMedico.getRemisionesList().remove(remisiones);
                IDMedico = em.merge(IDMedico);
            }
            em.remove(remisiones);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Remisiones> findRemisionesEntities() {
        return findRemisionesEntities(true, -1, -1);
    }

    public List<Remisiones> findRemisionesEntities(int maxResults, int firstResult) {
        return findRemisionesEntities(false, maxResults, firstResult);
    }

    private List<Remisiones> findRemisionesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Remisiones.class));
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

    public Remisiones findRemisiones(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Remisiones.class, id);
        } finally {
            em.close();
        }
    }

    public int getRemisionesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Remisiones> rt = cq.from(Remisiones.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
