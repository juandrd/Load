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
@Table(name = "hospitalizaciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Hospitalizaciones.findAll", query = "SELECT h FROM Hospitalizaciones h"),
    @NamedQuery(name = "Hospitalizaciones.findByCodigoHospitalizacion", query = "SELECT h FROM Hospitalizaciones h WHERE h.codigoHospitalizacion = :codigoHospitalizacion"),
    @NamedQuery(name = "Hospitalizaciones.findByIDUsuario", query = "SELECT h FROM Hospitalizaciones h WHERE h.iDUsuario = :iDUsuario"),
    @NamedQuery(name = "Hospitalizaciones.findByFechaSolicitud", query = "SELECT h FROM Hospitalizaciones h WHERE h.fechaSolicitud = :fechaSolicitud"),
    @NamedQuery(name = "Hospitalizaciones.findByHoraSolicitud", query = "SELECT h FROM Hospitalizaciones h WHERE h.horaSolicitud = :horaSolicitud"),
    @NamedQuery(name = "Hospitalizaciones.findByFechaAtencion", query = "SELECT h FROM Hospitalizaciones h WHERE h.fechaAtencion = :fechaAtencion"),
    @NamedQuery(name = "Hospitalizaciones.findByHoraAtencion", query = "SELECT h FROM Hospitalizaciones h WHERE h.horaAtencion = :horaAtencion"),
    @NamedQuery(name = "Hospitalizaciones.findByDuracionHospitalizacion", query = "SELECT h FROM Hospitalizaciones h WHERE h.duracionHospitalizacion = :duracionHospitalizacion"),
    @NamedQuery(name = "Hospitalizaciones.findByDiagnostico", query = "SELECT h FROM Hospitalizaciones h WHERE h.diagnostico = :diagnostico")})
public class Hospitalizaciones implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Codigo_Hospitalizacion")
    private String codigoHospitalizacion;
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
    @Column(name = "Duracion_Hospitalizacion")
    private Integer duracionHospitalizacion;
    @Column(name = "Diagnostico")
    private String diagnostico;
    @JoinColumn(name = "ID_Medico", referencedColumnName = "Cedula")
    @ManyToOne
    private Medico iDMedico;

    public Hospitalizaciones() {
    }

    public Hospitalizaciones(String codigoHospitalizacion) {
        this.codigoHospitalizacion = codigoHospitalizacion;
    }

    public String getCodigoHospitalizacion() {
        return codigoHospitalizacion;
    }

    public void setCodigoHospitalizacion(String codigoHospitalizacion) {
        this.codigoHospitalizacion = codigoHospitalizacion;
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

    public Integer getDuracionHospitalizacion() {
        return duracionHospitalizacion;
    }

    public void setDuracionHospitalizacion(Integer duracionHospitalizacion) {
        this.duracionHospitalizacion = duracionHospitalizacion;
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
        hash += (codigoHospitalizacion != null ? codigoHospitalizacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Hospitalizaciones)) {
            return false;
        }
        Hospitalizaciones other = (Hospitalizaciones) object;
        if ((this.codigoHospitalizacion == null && other.codigoHospitalizacion != null) || (this.codigoHospitalizacion != null && !this.codigoHospitalizacion.equals(other.codigoHospitalizacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades_DB.Hospitalizaciones[ codigoHospitalizacion=" + codigoHospitalizacion + " ]";
    }
    
}
