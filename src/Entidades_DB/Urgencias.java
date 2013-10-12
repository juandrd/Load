/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades_DB;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "urgencias")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Urgencias.findAll", query = "SELECT u FROM Urgencias u"),
    @NamedQuery(name = "Urgencias.findByCodigoUrgencia", query = "SELECT u FROM Urgencias u WHERE u.codigoUrgencia = :codigoUrgencia"),
    @NamedQuery(name = "Urgencias.findByIDUsuario", query = "SELECT u FROM Urgencias u WHERE u.iDUsuario = :iDUsuario"),
    @NamedQuery(name = "Urgencias.findByFechaSolicitud", query = "SELECT u FROM Urgencias u WHERE u.fechaSolicitud = :fechaSolicitud"),
    @NamedQuery(name = "Urgencias.findByHoraSolicitud", query = "SELECT u FROM Urgencias u WHERE u.horaSolicitud = :horaSolicitud"),
    @NamedQuery(name = "Urgencias.findByFechaAtencion", query = "SELECT u FROM Urgencias u WHERE u.fechaAtencion = :fechaAtencion"),
    @NamedQuery(name = "Urgencias.findByHoraAtencion", query = "SELECT u FROM Urgencias u WHERE u.horaAtencion = :horaAtencion"),
    @NamedQuery(name = "Urgencias.findByDiagnostico", query = "SELECT u FROM Urgencias u WHERE u.diagnostico = :diagnostico")})
public class Urgencias implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Codigo_Urgencia")
    private String codigoUrgencia;
    @Column(name = "ID_Usuario")
    private String iDUsuario;
    @Column(name = "Fecha_Solicitud")
    @Temporal(TemporalType.DATE)
    private Date fechaSolicitud;
    @Column(name = "Hora_Solicitud")
    @Temporal(TemporalType.TIME)
    private Date horaSolicitud;
    @Column(name = "Fecha_Atencion")
    @Temporal(TemporalType.DATE)
    private Date fechaAtencion;
    @Column(name = "Hora_Atencion")
    @Temporal(TemporalType.TIME)
    private Date horaAtencion;
    @Column(name = "Diagnostico")
    private String diagnostico;
    @JoinColumn(name = "ID_Medico", referencedColumnName = "Cedula")
    @ManyToOne
    private Medico iDMedico;

    public Urgencias() {
    }

    public Urgencias(String codigoUrgencia) {
        this.codigoUrgencia = codigoUrgencia;
    }

    public String getCodigoUrgencia() {
        return codigoUrgencia;
    }

    public void setCodigoUrgencia(String codigoUrgencia) {
        this.codigoUrgencia = codigoUrgencia;
    }

    public String getIDUsuario() {
        return iDUsuario;
    }

    public void setIDUsuario(String iDUsuario) {
        this.iDUsuario = iDUsuario;
    }

    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public Date getHoraSolicitud() {
        return horaSolicitud;
    }

    public void setHoraSolicitud(Date horaSolicitud) {
        this.horaSolicitud = horaSolicitud;
    }

    public Date getFechaAtencion() {
        return fechaAtencion;
    }

    public void setFechaAtencion(Date fechaAtencion) {
        this.fechaAtencion = fechaAtencion;
    }

    public Date getHoraAtencion() {
        return horaAtencion;
    }

    public void setHoraAtencion(Date horaAtencion) {
        this.horaAtencion = horaAtencion;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public Medico getIDMedico() {
        return iDMedico;
    }

    public void setIDMedico(Medico iDMedico) {
        this.iDMedico = iDMedico;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoUrgencia != null ? codigoUrgencia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Urgencias)) {
            return false;
        }
        Urgencias other = (Urgencias) object;
        if ((this.codigoUrgencia == null && other.codigoUrgencia != null) || (this.codigoUrgencia != null && !this.codigoUrgencia.equals(other.codigoUrgencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades_DB.Urgencias[ codigoUrgencia=" + codigoUrgencia + " ]";
    }
    
}
