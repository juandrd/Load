/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladorJPA;

import ControladorJPA.exceptions.NonexistentEntityException;
import ControladorJPA.exceptions.PreexistingEntityException;
import ControladorJPABodega.DiagnosticoBodegaJpaController;
import ControladorJPABodega.FabricaBodega;
import Entidades_Bodega.DiagnosticoBodega;
import Entidades_DB.CitasGenerales;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entidades_DB.Medico;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author USER
 */
public class CitasGeneralesJpaController implements Serializable {

    private FabricaBodega fabrica_bodega;

    public CitasGeneralesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CitasGenerales citasGenerales) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Medico IDMedico = citasGenerales.getIDMedico();
            if (IDMedico != null) {
                IDMedico = em.getReference(IDMedico.getClass(), IDMedico.getCedula());
                citasGenerales.setIDMedico(IDMedico);
            }
            em.persist(citasGenerales);
            if (IDMedico != null) {
                IDMedico.getCitasGeneralesList().add(citasGenerales);
                IDMedico = em.merge(IDMedico);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCitasGenerales(citasGenerales.getCodigocita()) != null) {
                throw new PreexistingEntityException("CitasGenerales " + citasGenerales + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CitasGenerales citasGenerales) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CitasGenerales persistentCitasGenerales = em.find(CitasGenerales.class, citasGenerales.getCodigocita());
            Medico IDMedicoOld = persistentCitasGenerales.getIDMedico();
            Medico IDMedicoNew = citasGenerales.getIDMedico();
            if (IDMedicoNew != null) {
                IDMedicoNew = em.getReference(IDMedicoNew.getClass(), IDMedicoNew.getCedula());
                citasGenerales.setIDMedico(IDMedicoNew);
            }
            citasGenerales = em.merge(citasGenerales);
            if (IDMedicoOld != null && !IDMedicoOld.equals(IDMedicoNew)) {
                IDMedicoOld.getCitasGeneralesList().remove(citasGenerales);
                IDMedicoOld = em.merge(IDMedicoOld);
            }
            if (IDMedicoNew != null && !IDMedicoNew.equals(IDMedicoOld)) {
                IDMedicoNew.getCitasGeneralesList().add(citasGenerales);
                IDMedicoNew = em.merge(IDMedicoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = citasGenerales.getCodigocita();
                if (findCitasGenerales(id) == null) {
                    throw new NonexistentEntityException("The citasGenerales with id " + id + " no longer exists.");
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
            CitasGenerales citasGenerales;
            try {
                citasGenerales = em.getReference(CitasGenerales.class, id);
                citasGenerales.getCodigocita();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The citasGenerales with id " + id + " no longer exists.", enfe);
            }
            Medico IDMedico = citasGenerales.getIDMedico();
            if (IDMedico != null) {
                IDMedico.getCitasGeneralesList().remove(citasGenerales);
                IDMedico = em.merge(IDMedico);
            }
            em.remove(citasGenerales);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CitasGenerales> findCitasGeneralesEntities() {
        return findCitasGeneralesEntities(true, -1, -1);
    }

    public List<CitasGenerales> findCitasGeneralesEntities(int maxResults, int firstResult) {
        return findCitasGeneralesEntities(false, maxResults, firstResult);
    }

    private List<CitasGenerales> findCitasGeneralesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CitasGenerales.class));
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

    public CitasGenerales findCitasGenerales(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CitasGenerales.class, id);
        } finally {
            em.close();
        }
    }

    public int getCitasGeneralesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CitasGenerales> rt = cq.from(CitasGenerales.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public void crearDiagnostico() throws ControladorJPABodega.exceptions.PreexistingEntityException, Exception {
        fabrica_bodega = new FabricaBodega();
        DiagnosticoBodegaJpaController controladorDiag = new DiagnosticoBodegaJpaController(fabrica_bodega.getFactory());

        List lista;
        lista = findCitasGeneralesEntities();
        int contador = 0;

        contador = controladorDiag.getDiagnosticoBodegaCount();
        System.err.println("Cita"+contador);
        for (int i = 0; i < lista.size(); i++) {

            CitasGenerales cita = (CitasGenerales) lista.get(i);
            DiagnosticoBodega diag = new DiagnosticoBodega();

            if (controladorDiag.consultarPorNombre(cita.getDiagnostico().toString())) {
            //    System.err.println(contador + 1 + " " + cita.getDiagnostico().toString());
            } else {
                diag.setDiagnosticoKey(contador + 1);
                System.out.println(contador + 1 + " " + cita.getDiagnostico().toString());
                diag.setDescripcion(cita.getDiagnostico().toString());
                controladorDiag.create(diag);
                contador++;
            }
        }
    }
}
