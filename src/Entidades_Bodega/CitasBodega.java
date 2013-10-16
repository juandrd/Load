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
@Table(name = "citas_bodega")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CitasBodega.findAll", query = "SELECT c FROM CitasBodega c"),
    @NamedQuery(name = "CitasBodega.findByMedicoKey", query = "SELECT c FROM CitasBodega c WHERE c.citasBodegaPK.medicoKey = :medicoKey"),
    @NamedQuery(name = "CitasBodega.findByPacienteKey", query = "SELECT c FROM CitasBodega c WHERE c.citasBodegaPK.pacienteKey = :pacienteKey"),
    @NamedQuery(name = "CitasBodega.findByDemografiaPacienteKey", query = "SELECT c FROM CitasBodega c WHERE c.citasBodegaPK.demografiaPacienteKey = :demografiaPacienteKey"),
    @NamedQuery(name = "CitasBodega.findByFechaSolicitudKey", query = "SELECT c FROM CitasBodega c WHERE c.citasBodegaPK.fechaSolicitudKey = :fechaSolicitudKey"),
    @NamedQuery(name = "CitasBodega.findByFechaAtencionKey", query = "SELECT c FROM CitasBodega c WHERE c.citasBodegaPK.fechaAtencionKey = :fechaAtencionKey"),
    @NamedQuery(name = "CitasBodega.findByDiagnosticoKey", query = "SELECT c FROM CitasBodega c WHERE c.citasBodegaPK.diagnosticoKey = :diagnosticoKey"),
    @NamedQuery(name = "CitasBodega.findByPreexistenciaKey", query = "SELECT c FROM CitasBodega c WHERE c.citasBodegaPK.preexistenciaKey = :preexistenciaKey"),
    @NamedQuery(name = "CitasBodega.findByIpsKey", query = "SELECT c FROM CitasBodega c WHERE c.citasBodegaPK.ipsKey = :ipsKey"),
    @NamedQuery(name = "CitasBodega.findByTipoCita", query = "SELECT c FROM CitasBodega c WHERE c.tipoCita = :tipoCita"),
    @NamedQuery(name = "CitasBodega.findByTiempoEsperaAtencion", query = "SELECT c FROM CitasBodega c WHERE c.tiempoEsperaAtencion = :tiempoEsperaAtencion"),
    @NamedQuery(name = "CitasBodega.findByHoraAtencion", query = "SELECT c FROM CitasBodega c WHERE c.horaAtencion = :horaAtencion"),
    @NamedQuery(name = "CitasBodega.findByDuracionHospitalizacion", query = "SELECT c FROM CitasBodega c WHERE c.duracionHospitalizacion = :duracionHospitalizacion")})
public class CitasBodega implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CitasBodegaPK citasBodegaPK;
    @Column(name = "tipo_cita")
    private String tipoCita;
    @Column(name = "tiempo_espera_atencion")
    private Integer tiempoEsperaAtencion;
    @Column(name = "hora_atencion")
    @Temporal(TemporalType.TIME)
    private Date horaAtencion;
    @Column(name = "duracion_hospitalizacion")
    private Integer duracionHospitalizacion;
    @JoinColumn(name = "ips_key", referencedColumnName = "ips_key", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private IpsBodega ipsBodega;
    @JoinColumn(name = "preexistencia_key", referencedColumnName = "preexistencia_key", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private PreexistenciaBodega preexistenciaBodega;
    @JoinColumn(name = "diagnostico_key", referencedColumnName = "diagnostico_key", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private DiagnosticoBodega diagnosticoBodega;
    @JoinColumn(name = "fecha_atencion_key", referencedColumnName = "date_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Dates dates;
    @JoinColumn(name = "fecha_solicitud_key", referencedColumnName = "date_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Dates dates1;
    @JoinColumn(name = "demografia_paciente_key", referencedColumnName = "demografia_paciente_key", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private DemografiaPacienteBodega demografiaPacienteBodega;
    @JoinColumn(name = "paciente_key", referencedColumnName = "paciente_key", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private PacienteBodega pacienteBodega;
    @JoinColumn(name = "medico_key", referencedColumnName = "medico_key", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private MedicoBodega medicoBodega;

    public CitasBodega() {
    }

    public CitasBodega(CitasBodegaPK citasBodegaPK) {
        this.citasBodegaPK = citasBodegaPK;
    }

    public CitasBodega(int medicoKey, int pacienteKey, int demografiaPacienteKey, long fechaSolicitudKey, long fechaAtencionKey, int diagnosticoKey, int preexistenciaKey, int ipsKey) {
        this.citasBodegaPK = new CitasBodegaPK(medicoKey, pacienteKey, demografiaPacienteKey, fechaSolicitudKey, fechaAtencionKey, diagnosticoKey, preexistenciaKey, ipsKey);
    }

    public CitasBodegaPK getCitasBodegaPK() {
        return citasBodegaPK;
    }

    public void setCitasBodegaPK(CitasBodegaPK citasBodegaPK) {
        this.citasBodegaPK = citasBodegaPK;
    }

    public String getTipoCita() {
        return tipoCita;
    }

    public void setTipoCita(String tipoCita) {
        this.tipoCita = tipoCita;
    }

    public Integer getTiempoEsperaAtencion() {
        return tiempoEsperaAtencion;
    }

    public void setTiempoEsperaAtencion(Integer tiempoEsperaAtencion) {
        this.tiempoEsperaAtencion = tiempoEsperaAtencion;
    }

    public Date getHoraAtencion() {
        return horaAtencion;
    }

    public void setHoraAtencion(Date horaAtencion) {
        this.horaAtencion = horaAtencion;
    }

    public Integer getDuracionHospitalizacion() {
        return duracionHospitalizacion;
    }

    public void setDuracionHospitalizacion(Integer duracionHospitalizacion) {
        this.duracionHospitalizacion = duracionHospitalizacion;
    }

    public IpsBodega getIpsBodega() {
        return ipsBodega;
    }

    public void setIpsBodega(IpsBodega ipsBodega) {
        this.ipsBodega = ipsBodega;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (citasBodegaPK != null ? citasBodegaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CitasBodega)) {
            return false;
        }
        CitasBodega other = (CitasBodega) object;
        if ((this.citasBodegaPK == null && other.citasBodegaPK != null) || (this.citasBodegaPK != null && !this.citasBodegaPK.equals(other.citasBodegaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades_Bodega.CitasBodega[ citasBodegaPK=" + citasBodegaPK + " ]";
    }
    
}
