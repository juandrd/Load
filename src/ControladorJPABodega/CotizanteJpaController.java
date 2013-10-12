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
import Entidades_DB.Ips;
import Entidades_DB.Beneficiario;
import Entidades_DB.Cotizante;
import java.util.ArrayList;
import java.util.List;
import Entidades_DB.Empresa;
import Entidades_DB.Retiros;
import Entidades_DB.Pagos;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author USER
 */
public class CotizanteJpaController implements Serializable {

    public CotizanteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cotizante cotizante) throws PreexistingEntityException, Exception {
        if (cotizante.getBeneficiarioList() == null) {
            cotizante.setBeneficiarioList(new ArrayList<Beneficiario>());
        }
        if (cotizante.getEmpresaList() == null) {
            cotizante.setEmpresaList(new ArrayList<Empresa>());
        }
        if (cotizante.getRetirosList() == null) {
            cotizante.setRetirosList(new ArrayList<Retiros>());
        }
        if (cotizante.getPagosList() == null) {
            cotizante.setPagosList(new ArrayList<Pagos>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ips idIps = cotizante.getIdIps();
            if (idIps != null) {
                idIps = em.getReference(idIps.getClass(), idIps.getIdIps());
                cotizante.setIdIps(idIps);
            }
            List<Beneficiario> attachedBeneficiarioList = new ArrayList<Beneficiario>();
            for (Beneficiario beneficiarioListBeneficiarioToAttach : cotizante.getBeneficiarioList()) {
                beneficiarioListBeneficiarioToAttach = em.getReference(beneficiarioListBeneficiarioToAttach.getClass(), beneficiarioListBeneficiarioToAttach.getIDBeneficiario());
                attachedBeneficiarioList.add(beneficiarioListBeneficiarioToAttach);
            }
            cotizante.setBeneficiarioList(attachedBeneficiarioList);
            List<Empresa> attachedEmpresaList = new ArrayList<Empresa>();
            for (Empresa empresaListEmpresaToAttach : cotizante.getEmpresaList()) {
                empresaListEmpresaToAttach = em.getReference(empresaListEmpresaToAttach.getClass(), empresaListEmpresaToAttach.getNit());
                attachedEmpresaList.add(empresaListEmpresaToAttach);
            }
            cotizante.setEmpresaList(attachedEmpresaList);
            List<Retiros> attachedRetirosList = new ArrayList<Retiros>();
            for (Retiros retirosListRetirosToAttach : cotizante.getRetirosList()) {
                retirosListRetirosToAttach = em.getReference(retirosListRetirosToAttach.getClass(), retirosListRetirosToAttach.getIDRetiro());
                attachedRetirosList.add(retirosListRetirosToAttach);
            }
            cotizante.setRetirosList(attachedRetirosList);
            List<Pagos> attachedPagosList = new ArrayList<Pagos>();
            for (Pagos pagosListPagosToAttach : cotizante.getPagosList()) {
                pagosListPagosToAttach = em.getReference(pagosListPagosToAttach.getClass(), pagosListPagosToAttach.getIDTransaccion());
                attachedPagosList.add(pagosListPagosToAttach);
            }
            cotizante.setPagosList(attachedPagosList);
            em.persist(cotizante);
            if (idIps != null) {
                idIps.getCotizanteList().add(cotizante);
                idIps = em.merge(idIps);
            }
            for (Beneficiario beneficiarioListBeneficiario : cotizante.getBeneficiarioList()) {
                beneficiarioListBeneficiario.getCotizanteList().add(cotizante);
                beneficiarioListBeneficiario = em.merge(beneficiarioListBeneficiario);
            }
            for (Empresa empresaListEmpresa : cotizante.getEmpresaList()) {
                empresaListEmpresa.getCotizanteList().add(cotizante);
                empresaListEmpresa = em.merge(empresaListEmpresa);
            }
            for (Retiros retirosListRetiros : cotizante.getRetirosList()) {
                Cotizante oldIDUsuarioOfRetirosListRetiros = retirosListRetiros.getIDUsuario();
                retirosListRetiros.setIDUsuario(cotizante);
                retirosListRetiros = em.merge(retirosListRetiros);
                if (oldIDUsuarioOfRetirosListRetiros != null) {
                    oldIDUsuarioOfRetirosListRetiros.getRetirosList().remove(retirosListRetiros);
                    oldIDUsuarioOfRetirosListRetiros = em.merge(oldIDUsuarioOfRetirosListRetiros);
                }
            }
            for (Pagos pagosListPagos : cotizante.getPagosList()) {
                Cotizante oldIDUsuarioOfPagosListPagos = pagosListPagos.getIDUsuario();
                pagosListPagos.setIDUsuario(cotizante);
                pagosListPagos = em.merge(pagosListPagos);
                if (oldIDUsuarioOfPagosListPagos != null) {
                    oldIDUsuarioOfPagosListPagos.getPagosList().remove(pagosListPagos);
                    oldIDUsuarioOfPagosListPagos = em.merge(oldIDUsuarioOfPagosListPagos);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCotizante(cotizante.getCedula()) != null) {
                throw new PreexistingEntityException("Cotizante " + cotizante + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cotizante cotizante) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cotizante persistentCotizante = em.find(Cotizante.class, cotizante.getCedula());
            Ips idIpsOld = persistentCotizante.getIdIps();
            Ips idIpsNew = cotizante.getIdIps();
            List<Beneficiario> beneficiarioListOld = persistentCotizante.getBeneficiarioList();
            List<Beneficiario> beneficiarioListNew = cotizante.getBeneficiarioList();
            List<Empresa> empresaListOld = persistentCotizante.getEmpresaList();
            List<Empresa> empresaListNew = cotizante.getEmpresaList();
            List<Retiros> retirosListOld = persistentCotizante.getRetirosList();
            List<Retiros> retirosListNew = cotizante.getRetirosList();
            List<Pagos> pagosListOld = persistentCotizante.getPagosList();
            List<Pagos> pagosListNew = cotizante.getPagosList();
            if (idIpsNew != null) {
                idIpsNew = em.getReference(idIpsNew.getClass(), idIpsNew.getIdIps());
                cotizante.setIdIps(idIpsNew);
            }
            List<Beneficiario> attachedBeneficiarioListNew = new ArrayList<Beneficiario>();
            for (Beneficiario beneficiarioListNewBeneficiarioToAttach : beneficiarioListNew) {
                beneficiarioListNewBeneficiarioToAttach = em.getReference(beneficiarioListNewBeneficiarioToAttach.getClass(), beneficiarioListNewBeneficiarioToAttach.getIDBeneficiario());
                attachedBeneficiarioListNew.add(beneficiarioListNewBeneficiarioToAttach);
            }
            beneficiarioListNew = attachedBeneficiarioListNew;
            cotizante.setBeneficiarioList(beneficiarioListNew);
            List<Empresa> attachedEmpresaListNew = new ArrayList<Empresa>();
            for (Empresa empresaListNewEmpresaToAttach : empresaListNew) {
                empresaListNewEmpresaToAttach = em.getReference(empresaListNewEmpresaToAttach.getClass(), empresaListNewEmpresaToAttach.getNit());
                attachedEmpresaListNew.add(empresaListNewEmpresaToAttach);
            }
            empresaListNew = attachedEmpresaListNew;
            cotizante.setEmpresaList(empresaListNew);
            List<Retiros> attachedRetirosListNew = new ArrayList<Retiros>();
            for (Retiros retirosListNewRetirosToAttach : retirosListNew) {
                retirosListNewRetirosToAttach = em.getReference(retirosListNewRetirosToAttach.getClass(), retirosListNewRetirosToAttach.getIDRetiro());
                attachedRetirosListNew.add(retirosListNewRetirosToAttach);
            }
            retirosListNew = attachedRetirosListNew;
            cotizante.setRetirosList(retirosListNew);
            List<Pagos> attachedPagosListNew = new ArrayList<Pagos>();
            for (Pagos pagosListNewPagosToAttach : pagosListNew) {
                pagosListNewPagosToAttach = em.getReference(pagosListNewPagosToAttach.getClass(), pagosListNewPagosToAttach.getIDTransaccion());
                attachedPagosListNew.add(pagosListNewPagosToAttach);
            }
            pagosListNew = attachedPagosListNew;
            cotizante.setPagosList(pagosListNew);
            cotizante = em.merge(cotizante);
            if (idIpsOld != null && !idIpsOld.equals(idIpsNew)) {
                idIpsOld.getCotizanteList().remove(cotizante);
                idIpsOld = em.merge(idIpsOld);
            }
            if (idIpsNew != null && !idIpsNew.equals(idIpsOld)) {
                idIpsNew.getCotizanteList().add(cotizante);
                idIpsNew = em.merge(idIpsNew);
            }
            for (Beneficiario beneficiarioListOldBeneficiario : beneficiarioListOld) {
                if (!beneficiarioListNew.contains(beneficiarioListOldBeneficiario)) {
                    beneficiarioListOldBeneficiario.getCotizanteList().remove(cotizante);
                    beneficiarioListOldBeneficiario = em.merge(beneficiarioListOldBeneficiario);
                }
            }
            for (Beneficiario beneficiarioListNewBeneficiario : beneficiarioListNew) {
                if (!beneficiarioListOld.contains(beneficiarioListNewBeneficiario)) {
                    beneficiarioListNewBeneficiario.getCotizanteList().add(cotizante);
                    beneficiarioListNewBeneficiario = em.merge(beneficiarioListNewBeneficiario);
                }
            }
            for (Empresa empresaListOldEmpresa : empresaListOld) {
                if (!empresaListNew.contains(empresaListOldEmpresa)) {
                    empresaListOldEmpresa.getCotizanteList().remove(cotizante);
                    empresaListOldEmpresa = em.merge(empresaListOldEmpresa);
                }
            }
            for (Empresa empresaListNewEmpresa : empresaListNew) {
                if (!empresaListOld.contains(empresaListNewEmpresa)) {
                    empresaListNewEmpresa.getCotizanteList().add(cotizante);
                    empresaListNewEmpresa = em.merge(empresaListNewEmpresa);
                }
            }
            for (Retiros retirosListOldRetiros : retirosListOld) {
                if (!retirosListNew.contains(retirosListOldRetiros)) {
                    retirosListOldRetiros.setIDUsuario(null);
                    retirosListOldRetiros = em.merge(retirosListOldRetiros);
                }
            }
            for (Retiros retirosListNewRetiros : retirosListNew) {
                if (!retirosListOld.contains(retirosListNewRetiros)) {
                    Cotizante oldIDUsuarioOfRetirosListNewRetiros = retirosListNewRetiros.getIDUsuario();
                    retirosListNewRetiros.setIDUsuario(cotizante);
                    retirosListNewRetiros = em.merge(retirosListNewRetiros);
                    if (oldIDUsuarioOfRetirosListNewRetiros != null && !oldIDUsuarioOfRetirosListNewRetiros.equals(cotizante)) {
                        oldIDUsuarioOfRetirosListNewRetiros.getRetirosList().remove(retirosListNewRetiros);
                        oldIDUsuarioOfRetirosListNewRetiros = em.merge(oldIDUsuarioOfRetirosListNewRetiros);
                    }
                }
            }
            for (Pagos pagosListOldPagos : pagosListOld) {
                if (!pagosListNew.contains(pagosListOldPagos)) {
                    pagosListOldPagos.setIDUsuario(null);
                    pagosListOldPagos = em.merge(pagosListOldPagos);
                }
            }
            for (Pagos pagosListNewPagos : pagosListNew) {
                if (!pagosListOld.contains(pagosListNewPagos)) {
                    Cotizante oldIDUsuarioOfPagosListNewPagos = pagosListNewPagos.getIDUsuario();
                    pagosListNewPagos.setIDUsuario(cotizante);
                    pagosListNewPagos = em.merge(pagosListNewPagos);
                    if (oldIDUsuarioOfPagosListNewPagos != null && !oldIDUsuarioOfPagosListNewPagos.equals(cotizante)) {
                        oldIDUsuarioOfPagosListNewPagos.getPagosList().remove(pagosListNewPagos);
                        oldIDUsuarioOfPagosListNewPagos = em.merge(oldIDUsuarioOfPagosListNewPagos);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = cotizante.getCedula();
                if (findCotizante(id) == null) {
                    throw new NonexistentEntityException("The cotizante with id " + id + " no longer exists.");
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
            Cotizante cotizante;
            try {
                cotizante = em.getReference(Cotizante.class, id);
                cotizante.getCedula();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cotizante with id " + id + " no longer exists.", enfe);
            }
            Ips idIps = cotizante.getIdIps();
            if (idIps != null) {
                idIps.getCotizanteList().remove(cotizante);
                idIps = em.merge(idIps);
            }
            List<Beneficiario> beneficiarioList = cotizante.getBeneficiarioList();
            for (Beneficiario beneficiarioListBeneficiario : beneficiarioList) {
                beneficiarioListBeneficiario.getCotizanteList().remove(cotizante);
                beneficiarioListBeneficiario = em.merge(beneficiarioListBeneficiario);
            }
            List<Empresa> empresaList = cotizante.getEmpresaList();
            for (Empresa empresaListEmpresa : empresaList) {
                empresaListEmpresa.getCotizanteList().remove(cotizante);
                empresaListEmpresa = em.merge(empresaListEmpresa);
            }
            List<Retiros> retirosList = cotizante.getRetirosList();
            for (Retiros retirosListRetiros : retirosList) {
                retirosListRetiros.setIDUsuario(null);
                retirosListRetiros = em.merge(retirosListRetiros);
            }
            List<Pagos> pagosList = cotizante.getPagosList();
            for (Pagos pagosListPagos : pagosList) {
                pagosListPagos.setIDUsuario(null);
                pagosListPagos = em.merge(pagosListPagos);
            }
            em.remove(cotizante);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cotizante> findCotizanteEntities() {
        return findCotizanteEntities(true, -1, -1);
    }

    public List<Cotizante> findCotizanteEntities(int maxResults, int firstResult) {
        return findCotizanteEntities(false, maxResults, firstResult);
    }

    private List<Cotizante> findCotizanteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cotizante.class));
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

    public Cotizante findCotizante(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cotizante.class, id);
        } finally {
            em.close();
        }
    }

    public int getCotizanteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cotizante> rt = cq.from(Cotizante.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
