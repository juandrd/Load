/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades_Bodega;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author USER
 */
@Entity
@Table(name = "remisiones_bodega")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RemisionesBodega.findAll", query = "SELECT r FROM RemisionesBodega r"),
    @NamedQuery(name = "RemisionesBodega.findByMedicoAtiendeKey", query = "SELECT r FROM RemisionesBodega r WHERE r.remisionesBodegaPK.medicoAtiendeKey = :medicoAtiendeKey"),
    @NamedQuery(name = "RemisionesBodega.findByMedicoRemitenteKey", query = "SELECT r FROM RemisionesBodega r WHERE r.remisionesBodegaPK.medicoRemitenteKey = :medicoRemitenteKey"),
    @NamedQuery(name = "RemisionesBodega.findByPacienteKey", query = "SELECT r FROM RemisionesBodega r WHERE r.remisionesBodegaPK.pacienteKey = :pacienteKey"),
    @NamedQuery(name = "RemisionesBodega.findByDemografiaPacienteKey", query = "SELECT r FROM RemisionesBodega r WHERE r.remisionesBodegaPK.demografiaPacienteKey = :demografiaPacienteKey"),
    @NamedQuery(name = "RemisionesBodega.findByFechaRemisionKey", query = "SELECT r FROM RemisionesBodega r WHERE r.remisionesBodegaPK.fechaRemisionKey = :fechaRemisionKey"),
    @NamedQuery(name = "RemisionesBodega.findByFechaAtencionKey", query = "SELECT r FROM RemisionesBodega r WHERE r.remisionesBodegaPK.fechaAtencionKey = :fechaAtencionKey"),
    @NamedQuery(name = "RemisionesBodega.findByDiagnosticoKey", query = "SELECT r FROM RemisionesBodega r WHERE r.remisionesBodegaPK.diagnosticoKey = :diagnosticoKey"),
    @NamedQuery(name = "RemisionesBodega.findByPreexistenciaKey", query = "SELECT r FROM RemisionesBodega r WHERE r.remisionesBodegaPK.preexistenciaKey = :preexistenciaKey"),
    @NamedQuery(name = "RemisionesBodega.findByServicioPosKey", query = "SELECT r FROM RemisionesBodega r WHERE r.remisionesBodegaPK.servicioPosKey = :servicioPosKey"),
    @NamedQuery(name = "RemisionesBodega.findByIpsKey", query = "SELECT r FROM RemisionesBodega r WHERE r.remisionesBodegaPK.ipsKey = :ipsKey"),
    @NamedQuery(name = "RemisionesBodega.findByTiempoEsperaRemision", query = "SELECT r FROM RemisionesBodega r WHERE r.tiempoEsperaRemision = :tiempoEsperaRemision")})
