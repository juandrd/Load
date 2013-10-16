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
import Entidades_DB.Hospitalizaciones;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entidades_DB.Medico;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author USER
 */
public class HospitalizacionesJpaController implements Serializable {
private FabricaBodega fabrica_bodega;
    public HospitalizacionesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Hospitalizaciones hospitalizaciones) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Medico IDMedico = hospitalizaciones.getIDMedico();
            if (IDMedico != null) {
                IDMedico = em.getReference(IDMedico.getClass(), IDMedico.getCedula());
                hospitalizaciones.setIDMedico(IDMedico);
            }
            em.persist(hospitalizaciones);
            if (IDMedico != null) {
                IDMedico.getHospitalizacionesList().add(hospitalizaciones);
                IDMedico = em.merge(IDMedico);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findHospitalizaciones(hospitalizaciones.getCodigoHospitalizacion()) != null) {
                throw new PreexistingEntityException("Hospitalizaciones " + hospitalizaciones + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Hospitalizaciones hospitalizaciones) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Hospitalizaciones persistentHospitalizaciones = em.find(Hospitalizaciones.class, hospitalizaciones.getCodigoHospitalizacion());
            Medico IDMedicoOld = persistentHospitalizaciones.getIDMedico();
            Medico IDMedicoNew = hospitalizaciones.getIDMedico();
            if (IDMedicoNew != null) {
                IDMedicoNew = em.getReference(IDMedicoNew.getClass(), IDMedicoNew.getCedula());
                hospitalizaciones.setIDMedico(IDMedicoNew);
            }
            hospitalizaciones = em.merge(hospitalizaciones);
            if (IDMedicoOld != null && !IDMedicoOld.equals(IDMedicoNew)) {
                IDMedicoOld.getHospitalizacionesList().remove(hospitalizaciones);
                IDMedicoOld = em.merge(IDMedicoOld);
            }
            if (IDMedicoNew != null && !IDMedicoNew.equals(IDMedicoOld)) {
                IDMedicoNew.getHospitalizacionesList().add(hospitalizaciones);
                IDMedicoNew = em.merge(IDMedicoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = hospitalizaciones.getCodigoHospitalizacion();
                if (findHospitalizaciones(id) == null) {
                    throw new NonexistentEntityException("The hospitalizaciones with id " + id + " no longer exists.");
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
            Hospitalizaciones hospitalizaciones;
            try {
                hospitalizaciones = em.getReference(Hospitalizaciones.class, id);
                hospitalizaciones.getCodigoHospitalizacion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The hospitalizaciones with id " + id + " no longer exists.", enfe);
            }
            Medico IDMedico = hospitalizaciones.getIDMedico();
            if (IDMedico != null) {
                IDMedico.getHospitalizacionesList().remove(hospitalizaciones);
                IDMedico = em.merge(IDMedico);
            }
            em.remove(hospitalizaciones);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Hospitalizaciones> findHospitalizacionesEntities() {
        return findHospitalizacionesEntities(true, -1, -1);
    }

    public List<Hospitalizaciones> findHospitalizacionesEntities(int maxResults, int firstResult) {
        return findHospitalizacionesEntities(false, maxResults, firstResult);
    }

    private List<Hospitalizaciones> findHospitalizacionesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Hospitalizaciones.class));
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

    public Hospitalizaciones findHospitalizaciones(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Hospitalizaciones.class, id);
        } finally {
            em.close();
        }
    }

    public int getHospitalizacionesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Hospitalizaciones> rt = cq.from(Hospitalizaciones.class);
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
        lista = findHospitalizacionesEntities();
        int contador = 0;

        contador = controladorDiag.getDiagnosticoBodegaCount();
        System.err.println("Hospitalizacion"+contador);
        for (int i = 0; i < lista.size(); i++) {

            Hospitalizaciones h = (Hospitalizaciones) lista.get(i);
            DiagnosticoBodega diag = new DiagnosticoBodega();

            if (controladorDiag.consultarPorNombre(h.getDiagnostico().toString())) {
            //    System.err.println(contador + 1 + " " + cita.getDiagnostico().toString());
            } else {
                diag.setDiagnosticoKey(contador + 1);
                System.out.println(contador + 1 + " " + h.getDiagnostico().toString());
                diag.setDescripcion(h.getDiagnostico().toString());
                controladorDiag.create(diag);
                contador++;
            }
        }
    }
    
}
