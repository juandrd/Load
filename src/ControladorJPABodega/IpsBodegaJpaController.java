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
import Entidades_Bodega.DepartamentoBodega;
import Entidades_Bodega.CitasBodega;
import java.util.ArrayList;
import java.util.List;
import Entidades_Bodega.AfiliacionesBodega;
import Entidades_Bodega.IpsBodega;
import Entidades_Bodega.UrgenciasBodega;
import Entidades_Bodega.RemisionesBodega;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author USER
 */
public class IpsBodegaJpaController implements Serializable {

    public IpsBodegaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(IpsBodega ipsBodega) throws PreexistingEntityException, Exception {
        if (ipsBodega.getCitasBodegaList() == null) {
            ipsBodega.setCitasBodegaList(new ArrayList<CitasBodega>());
        }
        if (ipsBodega.getAfiliacionesBodegaList() == null) {
            ipsBodega.setAfiliacionesBodegaList(new ArrayList<AfiliacionesBodega>());
        }
        if (ipsBodega.getUrgenciasBodegaList() == null) {
            ipsBodega.setUrgenciasBodegaList(new ArrayList<UrgenciasBodega>());
        }
        if (ipsBodega.getRemisionesBodegaList() == null) {
            ipsBodega.setRemisionesBodegaList(new ArrayList<RemisionesBodega>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DepartamentoBodega departamento = ipsBodega.getDepartamento();
            if (departamento != null) {
                departamento = em.getReference(departamento.getClass(), departamento.getCodDpto());
                ipsBodega.setDepartamento(departamento);
            }
            List<CitasBodega> attachedCitasBodegaList = new ArrayList<CitasBodega>();
            for (CitasBodega citasBodegaListCitasBodegaToAttach : ipsBodega.getCitasBodegaList()) {
                citasBodegaListCitasBodegaToAttach = em.getReference(citasBodegaListCitasBodegaToAttach.getClass(), citasBodegaListCitasBodegaToAttach.getCitasBodegaPK());
                attachedCitasBodegaList.add(citasBodegaListCitasBodegaToAttach);
            }
            ipsBodega.setCitasBodegaList(attachedCitasBodegaList);
            List<AfiliacionesBodega> attachedAfiliacionesBodegaList = new ArrayList<AfiliacionesBodega>();
            for (AfiliacionesBodega afiliacionesBodegaListAfiliacionesBodegaToAttach : ipsBodega.getAfiliacionesBodegaList()) {
                afiliacionesBodegaListAfiliacionesBodegaToAttach = em.getReference(afiliacionesBodegaListAfiliacionesBodegaToAttach.getClass(), afiliacionesBodegaListAfiliacionesBodegaToAttach.getAfiliacionesBodegaPK());
                attachedAfiliacionesBodegaList.add(afiliacionesBodegaListAfiliacionesBodegaToAttach);
            }
            ipsBodega.setAfiliacionesBodegaList(attachedAfiliacionesBodegaList);
            List<UrgenciasBodega> attachedUrgenciasBodegaList = new ArrayList<UrgenciasBodega>();
            for (UrgenciasBodega urgenciasBodegaListUrgenciasBodegaToAttach : ipsBodega.getUrgenciasBodegaList()) {
                urgenciasBodegaListUrgenciasBodegaToAttach = em.getReference(urgenciasBodegaListUrgenciasBodegaToAttach.getClass(), urgenciasBodegaListUrgenciasBodegaToAttach.getUrgenciasBodegaPK());
                attachedUrgenciasBodegaList.add(urgenciasBodegaListUrgenciasBodegaToAttach);
            }
            ipsBodega.setUrgenciasBodegaList(attachedUrgenciasBodegaList);
            List<RemisionesBodega> attachedRemisionesBodegaList = new ArrayList<RemisionesBodega>();
            for (RemisionesBodega remisionesBodegaListRemisionesBodegaToAttach : ipsBodega.getRemisionesBodegaList()) {
                remisionesBodegaListRemisionesBodegaToAttach = em.getReference(remisionesBodegaListRemisionesBodegaToAttach.getClass(), remisionesBodegaListRemisionesBodegaToAttach.getRemisionesBodegaPK());
                attachedRemisionesBodegaList.add(remisionesBodegaListRemisionesBodegaToAttach);
            }
            ipsBodega.setRemisionesBodegaList(attachedRemisionesBodegaList);
            em.persist(ipsBodega);
            if (departamento != null) {
                departamento.getIpsBodegaList().add(ipsBodega);
                departamento = em.merge(departamento);
            }
            for (CitasBodega citasBodegaListCitasBodega : ipsBodega.getCitasBodegaList()) {
                IpsBodega oldIpsBodegaOfCitasBodegaListCitasBodega = citasBodegaListCitasBodega.getIpsBodega();
                citasBodegaListCitasBodega.setIpsBodega(ipsBodega);
                citasBodegaListCitasBodega = em.merge(citasBodegaListCitasBodega);
                if (oldIpsBodegaOfCitasBodegaListCitasBodega != null) {
                    oldIpsBodegaOfCitasBodegaListCitasBodega.getCitasBodegaList().remove(citasBodegaListCitasBodega);
                    oldIpsBodegaOfCitasBodegaListCitasBodega = em.merge(oldIpsBodegaOfCitasBodegaListCitasBodega);
                }
            }
            for (AfiliacionesBodega afiliacionesBodegaListAfiliacionesBodega : ipsBodega.getAfiliacionesBodegaList()) {
                IpsBodega oldIpsBodegaOfAfiliacionesBodegaListAfiliacionesBodega = afiliacionesBodegaListAfiliacionesBodega.getIpsBodega();
                afiliacionesBodegaListAfiliacionesBodega.setIpsBodega(ipsBodega);
                afiliacionesBodegaListAfiliacionesBodega = em.merge(afiliacionesBodegaListAfiliacionesBodega);
                if (oldIpsBodegaOfAfiliacionesBodegaListAfiliacionesBodega != null) {
                    oldIpsBodegaOfAfiliacionesBodegaListAfiliacionesBodega.getAfiliacionesBodegaList().remove(afiliacionesBodegaListAfiliacionesBodega);
                    oldIpsBodegaOfAfiliacionesBodegaListAfiliacionesBodega = em.merge(oldIpsBodegaOfAfiliacionesBodegaListAfiliacionesBodega);
                }
            }
            for (UrgenciasBodega urgenciasBodegaListUrgenciasBodega : ipsBodega.getUrgenciasBodegaList()) {
                IpsBodega oldIpsKeyOfUrgenciasBodegaListUrgenciasBodega = urgenciasBodegaListUrgenciasBodega.getIpsKey();
                urgenciasBodegaListUrgenciasBodega.setIpsKey(ipsBodega);
                urgenciasBodegaListUrgenciasBodega = em.merge(urgenciasBodegaListUrgenciasBodega);
                if (oldIpsKeyOfUrgenciasBodegaListUrgenciasBodega != null) {
                    oldIpsKeyOfUrgenciasBodegaListUrgenciasBodega.getUrgenciasBodegaList().remove(urgenciasBodegaListUrgenciasBodega);
                    oldIpsKeyOfUrgenciasBodegaListUrgenciasBodega = em.merge(oldIpsKeyOfUrgenciasBodegaListUrgenciasBodega);
                }
            }
            for (RemisionesBodega remisionesBodegaListRemisionesBodega : ipsBodega.getRemisionesBodegaList()) {
                IpsBodega oldIpsBodegaOfRemisionesBodegaListRemisionesBodega = remisionesBodegaListRemisionesBodega.getIpsBodega();
                remisionesBodegaListRemisionesBodega.setIpsBodega(ipsBodega);
                remisionesBodegaListRemisionesBodega = em.merge(remisionesBodegaListRemisionesBodega);
                if (oldIpsBodegaOfRemisionesBodegaListRemisionesBodega != null) {
                    oldIpsBodegaOfRemisionesBodegaListRemisionesBodega.getRemisionesBodegaList().remove(remisionesBodegaListRemisionesBodega);
                    oldIpsBodegaOfRemisionesBodegaListRemisionesBodega = em.merge(oldIpsBodegaOfRemisionesBodegaListRemisionesBodega);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findIpsBodega(ipsBodega.getIpsKey()) != null) {
                throw new PreexistingEntityException("IpsBodega " + ipsBodega + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(IpsBodega ipsBodega) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            IpsBodega persistentIpsBodega = em.find(IpsBodega.class, ipsBodega.getIpsKey());
            DepartamentoBodega departamentoOld = persistentIpsBodega.getDepartamento();
            DepartamentoBodega departamentoNew = ipsBodega.getDepartamento();
            List<CitasBodega> citasBodegaListOld = persistentIpsBodega.getCitasBodegaList();
            List<CitasBodega> citasBodegaListNew = ipsBodega.getCitasBodegaList();
            List<AfiliacionesBodega> afiliacionesBodegaListOld = persistentIpsBodega.getAfiliacionesBodegaList();
            List<AfiliacionesBodega> afiliacionesBodegaListNew = ipsBodega.getAfiliacionesBodegaList();
            List<UrgenciasBodega> urgenciasBodegaListOld = persistentIpsBodega.getUrgenciasBodegaList();
            List<UrgenciasBodega> urgenciasBodegaListNew = ipsBodega.getUrgenciasBodegaList();
            List<RemisionesBodega> remisionesBodegaListOld = persistentIpsBodega.getRemisionesBodegaList();
            List<RemisionesBodega> remisionesBodegaListNew = ipsBodega.getRemisionesBodegaList();
            List<String> illegalOrphanMessages = null;
            for (CitasBodega citasBodegaListOldCitasBodega : citasBodegaListOld) {
                if (!citasBodegaListNew.contains(citasBodegaListOldCitasBodega)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain CitasBodega " + citasBodegaListOldCitasBodega + " since its ipsBodega field is not nullable.");
                }
            }
            for (AfiliacionesBodega afiliacionesBodegaListOldAfiliacionesBodega : afiliacionesBodegaListOld) {
                if (!afiliacionesBodegaListNew.contains(afiliacionesBodegaListOldAfiliacionesBodega)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain AfiliacionesBodega " + afiliacionesBodegaListOldAfiliacionesBodega + " since its ipsBodega field is not nullable.");
                }
            }
            for (RemisionesBodega remisionesBodegaListOldRemisionesBodega : remisionesBodegaListOld) {
                if (!remisionesBodegaListNew.contains(remisionesBodegaListOldRemisionesBodega)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain RemisionesBodega " + remisionesBodegaListOldRemisionesBodega + " since its ipsBodega field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (departamentoNew != null) {
                departamentoNew = em.getReference(departamentoNew.getClass(), departamentoNew.getCodDpto());
                ipsBodega.setDepartamento(departamentoNew);
            }
            List<CitasBodega> attachedCitasBodegaListNew = new ArrayList<CitasBodega>();
            for (CitasBodega citasBodegaListNewCitasBodegaToAttach : citasBodegaListNew) {
                citasBodegaListNewCitasBodegaToAttach = em.getReference(citasBodegaListNewCitasBodegaToAttach.getClass(), citasBodegaListNewCitasBodegaToAttach.getCitasBodegaPK());
                attachedCitasBodegaListNew.add(citasBodegaListNewCitasBodegaToAttach);
            }
            citasBodegaListNew = attachedCitasBodegaListNew;
            ipsBodega.setCitasBodegaList(citasBodegaListNew);
            List<AfiliacionesBodega> attachedAfiliacionesBodegaListNew = new ArrayList<AfiliacionesBodega>();
            for (AfiliacionesBodega afiliacionesBodegaListNewAfiliacionesBodegaToAttach : afiliacionesBodegaListNew) {
                afiliacionesBodegaListNewAfiliacionesBodegaToAttach = em.getReference(afiliacionesBodegaListNewAfiliacionesBodegaToAttach.getClass(), afiliacionesBodegaListNewAfiliacionesBodegaToAttach.getAfiliacionesBodegaPK());
                attachedAfiliacionesBodegaListNew.add(afiliacionesBodegaListNewAfiliacionesBodegaToAttach);
            }
            afiliacionesBodegaListNew = attachedAfiliacionesBodegaListNew;
            ipsBodega.setAfiliacionesBodegaList(afiliacionesBodegaListNew);
            List<UrgenciasBodega> attachedUrgenciasBodegaListNew = new ArrayList<UrgenciasBodega>();
            for (UrgenciasBodega urgenciasBodegaListNewUrgenciasBodegaToAttach : urgenciasBodegaListNew) {
                urgenciasBodegaListNewUrgenciasBodegaToAttach = em.getReference(urgenciasBodegaListNewUrgenciasBodegaToAttach.getClass(), urgenciasBodegaListNewUrgenciasBodegaToAttach.getUrgenciasBodegaPK());
                attachedUrgenciasBodegaListNew.add(urgenciasBodegaListNewUrgenciasBodegaToAttach);
            }
            urgenciasBodegaListNew = attachedUrgenciasBodegaListNew;
            ipsBodega.setUrgenciasBodegaList(urgenciasBodegaListNew);
            List<RemisionesBodega> attachedRemisionesBodegaListNew = new ArrayList<RemisionesBodega>();
            for (RemisionesBodega remisionesBodegaListNewRemisionesBodegaToAttach : remisionesBodegaListNew) {
                remisionesBodegaListNewRemisionesBodegaToAttach = em.getReference(remisionesBodegaListNewRemisionesBodegaToAttach.getClass(), remisionesBodegaListNewRemisionesBodegaToAttach.getRemisionesBodegaPK());
                attachedRemisionesBodegaListNew.add(remisionesBodegaListNewRemisionesBodegaToAttach);
            }
            remisionesBodegaListNew = attachedRemisionesBodegaListNew;
            ipsBodega.setRemisionesBodegaList(remisionesBodegaListNew);
            ipsBodega = em.merge(ipsBodega);
            if (departamentoOld != null && !departamentoOld.equals(departamentoNew)) {
                departamentoOld.getIpsBodegaList().remove(ipsBodega);
                departamentoOld = em.merge(departamentoOld);
            }
            if (departamentoNew != null && !departamentoNew.equals(departamentoOld)) {
                departamentoNew.getIpsBodegaList().add(ipsBodega);
                departamentoNew = em.merge(departamentoNew);
            }
            for (CitasBodega citasBodegaListNewCitasBodega : citasBodegaListNew) {
                if (!citasBodegaListOld.contains(citasBodegaListNewCitasBodega)) {
                    IpsBodega oldIpsBodegaOfCitasBodegaListNewCitasBodega = citasBodegaListNewCitasBodega.getIpsBodega();
                    citasBodegaListNewCitasBodega.setIpsBodega(ipsBodega);
                    citasBodegaListNewCitasBodega = em.merge(citasBodegaListNewCitasBodega);
                    if (oldIpsBodegaOfCitasBodegaListNewCitasBodega != null && !oldIpsBodegaOfCitasBodegaListNewCitasBodega.equals(ipsBodega)) {
                        oldIpsBodegaOfCitasBodegaListNewCitasBodega.getCitasBodegaList().remove(citasBodegaListNewCitasBodega);
                        oldIpsBodegaOfCitasBodegaListNewCitasBodega = em.merge(oldIpsBodegaOfCitasBodegaListNewCitasBodega);
                    }
                }
            }
            for (AfiliacionesBodega afiliacionesBodegaListNewAfiliacionesBodega : afiliacionesBodegaListNew) {
                if (!afiliacionesBodegaListOld.contains(afiliacionesBodegaListNewAfiliacionesBodega)) {
                    IpsBodega oldIpsBodegaOfAfiliacionesBodegaListNewAfiliacionesBodega = afiliacionesBodegaListNewAfiliacionesBodega.getIpsBodega();
                    afiliacionesBodegaListNewAfiliacionesBodega.setIpsBodega(ipsBodega);
                    afiliacionesBodegaListNewAfiliacionesBodega = em.merge(afiliacionesBodegaListNewAfiliacionesBodega);
                    if (oldIpsBodegaOfAfiliacionesBodegaListNewAfiliacionesBodega != null && !oldIpsBodegaOfAfiliacionesBodegaListNewAfiliacionesBodega.equals(ipsBodega)) {
                        oldIpsBodegaOfAfiliacionesBodegaListNewAfiliacionesBodega.getAfiliacionesBodegaList().remove(afiliacionesBodegaListNewAfiliacionesBodega);
                        oldIpsBodegaOfAfiliacionesBodegaListNewAfiliacionesBodega = em.merge(oldIpsBodegaOfAfiliacionesBodegaListNewAfiliacionesBodega);
                    }
                }
            }
            for (UrgenciasBodega urgenciasBodegaListOldUrgenciasBodega : urgenciasBodegaListOld) {
                if (!urgenciasBodegaListNew.contains(urgenciasBodegaListOldUrgenciasBodega)) {
                    urgenciasBodegaListOldUrgenciasBodega.setIpsKey(null);
                    urgenciasBodegaListOldUrgenciasBodega = em.merge(urgenciasBodegaListOldUrgenciasBodega);
                }
            }
            for (UrgenciasBodega urgenciasBodegaListNewUrgenciasBodega : urgenciasBodegaListNew) {
                if (!urgenciasBodegaListOld.contains(urgenciasBodegaListNewUrgenciasBodega)) {
                    IpsBodega oldIpsKeyOfUrgenciasBodegaListNewUrgenciasBodega = urgenciasBodegaListNewUrgenciasBodega.getIpsKey();
                    urgenciasBodegaListNewUrgenciasBodega.setIpsKey(ipsBodega);
                    urgenciasBodegaListNewUrgenciasBodega = em.merge(urgenciasBodegaListNewUrgenciasBodega);
                    if (oldIpsKeyOfUrgenciasBodegaListNewUrgenciasBodega != null && !oldIpsKeyOfUrgenciasBodegaListNewUrgenciasBodega.equals(ipsBodega)) {
                        oldIpsKeyOfUrgenciasBodegaListNewUrgenciasBodega.getUrgenciasBodegaList().remove(urgenciasBodegaListNewUrgenciasBodega);
                        oldIpsKeyOfUrgenciasBodegaListNewUrgenciasBodega = em.merge(oldIpsKeyOfUrgenciasBodegaListNewUrgenciasBodega);
                    }
                }
            }
            for (RemisionesBodega remisionesBodegaListNewRemisionesBodega : remisionesBodegaListNew) {
                if (!remisionesBodegaListOld.contains(remisionesBodegaListNewRemisionesBodega)) {
                    IpsBodega oldIpsBodegaOfRemisionesBodegaListNewRemisionesBodega = remisionesBodegaListNewRemisionesBodega.getIpsBodega();
                    remisionesBodegaListNewRemisionesBodega.setIpsBodega(ipsBodega);
                    remisionesBodegaListNewRemisionesBodega = em.merge(remisionesBodegaListNewRemisionesBodega);
                    if (oldIpsBodegaOfRemisionesBodegaListNewRemisionesBodega != null && !oldIpsBodegaOfRemisionesBodegaListNewRemisionesBodega.equals(ipsBodega)) {
                        oldIpsBodegaOfRemisionesBodegaListNewRemisionesBodega.getRemisionesBodegaList().remove(remisionesBodegaListNewRemisionesBodega);
                        oldIpsBodegaOfRemisionesBodegaListNewRemisionesBodega = em.merge(oldIpsBodegaOfRemisionesBodegaListNewRemisionesBodega);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = ipsBodega.getIpsKey();
                if (findIpsBodega(id) == null) {
                    throw new NonexistentEntityException("The ipsBodega with id " + id + " no longer exists.");
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
            IpsBodega ipsBodega;
            try {
                ipsBodega = em.getReference(IpsBodega.class, id);
                ipsBodega.getIpsKey();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ipsBodega with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<CitasBodega> citasBodegaListOrphanCheck = ipsBodega.getCitasBodegaList();
            for (CitasBodega citasBodegaListOrphanCheckCitasBodega : citasBodegaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This IpsBodega (" + ipsBodega + ") cannot be destroyed since the CitasBodega " + citasBodegaListOrphanCheckCitasBodega + " in its citasBodegaList field has a non-nullable ipsBodega field.");
            }
            List<AfiliacionesBodega> afiliacionesBodegaListOrphanCheck = ipsBodega.getAfiliacionesBodegaList();
            for (AfiliacionesBodega afiliacionesBodegaListOrphanCheckAfiliacionesBodega : afiliacionesBodegaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This IpsBodega (" + ipsBodega + ") cannot be destroyed since the AfiliacionesBodega " + afiliacionesBodegaListOrphanCheckAfiliacionesBodega + " in its afiliacionesBodegaList field has a non-nullable ipsBodega field.");
            }
            List<RemisionesBodega> remisionesBodegaListOrphanCheck = ipsBodega.getRemisionesBodegaList();
            for (RemisionesBodega remisionesBodegaListOrphanCheckRemisionesBodega : remisionesBodegaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This IpsBodega (" + ipsBodega + ") cannot be destroyed since the RemisionesBodega " + remisionesBodegaListOrphanCheckRemisionesBodega + " in its remisionesBodegaList field has a non-nullable ipsBodega field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            DepartamentoBodega departamento = ipsBodega.getDepartamento();
            if (departamento != null) {
                departamento.getIpsBodegaList().remove(ipsBodega);
                departamento = em.merge(departamento);
            }
            List<UrgenciasBodega> urgenciasBodegaList = ipsBodega.getUrgenciasBodegaList();
            for (UrgenciasBodega urgenciasBodegaListUrgenciasBodega : urgenciasBodegaList) {
                urgenciasBodegaListUrgenciasBodega.setIpsKey(null);
                urgenciasBodegaListUrgenciasBodega = em.merge(urgenciasBodegaListUrgenciasBodega);
            }
            em.remove(ipsBodega);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<IpsBodega> findIpsBodegaEntities() {
        return findIpsBodegaEntities(true, -1, -1);
    }

    public List<IpsBodega> findIpsBodegaEntities(int maxResults, int firstResult) {
        return findIpsBodegaEntities(false, maxResults, firstResult);
    }

    private List<IpsBodega> findIpsBodegaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(IpsBodega.class));
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

    public IpsBodega findIpsBodega(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(IpsBodega.class, id);
        } finally {
            em.close();
        }
    }

    public int getIpsBodegaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<IpsBodega> rt = cq.from(IpsBodega.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
     public IpsBodega consultarPorId(String id) {

         IpsBodega ips=new IpsBodega();
         List lista;
         lista=findIpsBodegaEntities();
         
          for (int i = 0; i < lista.size(); i++) {
              IpsBodega d=(IpsBodega) lista.get(i);
              if(d.getCodigoIps().toString().equalsIgnoreCase(id)){                  
                  ips=d;
              }
          }

        return ips;
    }
     
       
}
