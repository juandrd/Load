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
@Table(name = "retiros")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Retiros.findAll", query = "SELECT r FROM Retiros r"),
    @NamedQuery(name = "Retiros.findByIDRetiro", query = "SELECT r FROM Retiros r WHERE r.iDRetiro = :iDRetiro"),
    @NamedQuery(name = "Retiros.findByFechaRetiro", query = "SELECT r FROM Retiros r WHERE r.fechaRetiro = :fechaRetiro"),
    @NamedQuery(name = "Retiros.findByCambioAEPS", query = "SELECT r FROM Retiros r WHERE r.cambioAEPS = :cambioAEPS")})
public class Retiros implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_Retiro")
    private String iDRetiro;
    @Column(name = "Fecha_Retiro")
    @Temporal(TemporalType.DATE)
    private Date fechaRetiro;
    @Column(name = "Cambio_A_EPS")
    private String cambioAEPS;
    @JoinColumn(name = "ID_Usuario", referencedColumnName = "Cedula")
    @ManyToOne
    private Cotizante iDUsuario;

    public Retiros() {
    }

    public Retiros(String iDRetiro) {
        this.iDRetiro = iDRetiro;
    }

    public String getIDRetiro() {
        return iDRetiro;
    }

    public void setIDRetiro(String iDRetiro) {
        this.iDRetiro = iDRetiro;
    }

    public Date getFechaRetiro() {
        return fechaRetiro;
    }

    public void setFechaRetiro(Date fechaRetiro) {
        this.fechaRetiro = fechaRetiro;
    }

    public String getCambioAEPS() {
        return cambioAEPS;
    }

    public void setCambioAEPS(String cambioAEPS) {
        this.cambioAEPS = cambioAEPS;
    }

    public Cotizante getIDUsuario() {
        return iDUsuario;
    }

    public void setIDUsuario(Cotizante iDUsuario) {
        this.iDUsuario = iDUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDRetiro != null ? iDRetiro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Retiros)) {
            return false;
        }
        Retiros other = (Retiros) object;
        if ((this.iDRetiro == null && other.iDRetiro != null) || (this.iDRetiro != null && !this.iDRetiro.equals(other.iDRetiro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades_DB.Retiros[ iDRetiro=" + iDRetiro + " ]";
    }
    
}