public class RemisionesBodega implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RemisionesBodegaPK remisionesBodegaPK;
    @Column(name = "tiempo_espera_remision")
    private Integer tiempoEsperaRemision;
    @JoinColumn(name = "ips_key", referencedColumnName = "ips_key", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private IpsBodega ipsBodega;
    @JoinColumn(name = "servicio_pos_key", referencedColumnName = "servicio_pos_key", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private ServicioPosBodega servicioPosBodega;
    @JoinColumn(name = "preexistencia_key", referencedColumnName = "preexistencia_key", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private PreexistenciaBodega preexistenciaBodega;
    @JoinColumn(name = "diagnostico_key", referencedColumnName = "diagnostico_key", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private DiagnosticoBodega diagnosticoBodega;
    @JoinColumn(name = "fecha_atencion_key", referencedColumnName = "date_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Dates dates;
    @JoinColumn(name = "fecha_remision_key", referencedColumnName = "date_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Dates dates1;
    @JoinColumn(name = "demografia_paciente_key", referencedColumnName = "demografia_paciente_key", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private DemografiaPacienteBodega demografiaPacienteBodega;
    @JoinColumn(name = "paciente_key", referencedColumnName = "paciente_key", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private PacienteBodega pacienteBodega;
    @JoinColumn(name = "medico_remitente_key", referencedColumnName = "medico_key", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private MedicoBodega medicoBodega;
    @JoinColumn(name = "medico_atiende_key", referencedColumnName = "medico_key", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private MedicoBodega medicoBodega1;

    public RemisionesBodega() {
    }

    public RemisionesBodega(RemisionesBodegaPK remisionesBodegaPK) {
        this.remisionesBodegaPK = remisionesBodegaPK;
    }

    public RemisionesBodega(int medicoAtiendeKey, int medicoRemitenteKey, int pacienteKey, int demografiaPacienteKey, long fechaRemisionKey, long fechaAtencionKey, int diagnosticoKey, int preexistenciaKey, int servicioPosKey, int ipsKey) {
        this.remisionesBodegaPK = new RemisionesBodegaPK(medicoAtiendeKey, medicoRemitenteKey, pacienteKey, demografiaPacienteKey, fechaRemisionKey, fechaAtencionKey, diagnosticoKey, preexistenciaKey, servicioPosKey, ipsKey);
    }

    public RemisionesBodegaPK getRemisionesBodegaPK() {
        return remisionesBodegaPK;
    }

    public void setRemisionesBodegaPK(RemisionesBodegaPK remisionesBodegaPK) {
        this.remisionesBodegaPK = remisionesBodegaPK;
    }

    public Integer getTiempoEsperaRemision() {
        return tiempoEsperaRemision;
    }

    public void setTiempoEsperaRemision(Integer tiempoEsperaRemision) {
        this.tiempoEsperaRemision = tiempoEsperaRemision;
    }

    public IpsBodega getIpsBodega() {
        return ipsBodega;
    }

    public void setIpsBodega(IpsBodega ipsBodega) {
        this.ipsBodega = ipsBodega;
    }

    public ServicioPosBodega getServicioPosBodega() {
        return servicioPosBodega;
    }

    public void setServicioPosBodega(ServicioPosBodega servicioPosBodega) {
        this.servicioPosBodega = servicioPosBodega;
    }

    public PreexistenciaBodega getPreexistenciaBodega() {
        return preexistenciaBodega;
    }

    public void setPreexistenciaBodega(PreexistenciaBodega preexistenciaBodega) {
        this.preexistenciaBodega = preexistenciaBodega;
    }

    public DiagnosticoBodega getDiagnosticoBodega() {
        return diagnosticoBodega;
    }

    public void setDiagnosticoBodega(DiagnosticoBodega diagnosticoBodega) {
        this.diagnosticoBodega = diagnosticoBodega;
    }

    public Dates getDates() {
        return dates;
    }

    public void setDates(Dates dates) {
        this.dates = dates;
    }

    public Dates getDates1() {
        return dates1;
    }

    public void setDates1(Dates dates1) {
        this.dates1 = dates1;
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

    public MedicoBodega getMedicoBodega() {
        return medicoBodega;
    }

    public void setMedicoBodega(MedicoBodega medicoBodega) {
        this.medicoBodega = medicoBodega;
    }

    public MedicoBodega getMedicoBodega1() {
        return medicoBodega1;
    }

    public void setMedicoBodega1(MedicoBodega medicoBodega1) {
        this.medicoBodega1 = medicoBodega1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (remisionesBodegaPK != null ? remisionesBodegaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RemisionesBodega)) {
            return false;
        }
        RemisionesBodega other = (RemisionesBodega) object;
        if ((this.remisionesBodegaPK == null && other.remisionesBodegaPK != null) || (this.remisionesBodegaPK != null && !this.remisionesBodegaPK.equals(other.remisionesBodegaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades_Bodega.RemisionesBodega[ remisionesBodegaPK=" + remisionesBodegaPK + " ]";
    }
    
}
