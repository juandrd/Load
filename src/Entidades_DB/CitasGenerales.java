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
@Table(name = "citas_generales")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CitasGenerales.findAll", query = "SELECT c FROM CitasGenerales c"),
    @NamedQuery(name = "CitasGenerales.findByCodigocita", query = "SELECT c FROM CitasGenerales c WHERE c.codigocita = :codigocita"),
    @NamedQuery(name = "CitasGenerales.findByIDUsuario", query = "SELECT c FROM CitasGenerales c WHERE c.iDUsuario = :iDUsuario"),
    @NamedQuery(name = "CitasGenerales.findByFechaSolicitud", query = "SELECT c FROM CitasGenerales c WHERE c.fechaSolicitud = :fechaSolicitud"),
    @NamedQuery(name = "CitasGenerales.findByHoraSolicitud", query = "SELECT c FROM CitasGenerales c WHERE c.horaSolicitud = :horaSolicitud"),
    @NamedQuery(name = "CitasGenerales.findByFechaAtencion", query = "SELECT c FROM CitasGenerales c WHERE c.fechaAtencion = :fechaAtencion"),
    @NamedQuery(name = "CitasGenerales.findByHoraAtencion", query = "SELECT c FROM CitasGenerales c WHERE c.horaAtencion = :horaAtencion"),
    @NamedQuery(name = "CitasGenerales.findByDiagnostico", query = "SELECT c FROM CitasGenerales c WHERE c.diagnostico = :diagnostico")})
public class CitasGenerales implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Codigo_cita")
    private String codigocita;
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

    public CitasGenerales() {
    }

    public CitasGenerales(String codigocita) {
        this.codigocita = codigocita;
    }

    public String getCodigocita() {
        return codigocita;
    }

    public void setCodigocita(String codigocita) {
        this.codigocita = codigocita;
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
        hash += (codigocita != null ? codigocita.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CitasGenerales)) {
            return false;
        }
        CitasGenerales other = (CitasGenerales) object;
        if ((this.codigocita == null && other.codigocita != null) || (this.codigocita != null && !this.codigocita.equals(other.codigocita))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades_DB.CitasGenerales[ codigocita=" + codigocita + " ]";
    }
    
}
