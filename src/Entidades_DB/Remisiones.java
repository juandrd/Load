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
@Table(name = "remisiones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Remisiones.findAll", query = "SELECT r FROM Remisiones r"),
    @NamedQuery(name = "Remisiones.findByCodigoRemision", query = "SELECT r FROM Remisiones r WHERE r.codigoRemision = :codigoRemision"),
    @NamedQuery(name = "Remisiones.findByIDUsuario", query = "SELECT r FROM Remisiones r WHERE r.iDUsuario = :iDUsuario"),
    @NamedQuery(name = "Remisiones.findByFechaRemision", query = "SELECT r FROM Remisiones r WHERE r.fechaRemision = :fechaRemision"),
    @NamedQuery(name = "Remisiones.findByHoraRemision", query = "SELECT r FROM Remisiones r WHERE r.horaRemision = :horaRemision"),
    @NamedQuery(name = "Remisiones.findByFechaAtencion", query = "SELECT r FROM Remisiones r WHERE r.fechaAtencion = :fechaAtencion"),
    @NamedQuery(name = "Remisiones.findByHoraAtencion", query = "SELECT r FROM Remisiones r WHERE r.horaAtencion = :horaAtencion"),
    @NamedQuery(name = "Remisiones.findByDiagnostico", query = "SELECT r FROM Remisiones r WHERE r.diagnostico = :diagnostico")})
public class Remisiones implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Codigo_Remision")
    private String codigoRemision;
    @Column(name = "ID_Usuario")
    private String iDUsuario;
    @Column(name = "Fecha_Remision")
    @Temporal(TemporalType.DATE)
    private Date fechaRemision;
    @Column(name = "Hora_Remision")
    @Temporal(TemporalType.TIME)
    private Date horaRemision;
    @Column(name = "Fecha_Atencion")
    @Temporal(TemporalType.DATE)
    private Date fechaAtencion;
    @Column(name = "Hora_Atencion")
    @Temporal(TemporalType.TIME)
    private Date horaAtencion;
    @Column(name = "Diagnostico")
    private String diagnostico;
    @JoinColumn(name = "Servicio_POS", referencedColumnName = "ID_Servicio_POS")
    @ManyToOne
    private ServiciosPos servicioPOS;
    @JoinColumn(name = "ID_Medico_Remite", referencedColumnName = "Cedula")
    @ManyToOne
    private Medico iDMedicoRemite;
    @JoinColumn(name = "ID_Medico", referencedColumnName = "Cedula")
    @ManyToOne
    private Medico iDMedico;

    public Remisiones() {
    }

    public Remisiones(String codigoRemision) {
        this.codigoRemision = codigoRemision;
    }

    public String getCodigoRemision() {
        return codigoRemision;
    }

    public void setCodigoRemision(String codigoRemision) {
        this.codigoRemision = codigoRemision;
    }

    public String getIDUsuario() {
        return iDUsuario;
    }

    public void setIDUsuario(String iDUsuario) {
        this.iDUsuario = iDUsuario;
    }

    public Date getFechaRemision() {
        return fechaRemision;
    }

    public void setFechaRemision(Date fechaRemision) {
        this.fechaRemision = fechaRemision;
    }

    public Date getHoraRemision() {
        return horaRemision;
    }

    public void setHoraRemision(Date horaRemision) {
        this.horaRemision = horaRemision;
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

    public ServiciosPos getServicioPOS() {
        return servicioPOS;
    }

    public void setServicioPOS(ServiciosPos servicioPOS) {
        this.servicioPOS = servicioPOS;
    }

    public Medico getIDMedicoRemite() {
        return iDMedicoRemite;
    }

    public void setIDMedicoRemite(Medico iDMedicoRemite) {
        this.iDMedicoRemite = iDMedicoRemite;
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
        hash += (codigoRemision != null ? codigoRemision.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Remisiones)) {
            return false;
        }
        Remisiones other = (Remisiones) object;
        if ((this.codigoRemision == null && other.codigoRemision != null) || (this.codigoRemision != null && !this.codigoRemision.equals(other.codigoRemision))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades_DB.Remisiones[ codigoRemision=" + codigoRemision + " ]";
    }
    
}
