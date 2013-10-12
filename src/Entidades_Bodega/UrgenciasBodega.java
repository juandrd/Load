/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades_Bodega;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author USER
 */
@Entity
@Table(name = "urgencias_bodega")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UrgenciasBodega.findAll", query = "SELECT u FROM UrgenciasBodega u"),
    @NamedQuery(name = "UrgenciasBodega.findByPacienteKey", query = "SELECT u FROM UrgenciasBodega u WHERE u.urgenciasBodegaPK.pacienteKey = :pacienteKey"),
    @NamedQuery(name = "UrgenciasBodega.findByDemografiaPacienteKey", query = "SELECT u FROM UrgenciasBodega u WHERE u.urgenciasBodegaPK.demografiaPacienteKey = :demografiaPacienteKey"),
    @NamedQuery(name = "UrgenciasBodega.findByFechaKey", query = "SELECT u FROM UrgenciasBodega u WHERE u.urgenciasBodegaPK.fechaKey = :fechaKey"),
    @NamedQuery(name = "UrgenciasBodega.findByEmpresaKey", query = "SELECT u FROM UrgenciasBodega u WHERE u.urgenciasBodegaPK.empresaKey = :empresaKey"),
    @NamedQuery(name = "UrgenciasBodega.findByMedicoId", query = "SELECT u FROM UrgenciasBodega u WHERE u.medicoId = :medicoId"),
    @NamedQuery(name = "UrgenciasBodega.findByPreexistenciaKey", query = "SELECT u FROM UrgenciasBodega u WHERE u.urgenciasBodegaPK.preexistenciaKey = :preexistenciaKey"),
    @NamedQuery(name = "UrgenciasBodega.findByTiempoEsperaAtencion", query = "SELECT u FROM UrgenciasBodega u WHERE u.tiempoEsperaAtencion = :tiempoEsperaAtencion")})
public class UrgenciasBodega implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UrgenciasBodegaPK urgenciasBodegaPK;
    @Column(name = "medico_id")
    private Integer medicoId;
    @Column(name = "tiempo_espera_atencion")
    @Temporal(TemporalType.TIME)
    private Date tiempoEsperaAtencion;
    @JoinColumn(name = "preexistencia_key", referencedColumnName = "preexistencia_key", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private PreexistenciaBodega preexistenciaBodega;
    @JoinColumn(name = "ips_key", referencedColumnName = "ips_key")
    @ManyToOne
    private IpsBodega ipsKey;
    @JoinColumn(name = "diagnostico_key", referencedColumnName = "diagnostico_key")
    @ManyToOne
    private DiagnosticoBodega diagnosticoKey;
    @JoinColumn(name = "servicio_pos_key", referencedColumnName = "servicio_pos_key")
    @ManyToOne
    private ServicioPosBodega servicioPosKey;
    @JoinColumn(name = "empresa_key", referencedColumnName = "empresa_key", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private EmpresaBodega empresaBodega;
    @JoinColumn(name = "fecha_key", referencedColumnName = "date_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Dates dates;
    @JoinColumn(name = "demografia_paciente_key", referencedColumnName = "demografia_paciente_key", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private DemografiaPacienteBodega demografiaPacienteBodega;
    @JoinColumn(name = "paciente_key", referencedColumnName = "paciente_key", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private PacienteBodega pacienteBodega;

    public UrgenciasBodega() {
    }

    public UrgenciasBodega(UrgenciasBodegaPK urgenciasBodegaPK) {
        this.urgenciasBodegaPK = urgenciasBodegaPK;
    }

    public UrgenciasBodega(int pacienteKey, int demografiaPacienteKey, long fechaKey, int empresaKey, int preexistenciaKey) {
        this.urgenciasBodegaPK = new UrgenciasBodegaPK(pacienteKey, demografiaPacienteKey, fechaKey, empresaKey, preexistenciaKey);
    }

    public UrgenciasBodegaPK getUrgenciasBodegaPK() {
        return urgenciasBodegaPK;
    }

    public void setUrgenciasBodegaPK(UrgenciasBodegaPK urgenciasBodegaPK) {
        this.urgenciasBodegaPK = urgenciasBodegaPK;
    }

    public Integer getMedicoId() {
        return medicoId;
    }

    public void setMedicoId(Integer medicoId) {
        this.medicoId = medicoId;
    }

    public Date getTiempoEsperaAtencion() {
        return tiempoEsperaAtencion;
    }

    public void setTiempoEsperaAtencion(Date tiempoEsperaAtencion) {
        this.tiempoEsperaAtencion = tiempoEsperaAtencion;
    }

    public PreexistenciaBodega getPreexistenciaBodega() {
        return preexistenciaBodega;
    }

    public void setPreexistenciaBodega(PreexistenciaBodega preexistenciaBodega) {
        this.preexistenciaBodega = preexistenciaBodega;
    }

    public IpsBodega getIpsKey() {
        return ipsKey;
    }

    public void setIpsKey(IpsBodega ipsKey) {
        this.ipsKey = ipsKey;
    }

    public DiagnosticoBodega getDiagnosticoKey() {
        return diagnosticoKey;
    }

    public void setDiagnosticoKey(DiagnosticoBodega diagnosticoKey) {
        this.diagnosticoKey = diagnosticoKey;
    }

    public ServicioPosBodega getServicioPosKey() {
        return servicioPosKey;
    }

    public void setServicioPosKey(ServicioPosBodega servicioPosKey) {
        this.servicioPosKey = servicioPosKey;
    }

    public EmpresaBodega getEmpresaBodega() {
        return empresaBodega;
    }

    public void setEmpresaBodega(EmpresaBodega empresaBodega) {
        this.empresaBodega = empresaBodega;
    }

    public Dates getDates() {
        return dates;
    }

    public void setDates(Dates dates) {
        this.dates = dates;
    }

    public DemografiaPacienteBodega getDemografiaPacienteBodega() {
        return demografiaPacienteBodega;
    }

    public void setDemografiaPacienteBodega(DemografiaPacienteBodega demografiaPacienteBodega) {
        this.demografiaPacienteBodega = demografiaPacienteBodega;
    }

    public PacienteBodega getPacienteBodega() {
        return pacienteBodega;
    }

    public void setPacienteBodega(PacienteBodega pacienteBodega) {
        this.pacienteBodega = pacienteBodega;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (urgenciasBodegaPK != null ? urgenciasBodegaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UrgenciasBodega)) {
            return false;
        }
        UrgenciasBodega other = (UrgenciasBodega) object;
        if ((this.urgenciasBodegaPK == null && other.urgenciasBodegaPK != null) || (this.urgenciasBodegaPK != null && !this.urgenciasBodegaPK.equals(other.urgenciasBodegaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades_Bodega.UrgenciasBodega[ urgenciasBodegaPK=" + urgenciasBodegaPK + " ]";
    }
    
}
